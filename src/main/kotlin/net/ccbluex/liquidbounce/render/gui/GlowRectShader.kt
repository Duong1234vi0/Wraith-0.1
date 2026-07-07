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
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.VertexFormatElement
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier
import java.util.Optional

/**
 * SDF outer-glow rounded-rectangle GUI shader pipeline.
 *
 * Companion to [RoundedRectShader]: same per-vertex packing scheme (HalfSize
 * + RadiusStroke as fixed-point ivec2), but the fragment shader fades the SDF
 * distance OUTWARD into a soft halo instead of filling the interior, and the
 * pipeline uses ADDITIVE blending so overlapping glow layers accumulate into a
 * luminous bloom (the look the Potions frame asked for, with a brightness
 * curve inspired by Complementary-Unbound).
 *
 * Implements [ShaderSource] so [com.mojang.blaze3d.systems.GpuDevice.precompilePipeline]
 * resolves the liquidbounce-namespaced shader ids to the bundled `.fsh`/`.vsh`.
 * Driven by `MixinShaderManager` which calls [precompile] at the TAIL of
 * `ShaderManager.apply`.
 */
object GlowRectShader : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val PIPELINE_PATH = "pipeline/glow_rect"
    private const val FSH_RESOURCE = "/resources/liquidbounce/shaders/glow_rect/glow_rect.fsh"
    private const val VSH_RESOURCE = "/resources/liquidbounce/shaders/glow_rect/glow_rect.vsh"

    private val fragmentId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/glow_rect")
    private val vertexId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/glow_rect")

    private val fragmentSource: String by lazy { resourceToString(FSH_RESOURCE) }
    private val vertexSource: String by lazy { resourceToString(VSH_RESOURCE) }

    /** Same layout as [RoundedRectShader.FORMAT]; reused for the glow path. */
    @JvmField
    val FORMAT: VertexFormat = VertexFormat.builder()
        .add("Position", VertexFormatElement.POSITION)
        .add("UV0", VertexFormatElement.UV0)
        .add("Color", VertexFormatElement.COLOR)
        .add("HalfSize", VertexFormatElement.UV1)
        .add("RadiusStroke", VertexFormatElement.UV2)
        .build()

    @JvmField
    val PIPELINE: RenderPipeline = RenderPipeline.Builder()
        .apply { withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, PIPELINE_PATH)) }
        .apply { withSnippet(RenderPipelines.GUI_SNIPPET) }
        .withVertexShader(vertexId)
        .withFragmentShader(fragmentId)
        .withVertexFormat(FORMAT, VertexFormat.Mode.QUADS)
        .withColorTargetState(ColorTargetState(BlendFunction.ADDITIVE))
        .withDepthStencilState(Optional.empty())
        .build()

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
    }

    /** Force-compiles [PIPELINE] so the first glow draw doesn't stall. */
    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile GlowRect pipeline", throwable)
        }
    }
}
