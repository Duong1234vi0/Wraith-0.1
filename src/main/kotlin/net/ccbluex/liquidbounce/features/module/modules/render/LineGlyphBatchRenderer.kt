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
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.CompareOp
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
import java.util.OptionalDouble
import java.util.OptionalInt
import java.util.function.Supplier

/** Five LineGlyph source passes uploaded before one 26.1.2 RenderPass. */
internal object LineGlyphBatchRenderer {
    internal const val LOGICAL_PASS_COUNT = 5
    internal const val MAX_RENDER_PASSES = 1

    private const val BUFFER_SIZE = 0x200000
    private val tesselators = Array(LOGICAL_PASS_COUNT) { Tesselator(BUFFER_SIZE) }
    private val builders = arrayOfNulls<BufferBuilder>(LOGICAL_PASS_COUNT)
    private val hasVertices = BooleanArray(LOGICAL_PASS_COUNT)

    private val glowPipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("line_glyph_glow", BlendFunction.LIGHTNING)
    }
    private val corePipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("line_glyph_core", BlendFunction.TRANSLUCENT)
    }
    private val vertexBuffer = LineGlyphStreamingBuffer("LiquidBounce LineGlyph VBO", GpuBuffer.USAGE_VERTEX)
    private val shaderColor = Vector4f(1f, 1f, 1f, 1f)
    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()

    fun beginFrame() = clearFrame()

    @Suppress("LongParameterList")
    fun appendLine(
        passIndex: Int,
        x1: Float, y1: Float, z1: Float,
        x2: Float, y2: Float, z2: Float,
        color: Int,
        width: Float,
    ): Boolean {
        require(passIndex in 0 until LOGICAL_PASS_COUNT)
        val normal = WyvernLineGlyphRules.lineNormal(x1, y1, z1, x2, y2, z2)
        if (normal == null || !width.isFinite() || width <= 0f) return false
        val buffer = builders[passIndex] ?: tesselators[passIndex].begin(
            VertexFormat.Mode.LINES,
            DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH,
        ).also { builders[passIndex] = it }
        buffer.addVertex(x1, y1, z1).setColor(color).setNormal(normal.x, normal.y, normal.z).setLineWidth(width)
        buffer.addVertex(x2, y2, z2).setColor(color).setNormal(normal.x, normal.y, normal.z).setLineWidth(width)
        hasVertices[passIndex] = true
        return true
    }

    fun flush(modelViewMatrix: Matrix4fc) {
        val uploaded = ArrayList<UploadedDraw>(LOGICAL_PASS_COUNT)
        try {
            for (passIndex in 0 until LOGICAL_PASS_COUNT) {
                if (!hasVertices[passIndex]) continue
                val mesh = builders[passIndex]?.build() ?: continue
                builders[passIndex] = null
                uploaded += upload(mesh, if (passIndex < LOGICAL_PASS_COUNT - 1) glowPipeline else corePipeline)
            }
            if (uploaded.isEmpty()) return

            val target = mc.mainRenderTarget
            val colorAttachment = RenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return
            val depthAttachment = RenderSystem.outputDepthTextureOverride ?: target.depthTextureView.takeIf { target.useDepth }
            val dynamicTransform = RenderSystem.getDynamicUniforms().writeTransform(
                Matrix4f(modelViewMatrix), shaderColor, modelOffset, textureMatrix,
            )
            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Supplier { "LiquidBounce LineGlyph five-pass batch" },
                colorAttachment,
                OptionalInt.empty(),
                depthAttachment,
                OptionalDouble.empty(),
            ).use { pass ->
                for (draw in uploaded) {
                    pass.setPipeline(draw.pipeline)
                    RenderSystem.bindDefaultUniforms(pass)
                    pass.setUniform("DynamicTransforms", dynamicTransform)
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
        for (index in 0 until LOGICAL_PASS_COUNT) {
            builders[index] = null
            hasVertices[index] = false
            tesselators[index].clear()
        }
    }

    private fun upload(mesh: MeshData, pipeline: RenderPipeline): UploadedDraw {
        try {
            val state = mesh.drawState()
            val vertexSlice = vertexBuffer.upload(mesh.vertexBuffer())
            val sequential = RenderSystem.getSequentialBuffer(state.mode())
            val indexType = sequential.type()
            val indexSlice = sequential.getBuffer(state.indexCount())
                .slice(0L, state.indexCount().toLong() * indexType.bytes)
            return UploadedDraw(pipeline, state.format(), indexType, state.indexCount(), vertexSlice, indexSlice)
        } finally {
            mesh.close()
        }
    }

    private fun linePipeline(name: String, blend: BlendFunction): RenderPipeline =
        RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/$name"))
            .withColorTargetState(ColorTargetState(blend))
            .withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
            .withCull(false)
            .build()

    private data class UploadedDraw(
        val pipeline: RenderPipeline,
        val vertexFormat: VertexFormat,
        val indexType: VertexFormat.IndexType,
        val indexCount: Int,
        val vertexSlice: GpuBufferSlice,
        val indexSlice: GpuBufferSlice,
    )
}

/** Primitive reusable storage: six coordinates and one source alpha per emitted dash. */
internal class LineGlyphFrameGeometry(initialCapacity: Int = 1024) {
    private var coordinates = FloatArray(maxOf(6, initialCapacity * 6))
    private var alphas = FloatArray(maxOf(1, initialCapacity))
    var lineCount: Int = 0
        private set

    fun clear() {
        lineCount = 0
    }

    @Suppress("LongParameterList")
    fun append(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float, alpha: Float) {
        ensureCapacity(lineCount + 1)
        val offset = lineCount * 6
        coordinates[offset] = x1
        coordinates[offset + 1] = y1
        coordinates[offset + 2] = z1
        coordinates[offset + 3] = x2
        coordinates[offset + 4] = y2
        coordinates[offset + 5] = z2
        alphas[lineCount] = alpha
        lineCount++
    }

    fun forEach(block: (Float, Float, Float, Float, Float, Float, Float) -> Unit) {
        for (index in 0 until lineCount) {
            val offset = index * 6
            block(
                coordinates[offset], coordinates[offset + 1], coordinates[offset + 2],
                coordinates[offset + 3], coordinates[offset + 4], coordinates[offset + 5],
                alphas[index],
            )
        }
    }

    private fun ensureCapacity(required: Int) {
        if (required <= alphas.size) return
        val newCapacity = maxOf(required, alphas.size * 2)
        coordinates = coordinates.copyOf(newCapacity * 6)
        alphas = alphas.copyOf(newCapacity)
    }
}

private class LineGlyphStreamingBuffer(
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
