package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.GameScreen;

public class AsteroidsGame extends Game {
    public ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
        shapeRenderer = new ShapeRenderer();

        this.setScreen(new GameScreen(this));
	}
}
