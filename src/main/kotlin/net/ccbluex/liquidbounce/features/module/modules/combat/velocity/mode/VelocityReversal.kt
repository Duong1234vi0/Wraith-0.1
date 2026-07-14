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
import net.ccbluex.liquidbounce.event.events.PlayerTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.utils.entity.moving
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
import net.minecraft.world.phys.Vec3

object VelocityReversal : VelocityMode("Reversal") {

    private val delay by int("Delay", 2, 1..5, "ticks")
    private val xModifier by float("XModifier", 0.5f, 0f..1f)
    private val zModifier by float("ZModifier", 0.5f, 0f..1f)
    private val onlyMoving by boolean("OnlyMoving", false)

    private var velocityTicks = 0
    private var handlingVelocity = false

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        val packet = event.packet
        if (packet is ClientboundSetEntityMotionPacket && packet.id == player.id || packet is ClientboundExplodePacket) {
            if (onlyMoving && !player.moving) {
                return@handler
            }

            reset()
            handlingVelocity = true
        }
    }

    @Suppress("unused")
    private val tickHandler = handler<PlayerTickEvent> {
        if (!handlingVelocity) {
            return@handler
        }

        val velocity = player.velocity
        if (velocity.lengthSqr() == 0.0) {
            reset()
        } else if (velocityTicks++ >= delay) {
            player.velocity = Vec3(
                velocity.x * -xModifier,
                velocity.y,
                velocity.z * -zModifier
            )
            reset()
        }
    }

    private fun reset() {
        velocityTicks = 0
        handlingVelocity = false
    }

}
