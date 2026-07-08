/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_243;

public class SwimmingEvent
extends EventCancellable {
    class_243 vector;

    public void setVector(class_243 vector) {
        this.vector = vector;
    }

    public class_243 getVector() {
        return this.vector;
    }

    public SwimmingEvent(class_243 vector) {
        this.vector = vector;
    }
}
