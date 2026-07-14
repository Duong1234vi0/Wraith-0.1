package ua.babka.event;

import ua.babka.event.Event;

public class EventChangeSprint
extends Event {
    private boolean sprinting;

    public boolean isSprinting() {
        return this.sprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public EventChangeSprint(boolean sprinting) {
        this.sprinting = sprinting;
    }
}

