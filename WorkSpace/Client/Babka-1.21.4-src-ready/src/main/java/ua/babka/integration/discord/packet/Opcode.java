package ua.babka.integration.discord.packet;

import com.google.gson.JsonObject;
import java.util.function.Function;
import ua.babka.integration.discord.packet.CloseConnectionPacket;
import ua.babka.integration.discord.packet.DispatchPacket;
import ua.babka.integration.discord.packet.ErrorPacket;
import ua.babka.integration.discord.packet.RawPacket;
import ua.babka.integration.discord.packet.S2CPacket;

public enum Opcode {
    Handshake(jsonObject -> {
        throw new UnsupportedOperationException("Cannot create C2S packet from json.");
    }),
    Frame(jsonObject -> {
        if (DispatchPacket.is(jsonObject)) {
            return new DispatchPacket((JsonObject)jsonObject);
        }
        if (ErrorPacket.is(jsonObject)) {
            return new ErrorPacket((JsonObject)jsonObject);
        }
        throw new UnsupportedOperationException("Cannot create C2S packet from json.");
    }),
    Close(jsonObject -> new CloseConnectionPacket(jsonObject.get("code").getAsInt(), jsonObject.get("message").getAsString())),
    Ping(jsonObject -> new RawPacket()),
    Pong(jsonObject -> new RawPacket());

    public final Function<JsonObject, S2CPacket> toPacketFunction;
    private static final Opcode[] VALUES;

    public static Opcode valueOf(int i2) {
        return VALUES[i2];
    }

    private Opcode(Function<JsonObject, S2CPacket> toPacketFunction) {
        this.toPacketFunction = toPacketFunction;
    }

    static {
        VALUES = Opcode.values();
    }
}

