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
package net.ccbluex.liquidbounce.utils.render
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.utils.aiming.utils.toVec3d
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.math.geometry.Line
import net.ccbluex.liquidbounce.utils.math.minus
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import net.ccbluex.liquidbounce.render.engine.type.Vec3 as RenderVec3

import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f

/**
 * This util should only be called from main thread
 */
object WorldToScreen : EventListener {

    private val projModelViewMatrix = Matrix4f()
    private var cachedCameraPos: Vec3 = Vec3.ZERO

    private val cacheMatrix = Matrix4f()
    private val cacheVec3f = Vector3f()

    /**
     * #Edit By Codex: called from MixinGameRenderer with the real per-frame matrices.
     */
    @JvmStatic
    fun setMatrices(projectionMatrix: Matrix4fc, modelViewMatrix: Matrix4fc, cameraPos: Vec3) {
        projModelViewMatrix.set(projectionMatrix).mul(modelViewMatrix)
        cachedCameraPos = cameraPos
    }

    fun calculateScreenPos(
        pos: Vec3,
        cameraPos: Vec3 = cachedCameraPos,
    ): RenderVec3? {
        val transformedPos = cacheVec3f.set(
            (pos.x - cameraPos.x).toFloat(),
            (pos.y - cameraPos.y).toFloat(),
            (pos.z - cameraPos.z).toFloat()
        ).mulProject(projModelViewMatrix)

        val scaleFactor = mc.window.guiScale
        val guiScaleMul = 0.5f / scaleFactor.toFloat()

        val z = transformedPos.z
        val screenPos = transformedPos.mul(1.0F, -1.0F, 1.0F).add(1.0F, 1.0F, 0.0F)
            .mul(guiScaleMul * mc.framebuffer.viewportWidth, guiScaleMul * mc.framebuffer.viewportHeight, 1.0F)

        return if (z < 1.0F) RenderVec3(screenPos.x.toDouble(), screenPos.y.toDouble(), z.toDouble()) else null
    }

    fun calculateMouseRay(posOnScreen: Vec2, cameraPos: Vec3 = cachedCameraPos): Line {
        val screenVec = cacheVec3f.set(posOnScreen.x, posOnScreen.y, 1.0F)

        val scaleFactor = mc.window.guiScale
        val guiScaleMul = 0.5f / scaleFactor.toFloat()

        val transformedPos = screenVec.mul(
            1.0F / (guiScaleMul * mc.framebuffer.viewportWidth),
            1.0F / (guiScaleMul * mc.framebuffer.viewportHeight),
            1.0F
        ).sub(1.0F, 1.0F, 0.0F).mul(1.0F, -1.0F, 1.0F)

        val relativePos = cacheVec3f.set(transformedPos)
            .mulProject(cacheMatrix.set(projModelViewMatrix).invert())

        return Line(cameraPos, relativePos.toVec3d())
    }

}

private fun Vector3f.set(vec3d: Vec3) = set(vec3d.x.toFloat(), vec3d.y.toFloat(), vec3d.z.toFloat())
