package com.mygdx.game.components;

import com.mygdx.game.entity.archetypes.Archetype;

/**
 *
 * Created by isaac on 10/29/15.
 */
public class SpawnerComponent {
    public Archetype archetype;
    public int rate;

    public SpawnerComponent(Archetype archetype) {
        this.archetype = archetype;
        rate = 1000;
    }

    /**
     * Creates a new SpawnerComponent
     * @param archetype The archetype from which to spawn entities
     * @param rate The rate in ms to spawn entities
     */
    public SpawnerComponent(Archetype archetype, int rate) {
        this.archetype = archetype;
        this.rate = rate;
    }
}
