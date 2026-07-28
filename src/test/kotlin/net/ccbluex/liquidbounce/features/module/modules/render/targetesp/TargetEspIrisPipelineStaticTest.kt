/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO

class TargetEspIrisPipelineStaticTest {

    @Test
    fun `ghost base and late glow use textured additive pipeline while non ghosts retain emissive shader path`() {
        val module = source(MODULE)
        val pipelines = source(PIPELINES)

        assertTrue(module.contains("val pass = if (ghostHybrid) RenderPass.SHADER_REACTIVE_BASE else RenderPass.SHADER_WORLD"))
        assertEquals(1, Regex("RenderTypes\\.entityTranslucentEmissive").findAll(module).count())
        assertTrue(module.contains("if (pass == RenderPass.SHADER_WORLD)"))
        assertTrue(module.contains("BabkaTargetEspPipelines.texturedAdditiveNoDepth(texture)"))
        assertTrue(pipelines.contains("texturedAdditiveNoDepthPipeline to \"TEXTURED\""))
        assertTrue(pipelines.contains("BlendFunction.LIGHTNING"))
        assertTrue(pipelines.contains("DefaultVertexFormat.POSITION_TEX_COLOR"))
        assertFalse(module.contains("EMISSIVE_ENTITIES"))
    }

    @Test
    fun `sprite binding owns vertex layout instead of deriving it from pass name`() {
        val module = source(MODULE)

        assertTrue(module.contains("data class SpriteLayerBinding"))
        assertTrue(module.contains("SpriteVertexLayout.POSITION_TEX_COLOR"))
        assertTrue(module.contains("SpriteVertexLayout.ENTITY_TEXTURED"))
        assertTrue(module.contains("if (vertexLayout == SpriteVertexLayout.ENTITY_TEXTURED)"))
        assertFalse(module.contains("entityTextureFormat"))
        assertFalse(module.contains("pass == RenderPass.SHADER_WORLD\n\n        fun crystalBodyLayer"))
        assertTrue(module.contains("fun endOwnedBatches()"))
        assertTrue(module.contains("ownedLayers.forEach(provider::endBatch)"))
        assertFalse(module.contains("provider.endBatch()"))
    }

    @Test
    fun `ghost rendering is immutable and simulation remains in preparation`() {
        val module = source(MODULE)
        val ghosts2Render = between(module, "private fun renderGhosts2(", "private fun drawGhostSprite(")
        val ghostV2Render = between(module, "private fun renderGhostV2(", "private fun drawGhostV2Layer(")

        assertFalse(ghosts2Render.contains("trail.add"))
        assertFalse(ghosts2Render.contains("removeAt"))
        assertFalse(ghostV2Render.contains(".tick("))
        assertFalse(ghostV2Render.contains("moving.animate"))
        assertTrue(module.contains("prepareGhosts2Frame("))
        assertTrue(module.contains("ghostV2Renderer.prepare(rawTarget, nowMs)"))
        assertTrue(module.contains("particle.tick(fps)"))
        assertTrue(module.contains("when (frame.mode)"))
    }

    @Test
    fun `hybrid lifecycle clears pending state and reload hook is wired`() {
        val module = source(MODULE)
        val shaderManager = source(SHADER_MANAGER)

        assertTrue(module.contains("shaderPassCoordinator.clear()"))
        assertTrue(module.contains("handler<WorldChangeEvent>"))
        assertTrue(module.contains("handler<DisconnectEvent>"))
        assertTrue(module.contains("override fun disable()"))
        assertTrue(module.contains("fun onResourceReload()"))
        assertTrue(shaderManager.contains("ModuleTargetESP.onResourceReload();"))
        assertTrue(module.contains("consumeLate(IrisShaderPack.inUse(), mode)"))
    }

    @Test
    fun `late pass stays in WorldRenderEvent and early hook remains before shaderpack composite`() {
        val module = source(MODULE)
        val worldMixin = source(WORLD_MIXIN)
        val gameMixin = source(GAME_MIXIN)

        assertTrue(module.contains("handler<WorldRenderEvent>"))
        assertTrue(module.contains("RenderPass.LATE_CONTROLLED_GLOW"))
        assertTrue(worldMixin.contains("ModuleTargetESP.renderShaderWorldPass"))
        assertTrue(worldMixin.contains("lambda\$addMainPass\$0"))
        assertTrue(gameMixin.contains("WorldRenderEvent"))
    }

    @Test
    fun `bloom texture is an opaque RGB halo and no QuickProject asset is copied`() {
        val texture = Path.of("src/main/resources/assets/liquidbounce/textures/targetesp/babka/bloom.png")
        val image = ImageIO.read(texture.toFile())
        assertEquals(256, image.width)
        assertEquals(256, image.height)

        var minAlpha = 255
        var maxAlpha = 0
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val alpha = image.getRGB(x, y) ushr 24 and 255
                minAlpha = minOf(minAlpha, alpha)
                maxAlpha = maxOf(maxAlpha, alpha)
            }
        }
        assertEquals(255, minAlpha)
        assertEquals(255, maxAlpha)
        assertFalse(Files.exists(Path.of("src/main/resources/assets/liquidbounce/textures/targetesp/orbiznew.png")))
    }

    @Test
    fun `production adds no shaderpack patch framebuffer blur or unbounded diagnostics`() {
        val sources = Files.walk(Path.of(TARGETESP_DIR)).use { paths ->
            paths.filter { Files.isRegularFile(it) }.map(Files::readString).toList().joinToString("\n")
        }

        assertFalse(sources.contains("BLOOM_STRENGTH"))
        assertFalse(sources.contains("Complementary"))
        assertFalse(sources.contains("QuickProject"))
        assertFalse(sources.contains("orbiznew"))
        assertFalse(sources.contains("RenderTarget("))
        assertFalse(sources.contains("Kawase"))
        assertFalse(sources.contains("Gaussian"))
        assertFalse(sources.contains("[TargetEspShaderDiag]"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun between(source: String, start: String, end: String): String =
        source.substringAfter(start).substringBefore(end)

    private companion object {
        const val TARGETESP_DIR =
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/targetesp"
        const val MODULE = "$TARGETESP_DIR/ModuleTargetESP.kt"
        const val PIPELINES = "$TARGETESP_DIR/BabkaTargetEspPipelines.kt"
        const val WORLD_MIXIN =
            "src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinWorldRenderer.java"
        const val GAME_MIXIN =
            "src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinGameRenderer.java"
        const val SHADER_MANAGER =
            "src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinShaderManager.java"
    }
}
