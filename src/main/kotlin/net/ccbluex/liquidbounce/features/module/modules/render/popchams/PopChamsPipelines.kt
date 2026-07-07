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
package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.shaders.ShaderSource
import com.mojang.blaze3d.shaders.ShaderType
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.io.resourceToString
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import java.util.Optional

object PopChamsPipelines : ShaderSource {

    private const val NAMESPACE = "liquidbounce"
    private const val BUFFER_SIZE = 8192

    private val simpleVertexId: Identifier =
        Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/popchams_solid")
    private val simpleFragmentId: Identifier =
        Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/popchams_solid")
    private val texturedVertexId: Identifier =
        Identifier.fromNamespaceAndPath(NAMESPACE, "shader/vertex/popchams_textured")
    private val texturedFragmentId: Identifier =
        Identifier.fromNamespaceAndPath(NAMESPACE, "shader/fragment/popchams_textured")

    private val simpleVertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/popchams/popchams_solid.vsh")
    }
    private val simpleFragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/popchams/popchams_solid.fsh")
    }
    private val texturedVertexSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/popchams/popchams_textured.vsh")
    }
    private val texturedFragmentSource: String by lazy {
        resourceToString("/resources/liquidbounce/shaders/popchams/popchams_textured.fsh")
    }

    private val SIMPLE_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        entityPipeline(
            "popchams_simple",
            simpleVertexId,
            simpleFragmentId,
            BlendFunction.TRANSLUCENT,
            textured = false
        )
    }

    private val TEXTURED_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        entityPipeline(
            "popchams_textured",
            texturedVertexId,
            texturedFragmentId,
            BlendFunction.LIGHTNING,
            textured = true
        )
    }

    val simple: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        RenderType.create(
            "popchams_simple",
            RenderSetup.builder(SIMPLE_PIPELINE).bufferSize(BUFFER_SIZE).createRenderSetup()
        )
    }

    private val texturedCache = HashMap<Identifier, RenderType>()

    fun texturedAdditive(texture: Identifier): RenderType = texturedCache.getOrPut(texture) {
        RenderType.create(
            "popchams_textured_additive",
            RenderSetup.builder(TEXTURED_PIPELINE)
                .withTexture("Sampler0", texture)
                .bufferSize(BUFFER_SIZE)
                .createRenderSetup()
        )
    }

    private fun entityPipeline(
        name: String,
        vertexShader: Identifier,
        fragmentShader: Identifier,
        blend: BlendFunction,
        textured: Boolean
    ): RenderPipeline {
        val builder = RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(NAMESPACE, "pipeline/$name"))
            .withVertexShader(vertexShader)
            .withFragmentShader(fragmentShader)
            .withColorTargetState(ColorTargetState(blend))
            .withDepthStencilState(Optional.empty())
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.ENTITY, VertexFormat.Mode.QUADS)

        if (textured) {
            builder.withSampler("Sampler0")
        }

        return builder.build()
    }

    override fun get(identifier: Identifier, type: ShaderType): String? = when (type) {
        ShaderType.FRAGMENT -> when (identifier) {
            simpleFragmentId -> simpleFragmentSource
            texturedFragmentId -> texturedFragmentSource
            else -> null
        }
        ShaderType.VERTEX -> when (identifier) {
            simpleVertexId -> simpleVertexSource
            texturedVertexId -> texturedVertexSource
            else -> null
        }
    }

    fun precompile() {
        runCatching {
            val device = RenderSystem.getDevice()
            device.precompilePipeline(SIMPLE_PIPELINE, this)
            device.precompilePipeline(TEXTURED_PIPELINE, this)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile PopChams pipelines", throwable)
        }
    }
}
