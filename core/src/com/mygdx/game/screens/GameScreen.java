package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AsteroidsGame;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.systems.KeyboardInputSystem;
import com.mygdx.game.systems.PhysicsBinder;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.action.MoveActionSystem;
import com.mygdx.game.utils.EntityArchetypes;


/**
 * Created by Isaac on 9/27/2015.
 */
public class GameScreen implements Screen {

    public static boolean DEBUG_ORIGIN = true;
    public static boolean DEBUG_BOX2D = true;
    public static boolean DEBUG_SHOWPOS = true;

    public final AsteroidsGame game;

    OrthographicCamera camera;
    ScreenViewport viewport;

    Engine entityEngine;
    Entity player;
    World world;

    //Debug
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public GameScreen(AsteroidsGame game) {
        //Init work
        this.game = game;

        camera = new OrthographicCamera();
        camera.zoom = 0.15f;

        viewport = new ScreenViewport(camera);
        viewport.apply(false);

        entityEngine = new Engine();
        world = new World(new Vector2(0, 0), true);

        //Add systems
        entityEngine.addSystem(new KeyboardInputSystem());
        entityEngine.addSystem(new MoveActionSystem());
        entityEngine.addSystem(new PhysicsBinder());
        entityEngine.addSystem(new RenderSystem(this));

        //Add entities
        player = EntityArchetypes.spawnPlayer(world);
        entityEngine.addEntity(EntityArchetypes.spawnAsteroid(world));
        entityEngine.addEntity(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.shapeRenderer.setProjectionMatrix(camera.combined);

        world.step(1/60f, 6, 2);
        entityEngine.update(delta);

        if(DEBUG_ORIGIN) {
            game.shapeRenderer.begin();
            game.shapeRenderer.setColor(Color.BLUE);
            game.shapeRenderer.line(0f, 0f, 0f, 1f);
            game.shapeRenderer.setColor(Color.RED);
            game.shapeRenderer.line(0f, 0f, 1f, 0f);
            game.shapeRenderer.end();
        }

        if(DEBUG_BOX2D) {
            debugRenderer.render(world, camera.combined);
        }

        if(DEBUG_SHOWPOS) {
            PhysicsComponent p = player.getComponent(PhysicsComponent.class);
            Vector2 pos = p.physicsBody.getPosition();
            Vector2 vel = p.physicsBody.getLinearVelocity();
            float angVel = p.physicsBody.getAngularVelocity();

            game.spriteBatch.begin();
            game.spriteBatch.setColor(Color.WHITE);
            game.defaultFont.draw(game.spriteBatch, String.format("X: %.4f Y: %.4f", pos.x, pos.y),
                    10, Gdx.graphics.getHeight() - 5);
            game.defaultFont.draw(game.spriteBatch, String.format("Vel Ang: %.2f Vel X:  %.2f Vel Y:  %.2f", angVel, vel.x, vel.y),
                    10, Gdx.graphics.getHeight() - 20);
            game.spriteBatch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
