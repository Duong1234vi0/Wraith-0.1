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

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.GpuBufferSlice
import com.mojang.blaze3d.buffers.Std140Builder
import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.CompareOp
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.MeshData
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.joml.Matrix4f
import org.joml.Matrix4fc
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer
import java.util.EnumMap
import java.util.Optional
import java.util.OptionalDouble
import java.util.OptionalInt
import java.util.function.Supplier

object AlphaDlcRenderPipelines : ShaderSource {

    enum class BlockShaderStyle {
        ENERGY,
        PLASMA,
        HONEYCOMB
    }

    private const val NAMESPACE = "liquidbounce"
    private const val BLOCK_CONFIG_UBO_SIZE = 16

    private val blockVertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/block_overlay")
    private val energyFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/block_overlay_energy")
    private val plasmaFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/block_overlay_plasma")
    private val honeycombFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/block_overlay_honeycomb")

    private val blockVertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/block_overlay/block_overlay.vsh")
    }
    private val energyFragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/block_overlay/block_overlay_energy.fsh")
    }
    private val plasmaFragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/block_overlay/block_overlay_plasma.fsh")
    }
    private val honeycombFragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/block_overlay/block_overlay_honeycomb.fsh")
    }

    private val blockPipelines: Map<BlockShaderStyle, RenderPipeline> by lazy(LazyThreadSafetyMode.NONE) {
        mapOf(
            BlockShaderStyle.ENERGY to blockPipeline("block_overlay_energy", energyFragmentId),
            BlockShaderStyle.PLASMA to blockPipeline("block_overlay_plasma", plasmaFragmentId),
            BlockShaderStyle.HONEYCOMB to blockPipeline("block_overlay_honeycomb", honeycombFragmentId)
        )
    }

    val texturedAdditiveNoDepth: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/alphadlc_textured_additive_no_depth"))
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
            .withDepthStencilState(Optional.empty())
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()
    }

    val fillTranslucentNoDepth: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline("alphadlc_fill_translucent_no_depth", depth = false)
    }

    val fillTranslucentDepth: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline("alphadlc_fill_translucent_depth", depth = true)
    }

    val lineTranslucentDepth: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("alphadlc_line_translucent_depth", BlendFunction.TRANSLUCENT, depth = true)
    }

    val lineAdditiveNoDepth: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline("alphadlc_line_additive_no_depth", BlendFunction.LIGHTNING, depth = false)
    }

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce AlphaDLC block overlay config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        BLOCK_CONFIG_UBO_SIZE
    )

    private val vboByFormat = HashMap<VertexFormat, StreamingBuffer>()
    private val iboByType = EnumMap<VertexFormat.IndexType, StreamingBuffer>(VertexFormat.IndexType::class.java)

    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()
    private val shaderColor = Vector4f(1.0f, 1.0f, 1.0f, 1.0f)

    fun blockPipeline(style: BlockShaderStyle): RenderPipeline = blockPipelines.getValue(style)

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> if (identifier == blockVertexId) blockVertexSource else null
        ShaderType.FRAGMENT -> when (identifier) {
            energyFragmentId -> energyFragmentSource
            plasmaFragmentId -> plasmaFragmentSource
            honeycombFragmentId -> honeycombFragmentSource
            else -> null
        }
    }

    fun draw(
        meshData: MeshData,
        pipeline: RenderPipeline,
        modelViewMatrix: Matrix4fc,
        depthAttachment: Boolean,
        texture: Identifier? = null,
        blockConfig: BlockConfig? = null
    ): Boolean = runCatching {
        drawUnchecked(meshData, pipeline, modelViewMatrix, depthAttachment, texture, blockConfig)
        true
    }.onFailure { throwable ->
        LiquidBounce.logger.error("Failed to draw AlphaDLC mesh", throwable)
    }.getOrDefault(false)

    private fun drawUnchecked(
        meshData: MeshData,
        pipeline: RenderPipeline,
        modelViewMatrix: Matrix4fc,
        depthAttachment: Boolean,
        texture: Identifier?,
        blockConfig: BlockConfig?
    ) {
        try {
            val state = meshData.drawState()
            val format = state.format()
            val mode = state.mode()
            val vertexSlice = vboByFormat.getOrPut(format) {
                StreamingBuffer("LiquidBounce AlphaDLC VBO for $format", GpuBuffer.USAGE_VERTEX)
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
                    StreamingBuffer("LiquidBounce AlphaDLC IBO for $indexType", GpuBuffer.USAGE_INDEX)
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
            val blockUniform = blockConfig?.upload()

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Supplier { "LiquidBounce AlphaDLC mesh" },
                colorAttachment,
                OptionalInt.empty(),
                targetDepth,
                OptionalDouble.empty()
            ).use { pass ->
                pass.setPipeline(pipeline)
                RenderSystem.bindDefaultUniforms(pass)
                pass.setUniform("DynamicTransforms", dynamicTransform)
                if (blockUniform != null) {
                    pass.setUniform("BlockOverlayConfig", blockUniform)
                }
                if (texture != null && pipeline.samplers.contains("Sampler0")) {
                    val bound = mc.textureManager.getTexture(texture)
                    pass.bindTexture("Sampler0", bound.textureView, bound.sampler)
                }

                pass.setVertexBuffer(0, vertexSlice.buffer())
                pass.setIndexBuffer(indexSlice.buffer(), indexType)
                pass.drawIndexed(
                    (vertexSlice.offset() / format.vertexSize).toInt(),
                    (indexSlice.offset() / indexType.bytes).toInt(),
                    indexCount,
                    1
                )
            }

            if (blockUniform != null) {
                configUbo.rotate()
            }
        } finally {
            meshData.close()
        }
    }

    private fun BlockConfig.upload(): GpuBuffer {
        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, BLOCK_CONFIG_UBO_SIZE)
                .putVec4(time, opacity, 0.0f, 0.0f)
                .get()

            val buffer = configUbo.currentBuffer()
            RenderSystem.getDevice().createCommandEncoder().mapBuffer(buffer, false, true).use { mapped ->
                mapped.data().put(data)
            }

            return buffer
        }
    }

    fun precompile() {
        runCatching {
            val device = RenderSystem.getDevice()
            blockPipelines.values.forEach { device.precompilePipeline(it, this) }
            device.precompilePipeline(texturedAdditiveNoDepth)
            device.precompilePipeline(fillTranslucentNoDepth)
            device.precompilePipeline(fillTranslucentDepth)
            device.precompilePipeline(lineTranslucentDepth)
            device.precompilePipeline(lineAdditiveNoDepth)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile AlphaDLC render pipelines", throwable)
        }
    }

    data class BlockConfig(val time: Float, val opacity: Float)

    private fun blockPipeline(name: String, fragment: Identifier): RenderPipeline =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/$name"))
            .withVertexShader(blockVertexId)
            .withFragmentShader(fragment)
            .withUniform("BlockOverlayConfig", UniformType.UNIFORM_BUFFER)
            .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
            .withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()

    private fun colorPipeline(name: String, depth: Boolean): RenderPipeline {
        val builder = RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/$name"))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS)

        if (depth) {
            builder.withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
        } else {
            builder.withDepthStencilState(Optional.empty())
        }

        return builder
            .build()
    }

    private fun linePipeline(name: String, blend: BlendFunction, depth: Boolean): RenderPipeline {
        val builder = RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/$name"))
            .withColorTargetState(ColorTargetState(blend))
            .withCull(false)

        if (depth) {
            builder.withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
        } else {
            builder.withDepthStencilState(Optional.empty())
        }

        return builder
            .build()
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
