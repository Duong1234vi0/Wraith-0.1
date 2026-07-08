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
package net.ccbluex.liquidbounce.render.gui

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.AddressMode
import com.mojang.blaze3d.textures.FilterMode
import com.mojang.blaze3d.textures.GpuSampler
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.VertexFormatElement
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import java.util.Optional

object MsdfFontShader : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val PIPELINE_PATH = "pipeline/msdf_font"
    private const val FSH_RESOURCE = "/resources/liquidbounce/shaders/msdf_font/msdf_font.fsh"
    private const val VSH_RESOURCE = "/resources/liquidbounce/shaders/msdf_font/msdf_font.vsh"

    private val fragmentId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/msdf_font")
    private val vertexId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/msdf_font")

    private val fragmentSource: String by lazy { resourceToString(FSH_RESOURCE) }
    private val vertexSource: String by lazy { resourceToString(VSH_RESOURCE) }

    @JvmField
    val FORMAT: VertexFormat = VertexFormat.builder()
        .add("Position", VertexFormatElement.POSITION)
        .add("UV0", VertexFormatElement.UV0)
        .add("Color", VertexFormatElement.COLOR)
        .add("RangeThickness", VertexFormatElement.UV1)
        .add("SmoothOutline", VertexFormatElement.UV2)
        .build()

    @JvmField
    val PIPELINE: RenderPipeline = RenderPipeline.Builder()
        .apply { withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, PIPELINE_PATH)) }
        .apply { withSnippet(RenderPipelines.GUI_SNIPPET) }
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withSampler("Sampler0")
        .withVertexFormat(FORMAT, VertexFormat.Mode.QUADS)
        .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
        .withDepthStencilState(Optional.empty())
        .build()

    val linearSampler: GpuSampler
        get() = RenderSystem.getSamplerCache().getSampler(
            AddressMode.REPEAT,
            AddressMode.REPEAT,
            FilterMode.LINEAR,
            FilterMode.LINEAR,
            false
        )

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
    }

    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile MSDF font pipeline", throwable)
        }
    }
}
