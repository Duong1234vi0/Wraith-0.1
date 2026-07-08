/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.chat;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;

public class ChatEvent
extends EventCancellable {
    private String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
