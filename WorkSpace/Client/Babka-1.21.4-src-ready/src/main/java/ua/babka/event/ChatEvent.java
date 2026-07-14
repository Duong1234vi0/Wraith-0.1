package ua.babka.event;

import ua.babka.event.Event;

public class ChatEvent
extends Event {
    private final String message;

    public String getMessage() {
        return this.message;
    }

    public ChatEvent(String message) {
        this.message = message;
    }
}

