package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by isaac on 9/29/15.
 */
public class RenderComponent implements Component {
    public Polygon sprite;
    public Color renderColor;
}
