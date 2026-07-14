package ua.babka.integration.discord.packet;

import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.S2CPacket;

public class RawPacket
implements S2CPacket {
    @Override
    public Opcode getOpcode() {
        return null;
    }
}

