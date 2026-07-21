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
package net.ccbluex.liquidbounce.features.module.modules.render.totempop

import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.world.entity.player.PlayerModelType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class TotemPopStateTest {

    @Test
    fun `source settings and lifetime math are exact`() {
        assertEquals(700, TotemPopState.DEFAULT_DURATION)
        assertEquals(250, TotemPopState.MIN_DURATION)
        assertEquals(2_000, TotemPopState.MAX_DURATION)
        assertEquals(50, TotemPopState.DURATION_STEP)
        assertClose(2.4f, TotemPopState.DEFAULT_RISE)
        assertClose(0f, TotemPopState.MIN_RISE)
        assertClose(6f, TotemPopState.MAX_RISE)
        assertClose(0.1f, TotemPopState.RISE_STEP)

        assertClose(0f, TotemPopState.progress(1_000L, 1_000L, 700))
        assertClose(0.5f, TotemPopState.progress(1_350L, 1_000L, 700))
        assertClose(0.7f, TotemPopState.alpha(0f))
        assertClose(0.35f, TotemPopState.alpha(0.5f))
        assertClose(0f, TotemPopState.alpha(1f))
        assertClose(11.2, TotemPopState.renderedY(10.0, 2.4f, 0.5f))
        assertFalse(TotemPopState.expired(0.999f))
        assertTrue(TotemPopState.expired(1f))
    }

    @Test
    fun `wide and slim models use the vanilla avatar layers`() {
        assertSame(ModelLayers.PLAYER, TotemPopState.modelLayer(PlayerModelType.WIDE))
        assertFalse(TotemPopState.slim(PlayerModelType.WIDE))
        assertSame(ModelLayers.PLAYER_SLIM, TotemPopState.modelLayer(PlayerModelType.SLIM))
        assertTrue(TotemPopState.slim(PlayerModelType.SLIM))
    }

    @Test
    fun `scoped render parameters are restored after success and exception`() {
        assertNull(TotemPopRenderScope.current)
        val outer = TotemPopRenderParameters(0.7f, 0x123456)
        val inner = TotemPopRenderParameters(0.2f, 0x654321)
        TotemPopRenderScope.withGhost(outer) {
            assertEquals(outer, TotemPopRenderScope.current)
            TotemPopRenderScope.withGhost(inner) { assertEquals(inner, TotemPopRenderScope.current) }
            assertEquals(outer, TotemPopRenderScope.current)
        }
        assertNull(TotemPopRenderScope.current)

        assertThrows(IllegalStateException::class.java) {
            TotemPopRenderScope.withGhost(outer) { error("render failure") }
        }
        assertNull(TotemPopRenderScope.current)
    }

    @Test
    fun `temporary pose restoration runs after success and render exception`() {
        var state = 4
        val success = withRestoredState(
            capture = { state },
            apply = { state = 9 },
            restore = { state = it },
        ) {
            assertEquals(9, state)
            "done"
        }
        assertEquals("done", success)
        assertEquals(4, state)

        assertThrows(IllegalArgumentException::class.java) {
            withRestoredState(
                capture = { state },
                apply = { state = 12 },
                restore = { state = it },
            ) {
                throw IllegalArgumentException("model failure")
            }
        }
        assertEquals(4, state)
    }

    @Test
    fun `module reuses TotemPopEvent captures every pop and filters only self`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/totempop/ModuleTotemPop.kt")
        assertTrue(module.contains("handler<TotemPopEvent>"))
        assertTrue(module.contains("if (!self && entity === mc.player)"))
        assertTrue(module.contains("entries += PopEntry.capture"))
        assertFalse(module.contains("associateBy"))
        assertFalse(module.contains("putIfAbsent"))
        listOf(
            "entity.x,",
            "entity.y,",
            "entity.z,",
            "entity.yBodyRot",
            "entity.yHeadRot",
            "entity.xRot",
            "entity.walkAnimation.position()",
            "entity.walkAnimation.speed()",
        ).forEach { assertTrue(module.contains(it), it) }
        assertTrue(module.contains("handler<WorldChangeEvent> { clearEntries() }"))
        assertTrue(module.contains("handler<DisconnectEvent> { clearEntries() }"))
    }

    @Test
    fun `renderer is source constrained full bright and does not render held items`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/totempop/ModuleTotemPop.kt")
        assertTrue(module.contains("RenderTypes.entityTranslucentEmissive(entry.texture, false)"))
        assertTrue(module.contains("TotemPopState.FULL_BRIGHT_LIGHT"))
        assertTrue(module.contains("ClientTheme.accentA"))
        assertTrue(module.contains("entry.model.renderToBuffer"))
        assertFalse(module.contains("dispatcher.render"))
        assertFalse(module.contains("HeldItem"))
        assertFalse(module.contains("renderShaderWorldPass"))
        assertTrue(module.contains("renderer.getRenderOffset(state)"))
        assertTrue(module.contains("matrices.scale(-1f, -1f, 1f)"))
        assertTrue(module.contains("TotemPopState.AVATAR_SCALE * state.scale"))
        assertTrue(module.contains("TotemPopState.VANILLA_MODEL_Y"))
        assertFalse(module.contains("1.6f, 1.8f, 1.6f"))
        assertFalse(module.contains("MODEL_ROOT_SCALE_OFFSET"))
    }

    @Test
    fun `PopChams is gone and event producers remain registered`() {
        val mainRoot = Path.of("src/main")
        val allSource = Files.walk(mainRoot).use { stream ->
            stream.filter { Files.isRegularFile(it) && (it.toString().endsWith(".kt") || it.toString().endsWith(".java")) }
                .toList()
                .joinToString("\n") { Files.readString(it) }
        }
        assertFalse(allSource.contains("ModulePopChams"))
        assertFalse(allSource.contains("PopChamsPipelines"))
        assertFalse(Files.exists(Path.of("src/main/resources/resources/liquidbounce/popchams")))
        assertFalse(Files.exists(Path.of("src/main/resources/resources/liquidbounce/shaders/popchams")))

        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt")
        assertEquals(1, Regex("ModuleTotemPop,").findAll(manager).count())
        assertFalse(manager.contains("ModulePopChams"))
        val combat = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/combat/CombatManager.kt")
        val fake = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/fakeplayer/ModuleFakePlayer.kt")
        assertTrue(combat.contains("EventManager.callEvent(TotemPopEvent(entity, pops))"))
        assertTrue(fake.contains("EventManager.callEvent(TotemPopEvent(clone, pops))"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.0001f) { "expected <$expected> but was <$actual>" }
    }

    private fun assertClose(expected: Double, actual: Double) {
        assertTrue(abs(expected - actual) < 0.0001) { "expected <$expected> but was <$actual>" }
    }
}
