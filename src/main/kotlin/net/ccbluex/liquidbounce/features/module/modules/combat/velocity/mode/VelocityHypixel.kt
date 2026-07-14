/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode

import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.modules.movement.fly.ModuleFly
import net.ccbluex.liquidbounce.interfaces.ClientboundSetEntityMotionPacketAccessor
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
import net.minecraft.world.phys.Vec3

object VelocityHypixel : VelocityMode("Hypixel") {

    private var absorbedVelocity = false

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (ModuleFly.enabled) {
            return@handler
        }

        val packet = event.packet
        if (packet is ClientboundSetEntityMotionPacket && packet.id == player.id) {
            if (!player.onGround() && !absorbedVelocity) {
                event.cancelEvent()
                absorbedVelocity = true
                return@handler
            }

            val rawX = (player.velocity.x * 8000.0).toInt()
            val rawZ = (player.velocity.z * 8000.0).toInt()
            (packet as ClientboundSetEntityMotionPacketAccessor).`liquid_bounce$setMovement`(
                Vec3(rawX / 8000.0, packet.movement.y, rawZ / 8000.0)
            )
        }
    }

    @Suppress("unused")
    private val repeatable = tickHandler {
        if (player.onGround()) {
            absorbedVelocity = false
        }
    }

}
