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

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.VertexFormatElement
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import java.util.Optional

object NameTagsBackgroundShader : ShaderSource {

    private const val NAMESPACE = "liquidbounce"

    private val vertexId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/nametags_round")
    private val fragmentId = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/nametags_round")

    private val vertexSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/round.vsh")
    }
    private val fragmentSource by lazy {
        resourceToString("/resources/liquidbounce/shaders/nametags/round.fsh")
    }

    @JvmField
    val FORMAT: VertexFormat = VertexFormat.builder()
        .add("Position", VertexFormatElement.POSITION)
        .add("Color", VertexFormatElement.COLOR)
        .add("Location", VertexFormatElement.UV0)
        .add("Size", VertexFormatElement.UV1)
        .add("Radius", VertexFormatElement.UV2)
        .build()

    @JvmField
    val PIPELINE: RenderPipeline = RenderPipeline.builder(RenderPipelines.GUI_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/nametags_round"))
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withVertexFormat(FORMAT, VertexFormat.Mode.QUADS)
        .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
        .withDepthStencilState(Optional.empty())
        .withCull(false)
        .build()

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
    }

    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile NameTags background pipeline", throwable)
        }
    }
}
