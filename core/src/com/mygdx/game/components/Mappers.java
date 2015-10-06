package com.mygdx.game.components;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.game.components.action.MoveAction;
import com.mygdx.game.components.input.InputComponent;
import com.mygdx.game.components.input.KeyboardInputComponent;

/**
 * Created by isaac on 9/29/15.
 */
public class Mappers {
    public final static ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
    public final static ComponentMapper<RenderComponent> renderMapper = ComponentMapper.getFor(RenderComponent.class);

    public final static ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
    public final static ComponentMapper<KeyboardInputComponent> keyInputMapper = ComponentMapper.getFor(KeyboardInputComponent.class);

    public final static ComponentMapper<MoveAction>  mvaMapper = ComponentMapper.getFor(MoveAction.class);
}
