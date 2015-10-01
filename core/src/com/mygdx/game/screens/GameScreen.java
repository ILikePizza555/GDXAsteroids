package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AsteroidsGame;
import com.mygdx.game.components.KeyboardInputController;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.actions.MoveBackward;
import com.mygdx.game.components.actions.MoveForward;
import com.mygdx.game.systems.KeyboardInputSystem;
import com.mygdx.game.systems.PhysicsBinder;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.actions.MoveHandler;


/**
 * Created by Isaac on 9/27/2015.
 */
public class GameScreen implements Screen {

    public static boolean DEBUG_ORIGIN = true;
    public static boolean DEBUG_BOX2D = true;

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
        viewport.apply(false);

        entityEngine = new Engine();
        world = new World(new Vector2(0, 0), true);

        //Add systems
        entityEngine.addSystem(new KeyboardInputSystem());
        entityEngine.addSystem(new MoveHandler());
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
        playerBody.position.set(0, 0);

        //Create the components
        PhysicsComponent p = new PhysicsComponent(world.createBody(playerBody), player);
        RenderComponent r = new RenderComponent();
        MoveForward mv_f = new MoveForward(100);
        MoveBackward mv_b = new MoveBackward(100);
        KeyboardInputController k = new KeyboardInputController();

        //Properties for the components
        Polygon sprite = new Polygon(new float[] {
                0, 25,
                15, -15,
                0, -10,
                -15, -15
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

        k.addKeyBind(Input.Keys.W, mv_f);
        k.addKeyBind(Input.Keys.S, mv_b);

        //Add to player
        player.add(p);
        player.add(r);
        player.add(mv_b);
        player.add(mv_f);
        player.add(k);
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

        if(DEBUG_ORIGIN) {
            game.shapeRenderer.begin();
            game.shapeRenderer.setColor(Color.BLUE);
            game.shapeRenderer.line(0f, 0f, 0f, 50f);
            game.shapeRenderer.setColor(Color.RED);
            game.shapeRenderer.line(0f, 0f, 50f, 0f);
            game.shapeRenderer.end();
        }

        if(DEBUG_BOX2D) {
            debugRenderer.render(world, camera.combined);
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
