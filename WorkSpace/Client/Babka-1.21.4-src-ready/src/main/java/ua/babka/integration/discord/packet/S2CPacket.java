package ua.babka.integration.discord.packet;

import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.Packet;

public interface S2CPacket
extends Packet {
    @Override
    public Opcode getOpcode();
}

