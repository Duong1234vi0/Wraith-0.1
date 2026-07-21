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
package net.ccbluex.liquidbounce.features.module.modules.render.killeffect

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

/** One source-equivalent line batch per KillEffect pass, submitted in one modern RenderPass. */
internal object KillEffectBatchRenderer {
    internal const val LOGICAL_PASS_COUNT = 5
    internal const val MAX_RENDER_PASSES = 1

    private const val TESSELLATOR_SIZE = 0x40000

    private val tesselators = Array(LOGICAL_PASS_COUNT) { Tesselator(TESSELLATOR_SIZE) }
    private val builders = arrayOfNulls<BufferBuilder>(LOGICAL_PASS_COUNT)
    private val hasVertices = BooleanArray(LOGICAL_PASS_COUNT)

    private val additivePipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("kill_effect_glow", BlendFunction.LIGHTNING)
    }
    private val corePipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("kill_effect_core", BlendFunction.TRANSLUCENT)
    }

    private val vertexBuffer = StreamingBuffer("LiquidBounce KillEffect line VBO", GpuBuffer.USAGE_VERTEX)
    private val shaderColor = Vector4f(1f, 1f, 1f, 1f)
    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()

    fun beginFrame() = clearFrame()

    @Suppress("LongParameterList")
    fun appendLine(
        passIndex: Int,
        x1: Float,
        y1: Float,
        z1: Float,
        x2: Float,
        y2: Float,
        z2: Float,
        color: Int,
        width: Float,
    ): Boolean {
        require(passIndex in 0 until LOGICAL_PASS_COUNT)
        val normal = KillEffectRules.lineNormal(x1, y1, z1, x2, y2, z2)
        if (normal == null || !width.isFinite() || width <= 0f) {
            return false
        }

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
                uploaded += upload(mesh, if (passIndex < LOGICAL_PASS_COUNT - 1) additivePipeline else corePipeline)
            }
            if (uploaded.isEmpty()) return

            val target = mc.mainRenderTarget
            val colorAttachment = RenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return
            val depthAttachment = RenderSystem.outputDepthTextureOverride ?: target.depthTextureView.takeIf { target.useDepth }
            val dynamicTransform = RenderSystem.getDynamicUniforms().writeTransform(
                Matrix4f(modelViewMatrix), shaderColor, modelOffset, textureMatrix,
            )

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Supplier { "LiquidBounce KillEffect five-pass batch" },
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
            return UploadedDraw(
                pipeline,
                state.format(),
                indexType,
                state.indexCount(),
                vertexSlice,
                indexSlice,
            )
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
