package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AsteroidsGame;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.systems.PhysicsBinder;
import com.mygdx.game.systems.RenderSystem;


/**
 * Created by Isaac on 9/27/2015.
 */
public class GameScreen implements Screen {

    public final AsteroidsGame game;

    OrthographicCamera camera;
    ScreenViewport viewport;

    Engine entityEngine;
    World world;

    //Debug
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public GameScreen(AsteroidsGame game) {
        //Init work
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply(true);

        entityEngine = new Engine();
        world = new World(new Vector2(0, 0), true);

        //Add systems
        entityEngine.addSystem(new PhysicsBinder());
        entityEngine.addSystem(new RenderSystem(this));

        //Add entities
        entityEngine.addEntity(spawnPlayer());
    }

    private Entity spawnPlayer() {
        Entity player = new Entity();

        //Define the body
        BodyDef playerBody = new BodyDef();
        playerBody.type = BodyDef.BodyType.DynamicBody;
        playerBody.position.set(300, 300);

        //Create the components
        PhysicsComponent p = new PhysicsComponent(world.createBody(playerBody), player);
        RenderComponent r = new RenderComponent();

        //Properties for the components
        Polygon sprite = new Polygon(new float[] {
                -40, 40,
                40, 40,
                40, -40,
                -40, -40
        });

        PolygonShape fixShape = new PolygonShape();
        fixShape.set(sprite.getVertices());

        FixtureDef def = new FixtureDef();
        def.shape = fixShape;
        def.density = 0.5f;
        def.friction = 0.01f;
        def.restitution = 0.6f;

        //Apply properties
        p.physicsBody.createFixture(def);

        r.renderColor = Color.BLUE;
        r.sprite = sprite;

        //Add to player
        player.add(p);
        player.add(r);
        return player;
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

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
