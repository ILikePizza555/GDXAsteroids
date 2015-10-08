package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.Mappers;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.RenderComponent;

/**
 * Binds the Box2D system with the render system, so that sprites render correctly
 * Created by isaac on 9/29/15.
 */
public class PhysicsBinder extends IteratingSystem {
    public PhysicsBinder() {
        super(Family.all(PhysicsComponent.class, RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent p = Mappers.physicsMapper.get(entity);
        RenderComponent r = Mappers.renderMapper.get(entity);

        Vector2 pos = p.physicsBody.getPosition();
        r.sprite.setPosition(pos.x, pos.y);
        r.sprite.setRotation(p.physicsBody.getAngle() * MathUtils.radiansToDegrees);
    }
}
