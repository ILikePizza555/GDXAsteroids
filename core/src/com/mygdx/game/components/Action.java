package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

/**
 * Defines an action. Activate() and Deactivate() are only for Controllers.
 * Created by isaac on 10/1/15.
 */
public abstract class Action implements Component {
    public abstract void activate();
    public abstract void deactivate();
}
