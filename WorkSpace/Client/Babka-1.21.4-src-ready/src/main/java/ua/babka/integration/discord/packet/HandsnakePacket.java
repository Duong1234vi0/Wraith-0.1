package ua.babka.integration.discord.packet;

import com.google.gson.JsonObject;
import ua.babka.integration.discord.packet.C2SPacket;
import ua.babka.integration.discord.packet.Opcode;

public class HandsnakePacket
implements C2SPacket {
    private final long appId;
    private final int version;

    public HandsnakePacket(long appId, int version) {
        this.appId = appId;
        this.version = version;
    }

    @Override
    public Opcode getOpcode() {
        return Opcode.Handshake;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("v", (Number)this.version);
        jsonObject.addProperty("client_id", Long.toString(this.appId));
        return jsonObject;
    }
}

