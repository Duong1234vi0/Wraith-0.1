package ua.babka.event;

import net.minecraft.util.math.BlockPos;
import ua.babka.event.Event;

public class EventObsidianPlace
extends Event {
    private final BlockPos blockPos;

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public EventObsidianPlace(BlockPos blockPos) {
        this.blockPos = blockPos;
    }
}

