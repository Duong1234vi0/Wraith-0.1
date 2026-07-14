package ua.babka.integration.discord.packet;

import com.google.gson.JsonObject;
import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.S2CPacket;

public record ErrorPacket(int code, String message) implements S2CPacket
{
    public ErrorPacket(JsonObject jsonObject) {
        this(jsonObject.getAsJsonObject("data").get("code").getAsInt(), jsonObject.getAsJsonObject("data").get("message").getAsString());
    }

    @Override
    public Opcode getOpcode() {
        return Opcode.Frame;
    }

    public static boolean is(JsonObject jsonObject) {
        return jsonObject.has("evt") && jsonObject.get("evt").getAsString().equals("ERROR");
    }
}

