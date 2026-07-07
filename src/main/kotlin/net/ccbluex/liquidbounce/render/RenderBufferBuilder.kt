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
@file:Suppress("LongParameterList")

package net.ccbluex.liquidbounce.render
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.BufferRenderer
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.engine.type.UV2f
import net.ccbluex.liquidbounce.render.engine.type.Vec3
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKey
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.minecraft.client.renderer.*
import com.mojang.blaze3d.vertex.*
import com.mojang.blaze3d.vertex.VertexFormat.Mode
import net.minecraft.world.phys.AABB



/**
 * A utility class for drawing shapes in batches.
 *
 * Not sync, not send. Not thread-safe at all.
 */
class RenderBufferBuilder<I : VertexInputType>(
    private val drawMode: Mode,
    private val vertexFormat: I,
    private val tesselator: Tesselator
) {
    // Begin drawing lines with position format
    val buffer: BufferBuilder = tesselator.begin(drawMode, vertexFormat.vertexFormat)

    /**
     * Function to draw a solid box using the specified [box].
     *
     * @param box The bounding box of the box.
     */
    fun drawBox(
        env: RenderEnvironment,
        box: AABB,
        useOutlineVertices: Boolean = false,
        color: Color4b? = null,
        verticesToUse: Int = -1
    ) {
        val matrix = env.currentMvpMatrix

        val vertexPositions = if (useOutlineVertices) {
            box.outlineVertexPositions()
        } else {
            box.vertexPositions()
        }

        val check = verticesToUse != -1

        // Draw the vertices of the box
        for (i in vertexPositions.indices) {
            if (check && (verticesToUse and (1 shl i)) != 0) {
                continue
            }

            val (x, y, z) = vertexPositions[i]
            val bb = buffer.addVertex(matrix, x, y, z)

            if (color != null) {
                bb.setColor(color.toARGB())
            }
        }
    }

    fun draw() {
        val built = buffer.build() ?: return

        RenderSystem.setShader(vertexFormat.shaderProgram)

        BufferRenderer.drawWithGlobalProgram(built)
        tesselator.clear()
    }

    fun reset() {
        buffer.build()
    }

    companion object {
        val TESSELATOR_A: Tesselator = Tesselator(0x200000)
        val TESSELATOR_B: Tesselator = Tesselator(0x200000)
    }
}

class BoxRenderer private constructor(private val env: WorldRenderEnvironment) {
    private val faceRenderer = RenderBufferBuilder(
        Mode.QUADS,
        VertexInputType.PosColor,
        RenderBufferBuilder.TESSELATOR_A
    )
    private val outlinesRenderer = RenderBufferBuilder(
        Mode.DEBUG_LINES,
        VertexInputType.PosColor,
        RenderBufferBuilder.TESSELATOR_B
    )

    companion object {
        /**
         * Draws colored boxes. Renders automatically
         */
        fun drawWith(env: WorldRenderEnvironment, fn: BoxRenderer.() -> Unit) {
            val renderer = BoxRenderer(env)

            try {
                fn(renderer)
            } finally {
                renderer.draw()
            }
        }
    }

    fun drawBox(
        box: AABB,
        faceColor: Color4b,
        outlineColor: Color4b? = null,
        vertices: Int = -1,
        outlineVertices: Int = -1
    ) {
        faceRenderer.drawBox(env, box, color = faceColor, verticesToUse = vertices)

        if (outlineColor != null) {
            outlinesRenderer.drawBox(env, box, true, outlineColor, outlineVertices)
        }
    }

    private fun draw() {
        faceRenderer.draw()
        outlinesRenderer.draw()
    }

}

fun AABB.vertexPositions(): Array<Vec3> {
    return arrayOf(
        // down
        Vec3(minX, minY, minZ),
        Vec3(maxX, minY, minZ),
        Vec3(maxX, minY, maxZ),
        Vec3(minX, minY, maxZ),

        // up
        Vec3(minX, maxY, minZ),
        Vec3(minX, maxY, maxZ),
        Vec3(maxX, maxY, maxZ),
        Vec3(maxX, maxY, minZ),

        // north
        Vec3(minX, minY, minZ),
        Vec3(minX, maxY, minZ),
        Vec3(maxX, maxY, minZ),
        Vec3(maxX, minY, minZ),

        // east
        Vec3(maxX, minY, minZ),
        Vec3(maxX, maxY, minZ),
        Vec3(maxX, maxY, maxZ),
        Vec3(maxX, minY, maxZ),

        // south
        Vec3(minX, minY, maxZ),
        Vec3(maxX, minY, maxZ),
        Vec3(maxX, maxY, maxZ),
        Vec3(minX, maxY, maxZ),

        // west
        Vec3(minX, minY, minZ),
        Vec3(minX, minY, maxZ),
        Vec3(minX, maxY, maxZ),
        Vec3(minX, maxY, minZ)
    )
}

fun AABB.outlineVertexPositions(): Array<Vec3> {
    return arrayOf(
        // down north
        Vec3(minX, minY, minZ),
        Vec3(maxX, minY, minZ),

        // down east
        Vec3(maxX, minY, minZ),
        Vec3(maxX, minY, maxZ),

        // down south
        Vec3(maxX, minY, maxZ),
        Vec3(minX, minY, maxZ),

        // down west
        Vec3(minX, minY, maxZ),
        Vec3(minX, minY, minZ),

        // north west
        Vec3(minX, minY, minZ),
        Vec3(minX, maxY, minZ),

        // north east
        Vec3(maxX, minY, minZ),
        Vec3(maxX, maxY, minZ),

        // south east
        Vec3(maxX, minY, maxZ),
        Vec3(maxX, maxY, maxZ),

        // south west
        Vec3(minX, minY, maxZ),
        Vec3(minX, maxY, maxZ),

        // up north
        Vec3(minX, maxY, minZ),
        Vec3(maxX, maxY, minZ),

        // up east
        Vec3(maxX, maxY, minZ),
        Vec3(maxX, maxY, maxZ),

        // up south
        Vec3(maxX, maxY, maxZ),
        Vec3(minX, maxY, maxZ),

        // up west
        Vec3(minX, maxY, maxZ),
        Vec3(minX, maxY, minZ)
    )
}

fun RenderEnvironment.drawSolidBox(consumer: VertexConsumer, box: AABB, color: Color4b) {
    val matrix = currentMvpMatrix

    // Draw the vertices of the box
    box.vertexPositions().forEach { (x, y, z) ->
        consumer.addVertex(matrix, x, y, z).setColor(color.toARGB())
    }
}

fun RenderBufferBuilder<VertexInputType.PosTexColor>.drawQuad(
    env: RenderEnvironment,
    pos1: Vec3,
    uv1: UV2f,
    pos2: Vec3,
    uv2: UV2f,
    color: Color4b
) {
    val matrix = env.currentMvpMatrix

    // Draw the vertices of the box
    with(buffer) {
        vertex(matrix, pos1.x.toFloat(), pos2.y.toFloat(), pos1.z.toFloat())
            .setUv(uv1.u, uv2.v)
            .setColor(color.toARGB())
        vertex(matrix, pos2.x.toFloat(), pos2.y.toFloat(), pos2.z.toFloat())
            .setUv(uv2.u, uv2.v)
            .setColor(color.toARGB())
        vertex(matrix, pos2.x.toFloat(), pos1.y.toFloat(), pos2.z.toFloat())
            .setUv(uv2.u, uv1.v)
            .setColor(color.toARGB())
        vertex(matrix, pos1.x.toFloat(), pos1.y.toFloat(), pos1.z.toFloat())
            .setUv(uv1.u, uv1.v)
            .setColor(color.toARGB())
    }
}

fun RenderBufferBuilder<VertexInputType.Pos>.drawQuad(
    env: RenderEnvironment,
    pos1: Vec3,
    pos2: Vec3,
) {
    val matrix = env.currentMvpMatrix

    // Draw the vertices of the box
    with(buffer) {
        vertex(matrix, pos1.x, pos2.y, pos1.z)
        vertex(matrix, pos2.x, pos2.y, pos2.z)
        vertex(matrix, pos2.x, pos1.y, pos2.z)
        vertex(matrix, pos1.x, pos1.y, pos1.z)
    }
}

fun RenderBufferBuilder<VertexInputType.Pos>.drawQuadOutlines(
    env: RenderEnvironment,
    pos1: Vec3,
    pos2: Vec3,
) {
    val matrix = env.currentMvpMatrix

    // Draw the vertices of the box
    with(buffer) {
        vertex(matrix, pos1.x, pos1.y, pos1.z)
        vertex(matrix, pos1.x, pos2.y, pos1.z)

        vertex(matrix, pos1.x, pos2.y, pos1.z)
        vertex(matrix, pos2.x, pos2.y, pos1.z)

        vertex(matrix, pos2.x, pos1.y, pos1.z)
        vertex(matrix, pos2.x, pos2.y, pos1.z)

        vertex(matrix, pos1.x, pos1.y, pos1.z)
        vertex(matrix, pos2.x, pos1.y, pos1.z)
    }
}

fun RenderBufferBuilder<VertexInputType.PosColor>.drawLine(
    env: RenderEnvironment,
    pos1: Vec3,
    pos2: Vec3,
    color: Color4b
) {
    val matrix = env.currentMvpMatrix

    // Draw the vertices of the box
    with(buffer) {
        vertex(matrix, pos1.x, pos1.y, pos1.z).setColor(color.toARGB())
        vertex(matrix, pos2.x, pos2.y, pos2.z).setColor(color.toARGB())
    }
}

sealed class VertexInputType {
    abstract val vertexFormat: VertexFormat
    abstract val shaderProgram: ShaderProgramKey

    object Pos : VertexInputType() {
        override val vertexFormat: VertexFormat
            get() = DefaultVertexFormat.POSITION
        override val shaderProgram: ShaderProgramKey
            get() = ShaderProgramKeys.POSITION
    }

    object PosColor : VertexInputType() {
        override val vertexFormat: VertexFormat
            get() = DefaultVertexFormat.POSITION_COLOR
        override val shaderProgram: ShaderProgramKey
            get() = ShaderProgramKeys.POSITION_COLOR
    }

    object PosTexColor : VertexInputType() {
        override val vertexFormat: VertexFormat
            get() = DefaultVertexFormat.POSITION_TEX_COLOR
        override val shaderProgram: ShaderProgramKey
            get() = ShaderProgramKeys.POSITION_TEX_COLOR
    }

}
