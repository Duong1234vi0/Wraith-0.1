/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_243;

public class PlayerTravelEvent
extends EventCancellable {
    private class_243 motion;
    private final boolean pre;

    public PlayerTravelEvent(class_243 motion, boolean pre) {
        this.motion = motion;
        this.pre = pre;
    }

    public class_243 getMotion() {
        return this.motion;
    }

    public void setMotion(class_243 motion) {
        this.motion = motion;
    }

    public boolean isPre() {
        return this.pre;
    }
}
