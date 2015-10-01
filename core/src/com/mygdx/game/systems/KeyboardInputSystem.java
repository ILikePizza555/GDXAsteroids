package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.components.Action;
import com.mygdx.game.components.KeyboardInputController;
import com.mygdx.game.components.Mappers;

import java.util.Map;

/**
 * Created by isaac on 10/1/15.
 */
public class KeyboardInputSystem extends IteratingSystem {

    public KeyboardInputSystem() {
        super(Family.one(KeyboardInputController.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        KeyboardInputController keyboardInputController = Mappers.kbInputMapper.get(entity);

        for (Map.Entry<Integer, Action> binding : keyboardInputController.keyBinds.entrySet()) {
            if (Gdx.input.isKeyPressed(binding.getKey())) {
                binding.getValue().activate();
            } else {
                binding.getValue().deactivate();
            }
        }
    }
}
