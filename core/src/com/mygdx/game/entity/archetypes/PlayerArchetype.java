package com.mygdx.game.entity.archetypes;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.action.MoveAction;
import com.mygdx.game.components.input.KeyboardInputComponent;

/**
 * Created by isaac on 10/29/15.
 */
public class PlayerArchetype extends Archetype {

    public World physicsWorld;

    public PlayerArchetype(World physicsWorld) {
        this.physicsWorld = physicsWorld;
    }

    //TODO: Refactor into multiple functions
    @Override
    public Entity buildEntity() {
        Entity player = new Entity();

        //Define the body
        BodyDef playerBody = new BodyDef();
        playerBody.type = BodyDef.BodyType.DynamicBody;
        playerBody.position.set(0, 0);

        //Create the components
        PhysicsComponent p = new PhysicsComponent(physicsWorld.createBody(playerBody), player);
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

    public static Entity spawnPlayer(World world) {
        return (new PlayerArchetype(world)).buildEntity();
    }
}
