/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.VoiceHostEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class VoiceHostEventImpl
extends ServerEventImpl
implements VoiceHostEvent {
    private String voiceHost;

    public VoiceHostEventImpl(String voiceHost) {
        this.voiceHost = voiceHost;
    }

    @Override
    public String getVoiceHost() {
        return this.voiceHost;
    }

    @Override
    public void setVoiceHost(String voiceHost) {
        this.voiceHost = voiceHost;
    }
}

