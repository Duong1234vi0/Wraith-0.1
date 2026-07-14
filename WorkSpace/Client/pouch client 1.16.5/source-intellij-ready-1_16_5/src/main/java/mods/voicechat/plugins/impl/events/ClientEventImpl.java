/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VoicechatClientApi;
import mods.voicechat.api.events.ClientEvent;
import mods.voicechat.plugins.impl.VoicechatClientApiImpl;
import mods.voicechat.plugins.impl.events.EventImpl;

public class ClientEventImpl
extends EventImpl
implements ClientEvent {
    @Override
    public VoicechatClientApi getVoicechat() {
        return VoicechatClientApiImpl.instance();
    }
}

