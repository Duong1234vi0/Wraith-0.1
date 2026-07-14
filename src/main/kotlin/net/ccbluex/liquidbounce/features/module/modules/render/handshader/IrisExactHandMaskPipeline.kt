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
import com.mojang.blaze3d.textures.GpuTextureView
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.MappableRingBuffer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import org.lwjgl.system.MemoryStack
import java.util.Optional
import java.util.OptionalInt

object IrisExactHandMaskPipeline : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val CONFIG_UBO_SIZE = 16

    private val vertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/iris_exact_hand_mask")
    private val fragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/iris_exact_hand_mask")

    private val vertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/hand_shader/hand_shader.vsh")
    }

    private val fragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/hand_shader/iris_exact_hand_mask.fsh")
    }

    val pipeline: RenderPipeline = RenderPipeline.builder(RenderPipelines.POST_PROCESSING_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/iris_exact_hand_mask"))
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withSampler("BeforeDepth")
        .withSampler("AfterDepth")
        .withSampler("PreviousMask")
        .withUniform("IrisExactHandMaskConfig", UniformType.UNIFORM_BUFFER)
        .withColorTargetState(ColorTargetState.DEFAULT)
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    private val configUbo = MappableRingBuffer(
        Suppliers.ofInstance("LiquidBounce Iris exact hand mask config"),
        GpuBuffer.USAGE_UNIFORM or GpuBuffer.USAGE_MAP_WRITE,
        CONFIG_UBO_SIZE
    )

    @Volatile
    private var unavailable = false
    private var errorLogged = false

    val isAvailable: Boolean
        get() = !unavailable

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
    }

    fun render(
        beforeDepth: GpuTextureView,
        afterDepth: GpuTextureView,
        previousMask: GpuTextureView,
        outputMask: GpuTextureView,
        mergePrevious: Boolean
    ): Boolean {
        if (unavailable) {
            return false
        }

        RenderSystem.assertOnRenderThread()

        return runCatching {
            val uniformBuffer = uploadUniforms(mergePrevious)
            val sampler = RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST)

            RenderSystem.getDevice().createCommandEncoder().createRenderPass(
                Suppliers.ofInstance("LiquidBounce Iris exact hand mask"),
                outputMask,
                OptionalInt.empty()
            ).use { pass ->
                pass.setPipeline(pipeline)
                RenderSystem.bindDefaultUniforms(pass)
                pass.setUniform("IrisExactHandMaskConfig", uniformBuffer)
                pass.bindTexture("BeforeDepth", beforeDepth, sampler)
                pass.bindTexture("AfterDepth", afterDepth, sampler)
                pass.bindTexture("PreviousMask", previousMask, sampler)
                pass.draw(0, 3)
            }

            configUbo.rotate()
            true
        }.onFailure { throwable ->
            markUnavailable("Iris exact hand mask pipeline failed; Iris hand post-processing will be skipped.", throwable)
        }.getOrDefault(false)
    }

    private fun uploadUniforms(mergePrevious: Boolean): GpuBuffer {
        MemoryStack.stackPush().use { stack ->
            val data = Std140Builder.onStack(stack, CONFIG_UBO_SIZE)
                .putIVec4(if (mergePrevious) 1 else 0, 0, 0, 0)
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
            unavailable = false
            errorLogged = false
        }.onFailure { throwable ->
            markUnavailable("Failed to precompile Iris exact hand mask pipeline; Iris hand effects will stay unavailable.", throwable)
        }
    }

    private fun markUnavailable(message: String, throwable: Throwable) {
        unavailable = true
        if (!errorLogged) {
            errorLogged = true
            LiquidBounce.logger.error(message, throwable)
        }
    }
}
