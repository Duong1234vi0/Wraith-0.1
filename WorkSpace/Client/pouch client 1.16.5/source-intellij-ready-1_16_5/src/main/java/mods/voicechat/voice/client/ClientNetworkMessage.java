/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lightning.product.b_2585_i;
import mods.voicechat.Voicechat;
import mods.voicechat.api.RawUdpPacket;
import mods.voicechat.voice.client.ClientVoicechatConnection;
import mods.voicechat.voice.common.NetworkMessage;

public class ClientNetworkMessage {
    @Nullable
    public static NetworkMessage readPacketClient(RawUdpPacket packet, ClientVoicechatConnection client) throws IllegalAccessException, InstantiationException, IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException {
        byte[] data = packet.getData();
        b_2585_i b = new b_2585_i(Unpooled.wrappedBuffer((byte[])data));
        if (b.readByte() != -1) {
            Voicechat.LOGGER.debug("Received invalid packet from {}", client.getAddress());
            return null;
        }
        return NetworkMessage.readFromBytes(packet.getSocketAddress(), client.getData().getSecret(), b.n_1700_B(), System.currentTimeMillis());
    }

    public static byte[] writeClient(ClientVoicechatConnection client, NetworkMessage networkMessage) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        byte[] payload = networkMessage.write(client.getData().getSecret());
        b_2585_i buffer = new b_2585_i(Unpooled.buffer((int)(17 + payload.length)));
        buffer.writeByte(-1);
        buffer.n_1700_B(client.getData().getPlayerUUID());
        buffer.n_1700_B(payload);
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        return bytes;
    }
}

