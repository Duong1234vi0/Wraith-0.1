/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.render;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;

public class AspectRatioEvent
extends EventCancellable {
    private float ratio;

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
