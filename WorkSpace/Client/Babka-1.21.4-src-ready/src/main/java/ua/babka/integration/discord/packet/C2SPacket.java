package ua.babka.integration.discord.packet;

import com.google.gson.JsonObject;
import java.nio.ByteBuffer;
import java.util.UUID;
import ua.babka.integration.discord.packet.Packet;

public interface C2SPacket
extends Packet {
    public JsonObject toJson();

    public static ByteBuffer writeToBuffer(C2SPacket packet) {
        JsonObject packetObject = packet.toJson();
        packetObject.addProperty("nonce", UUID.randomUUID().toString());
        byte[] d2 = packetObject.toString().getBytes();
        ByteBuffer packetBuf = ByteBuffer.allocate(d2.length + 8);
        packetBuf.putInt(Integer.reverseBytes(packet.getOpcode().ordinal()));
        packetBuf.putInt(Integer.reverseBytes(d2.length));
        packetBuf.put(d2);
        packetBuf.rewind();
        return packetBuf;
    }
}

