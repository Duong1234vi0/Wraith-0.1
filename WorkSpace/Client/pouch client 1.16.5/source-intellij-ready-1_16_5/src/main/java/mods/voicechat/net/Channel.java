/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.net;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.s_4922_C;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.Packet;
import net.minecraft.server.G_564_y;

public class Channel<T extends Packet<T>> {
    @Nullable
    private NetManager.ServerReceiver<T> serverListener;

    public void setServerListener(NetManager.ServerReceiver<T> packetReceiver) {
        this.serverListener = packetReceiver;
    }

    public void onServerPacket(G_564_y server, B_4088_l player, s_4922_C handler, T packet) {
        server.execute(() -> {
            if (this.serverListener != null) {
                this.serverListener.onPacket(server, player, handler, (Packet)packet);
            }
        });
    }
}

