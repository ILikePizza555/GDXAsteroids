package com.mygdx.game.systems.action;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.Mappers;
import com.mygdx.game.components.PhysicsComponent;
import com.mygdx.game.components.action.MoveAction;
import com.mygdx.game.components.input.InputComponent;
import com.mygdx.game.components.input.KeyboardInputComponent;


/**
 * Created by Isaac on 10/5/2015.
 */
public class MoveActionSystem extends IteratingSystem {
    public MoveActionSystem() {
        super(Family.all(MoveAction.class, PhysicsComponent.class).one(KeyboardInputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent i = Mappers.keyInputMapper.get(entity);
        MoveAction a = Mappers.mvaMapper.get(entity);
        PhysicsComponent p = Mappers.physicsMapper.get(entity);

        Vector2 pos = p.physicsBody.getPosition();
        float angle = p.physicsBody.getAngle();
        float speed = a.lin_v * i.linearAxis.value;

        p.physicsBody.applyLinearImpulse(-1 * MathUtils.sin(angle) * speed, MathUtils.cos(angle) * speed, pos.x, pos.y, true);
        p.physicsBody.applyAngularImpulse(a.rot_v * i.rotAxis.value, true);

        if(i.brake.value == true) {
            p.physicsBody.setAngularDamping(p.brakeAngleDamp);
            p.physicsBody.setLinearDamping(p.brakeLineDamp);
        } else {
            p.physicsBody.setAngularDamping(p.movAngleDamp);
            p.physicsBody.setLinearDamping(p.movLineDamp);
        }
    }
}
