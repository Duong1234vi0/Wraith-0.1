/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.ClientVoicechatSocket;
import mods.voicechat.api.events.ClientVoicechatInitializationEvent;
import mods.voicechat.plugins.impl.events.ClientEventImpl;

public class ClientVoicechatInitializationEventImpl
extends ClientEventImpl
implements ClientVoicechatInitializationEvent {
    @Nullable
    private ClientVoicechatSocket socketImplementation;

    @Override
    public void setSocketImplementation(ClientVoicechatSocket socket) {
        this.socketImplementation = socket;
    }

    @Override
    @Nullable
    public ClientVoicechatSocket getSocketImplementation() {
        return this.socketImplementation;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}

