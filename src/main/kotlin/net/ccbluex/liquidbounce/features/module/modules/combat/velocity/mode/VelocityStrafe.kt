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

import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.sequenceHandler
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.utils.facingEnemy
import net.ccbluex.liquidbounce.utils.combat.findEnemy
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.sqrtSpeed
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.ccbluex.liquidbounce.utils.entity.withStrafe
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket

object VelocityStrafe : VelocityMode("Strafe") {

    private val delay by int("Delay", 2, 0..10, "ticks")
    private val strength by float("Strength", 1f, 0.1f..2f)

    private object OnlyFacing : ToggleableConfigurable(VelocityStrafe, "OnlyFacing", false) {
        val range by float("Range", 3.5f, 0.1f..6f)
    }

    private val untilGround by boolean("UntilGround", false)

    private var applyStrafe = false
    private var shouldStrafe = false

    init {
        tree(OnlyFacing)
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (!OnlyFacing.running) {
            return@handler
        }

        val target = world.findEnemy(0f..OnlyFacing.range) ?: return@handler
        shouldStrafe = facingEnemy(
            target,
            OnlyFacing.range.toDouble(),
            RotationManager.currentRotation ?: player.rotation
        )
    }

    @Suppress("unused")
    private val packetHandler = sequenceHandler<PacketEvent> { event ->
        val packet = event.packet
        if (packet is ClientboundSetEntityMotionPacket && packet.id == player.id || packet is ClientboundExplodePacket) {
            if (OnlyFacing.running && !shouldStrafe) {
                return@sequenceHandler
            }

            waitTicks(delay)

            player.velocity = player.velocity.withStrafe(
                speed = player.sqrtSpeed * strength
            )

            if (untilGround) {
                applyStrafe = true
            }
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (player.onGround()) {
            applyStrafe = false
        } else if (applyStrafe) {
            event.movement = event.movement.withStrafe(speed = player.sqrtSpeed * strength)
        }
    }

}
