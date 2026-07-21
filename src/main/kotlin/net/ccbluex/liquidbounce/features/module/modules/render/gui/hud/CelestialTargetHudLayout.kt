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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import kotlin.math.ceil

data class CelestialTargetHudRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
)

data class CelestialTargetHudGeometry(
    val panel: CelestialTargetHudRect,
    val head: CelestialTargetHudRect,
    val textX: Float,
    val nameY: Float,
    val nameClip: CelestialTargetHudRect,
    val healthTextY: Float,
    val healthTrack: CelestialTargetHudRect,
    val healthFillHeight: Float,
)

object CelestialTargetHudLayout {
    const val RENDER_WIDTH = 170f / 1.5f
    const val RENDER_HEIGHT = 57f / 1.5f
    const val HEAD_SIZE = 32f

    // The owning Celestial class was not supplied, so the requested fallback value is explicit here.
    const val SPACING = 4f
    const val PANEL_RADIUS = 5f
    const val HEAD_RADIUS = 3f
    const val BAR_RADIUS = 2f
    const val OUTLINE_WIDTH = 0.8f
    const val SHADOW_SPREAD = 6f
    const val NAME_SIZE = 17f / 1.5f
    const val HEALTH_TEXT_SIZE = 13f / 1.5f

    val EDITOR_WIDTH: Int = ceil(RENDER_WIDTH).toInt()
    val EDITOR_HEIGHT: Int = ceil(RENDER_HEIGHT).toInt()

    fun geometry(x: Float, y: Float, nameHeight: Float): CelestialTargetHudGeometry {
        val textX = x + HEAD_SIZE + SPACING + 2f
        val trackWidth = RENDER_WIDTH - (HEAD_SIZE + SPACING * 2f)
        return CelestialTargetHudGeometry(
            panel = CelestialTargetHudRect(x, y, RENDER_WIDTH, RENDER_HEIGHT),
            head = CelestialTargetHudRect(x + 4f, y + (RENDER_HEIGHT - HEAD_SIZE) * 0.5f, HEAD_SIZE, HEAD_SIZE),
            textX = textX,
            nameY = y - 1f + SPACING + 3f,
            nameClip = CelestialTargetHudRect(
                textX,
                y - 1f + 6f,
                RENDER_WIDTH - 40f,
                nameHeight.takeIf { it.isFinite() && it > 0f } ?: 1f,
            ),
            healthTextY = y + RENDER_HEIGHT - SPACING - 14f,
            healthTrack = CelestialTargetHudRect(
                textX - 1f,
                y + RENDER_HEIGHT - SPACING * 2f - 2.5f,
                trackWidth,
                8f,
            ),
            healthFillHeight = 7.5f,
        )
    }

    fun sanitizeHealth(health: Float): Float =
        health.takeIf { it.isFinite() }?.coerceAtLeast(0f) ?: 0f

    fun healthRatio(health: Float, maxHealth: Float): Float {
        val safeMax = maxHealth.takeIf { it.isFinite() && it > 0f } ?: return 0f
        return (sanitizeHealth(health) / safeMax).takeIf(Float::isFinite)?.coerceIn(0f, 1f) ?: 0f
    }

    fun fillWidth(trackWidth: Float, ratio: Float): Float {
        val safeTrack = trackWidth.takeIf { it.isFinite() && it > 0f } ?: return 0f
        val safeRatio = ratio.takeIf { it.isFinite() }?.coerceIn(0f, 1f) ?: 0f
        return (safeTrack * safeRatio).coerceIn(0f, safeTrack)
    }
}

data class CelestialTargetHealthSnapshot(
    val health: Float,
    val ratio: Float,
)

class CelestialTargetHealthAnimation {
    private var initialized = false
    private var displayedHealth = 0f
    private var displayedRatio = 0f

    fun resetForTarget(health: Float, maxHealth: Float) {
        displayedHealth = CelestialTargetHudLayout.sanitizeHealth(health)
        displayedRatio = CelestialTargetHudLayout.healthRatio(health, maxHealth)
        initialized = true
    }

    fun update(health: Float, maxHealth: Float): CelestialTargetHealthSnapshot {
        val targetHealth = CelestialTargetHudLayout.sanitizeHealth(health)
        val targetRatio = CelestialTargetHudLayout.healthRatio(health, maxHealth)
        if (!initialized) {
            resetForTarget(targetHealth, maxHealth)
        } else {
            displayedHealth = approach(displayedHealth, targetHealth, 0.18f)
            displayedRatio = approach(displayedRatio, targetRatio, 0.12f).coerceIn(0f, 1f)
        }
        return snapshot()
    }

    fun snapshot(): CelestialTargetHealthSnapshot = CelestialTargetHealthSnapshot(
        health = displayedHealth.takeIf { it.isFinite() }?.coerceAtLeast(0f) ?: 0f,
        ratio = displayedRatio.takeIf { it.isFinite() }?.coerceIn(0f, 1f) ?: 0f,
    )

    fun reset() {
        initialized = false
        displayedHealth = 0f
        displayedRatio = 0f
    }

    private fun approach(current: Float, target: Float, factor: Float): Float {
        val safeCurrent = current.takeIf { it.isFinite() } ?: target
        val next = safeCurrent + (target - safeCurrent) * factor
        return next.takeIf { it.isFinite() } ?: target
    }
}
