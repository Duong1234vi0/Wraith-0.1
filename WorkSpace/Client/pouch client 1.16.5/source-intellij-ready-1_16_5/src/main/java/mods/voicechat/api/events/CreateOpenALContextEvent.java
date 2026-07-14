/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ClientEvent;

public interface CreateOpenALContextEvent
extends ClientEvent {
    public long getContext();

    public long getDevice();
}

