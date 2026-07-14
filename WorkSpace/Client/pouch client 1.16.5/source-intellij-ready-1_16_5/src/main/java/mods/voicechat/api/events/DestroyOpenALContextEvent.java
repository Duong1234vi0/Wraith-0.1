/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ClientEvent;

public interface DestroyOpenALContextEvent
extends ClientEvent {
    public long getContext();

    public long getDevice();
}

