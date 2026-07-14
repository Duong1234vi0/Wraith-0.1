/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.trajectories

import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.engine.type.Vec3 as RenderVec3
import net.ccbluex.liquidbounce.render.drawLines
import net.ccbluex.liquidbounce.render.longLines
import net.ccbluex.liquidbounce.render.relativeToCamera
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withColor
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.deltaMovement
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.drawStackOverlay
import net.ccbluex.liquidbounce.utils.entity.getEntities
import net.ccbluex.liquidbounce.utils.entity.isTouchingWater
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.entity.stack
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

object ModulePredictions : ClientModule(
    "Prediction",
    Category.RENDER,
    aliases = arrayOf("Predictions", "Trajectories")
) {

    private val pearl by boolean("Pearl", true)
    private val item by boolean("Item", false)
    private val showTags by boolean("Tag", true)

    private var cacheWorldTime = Long.MIN_VALUE
    private var cachePearlSetting = pearl
    private var cacheItemSetting = item
    private var cachedResults: List<PredictionResult> = emptyList()

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val level = mc.level
        val player = mc.player
        if (level == null || player == null) {
            clearCache()
            return@handler
        }

        val results = getPredictions(level)
        if (results.isEmpty()) {
            return@handler
        }

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.lineWidth(2.5f)
            RenderSystem.disableDepthTest()
            RenderSystem.disableCull()
            try {
                longLines {
                    for (result in results) {
                        renderPath(result)
                    }
                }
            } finally {
                RenderSystem.lineWidth(1.0f)
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

        if (!showTags || mc.options.hideGui) {
            return@handler
        }

        for (result in getPredictions(level)) {
            drawTag(event.context, result)
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearCache()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearCache()
    }

    override fun disable() {
        clearCache()
        super.disable()
    }

    private fun clearCache() {
        cachedResults = emptyList()
        cacheWorldTime = Long.MIN_VALUE
    }

    private fun getPredictions(level: Level): List<PredictionResult> {
        val player = mc.player
        if (player == null || mc.level == null) {
            clearCache()
            return emptyList()
        }

        val worldTime = level.gameTime
        if (
            cachedResults.isEmpty() ||
            worldTime != cacheWorldTime ||
            pearl != cachePearlSetting ||
            item != cacheItemSetting
        ) {
            cachedResults = computePredictions(level, pearl, item)
            cacheWorldTime = worldTime
            cachePearlSetting = pearl
            cacheItemSetting = item
        }

        return cachedResults
    }

    private fun computePredictions(level: Level, pearlsEnabled: Boolean, itemsEnabled: Boolean): List<PredictionResult> {
        if (!pearlsEnabled && !itemsEnabled) {
            return emptyList()
        }

        val results = ArrayList<PredictionResult>()
        for (entity in level.getEntities()) {
            when {
                entity is ThrownEnderpearl && pearlsEnabled && !entity.onGround() -> {
                    simulate(level, entity, ItemStack(Items.ENDER_PEARL))?.let(results::add)
                }
                entity is ItemEntity && itemsEnabled && shouldSimulateItem(entity) -> {
                    simulate(level, entity, entity.stack.copy())?.let(results::add)
                }
            }
        }

        return results
    }

    private fun shouldSimulateItem(itemEntity: ItemEntity): Boolean {
        return !itemEntity.onGround() && itemEntity.deltaMovement.lengthSqr() > 1.0E-4
    }

    private fun simulate(level: Level, entity: Entity, icon: ItemStack): PredictionResult? {
        var position = entity.position()
        var motion = entity.deltaMovement
        val points = ArrayList<Vec3>()
        var landing = position
        var display = position
        var time = 0.0

        for (i in 0 until MAX_STEPS) {
            points += position
            val next = position.add(motion)
            val halfHeight = entity.bbHeight * 0.5
            val start = position.add(0.0, halfHeight, 0.0)
            val end = next.add(0.0, halfHeight, 0.0)
            val hit = findCollision(level, start, end)

            motion = updateMotion(entity, motion)
            time += STEP_SECONDS

            if (hit != null) {
                val hitPos = hit.location
                val segment = hitPos.subtract(position)
                var renderEnd = hitPos
                val segmentLength = segment.length()
                if (segmentLength > 0.001) {
                    val trim = min(0.5, max(0.0, segmentLength - 0.01))
                    if (trim > 0.0) {
                        renderEnd = hitPos.subtract(segment.normalize().scale(trim))
                    }
                }

                points += renderEnd
                landing = hitPos
                display = renderEnd
                break
            }

            if (next.y <= level.minY) {
                points += next
                landing = next
                display = next
                break
            }

            position = next
            landing = next
            display = next
        }

        return if (points.size < 2) {
            null
        } else {
            PredictionResult(points.toList(), landing, display, time, icon.copy())
        }
    }

    private fun updateMotion(entity: Entity, original: Vec3): Vec3 {
        var updated = if (entity.isTouchingWater) {
            original.scale(0.8)
        } else {
            original.scale(0.99)
        }

        updated = if ((entity.isNoGravity || entity !is AbstractArrow) && entity !is ItemEntity) {
            if (!entity.isNoGravity && entity is ThrownEnderpearl) {
                updated.add(0.0, -0.03, 0.0)
            } else {
                updated
            }
        } else {
            updated.add(0.0, -0.05, 0.0)
        }

        return updated
    }

    private fun findCollision(level: Level, start: Vec3, end: Vec3): HitResult? {
        val player = mc.player ?: return null
        val result = level.clip(ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player))
        return result.takeIf { it.type == HitResult.Type.BLOCK }
    }

    private fun WorldRenderEnvironment.renderPath(result: PredictionResult) {
        val path = result.path
        val layout = if (showTags && !mc.options.hideGui) {
            WorldToScreen.calculateScreenPos(result.displayPos)?.takeIf(::isOnScreen)?.let {
                createTagLayout(result, it)
            }
        } else {
            null
        }

        for (i in 0 until path.lastIndex) {
            val start = path[i]
            var end = path[i + 1]
            var clipSegment = false

            if (layout != null) {
                val startScreen = WorldToScreen.calculateScreenPos(start)
                val endScreen = WorldToScreen.calculateScreenPos(end)
                if (startScreen != null && endScreen != null && isValidScreenPoint(startScreen) &&
                    isValidScreenPoint(endScreen)
                ) {
                    val topY = layout.y.toDouble()
                    if (!(startScreen.y <= topY) || !(endScreen.y <= topY)) {
                        if (startScreen.y < topY && endScreen.y > topY) {
                            val t = ((topY - startScreen.y) / (endScreen.y - startScreen.y)).coerceIn(0.0, 1.0)
                            end = start.lerp(end, t)
                            clipSegment = true
                        } else if (startScreen.y >= topY && endScreen.y >= topY) {
                            break
                        }
                    }
                }
            }

            drawSegment(start, end, i)
            if (clipSegment) {
                break
            }
        }
    }

    private fun WorldRenderEnvironment.drawSegment(start: Vec3, end: Vec3, index: Int) {
        withColor(themeColor(index * 2)) {
            drawLines(relativeToCamera(start), relativeToCamera(end))
        }
    }

    private fun drawTag(context: DrawContext, result: PredictionResult) {
        val screen = WorldToScreen.calculateScreenPos(result.displayPos)?.takeIf(::isOnScreen) ?: return
        val layout = createTagLayout(result, screen)
        val matrices = context.matrices

        context.pose().pushMatrix()
        try {
            applyTagScale(context, layout)
            ExosGuiRenderer.roundedRect(matrices, layout.x, layout.y, layout.width, layout.height, 3f, TAG_BACKGROUND)
            if (!result.icon.isEmpty) {
                context.pose().pushMatrix()
                try {
                    context.pose().translate(layout.iconX, layout.iconY)
                    context.pose().scale(layout.iconScale, layout.iconScale)
                    context.drawItem(result.icon, 0, 0)
                    context.drawStackOverlay(mc.font, result.icon, 0, 0)
                } finally {
                    context.pose().popMatrix()
                }
            }

            ExosGuiRenderer.text(matrices, layout.text, layout.textX, layout.textY, WHITE, TAG_TEXT_SIZE)
        } finally {
            context.pose().popMatrix()
        }
    }

    private fun applyTagScale(context: DrawContext, layout: TagLayout) {
        val centerX = layout.x + layout.width / 2f
        val centerY = layout.y + layout.height / 2f
        context.pose().translate(centerX, centerY)
        context.pose().scale(TAG_SCALE, TAG_SCALE)
        context.pose().translate(-centerX, -centerY)
    }

    private fun createTagLayout(result: PredictionResult, screen: RenderVec3): TagLayout {
        val text = formatTime(result.timeSeconds)
        val textWidth = ExosGuiRenderer.textWidth(text, TAG_TEXT_SIZE)
        val width = max(TAG_MIN_WIDTH, textWidth + 20f)
        val height = TAG_HEIGHT
        val x = screen.x.toFloat() - width / 2f
        val y = screen.y.toFloat() + 4f
        val iconScale = 0.8f
        val iconSize = 16f * iconScale
        val iconX = x + 2f
        val iconY = y + (height - iconSize) / 2f
        val centeredTextX = x + width / 2f - textWidth / 2f
        val minTextX = iconX + iconSize + 2f
        val textX = max(minTextX, centeredTextX)
        val textHeight = ExosGuiRenderer.textHeight(text, TAG_TEXT_SIZE)
        val textY = y + (height - textHeight) / 2f

        return TagLayout(text, x, y, width, height, iconX, iconY, iconScale, textX, textY)
    }

    private fun isOnScreen(screenPos: RenderVec3): Boolean {
        return screenPos.x.isFinite() && screenPos.y.isFinite() && screenPos.z > 0.0 && screenPos.z < 1.0
    }

    private fun isValidScreenPoint(screenPos: RenderVec3): Boolean = isOnScreen(screenPos)

    private fun formatTime(seconds: Double): String = String.format(Locale.ROOT, "%.1f s", seconds)

    private fun themeColor(count: Int): Color4b {
        val angleBase = ((System.currentTimeMillis() / 10.0 + count) % 360.0).toInt()
        val angle = (if (angleBase >= 180) 360 - angleBase else angleBase) * 2
        val color = ExosGuiRenderer.mix(ClientTheme.accentA, ClientTheme.accentB, angle / 360f)
        return Color4b(
            (color shr 16) and 0xFF,
            (color shr 8) and 0xFF,
            color and 0xFF,
            200
        )
    }

    private data class PredictionResult(
        val path: List<Vec3>,
        val landingPos: Vec3,
        val displayPos: Vec3,
        val timeSeconds: Double,
        val icon: ItemStack
    )

    private data class TagLayout(
        val text: String,
        val x: Float,
        val y: Float,
        val width: Float,
        val height: Float,
        val iconX: Float,
        val iconY: Float,
        val iconScale: Float,
        val textX: Float,
        val textY: Float
    )

    private const val MAX_STEPS = 600
    private const val STEP_SECONDS = 0.05
    private const val TAG_SCALE = 0.85f
    private const val TAG_HEIGHT = 14f
    private const val TAG_MIN_WIDTH = 35f
    private const val TAG_TEXT_SIZE = 9f
    private const val TAG_BACKGROUND = 0x96000000.toInt()
    private const val WHITE = 0xFFFFFFFF.toInt()
}
