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
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.entity.sendChatCommand
import net.ccbluex.liquidbounce.utils.entity.sendChatMessage

/**
 * MacroChat module
 *
 * Sends a customizable chat message or command when enabled. Optionally repeats it on an interval.
 */
object ModuleMacroChat : ClientModule("MacroChat", Category.MISC) {

    private val message by text("Message", "/hub")
    private val sendOnEnable by boolean("SendOnEnable", true)
    private val repeat by boolean("Repeat", false)
    private val delay by int("Delay", 10, 1..600, "s")

    override fun enable() {
        if (sendOnEnable) {
            send()
        }
    }

    @Suppress("unused")
    private val repeatable = tickHandler {
        if (!repeat) {
            return@tickHandler
        }

        waitSeconds(delay)

        // Re-check after waiting in case the toggle changed while we slept.
        if (repeat) {
            send()
        }
    }

    /**
     * Sends [message] to the server. A leading "/" is sent as a command, anything else as chat.
     */
    private fun send() {
        val text = message.trim()
        if (text.isEmpty()) {
            return
        }

        if (text.startsWith("/")) {
            network.sendChatCommand(text.substring(1))
        } else {
            network.sendChatMessage(text)
        }
    }

}
