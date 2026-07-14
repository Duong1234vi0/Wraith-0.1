package ua.babka.integration.discord.packet;

import com.google.gson.JsonObject;
import ua.babka.integration.discord.IPCUser;
import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.S2CPacket;

public record DispatchPacket(IPCUser discordUser) implements S2CPacket
{
    public DispatchPacket(JsonObject jsonObject) {
        this(IPCUser.fromJson(jsonObject.getAsJsonObject("data").getAsJsonObject("user")));
    }

    @Override
    public Opcode getOpcode() {
        return Opcode.Frame;
    }

    public static boolean is(JsonObject jsonObject) {
        return jsonObject.has("cmd") && jsonObject.get("cmd").getAsString().equals("DISPATCH");
    }
}

