/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.render;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class CameraPositionEvent
implements Event {
    private class_243 pos;

    public class_243 getPos() {
        return this.pos;
    }

    public void setPos(class_243 pos) {
        this.pos = pos;
    }

    public CameraPositionEvent(class_243 pos) {
        this.pos = pos;
    }
}
