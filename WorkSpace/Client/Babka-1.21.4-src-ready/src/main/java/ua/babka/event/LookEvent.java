package ua.babka.event;

import ua.babka.event.Event;

public class LookEvent
extends Event {
    private double yaw;
    private double pitch;

    public double getYaw() {
        return this.yaw;
    }

    public double getPitch() {
        return this.pitch;
    }

    public LookEvent(double yaw, double pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}

