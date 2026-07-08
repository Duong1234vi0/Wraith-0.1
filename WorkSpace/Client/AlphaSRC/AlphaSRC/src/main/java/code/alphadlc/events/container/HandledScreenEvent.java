/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1735
 *  net.minecraft.class_332
 */
package code.alphadlc.events.container;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_1735;
import net.minecraft.class_332;

public class HandledScreenEvent
implements Event {
    private class_332 drawContext;
    private class_1735 slotHover;
    private int backgroundWidth;
    private int backgroundHeight;
    private int mouseX;
    private int mouseY;
    private float tickDelta;

    public class_332 getDrawContext() {
        return this.drawContext;
    }

    public class_1735 getSlotHover() {
        return this.slotHover;
    }

    public int getBackgroundWidth() {
        return this.backgroundWidth;
    }

    public int getBackgroundHeight() {
        return this.backgroundHeight;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }

    public float getTickDelta() {
        return this.tickDelta;
    }

    public HandledScreenEvent(class_332 drawContext, class_1735 slotHover, int backgroundWidth, int backgroundHeight, int mouseX, int mouseY, float tickDelta) {
        this.drawContext = drawContext;
        this.slotHover = slotHover;
        this.backgroundWidth = backgroundWidth;
        this.backgroundHeight = backgroundHeight;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.tickDelta = tickDelta;
    }
}
