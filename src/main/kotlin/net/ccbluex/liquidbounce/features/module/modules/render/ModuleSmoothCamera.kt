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
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.util.Mth
import net.minecraft.world.phys.Vec3

data class SmoothedCameraRotation(
    val yaw: Float,
    val pitch: Float
)

object ModuleSmoothCamera : ClientModule("SmoothCamera", Category.RENDER) {

    private val smooth by float("Smooth", 0.08f, 0.01f..0.30f, 0.01f)

    private var smoothYaw = 0.0f
    private var smoothPitch = 0.0f
    private var smoothPos: Vec3? = null
    private var initialized = false
    private var lastPlayer: Any? = null

    override fun enable() {
        resetState()
    }

    override fun disable() {
        resetState()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetState()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        resetState()
    }

    @JvmStatic
    fun resetState() {
        smoothYaw = 0.0f
        smoothPitch = 0.0f
        smoothPos = null
        initialized = false
        lastPlayer = mc.player
    }

    @JvmStatic
    fun updateRotation(targetYaw: Float, targetPitch: Float): SmoothedCameraRotation {
        ensureCurrentPlayer()

        if (!running) {
            return SmoothedCameraRotation(targetYaw, targetPitch)
        }

        if (!initialized) {
            smoothYaw = targetYaw
            smoothPitch = targetPitch
            initialized = true
            return SmoothedCameraRotation(smoothYaw, smoothPitch)
        }

        val deltaYaw = Mth.wrapDegrees(targetYaw - smoothYaw)
        smoothYaw += deltaYaw * smooth
        smoothPitch += (targetPitch - smoothPitch) * smooth

        return SmoothedCameraRotation(smoothYaw, smoothPitch)
    }

    @JvmStatic
    fun updatePosition(target: Vec3): Vec3 {
        ensureCurrentPlayer()

        if (!running) {
            return target
        }

        val previous = smoothPos
        if (previous == null) {
            smoothPos = target
            return target
        }

        val factor = smooth.toDouble()
        val smoothed = Vec3(
            previous.x + (target.x - previous.x) * factor,
            previous.y + (target.y - previous.y) * factor,
            previous.z + (target.z - previous.z) * factor
        )

        smoothPos = smoothed
        return smoothed
    }

    private fun ensureCurrentPlayer() {
        val currentPlayer = mc.player
        if (lastPlayer !== currentPlayer) {
            resetState()
        }
    }

}
