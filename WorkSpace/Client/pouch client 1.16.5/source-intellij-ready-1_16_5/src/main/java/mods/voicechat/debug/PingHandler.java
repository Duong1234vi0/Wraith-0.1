/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package mods.voicechat.debug;

import io.netty.buffer.Unpooled;
import java.net.SocketAddress;
import java.util.UUID;
import lightning.product.b_2585_i;
import mods.voicechat.Voicechat;
import mods.voicechat.voice.server.Server;

public class PingHandler {
    public static final UUID PING_V1 = UUID.fromString("58bc9ae9-c7a8-45e4-a11c-efbb67199425");

    public static boolean onPacket(Server server, SocketAddress socketAddress, UUID playerID, b_2585_i buf) {
        if (!((Boolean)Voicechat.SERVER_CONFIG.allowPings.get()).booleanValue()) {
            return false;
        }
        if (!PING_V1.equals(playerID)) {
            return false;
        }
        try {
            byte[] payload = buf.n_1700_B();
            b_2585_i buffer = new b_2585_i(Unpooled.wrappedBuffer((byte[])payload));
            UUID id = buffer.w_1484_f();
            long timestamp = buffer.readLong();
            Voicechat.LOGGER.debug("Received ping {} from {}", id, socketAddress);
            b_2585_i responseBuffer = new b_2585_i(Unpooled.buffer((int)24));
            responseBuffer.n_1700_B(id);
            responseBuffer.writeLong(timestamp);
            byte[] response = new byte[responseBuffer.readableBytes()];
            responseBuffer.readBytes(response);
            server.getSocket().send(response, socketAddress);
        }
        catch (Exception e) {
            Voicechat.LOGGER.debug("Failed to send ping to {}: {}", socketAddress, e.getMessage());
        }
        return true;
    }
}

