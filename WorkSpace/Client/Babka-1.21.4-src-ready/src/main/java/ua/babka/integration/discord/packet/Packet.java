package ua.babka.integration.discord.packet;

import ua.babka.integration.discord.packet.Opcode;

public interface Packet {
    public Opcode getOpcode();
}

