/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class FireworkEvent
implements Event {
    public class_243 vector;

    public FireworkEvent(class_243 vector) {
        this.vector = vector;
    }

    public class_243 getVector() {
        return this.vector;
    }

    public void setVector(class_243 vector) {
        this.vector = vector;
    }
}
