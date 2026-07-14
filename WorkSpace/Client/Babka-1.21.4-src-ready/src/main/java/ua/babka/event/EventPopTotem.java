package ua.babka.event;

import net.minecraft.entity.player.PlayerEntity;
import ua.babka.event.Event;

public class EventPopTotem
extends Event {
    private final PlayerEntity player;

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public EventPopTotem(PlayerEntity player) {
        this.player = player;
    }
}

