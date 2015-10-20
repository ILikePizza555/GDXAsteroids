package com.mygdx.game.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AsteroidsGame;
import com.mygdx.game.components.PhysicsComponent;

/**
 * Shows UI to the user
 * Created by isaac on 10/15/15.
 */
public class PlayerUI extends UI {
    public static boolean isGamePaused = false;

    Table showPos;
    Label posInfo;
    Label velInfo;

    ConsoleWindow consoleWindow;

    Entity player;
    AsteroidsGame game;

    public PlayerUI(Viewport vp, Entity player, AsteroidsGame game) {
        super(vp);
        this.player = player;
        this.game = game;

        setupShowPos();
        setupConsole();
    }

    private void setupShowPos() {
        posInfo = new Label("Hello!", game.defaultSkin);
        velInfo = new Label("Hello!", game.defaultSkin);

        showPos = new Table();
        showPos.setOrigin(Align.left);
        showPos.setPosition(150, 30);
        showPos.setDebug(true);

        showPos.add(posInfo);
        showPos.row();
        showPos.add(velInfo);

        ui_stage.addActor(showPos);
    }

    private void setupConsole() {
        consoleWindow = new ConsoleWindow("Developer Console", game.defaultSkin);
        ui_stage.addActor(consoleWindow);
    }


    @Override
    protected void update() {
        updateShowPos();
        updateConsole();
    }

    private void updateShowPos() {
        PhysicsComponent p = player.getComponent(PhysicsComponent.class);
        Vector2 pos = p.physicsBody.getPosition();
        Vector2 vel = p.physicsBody.getLinearVelocity();
        float angVel = p.physicsBody.getAngularVelocity();

        posInfo.setText(String.format("X: %.4f Y: %.4f", pos.x, pos.y));
        velInfo.setText(String.format("Vel Ang: %.2f Vel X: %.2f Vel Y: %.2f", angVel, vel.x, vel.y));
    }

    private void updateConsole() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            consoleWindow.setVisible(!consoleWindow.isVisible());
            isGamePaused = consoleWindow.isVisible();
        }
    }
}
