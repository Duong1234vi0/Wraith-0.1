/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.ServerEvent;

public interface VoiceHostEvent
extends ServerEvent {
    public String getVoiceHost();

    public void setVoiceHost(String var1);
}

