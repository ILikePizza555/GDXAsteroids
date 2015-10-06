package com.mygdx.game.utils;

/**
 * Created by Isaac on 10/5/2015.
 */
public class Toggle<T> {
    public final T val_active;
    public final T val_default;
    public T value;

    public Toggle(T val_active, T val_default) {
        this.val_active = val_active;
        this.val_default = val_default;
    }

    public void toggle() {
        if (value == val_default)
            value = val_active;
        else
            value = val_default;
    }
}
