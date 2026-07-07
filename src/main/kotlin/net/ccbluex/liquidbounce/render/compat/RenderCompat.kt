package net.ccbluex.liquidbounce.render.compat

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.GpuBufferSlice
import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.systems.RenderPass
import com.mojang.blaze3d.systems.RenderSystem as MojangRenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.MeshData
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer
import java.util.EnumMap
import java.util.Optional
import java.util.OptionalDouble
import java.util.OptionalInt
import java.util.function.Supplier

/**
 * Compatibility facade for modules that still use the pre-1.26 global render API.
 *
 * 1.26 no longer draws meshes through the old RenderSystem/BufferRenderer path. The previous stub
 * only closed MeshData, so Render-category modules compiled but never displayed anything.
 */
object RenderSystem {
    private val identityProjection = Matrix4f()
    private var projectionType: Any? = null

    internal var currentShader: ShaderProgramKey = ShaderProgramKeys.POSITION_COLOR
    internal var currentTexture: Identifier? = null
    internal var depthTest = true
    internal var cull = true
    // #Edit By Claude 2026-06-12: opt-in ADDITIVE blend for the compat mesh path. The legacy
    // RenderSystem.blendFunc() is a no-op, so additive glow (SRC_ALPHA, ONE) silently downgraded to
    // TRANSLUCENT and looked dull. A caller must EXPLICITLY call enableAdditive() to get additive — auto-
    // detecting from blendFunc args would wrongly flip Dreamcore/KillAnimation/Wings/etc. (all use dst=ONE
    // and were tuned for the translucent no-op). Cleared by defaultBlendFunc/disableBlend so it never leaks
    // past one module's draw. Part of the pipeline cache key.
    internal var additiveBlend = false

    /** #Edit By Claude 2026-06-12: opt into ADDITIVE compositing for subsequent compat-mesh draws. */
    fun enableAdditive() { additiveBlend = true }
    // Tracked color modulator. The legacy RenderSystem.setShaderColor() path was a no-op,
    // so withColor { drawLines(...) } and similar idioms produced colorless geometry.
    // CompatMeshSubmitter feeds this into the DynamicTransforms uniform per draw.
    internal val shaderColor = Vector4f(1.0f, 1.0f, 1.0f, 1.0f)

    fun assertOnRenderThread() = MojangRenderSystem.assertOnRenderThread()
    fun enableBlend() = Unit
    fun disableBlend() { additiveBlend = false }
    fun defaultBlendFunc() { additiveBlend = false }
    fun enableCull() {
        cull = true
    }
    fun disableCull() {
        cull = false
    }
    fun enableDepthTest() {
        depthTest = true
    }
    fun disableDepthTest() {
        depthTest = false
    }
    fun depthMask(mask: Boolean) = Unit
    fun lineWidth(width: Float) = Unit
    fun setShader(shader: ShaderProgramKey) {
        currentShader = shader
    }
    fun setShader(shader: Any?) {
        currentShader = shader as? ShaderProgramKey ?: ShaderProgramKeys.POSITION_COLOR
    }
    fun setShaderColor(r: Float, g: Float, b: Float, a: Float) {
        shaderColor.set(r, g, b, a)
    }
    fun setShaderTexture(slot: Int, texture: Identifier?) {
        if (slot == 0) {
            currentTexture = texture
        }
    }
    fun setShaderTexture(slot: Int, texture: Int) = Unit
    fun getShaderTexture(slot: Int): Int = 0
    fun bindTexture(texture: Int) = Unit
    fun blendFunc(src: Any?, dst: Any?) = Unit
    fun blendFuncSeparate(srcRgb: Any?, dstRgb: Any?, srcAlpha: Any?, dstAlpha: Any?) = Unit
    fun getProjectionMatrix(): Matrix4f = Matrix4f(identityProjection)
    fun getModelViewMatrix(): Matrix4f = Matrix4f(MojangRenderSystem.getModelViewMatrix())
    fun getProjectionType(): Any? = projectionType
    fun setProjectionMatrix(matrix: Matrix4f, vertexSorting: Any?) {
        identityProjection.set(matrix)
        projectionType = vertexSorting
    }
    fun renderThreadTesselator(): Tesselator = Tesselator.getInstance()
    fun recordRenderCall(runnable: Runnable) = runnable.run()
}

object BufferRenderer {
    // #Edit By Codex: submit legacy MeshData through the 1.26 RenderPipeline path instead of discarding it.
    fun drawWithGlobalProgram(meshData: MeshData) = CompatMeshSubmitter.draw(meshData)
}

private object CompatMeshSubmitter {
    private val vboByFormat = HashMap<VertexFormat, StreamingBuffer>()
    private val iboByType = EnumMap<VertexFormat.IndexType, StreamingBuffer>(VertexFormat.IndexType::class.java)
    private val pipelines = HashMap<PipelineKey, RenderPipeline>()

    private val modelOffset = Vector3f()
    private val textureMatrix = Matrix4f()

    fun draw(meshData: MeshData) {
        try {
            val state = meshData.drawState()
            val format = state.format()
            val mode = state.mode()
            val pipeline = pipelineFor(format, mode)

            val vertexSlice = vboByFormat.getOrPut(format) {
                StreamingBuffer("LiquidBounce compat VBO for $format", GpuBuffer.USAGE_VERTEX)
            }.upload(meshData.vertexBuffer())

            val rawIndices = meshData.indexBuffer()
            val indexCount = state.indexCount()
            val indexType: VertexFormat.IndexType
            val indexSlice: GpuBufferSlice

            if (rawIndices == null) {
                val sequential = MojangRenderSystem.getSequentialBuffer(mode)
                indexType = sequential.type()
                indexSlice = sequential.getBuffer(indexCount)
                    .slice(0L, indexCount.toLong() * indexType.bytes)
            } else {
                indexType = state.indexType()
                indexSlice = iboByType.getOrPut(indexType) {
                    StreamingBuffer("LiquidBounce compat IBO for $indexType", GpuBuffer.USAGE_INDEX)
                }.upload(rawIndices)
            }

            drawUploaded(pipeline, format, indexType, indexCount, vertexSlice, indexSlice)
        } finally {
            meshData.close()
        }
    }

    private fun drawUploaded(
        pipeline: RenderPipeline,
        vertexFormat: VertexFormat,
        indexType: VertexFormat.IndexType,
        indexCount: Int,
        vertexSlice: GpuBufferSlice,
        indexSlice: GpuBufferSlice
    ) {
        val target = mc.mainRenderTarget
        val colorAttachment = MojangRenderSystem.outputColorTextureOverride ?: target.colorTextureView ?: return
        val depthAttachment = MojangRenderSystem.outputDepthTextureOverride
            ?: target.depthTextureView.takeIf { target.useDepth && RenderSystem.depthTest }

        // writeTransform internally maps a uniform buffer, which the GPU API forbids
        // while a render pass is open. Compute the slice BEFORE opening the pass.
        val dynamicTransform = MojangRenderSystem.getDynamicUniforms().writeTransform(
            MojangRenderSystem.getModelViewMatrix(),
            RenderSystem.shaderColor,
            modelOffset,
            textureMatrix
        )

        MojangRenderSystem.getDevice().createCommandEncoder().createRenderPass(
            Supplier { "LiquidBounce legacy compat mesh" },
            colorAttachment,
            OptionalInt.empty(),
            depthAttachment,
            OptionalDouble.empty()
        ).use { pass ->
            pass.setPipeline(pipeline)
            MojangRenderSystem.bindDefaultUniforms(pass)
            pass.setUniform("DynamicTransforms", dynamicTransform)
            bindTextureIfNeeded(pass, pipeline)
            setupScissor(pass)

            pass.setVertexBuffer(0, vertexSlice.buffer())
            pass.setIndexBuffer(indexSlice.buffer(), indexType)
            pass.drawIndexed(
                (vertexSlice.offset() / vertexFormat.vertexSize).toInt(),
                (indexSlice.offset() / indexType.bytes).toInt(),
                indexCount,
                1
            )
        }
    }

    private fun bindTextureIfNeeded(pass: RenderPass, pipeline: RenderPipeline) {
        if (!pipeline.samplers.contains("Sampler0")) {
            return
        }

        val textureId = RenderSystem.currentTexture ?: return
        val texture = mc.textureManager.getTexture(textureId)
        pass.bindTexture("Sampler0", texture.textureView, texture.sampler)
    }

    private fun setupScissor(pass: RenderPass) {
        val scissor = MojangRenderSystem.getScissorStateForRenderTypeDraws()
        if (scissor.enabled()) {
            pass.enableScissor(scissor.x(), scissor.y(), scissor.width(), scissor.height())
        }
    }

    private fun pipelineFor(format: VertexFormat, mode: VertexFormat.Mode): RenderPipeline {
        val textured = format == DefaultVertexFormat.POSITION_TEX_COLOR ||
            RenderSystem.currentShader == ShaderProgramKeys.POSITION_TEX_COLOR
        // #Edit By Claude 2026-06-12: additive state is part of the cache key so additive and translucent
        // variants of the same format/mode get distinct pipelines (no cross-contamination).
        val additive = RenderSystem.additiveBlend
        val key = PipelineKey(format, mode, textured, RenderSystem.depthTest, RenderSystem.cull, additive)

        return pipelines.getOrPut(key) {
            RenderPipeline.Builder()
                .withLocation(
                    Identifier.fromNamespaceAndPath(
                        "liquidbounce",
                        "pipeline/legacy_compat_${pipelines.size}"
                    )
                )
                .apply {
                    when {
                        textured -> {
                            withSnippet(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
                            withVertexShader("core/position_tex_color")
                            withFragmentShader("core/position_tex_color")
                            withSampler("Sampler0")
                            withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, mode)
                        }
                        format == DefaultVertexFormat.POSITION -> {
                            // #Edit By Codex: core/position fragment shader uses apply_fog(),
                            // which depends on the Fog UBO. Including only MATRICES_PROJECTION
                            // leaves Fog uniforms unbound and the result tints to white.
                            withSnippet(RenderPipelines.MATRICES_FOG_SNIPPET)
                            withVertexShader("core/position")
                            withFragmentShader("core/position")
                            withVertexFormat(DefaultVertexFormat.POSITION, mode)
                        }
                        else -> {
                            withSnippet(RenderPipelines.DEBUG_FILLED_SNIPPET)
                            withVertexFormat(DefaultVertexFormat.POSITION_COLOR, mode)
                        }
                    }

                    withCull(RenderSystem.cull)
                    // #Edit By Claude 2026-06-12: wonderful's glow uses blendFunc(770, 1) = SRC_ALPHA/ONE,
                    // which is BlendFunction.LIGHTNING — NOT ADDITIVE (ONE/ONE). bloom.png is white RGB
                    // everywhere with the radial falloff ONLY in the alpha channel; ADDITIVE ignores alpha
                    // and paints solid white squares (the giant blob). LIGHTNING weights the source by
                    // alpha, giving the soft radial glow wonderful shows.
                    withColorTargetState(
                        ColorTargetState(if (additive) BlendFunction.LIGHTNING else BlendFunction.TRANSLUCENT)
                    )
                    if (!RenderSystem.depthTest) {
                        withDepthStencilState(Optional.empty())
                    }
                }
                .build()
        }
    }

    private data class PipelineKey(
        val format: VertexFormat,
        val mode: VertexFormat.Mode,
        val textured: Boolean,
        val depthTest: Boolean,
        val cull: Boolean,
        val additive: Boolean
    )
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
        MojangRenderSystem.getDevice().createCommandEncoder().mapBuffer(slice, false, true).use { mapped ->
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
        val size = align4(maxOf(byteCount, 8192))
        ring = MappableRingBuffer(
            Suppliers.ofInstance(label),
            usage or GpuBuffer.USAGE_MAP_WRITE,
            size
        )
        offset = 0
    }

    private fun align4(value: Int): Int {
        return (value + 3) and 3.inv()
    }
}

typealias ShaderProgramKey = String

object ShaderProgramKeys {
    const val POSITION: ShaderProgramKey = "position"
    const val POSITION_COLOR: ShaderProgramKey = "position_color"
    const val POSITION_TEX_COLOR: ShaderProgramKey = "position_tex_color"
}

object GlStateManager {
    enum class SrcFactor {
        SRC_ALPHA,
        ONE,
        ZERO,
        DST_COLOR
    }

    enum class DstFactor {
        ONE_MINUS_SRC_ALPHA,
        ONE,
        ZERO,
        SRC_COLOR
    }
}
