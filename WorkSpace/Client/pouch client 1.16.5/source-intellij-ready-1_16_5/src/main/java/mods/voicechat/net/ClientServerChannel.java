/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.net;

import javax.annotation.Nullable;
import lightning.product.W_2853_p;
import lightning.product.MinecraftClient;
import mods.voicechat.net.Channel;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.Packet;

public class ClientServerChannel<T extends Packet<T>>
extends Channel<T> {
    @Nullable
    private ClientServerNetManager.ClientReceiver<T> clientListener;

    public void setClientListener(ClientServerNetManager.ClientReceiver<T> packetReceiver) {
        this.clientListener = packetReceiver;
    }

    public void onClientPacket(MinecraftClient client, W_2853_p handler, T packet) {
        client.execute(() -> {
            if (this.clientListener != null) {
                this.clientListener.onPacket(client, handler, (Packet)packet);
            }
        });
    }
}


