/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ClientEvent;

public interface ClientVoicechatConnectionEvent
extends ClientEvent {
    public boolean isConnected();
}

