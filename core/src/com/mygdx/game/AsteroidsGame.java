package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.GameScreen;

import java.text.DecimalFormat;

public class AsteroidsGame extends Game {
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont defaultFont;


    @Override
	public void create () {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        defaultFont = new BitmapFont();

        this.setScreen(new GameScreen(this));
	}
}
