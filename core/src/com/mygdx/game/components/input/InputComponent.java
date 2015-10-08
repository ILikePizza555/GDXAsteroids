package com.mygdx.game.components.input;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.utils.Axis;
import com.mygdx.game.utils.Toggle;

/**
 * Component that hols input data such as axes or button presses. Contains data that all input components should have.
 * Created by Isaac on 10/5/2015.
 */
public abstract class InputComponent implements Component {
    public Axis<Float> linearAxis = new Axis<>(1f, -1f, 0f);
    public Axis<Float> rotAxis = new Axis<Float>(1f, -1f, 0f);

    public Toggle<Boolean> brake = new Toggle<>(true, false);
}
