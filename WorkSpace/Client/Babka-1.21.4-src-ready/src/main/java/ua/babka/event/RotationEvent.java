package ua.babka.event;

import ua.babka.event.Event;

public class RotationEvent
extends Event {
    private float yaw;
    private float pitch;
    private float partialTicks;

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public RotationEvent(float yaw, float pitch, float partialTicks) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.partialTicks = partialTicks;
    }
}

