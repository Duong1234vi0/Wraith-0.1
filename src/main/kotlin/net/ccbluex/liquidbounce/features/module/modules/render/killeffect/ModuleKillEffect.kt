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
package net.ccbluex.liquidbounce.features.module.modules.render.killeffect

import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import java.util.Random

object ModuleKillEffect : ClientModule("KillEffect", Category.RENDER) {
    private val tracked = KillEffectTracking<LivingEntity>()
    private val effects = ArrayList<LightningEffect>()
    private val random = Random()

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        if (event.isCancelled) return@handler
        val living = event.entity as? LivingEntity ?: return@handler
        tracked.track(living.id, living, living.isAlive, System.currentTimeMillis())
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (mc.level == null) {
            clearRuntime()
            return@handler
        }
        val now = System.currentTimeMillis()
        tracked.process(now) { entity ->
            when {
                entity.isRemoved -> KillTrackedState.REMOVED
                !entity.isAlive -> KillTrackedState.DEAD
                else -> KillTrackedState.ALIVE
            }
        }.forEach(::spawnEffect)
        effects.removeIf { now - it.startTime > KillEffectRules.EFFECT_DURATION_MS }
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (effects.isEmpty() || mc.player == null) return@handler
        val now = System.currentTimeMillis()
        val camera = event.camera.position()
        try {
            KillEffectBatchRenderer.beginFrame()
            for ((passIndex, pass) in KillEffectRules.renderPasses.withIndex()) {
                appendPass(passIndex, pass, now, camera)
            }
            KillEffectBatchRenderer.flush(event.matrixStack.last().pose())
        } finally {
            KillEffectBatchRenderer.clearFrame()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { clearRuntime() }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> { clearRuntime() }

    override fun disable() {
        clearRuntime()
        super.disable()
    }

    private fun clearRuntime() {
        tracked.clear()
        effects.clear()
    }

    private fun spawnEffect(entity: LivingEntity) {
        val target = entity.position().add(0.0, entity.bbHeight * 0.5, 0.0)
        mc.level?.playLocalSound(
            target.x,
            target.y,
            target.z,
            SoundEvents.LIGHTNING_BOLT_THUNDER,
            SoundSource.WEATHER,
            1f,
            1f,
            false,
        )
        effects += LightningEffect(
            KillEffectRules.generateLightning(target, random::nextDouble),
            System.currentTimeMillis(),
        )
    }

    private fun appendPass(passIndex: Int, pass: KillEffectRenderPass, nowMs: Long, camera: Vec3) {
        for (effect in effects) {
            val alpha = KillEffectRules.alpha(KillEffectRules.progress(nowMs, effect.startTime))
            val color = themeColor((alpha * pass.alphaMaximum).toInt())
            if ((color ushr 24) == 0) continue
            for (index in 0 until effect.points.lastIndex) {
                val start = effect.points[index].subtract(camera)
                val end = effect.points[index + 1].subtract(camera)
                KillEffectBatchRenderer.appendLine(
                    passIndex,
                    start.x.toFloat(), start.y.toFloat(), start.z.toFloat(),
                    end.x.toFloat(), end.y.toFloat(), end.z.toFloat(),
                    color,
                    pass.width,
                )
            }
        }
    }

    private fun themeColor(alpha: Int): Int {
        val color = ClientTheme.accentA
        return (alpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF)
    }

    private data class LightningEffect(val points: List<Vec3>, val startTime: Long)
}
