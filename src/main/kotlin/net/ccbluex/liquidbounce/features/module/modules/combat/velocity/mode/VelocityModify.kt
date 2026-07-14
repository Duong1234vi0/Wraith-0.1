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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.interfaces.ClientboundExplodePacketAccessor
import net.ccbluex.liquidbounce.interfaces.ClientboundSetEntityMotionPacketAccessor
import net.ccbluex.liquidbounce.utils.entity.moving
import net.ccbluex.liquidbounce.utils.entity.velocity
import net.minecraft.network.protocol.common.ServerboundPongPacket
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
import net.minecraft.world.phys.Vec3
import java.util.Optional
import kotlin.jvm.optionals.getOrNull
import kotlin.random.Random

@Suppress("MagicNumber")
object VelocityModify : VelocityMode("Modify") {

    private val horizontal by float("Horizontal", 0f, -1f..1f)
    private val vertical by float("Vertical", 0f, -1f..1f)
    private val motionHorizontal by float("MotionHorizontal", 0f, 0f..1f)
    private val motionVertical by float("MotionVertical", 0f, 0f..1f)
    private val chance by int("Chance", 100, 0..100, "%")
    private val filter by enumChoice("Filter", Filter.ALWAYS)
    private val onlyMove by boolean("OnlyMove", false)
    private val transactionBufferValue by int("TransactionBuffer", 0, 0..3)

    private var transactionBuffer = 0

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        val packet = event.packet

        when (packet) {
            is ClientboundSetEntityMotionPacket -> {
                if (event.origin != TransferOrigin.INCOMING || packet.id != player.id) {
                    return@handler
                }

                if (!passesVelocityConditions()) {
                    return@handler
                }

                handleMotionPacket(event, packet)
            }

            is ClientboundExplodePacket -> {
                if (event.origin != TransferOrigin.INCOMING) {
                    return@handler
                }

                if (!passesVelocityConditions()) {
                    return@handler
                }

                handleExplosionPacket(packet)
            }

            is ServerboundPongPacket -> {
                if (event.origin == TransferOrigin.OUTGOING && transactionBuffer > 0) {
                    event.cancelEvent()
                    transactionBuffer--
                }
            }
        }
    }

    private fun handleMotionPacket(event: PacketEvent, packet: ClientboundSetEntityMotionPacket) {
        if (horizontal == 0f && vertical == 0f) {
            event.cancelEvent()
            return
        }

        val velocity = packet.movement
        val rawX = if (horizontal != 0f) {
            (raw(velocity.x) * horizontal).toInt()
        } else {
            (player.velocity.x * motionHorizontal * 8000.0).toInt()
        }
        val rawZ = if (horizontal != 0f) {
            (raw(velocity.z) * horizontal).toInt()
        } else {
            (player.velocity.z * motionHorizontal * 8000.0).toInt()
        }
        val rawY = if (vertical != 0f) {
            (raw(velocity.y) * vertical).toInt()
        } else {
            (player.velocity.y * motionVertical * 8000.0).toInt()
        }

        packet.setMovement(rawX, rawY, rawZ)
        transactionBuffer += transactionBufferValue
    }

    private fun passesVelocityConditions() = passesChance() && passesFilter() && (!onlyMove || player.moving)

    private fun handleExplosionPacket(packet: ClientboundExplodePacket) {
        val knockback = packet.playerKnockback.getOrNull() ?: return
        (packet as ClientboundExplodePacketAccessor).`liquid_bounce$setPlayerKnockback`(
            Optional.of(Vec3(knockback.x * horizontal, knockback.y * vertical, knockback.z * horizontal))
        )
        transactionBuffer += transactionBufferValue
    }

    private fun passesChance() = chance == 100 || Random.nextInt(100) <= chance

    private fun passesFilter() = when (filter) {
        Filter.ALWAYS -> true
        Filter.ON_GROUND -> player.onGround()
        Filter.IN_AIR -> !player.onGround()
    }

    private fun raw(value: Double): Int = (value * 8000.0).toInt()

    private fun ClientboundSetEntityMotionPacket.setMovement(rawX: Int, rawY: Int, rawZ: Int) {
        (this as ClientboundSetEntityMotionPacketAccessor).`liquid_bounce$setMovement`(
            Vec3(rawX / 8000.0, rawY / 8000.0, rawZ / 8000.0)
        )
    }

    private enum class Filter(override val choiceName: String) : NamedChoice {
        ALWAYS("Always"),
        ON_GROUND("OnGround"),
        IN_AIR("InAir")
    }

}
