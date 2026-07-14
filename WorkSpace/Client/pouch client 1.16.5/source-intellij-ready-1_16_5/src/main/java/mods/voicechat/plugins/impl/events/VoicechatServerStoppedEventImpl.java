/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.VoicechatServerStoppedEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class VoicechatServerStoppedEventImpl
extends ServerEventImpl
implements VoicechatServerStoppedEvent {
    @Override
    public boolean isCancellable() {
        return false;
    }
}

