package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/**
 * Created by isaac on 10/19/15.
 */
public class ConsoleWindow extends Window {
    private ScrollPane scrollPane;
    private Table consoleOut;
    private Array<Label> outputs;
    private TextField consoleIn;
    private TextButton submitButton;

    private Skin skin;
    private ConsoleInputProcessor inputProcessor;

    public interface ConsoleInputProcessor {
        void processInput(String inputString, ConsoleWindow instance);
    }

    private class DefaultConsoleInputProcessor implements ConsoleInputProcessor {
        @Override
        public void processInput(String inputString, ConsoleWindow instance) {
            instance.out(inputString);
        }
    }

    public ConsoleWindow(String title, Skin skin) {
        super(title, skin);
        this.skin = skin;
        this.setInputProcessor(new DefaultConsoleInputProcessor());
        setup();
    }

    public ConsoleWindow(String title, ConsoleInputProcessor inputProcessor, Skin skin) {
        super(title, skin);
        this.skin = skin;
        this.inputProcessor = inputProcessor;
        setup();
    }

    private void setup() {
        this.setResizable(true);
        this.setMovable(true);
        this.setSize(450, 350);
        this.setPosition(0, Gdx.graphics.getHeight());

        outputs = new Array<>();
        outputs.add(new Label("Console initialized.", skin));

        consoleOut = new Table(skin);

        consoleIn = new TextField("", skin);

        scrollPane = new ScrollPane(consoleOut, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollbarsOnTop(false);
        scrollPane.setOverscroll(false, false);

        submitButton = new TextButton("Submit", skin);
        final ConsoleWindow self = this; //For the anonymous class
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                inputProcessor.processInput(consoleIn.getText(), self);
                consoleIn.setText("");
            }
        });
        this.add(scrollPane).expand().fill().pad(4);
        this.row();
        this.add(consoleIn).fillX().pad(4);
        this.add(submitButton).pad(4);
    }

    @Override
    public void act(float dt) {
        if(this.isVisible()) {
            consoleOut.clear();
            consoleOut.top().left().row();

            for (Label l : outputs) {
                consoleOut.add(l).expandX().fillX().top().left().pad(1).row();
            }

            scrollPane.validate();
            scrollPane.setScrollPercentY(1);
        }
    }

    private void addLabel(String text, Skin skin, String font, Color color) {
        Label l = new Label(text, skin, font, color);
        l.setWrap(true);
        outputs.add(l);
    }

    public final void out(String text) {
        addLabel(text, this.skin, "default-font", Color.WHITE);
    }

    public void info(String text) {
        addLabel(text, this.skin, "default-font", Color.BLUE);
    }

    public void warn(String text) {
        addLabel(text, this.skin, "default-font", Color.YELLOW);
    }

    public void err(String text) {
        addLabel(text, this.skin, "default-font", Color.RED);
    }

    public void setInputProcessor(ConsoleInputProcessor ip) {
        this.inputProcessor = ip;
    }
}
