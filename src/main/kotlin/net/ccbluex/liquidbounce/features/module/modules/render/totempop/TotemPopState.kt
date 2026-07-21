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
package net.ccbluex.liquidbounce.features.module.modules.render.totempop

import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.world.entity.player.PlayerModelType

internal object TotemPopState {
    const val DEFAULT_DURATION = 700
    const val MIN_DURATION = 250
    const val MAX_DURATION = 2_000
    const val DURATION_STEP = 50
    const val DEFAULT_RISE = 2.4f
    const val MIN_RISE = 0f
    const val MAX_RISE = 6f
    const val RISE_STEP = 0.1f
    const val SOURCE_ALPHA = 0.7f
    const val FULL_BRIGHT_LIGHT = 0xF000F0
    const val AVATAR_SCALE = 0.9375f
    const val VANILLA_MODEL_Y = -1.501f

    fun progress(nowMs: Long, startMs: Long, durationMs: Int): Float {
        val safeDuration = durationMs.coerceAtLeast(1)
        return ((nowMs - startMs).coerceAtLeast(0L).toFloat() / safeDuration).coerceAtLeast(0f)
    }

    fun alpha(progress: Float): Float = SOURCE_ALPHA * (1f - progress.coerceIn(0f, 1f))

    fun renderedY(capturedY: Double, rise: Float, progress: Float): Double =
        capturedY + rise * progress.coerceIn(0f, 1f)

    fun expired(progress: Float): Boolean = progress >= 1f

    fun modelLayer(modelType: PlayerModelType): ModelLayerLocation =
        if (modelType == PlayerModelType.SLIM) ModelLayers.PLAYER_SLIM else ModelLayers.PLAYER

    fun slim(modelType: PlayerModelType): Boolean = modelType == PlayerModelType.SLIM
}

internal data class TotemPopRenderParameters(val alpha: Float, val rgb: Int)

internal object TotemPopRenderScope {
    private val currentValue = ThreadLocal<TotemPopRenderParameters?>()

    val current: TotemPopRenderParameters?
        get() = currentValue.get()

    inline fun <T> withGhost(parameters: TotemPopRenderParameters, block: () -> T): T {
        val previous = currentValue.get()
        currentValue.set(parameters)
        return try {
            block()
        } finally {
            if (previous == null) currentValue.remove() else currentValue.set(previous)
        }
    }

    fun clear() = currentValue.remove()
}

internal inline fun <S, T> withRestoredState(
    capture: () -> S,
    apply: () -> Unit,
    restore: (S) -> Unit,
    block: () -> T,
): T {
    val original = capture()
    return try {
        apply()
        block()
    } finally {
        restore(original)
    }
}
