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
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.CompareOp
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.LiquidBounce
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import java.lang.reflect.InvocationTargetException
import java.util.Collections
import java.util.IdentityHashMap
import java.util.Optional

object BabkaTargetEspPipelines {

    private const val BUFFER_SIZE = 32768
    private const val NAMESPACE = "liquidbounce"

    private val noDepth = Optional.empty<DepthStencilState>()
    private val depthNoWrite = Optional.of(
        DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false)
    )

    private val texturedAdditiveNoDepthPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturedPipeline("targetesp_babka_textured_additive_no_depth", noDepth)
    }

    private val texturedAdditiveDepthPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturedPipeline("targetesp_babka_textured_additive_depth", depthNoWrite)
    }

    private val trianglesTranslucentNoDepthPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline(
            "targetesp_babka_triangles_translucent_no_depth",
            VertexFormat.Mode.TRIANGLES,
            BlendFunction.TRANSLUCENT,
            noDepth
        )
    }

    private val cubeLinesDepthPipeline by lazy(LazyThreadSafetyMode.NONE) {
        linePipeline(
            "targetesp_babka_cube_lines_depth",
            BlendFunction.LIGHTNING,
            depthNoWrite
        )
    }

    private val cubeFacesDepthPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline(
            "targetesp_babka_cube_faces_depth",
            VertexFormat.Mode.QUADS,
            BlendFunction.LIGHTNING,
            depthNoWrite
        )
    }

    private val texturedAdditiveNoDepthCache = HashMap<Identifier, RenderType>()
    private val texturedAdditiveDepthCache = HashMap<Identifier, RenderType>()

    val trianglesTranslucentNoDepth: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        renderType("targetesp_babka_triangles_translucent_no_depth", trianglesTranslucentNoDepthPipeline)
    }

    val cubeLinesDepth: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        renderType("targetesp_babka_cube_lines_depth", cubeLinesDepthPipeline)
    }

    val cubeFacesDepth: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        renderType("targetesp_babka_cube_faces_depth", cubeFacesDepthPipeline)
    }

    fun texturedAdditiveNoDepth(texture: Identifier): RenderType =
        texturedAdditiveNoDepthCache.getOrPut(texture) {
            texturedRenderType("targetesp_babka_textured_additive_no_depth", texturedAdditiveNoDepthPipeline, texture)
        }

    fun texturedAdditiveDepth(texture: Identifier): RenderType =
        texturedAdditiveDepthCache.getOrPut(texture) {
            texturedRenderType("targetesp_babka_textured_additive_depth", texturedAdditiveDepthPipeline, texture)
        }

    fun clearTextureCache() {
        texturedAdditiveNoDepthCache.clear()
        texturedAdditiveDepthCache.clear()
    }

    fun irisMappingsAvailable(): Boolean =
        TargetEspIrisPipelineCompat.assignRequired(
            arrayOf(
                trianglesTranslucentNoDepthPipeline to "BASIC",
                cubeFacesDepthPipeline to "BASIC",
                cubeLinesDepthPipeline to "LINES",
                texturedAdditiveNoDepthPipeline to "TEXTURED",
                texturedAdditiveDepthPipeline to "TEXTURED"
            )
        )

    fun precompile() {
        irisMappingsAvailable()
        runCatching {
            val device = RenderSystem.getDevice()
            device.precompilePipeline(trianglesTranslucentNoDepthPipeline)
            device.precompilePipeline(cubeFacesDepthPipeline)
            device.precompilePipeline(cubeLinesDepthPipeline)
            device.precompilePipeline(texturedAdditiveNoDepthPipeline)
            device.precompilePipeline(texturedAdditiveDepthPipeline)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile Babka TargetESP pipelines", throwable)
        }
    }

    private fun texturedPipeline(name: String, depth: Optional<DepthStencilState>) =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id("pipeline/$name"))
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
            .withDepthStencilState(depth)
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()

    private fun colorPipeline(
        name: String,
        mode: VertexFormat.Mode,
        blend: BlendFunction,
        depth: Optional<DepthStencilState>
    ) =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id("pipeline/$name"))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(blend))
            .withDepthStencilState(depth)
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, mode)
            .build()

    private fun linePipeline(
        name: String,
        blend: BlendFunction,
        depth: Optional<DepthStencilState>
    ) =
        RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
            .withLocation(id("pipeline/$name"))
            .withColorTargetState(ColorTargetState(blend))
            .withDepthStencilState(depth)
            .withCull(false)
            .build()

    private fun renderType(name: String, pipeline: com.mojang.blaze3d.pipeline.RenderPipeline): RenderType =
        RenderType.create(
            name,
            RenderSetup.builder(pipeline)
                .bufferSize(BUFFER_SIZE)
                .createRenderSetup()
        )

    private fun texturedRenderType(
        name: String,
        pipeline: com.mojang.blaze3d.pipeline.RenderPipeline,
        texture: Identifier
    ): RenderType =
        RenderType.create(
            name,
            RenderSetup.builder(pipeline)
                .withTexture("Sampler0", texture)
                .bufferSize(BUFFER_SIZE)
                .createRenderSetup()
        )

    private fun id(path: String) = Identifier.fromNamespaceAndPath(NAMESPACE, path)
}

private object TargetEspIrisPipelineCompat {

    private val assigned = Collections.newSetFromMap(IdentityHashMap<RenderPipeline, Boolean>())

    private var apiResolved = false
    private var apiInstance: Any? = null
    private var assignPipeline: java.lang.reflect.Method? = null
    private var programs: Map<String, Any> = emptyMap()
    private var failureLogged = false

    fun assignRequired(mappings: Array<Pair<RenderPipeline, String>>): Boolean {
        if (!resolveApi()) {
            return false
        }

        val api = apiInstance ?: return false
        val method = assignPipeline ?: return false
        var success = true

        for ((pipeline, programName) in mappings) {
            if (pipeline in assigned) {
                continue
            }

            val program = programs[programName]
            if (program == null) {
                logFailure(IllegalStateException("IrisProgram.$programName is unavailable"))
                success = false
                continue
            }

            runCatching {
                method.invoke(api, pipeline, program)
                assigned += pipeline
            }.onFailure { throwable ->
                logFailure(throwable.unwrapInvocationTarget())
                success = false
            }
        }

        return success && mappings.all { (pipeline, _) -> pipeline in assigned }
    }

    private fun resolveApi(): Boolean {
        if (apiResolved) {
            return apiInstance != null && assignPipeline != null
        }

        apiResolved = true
        runCatching {
            val apiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi")
            val programClass = Class.forName("net.irisshaders.iris.api.v0.IrisProgram")
            apiInstance = apiClass.getMethod("getInstance").invoke(null)
            assignPipeline = apiClass.getMethod("assignPipeline", RenderPipeline::class.java, programClass)
            programs = programClass.enumConstants
                .filterIsInstance<Enum<*>>()
                .associateBy { it.name }
        }.onFailure {
            apiInstance = null
            assignPipeline = null
            programs = emptyMap()
        }

        return apiInstance != null && assignPipeline != null
    }

    private fun logFailure(throwable: Throwable) {
        if (!failureLogged) {
            failureLogged = true
            LiquidBounce.logger.error("Failed to assign Babka TargetESP pipelines to Iris programs", throwable)
        }
    }

    private fun Throwable.unwrapInvocationTarget(): Throwable =
        if (this is InvocationTargetException) {
            targetException ?: cause ?: this
        } else {
            this
        }
}
