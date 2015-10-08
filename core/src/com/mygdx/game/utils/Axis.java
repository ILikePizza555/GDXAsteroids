package com.mygdx.game.utils;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Isaac on 10/5/2015.
 */
public class Axis<T> {
    public T value;
    public T max;
    public T min;

    public Axis(T max, T min) {
        this.max = max;
        this.min = min;

        this.value = null;
    }

    public Axis(T max, T min, T value) {
        this.max = max;
        this.min = min;

        this.value = value;
    }
}
