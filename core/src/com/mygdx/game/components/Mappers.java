package com.mygdx.game.components;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.game.components.actions.MoveBackward;
import com.mygdx.game.components.actions.MoveForward;

/**
 * Created by isaac on 9/29/15.
 */
public class Mappers {
    public final static ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
    public final static ComponentMapper<RenderComponent> renderMapper = ComponentMapper.getFor(RenderComponent.class);
    public final static ComponentMapper<KeyboardInputController> kbInputMapper = ComponentMapper.getFor(KeyboardInputController.class);

    public final static ComponentMapper<MoveForward> moveFMapper = ComponentMapper.getFor(MoveForward.class);
    public final static ComponentMapper<MoveBackward> moveBMapper = ComponentMapper.getFor(MoveBackward.class);

}
