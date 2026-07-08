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
package net.ccbluex.liquidbounce.features.module.modules.render.handshader

import com.google.common.base.Suppliers
import com.mojang.blaze3d.buffers.GpuBuffer
import com.mojang.blaze3d.buffers.Std140Builder
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.shaders.UniformType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.FilterMode
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.lwjgl.system.MemoryStack
import java.util.Optional
import java.util.OptionalInt

/**
 * Current-GPU-API equivalent of AlphaDLC's legacy `alphadlc:core/hand_blur` post pass.
 */
object HandShaderPipeline : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val PIPELINE_PATH = "pipeline/hand_shader"
    private const val CONFIG_UBO_SIZE = 64

    private val vertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/hand_shader")
    private val fragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/hand_shader")

    private val vertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/hand_shader/hand_shader.vsh")
    }

    private val fragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/hand_shader/hand_shader.fsh")
    }

    val pipeline: RenderPipeline = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, PIPELINE_PATH))
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withSampler("Sampler0")
        .withSampler("DepthSampler")
        .withUniform("HandShaderConfig", UniformType.UNIFORM_BUFFER)
        .withColorTargetState(ColorTargetState.DEFAULT)
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce HandShader config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        CONFIG_UBO_SIZE
    )

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
    }

    fun render(
        sourceColor: com.mojang.blaze3d.textures.GpuTextureView,
        handDepth: com.mojang.blaze3d.textures.GpuTextureView,
        outputColor: com.mojang.blaze3d.textures.GpuTextureView,
        mode: ModuleHandShader.Mode,
        tint: Color4b,
        blurRadius: Float,
        threshold: Float,
        opacity: Float,
        time: Float,
        shaderScale: Float
    ) {
        RenderSystem.assertOnRenderThread()

        val uniformBuffer = uploadUniforms(
            mode = mode.ordinal,
            tint = tint,
            blurRadius = blurRadius,
            threshold = threshold,
            opacity = opacity,
            time = time,
            shaderScale = shaderScale
        )

        val sampler = RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST)
        RenderSystem.getDevice().createCommandEncoder().createRenderPass(
            Suppliers.ofInstance("LiquidBounce HandShader post-process"),
            outputColor,
            OptionalInt.empty()
        ).use { pass ->
            pass.setPipeline(pipeline)
            RenderSystem.bindDefaultUniforms(pass)
            pass.setUniform("HandShaderConfig", uniformBuffer)
            pass.bindTexture("Sampler0", sourceColor, sampler)
            pass.bindTexture("DepthSampler", handDepth, sampler)
            pass.draw(0, 3)
        }

        configUbo.rotate()
    }

    private fun uploadUniforms(
        mode: Int,
        tint: Color4b,
        blurRadius: Float,
        threshold: Float,
        opacity: Float,
        time: Float,
        shaderScale: Float
    ): GpuBuffer {
        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, CONFIG_UBO_SIZE)
                .putVec4(
                    tint.r / 255.0f,
                    tint.g / 255.0f,
                    tint.b / 255.0f,
                    opacity
                )
                .putVec4(0.0f, 0.0f, 0.0f, 0.0f)
                .putVec4(blurRadius, threshold, time, shaderScale)
                .putIVec4(mode, 0, 0, 0)
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
            RenderSystem.getDevice().precompilePipeline(pipeline, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile HandShader pipeline", throwable)
        }
    }
}
