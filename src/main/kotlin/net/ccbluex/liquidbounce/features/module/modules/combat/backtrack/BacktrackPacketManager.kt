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
package net.ccbluex.liquidbounce.features.module.modules.combat.backtrack

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.TickPacketProcessEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.utils.client.handlePacket
import net.ccbluex.liquidbounce.utils.client.inGame

object BacktrackPacketManager : EventListener {

    @Suppress("unused")
    private val packetProcessHandler = handler<TickPacketProcessEvent> {
        if (!inGame) {
            ModuleBacktrack.clear(clearOnly = true)
            ModuleBacktrack.packetProcessQueue.clear()
            return@handler
        }

        if (ModuleBacktrack.shouldCancelPackets()) {
            ModuleBacktrack.processPackets()
        } else {
            ModuleBacktrack.clear()
        }

        ModuleBacktrack.packetProcessQueue.removeIf { packet ->
            handlePacket(packet)
            true
        }

        if (ModuleBacktrack.arePacketQueuesEmpty()) {
            ModuleBacktrack.currentDelay = ModuleBacktrack.delay.random()
        }
    }

}
