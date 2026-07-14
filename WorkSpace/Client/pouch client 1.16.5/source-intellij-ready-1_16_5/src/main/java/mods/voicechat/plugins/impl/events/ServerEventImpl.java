/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VoicechatServerApi;
import mods.voicechat.api.events.ServerEvent;
import mods.voicechat.plugins.impl.VoicechatServerApiImpl;
import mods.voicechat.plugins.impl.events.EventImpl;

public class ServerEventImpl
extends EventImpl
implements ServerEvent {
    @Override
    public VoicechatServerApi getVoicechat() {
        return VoicechatServerApiImpl.instance();
    }
}

