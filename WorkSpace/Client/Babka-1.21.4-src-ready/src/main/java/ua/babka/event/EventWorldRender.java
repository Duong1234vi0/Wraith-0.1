package ua.babka.event;

import net.minecraft.client.util.math.MatrixStack;
import ua.babka.event.Event;

public class EventWorldRender
extends Event {
    private final MatrixStack matrixStack;
    private final float tickDelta;

    public MatrixStack getMatrixStack() {
        return this.matrixStack;
    }

    public float getTickDelta() {
        return this.tickDelta;
    }

    public EventWorldRender(MatrixStack matrixStack, float tickDelta) {
        this.matrixStack = matrixStack;
        this.tickDelta = tickDelta;
    }
}

