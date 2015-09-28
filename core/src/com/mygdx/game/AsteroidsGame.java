package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class AsteroidsGame extends ApplicationAdapter {
    public ShapeRenderer shapeRenderer;
	public PooledEngine entityEngine;

	
	@Override
	public void create () {
        shapeRenderer = new ShapeRenderer();
        entityEngine = new PooledEngine();
	}
}
