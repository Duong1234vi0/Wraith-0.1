/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import lightning.product.D_1098_v;
import lightning.product.g_221_o;

public final class RenderEvent {
    private final float partialTicks;
    private final D_1098_v projectionMatrix;
    private final g_221_o modelViewStack;

    public RenderEvent(float partialTicks, g_221_o modelViewStack, D_1098_v projectionMatrix) {
        this.partialTicks = partialTicks;
        this.modelViewStack = modelViewStack;
        this.projectionMatrix = projectionMatrix;
    }

    public final float getPartialTicks() {
        return this.partialTicks;
    }

    public g_221_o getModelViewStack() {
        return this.modelViewStack;
    }

    public D_1098_v getProjectionMatrix() {
        return this.projectionMatrix;
    }
}

