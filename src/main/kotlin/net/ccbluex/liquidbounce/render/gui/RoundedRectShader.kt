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
 * SDF rounded-rectangle GUI shader pipeline.
 *
 * Implements [ShaderSource] so the [GpuDevice.precompilePipeline] path
 * resolves liquidbounce-namespaced shader ids to bundled `.fsh`/`.vsh`
 * sources at compile time. Driven by [MixinShaderManager] which calls
 * [precompile] at the TAIL of `ShaderManager.apply` (i.e. on every
 * resource reload, including the initial one).
 *
 * Per-rectangle parameters (half-extents, corner radius, stroke width)
 * are encoded into per-vertex `ivec2` attributes (fixed-point px*16) so
 * the same pipeline draws fill / outline / gradient rectangles with one
 * batched flush from vanilla's GUI renderer — no per-element UBO needed.
 */
object RoundedRectShader : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val PIPELINE_PATH = "pipeline/rounded_rect"
    private const val FSH_RESOURCE = "/resources/liquidbounce/shaders/rounded_rect/rounded_rect.fsh"
    private const val VSH_RESOURCE = "/resources/liquidbounce/shaders/rounded_rect/rounded_rect.vsh"

    private val fragmentId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/rounded_rect")
    private val vertexId: Identifier = Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/rounded_rect")

    private val fragmentSource: String by lazy { resourceToString(FSH_RESOURCE) }
    private val vertexSource: String by lazy { resourceToString(VSH_RESOURCE) }

    /**
     * Per-vertex format. Position + UV0 + Color carry the standard quad
     * geometry. The two `ivec2` attributes carry the SDF parameters in
     * fixed-point pixels (raw int / 16.0 = float pixels):
     *
     *  - `HalfSize` ([Short.x], [Short.y]) — half-extents of the
     *    rectangle in pixel space (max ~2048px each side).
     *  - `RadiusStroke` ([Short.x], [Short.y]) — corner radius and
     *    stroke width in pixel space (`stroke == 0` ⇒ filled rect).
     */
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
        .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
        .withDepthStencilState(Optional.empty())
        .build()

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.FRAGMENT -> if (identifier == fragmentId) fragmentSource else null
        ShaderType.VERTEX -> if (identifier == vertexId) vertexSource else null
    }

    /**
     * Force-compiles [PIPELINE] against this shader source so the very
     * first draw call doesn't stall the render thread. Called by
     * `MixinShaderManager` after vanilla finishes its own shader pass.
     */
    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile RoundedRect pipeline", throwable)
        }
    }
}
