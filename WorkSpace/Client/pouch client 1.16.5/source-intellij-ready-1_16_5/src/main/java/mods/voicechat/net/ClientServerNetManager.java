/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package mods.voicechat.net;

import io.netty.buffer.Unpooled;
import lightning.product.O_3036_q;
import lightning.product.W_2853_p;
import lightning.product.b_2585_i;
import lightning.product.MinecraftClient;
import mods.voicechat.net.Channel;
import mods.voicechat.net.ClientServerChannel;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.Packet;

public abstract class ClientServerNetManager
extends NetManager {
    public static void sendToServer(Packet<?> packet) {
        b_2585_i buffer = new b_2585_i(Unpooled.buffer());
        packet.toBytes(buffer);
        W_2853_p connection = MinecraftClient.A_4115_X().k_2293_S();
        if (connection != null && connection.s_956_w() != null) {
            connection.n_1700_B(new O_3036_q(packet.getIdentifier(), buffer));
        }
    }

    public static <T extends Packet<T>> void setClientListener(Channel<T> channel, ClientReceiver<T> packetReceiver) {
        if (!(channel instanceof ClientServerChannel)) {
            throw new IllegalStateException("Channel is not a ClientServerChannel");
        }
        ClientServerChannel c = (ClientServerChannel)channel;
        c.setClientListener(packetReceiver);
    }

    public static interface ClientReceiver<T extends Packet<T>> {
        public void onPacket(MinecraftClient var1, W_2853_p var2, T var3);
    }
}


