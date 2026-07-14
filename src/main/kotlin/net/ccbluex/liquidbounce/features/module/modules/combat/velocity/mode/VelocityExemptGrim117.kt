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
import net.ccbluex.liquidbounce.event.Sequence
import net.ccbluex.liquidbounce.event.sequenceHandler
import net.ccbluex.liquidbounce.utils.entity.horizontalFacing
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.sendPacket
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket

object VelocityExemptGrim117 : VelocityMode("ExemptGrim117") {

    private val alternativeBypass by boolean("AlternativeBypass", true)

    private var canCancel = false

    override fun enable() {
        canCancel = false
        super.enable()
    }

    @Suppress("unused")
    private val packetHandler = sequenceHandler<PacketEvent> { event ->
        when (val packet = event.packet) {
            is ClientboundDamageEventPacket -> {
                if (packet.entityId == player.id) {
                    canCancel = true
                }
            }

            is ClientboundSetEntityMotionPacket -> {
                if (packet.id == player.id) {
                    cancelAndBypass(event)
                }
            }

            is ClientboundExplodePacket -> cancelAndBypass(event)
        }
    }

    private suspend fun Sequence.cancelAndBypass(event: PacketEvent) {
        if (!canCancel) {
            return
        }

        event.cancelEvent()
        waitTicks(1)

        repeat(if (alternativeBypass) 4 else 1) {
            network.sendPacket(
                ServerboundMovePlayerPacket.PosRot(
                    player.x,
                    player.y,
                    player.z,
                    player.yaw,
                    player.pitch,
                    player.onGround(),
                    player.horizontalCollision
                )
            )
        }

        network.sendPacket(
            ServerboundPlayerActionPacket(
                ServerboundPlayerActionPacket.Action.STOP_DESTROY_BLOCK,
                player.blockPosition(),
                player.horizontalFacing.opposite
            )
        )
        canCancel = false
    }

}
