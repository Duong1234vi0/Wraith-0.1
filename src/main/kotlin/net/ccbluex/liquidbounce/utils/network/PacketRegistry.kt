package net.ccbluex.liquidbounce.utils.network
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.network.protocol.PacketFlow
import net.minecraft.resources.Identifier
import java.util.EnumMap

/**
 * A registry for packet types, allowing registration of packet identifiers
 * for both clientbound and serverbound packets.
 * This is used to keep track of which packets are registered for each side of the network.
 *
 * Be aware that serverbound means packets sent from the client to the server (C2S),
 * and clientbound means packets sent from the server to the client (S2C).
 */
val packetRegistry = EnumMap<PacketFlow, MutableSet<Identifier>>(PacketFlow::class.java)
