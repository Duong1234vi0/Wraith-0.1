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
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TargetEspShaderPassCoordinatorTest {

    private data class Frame(
        val serial: Long,
        val mode: String,
        val target: String,
        val partialTick: Float = 0.35F,
        val camera: String = "camera-snapshot",
        val accentColor: Int = 0x55AAFF,
        val animation: Float = 0.75F,
        val trailRevision: Int = 4,
        val particleRevision: Int = 6
    )

    @Test
    fun `shader inactive ghosts prepare one normal late pass`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()

        assertTrue(coordinator.consumeLate(shaderActive = false, currentMode = "Ghosts2") is TargetEspLatePlan.PrepareNormal)
        assertEquals(TargetEspShaderFrameState.NONE, coordinator.state)
    }

    @Test
    fun `shader active ghosts consume one controlled late pass from the same frame`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        val frame = Frame(7L, "Ghost V2", "target-a")
        coordinator.beginEarlyFrame()
        coordinator.ghostBaseRendered(frame, frame.mode)

        val plan = coordinator.consumeLate(shaderActive = true, currentMode = frame.mode)
        assertTrue(plan is TargetEspLatePlan.RenderControlledGlow)
        val consumed = (plan as TargetEspLatePlan.RenderControlledGlow).frame
        assertSame(frame, consumed)
        assertEquals(frame.serial, consumed.serial)
        assertEquals(frame.mode, consumed.mode)
        assertEquals(frame.target, consumed.target)
        assertEquals(frame.partialTick, consumed.partialTick)
        assertEquals(frame.camera, consumed.camera)
        assertEquals(frame.accentColor, consumed.accentColor)
        assertEquals(frame.animation, consumed.animation)
        assertEquals(frame.trailRevision, consumed.trailRevision)
        assertEquals(frame.particleRevision, consumed.particleRevision)
        assertEquals(TargetEspShaderFrameState.NONE, coordinator.state)
    }

    @Test
    fun `shader active non ghost remains one full shader-world pass`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        coordinator.beginEarlyFrame()
        coordinator.fullShaderPassRendered()

        assertTrue(coordinator.consumeLate(true, "Crystals") is TargetEspLatePlan.Skip)
    }

    @Test
    fun `mapping unavailable prepares one complete normal fallback`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        coordinator.beginEarlyFrame()

        assertTrue(coordinator.consumeLate(true, "Ghosts2") is TargetEspLatePlan.PrepareNormal)
    }

    @Test
    fun `early failure reuses prepared frame for one normal fallback`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        val frame = Frame(9L, "Ghosts2", "target-b")
        var preparationCount = 0
        preparationCount++
        coordinator.normalFallbackPending(frame, frame.mode)

        val plan = coordinator.consumeLate(true, frame.mode)
        assertTrue(plan is TargetEspLatePlan.RenderNormal)
        assertSame(frame, (plan as TargetEspLatePlan.RenderNormal).frame)
        assertEquals(1, preparationCount)
    }

    @Test
    fun `hybrid simulation preparation happens exactly once`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        var targetTransitions = 0
        var animationAdvances = 0
        var trailInsertions = 0
        var particleTicks = 0

        fun prepare(): Frame {
            targetTransitions++
            animationAdvances++
            trailInsertions++
            particleTicks++
            return Frame(13L, "Ghost V2", "target-f")
        }

        val frame = prepare()
        coordinator.ghostBaseRendered(frame, frame.mode)
        assertTrue(coordinator.consumeLate(true, frame.mode) is TargetEspLatePlan.RenderControlledGlow)
        assertEquals(1, targetTransitions)
        assertEquals(1, animationAdvances)
        assertEquals(1, trailInsertions)
        assertEquals(1, particleTicks)
    }

    @Test
    fun `late consumption clears ownership before rendering can fail`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        coordinator.ghostBaseRendered(Frame(10L, "Ghost V2", "target-c"), "Ghost V2")

        assertTrue(coordinator.consumeLate(true, "Ghost V2") is TargetEspLatePlan.RenderControlledGlow)
        assertEquals(TargetEspShaderFrameState.NONE, coordinator.state)
    }

    @Test
    fun `live mode mutation cannot combine different ghost modes`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        coordinator.ghostBaseRendered(Frame(11L, "Ghosts2", "target-d"), "Ghosts2")

        assertTrue(coordinator.consumeLate(true, "Ghost V2") is TargetEspLatePlan.Skip)
        assertEquals(TargetEspShaderFrameState.NONE, coordinator.state)
    }

    @Test
    fun `shader disable and lifecycle clears discard pending hybrid frame`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        val frame = Frame(12L, "Ghosts2", "target-e")
        coordinator.ghostBaseRendered(frame, frame.mode)
        assertTrue(coordinator.consumeLate(false, frame.mode) is TargetEspLatePlan.Skip)

        repeat(7) {
            coordinator.ghostBaseRendered(frame, frame.mode)
            coordinator.clear()
            assertEquals(TargetEspShaderFrameState.NONE, coordinator.state)
        }
    }

    @Test
    fun `newer early frame replaces an unconsumed frame`() {
        val coordinator = TargetEspShaderPassCoordinator<Frame, String>()
        coordinator.ghostBaseRendered(Frame(20L, "Ghosts2", "old"), "Ghosts2")
        coordinator.beginEarlyFrame()
        val newer = Frame(21L, "Ghosts2", "new")
        coordinator.ghostBaseRendered(newer, newer.mode)

        val plan = coordinator.consumeLate(true, newer.mode) as TargetEspLatePlan.RenderControlledGlow
        assertSame(newer, plan.frame)
    }
}
