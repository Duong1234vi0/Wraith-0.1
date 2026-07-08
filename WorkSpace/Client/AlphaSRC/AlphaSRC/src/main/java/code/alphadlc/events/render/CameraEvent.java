/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.render;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import code.alphadlc.utils.features.aura.warp.Turns;

public class CameraEvent
extends EventCancellable {
    private boolean cameraClip;
    private float distance;
    private Turns angle;

    public boolean isCameraClip() {
        return this.cameraClip;
    }

    public float getDistance() {
        return this.distance;
    }

    public Turns getAngle() {
        return this.angle;
    }

    public void setCameraClip(boolean cameraClip) {
        this.cameraClip = cameraClip;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setAngle(Turns angle) {
        this.angle = angle;
    }

    public CameraEvent(boolean cameraClip, float distance, Turns angle) {
        this.cameraClip = cameraClip;
        this.distance = distance;
        this.angle = angle;
    }
}
