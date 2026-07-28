/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

internal enum class TargetEspShaderFrameState {
    NONE,
    GHOST_BASE_RENDERED_PENDING_LATE,
    FULL_SHADER_PASS_RENDERED,
    NORMAL_FALLBACK_PENDING
}

internal sealed interface TargetEspLatePlan<out Frame> {
    data object PrepareNormal : TargetEspLatePlan<Nothing>
    data object Skip : TargetEspLatePlan<Nothing>
    data class RenderNormal<Frame>(val frame: Frame) : TargetEspLatePlan<Frame>
    data class RenderControlledGlow<Frame>(val frame: Frame) : TargetEspLatePlan<Frame>
}

/** Owns at most one frame between the early world hook and the late world event. */
internal class TargetEspShaderPassCoordinator<Frame, Mode> {
    private data class Pending<Frame, Mode>(val frame: Frame, val mode: Mode)

    var state = TargetEspShaderFrameState.NONE
        private set

    private var pending: Pending<Frame, Mode>? = null

    /** A newer early hook deterministically invalidates any unconsumed older frame. */
    fun beginEarlyFrame() {
        clear()
    }

    fun ghostBaseRendered(frame: Frame, mode: Mode) {
        pending = Pending(frame, mode)
        state = TargetEspShaderFrameState.GHOST_BASE_RENDERED_PENDING_LATE
    }

    fun fullShaderPassRendered() {
        pending = null
        state = TargetEspShaderFrameState.FULL_SHADER_PASS_RENDERED
    }

    fun normalFallbackPending(frame: Frame, mode: Mode) {
        pending = Pending(frame, mode)
        state = TargetEspShaderFrameState.NORMAL_FALLBACK_PENDING
    }

    fun consumeLate(shaderActive: Boolean, currentMode: Mode): TargetEspLatePlan<Frame> {
        val oldState = state
        val oldPending = pending
        clear()

        return when (oldState) {
            TargetEspShaderFrameState.NONE -> TargetEspLatePlan.PrepareNormal
            TargetEspShaderFrameState.FULL_SHADER_PASS_RENDERED -> TargetEspLatePlan.Skip
            TargetEspShaderFrameState.NORMAL_FALLBACK_PENDING -> {
                if (oldPending?.mode == currentMode) {
                    TargetEspLatePlan.RenderNormal(oldPending.frame)
                } else {
                    TargetEspLatePlan.Skip
                }
            }
            TargetEspShaderFrameState.GHOST_BASE_RENDERED_PENDING_LATE -> {
                if (shaderActive && oldPending?.mode == currentMode) {
                    TargetEspLatePlan.RenderControlledGlow(oldPending.frame)
                } else {
                    TargetEspLatePlan.Skip
                }
            }
        }
    }

    fun clear() {
        pending = null
        state = TargetEspShaderFrameState.NONE
    }
}
