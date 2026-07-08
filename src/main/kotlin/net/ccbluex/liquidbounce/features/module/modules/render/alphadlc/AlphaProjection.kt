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
package net.ccbluex.liquidbounce.features.module.modules.render.alphadlc

import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.camera
import net.minecraft.client.renderer.culling.Frustum
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f
import org.joml.Vector3fc
import org.joml.Vector4f
import kotlin.math.max
import kotlin.math.min

object AlphaProjection {

    private var currentFrustum: Frustum? = null
    private val projectionMatrix = Matrix4f()
    private val modelViewMatrix = Matrix4f()
    private var cameraPos: Vec3 = Vec3.ZERO
    private var matricesReady = false

    private val transformed = Vector4f()
    private val projected = Vector3f()
    private val viewport = IntArray(4)
    private val screenScratch = DoubleArray(3)

    @JvmStatic
    fun setFrustum(frustum: Frustum?) {
        currentFrustum = frustum
    }

    @JvmStatic
    fun setMatrices(projectionMatrix: Matrix4fc, modelViewMatrix: Matrix4fc, cameraPos: Vec3) {
        this.projectionMatrix.set(projectionMatrix)
        this.modelViewMatrix.set(modelViewMatrix)
        this.cameraPos = cameraPos
        matricesReady = true
    }

    fun maxRenderDistanceSq(): Double {
        val distance = (mc.options.renderDistance().get().toDouble() * 16.0)
        return distance * distance
    }

    fun isWithinRenderDistance(entity: Entity): Boolean {
        val player = mc.player ?: return false
        return entity.distanceToSqr(player) <= maxRenderDistanceSq()
    }

    fun canSee(box: AABB): Boolean = currentFrustum?.isVisible(box) ?: true

    fun isInFrontOfCamera(pos: Vec3): Boolean {
        val camera = mc.gameRenderer.camera
        val delta = pos.subtract(camera.position())
        val forward = camera.forwardVector()
        return dot(forward, delta) > 0.0
    }

    fun projectEntityBox(entity: Entity, tickDelta: Float): ScreenBox? {
        if (!isWithinRenderDistance(entity)) {
            return null
        }

        val interpX = entity.xo + (entity.x - entity.xo) * tickDelta
        val interpY = entity.yo + (entity.y - entity.yo) * tickDelta
        val interpZ = entity.zo + (entity.z - entity.zo) * tickDelta
        val box = entity.boundingBox
            .move(interpX - entity.x, interpY - entity.y, interpZ - entity.z)
            .inflate(0.1)

        var screenBox: ScreenBox? = null
        screenBox = mergeCorner(screenBox, box.minX, box.minY, box.minZ)
        screenBox = mergeCorner(screenBox, box.minX, box.maxY, box.minZ)
        screenBox = mergeCorner(screenBox, box.maxX, box.minY, box.minZ)
        screenBox = mergeCorner(screenBox, box.maxX, box.maxY, box.minZ)
        screenBox = mergeCorner(screenBox, box.minX, box.minY, box.maxZ)
        screenBox = mergeCorner(screenBox, box.minX, box.maxY, box.maxZ)
        screenBox = mergeCorner(screenBox, box.maxX, box.minY, box.maxZ)
        screenBox = mergeCorner(screenBox, box.maxX, box.maxY, box.maxZ)

        val visibleBox = screenBox ?: return null
        return if (cantSee(visibleBox)) null else visibleBox
    }

    private fun mergeCorner(box: ScreenBox?, x: Double, y: Double, z: Double): ScreenBox? {
        if (!worldSpaceToScreenSpaceInto(x, y, z, screenScratch)) {
            return box
        }

        val screenX = screenScratch[0]
        val screenY = screenScratch[1]
        return if (box == null) {
            ScreenBox(screenX, screenY, screenX, screenY)
        } else {
            ScreenBox(
                min(box.minX, screenX),
                min(box.minY, screenY),
                max(box.maxX, screenX),
                max(box.maxY, screenY)
            )
        }
    }

    private fun worldSpaceToScreenSpaceInto(x: Double, y: Double, z: Double, out: DoubleArray): Boolean {
        if (!matricesReady) {
            return false
        }

        val framebuffer = mc.mainRenderTarget
        val viewportWidth = framebuffer.width
        val viewportHeight = framebuffer.height
        if (viewportWidth <= 0 || viewportHeight <= 0) {
            return false
        }

        transformed.set(
            (x - cameraPos.x).toFloat(),
            (y - cameraPos.y).toFloat(),
            (z - cameraPos.z).toFloat(),
            1.0f
        ).mul(modelViewMatrix)

        viewport[0] = 0
        viewport[1] = 0
        viewport[2] = viewportWidth
        viewport[3] = viewportHeight
        projectionMatrix.project(transformed.x(), transformed.y(), transformed.z(), viewport, projected)

        val depth = projected.z().toDouble()
        if (depth <= 0.0) {
            return false
        }

        val scale = mc.window.guiScale.toDouble()
        out[0] = projected.x().toDouble() / scale
        out[1] = (viewportHeight.toDouble() - projected.y().toDouble()) / scale
        out[2] = depth
        return true
    }

    private fun cantSee(box: ScreenBox): Boolean {
        return box.minX < 0.0 && box.maxX < 1.0 || box.minY < 0.0 && box.maxY < 1.0
    }

    private fun dot(forward: Vector3fc, delta: Vec3): Double {
        return forward.x().toDouble() * delta.x + forward.y().toDouble() * delta.y + forward.z().toDouble() * delta.z
    }

    data class ScreenBox(val minX: Double, val minY: Double, val maxX: Double, val maxY: Double) {
        val width: Double
            get() = maxX - minX
        val height: Double
            get() = maxY - minY
    }
}
