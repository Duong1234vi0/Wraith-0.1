/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.GpuBufferSlice
import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.MeshData
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer
import java.util.Optional
import java.util.OptionalDouble
import java.util.OptionalInt
import java.util.function.Supplier
import kotlin.math.sqrt

/** Three Wyvern source batches uploaded before one Cubes-local RenderPass. */
internal object CubesBatchRenderer {
    internal const val LOGICAL_BATCH_COUNT = 3
    internal const val MAX_RENDER_PASSES = 1

    private const val BUFFER_SIZE = 0x200000
    private const val EDGE_WIDTH = 1f
    private const val MIN_LINE_LENGTH_SQ = 1.0E-12f

    private val glowTesselator = Tesselator(BUFFER_SIZE)
    private val faceTesselator = Tesselator(BUFFER_SIZE)
    private val edgeTesselator = Tesselator(BUFFER_SIZE)
    private var glowBuilder: BufferBuilder? = null
    private var faceBuilder: BufferBuilder? = null
    private var edgeBuilder: BufferBuilder? = null
    private var cubeFaces = true
    private var hasGlow = false
    private var hasFaces = false
    private var hasEdges = false

    private val glowPipeline by lazy(LazyThreadSafetyMode.NONE) {
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id("cubes_glow"))
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
            .withDepthStencilState(Optional.empty())
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()
    }
    private val cubeFacePipeline by lazy(LazyThreadSafetyMode.NONE) {
        facePipeline("cubes_faces", VertexFormat.Mode.QUADS)
    }
    private val triangleFacePipeline by lazy(LazyThreadSafetyMode.NONE) {
        facePipeline("cubes_triangle_faces", VertexFormat.Mode.TRIANGLES)
    }
    private val edgePipeline by lazy(LazyThreadSafetyMode.NONE) {
        RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
            .withLocation(id("cubes_edges"))
            .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
            .withDepthStencilState(Optional.empty())
            .withCull(false)
            .build()
    }

    private val vertexBuffers = HashMap<VertexFormat, StreamingBuffer>()
    private val shaderColor = Vector4f(1f, 1f, 1f, 1f)
    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()
    private val lineStart = Vector3f()
    private val lineEnd = Vector3f()

    fun beginFrame(cubeFaces: Boolean) {
        clearFrame()
        this.cubeFaces = cubeFaces
    }

    fun appendGlow(x: Float, y: Float, z: Float, u: Float, v: Float, color: Int) {
        val buffer = glowBuilder ?: glowTesselator.begin(
            VertexFormat.Mode.QUADS,
            DefaultVertexFormat.POSITION_TEX_COLOR,
        ).also { glowBuilder = it }
        buffer.addVertex(x, y, z).setUv(u, v).setColor(color)
        hasGlow = true
    }

    fun appendFace(matrix: Matrix4fc, x: Float, y: Float, z: Float, color: Int) {
        val buffer = faceBuilder ?: faceTesselator.begin(
            if (cubeFaces) VertexFormat.Mode.QUADS else VertexFormat.Mode.TRIANGLES,
            DefaultVertexFormat.POSITION_COLOR,
        ).also { faceBuilder = it }
        buffer.addVertex(matrix, x, y, z).setColor(color)
        hasFaces = true
    }

    @Suppress("LongParameterList")
    fun appendEdge(
        matrix: Matrix4fc,
        x1: Float,
        y1: Float,
        z1: Float,
        x2: Float,
        y2: Float,
        z2: Float,
        color: Int,
    ): Boolean {
        matrix.transformPosition(x1, y1, z1, lineStart)
        matrix.transformPosition(x2, y2, z2, lineEnd)
        val dx = lineStart.x - lineEnd.x
        val dy = lineStart.y - lineEnd.y
        val dz = lineStart.z - lineEnd.z
        val lengthSquared = dx * dx + dy * dy + dz * dz
        if (!lengthSquared.isFinite() || lengthSquared <= MIN_LINE_LENGTH_SQ) return false
        val inverseLength = 1f / sqrt(lengthSquared)
        val nx = dx * inverseLength
        val ny = dy * inverseLength
        val nz = dz * inverseLength
        if (!nx.isFinite() || !ny.isFinite() || !nz.isFinite()) return false

        val buffer = edgeBuilder ?: edgeTesselator.begin(
            VertexFormat.Mode.LINES,
            DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH,
        ).also { edgeBuilder = it }
        buffer.addVertex(lineStart.x, lineStart.y, lineStart.z)
            .setColor(color).setNormal(nx, ny, nz).setLineWidth(EDGE_WIDTH)
        buffer.addVertex(lineEnd.x, lineEnd.y, lineEnd.z)
            .setColor(color).setNormal(nx, ny, nz).setLineWidth(EDGE_WIDTH)
        hasEdges = true
        return true
    }

    fun flush(modelViewMatrix: Matrix4fc, glowTexture: Identifier) {
        val uploaded = ArrayList<UploadedDraw>(LOGICAL_BATCH_COUNT)
        try {
            buildAndUpload(glowBuilder, hasGlow, glowPipeline, true)?.let(uploaded::add)
            glowBuilder = null
            buildAndUpload(
                faceBuilder,
                hasFaces,
                if (cubeFaces) cubeFacePipeline else triangleFacePipeline,
                false,
            )?.let(uploaded::add)
            faceBuilder = null
            buildAndUpload(edgeBuilder, hasEdges, edgePipeline, false)?.let(uploaded::add)
            edgeBuilder = null
            if (uploaded.isEmpty()) return

            val target = mc.mainRenderTarget
            val colorAttachment = RenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return
            val dynamicTransform = RenderSystem.getDynamicUniforms().writeTransform(
                Matrix4f(modelViewMatrix), shaderColor, modelOffset, textureMatrix,
            )
            val texture = mc.textureManager.getTexture(glowTexture)

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Supplier { "LiquidBounce Cubes three-batch pass" },
                colorAttachment,
                OptionalInt.empty(),
                null,
                OptionalDouble.empty(),
            ).use { pass ->
                for (draw in uploaded) {
                    pass.setPipeline(draw.pipeline)
                    RenderSystem.bindDefaultUniforms(pass)
                    pass.setUniform("DynamicTransforms", dynamicTransform)
                    if (draw.textured) {
                        pass.bindTexture("Sampler0", texture.textureView, texture.sampler)
                    }
                    pass.setVertexBuffer(0, draw.vertexSlice.buffer())
                    pass.setIndexBuffer(draw.indexSlice.buffer(), draw.indexType)
                    pass.drawIndexed(
                        (draw.vertexSlice.offset() / draw.vertexFormat.vertexSize).toInt(),
                        (draw.indexSlice.offset() / draw.indexType.bytes).toInt(),
                        draw.indexCount,
                        1,
                    )
                }
            }
        } finally {
            clearFrame()
        }
    }

    fun clearFrame() {
        glowBuilder = null
        faceBuilder = null
        edgeBuilder = null
        hasGlow = false
        hasFaces = false
        hasEdges = false
        glowTesselator.clear()
        faceTesselator.clear()
        edgeTesselator.clear()
    }

    private fun buildAndUpload(
        builder: BufferBuilder?,
        hasVertices: Boolean,
        pipeline: RenderPipeline,
        textured: Boolean,
    ): UploadedDraw? {
        if (!hasVertices) return null
        val mesh = builder?.build() ?: return null
        try {
            val state = mesh.drawState()
            val vertexSlice = vertexBuffers.getOrPut(state.format()) {
                StreamingBuffer("LiquidBounce Cubes VBO for ${state.format()}", GpuBuffer.USAGE_VERTEX)
            }.upload(mesh.vertexBuffer())
            val sequential = RenderSystem.getSequentialBuffer(state.mode())
            val indexType = sequential.type()
            val indexSlice = sequential.getBuffer(state.indexCount())
                .slice(0L, state.indexCount().toLong() * indexType.bytes)
            return UploadedDraw(
                pipeline, state.format(), indexType, state.indexCount(), vertexSlice, indexSlice, textured,
            )
        } finally {
            mesh.close()
        }
    }

    private fun facePipeline(name: String, mode: VertexFormat.Mode): RenderPipeline =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id(name))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
            .withDepthStencilState(Optional.empty())
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, mode)
            .build()

    private fun id(name: String) = Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/$name")

    private data class UploadedDraw(
        val pipeline: RenderPipeline,
        val vertexFormat: VertexFormat,
        val indexType: VertexFormat.IndexType,
        val indexCount: Int,
        val vertexSlice: GpuBufferSlice,
        val indexSlice: GpuBufferSlice,
        val textured: Boolean,
    )
}

private class StreamingBuffer(
    private val label: String,
    private val usage: @GpuBuffer.Usage Int,
) {
    private var ring: MappableRingBuffer? = null
    private var offset = 0

    fun upload(data: ByteBuffer): GpuBufferSlice {
        val byteCount = data.remaining()
        ensureCapacity(byteCount)
        var active = checkNotNull(ring)
        if (offset + byteCount > active.size()) {
            active.rotate()
            offset = 0
            active = checkNotNull(ring)
        }
        val slice = active.currentBuffer().slice(offset.toLong(), byteCount.toLong())
        RenderSystem.getDevice().createCommandEncoder().mapBuffer(slice, false, true).use { mapped ->
            MemoryUtil.memCopy(data, mapped.data())
        }
        offset = align4(offset + byteCount)
        return slice
    }

    private fun ensureCapacity(byteCount: Int) {
        val current = ring
        if (current != null && current.size() >= byteCount) return
        current?.close()
        ring = MappableRingBuffer(
            Suppliers.ofInstance(label),
            usage or GpuBuffer.USAGE_MAP_WRITE,
            align4(maxOf(byteCount, 8192)),
        )
        offset = 0
    }

    private fun align4(value: Int) = (value + 3) and 3.inv()
}
