/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.minecraft.util.Mth

/** V-Core Aura Grim rotation using Wraith's single downstream GCD normalization. */
class GrimAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("Grim", parent) {

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation {
        val shortestYaw = Mth.wrapDegrees(targetRotation.yaw - currentRotation.yaw)
        return Rotation(
            yaw = currentRotation.yaw + shortestYaw + (-1.0f..1.0f).random(),
            pitch = targetRotation.pitch + (-1.0f..1.0f).random()
        )
    }

    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int = 1
}
