/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatSocket;
import mods.voicechat.api.events.VoicechatServerStartingEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class VoicechatServerStartingEventImpl
extends ServerEventImpl
implements VoicechatServerStartingEvent {
    @Nullable
    private VoicechatSocket socketImplementation;

    @Override
    public void setSocketImplementation(VoicechatSocket socket) {
        this.socketImplementation = socket;
    }

    @Override
    @Nullable
    public VoicechatSocket getSocketImplementation() {
        return this.socketImplementation;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}

