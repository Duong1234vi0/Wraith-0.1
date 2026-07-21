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

import com.mojang.blaze3d.platform.GlStateManager
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.drawLines
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.longLines
import net.ccbluex.liquidbounce.render.relativeToCamera
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withColor
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import java.util.Random
import java.util.concurrent.CopyOnWriteArrayList

object ModuleKillEffect : ClientModule("KillEffect", Category.RENDER) {
    private val tracked = KillEffectTracking<LivingEntity>()
    private val effects = CopyOnWriteArrayList<LightningEffect>()
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
        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableBlend()
            RenderSystem.disableCull()
            RenderSystem.enableDepthTest()
            RenderSystem.depthMask(false)
            try {
                for (pass in KillEffectRules.renderPasses) {
                    if (pass.additive) {
                        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
                    } else {
                        RenderSystem.defaultBlendFunc()
                    }
                    renderPass(pass, now)
                }
            } finally {
                RenderSystem.lineWidth(1f)
                RenderSystem.defaultBlendFunc()
                RenderSystem.depthMask(true)
                RenderSystem.enableCull()
                RenderSystem.enableDepthTest()
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
            }
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

    private fun WorldRenderEnvironment.renderPass(pass: KillEffectRenderPass, nowMs: Long) {
        RenderSystem.lineWidth(pass.width)
        longLines {
            for (effect in effects) {
                val alpha = KillEffectRules.alpha(KillEffectRules.progress(nowMs, effect.startTime))
                val color = themeColor((alpha * pass.alphaMaximum).toInt())
                if (color.a <= 0) continue
                withColor(color) {
                    for (index in 0 until effect.points.lastIndex) {
                        drawLines(relativeToCamera(effect.points[index]), relativeToCamera(effect.points[index + 1]))
                    }
                }
            }
        }
    }

    private fun themeColor(alpha: Int): Color4b {
        val color = ClientTheme.accentA
        return Color4b(
            (color ushr 16) and 0xFF,
            (color ushr 8) and 0xFF,
            color and 0xFF,
            alpha.coerceIn(0, 255),
        )
    }

    private data class LightningEffect(val points: List<Vec3>, val startTime: Long)
}
