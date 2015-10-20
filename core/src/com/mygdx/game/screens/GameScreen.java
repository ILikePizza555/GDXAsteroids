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
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.systems.KeyboardInputSystem;
import com.mygdx.game.systems.PhysicsBinder;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.action.MoveActionSystem;
import com.mygdx.game.ui.PlayerUI;
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
    PlayerUI ui;
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

        ui = new PlayerUI(new ScreenViewport(), player, game);
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

        if(!PlayerUI.isGamePaused) {
            world.step(1 / 60f, 6, 2);
            entityEngine.update(delta);
        } else {
            entityEngine.getSystem(RenderSystem.class).update(delta);
        }

        ui.render();

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
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
        ui.resize(width, height);
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
