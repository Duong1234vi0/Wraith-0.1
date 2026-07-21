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

import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.client.CameraType
import net.minecraft.world.phys.Vec3
import java.util.Random
import kotlin.math.roundToInt
import kotlin.math.sqrt

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
    private val frameGeometry = LineGlyphFrameGeometry()

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (mc.player == null || mc.level == null) {
            paths.clear()
            return@handler
        }

        paths.removeAll(Path::isDead)
        paths.forEach { it.update(speed) }

        if (paths.size < count) {
            paths += Path(randomSpawnPos(), 12 + random.nextInt(15))
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        paths.clear()
        frameGeometry.clear()
        LineGlyphBatchRenderer.clearFrame()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (paths.isEmpty() || mc.player == null) {
            return@handler
        }

        try {
            prepareFrameGeometry(event.camera.position(), event.partialTicks)
            if (frameGeometry.lineCount == 0) return@handler
            LineGlyphBatchRenderer.beginFrame()
            if (glow) {
                WyvernLineGlyphRules.glowPasses.forEachIndexed { index, (width, alpha) ->
                    appendPreparedPass(index, thickness * width, alpha)
                }
            }
            appendPreparedPass(LineGlyphBatchRenderer.LOGICAL_PASS_COUNT - 1, thickness, 1f)
            LineGlyphBatchRenderer.flush(event.matrixStack.last().pose())
        } finally {
            LineGlyphBatchRenderer.clearFrame()
        }
    }

    override fun disable() {
        paths.clear()
        frameGeometry.clear()
        LineGlyphBatchRenderer.clearFrame()
        super.disable()
    }

    private fun prepareFrameGeometry(cameraPos: Vec3, tickDelta: Float) {
        frameGeometry.clear()
        val firstPerson = mc.options.cameraType == CameraType.FIRST_PERSON
        val cameraLook = cameraLookVector()
        for (path in paths) {
            val pathAlpha = path.alpha
            if (path.pointCount < 2 || pathAlpha <= 0.005f) continue

            val progress = path.renderProgress(tickDelta)
            val firstPoint = path.point(0, progress)
            if (WyvernLineGlyphRules.shouldCullFirstPerson(
                    firstPerson,
                    firstPoint.subtract(cameraPos).dot(cameraLook),
                )
            ) {
                continue
            }

            for (index in 0 until path.pointCount - 1) {
                val segmentAlpha = WyvernLineGlyphRules.segmentAlpha(pathAlpha, index, path.pointCount)
                if (segmentAlpha <= 0.005f) continue
                appendPreparedDashes(
                    path.point(index, progress),
                    path.point(index + 1, progress),
                    cameraPos,
                    segmentAlpha,
                )
            }
        }
    }

    private fun appendPreparedPass(passIndex: Int, lineWidth: Float, alphaMultiplier: Float) {
        val baseColor = ClientTheme.accentA
        frameGeometry.forEach { x1, y1, z1, x2, y2, z2, sourceAlpha ->
            val alpha = sourceAlpha * alphaMultiplier
            if (alpha > 0.005f) {
                LineGlyphBatchRenderer.appendLine(
                    passIndex, x1, y1, z1, x2, y2, z2,
                    withAlpha(baseColor, alpha), lineWidth,
                )
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

    private fun cameraLookVector(): Vec3 {
        val look = org.joml.Vector3f(0f, 0f, -1f).rotate(mc.gameRenderer.camera.rotation)
        return Vec3(look.x.toDouble(), look.y.toDouble(), look.z.toDouble())
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
        var direction: Direction3
        do {
            direction = randomDirection()
        } while (!WyvernLineGlyphRules.acceptsDirection(direction.dot(lastDirection)))
        return direction
    }

    private fun withAlpha(color: Int, alpha: Float): Int {
        val alphaChannel = (alpha.coerceIn(0.0f, 1.0f) * 255.0f).roundToInt().coerceIn(0, 255)
        return (alphaChannel shl 24) or (color and 0x00FFFFFF)
    }

    private fun appendPreparedDashes(
        start: Vec3,
        end: Vec3,
        cameraPos: Vec3,
        alpha: Float,
    ) {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val dz = end.z - start.z
        val length = sqrt(dx * dx + dy * dy + dz * dz)
        if (length < 0.0001) return

        if (length <= WyvernLineGlyphRules.DASH_LENGTH + WyvernLineGlyphRules.GAP_LENGTH + 1.0E-9) {
            frameGeometry.append(
                (start.x - cameraPos.x).toFloat(),
                (start.y - cameraPos.y).toFloat(),
                (start.z - cameraPos.z).toFloat(),
                (end.x - cameraPos.x).toFloat(),
                (end.y - cameraPos.y).toFloat(),
                (end.z - cameraPos.z).toFloat(),
                alpha,
            )
            return
        }

        val nx = dx / length
        val ny = dy / length
        val nz = dz / length
        var distance = 0.0
        while (distance < length) {
            val dashEnd = minOf(length, distance + WyvernLineGlyphRules.DASH_LENGTH)
            frameGeometry.append(
                (start.x + nx * distance - cameraPos.x).toFloat(),
                (start.y + ny * distance - cameraPos.y).toFloat(),
                (start.z + nz * distance - cameraPos.z).toFloat(),
                (start.x + nx * dashEnd - cameraPos.x).toFloat(),
                (start.y + ny * dashEnd - cameraPos.y).toFloat(),
                (start.z + nz * dashEnd - cameraPos.z).toFloat(),
                alpha,
            )
            distance += WyvernLineGlyphRules.DASH_LENGTH + WyvernLineGlyphRules.GAP_LENGTH
        }
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
            moveProgress += WyvernLineGlyphRules.MOVEMENT_PROGRESS * speedMul
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

        private fun remove() {
            removing = true
        }

        fun isDead(): Boolean {
            return removing && alphaFade.value <= 0.01f
        }

        private fun addPoint() {
            val last = points.lastOrNull() ?: return remove()
            val direction = ModuleLineGlyphs.nextDirection(lastDirection)
            lastDirection = direction
            points += last.add(
                direction.x * WyvernLineGlyphRules.STEP_LENGTH,
                direction.y * WyvernLineGlyphRules.STEP_LENGTH,
                direction.z * WyvernLineGlyphRules.STEP_LENGTH,
            )
        }
    }

    private class AlphaFade {
        private var from = 0.0f
        private var target = 0.0f
        private var started = System.currentTimeMillis()

        val value: Float
            get() {
                val elapsed = ((System.currentTimeMillis() - started).toFloat() /
                    WyvernLineGlyphRules.ALPHA_DURATION_MS)
                    .coerceIn(0.0f, 1.0f)
                val eased = WyvernLineGlyphRules.quadInOut(elapsed)
                return from + (target - from) * eased
            }

        fun snap(value: Float) {
            from = value
            target = value
            started = System.currentTimeMillis()
        }

        fun target(newTarget: Float) {
            if (kotlin.math.abs(newTarget - target) <= 1.0E-4f) {
                return
            }

            from = value
            target = newTarget
            started = System.currentTimeMillis()
        }
    }

    private data class Direction3(val x: Double, val y: Double, val z: Double) {
        fun dot(other: Direction3): Double = x * other.x + y * other.y + z * other.z
    }

}
