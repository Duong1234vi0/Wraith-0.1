/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.VoicechatServerApi;
import mods.voicechat.api.events.Event;

public interface ServerEvent
extends Event {
    public VoicechatServerApi getVoicechat();
}

