/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
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

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.world.phys.Vec3
import java.util.Random
import kotlin.math.roundToInt

/**
 * Wyvern-DLC LineGlyphes behavior ported to this 1.26 render stack.
 */
object ModuleLineGlyphs : ClientModule("LineGlyphs", Category.RENDER, aliases = arrayOf("LineGlyphes")) {

    private val count by int("Count", 50, 10..200)
    private val speed by float("Speed", 1.0f, 0.1f..5.0f)
    private val glow by boolean("Glow", true)
    private val thickness by float("Thickness", 1.5f, 0.5f..5.0f)

    private val random = Random()
    private val paths = ArrayList<Path>(200)

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (mc.player == null || mc.level == null) {
            paths.clear()
            return@handler
        }

        val iterator = paths.iterator()
        while (iterator.hasNext()) {
            val path = iterator.next()
            path.update(speed)
            if (path.isDead()) {
                iterator.remove()
            }
        }

        val target = count.coerceIn(0, MAX_PATHS)
        var active = 0
        for (path in paths) {
            if (!path.removing) {
                active++
            }
        }

        while (active < target) {
            paths += Path(randomSpawnPos(), 12 + random.nextInt(15))
            active++
        }

        if (active > target) {
            for (path in paths) {
                if (!path.removing) {
                    path.remove()
                    active--
                    if (active <= target) {
                        break
                    }
                }
            }
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        paths.clear()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (paths.isEmpty() || mc.player == null) {
            return@handler
        }

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableBlend()
            RenderSystem.enableDepthTest()
            RenderSystem.disableCull()
            RenderSystem.depthMask(false)

            if (glow) {
                RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
                RenderSystem.enableAdditive()
                drawPathPass(event.camera.position(), event.partialTicks, 8.0f, 0.04f)
                drawPathPass(event.camera.position(), event.partialTicks, 5.0f, 0.08f)
                drawPathPass(event.camera.position(), event.partialTicks, 2.5f, 0.15f)
                drawPathPass(event.camera.position(), event.partialTicks, 1.35f, 0.22f)
            }

            RenderSystem.defaultBlendFunc()
            drawPathPass(event.camera.position(), event.partialTicks, 1.0f, 1.0f)

            RenderSystem.lineWidth(1.0f)
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
            RenderSystem.defaultBlendFunc()
        }
    }

    override fun disable() {
        paths.clear()
        super.disable()
    }

    private fun WorldRenderEnvironment.drawPathPass(cameraPos: Vec3, tickDelta: Float, widthMul: Float, alphaMul: Float) {
        val lineWidth = (thickness * widthMul).coerceIn(0.5f, 18.0f)
        val baseColorA = ClientTheme.accentA
        val baseColorB = ClientTheme.accentB
        if (paths.none { it.pointCount >= 2 && it.alpha > 0.005f }) {
            return
        }

        RenderSystem.lineWidth(lineWidth)
        drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (pathIndex in paths.indices) {
                val path = paths[pathIndex]
                val alpha = path.alpha * alphaMul
                if (path.pointCount < 2 || alpha <= 0.005f) {
                    continue
                }

                val progress = path.renderProgress(tickDelta)
                val last = path.pointCount - 1
                for (index in 0 until last) {
                    val from = path.point(index, progress).subtract(cameraPos)
                    val to = path.point(index + 1, progress).subtract(cameraPos)
                    val segmentAlpha = alpha * ((index + 1).toFloat() / path.pointCount.toFloat())
                    if (segmentAlpha <= 0.005f) {
                        continue
                    }

                    val color = themeColor(baseColorA, baseColorB, pathIndex + index, segmentAlpha)
                    this.vertex(matrix, from.x.toFloat(), from.y.toFloat(), from.z.toFloat()).setColor(color)
                    this.vertex(matrix, to.x.toFloat(), to.y.toFloat(), to.z.toFloat()).setColor(color)
                }
            }
        }
    }

    private fun randomSpawnPos(): Vec3 {
        val player = mc.player ?: return Vec3.ZERO
        val range = 30.0
        return Vec3(
            player.x + (random.nextDouble() - 0.5) * range * 2.0,
            player.y + (random.nextDouble() - 0.5) * 15.0,
            player.z + (random.nextDouble() - 0.5) * range * 2.0
        )
    }

    private fun randomDirection(): Direction3 {
        val sign = if (random.nextBoolean()) 1.0 else -1.0
        return when (random.nextInt(3)) {
            0 -> Direction3(sign, 0.0, 0.0)
            1 -> Direction3(0.0, sign, 0.0)
            else -> Direction3(0.0, 0.0, sign)
        }
    }

    private fun nextDirection(lastDirection: Direction3): Direction3 {
        repeat(12) {
            val direction = randomDirection()
            if (direction.dot(lastDirection) >= -0.5) {
                return direction
            }
        }

        return randomDirection()
    }

    private fun themeColor(first: Int, second: Int, offset: Int, alpha: Float): Int {
        val progress = ((System.currentTimeMillis() / 18L + offset * 9L) % 120L).toFloat() / 119.0f
        val red = lerpChannel(first shr 16, second shr 16, progress)
        val green = lerpChannel(first shr 8, second shr 8, progress)
        val blue = lerpChannel(first, second, progress)
        val alphaChannel = (alpha.coerceIn(0.0f, 1.0f) * 255.0f).roundToInt().coerceIn(0, 255)
        return (alphaChannel shl 24) or (red shl 16) or (green shl 8) or blue
    }

    private fun lerpChannel(from: Int, to: Int, progress: Float): Int {
        val start = from and 0xFF
        val end = to and 0xFF
        return (start + (end - start) * progress).roundToInt().coerceIn(0, 255)
    }

    private class Path(start: Vec3, private val maxPoints: Int) {
        private val points = ArrayList<Vec3>(32)
        private val alphaFade = AlphaFade()
        private var lastDirection = Direction3(0.0, 0.0, 0.0)
        private var moveProgress = 0.0f
        private var previousMoveProgress = 0.0f

        var removing = false
            private set

        val pointCount: Int
            get() = points.size

        val alpha: Float
            get() = alphaFade.value

        init {
            points += start
            alphaFade.snap(0.0f)
        }

        fun update(speedMul: Float) {
            alphaFade.target(if (removing) 0.0f else 1.0f)
            if (removing) {
                return
            }

            previousMoveProgress = moveProgress
            moveProgress += 0.025f * speedMul
            if (moveProgress >= 1.0f) {
                previousMoveProgress = 0.0f
                moveProgress = 0.0f
                addPoint()
            }

            if (points.size >= maxPoints) {
                remove()
            }
        }

        fun renderProgress(tickDelta: Float): Float {
            return (previousMoveProgress + (moveProgress - previousMoveProgress) * tickDelta).coerceIn(0.0f, 1.0f)
        }

        fun point(index: Int, progress: Float): Vec3 {
            if (index == points.lastIndex && points.size >= 2 && !removing) {
                val previous = points[index - 1]
                val current = points[index]
                return Vec3(
                    previous.x + (current.x - previous.x) * progress,
                    previous.y + (current.y - previous.y) * progress,
                    previous.z + (current.z - previous.z) * progress
                )
            }

            return points[index]
        }

        fun remove() {
            removing = true
        }

        fun isDead(): Boolean {
            return removing && alphaFade.value <= 0.01f
        }

        private fun addPoint() {
            val last = points.lastOrNull() ?: return remove()
            val direction = ModuleLineGlyphs.nextDirection(lastDirection)
            lastDirection = direction
            points += last.add(direction.x * STEP_LENGTH, direction.y * STEP_LENGTH, direction.z * STEP_LENGTH)
        }
    }

    private class AlphaFade {
        private var from = 0.0f
        private var target = 0.0f
        private var started = System.nanoTime()

        val value: Float
            get() {
                val elapsed = ((System.nanoTime() - started).toDouble() / 1_000_000_000.0 / ALPHA_SECONDS)
                    .coerceIn(0.0, 1.0)
                    .toFloat()
                val eased = if (target > from) {
                    elapsed * elapsed
                } else {
                    1.0f - (1.0f - elapsed) * (1.0f - elapsed)
                }
                return from + (target - from) * eased
            }

        fun snap(value: Float) {
            from = value
            target = value
            started = System.nanoTime()
        }

        fun target(newTarget: Float) {
            if (kotlin.math.abs(newTarget - target) <= 1.0E-4f) {
                return
            }

            from = value
            target = newTarget
            started = System.nanoTime()
        }
    }

    private data class Direction3(val x: Double, val y: Double, val z: Double) {
        fun dot(other: Direction3): Double = x * other.x + y * other.y + z * other.z
    }

    private const val MAX_PATHS = 160
    private const val STEP_LENGTH = 2.5
    private const val ALPHA_SECONDS = 1.2f
}
