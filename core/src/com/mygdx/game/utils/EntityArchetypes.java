package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.action.MoveAction;
import com.mygdx.game.components.input.KeyboardInputComponent;

import java.util.ArrayList;

/**
 * Class to hold functions that create entities
 * Created by isaac on 10/8/15.
 */
public final class EntityArchetypes {

    public static Entity spawnPlayer(World world) {
        Entity player = new Entity();

        //Define the body
        BodyDef playerBody = new BodyDef();
        playerBody.type = BodyDef.BodyType.DynamicBody;
        playerBody.position.set(0, 0);

        //Create the components
        PhysicsComponent p = new PhysicsComponent(world.createBody(playerBody), player);
        RenderComponent r = new RenderComponent();
        KeyboardInputComponent i = new KeyboardInputComponent();
        MoveAction m = new MoveAction();

        //Properties for the components
        Polygon sprite = new Polygon(new float[] {
                0, 5,
                3, -3,
                0, -2,
                -3, -3
        });

        PolygonShape fixShape = new PolygonShape();
        fixShape.set(sprite.getVertices());

        FixtureDef def = new FixtureDef();
        def.shape = fixShape;
        def.density = 1.2f;
        def.friction = 0f;
        def.restitution = 0.1f;

        //Apply properties
        p.physicsBody.createFixture(def);

        r.renderColor = Color.BLUE;
        r.sprite = sprite;

        m.lin_v = 5f;
        m.rot_v = 2f;

        //Add to player
        player.add(p);
        player.add(r);
        player.add(i);
        player.add(m);
        return player;
    }

    public static Entity spawnAsteroid(World world) {
        Entity asteroid = new Entity();

        BodyDef asteroidBody = new BodyDef();
        asteroidBody.type = BodyDef.BodyType.DynamicBody;
        asteroidBody.position.set(10, 10);

        //Create the component
        RenderComponent r = new RenderComponent();
        PhysicsComponent p = new PhysicsComponent(world.createBody(asteroidBody), asteroid);

        //Create the polygon
        {
            ArrayList<Float> polygon = new ArrayList<>();

            float totalAngle = 0f;
            while (totalAngle < 360f) {
                float distance = MathUtils.random(2, 5);
                float angle = MathUtils.random(0, 90);

                totalAngle += angle;

                polygon.add(MathUtils.cosDeg(totalAngle) * distance);
                polygon.add(MathUtils.sinDeg(totalAngle) * distance);
            }

            float[] vertices = new float[polygon.size()];
            for (int i = 0; i < polygon.size(); i++)
                vertices[i] = polygon.get(i);

            r.sprite = new Polygon(vertices);
            r.renderColor = new Color(Color.RED);
        }

        PolygonShape fixShape = new PolygonShape();
        fixShape.set(r.sprite.getVertices());

        FixtureDef def = new FixtureDef();
        def.shape = fixShape;
        def.density = 0.8f;
        def.friction = 0f;
        def.restitution = 0.8f;

        p.physicsBody.createFixture(def);

        asteroid.add(r);
        asteroid.add(p);

        return asteroid;
    }
}
