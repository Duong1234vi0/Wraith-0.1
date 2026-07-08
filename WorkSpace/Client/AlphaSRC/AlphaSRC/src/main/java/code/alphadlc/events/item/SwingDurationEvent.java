/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.item;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;

public class SwingDurationEvent
extends EventCancellable {
    private float animation;

    public float getAnimation() {
        return this.animation;
    }

    public void setAnimation(float animation) {
        this.animation = animation;
    }
}
