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
package net.ccbluex.liquidbounce.features.module.modules.combat.velocity

import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.sequenceHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityAAC442
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityBlocksMC
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityDexland
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityExemptGrim117
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityHypixel
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityHylex
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityIntave
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityJumpReset
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityModify
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityReversal
import net.ccbluex.liquidbounce.features.module.modules.combat.velocity.mode.VelocityStrafe
import net.ccbluex.liquidbounce.utils.client.handlePacket
import net.ccbluex.liquidbounce.utils.client.player
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket

object ModuleVelocity : ClientModule("Velocity", Category.COMBAT, aliases = arrayOf("AntiKnockBack")) {

    init {
        enableLock()
    }

    val modes = choices(
        "Mode",
        VelocityModify,
        arrayOf(
            VelocityModify,
            VelocityReversal,
            VelocityStrafe,
            VelocityJumpReset,
            VelocityHypixel,
            VelocityDexland,
            VelocityHylex,
            VelocityBlocksMC,
            VelocityAAC442,
            VelocityExemptGrim117,
            VelocityIntave
        )
    ).apply(::tagBy)

    private val delay by intRange("Delay", 0..0, 0..40, "ticks")
    private val pauseOnFlag by int("PauseOnFlag", 0, 0..20, "ticks")

    internal var pause = 0

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (pause > 0) {
            pause--
        }
    }

    @Suppress("unused")
    private val packetHandler = sequenceHandler<PacketEvent>(priority = 1) { event ->
        val packet = event.packet

        if (!event.original || pause > 0) {
            return@sequenceHandler
        }

        if (packet is ClientboundSetEntityMotionPacket && packet.id == player.id || packet is ClientboundExplodePacket) {
            if (delay.last > 0) {
                event.cancelEvent()

                val currentDelay = delay.random()
                if (currentDelay > 0) {
                    val until = System.currentTimeMillis() + currentDelay * 50L
                    waitUntil { System.currentTimeMillis() >= until }
                }

                val velocityEvent = PacketEvent(TransferOrigin.INCOMING, packet, original = false)
                EventManager.callEvent(velocityEvent)

                if (!velocityEvent.isCancelled) {
                    handlePacket(packet)
                }
            }
        } else if (packet is ClientboundPlayerPositionPacket) {
            pause = pauseOnFlag
        }
    }

}
