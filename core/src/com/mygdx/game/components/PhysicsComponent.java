package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Component to contain Box2D information
 * Created by isaac on 9/28/15.
 */
public class PhysicsComponent implements Component {
    public Body physicsBody;

    public float movAngleDamp = 0.1f;
    public float movLineDamp = 0.1f;

    public float brakeAngleDamp = 2f;
    public float brakeLineDamp = 3f;

    public PhysicsComponent(Body body, Entity ent) {
        physicsBody = body;
        physicsBody.setUserData(ent);
    }
}
