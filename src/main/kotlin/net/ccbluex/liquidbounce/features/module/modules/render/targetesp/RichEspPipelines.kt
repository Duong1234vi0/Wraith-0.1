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
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import java.util.Optional

/**
 * Port of Rich-Modern's `rich.util.render.сliemtpipeline.ClientPipelines`
 * (the subset used by TargetESP).
 *
 * #Edit By Claude 2026-06-12: ported 1:1 onto 1.26.2 / Mojang mappings.
 *
 * KEY DESIGN — why this keeps Rich's own pipelines instead of the fork's
 * compat render path:
 * Rich bakes the blend mode (additive [BlendFunction.LIGHTNING]) straight
 * into each [RenderPipeline] and draws through the *vanilla* entity buffer
 * source ([net.minecraft.client.renderer.MultiBufferSource.BufferSource]).
 * The fork's `RenderCompat.blendFunc(...)` is a NO-OP, so any module that
 * relied on `RenderSystem.blendFunc(SRC_ALPHA, ONE)` for additive glow got
 * dull/black geometry (see the long TargetESP saga in Agent1.md). By owning
 * the pipeline we sidestep that entirely — the GPU gets the right blend.
 *
 * 1.21.11 → 1.26.2 API mapping:
 *  - `RenderPipeline.builder(TRANSFORMS_AND_PROJECTION_SNIPPET)` →
 *    `RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)`
 *    (vanilla `core/position*` shaders only read DynamicTransforms +
 *    Projection UBOs — no Fog UBO — so this snippet is correct).
 *  - `.withBlend(BlendFunction.X)` → `.withColorTargetState(ColorTargetState(X))`.
 *  - `.withDepthTestFunction(NO_DEPTH_TEST)` → `.withDepthStencilState(Optional.empty())`.
 *  - `.withDepthTestFunction(LESS/LEQUAL) + .withDepthWrite(false)` →
 *    `.withDepthStencilState(DepthStencilState(CompareOp.X, /*writeDepth*/ false))`.
 *  - `RenderLayer.of(name, RenderSetup)` → `RenderType.create(name, RenderSetup)`
 *    (accesswidened). `.expectedBufferSize` → `.bufferSize`, `.build()` →
 *    `.createRenderSetup()`. Rich's `.translucent()` has no 1.26.2 equivalent
 *    on the builder and is unnecessary for additive blending — dropped.
 *
 * Pipelines compile lazily on first `setPipeline` during `endBatch`, resolving
 * the vanilla shaders from the resource manager, so no precompile hook is
 * needed (unlike the custom-GLSL RoundedRect/GlowRect pipelines).
 */
@Suppress("MagicNumber")
object RichEspPipelines {

    private fun id(path: String): Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/$path")

    private fun texturePipeline(name: String, depth: Optional<DepthStencilState>): RenderPipeline =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id(name))
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
            .withDepthStencilState(depth)
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .build()

    private fun colorPipeline(name: String, blend: BlendFunction, depth: Optional<DepthStencilState>): RenderPipeline =
        RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(id(name))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(blend))
            .withDepthStencilState(depth)
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS)
            .build()

    // #Edit By Codex 2026-06-13: build pipelines lazily per mode so one broken TargetESP
    // pipeline on FCL/Android cannot fail the whole RichEspPipelines object initializer.
    // Rhomb — no depth test (always-on-top diamond).
    private val ROMB_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturePipeline("romb_esp", Optional.empty())
    }

    // Ghost — LESS depth test, no depth write.
    private val GHOSTS_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturePipeline("ghosts_esp", Optional.of(DepthStencilState(CompareOp.LESS_THAN, false)))
    }

    // Chain — LEQUAL depth test, no depth write.
    private val CHAIN_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturePipeline("chain_esp", Optional.of(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false)))
    }

    // #Edit By Claude 2026-06-16: Wonderful's crystal pass calls disableDepthTest (always-on-top
    // glow), so the bloom billboards now use no depth state instead of LEQUAL.
    // Bloom (crystal halo) — no depth test, additive.
    private val BLOOM_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        texturePipeline("bloom_esp", Optional.empty())
    }

    // #Edit By Claude 2026-06-16: no depth test to match Wonderful's disableDepthTest crystal pass.
    // Crystal body — translucent fill, no depth test.
    private val CRYSTAL_FILLED_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline("crystal_filled", BlendFunction.TRANSLUCENT, Optional.empty())
    }

    // Crystal glow shell — additive.
    private val CRYSTAL_GLOW_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline("crystal_glow", BlendFunction.LIGHTNING,
            Optional.of(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false)))
    }

    // Circle ribbon — additive, no depth test (always-on-top twin-band ring).
    private val CIRCLE_PIPELINE: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
        colorPipeline("circle_esp", BlendFunction.LIGHTNING, Optional.empty())
    }

    private val romb = HashMap<Identifier, RenderType>()
    private val ghosts = HashMap<Identifier, RenderType>()
    private val chain = HashMap<Identifier, RenderType>()
    private val bloom = HashMap<Identifier, RenderType>()

    private fun texturedLayer(
        cache: HashMap<Identifier, RenderType>,
        pipeline: RenderPipeline,
        name: String,
        texture: Identifier,
        bufferSize: Int
    ): RenderType = cache.getOrPut(texture) {
        val setup = RenderSetup.builder(pipeline)
            .withTexture("Sampler0", texture)
            .bufferSize(bufferSize)
            .createRenderSetup()
        RenderType.create(name, setup)
    }

    fun romb(texture: Identifier): RenderType = texturedLayer(romb, ROMB_PIPELINE, "rich_romb_esp", texture, 1536)

    fun ghosts(texture: Identifier): RenderType =
        texturedLayer(ghosts, GHOSTS_PIPELINE, "rich_ghosts_esp", texture, 1536)

    fun chain(texture: Identifier): RenderType = texturedLayer(chain, CHAIN_PIPELINE, "rich_chain_esp", texture, 1536)

    fun bloom(texture: Identifier): RenderType = texturedLayer(bloom, BLOOM_PIPELINE, "rich_bloom_esp", texture, 2048)

    val crystalFilled: RenderType by lazy {
        RenderType.create(
            "rich_crystal_filled",
            RenderSetup.builder(CRYSTAL_FILLED_PIPELINE).bufferSize(8192).createRenderSetup()
        )
    }

    val crystalGlow: RenderType by lazy {
        RenderType.create(
            "rich_crystal_glow",
            RenderSetup.builder(CRYSTAL_GLOW_PIPELINE).bufferSize(4096).createRenderSetup()
        )
    }

    val circleQuads: RenderType by lazy {
        RenderType.create(
            "rich_circle_esp",
            RenderSetup.builder(CIRCLE_PIPELINE).bufferSize(8192).createRenderSetup()
        )
    }
}
