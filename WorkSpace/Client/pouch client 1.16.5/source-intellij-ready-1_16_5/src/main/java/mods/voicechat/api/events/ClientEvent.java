/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.VoicechatClientApi;
import mods.voicechat.api.events.Event;

public interface ClientEvent
extends Event {
    public VoicechatClientApi getVoicechat();
}

