package ua.babka.event;

import net.minecraft.util.math.Vec3d;
import ua.babka.event.Event;

public class EventUpdateVelocity
extends Event {
    private final float speed;
    private final Vec3d movementInput;

    public float getSpeed() {
        return this.speed;
    }

    public Vec3d getMovementInput() {
        return this.movementInput;
    }

    public EventUpdateVelocity(float speed, Vec3d movementInput) {
        this.speed = speed;
        this.movementInput = movementInput;
    }
}

