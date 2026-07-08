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
package net.ccbluex.liquidbounce.features.module.modules.render.nametags

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.Std140Builder
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.systems.RenderPass
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.FilterMode
import com.mojang.blaze3d.textures.GpuTextureView
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.lwjgl.system.MemoryStack
import java.util.Optional

object NameTagsBlurPipelines : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val CONFIG_UBO_SIZE = 16

    private val kawaseVertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/nametags_kawase")
    private val downscaleFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/nametags_kawase_downscale")
    private val upscaleFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/nametags_kawase_upscale")
    private val maskVertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/nametags_sampler_texture")
    private val maskFragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/nametags_sampler_texture")

    private val kawaseVertexSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/blur/kawase.vsh")
    }
    private val downscaleFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/blur/downscale.fsh")
    }
    private val upscaleFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/blur/upscale.fsh")
    }
    private val maskVertexSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/sampler_texture.vsh")
    }
    private val maskFragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/sampler_texture.fsh")
    }

    @JvmField
    val DOWNSCALE_PIPELINE: RenderPipeline = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/nametags_kawase_downscale"))
        .withVertexShader(kawaseVertexId)
        .withFragmentShader(downscaleFragmentId)
        .withSampler("Sampler0")
        .withUniform("KawaseConfig", UniformType.UNIFORM_BUFFER)
        .withColorTargetState(ColorTargetState.DEFAULT)
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    @JvmField
    val UPSCALE_PIPELINE: RenderPipeline = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/nametags_kawase_upscale"))
        .withVertexShader(kawaseVertexId)
        .withFragmentShader(upscaleFragmentId)
        .withSampler("Sampler0")
        .withUniform("KawaseConfig", UniformType.UNIFORM_BUFFER)
        .withColorTargetState(ColorTargetState.DEFAULT)
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    @JvmField
    val MASK_PIPELINE: RenderPipeline = RenderPipeline.builder(RenderPipelines.GUI_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/nametags_sampler_texture"))
        .withVertexShader(maskVertexId)
        .withFragmentShader(maskFragmentId)
        .withSampler("Sampler0")
        .withVertexFormat(NameTagsBackgroundShader.FORMAT, com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS)
        .withColorTargetState(ColorTargetState(com.mojang.blaze3d.pipeline.BlendFunction.TRANSLUCENT))
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce NameTags Kawase config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        CONFIG_UBO_SIZE
    )

    private val sampler by lazy {
        RenderSystem.getSamplerCache().getClampToEdge(FilterMode.LINEAR)
    }

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> when (identifier) {
            kawaseVertexId -> kawaseVertexSource
            maskVertexId -> maskVertexSource
            else -> null
        }
        ShaderType.FRAGMENT -> when (identifier) {
            downscaleFragmentId -> downscaleFragmentSource
            upscaleFragmentId -> upscaleFragmentSource
            maskFragmentId -> maskFragmentSource
            else -> null
        }
    }

    fun renderPass(
        pipeline: RenderPipeline,
        source: GpuTextureView,
        output: GpuTextureView,
        sourceWidth: Int,
        sourceHeight: Int,
        passIndex: Int,
        steps: Int,
        offset: Float
    ) {
        val uniform = uploadUniforms(
            halfTexelX = 0.5f / sourceWidth.toFloat(),
            halfTexelY = 0.5f / sourceHeight.toFloat(),
            offset = offset * (passIndex.toFloat() / steps.toFloat())
        )

        RenderSystem.getDevice().createCommandEncoder().createRenderPass(
            Suppliers.ofInstance("LiquidBounce NameTags Kawase pass"),
            output,
            java.util.OptionalInt.empty()
        ).use { renderPass: RenderPass ->
            renderPass.setPipeline(pipeline)
            RenderSystem.bindDefaultUniforms(renderPass)
            renderPass.setUniform("KawaseConfig", uniform)
            renderPass.bindTexture("Sampler0", source, sampler)
            renderPass.draw(0, 3)
        }

        configUbo.rotate()
    }

    private fun uploadUniforms(halfTexelX: Float, halfTexelY: Float, offset: Float): GpuBuffer {
        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, CONFIG_UBO_SIZE)
                .putVec4(halfTexelX, halfTexelY, offset, 0.0f)
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
            device.precompilePipeline(DOWNSCALE_PIPELINE, this)
            device.precompilePipeline(UPSCALE_PIPELINE, this)
            device.precompilePipeline(MASK_PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile NameTags blur pipelines", throwable)
        }
    }
}
