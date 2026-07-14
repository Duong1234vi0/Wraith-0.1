/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.VoicechatServerStartedEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class VoicechatServerStartedEventImpl
extends ServerEventImpl
implements VoicechatServerStartedEvent {
    @Override
    public boolean isCancellable() {
        return false;
    }
}

