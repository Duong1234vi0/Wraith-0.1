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
package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth
import net.ccbluex.liquidbounce.utils.kotlin.random

/**
 * #Edit By Claude: Port of VCore's "Grim" rotation
 * (vcore/features/modules/combat/aura/rotation/Grim.java).
 *
 * Grim snaps straight onto the target in a single tick, but adds a small ±[jitter]° random offset to both
 * yaw and pitch each tick so the rotation is never a "perfect" lock (which rotation-based anticheats like
 * Matrix flag). VCore additionally applies a GCD round — here that is handled afterward by the engine via
 * [Rotation.normalize], so it is NOT reimplemented. Pitch is clamped to ±90° by normalization.
 */
class GrimAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("Grim", parent) {

    private val jitter by float("Jitter", 1.0f, 0.0f..3.0f)

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation {
        // VCore Grim.java:46-47 — random ±jitter added to the target yaw/pitch before snapping.
        val jittered = Rotation(
            targetRotation.yaw + (-jitter..jitter).random(),
            targetRotation.pitch + (-jitter..jitter).random()
        )

        // Snap fully toward the jittered target (full 180/180 step = reach it this tick).
        return currentRotation.towardsLinear(jittered, 180f, 180f)
    }

    /**
     * Grim reaches the target in a single tick, so it is always one tick away. This keeps KillAura's
     * SNAP / ON_TICK rotation timing accurate.
     */
    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int = 1

}
