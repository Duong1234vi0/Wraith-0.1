/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.MinecraftClient;
import mods.voicechat.Voicechat;
import mods.voicechat.eventforge.ForgeNetworkEvents;
import mods.voicechat.net.Channel;
import mods.voicechat.net.ClientServerChannel;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.Packet;
import mods.voicechat.net.RequestSecretPacket;

public class ForgeNetManager
extends NetManager {
    @Override
    public <T extends Packet<T>> Channel<T> registerReceiver(Class<T> packetType, boolean toClient, boolean toServer) {
        ClientServerChannel c = new ClientServerChannel();
        try {
            Packet dummyPacket = (Packet)packetType.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (toServer) {
                ForgeNetworkEvents.registerServerPacket(dummyPacket.getIdentifier(), (packet, player) -> {
                    try {
                        if (!Voicechat.SERVER.isCompatible(player) && !packetType.equals(RequestSecretPacket.class)) {
                            return;
                        }
                        Packet vcPacket = (Packet)packetType.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        vcPacket.fromBytes(packet.R_4764_Y());
                        c.onServerPacket(player.J_1907_R, player, player.n_1700_B, vcPacket);
                    }
                    catch (Exception e) {
                        Voicechat.LOGGER.error("Failed to process packet", e);
                    }
                });
            }
            if (toClient) {
                ForgeNetworkEvents.registerClientPacket(dummyPacket.getIdentifier(), payload -> {
                    try {
                        Packet packet = (Packet)packetType.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        packet.fromBytes(payload.R_4764_Y());
                        this.onClientPacket(c, packet);
                    }
                    catch (Exception e) {
                        Voicechat.LOGGER.error("Failed to process packet", e);
                    }
                });
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return c;
    }

    private <T extends Packet<T>> void onClientPacket(ClientServerChannel<T> channel, T packet) {
        channel.onClientPacket(MinecraftClient.A_4115_X(), MinecraftClient.A_4115_X().k_2293_S(), packet);
    }
}


