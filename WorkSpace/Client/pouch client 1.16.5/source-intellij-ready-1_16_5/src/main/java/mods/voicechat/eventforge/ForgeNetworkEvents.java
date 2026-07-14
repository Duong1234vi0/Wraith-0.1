/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lightning.product.B_4088_l;
import lightning.product.O_3036_q;
import lightning.product.g_2336_b;
import lightning.product.ClientboundCustomPayloadPacket;

public class ForgeNetworkEvents {
    private static final Map<g_2336_b, ServerCustomPayloadEvent> serverPackets = new ConcurrentHashMap<g_2336_b, ServerCustomPayloadEvent>();
    private static final Map<g_2336_b, ClientCustomPayloadEvent> clientPackets = new ConcurrentHashMap<g_2336_b, ClientCustomPayloadEvent>();

    public static void registerServerPacket(g_2336_b channel, ServerCustomPayloadEvent event) {
        serverPackets.put(channel, event);
    }

    public static void registerClientPacket(g_2336_b channel, ClientCustomPayloadEvent event) {
        clientPackets.put(channel, event);
    }

    public static boolean onCustomPayloadServer(O_3036_q packet, B_4088_l player) {
        ServerCustomPayloadEvent event = serverPackets.get(packet.J_1907_R());
        if (event != null) {
            event.onCustomPayload(packet, player);
            return true;
        }
        return false;
    }

    public static boolean onCustomPayloadClient(ClientboundCustomPayloadPacket packet) {
        ClientCustomPayloadEvent event = clientPackets.get(packet.J_1907_R());
        if (event != null) {
            event.onCustomPayload(packet);
            return true;
        }
        return false;
    }

    public static interface ServerCustomPayloadEvent {
        public void onCustomPayload(O_3036_q var1, B_4088_l var2);
    }

    public static interface ClientCustomPayloadEvent {
        public void onCustomPayload(ClientboundCustomPayloadPacket var1);
    }
}


