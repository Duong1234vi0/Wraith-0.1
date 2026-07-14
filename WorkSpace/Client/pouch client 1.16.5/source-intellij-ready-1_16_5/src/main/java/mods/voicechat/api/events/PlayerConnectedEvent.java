/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.ServerEvent;

public interface PlayerConnectedEvent
extends ServerEvent {
    public VoicechatConnection getConnection();
}

