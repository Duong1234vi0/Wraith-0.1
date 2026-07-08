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

import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.minecraft.core.BlockPos
import net.minecraft.resources.Identifier as MinecraftIdentifier
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import org.joml.Matrix4fc
import org.joml.Vector3f
import kotlin.math.max
import kotlin.math.sqrt

object AlphaDlcWorldRenderer {

    private const val UV_SCALE = 4.0
    private const val BLOCK_SHADER_EXPAND = 0.0025
    private const val BOX_EPSILON = 0.001

    private val bloomPassAlpha = intArrayOf(55, 110)
    private val bloomPassExpand = doubleArrayOf(1.0, 0.45)
    private val glowLayerAlpha = intArrayOf(45, 85, 140, 235)
    private val glowLayerWidthMul = doubleArrayOf(3.2, 2.0, 1.1, 0.5)

    private var bloomTexture: MinecraftIdentifier? = null

    fun drawShaderShape(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        shape: VoxelShape,
        origin: BlockPos,
        style: AlphaDlcRenderPipelines.BlockShaderStyle,
        color: Color4b,
        time: Float,
        opacity: Float
    ): Boolean {
        if (shape.isEmpty) {
            return false
        }

        val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)
        val boxes = shape.toAabbs()
        if (boxes.isEmpty()) {
            return false
        }

        for (box in boxes) {
            appendShaderBox(buffer, box.move(origin).inflate(BLOCK_SHADER_EXPAND), cameraPos, color)
        }

        val mesh = buffer.build() ?: return false
        return AlphaDlcRenderPipelines.draw(
            mesh,
            AlphaDlcRenderPipelines.blockPipeline(style),
            modelViewMatrix,
            depthAttachment = true,
            blockConfig = AlphaDlcRenderPipelines.BlockConfig(time, opacity)
        )
    }

    fun drawShapeAlternative(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        origin: BlockPos,
        shape: VoxelShape,
        color: Color4b,
        lineWidth: Float,
        fill: Boolean,
        lines: Boolean,
        depth: Boolean
    ) {
        val blockBox = AABB(
            origin.x.toDouble(), origin.y.toDouble(), origin.z.toDouble(),
            origin.x + 1.0, origin.y + 1.0, origin.z + 1.0
        )
        if (!AlphaProjection.canSee(blockBox)) {
            return
        }

        if (fill) {
            val boxes = shape.toAabbs()
            if (boxes.isNotEmpty()) {
                val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
                val fillColor = color.with(a = (color.a * 0.1f).toInt().coerceIn(0, 255))
                for (box in boxes) {
                    appendSolidBox(buffer, box.move(origin).inflate(BOX_EPSILON), cameraPos, fillColor.toARGB())
                }
                val mesh = buffer.build()
                if (mesh != null) {
                    AlphaDlcRenderPipelines.draw(
                        mesh,
                        if (depth) AlphaDlcRenderPipelines.fillTranslucentDepth
                        else AlphaDlcRenderPipelines.fillTranslucentNoDepth,
                        modelViewMatrix,
                        depthAttachment = depth
                    )
                }
            }
        }

        if (lines) {
            drawShapeLines(modelViewMatrix, cameraPos, origin, shape, color.toARGB(), lineWidth, depth)
        }
    }

    fun drawWorldGlow(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        boxes: List<AABB>,
        colors: List<Int>,
        thickness: Float
    ) {
        if (boxes.isEmpty()) {
            return
        }

        drawWorldBloom(modelViewMatrix, cameraPos, boxes, colors, thickness)
        drawBoxFillGradient(modelViewMatrix, cameraPos, boxes, colors)

        for (layer in glowLayerAlpha.indices) {
            val width = if (layer == glowLayerAlpha.lastIndex) {
                max(1.0, thickness * glowLayerWidthMul[layer]).toFloat()
            } else {
                (thickness * glowLayerWidthMul[layer]).toFloat()
            }
            drawGlowLines(modelViewMatrix, cameraPos, boxes, colors, glowLayerAlpha[layer], width)
        }
    }

    private fun drawWorldBloom(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        boxes: List<AABB>,
        colors: List<Int>,
        thickness: Float
    ) {
        val texture = bloomTexture() ?: return
        for (pass in bloomPassAlpha.indices) {
            val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)
            for (index in boxes.indices) {
                appendBloomQuads(
                    buffer,
                    boxes[index],
                    cameraPos,
                    colors[index],
                    thickness,
                    bloomPassAlpha[pass],
                    bloomPassExpand[pass]
                )
            }
            val mesh = buffer.build() ?: continue
            AlphaDlcRenderPipelines.draw(
                mesh,
                AlphaDlcRenderPipelines.texturedAdditiveNoDepth,
                modelViewMatrix,
                depthAttachment = false,
                texture = texture
            )
        }
    }

    private fun drawBoxFillGradient(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        boxes: List<AABB>,
        colors: List<Int>
    ) {
        val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
        for (index in boxes.indices) {
            val rgb = colors[index] and 0x00FFFFFF
            appendFillGradientQuads(buffer, boxes[index], cameraPos, rgb or (0x46 shl 24), rgb)
        }
        val mesh = buffer.build() ?: return
        AlphaDlcRenderPipelines.draw(
            mesh,
            AlphaDlcRenderPipelines.fillTranslucentNoDepth,
            modelViewMatrix,
            depthAttachment = false
        )
    }

    private fun drawGlowLines(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        boxes: List<AABB>,
        colors: List<Int>,
        alpha: Int,
        width: Float
    ) {
        val buffer = Tesselator.getInstance().begin(
            VertexFormat.Mode.LINES,
            DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH
        )
        for (index in boxes.indices) {
            val color = (colors[index] and 0x00FFFFFF) or (alpha shl 24)
            appendGlowBoxLines(buffer, boxes[index], cameraPos, color, width)
        }
        val mesh = buffer.build() ?: return
        AlphaDlcRenderPipelines.draw(
            mesh,
            AlphaDlcRenderPipelines.lineAdditiveNoDepth,
            modelViewMatrix,
            depthAttachment = false
        )
    }

    private fun drawShapeLines(
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        origin: BlockPos,
        shape: VoxelShape,
        color: Int,
        width: Float,
        depth: Boolean
    ) {
        val buffer = Tesselator.getInstance().begin(
            VertexFormat.Mode.LINES,
            DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH
        )
        shape.forAllEdges { x1, y1, z1, x2, y2, z2 ->
            appendLine(
                buffer,
                x1 + origin.x - cameraPos.x,
                y1 + origin.y - cameraPos.y,
                z1 + origin.z - cameraPos.z,
                x2 + origin.x - cameraPos.x,
                y2 + origin.y - cameraPos.y,
                z2 + origin.z - cameraPos.z,
                color,
                color,
                width
            )
        }
        val mesh = buffer.build() ?: return
        AlphaDlcRenderPipelines.draw(
            mesh,
            if (depth) AlphaDlcRenderPipelines.lineTranslucentDepth else AlphaDlcRenderPipelines.lineAdditiveNoDepth,
            modelViewMatrix,
            depthAttachment = depth
        )
    }

    private fun appendShaderBox(buffer: BufferBuilder, box: AABB, cameraPos: Vec3, color: Color4b) {
        // bottom
        shaderVertex(buffer, box.minX, box.minY, box.minZ, 0, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.minY, box.minZ, 0, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.minY, box.maxZ, 0, cameraPos, color)
        shaderVertex(buffer, box.minX, box.minY, box.maxZ, 0, cameraPos, color)

        // top
        shaderVertex(buffer, box.minX, box.maxY, box.minZ, 0, cameraPos, color)
        shaderVertex(buffer, box.minX, box.maxY, box.maxZ, 0, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.maxZ, 0, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.minZ, 0, cameraPos, color)

        // north
        shaderVertex(buffer, box.minX, box.minY, box.minZ, 1, cameraPos, color)
        shaderVertex(buffer, box.minX, box.maxY, box.minZ, 1, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.minZ, 1, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.minY, box.minZ, 1, cameraPos, color)

        // south
        shaderVertex(buffer, box.maxX, box.minY, box.maxZ, 1, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.maxZ, 1, cameraPos, color)
        shaderVertex(buffer, box.minX, box.maxY, box.maxZ, 1, cameraPos, color)
        shaderVertex(buffer, box.minX, box.minY, box.maxZ, 1, cameraPos, color)

        // west
        shaderVertex(buffer, box.minX, box.minY, box.maxZ, 2, cameraPos, color)
        shaderVertex(buffer, box.minX, box.maxY, box.maxZ, 2, cameraPos, color)
        shaderVertex(buffer, box.minX, box.maxY, box.minZ, 2, cameraPos, color)
        shaderVertex(buffer, box.minX, box.minY, box.minZ, 2, cameraPos, color)

        // east
        shaderVertex(buffer, box.maxX, box.minY, box.minZ, 2, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.minZ, 2, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.maxY, box.maxZ, 2, cameraPos, color)
        shaderVertex(buffer, box.maxX, box.minY, box.maxZ, 2, cameraPos, color)
    }

    private fun shaderVertex(
        buffer: BufferBuilder,
        x: Double,
        y: Double,
        z: Double,
        plane: Int,
        cameraPos: Vec3,
        color: Color4b
    ) {
        val u = if (plane == 2) z * UV_SCALE else x * UV_SCALE
        val v = if (plane == 0) z * UV_SCALE else y * UV_SCALE
        buffer.addVertex((x - cameraPos.x).toFloat(), (y - cameraPos.y).toFloat(), (z - cameraPos.z).toFloat())
            .setUv(u.toFloat(), v.toFloat())
            .setColor(color.toARGB())
    }

    private fun appendSolidBox(buffer: BufferBuilder, box: AABB, cameraPos: Vec3, color: Int) {
        vertex(buffer, box.minX, box.minY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.minY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.minY, box.maxZ, cameraPos, color)
        vertex(buffer, box.minX, box.minY, box.maxZ, cameraPos, color)

        vertex(buffer, box.minX, box.maxY, box.minZ, cameraPos, color)
        vertex(buffer, box.minX, box.maxY, box.maxZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.maxZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.minZ, cameraPos, color)

        vertex(buffer, box.minX, box.minY, box.minZ, cameraPos, color)
        vertex(buffer, box.minX, box.maxY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.minY, box.minZ, cameraPos, color)

        vertex(buffer, box.maxX, box.minY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.minZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.maxZ, cameraPos, color)
        vertex(buffer, box.maxX, box.minY, box.maxZ, cameraPos, color)

        vertex(buffer, box.minX, box.minY, box.maxZ, cameraPos, color)
        vertex(buffer, box.maxX, box.minY, box.maxZ, cameraPos, color)
        vertex(buffer, box.maxX, box.maxY, box.maxZ, cameraPos, color)
        vertex(buffer, box.minX, box.maxY, box.maxZ, cameraPos, color)

        vertex(buffer, box.minX, box.minY, box.minZ, cameraPos, color)
        vertex(buffer, box.minX, box.minY, box.maxZ, cameraPos, color)
        vertex(buffer, box.minX, box.maxY, box.maxZ, cameraPos, color)
        vertex(buffer, box.minX, box.maxY, box.minZ, cameraPos, color)
    }

    private fun appendBloomQuads(
        buffer: BufferBuilder,
        box: AABB,
        cameraPos: Vec3,
        color: Int,
        thickness: Float,
        alpha: Int,
        expand: Double
    ) {
        val b = box.inflate(BOX_EPSILON)
        val mxz = (b.xsize * 0.6) + thickness * 0.05
        val my = (b.ysize * 0.12) + thickness * 0.05
        val c = (color and 0x00FFFFFF) or (alpha shl 24)
        val ex = mxz * expand
        val ey = my * expand
        val ax1 = b.minX - ex
        val ax2 = b.maxX + ex
        val ay1 = b.minY - ey
        val ay2 = b.maxY + ey
        val az1 = b.minZ - ex
        val az2 = b.maxZ + ex

        texVertex(buffer, ax1, ay1, b.minZ, 0.0f, 1.0f, cameraPos, c)
        texVertex(buffer, ax2, ay1, b.minZ, 1.0f, 1.0f, cameraPos, c)
        texVertex(buffer, ax2, ay2, b.minZ, 1.0f, 0.0f, cameraPos, c)
        texVertex(buffer, ax1, ay2, b.minZ, 0.0f, 0.0f, cameraPos, c)

        texVertex(buffer, ax1, ay1, b.maxZ, 0.0f, 1.0f, cameraPos, c)
        texVertex(buffer, ax2, ay1, b.maxZ, 1.0f, 1.0f, cameraPos, c)
        texVertex(buffer, ax2, ay2, b.maxZ, 1.0f, 0.0f, cameraPos, c)
        texVertex(buffer, ax1, ay2, b.maxZ, 0.0f, 0.0f, cameraPos, c)

        texVertex(buffer, b.minX, ay1, az1, 0.0f, 1.0f, cameraPos, c)
        texVertex(buffer, b.minX, ay1, az2, 1.0f, 1.0f, cameraPos, c)
        texVertex(buffer, b.minX, ay2, az2, 1.0f, 0.0f, cameraPos, c)
        texVertex(buffer, b.minX, ay2, az1, 0.0f, 0.0f, cameraPos, c)

        texVertex(buffer, b.maxX, ay1, az1, 0.0f, 1.0f, cameraPos, c)
        texVertex(buffer, b.maxX, ay1, az2, 1.0f, 1.0f, cameraPos, c)
        texVertex(buffer, b.maxX, ay2, az2, 1.0f, 0.0f, cameraPos, c)
        texVertex(buffer, b.maxX, ay2, az1, 0.0f, 0.0f, cameraPos, c)
    }

    private fun appendFillGradientQuads(buffer: BufferBuilder, box: AABB, cameraPos: Vec3, bottom: Int, top: Int) {
        val b = box.inflate(BOX_EPSILON)

        vertex(buffer, b.minX, b.minY, b.minZ, cameraPos, bottom)
        vertex(buffer, b.maxX, b.minY, b.minZ, cameraPos, bottom)
        vertex(buffer, b.maxX, b.minY, b.maxZ, cameraPos, bottom)
        vertex(buffer, b.minX, b.minY, b.maxZ, cameraPos, bottom)

        vertex(buffer, b.minX, b.maxY, b.minZ, cameraPos, top)
        vertex(buffer, b.minX, b.maxY, b.maxZ, cameraPos, top)
        vertex(buffer, b.maxX, b.maxY, b.maxZ, cameraPos, top)
        vertex(buffer, b.maxX, b.maxY, b.minZ, cameraPos, top)

        vertex(buffer, b.minX, b.minY, b.minZ, cameraPos, bottom)
        vertex(buffer, b.minX, b.maxY, b.minZ, cameraPos, top)
        vertex(buffer, b.maxX, b.maxY, b.minZ, cameraPos, top)
        vertex(buffer, b.maxX, b.minY, b.minZ, cameraPos, bottom)

        vertex(buffer, b.minX, b.minY, b.maxZ, cameraPos, bottom)
        vertex(buffer, b.maxX, b.minY, b.maxZ, cameraPos, bottom)
        vertex(buffer, b.maxX, b.maxY, b.maxZ, cameraPos, top)
        vertex(buffer, b.minX, b.maxY, b.maxZ, cameraPos, top)

        vertex(buffer, b.minX, b.minY, b.minZ, cameraPos, bottom)
        vertex(buffer, b.minX, b.minY, b.maxZ, cameraPos, bottom)
        vertex(buffer, b.minX, b.maxY, b.maxZ, cameraPos, top)
        vertex(buffer, b.minX, b.maxY, b.minZ, cameraPos, top)

        vertex(buffer, b.maxX, b.minY, b.minZ, cameraPos, bottom)
        vertex(buffer, b.maxX, b.maxY, b.minZ, cameraPos, top)
        vertex(buffer, b.maxX, b.maxY, b.maxZ, cameraPos, top)
        vertex(buffer, b.maxX, b.minY, b.maxZ, cameraPos, bottom)
    }

    private fun appendGlowBoxLines(buffer: BufferBuilder, box: AABB, cameraPos: Vec3, color: Int, width: Float) {
        val b = box.inflate(BOX_EPSILON)
        val topColor = color and 0x00FFFFFF

        appendEspGlowLine(buffer, b.minX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z,
            b.maxX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z, color, color, width)
        appendEspGlowLine(buffer, b.maxX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z,
            b.maxX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z, color, color, width)
        appendEspGlowLine(buffer, b.maxX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z,
            b.minX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z, color, color, width)
        appendEspGlowLine(buffer, b.minX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z,
            b.minX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z, color, color, width)

        appendEspGlowLine(buffer, b.minX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z,
            b.minX - cameraPos.x, b.maxY - cameraPos.y, b.minZ - cameraPos.z, color, topColor, width)
        appendEspGlowLine(buffer, b.maxX - cameraPos.x, b.minY - cameraPos.y, b.minZ - cameraPos.z,
            b.maxX - cameraPos.x, b.maxY - cameraPos.y, b.minZ - cameraPos.z, color, topColor, width)
        appendEspGlowLine(buffer, b.maxX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z,
            b.maxX - cameraPos.x, b.maxY - cameraPos.y, b.maxZ - cameraPos.z, color, topColor, width)
        appendEspGlowLine(buffer, b.minX - cameraPos.x, b.minY - cameraPos.y, b.maxZ - cameraPos.z,
            b.minX - cameraPos.x, b.maxY - cameraPos.y, b.maxZ - cameraPos.z, color, topColor, width)
    }

    private fun appendEspGlowLine(
        buffer: BufferBuilder,
        x1: Double,
        y1: Double,
        z1: Double,
        x2: Double,
        y2: Double,
        z2: Double,
        color1: Int,
        color2: Int,
        width: Float
    ) {
        val normal = sourceLineNormal(x1, y1, z1, x2, y2, z2)
        buffer.addVertex(x1.toFloat(), y1.toFloat(), z1.toFloat())
            .setColor(color1)
            .setNormal(normal.x, normal.y, normal.z)
            .setLineWidth(width)
        buffer.addVertex(x2.toFloat(), y2.toFloat(), z2.toFloat())
            .setColor(color2)
            .setNormal(normal.x, normal.y, normal.z)
            .setLineWidth(width)
    }

    private fun appendLine(
        buffer: BufferBuilder,
        x1: Double,
        y1: Double,
        z1: Double,
        x2: Double,
        y2: Double,
        z2: Double,
        color1: Int,
        color2: Int,
        width: Float
    ) {
        val normal = lineNormal(x1, y1, z1, x2, y2, z2)
        buffer.addVertex(x1.toFloat(), y1.toFloat(), z1.toFloat())
            .setColor(color1)
            .setNormal(normal.x, normal.y, normal.z)
            .setLineWidth(width)
        buffer.addVertex(x2.toFloat(), y2.toFloat(), z2.toFloat())
            .setColor(color2)
            .setNormal(normal.x, normal.y, normal.z)
            .setLineWidth(width)
    }

    private fun lineNormal(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double): Vector3f {
        val dx = (x2 - x1).toFloat()
        val dy = (y2 - y1).toFloat()
        val dz = (z2 - z1).toFloat()
        val len = sqrt(dx * dx + dy * dy + dz * dz)
        return if (len > 0.0f) Vector3f(dx / len, dy / len, dz / len) else Vector3f(0.0f, 1.0f, 0.0f)
    }

    private fun sourceLineNormal(
        x1: Double,
        y1: Double,
        z1: Double,
        x2: Double,
        y2: Double,
        z2: Double
    ): Vector3f {
        val dx = (x1 - x2).toFloat()
        val dy = (y1 - y2).toFloat()
        val dz = (z1 - z2).toFloat()
        val len = sqrt(dx * dx + dy * dy + dz * dz)
        return if (len > 0.0f) Vector3f(dx / len, dy / len, dz / len) else Vector3f(0.0f, 1.0f, 0.0f)
    }

    private fun texVertex(
        buffer: BufferBuilder,
        x: Double,
        y: Double,
        z: Double,
        u: Float,
        v: Float,
        cameraPos: Vec3,
        color: Int
    ) {
        buffer.addVertex((x - cameraPos.x).toFloat(), (y - cameraPos.y).toFloat(), (z - cameraPos.z).toFloat())
            .setUv(u, v)
            .setColor(color)
    }

    private fun vertex(buffer: BufferBuilder, x: Double, y: Double, z: Double, cameraPos: Vec3, color: Int) {
        buffer.addVertex((x - cameraPos.x).toFloat(), (y - cameraPos.y).toFloat(), (z - cameraPos.z).toFloat())
            .setColor(color)
    }

    private fun bloomTexture(): MinecraftIdentifier? {
        bloomTexture?.let { return it }
        val id = MinecraftIdentifier.fromNamespaceAndPath("liquidbounce", "alphadlc-bloom")
        return runCatching {
            id.apply { registerDynamicImageFromResources("alphadlc/textures/bloom.png") }
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to load AlphaDLC bloom texture", throwable)
        }.getOrNull()?.also {
            bloomTexture = it
        }
    }
}
