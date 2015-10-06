package com.mygdx.game.components.input;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.utils.Axis;

/**
 * Component that hols input data such as axes or button presses. Contains data that all input components should have.
 * Created by Isaac on 10/5/2015.
 */
public abstract class InputComponent implements Component {
    public Axis<Float> linearAxis = new Axis<>(1f, -1f, 0f);
}
