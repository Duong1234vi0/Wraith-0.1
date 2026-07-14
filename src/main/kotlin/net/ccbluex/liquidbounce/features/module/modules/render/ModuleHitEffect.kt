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
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaDlcWorldRenderer
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.block.getOutlineShape
import net.ccbluex.liquidbounce.utils.block.isAir
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.READ_FINAL_STATE
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Xyeta/Wild HitEffect port.
 *
 * The source effect is a terrain block-outline wave that starts at the attacked entity block,
 * not a target particle burst. Only the empty decompiler catch was adapted: invalid shape draws
 * are contained and the first unexpected render failure is logged.
 */
object ModuleHitEffect : ClientModule("HitEffect", Category.RENDER) {

    private const val DURATION_MS = 2000L
    private const val MAX_RADIUS = 8
    private const val RING_THICKNESS = 0.8f
    private const val MAX_BLOCKS_PER_WAVE_FRAME = 400
    private const val SEARCH_HEIGHT = 10

    private val waves = ArrayList<WaveEffect>()
    private var renderFailureLogged = false

    override fun disable() {
        clearWaves()
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent>(priority = READ_FINAL_STATE) { event ->
        if (event.isCancelled || mc.level == null) {
            return@handler
        }

        waves += WaveEffect(event.entity.blockPosition(), System.currentTimeMillis())
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val level = mc.level ?: return@handler
        if (waves.isEmpty()) {
            return@handler
        }

        val now = System.currentTimeMillis()
        val iterator = waves.iterator()
        while (iterator.hasNext()) {
            val wave = iterator.next()
            val elapsed = now - wave.startTime
            val progress = elapsed.toFloat() / DURATION_MS.toFloat()

            if (progress > 1.0f) {
                iterator.remove()
                continue
            }

            renderWave(level, event, wave, progress)
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearWaves()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearWaves()
    }

    private fun renderWave(level: Level, event: WorldRenderEvent, wave: WaveEffect, progress: Float) {
        val globalAlpha = smoothAlpha(progress)
        if (globalAlpha <= 0.01f) {
            return
        }

        val currentRadius = easeOutCubic(progress) * MAX_RADIUS
        val modelViewMatrix = event.matrixStack.last().pose()
        val cameraPos = event.camera.position()
        var rendered = 0

        for (x in -MAX_RADIUS..MAX_RADIUS) {
            for (z in -MAX_RADIUS..MAX_RADIUS) {
                if (rendered >= MAX_BLOCKS_PER_WAVE_FRAME) {
                    return
                }

                val distance = sqrt((x * x + z * z).toDouble())
                val diff = abs(distance - currentRadius.toDouble())
                if (diff > RING_THICKNESS) {
                    continue
                }

                val renderPos = findBlock(level, wave.centerPos.offset(x, 0, z)) ?: continue
                val state = level.getBlockState(renderPos)
                if (state.isAir) {
                    continue
                }

                val shape = state.getOutlineShape(level, renderPos)
                if (shape.isEmpty) {
                    continue
                }

                rendered++
                var localAlpha = (1.1 - diff / RING_THICKNESS).toFloat()
                localAlpha *= localAlpha
                localAlpha *= globalAlpha
                if (localAlpha <= 0.015f) {
                    continue
                }

                val alpha = (localAlpha * 250.0f).toInt().coerceIn(0, 255)
                val color = Color4b(ClientTheme.accentA).with(a = alpha)
                val lineWidth = 1.0f + localAlpha * 2.5f

                runCatching {
                    AlphaDlcWorldRenderer.drawShapeAlternative(
                        modelViewMatrix,
                        cameraPos,
                        renderPos,
                        shape,
                        color,
                        lineWidth,
                        fill = true,
                        lines = true,
                        depth = true
                    )
                }.onFailure { throwable ->
                    if (!renderFailureLogged) {
                        renderFailureLogged = true
                        logger.error("HitEffect failed to render a block wave shape.", throwable)
                    }
                }
            }
        }
    }

    private fun findBlock(level: Level, start: BlockPos): BlockPos? {
        if (level.isOutsideBuildHeight(start)) {
            return null
        }

        if (!level.getBlockState(start).isAir) {
            return start
        }

        for (y in 1..SEARCH_HEIGHT) {
            val down = start.below(y)
            if (!level.isOutsideBuildHeight(down) && !level.getBlockState(down).isAir) {
                return down
            }

            val up = start.above(y)
            if (!level.isOutsideBuildHeight(up) && !level.getBlockState(up).isAir) {
                return up
            }
        }

        return null
    }

    private fun clearWaves() {
        waves.clear()
        renderFailureLogged = false
    }

    private fun easeOutCubic(t: Float): Float = 1.0f - (1.0f - t).toDouble().pow(3.0).toFloat()

    private fun smoothAlpha(progress: Float): Float {
        var fadeIn = (progress / 0.1f).coerceAtMost(1.0f)
        fadeIn = fadeIn * fadeIn * (3.0f - 2.0f * fadeIn)
        val fadeOut = (1.0f - progress).toDouble().pow(4.8).toFloat()
        return fadeIn * fadeOut
    }

    private data class WaveEffect(val centerPos: BlockPos, val startTime: Long)
}
