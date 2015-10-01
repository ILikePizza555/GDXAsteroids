package com.mygdx.game.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * Created by isaac on 9/29/15.
 */
public class Mappers {
    public final static ComponentMapper<PhysicsComponent> physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
    public final static ComponentMapper<RenderComponent> renderMapper = ComponentMapper.getFor(RenderComponent.class);
}
