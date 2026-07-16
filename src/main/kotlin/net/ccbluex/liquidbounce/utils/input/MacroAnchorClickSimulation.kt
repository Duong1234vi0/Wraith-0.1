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
package net.ccbluex.liquidbounce.utils.input

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.GameRenderTaskQueueEvent
import net.ccbluex.liquidbounce.event.handler
import java.util.ArrayDeque

/**
 * Non-gameplay click records used by MacroAnchor's Argon-compatible ClickSimulation setting.
 *
 * Wraith currently has no right-click CPS consumer. This therefore records the synthetic press/release timing
 * without dispatching MouseButtonEvent, changing GLFW state, feeding keybinds, or starting another use action.
 */
object MacroAnchorClickSimulation : EventListener {

    private const val CLICK_DURATION_NANOS = 35_000_000L

    private val pendingReleaseTimes = ArrayDeque<Long>()

    var rightMousePresses = 0L
        private set

    var rightMouseReleases = 0L
        private set

    val rightMousePressed: Boolean
        get() = pendingReleaseTimes.isNotEmpty()

    fun recordRightClick() {
        rightMousePresses++
        pendingReleaseTimes.addLast(System.nanoTime() + CLICK_DURATION_NANOS)
    }

    /**
     * This runs on Minecraft's client thread. It never sleeps or invokes normal input handling.
     */
    @Suppress("unused")
    private val releaseHandler = handler<GameRenderTaskQueueEvent> {
        val now = System.nanoTime()

        while (pendingReleaseTimes.peekFirst()?.let { it <= now } == true) {
            pendingReleaseTimes.removeFirst()
            rightMouseReleases++
        }
    }
}
