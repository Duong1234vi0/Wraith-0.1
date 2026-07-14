package ua.babka.event;

import ua.babka.event.Event;

public class EventKeyInput
extends Event {
    private final int key;
    private final int action;

    public int getKey() {
        return this.key;
    }

    public int getAction() {
        return this.action;
    }

    public EventKeyInput(int key, int action) {
        this.key = key;
        this.action = action;
    }
}

