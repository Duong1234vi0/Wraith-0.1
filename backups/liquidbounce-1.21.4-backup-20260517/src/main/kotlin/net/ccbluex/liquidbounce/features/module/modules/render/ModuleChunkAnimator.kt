/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.render.chunk.ChunkBuilder
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import java.util.HashSet
import java.util.WeakHashMap
import kotlin.math.abs
import kotlin.math.pow

/**
 * Dreamcore Chunk Animator port.
 */
object ModuleChunkAnimator : ClientModule("ChunkAnimator", Category.RENDER) {

    private val time by int("Time", 800, 50..1500, "ms")
    private val mode by enumChoice("Modes", Mode.LINEAR).apply { tagBy(this) }

    private val animations = WeakHashMap<ChunkBuilder.BuiltChunk, AnimationData>()
    private val chunkSections = WeakHashMap<ChunkBuilder.BuiltChunk, Long>()
    private val seenSections = HashSet<Long>()

    private var warmupUntil = 0L
    private var anchorChunkX: Int? = null
    private var anchorChunkZ: Int? = null

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetWithWarmup()
    }

    override fun enable() {
        resetWithWarmup()
    }

    override fun disable() {
        clear()
    }

    @JvmStatic
    fun setPosition(chunk: ChunkBuilder.BuiltChunk) {
        val origin = chunk.origin
        val originX = origin.x
        val originY = origin.y
        val originZ = origin.z
        val sectionKey = sectionKey(originX, originY, originZ)
        val previousSection = chunkSections.put(chunk, sectionKey)
        val sectionWasSeen = !seenSections.add(sectionKey)

        if (!running) {
            animations.remove(chunk)
            return
        }

        val player = mc.player ?: return
        primeAnchor(player.blockX, player.blockZ)

        if (
            System.currentTimeMillis() < warmupUntil ||
            !playerMovedFromAnchor(player.blockX, player.blockZ) ||
            previousSection == null ||
            previousSection == sectionKey ||
            sectionWasSeen
        ) {
            animations.remove(chunk)
            return
        }

        val playerX = player.blockX
        val playerZ = player.blockZ
        val chunkCenterX = originX + 8
        val chunkCenterZ = originZ + 8
        val diffX = playerX - chunkCenterX
        val diffZ = playerZ - chunkCenterZ

        if (diffX * diffX + diffZ * diffZ > ANIMATION_DISTANCE_SQUARED) {
            animations[chunk] = AnimationData(
                timestamp = -1L,
                originX = originX,
                originY = originY,
                originZ = originZ,
                chunkFacing = if (mode == Mode.QUARTA) getChunkFacing(diffX, diffZ) else null
            )
        } else {
            animations.remove(chunk)
        }
    }

    @JvmStatic
    fun getOffset(chunk: ChunkBuilder.BuiltChunk): Vec3d {
        val origin = chunk.origin
        if (!running) {
            return Vec3d.ZERO
        }

        val animationData = animations[chunk] ?: return Vec3d.ZERO
        val now = System.currentTimeMillis()
        var startTime = animationData.timestamp

        if (startTime == -1L) {
            startTime = now
            animationData.timestamp = startTime

            if (mode == Mode.EXPO && animationData.chunkFacing == null) {
                val player = mc.player
                if (player != null) {
                    animationData.chunkFacing = getChunkFacing(
                        player.blockX - (origin.x + 8),
                        player.blockZ - (origin.z + 8)
                    )
                } else {
                    animationData.chunkFacing = Direction.NORTH
                }
            }
        }

        val elapsed = now - startTime
        val duration = time
        if (elapsed >= duration) {
            animations.remove(chunk)
            return Vec3d.ZERO
        }

        val chunkY = animationData.originY
        val animationMode = when (mode) {
            Mode.CUBE -> if (chunkY < 0) 0 else 1
            Mode.EXPO -> 3
            else -> mode.ordinal
        }

        val eased = functionValue(elapsed.toFloat(), 0f, when (animationMode) {
            0 -> chunkY.toFloat()
            1 -> 256f - chunkY
            3 -> 200f
            else -> 0f
        }, duration.toFloat())

        return when (animationMode) {
            0 -> Vec3d(0.0, -chunkY + eased.toDouble(), 0.0)
            1 -> Vec3d(0.0, 256.0 - chunkY - eased.toDouble(), 0.0)
            3 -> {
                val facing = animationData.chunkFacing ?: Direction.NORTH
                val vector = facing.vector
                val modifier = -(200.0 - eased)
                Vec3d(vector.x * modifier, 0.0, vector.z * modifier)
            }

            else -> Vec3d.ZERO
        }
    }

    private fun functionValue(t: Float, b: Float, c: Float, d: Float): Float {
        return when (mode) {
            Mode.LINEAR -> c * t / d + b
            Mode.QUAD -> {
                val value = t / d
                -c * value * (value - 2f) + b
            }
            Mode.CUBE -> c * ((t / d - 1f).pow(3) + 1f) + b
            Mode.QUARTA -> -c * ((t / d - 1f).pow(4) - 1f) + b
            Mode.EXPO -> c * ((t / d - 1f).pow(5) + 1f) + b
        }
    }

    private fun getChunkFacing(diffX: Int, diffZ: Int): Direction {
        return if (abs(diffX) > abs(diffZ)) {
            if (diffX > 0) Direction.EAST else Direction.WEST
        } else {
            if (diffZ > 0) Direction.SOUTH else Direction.NORTH
        }
    }

    private fun resetWithWarmup() {
        clear()
        warmupUntil = System.currentTimeMillis() + INITIAL_WARMUP_MS

        val player = mc.player
        if (player != null) {
            primeAnchor(player.blockX, player.blockZ)
        }
    }

    private fun clear() {
        animations.clear()
        chunkSections.clear()
        seenSections.clear()
        anchorChunkX = null
        anchorChunkZ = null
    }

    private fun primeAnchor(playerX: Int, playerZ: Int) {
        if (anchorChunkX != null && anchorChunkZ != null) {
            return
        }

        anchorChunkX = Math.floorDiv(playerX, 16)
        anchorChunkZ = Math.floorDiv(playerZ, 16)
    }

    private fun playerMovedFromAnchor(playerX: Int, playerZ: Int): Boolean {
        val anchorX = anchorChunkX ?: return false
        val anchorZ = anchorChunkZ ?: return false

        return Math.floorDiv(playerX, 16) != anchorX || Math.floorDiv(playerZ, 16) != anchorZ
    }

    private fun sectionKey(originX: Int, originY: Int, originZ: Int): Long {
        val sectionX = Math.floorDiv(originX, 16)
        val sectionY = Math.floorDiv(originY, 16)
        val sectionZ = Math.floorDiv(originZ, 16)

        return ((sectionX.toLong() and 0x3FFFFFFL) shl 38) or
            ((sectionZ.toLong() and 0x3FFFFFFL) shl 12) or
            (sectionY.toLong() and 0xFFFL)
    }

    private data class AnimationData(
        var timestamp: Long,
        val originX: Int,
        val originY: Int,
        val originZ: Int,
        var chunkFacing: Direction?
    )

    enum class Mode(override val choiceName: String) : NamedChoice {
        LINEAR("Linear"),
        QUAD("Quad"),
        CUBE("Cube"),
        QUARTA("Quarta"),
        EXPO("Expo")
    }

    private const val ANIMATION_DISTANCE_SQUARED = 32 * 32
    private const val INITIAL_WARMUP_MS = 2500L
}
