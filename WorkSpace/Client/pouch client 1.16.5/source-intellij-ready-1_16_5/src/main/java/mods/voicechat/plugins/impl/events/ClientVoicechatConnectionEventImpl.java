/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.events.ClientVoicechatConnectionEvent;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class ClientVoicechatConnectionEventImpl
extends ClientEventImpl
implements ClientVoicechatConnectionEvent {
    private final boolean connected;

    public ClientVoicechatConnectionEventImpl(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }
}

