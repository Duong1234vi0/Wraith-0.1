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
package net.ccbluex.liquidbounce.features.module.modules.render.hiteffect

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.GpuBufferSlice
import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.CompareOp
import com.mojang.blaze3d.platform.DestFactor
import com.mojang.blaze3d.platform.SourceFactor
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.MeshData
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.core.BlockPos
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer
import java.util.EnumMap
import java.util.Optional
import java.util.OptionalDouble
import java.util.OptionalInt
import java.util.WeakHashMap
import java.util.function.Supplier
import kotlin.math.sqrt

/**
 * HitEffect-only world renderer modeled after Xyeta's RenderUtil3D queue/flush path.
 *
 * The cache stores local VoxelShape geometry only. World positions, colors, camera offsets,
 * matrices, and uploaded GPU buffers remain frame-local.
 */
object HitEffectBatchRenderer {

    private const val BOX_EPSILON = 0.001
    private const val BUFFER_SIZE = 0x200000
    private const val SHAPE_CACHE_LIMIT = 256

    private val fillTesselator = Tesselator(BUFFER_SIZE)
    private val lineTesselator = Tesselator(BUFFER_SIZE)

    private var fillBuilder: BufferBuilder? = null
    private var lineBuilder: BufferBuilder? = null
    private var cameraPos: Vec3 = Vec3.ZERO
    private var hasFillVertices = false
    private var hasLineVertices = false

    private val weakShapeCache = WeakHashMap<VoxelShape, CachedShape>()
    private val signatureShapeCache = object : LinkedHashMap<ShapeKey, CachedShape>(
        SHAPE_CACHE_LIMIT,
        0.75f,
        true
    ) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<ShapeKey, CachedShape>?): Boolean =
            size > SHAPE_CACHE_LIMIT
    }

    private val hitEffectBlend = BlendFunction(
        SourceFactor.SRC_ALPHA,
        DestFactor.ONE_MINUS_CONSTANT_ALPHA
    )

    private val fillPipeline: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/hit_effect_fill_depth"))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(hitEffectBlend))
            .withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, true))
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS)
            .build()
    }

    private val linePipeline: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/hit_effect_line_depth"))
            .withColorTargetState(ColorTargetState(hitEffectBlend))
            .withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
            .withCull(false)
            .build()
    }

    private val vboByFormat = HashMap<VertexFormat, StreamingBuffer>()
    private val iboByType = EnumMap<VertexFormat.IndexType, StreamingBuffer>(VertexFormat.IndexType::class.java)

    private val shaderColor = Vector4f(1.0f, 1.0f, 1.0f, 1.0f)
    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()

    fun beginFrame(cameraPos: Vec3) {
        clearFrame()
        this.cameraPos = cameraPos
    }

    fun appendShape(origin: BlockPos, shape: VoxelShape, color: Color4b, lineWidth: Float): Boolean {
        if (shape.isEmpty) {
            return false
        }

        val blockBox = AABB(
            origin.x.toDouble(),
            origin.y.toDouble(),
            origin.z.toDouble(),
            origin.x + 1.0,
            origin.y + 1.0,
            origin.z + 1.0
        )
        if (!AlphaProjection.canSee(blockBox)) {
            return false
        }

        val geometry = geometryFor(shape)
        if (geometry.boxes.isEmpty() && geometry.edges.isEmpty()) {
            return false
        }

        if (geometry.boxes.isNotEmpty()) {
            val fillColor = color.with(a = (color.a * 0.1f).toInt().coerceIn(0, 255)).toARGB()
            val buffer = fillBuffer()
            for (box in geometry.boxes) {
                appendSolidBox(buffer, box, origin, fillColor)
            }
        }

        if (geometry.edges.isNotEmpty()) {
            val lineColor = color.toARGB()
            val buffer = lineBuffer()
            for (edge in geometry.edges) {
                appendLine(buffer, edge, origin, lineColor, lineWidth)
            }
        }

        return true
    }

    fun flush(modelViewMatrix: Matrix4fc) {
        if (hasFillVertices) {
            val mesh = fillBuilder?.build()
            fillBuilder = null
            if (mesh != null) {
                drawMesh(mesh, fillPipeline, modelViewMatrix, depthAttachment = true)
            }
            fillTesselator.clear()
        }

        if (hasLineVertices) {
            val mesh = lineBuilder?.build()
            lineBuilder = null
            if (mesh != null) {
                drawMesh(mesh, linePipeline, modelViewMatrix, depthAttachment = true)
            }
            lineTesselator.clear()
        }

        hasFillVertices = false
        hasLineVertices = false
    }

    fun clearFrame() {
        fillBuilder = null
        lineBuilder = null
        hasFillVertices = false
        hasLineVertices = false
        fillTesselator.clear()
        lineTesselator.clear()
    }

    private fun fillBuffer(): BufferBuilder {
        hasFillVertices = true
        return fillBuilder ?: fillTesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
            .also { fillBuilder = it }
    }

    private fun lineBuffer(): BufferBuilder {
        hasLineVertices = true
        return lineBuilder ?: lineTesselator.begin(
            VertexFormat.Mode.LINES,
            DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH
        ).also { lineBuilder = it }
    }

    private fun geometryFor(shape: VoxelShape): CachedShape {
        weakShapeCache[shape]?.let { return it }

        val boxes = shape.toAabbs().mapTo(ArrayList()) { box ->
            LocalBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ)
        }
        val edges = ArrayList<LocalEdge>()
        shape.forAllEdges { minX, minY, minZ, maxX, maxY, maxZ ->
            edges += LocalEdge(minX, minY, minZ, maxX, maxY, maxZ)
        }

        val key = ShapeKey(boxes, edges)
        val cached = signatureShapeCache.getOrPut(key) {
            CachedShape(boxes, edges)
        }
        weakShapeCache[shape] = cached
        return cached
    }

    private fun appendSolidBox(buffer: BufferBuilder, box: LocalBox, origin: BlockPos, color: Int) {
        val minX = origin.x + box.minX - BOX_EPSILON
        val minY = origin.y + box.minY - BOX_EPSILON
        val minZ = origin.z + box.minZ - BOX_EPSILON
        val maxX = origin.x + box.maxX + BOX_EPSILON
        val maxY = origin.y + box.maxY + BOX_EPSILON
        val maxZ = origin.z + box.maxZ + BOX_EPSILON

        vertex(buffer, minX, minY, minZ, color)
        vertex(buffer, maxX, minY, minZ, color)
        vertex(buffer, maxX, minY, maxZ, color)
        vertex(buffer, minX, minY, maxZ, color)

        vertex(buffer, minX, minY, minZ, color)
        vertex(buffer, minX, maxY, minZ, color)
        vertex(buffer, maxX, maxY, minZ, color)
        vertex(buffer, maxX, minY, minZ, color)

        vertex(buffer, maxX, minY, minZ, color)
        vertex(buffer, maxX, maxY, minZ, color)
        vertex(buffer, maxX, maxY, maxZ, color)
        vertex(buffer, maxX, minY, maxZ, color)

        vertex(buffer, minX, minY, maxZ, color)
        vertex(buffer, maxX, minY, maxZ, color)
        vertex(buffer, maxX, maxY, maxZ, color)
        vertex(buffer, minX, maxY, maxZ, color)

        vertex(buffer, minX, minY, minZ, color)
        vertex(buffer, minX, minY, maxZ, color)
        vertex(buffer, minX, maxY, maxZ, color)
        vertex(buffer, minX, maxY, minZ, color)

        vertex(buffer, minX, maxY, minZ, color)
        vertex(buffer, minX, maxY, maxZ, color)
        vertex(buffer, maxX, maxY, maxZ, color)
        vertex(buffer, maxX, maxY, minZ, color)
    }

    private fun vertex(buffer: BufferBuilder, x: Double, y: Double, z: Double, color: Int) {
        buffer.addVertex(
            (x - cameraPos.x).toFloat(),
            (y - cameraPos.y).toFloat(),
            (z - cameraPos.z).toFloat()
        ).setColor(color)
    }

    private fun appendLine(buffer: BufferBuilder, edge: LocalEdge, origin: BlockPos, color: Int, width: Float) {
        val x1 = edge.x1 + origin.x - cameraPos.x
        val y1 = edge.y1 + origin.y - cameraPos.y
        val z1 = edge.z1 + origin.z - cameraPos.z
        val x2 = edge.x2 + origin.x - cameraPos.x
        val y2 = edge.y2 + origin.y - cameraPos.y
        val z2 = edge.z2 + origin.z - cameraPos.z

        val dx = (x1 - x2).toFloat()
        val dy = (y1 - y2).toFloat()
        val dz = (z1 - z2).toFloat()
        val length = sqrt(dx * dx + dy * dy + dz * dz)
        val normalX: Float
        val normalY: Float
        val normalZ: Float
        if (length > 0.0f) {
            normalX = dx / length
            normalY = dy / length
            normalZ = dz / length
        } else {
            normalX = 0.0f
            normalY = 1.0f
            normalZ = 0.0f
        }

        buffer.addVertex(x1.toFloat(), y1.toFloat(), z1.toFloat())
            .setColor(color)
            .setNormal(normalX, normalY, normalZ)
            .setLineWidth(width)
        buffer.addVertex(x2.toFloat(), y2.toFloat(), z2.toFloat())
            .setColor(color)
            .setNormal(normalX, normalY, normalZ)
            .setLineWidth(width)
    }

    private fun drawMesh(
        meshData: MeshData,
        pipeline: RenderPipeline,
        modelViewMatrix: Matrix4fc,
        depthAttachment: Boolean
    ) {
        try {
            val state = meshData.drawState()
            val format = state.format()
            val mode = state.mode()
            val vertexSlice = vboByFormat.getOrPut(format) {
                StreamingBuffer("LiquidBounce HitEffect VBO for $format", GpuBuffer.USAGE_VERTEX)
            }.upload(meshData.vertexBuffer())

            val rawIndices = meshData.indexBuffer()
            val indexCount = state.indexCount()
            val indexType: VertexFormat.IndexType
            val indexSlice: GpuBufferSlice

            if (rawIndices == null) {
                val sequential = RenderSystem.getSequentialBuffer(mode)
                indexType = sequential.type()
                indexSlice = sequential.getBuffer(indexCount)
                    .slice(0L, indexCount.toLong() * indexType.bytes)
            } else {
                indexType = state.indexType()
                indexSlice = iboByType.getOrPut(indexType) {
                    StreamingBuffer("LiquidBounce HitEffect IBO for $indexType", GpuBuffer.USAGE_INDEX)
                }.upload(rawIndices)
            }

            val target = mc.mainRenderTarget
            val colorAttachment = RenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return
            val targetDepth = if (depthAttachment) {
                RenderSystem.outputDepthTextureOverride ?: target.depthTextureView.takeIf { target.useDepth }
            } else {
                null
            }

            val dynamicTransform = RenderSystem.getDynamicUniforms().writeTransform(
                Matrix4f(modelViewMatrix),
                shaderColor,
                modelOffset,
                textureMatrix
            )

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Supplier { "LiquidBounce HitEffect batch" },
                colorAttachment,
                OptionalInt.empty(),
                targetDepth,
                OptionalDouble.empty()
            ).use { pass ->
                pass.setPipeline(pipeline)
                RenderSystem.bindDefaultUniforms(pass)
                pass.setUniform("DynamicTransforms", dynamicTransform)
                pass.setVertexBuffer(0, vertexSlice.buffer())
                pass.setIndexBuffer(indexSlice.buffer(), indexType)
                pass.drawIndexed(
                    (vertexSlice.offset() / format.vertexSize).toInt(),
                    (indexSlice.offset() / indexType.bytes).toInt(),
                    indexCount,
                    1
                )
            }
        } finally {
            meshData.close()
        }
    }

    private data class CachedShape(val boxes: List<LocalBox>, val edges: List<LocalEdge>)

    private data class LocalBox(
        val minX: Double,
        val minY: Double,
        val minZ: Double,
        val maxX: Double,
        val maxY: Double,
        val maxZ: Double
    )

    private data class LocalEdge(
        val x1: Double,
        val y1: Double,
        val z1: Double,
        val x2: Double,
        val y2: Double,
        val z2: Double
    )

    private class ShapeKey(boxes: List<LocalBox>, edges: List<LocalEdge>) {
        private val values: LongArray = LongArray(2 + boxes.size * 6 + edges.size * 6)
        private val hash: Int

        init {
            var index = 0
            values[index++] = boxes.size.toLong()
            for (box in boxes) {
                values[index++] = bits(box.minX)
                values[index++] = bits(box.minY)
                values[index++] = bits(box.minZ)
                values[index++] = bits(box.maxX)
                values[index++] = bits(box.maxY)
                values[index++] = bits(box.maxZ)
            }
            values[index++] = edges.size.toLong()
            for (edge in edges) {
                values[index++] = bits(edge.x1)
                values[index++] = bits(edge.y1)
                values[index++] = bits(edge.z1)
                values[index++] = bits(edge.x2)
                values[index++] = bits(edge.y2)
                values[index++] = bits(edge.z2)
            }
            hash = values.contentHashCode()
        }

        override fun equals(other: Any?): Boolean =
            other is ShapeKey && values.contentEquals(other.values)

        override fun hashCode(): Int = hash

        private fun bits(value: Double): Long =
            java.lang.Double.doubleToLongBits(if (value == 0.0) 0.0 else value)
    }
}

private class StreamingBuffer(
    private val label: String,
    private val usage: @GpuBuffer.Usage Int
) {
    private var ring: MappableRingBuffer? = null
    private var offset = 0

    fun upload(data: ByteBuffer): GpuBufferSlice {
        val byteCount = data.remaining()
        ensureCapacity(byteCount)

        var ring = checkNotNull(ring)
        if (offset + byteCount > ring.size()) {
            ring.rotate()
            offset = 0
            ring = checkNotNull(this.ring)
        }

        val slice = ring.currentBuffer().slice(offset.toLong(), byteCount.toLong())
        RenderSystem.getDevice().createCommandEncoder().mapBuffer(slice, false, true).use { mapped ->
            MemoryUtil.memCopy(data, mapped.data())
        }

        offset = align4(offset + byteCount)
        return slice
    }

    private fun ensureCapacity(byteCount: Int) {
        val current = ring
        if (current != null && current.size() >= byteCount) {
            return
        }

        current?.close()
        ring = MappableRingBuffer(
            Suppliers.ofInstance(label),
            usage or GpuBuffer.USAGE_MAP_WRITE,
            align4(maxOf(byteCount, 8192))
        )
        offset = 0
    }

    private fun align4(value: Int) = (value + 3) and 3.inv()
}
