package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.Mappers;
import com.mygdx.game.components.SpawnerComponent;

import java.util.HashMap;

/**
 * Created by isaac on 10/29/15.
 */
public class SpawnerSystem extends IteratingSystem {
    HashMap<Entity, Float> timers;
    Engine engine;

    public SpawnerSystem(Engine engine) {
        super(Family.all(SpawnerComponent.class).get());
        timers = new HashMap<>();

        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpawnerComponent sp = Mappers.spawnerMapper.get(entity);

        if(timers.containsKey(entity)) {
            //Old time
            float time = timers.get(entity);
            System.out.printf("Entity: %d, time: %f%n", entity.getId(), time);

            if(time >= sp.rate) {
                //Timer was hit
                engine.addEntity(sp.archetype.buildEntity());
                timers.replace(entity, 0f);
            } else {
                //Timer is going
                timers.replace(entity, time + deltaTime);
            }
        } else
            timers.put(entity, 0f);
    }
}
