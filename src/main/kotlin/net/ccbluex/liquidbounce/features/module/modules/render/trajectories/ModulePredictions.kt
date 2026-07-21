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

import com.mojang.blaze3d.platform.GlStateManager
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.BoxRenderer
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.drawLines
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.engine.type.Vec3 as RenderVec3
import net.ccbluex.liquidbounce.render.longLines
import net.ccbluex.liquidbounce.render.relativeToCamera
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withColor
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.deltaMovement
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.getEntities
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.stack
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.entity.projectile.arrow.ThrownTrident
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import java.util.Locale
import kotlin.math.max

object ModulePredictions : ClientModule(
    "Prediction",
    Category.RENDER,
    aliases = arrayOf("Predictions", "Trajectories"),
) {
    val markers by boolean("Markers", true)
    val landingBox by boolean("LandingBox", true)
    val thickness by float("Thickness", 2f, 0.5f..5f)

    private var cacheWorldTime = Long.MIN_VALUE
    private var cacheValid = false
    private var cachedResults: List<PredictionResult> = emptyList()

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val level = mc.level
        val player = mc.player
        if (level == null || player == null) {
            clearCache()
            return@handler
        }

        val predictions = getPredictions(level)
        val bow = simulateClientBow(level)
        if (predictions.isEmpty() && bow == null) return@handler

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableBlend()
            RenderSystem.disableDepthTest()
            RenderSystem.disableCull()
            try {
                renderPredictionPass(predictions, WyvernPredictionRules.BASE_LINE_WIDTH, false) { index ->
                    WyvernPredictionRules.baseAlpha(index)
                }
                RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
                renderPredictionPass(predictions, WyvernPredictionRules.INNER_GLOW_WIDTH, true) { index ->
                    WyvernPredictionRules.glowAlpha(index)
                }
                renderPredictionPass(predictions, WyvernPredictionRules.OUTER_GLOW_WIDTH, true) { index ->
                    WyvernPredictionRules.glowAlpha(index)
                }
                RenderSystem.defaultBlendFunc()
                bow?.let { renderClientBow(it) }
            } finally {
                RenderSystem.lineWidth(1f)
                RenderSystem.defaultBlendFunc()
                RenderSystem.enableCull()
                RenderSystem.enableDepthTest()
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
            }
        }
    }

    @Suppress("unused")
    private val overlayHandler = handler<OverlayRenderEvent> { event ->
        val level = mc.level
        if (level == null || mc.player == null) {
            clearCache()
            return@handler
        }
        if (mc.options.hideGui) return@handler

        getPredictions(level).mapNotNull(PredictionResult::impact).forEach { drawTag(event.context, it) }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { clearCache() }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> { clearCache() }

    override fun disable() {
        clearCache()
        super.disable()
    }

    private fun clearCache() {
        cachedResults = emptyList()
        cacheWorldTime = Long.MIN_VALUE
        cacheValid = false
    }

    private fun getPredictions(level: Level): List<PredictionResult> {
        if (!cacheValid || level.gameTime != cacheWorldTime) {
            cachedResults = level.getEntities().mapNotNull { entity ->
                classify(entity)?.takeIf { isEligible(level, entity, it) }?.let { simulate(level, entity, it) }
            }
            cacheWorldTime = level.gameTime
            cacheValid = true
        }
        return cachedResults
    }

    private fun classify(entity: Entity): WyvernPredictionEntityKind? = when (entity) {
        is ThrownTrident -> WyvernPredictionEntityKind.TRIDENT
        is AbstractArrow -> WyvernPredictionEntityKind.PERSISTENT_PROJECTILE
        is ThrowableItemProjectile -> WyvernPredictionEntityKind.THROWN_ITEM
        is ItemEntity -> WyvernPredictionEntityKind.DROPPED_ITEM
        else -> null
    }

    private fun isEligible(level: Level, entity: Entity, kind: WyvernPredictionEntityKind): Boolean {
        val stationary = WyvernPredictionRules.isStationary(
            entity.x, entity.y, entity.z, entity.xOld, entity.yOld, entity.zOld,
        )
        if (kind != WyvernPredictionEntityKind.DROPPED_ITEM) return !stationary
        val inWater = level.getBlockStates(entity.boundingBox.inflate(2.0))
            .anyMatch { it.fluidState.`is`(FluidTags.WATER) }
        return WyvernPredictionRules.droppedItemEligible(stationary, entity.onGround(), inWater)
    }

    private fun simulate(level: Level, entity: Entity, kind: WyvernPredictionEntityKind): PredictionResult {
        var position = entity.position()
        var motion = entity.deltaMovement
        val path = ArrayList<Vec3>(WyvernPredictionRules.MAX_STEPS + 1)
        path += position
        var impact: PredictionImpact? = null

        for (ticks in 0 until WyvernPredictionRules.MAX_STEPS) {
            val previous = position
            position = position.add(motion)
            val inWater = level.getFluidState(BlockPos.containing(previous)).`is`(FluidTags.WATER)
            motion = WyvernPredictionRules.updateMotion(motion, kind, inWater, entity.gravity)
            val blockResult = level.clip(
                ClipContext(previous, position, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity),
            )
            val blockHit = blockResult.type == HitResult.Type.BLOCK
            if (blockHit) position = blockResult.location

            val livingHit = intersectsLivingEntity(level, entity, previous, position)
            val inEntity = blockResult.type == HitResult.Type.ENTITY
            path += position
            if (WyvernPredictionRules.shouldTerminate(blockHit, livingHit, inEntity, position.y)) {
                impact = PredictionImpact(position, ticks, impactItem(entity))
                break
            }
        }
        return PredictionResult(path, impact)
    }

    private fun intersectsLivingEntity(level: Level, projectile: Entity, start: Vec3, end: Vec3): Boolean {
        val localPlayer = mc.player
        val search = AABB(start, end).inflate(WyvernPredictionRules.ENTITY_EXPANSION)
        return level.getEntities(projectile, search) { candidate ->
            candidate is LivingEntity && candidate !== localPlayer && candidate.isAlive
        }.any { candidate ->
            candidate.boundingBox.inflate(WyvernPredictionRules.ENTITY_EXPANSION).clip(start, end).isPresent
        }
    }

    private fun impactItem(entity: Entity): ItemStack = when (entity) {
        is ItemEntity -> entity.stack.copy()
        is ThrowableItemProjectile -> entity.item.copy()
        is AbstractArrow -> entity.pickupItemStackOrigin.copy()
        else -> ItemStack.EMPTY
    }

    private fun WorldRenderEnvironment.renderPredictionPass(
        predictions: List<PredictionResult>,
        width: Float,
        glow: Boolean,
        alpha: (Int) -> Float,
    ) {
        if (predictions.isEmpty()) return
        RenderSystem.lineWidth(width)
        longLines {
            for (prediction in predictions) {
                for (index in 0 until prediction.path.lastIndex) {
                    val color = themeColor(index, alpha(index), glow)
                    withColor(color) {
                        drawLines(relativeToCamera(prediction.path[index]), relativeToCamera(prediction.path[index + 1]))
                    }
                }
            }
        }
    }

    private fun themeColor(index: Int, alpha: Float, glow: Boolean): Color4b {
        val mixed = ExosGuiRenderer.mix(ClientTheme.accentA, ClientTheme.accentB, (index % 50) / 49f)
        val baseAlpha = if (glow) alpha else alpha.coerceAtMost(1f)
        return Color4b((mixed ushr 16) and 0xFF, (mixed ushr 8) and 0xFF, mixed and 0xFF, (baseAlpha * 255).toInt())
    }

    private fun simulateClientBow(level: Level): ClientBowResult? {
        val player = mc.player ?: return null
        if (player.mainHandItem.item !is BowItem && player.offhandItem.item !is BowItem) return null
        val pull = WyvernPredictionRules.bowPull(player.ticksUsingItem, player.isUsingItem)
        if (pull == 0f && !player.isUsingItem) return null

        val rotation = RotationManager.currentRotation ?: player.rotation
        var motion = rotation.directionVector.normalize().scale(pull * 3.0)
        var position = player.eyePosition
        val path = ArrayList<Vec3>(CLIENT_BOW_STEPS + 1)
        path += position
        var landing: Vec3? = null
        for (ignored in 0 until CLIENT_BOW_STEPS) {
            var next = position.add(motion)
            val hit = level.clip(ClipContext(position, next, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player))
            if (hit.type != HitResult.Type.MISS) {
                next = hit.location
                path += next
                if (hit.type == HitResult.Type.BLOCK) landing = next
                break
            }
            path += next
            position = next
            motion = motion.scale(CLIENT_BOW_DRAG).add(0.0, -CLIENT_BOW_GRAVITY, 0.0)
        }
        return ClientBowResult(path, landing)
    }

    private fun WorldRenderEnvironment.renderClientBow(result: ClientBowResult) {
        val color = color(ClientTheme.accentA, 255)
        RenderSystem.lineWidth(thickness)
        longLines {
            for (index in 0 until result.path.lastIndex) {
                withColor(color) {
                    drawLines(relativeToCamera(result.path[index]), relativeToCamera(result.path[index + 1]))
                }
            }
        }
        if (!markers && (!landingBox || result.landing == null)) return

        BoxRenderer.drawWith(this) {
            if (markers) {
                result.path.drop(1).forEachIndexed { index, point ->
                    if (index % 3 == 0) {
                        withPositionRelativeToCamera(point) {
                            drawBox(MARKER_BOX, color.with(a = 60), color)
                        }
                    }
                }
            }
            if (landingBox) {
                result.landing?.let { point ->
                    withPositionRelativeToCamera(point) {
                        drawBox(LANDING_BOX, color.with(a = 60), color)
                    }
                }
            }
        }
    }

    private fun drawTag(context: DrawContext, impact: PredictionImpact) {
        val screen = WorldToScreen.calculateScreenPos(impact.position)?.takeIf(::isOnScreen) ?: return
        val text = formatTime(WyvernPredictionRules.seconds(impact.ticks))
        val textWidth = ExosGuiRenderer.textWidth(text, TAG_TEXT_SIZE)
        val width = max(TAG_MIN_WIDTH, textWidth + 16f)
        val height = TAG_HEIGHT
        val x = screen.x.toFloat() - width / 2f
        val y = screen.y.toFloat() - height / 2f + 5f

        ExosGuiRenderer.roundedRect(context.matrices, x, y, width, height, 0f, TAG_BACKGROUND)
        if (!impact.icon.isEmpty) {
            context.pose().pushMatrix()
            try {
                context.pose().translate(x + 2f, y + 3f)
                context.pose().scale(0.5f, 0.5f)
                context.drawItem(impact.icon, 0, 0)
            } finally {
                context.pose().popMatrix()
            }
        }
        ExosGuiRenderer.text(context.matrices, text, x + 13f, y + 4.5f, WHITE, TAG_TEXT_SIZE)
    }

    private fun isOnScreen(screen: RenderVec3): Boolean =
        screen.x.isFinite() && screen.y.isFinite() && screen.z > 0.0 && screen.z < 1.0

    internal fun formatTime(seconds: Double): String = String.format(Locale.ROOT, "%.1f s", seconds)

    private fun color(argb: Int, alpha: Int): Color4b = Color4b(
        (argb ushr 16) and 0xFF,
        (argb ushr 8) and 0xFF,
        argb and 0xFF,
        alpha,
    )

    private data class PredictionResult(val path: List<Vec3>, val impact: PredictionImpact?)

    private data class PredictionImpact(val position: Vec3, val ticks: Int, val icon: ItemStack)

    private data class ClientBowResult(val path: List<Vec3>, val landing: Vec3?)

    private const val CLIENT_BOW_STEPS = 100
    private const val CLIENT_BOW_DRAG = 0.99
    private const val CLIENT_BOW_GRAVITY = 0.05
    private const val TAG_HEIGHT = 14f
    private const val TAG_MIN_WIDTH = 35f
    private const val TAG_TEXT_SIZE = 7f
    private const val TAG_BACKGROUND = 0x78000000
    private const val WHITE = 0xFFFFFFFF.toInt()
    private val MARKER_BOX = AABB(-0.05, -0.05, -0.05, 0.05, 0.05, 0.05)
    private val LANDING_BOX = AABB(-0.2, -0.2, -0.2, 0.2, 0.2, 0.2)
}
