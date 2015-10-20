package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.GameScreen;

import java.text.DecimalFormat;

public class AsteroidsGame extends Game {
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont defaultFont;

    public Skin defaultSkin;

    @Override
	public void create () {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        defaultFont = new BitmapFont();
        defaultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        this.setScreen(new GameScreen(this));
	}
}
