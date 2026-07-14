/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth

/**
 * Serialized selection marker for KillAura's complete V-Core Track strategy.
 * KillAura bypasses this processor and delegates to VCoreAuraRotationController.
 */
class TrackAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("Track", parent) {
    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation = targetRotation

    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int = 1
}
