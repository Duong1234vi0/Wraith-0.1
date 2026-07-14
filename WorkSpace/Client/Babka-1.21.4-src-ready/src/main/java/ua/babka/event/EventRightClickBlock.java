package ua.babka.event;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import ua.babka.event.Event;

public class EventRightClickBlock
extends Event {
    private final Hand hand;
    private final BlockHitResult hitResult;

    public Hand getHand() {
        return this.hand;
    }

    public BlockHitResult getHitResult() {
        return this.hitResult;
    }

    public EventRightClickBlock(Hand hand, BlockHitResult hitResult) {
        this.hand = hand;
        this.hitResult = hitResult;
    }
}

