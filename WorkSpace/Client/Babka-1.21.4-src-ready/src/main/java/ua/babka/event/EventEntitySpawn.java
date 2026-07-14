package ua.babka.event;

import net.minecraft.entity.Entity;
import ua.babka.event.Event;

public class EventEntitySpawn
extends Event {
    private final Entity entity;

    public Entity getEntity() {
        return this.entity;
    }

    public EventEntitySpawn(Entity entity) {
        this.entity = entity;
    }
}

