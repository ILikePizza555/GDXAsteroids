package com.mygdx.game.entity.archetypes;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;

import java.util.ArrayList;

/**
 * Created by isaac on 10/29/15.
 */
public class AsteroidArchetype extends Archetype {
    private World physicsWorld;

    //Save memory
    private ArrayList<Float> verts = null;

    public AsteroidArchetype(World physicsWorld) {
        this.physicsWorld = physicsWorld;
    }

    private float[] buildPolygon() {
        verts = new ArrayList<>();

        float totalAngle = 0f;
        while (totalAngle < 360f) {
            float distance = MathUtils.random(2f, 8f);
            float angle = MathUtils.random(5f, 90f);

            totalAngle += angle;

            if(totalAngle < 360f) {
                verts.add(MathUtils.cosDeg(totalAngle) * distance);
                verts.add(MathUtils.sinDeg(totalAngle) * distance);
            }

        }

        float[] polygon = new float[verts.size()];
        for (int i = 0; i < verts.size(); i++)
            polygon[i] = verts.get(i);

        return polygon;
    }

    private void triangulateFixtures(PhysicsComponent p, float[] polygon) {
        PolygonShape triangle;
        float[] verts;
        for(int i = 2; i < polygon.length+ 2; i+=2) {
            if(i >= 4) {
                verts = new float[]{
                        0, 0,
                        polygon[i - 2], polygon[i - 1],
                        polygon[i - 4], polygon[i - 3]
                };
            } else {
                verts = new float[]{
                        0, 0,
                        polygon[i - 2], polygon[i - 1],
                        polygon[polygon.length-2], polygon[polygon.length-1]
                };
            }
            triangle = new PolygonShape();
            triangle.set(verts);
            p.physicsBody.createFixture(triangle, 0.3f);
            triangle.dispose();
        }
    }

    private void setSpeed(PhysicsComponent p) {
        float speed = MathUtils.random(100f, 1000f);
        float angle = MathUtils.random(0f, 360f);

        Vector2 pos = p.physicsBody.getPosition();

        p.physicsBody.applyLinearImpulse(-1 * MathUtils.sinDeg(angle) * speed, MathUtils.cosDeg(angle) * speed, pos.x, pos.y, true);
    }

    @Override
    public Entity buildEntity() {
        Entity asteroid = new Entity();

        BodyDef asteroidBody = new BodyDef();
        asteroidBody.type = BodyDef.BodyType.DynamicBody;
        asteroidBody.position.set(10, 10);

        //Create the component
        RenderComponent r = new RenderComponent();
        PhysicsComponent p = new PhysicsComponent(physicsWorld.createBody(asteroidBody), asteroid);

        //Create the polygon
        float[] polygon = buildPolygon();
        triangulateFixtures(p, polygon);

        setSpeed(p);

        r.sprite = new Polygon(polygon);
        r.renderColor = new Color(Color.RED);

        asteroid.add(r);
        asteroid.add(p);

        return asteroid;
    }
}
