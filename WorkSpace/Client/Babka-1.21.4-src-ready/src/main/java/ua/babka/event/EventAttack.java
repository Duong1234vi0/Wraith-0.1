package ua.babka.event;

import net.minecraft.entity.Entity;
import ua.babka.event.Event;

public class EventAttack
extends Event {
    private final Entity entity;

    public Entity getEntity() {
        return this.entity;
    }

    public EventAttack(Entity entity) {
        this.entity = entity;
    }
}

