package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
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

        ArrayList<Float> polygon = new ArrayList<>();

        float totalAngle = 0f;
        while (totalAngle < 360f) {
            float distance = MathUtils.random(5, 8);
            float angle = MathUtils.random(0, 90);

            totalAngle += angle;

            polygon.add(MathUtils.cosDeg(totalAngle) * distance);
            polygon.add(MathUtils.sinDeg(totalAngle) * distance);

        }

        PolygonShape triangle;
        float[] verts;
        for(int i = 2; i < polygon.size() + 2; i+=2) {
            if(i >= 4) {
                verts = new float[]{
                        0, 0,
                        polygon.get(i - 2), polygon.get(i - 1),
                        polygon.get(i - 4), polygon.get(i - 3)
                };
            } else {
                verts = new float[]{
                        0, 0,
                        polygon.get(i - 2), polygon.get(i - 1),
                        polygon.get(polygon.size()-2), polygon.get(polygon.size()-1)
                };
            }
            triangle = new PolygonShape();
            triangle.set(verts);
            p.physicsBody.createFixture(triangle, 0.3f);
            triangle.dispose();
        }

        float[] vertices = new float[polygon.size()];
        for (int i = 0; i < polygon.size(); i++)
            vertices[i] = polygon.get(i);

        r.sprite = new Polygon(vertices);
        r.renderColor = new Color(Color.RED);

        asteroid.add(r);
        asteroid.add(p);

        return asteroid;
    }
}
