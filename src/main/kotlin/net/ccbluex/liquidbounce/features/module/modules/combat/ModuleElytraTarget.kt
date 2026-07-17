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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.render.BoxRenderer
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.math.hypot
import kotlin.math.min
import kotlin.math.sqrt

/** Predicts elytra target movement for Aura. */
object ModuleElytraTarget : ClientModule("ElytraTarget", Category.COMBAT) {

    private val prediction by boolean("Prediction", true)
    private val visual by boolean("Visual", true).visibleWhen { prediction }
    private val color by color("Color", Color4b(255, 160, 0, 255))
        .visibleWhen { visual && prediction }
    private val mode by enumChoice("Mode", Mode.AUTO)
    private val predictRange by float("Predict Range", 2.6f, 1.5f..6.0f)
        .visibleWhen { mode == Mode.DEFAULT }
    private val predictMax by float("Predict Max", 10.0f, 1.5f..10.0f)
        .visibleWhen { mode == Mode.AUTO }

    /** Source-visible state retained for dependent Aura and Mace logic. */
    var status = true
        private set
    var disableForward = false
        private set
    private var bps = 0.0
    var scale = 0.0
        private set

    private var hurtTimerNanos = System.nanoTime()

    @Suppress("unused")
    private val syncHandler = handler<RotationUpdateEvent>(priority = AFTER_KILLAURA_PRIORITY) {
        val player = mc.player ?: return@handler
        if (mc.level == null) {
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        status = prediction

        if (target != null && shouldTarget(target)) {
            bps = getBps(target)
            scale = min(sqrt(bps) / 2.0, predictMax.toDouble())
        } else {
            bps = 0.0
        }

        // #Edit By Codex 2026-07-17: V-Core clears this suppression immediately on a null
        // Aura target, while local hurt state otherwise holds prediction off for 500 ms.
        if (target == null) {
            disableForward = false
        } else {
            if (player.hurtTime > 0) {
                disableForward = true
                hurtTimerNanos = System.nanoTime()
            }

            if (hasHurtSuppressionElapsed()) {
                disableForward = false
            }
        }
    }

    /**
     * The source's range value is intentionally target-independent in both modes.
     */
    fun getPrediction(@Suppress("UNUSED_PARAMETER") target: LivingEntity): Double = when (mode) {
        Mode.DEFAULT -> predictRange.toDouble()
        Mode.AUTO -> scale
    }

    fun shouldTarget(target: LivingEntity?): Boolean {
        val player = mc.player
        return running && target != null && !disableForward && player != null && status &&
            player.isFallFlying && target.isFallFlying
    }

    fun getBps(entity: LivingEntity?): Double {
        if (entity == null) {
            return 0.0
        }

        val velocity = entity.deltaMovement
        return hypot(velocity.x, velocity.z) * 20.0
    }

    /**
     * Single authoritative V-Core target point. It clamps the local eye into the live target box
     * before applying the source velocity prediction, rather than predicting a future box.
     */
    fun getElytraTargetVec(target: LivingEntity): Vec3? {
        val player = mc.player ?: return null
        val box = target.boundingBox
        var result = closestPointInBox(player.eyePosition, box)

        if (shouldTarget(target) && getBps(target) >= MINIMUM_PREDICTION_BPS) {
            result = result.add(target.deltaMovement.scale(getPrediction(target)))
        }

        return result
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (!visual) {
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target ?: return@handler
        if (!shouldTarget(target)) {
            return@handler
        }

        val point = getElytraTargetVec(target) ?: return@handler
        val box = AABB(-BOX_RADIUS, -BOX_RADIUS, -BOX_RADIUS, BOX_RADIUS, BOX_RADIUS, BOX_RADIUS)

        renderEnvironmentForWorld(event.matrixStack) {
            withPositionRelativeToCamera(point) {
                RenderSystem.lineWidth(1.0f)
                BoxRenderer.drawWith(this) {
                    drawBox(box, color.with(a = 120), color.with(a = 255))
                }
            }
        }
    }

    private fun closestPointInBox(point: Vec3, box: AABB) = Vec3(
        point.x.coerceIn(box.minX, box.maxX),
        point.y.coerceIn(box.minY, box.maxY),
        point.z.coerceIn(box.minZ, box.maxZ),
    )

    enum class Mode(override val choiceName: String) : NamedChoice {
        AUTO("Auto"),
        DEFAULT("Default"),
    }

    private const val HURT_SUPPRESSION_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
    private const val MINIMUM_PREDICTION_BPS = 20.0
    private const val BOX_RADIUS = 0.2
    private const val AFTER_KILLAURA_PRIORITY: Short = -1

    private fun hasHurtSuppressionElapsed() =
        System.nanoTime() - hurtTimerNanos >= HURT_SUPPRESSION_MS * NANOS_PER_MILLISECOND
}
