/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import java.util.ArrayList;
import java.util.List;
import lightning.product.B_4088_l;
import lightning.product.s_4922_C;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.Packet;
import net.minecraft.server.G_564_y;

public class ServerChannel<T extends Packet<T>> {
    private final List<NetManager.ServerReceiver<T>> listeners = new ArrayList<NetManager.ServerReceiver<T>>();

    public void registerServerListener(NetManager.ServerReceiver<T> packetReceiver) {
        this.listeners.add(packetReceiver);
    }

    public void onPacket(G_564_y server, B_4088_l player, s_4922_C handler, T packet) {
        this.listeners.forEach(receiver -> receiver.onPacket(server, player, handler, packet));
    }

    public List<NetManager.ServerReceiver<T>> getListeners() {
        return this.listeners;
    }
}

