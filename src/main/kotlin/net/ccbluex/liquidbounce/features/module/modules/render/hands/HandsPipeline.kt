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
package net.ccbluex.liquidbounce.features.module.modules.render.hands

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.Std140Builder
import com.mojang.blaze3d.buffers.Std140SizeCalculator
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.FilterMode
import com.mojang.blaze3d.textures.GpuTextureView
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.lwjgl.system.MemoryStack
import java.util.Optional
import java.util.OptionalInt

object HandsPipeline : ShaderSource {

    private const val NAMESPACE = "liquidbounce"

    private val configSize = Std140SizeCalculator()
        .putVec4()
        .putVec4()
        .putVec4()
        .putVec4()
        .putVec4()
        .putVec4()
        .putIVec4()
        .get()

    private val vertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/hands_fullscreen")
    private val baseFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_base")
    private val kawaseDownFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_kawase_down")
    private val kawaseUpFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_kawase_up")
    private val glowFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_glow")
    private val outlineFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_outline")
    private val edgeMaskFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_edge_mask")
    private val trailUpdateFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_trail_update")
    private val compositeFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hands_composite")

    private val vertexSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/fullscreen.vsh")
    }
    private val baseFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/base.fsh")
    }
    private val kawaseDownFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/kawase_down.fsh")
    }
    private val kawaseUpFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/kawase_up.fsh")
    }
    private val glowFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/glow.fsh")
    }
    private val outlineFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/outline.fsh")
    }
    private val edgeMaskFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/edge_mask.fsh")
    }
    private val trailUpdateFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/trail_update.fsh")
    }
    private val compositeFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/hands/composite.fsh")
    }

    val basePipeline = pipeline(
        name = "hands_base",
        fragment = baseFragmentId,
        samplers = arrayOf("SceneBefore", "RawAfter", "DepthSampler", "HandMaskSampler", "BlurredScene")
    )

    val kawaseDownPipeline = pipeline(
        name = "hands_kawase_down",
        fragment = kawaseDownFragmentId,
        samplers = arrayOf("Sampler0")
    )

    val kawaseUpPipeline = pipeline(
        name = "hands_kawase_up",
        fragment = kawaseUpFragmentId,
        samplers = arrayOf("Sampler0")
    )

    val glowPipeline = pipeline(
        name = "hands_glow",
        fragment = glowFragmentId,
        samplers = arrayOf("BloomTexture", "MaskTexture")
    )

    val outlinePipeline = pipeline(
        name = "hands_outline",
        fragment = outlineFragmentId,
        samplers = arrayOf("MaskTexture", "BloomTexture")
    )

    val edgeMaskPipeline = pipeline(
        name = "hands_edge_mask",
        fragment = edgeMaskFragmentId,
        samplers = arrayOf("MaskTexture")
    )

    val trailUpdatePipeline = pipeline(
        name = "hands_trail_update",
        fragment = trailUpdateFragmentId,
        samplers = arrayOf("HistoryTexture", "CurrentGlowTexture", "ModelTexture")
    )

    val compositePipeline = pipeline(
        name = "hands_composite",
        fragment = compositeFragmentId,
        samplers = arrayOf("CurrentMain", "BaseHand", "GlowTexture", "OutlineTexture", "TrailTexture")
    )

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce Hands config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        configSize
    )

    private val sampler by lazy {
        RenderSystem.getSamplerCache().getClampToEdge(FilterMode.LINEAR)
    }

    @Volatile
    private var coreUnavailable = false
    @Volatile
    private var trailUnavailable = false
    private var coreErrorLogged = false
    private var trailErrorLogged = false

    val isCoreAvailable: Boolean
        get() = !coreUnavailable

    val isTrailAvailable: Boolean
        get() = !trailUnavailable

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
        ShaderType.FRAGMENT -> when (identifier) {
            baseFragmentId -> baseFragmentSource
            kawaseDownFragmentId -> kawaseDownFragmentSource
            kawaseUpFragmentId -> kawaseUpFragmentSource
            glowFragmentId -> glowFragmentSource
            outlineFragmentId -> outlineFragmentSource
            edgeMaskFragmentId -> edgeMaskFragmentSource
            trailUpdateFragmentId -> trailUpdateFragmentSource
            compositeFragmentId -> compositeFragmentSource
            else -> null
        }
    }

    fun renderPass(
        pipeline: RenderPipeline,
        label: String,
        output: GpuTextureView,
        config: Config,
        textures: Array<Pair<String, GpuTextureView>>,
        trailOnly: Boolean = false
    ): Boolean {
        if (coreUnavailable || trailOnly && trailUnavailable) {
            return false
        }

        return runCatching {
            RenderSystem.assertOnRenderThread()
            val uniform = uploadConfig(config)

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Suppliers.ofInstance(label),
                output,
                OptionalInt.empty()
            ).use { pass ->
                pass.setPipeline(pipeline)
                RenderSystem.bindDefaultUniforms(pass)
                pass.setUniform("HandsConfig", uniform)
                for ((name, view) in textures) {
                    pass.bindTexture(name, view, sampler)
                }
                pass.draw(0, 3)
            }

            configUbo.rotate()
            true
        }.onFailure { throwable ->
            if (trailOnly) {
                markTrailUnavailable("Hands trail shader failed; disabling only the trail path.", throwable)
            } else {
                markCoreUnavailable("Hands shader failed; disabling only the Hands module path.", throwable)
            }
        }.getOrDefault(false)
    }

    private fun uploadConfig(config: Config): GpuBuffer {
        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, configSize)
                .putColor(config.color0)
                .putColor(config.color1)
                .putVec4(config.params0.x, config.params0.y, config.params0.z, config.params0.w)
                .putVec4(config.params1.x, config.params1.y, config.params1.z, config.params1.w)
                .putVec4(config.params2.x, config.params2.y, config.params2.z, config.params2.w)
                .putVec4(config.params3.x, config.params3.y, config.params3.z, config.params3.w)
                .putIVec4(config.flags0.x, config.flags0.y, config.flags0.z, config.flags0.w)
                .get()

            val buffer = configUbo.currentBuffer()
            RenderSystem.getDevice().createCommandEncoder().mapBuffer(buffer, false, true).use { mapped ->
                mapped.data().put(data)
            }

            return buffer
        }
    }

    private fun Std140Builder.putColor(color: Color4b): Std140Builder {
        return putVec4(
            color.r / 255.0f,
            color.g / 255.0f,
            color.b / 255.0f,
            color.a / 255.0f
        )
    }

    fun precompile() {
        runCatching {
            val device = RenderSystem.getDevice()
            device.precompilePipeline(basePipeline, this)
            device.precompilePipeline(kawaseDownPipeline, this)
            device.precompilePipeline(kawaseUpPipeline, this)
            device.precompilePipeline(glowPipeline, this)
            device.precompilePipeline(outlinePipeline, this)
            device.precompilePipeline(edgeMaskPipeline, this)
            device.precompilePipeline(compositePipeline, this)
            coreUnavailable = false
            coreErrorLogged = false
        }.onFailure { throwable ->
            markCoreUnavailable("Failed to precompile Hands core pipelines; Hands will stay unavailable.", throwable)
        }

        runCatching {
            RenderSystem.getDevice().precompilePipeline(trailUpdatePipeline, this)
            trailUnavailable = false
            trailErrorLogged = false
        }.onFailure { throwable ->
            markTrailUnavailable("Failed to precompile Hands trail pipeline; only trail will be unavailable.", throwable)
        }
    }

    fun markTrailUnavailable(message: String, throwable: Throwable) {
        trailUnavailable = true
        if (!trailErrorLogged) {
            trailErrorLogged = true
            LiquidBounce.logger.error(message, throwable)
        }
    }

    private fun markCoreUnavailable(message: String, throwable: Throwable) {
        coreUnavailable = true
        if (!coreErrorLogged) {
            coreErrorLogged = true
            LiquidBounce.logger.error(message, throwable)
        }
    }

    private fun pipeline(name: String, fragment: Identifier, samplers: Array<String>): RenderPipeline {
        val builder = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/$name"))
            .withVertexShader(vertexId)
            .withFragmentShader(fragment)
            .withUniform("HandsConfig", UniformType.UNIFORM_BUFFER)
            .withColorTargetState(ColorTargetState.DEFAULT)
            .withDepthStencilState(Optional.empty())
            .withCull(false)

        for (samplerName in samplers) {
            builder.withSampler(samplerName)
        }

        return builder.build()
    }

    data class Config(
        val color0: Color4b = Color4b.TRANSPARENT,
        val color1: Color4b = Color4b.TRANSPARENT,
        val params0: Vec4 = Vec4(),
        val params1: Vec4 = Vec4(),
        val params2: Vec4 = Vec4(),
        val params3: Vec4 = Vec4(),
        val flags0: IVec4 = IVec4()
    )

    data class Vec4(
        val x: Float = 0.0f,
        val y: Float = 0.0f,
        val z: Float = 0.0f,
        val w: Float = 0.0f
    )

    data class IVec4(
        val x: Int = 0,
        val y: Int = 0,
        val z: Int = 0,
        val w: Int = 0
    )
}
