/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime

object ModuleHandMyItems : ClientModule("HandMyItems", Category.RENDER) {

    private var lastPlayer: Any? = null

    override fun init() {
        HandMyItemsRuntime.initialize()
    }

    override fun enable() {
        lastPlayer = mc.player
        HandMyItemsRuntime.enable()
    }

    override fun disable() {
        lastPlayer = null
        HandMyItemsRuntime.disable()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { event ->
        lastPlayer = mc.player
        HandMyItemsRuntime.onWorldChanged(event.world != null)
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        lastPlayer = null
        HandMyItemsRuntime.disable()
    }

    @Suppress("unused")
    private val playerReplacementHandler = handler<GameTickEvent> {
        val player = mc.player
        if (lastPlayer !== player) {
            lastPlayer = player
            HandMyItemsRuntime.onLocalPlayerReplaced()
        }
    }
}
