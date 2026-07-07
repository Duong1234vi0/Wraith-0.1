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

import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.world.phys.Vec3
import kotlin.math.abs

/**
 * SmoothCamera module.
 *
 * Makes camera movement smoother by interpolating the camera position before
 * Minecraft applies it to the render camera.
 *
 * #Edit By Codex 2026-06-19: ported from `AnotherProject/WorkSpace/LiquidBounce`
 * and adapted to this fork's Category/render camera hook.
 */
object ModuleSmoothCamera : ClientModule("SmoothCamera", Category.RENDER) {

    private val enableFirstPOV by boolean("EnableFirstPOV", false)
    private val resetOnPerspectiveChange by boolean("ResetOnPerspectiveChange", true)

    private val factorH by float("HorizontalFactor", 0.9f, 0f..1f)
    private val factorV by float("VerticalFactor", 0.93f, 0f..1f)

    var smoothPos: Vec3 = Vec3.ZERO
        private set

    private val perspective
        get() = mc.options.cameraType

    private var lastPerspective = perspective

    override fun disable() {
        resetState()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetState()
    }

    private fun resetState() {
        smoothPos = Vec3.ZERO
        lastPerspective = perspective
    }

    @JvmStatic
    fun cameraUpdate(pos: Vec3) {
        if (!running) {
            lastPerspective = perspective
            return
        }

        // This provides better responsiveness when switching perspectives.
        if (resetOnPerspectiveChange && lastPerspective != perspective) {
            smoothPos = pos
            lastPerspective = perspective
            return
        }
        lastPerspective = perspective

        // Don't smooth for first person since it looks weird.
        if (!enableFirstPOV && perspective.isFirstPerson) {
            smoothPos = pos
            return
        }

        if (smoothPos.isLikelyZero()) {
            smoothPos = pos
        }

        smoothPos = Vec3(
            smoothPos.x * factorH + pos.x * (1 - factorH),
            smoothPos.y * factorV + pos.y * (1 - factorV),
            smoothPos.z * factorH + pos.z * (1 - factorH)
        )
    }

    @JvmStatic
    fun shouldApplyChanges(): Boolean = running

    private fun Vec3.isLikelyZero(): Boolean =
        abs(x) < 1.0E-7 && abs(y) < 1.0E-7 && abs(z) < 1.0E-7
}
