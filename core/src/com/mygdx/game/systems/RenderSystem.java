package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.mygdx.game.components.Mappers;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by isaac on 9/29/15.
 */
public class RenderSystem extends IteratingSystem {

    GameScreen screen;

    public RenderSystem(GameScreen screen) {
        super(Family.all(RenderComponent.class).get());

        this.screen = screen;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent r = Mappers.renderMapper.get(entity);

        screen.game.shapeRenderer.setColor(r.renderColor);
        screen.game.shapeRenderer.setAutoShapeType(true);

        screen.game.shapeRenderer.begin();
        screen.game.shapeRenderer.polygon(r.sprite.getTransformedVertices());
        screen.game.shapeRenderer.end();
    }
}
