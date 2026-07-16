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
import org.lwjgl.glfw.GLFW

/**
 * Non-gameplay mouse records used by the strict Argon TriggerBot ClickSimulation and UseShield settings.
 *
 * Argon's MouseSimulation only changed its own button map and fired an Argon-local button event. Wraith has no
 * equivalent proven CPS consumer, so this intentionally does not dispatch MouseButtonEvent, change GLFW state,
 * feed module binds, sleep, start a thread, use an item, or invoke Minecraft.startAttack().
 */
object TriggerBotClickSimulation : EventListener {

    private data class PendingRelease(val button: Int, val releaseAtNanos: Long)

    private val pendingReleases = mutableListOf<PendingRelease>()

    var leftMousePresses = 0L
        private set
    var leftMouseReleases = 0L
        private set
    var rightMousePresses = 0L
        private set
    var rightMouseReleases = 0L
        private set

    var leftMousePressed = false
        private set
    var rightMousePressed = false
        private set

    fun recordLeftClick(durationMs: Long = DEFAULT_CLICK_DURATION_MS) {
        recordClick(GLFW.GLFW_MOUSE_BUTTON_LEFT, durationMs)
    }

    fun recordRightClick(durationMs: Long) {
        recordClick(GLFW.GLFW_MOUSE_BUTTON_RIGHT, durationMs)
    }

    /** Records Argon's immediate synthetic right-button release without affecting real use state. */
    fun recordRightRelease() {
        recordRelease(GLFW.GLFW_MOUSE_BUTTON_RIGHT)
    }

    private fun recordClick(button: Int, durationMs: Long) {
        recordPress(button)

        // This recorder deliberately has bounded state. A cooldown tick may request a synthetic shield click
        // every game tick, but Wraith has no consumer which needs an unbounded history of those requests.
        pendingReleases.removeAll { it.button == button }
        pendingReleases += PendingRelease(
            button = button,
            releaseAtNanos = System.nanoTime() + durationMs.coerceAtLeast(0L) * NANOS_PER_MILLISECOND
        )
    }

    private fun recordPress(button: Int) {
        when (button) {
            GLFW.GLFW_MOUSE_BUTTON_LEFT -> {
                leftMousePresses++
                leftMousePressed = true
            }

            GLFW.GLFW_MOUSE_BUTTON_RIGHT -> {
                rightMousePresses++
                rightMousePressed = true
            }
        }
    }

    private fun recordRelease(button: Int) {
        when (button) {
            GLFW.GLFW_MOUSE_BUTTON_LEFT -> {
                leftMouseReleases++
                leftMousePressed = false
            }

            GLFW.GLFW_MOUSE_BUTTON_RIGHT -> {
                rightMouseReleases++
                rightMousePressed = false
            }
        }
    }

    /** Runs on the Minecraft client thread and only drains recorded release timestamps. */
    @Suppress("unused")
    private val releaseHandler = handler<GameRenderTaskQueueEvent> {
        val now = System.nanoTime()
        val iterator = pendingReleases.iterator()

        while (iterator.hasNext()) {
            val pendingRelease = iterator.next()
            if (pendingRelease.releaseAtNanos <= now) {
                iterator.remove()
                recordRelease(pendingRelease.button)
            }
        }
    }

    private const val DEFAULT_CLICK_DURATION_MS = 35L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
}
