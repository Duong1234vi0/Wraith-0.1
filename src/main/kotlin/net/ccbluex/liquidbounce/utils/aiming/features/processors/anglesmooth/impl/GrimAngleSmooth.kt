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
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleWallsBypass
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraRotationsConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.minecraft.util.Mth

/** V-Core Grim rotation with its absolute GCD and normalized-result contract. */
class GrimAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("Grim", parent) {

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation {
        val entity = rotationTarget.entity
        val canSeeTarget = entity == null || player.canSee(entity)
        // KillAura resolves V-Core's base/predicted -> PeekHigh -> PeakAssist point before this
        // processor. Recomputing PeekHigh here would discard PeakAssist's height.
        var shortestYaw = Mth.wrapDegrees(targetRotation.yaw - currentRotation.yaw)
        if (entity != null && !ModuleKillAura.isTrackAttackReady(entity) &&
            KillAuraRotationsConfigurable.aimThroughWalls &&
            ModuleWallsBypass.running && ModuleWallsBypass.isYawOffset() && !canSeeTarget) {
            shortestYaw += 20.0f
        }
        val yaw = currentRotation.yaw + shortestYaw + (-1.0f..1.0f).random()
        val pitch = (targetRotation.pitch + (-1.0f..1.0f).random()).coerceIn(-90.0f, 90.0f)

        if ((yaw == -90.0f && pitch == 90.0f) || yaw == -180.0f) {
            return Rotation(player.yaw, player.pitch, isNormalized = true)
        }

        val sensitivity = mc.options.sensitivity().get().toDouble()
        val value = sensitivity * 0.6 + 0.2
        val gcd = Math.pow(value, 1.5) * 0.8 * 0.15
        return Rotation(
            yaw = (yaw - yaw % gcd).toFloat(),
            pitch = (pitch - pitch % gcd).toFloat().coerceIn(-90.0f, 90.0f),
            isNormalized = true,
        )
    }

    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int = 1
}
