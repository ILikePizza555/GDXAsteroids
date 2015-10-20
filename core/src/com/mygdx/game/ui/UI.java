package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Container for a stage to hold widgets and stuff
 * Created by isaac on 10/14/15.
 */
public abstract class UI {
    protected Stage ui_stage;

    public UI() {
        ui_stage = new Stage();
    }

    public UI(Viewport vp) {
        ui_stage = new Stage(vp);
        Gdx.input.setInputProcessor(ui_stage);
    }

    public void resize(int width, int height) {
        ui_stage.getViewport().update(width, height);
    }

    protected abstract void update();

    public void render() {
        try {
            update();
            ui_stage.act();
            ui_stage.draw();
        } catch (Exception e) {
            System.err.format("[UI] Caught unhandled %s: %s%n", e.getClass().getTypeName(), e.getLocalizedMessage());
        }
    }

    public void dispose() {
        ui_stage.dispose();
    }
}
