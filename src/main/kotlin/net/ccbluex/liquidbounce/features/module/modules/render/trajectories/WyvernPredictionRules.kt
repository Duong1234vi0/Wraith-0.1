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
package net.ccbluex.liquidbounce.features.module.modules.render.trajectories

import net.minecraft.world.phys.Vec3

internal enum class WyvernPredictionEntityKind {
    TRIDENT,
    PERSISTENT_PROJECTILE,
    THROWN_ITEM,
    DROPPED_ITEM,
}

internal object WyvernPredictionRules {
    const val MAX_STEPS = 300
    const val BASE_LINE_WIDTH = 2f
    const val INNER_GLOW_WIDTH = 4.5f
    const val OUTER_GLOW_WIDTH = 7f
    const val BELOW_WORLD_Y = -128.0
    const val ENTITY_EXPANSION = 0.25
    const val TICK_SECONDS = 0.05

    fun isStationary(x: Double, y: Double, z: Double, oldX: Double, oldY: Double, oldZ: Double): Boolean =
        x == oldX && y == oldY && z == oldZ

    fun droppedItemEligible(stationary: Boolean, grounded: Boolean, intersectsWater: Boolean): Boolean =
        !stationary && !grounded && !intersectsWater

    fun drag(kind: WyvernPredictionEntityKind, inWater: Boolean): Double = when (kind) {
        WyvernPredictionEntityKind.TRIDENT -> 0.99
        WyvernPredictionEntityKind.PERSISTENT_PROJECTILE -> if (inWater) 0.6 else 0.99
        WyvernPredictionEntityKind.THROWN_ITEM,
        WyvernPredictionEntityKind.DROPPED_ITEM -> if (inWater) 0.8 else 0.99
    }

    fun updateMotion(motion: Vec3, kind: WyvernPredictionEntityKind, inWater: Boolean, gravity: Double): Vec3 =
        motion.scale(drag(kind, inWater)).add(0.0, -gravity, 0.0)

    fun shouldTerminate(blockHit: Boolean, livingHit: Boolean, inEntity: Boolean, y: Double): Boolean =
        blockHit || livingHit || inEntity || y < BELOW_WORLD_Y

    fun baseAlpha(index: Int): Float = (index / 25f).coerceIn(0f, 1f)

    fun glowAlpha(index: Int): Float = (index / 50f).coerceIn(0f, 0.3f)

    fun seconds(ticks: Int): Double = ticks * TICK_SECONDS

    fun bowPull(ticksUsingItem: Int, isUsingItem: Boolean): Float {
        var progress = ticksUsingItem.coerceAtLeast(0) / 20f
        progress = ((progress * progress + progress * 2f) / 3f).coerceAtMost(1f)
        return if (progress == 0f && isUsingItem) 1f else progress
    }
}
