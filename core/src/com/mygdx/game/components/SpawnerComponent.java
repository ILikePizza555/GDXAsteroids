package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.entity.archetypes.Archetype;

/**
 *
 * Created by isaac on 10/29/15.
 */
public class SpawnerComponent implements Component {
    public Archetype archetype;
    public float rate;

    public SpawnerComponent(Archetype archetype) {
        this.archetype = archetype;
        rate = 1.0f;
    }

    /**
     * Creates a new SpawnerComponent
     * @param archetype The archetype from which to spawn entities
     * @param rate The rate in seconds to spawn entities
     */
    public SpawnerComponent(Archetype archetype, float rate) {
        this.archetype = archetype;
        this.rate = rate;
    }
}
