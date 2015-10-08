package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.components.Mappers;
import com.mygdx.game.components.input.KeyboardInputComponent;

/**
 * Created by Isaac on 10/5/2015.
 */
public class KeyboardInputSystem extends IteratingSystem {

    public KeyboardInputSystem() {
        super(Family.all(KeyboardInputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //TODO: See if I can abstract this process somehow (annotations?)
        KeyboardInputComponent i = Mappers.keyInputMapper.get(entity);

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            i.linearAxis.value = i.linearAxis.max;
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            i.linearAxis.value = i.linearAxis.min;
        } else {
            i.linearAxis.value = 0f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            i.rotAxis.value = i.rotAxis.max;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            i.rotAxis.value = i.rotAxis.min;
        } else {
            i.rotAxis.value = 0f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            i.brake.toggle();
        } else {
            i.brake.value = i.brake.val_default;
        }

        i.linearAxis.value = MathUtils.clamp(i.linearAxis.value, i.linearAxis.min, i.linearAxis.max);
        i.rotAxis.value = MathUtils.clamp(i.rotAxis.value, i.rotAxis.min, i.rotAxis.max);
    }
}
