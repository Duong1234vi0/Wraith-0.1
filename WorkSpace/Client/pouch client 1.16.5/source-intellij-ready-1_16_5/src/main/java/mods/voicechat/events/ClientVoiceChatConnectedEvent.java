/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.events;

import lightning.product.x_607_J;
import mods.voicechat.voice.client.ClientVoicechatConnection;

public class ClientVoiceChatConnectedEvent
implements x_607_J {
    private final ClientVoicechatConnection client;

    public ClientVoiceChatConnectedEvent(ClientVoicechatConnection client) {
        this.client = client;
    }

    public ClientVoicechatConnection getClient() {
        return this.client;
    }
}

