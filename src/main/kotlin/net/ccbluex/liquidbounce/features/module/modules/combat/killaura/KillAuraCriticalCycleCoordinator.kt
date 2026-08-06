/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.combat.killaura

import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode

/**
 * Owns the real KillAura manual-jump lifecycle. A physical key level is only the intent signal;
 * the cycle remains active until the jump has gone airborne and landed again.
 */
internal class KillAuraCriticalCycleCoordinator {

    var manualJumpCycleActive = false
        private set
    var pendingReadyClick = false
        private set
    var cycleSerial = 0L
        private set
    var physicalJumpHeld = false
        private set
    var transformedJump = false
        private set
    var serverProvenFalling = false
        private set
    var pipelineInProgress = false
        private set

    private var observedAirborne = false
    private var lastPhysicalJumpHeld = false
    private var playerIdentity: Any? = null
    private var worldIdentity: Any? = null
    private var targetIdentity: Any? = null

    fun synchronizeScope(
        running: Boolean,
        playerIdentity: Any?,
        worldIdentity: Any?,
        targetIdentity: Any?,
    ) {
        val valid = running && playerIdentity != null && worldIdentity != null && targetIdentity != null
        val changed = this.playerIdentity !== playerIdentity || this.worldIdentity !== worldIdentity ||
            this.targetIdentity !== targetIdentity

        if (!valid || changed) {
            reset()
        }

        if (valid) {
            this.playerIdentity = playerIdentity
            this.worldIdentity = worldIdentity
            this.targetIdentity = targetIdentity
        }
    }

    fun observeInput(
        mode: CriticalsSelectionMode,
        physicalJumpHeld: Boolean,
        transformedJump: Boolean,
        onGround: Boolean,
        naturalCriticalEnvironmentPossible: Boolean,
        gliding: Boolean,
    ) {
        this.physicalJumpHeld = physicalJumpHeld
        this.transformedJump = transformedJump

        val smartManualIntent = mode == CriticalsSelectionMode.SMART && physicalJumpHeld && transformedJump &&
            naturalCriticalEnvironmentPossible && !gliding

        if (mode != CriticalsSelectionMode.SMART || !naturalCriticalEnvironmentPossible || gliding) {
            clearCycle(clearPendingClick = true)
        } else if (manualJumpCycleActive && onGround && !observedAirborne && !smartManualIntent) {
            // The final transformed input cancelled the jump before an airborne transition.
            clearCycle(clearPendingClick = true)
        } else if (!manualJumpCycleActive && smartManualIntent && (onGround || !lastPhysicalJumpHeld)) {
            manualJumpCycleActive = true
            observedAirborne = !onGround
            serverProvenFalling = false
            cycleSerial++
        }

        lastPhysicalJumpHeld = physicalJumpHeld
    }

    /** Called only after LocalPlayer.sendPosition has returned. */
    fun observePostMovement(serverOnGround: Boolean, fallDistance: Float) {
        serverProvenFalling = !serverOnGround && fallDistance > 0.0f

        if (!manualJumpCycleActive) {
            return
        }

        if (!serverOnGround) {
            observedAirborne = true
            return
        }

        if (observedAirborne) {
            // Keep a ready click only while the held key can immediately begin the next bunny-hop cycle.
            clearCycle(clearPendingClick = !physicalJumpHeld)
        }
    }

    fun noteClickReadiness(mode: CriticalsSelectionMode, clickReady: Boolean) {
        if (clickReady && (mode == CriticalsSelectionMode.ALWAYS || manualJumpCycleActive)) {
            pendingReadyClick = true
        }
    }

    fun shouldDeferGameTick(
        mode: CriticalsSelectionMode,
        physicalManualIntentAwaitingInput: Boolean = false,
    ): Boolean = mode == CriticalsSelectionMode.SMART &&
        (manualJumpCycleActive || physicalManualIntentAwaitingInput)

    fun shouldAttemptPostMovement(mode: CriticalsSelectionMode): Boolean = pendingReadyClick &&
        !pipelineInProgress && serverProvenFalling &&
        (mode == CriticalsSelectionMode.ALWAYS || mode == CriticalsSelectionMode.SMART && manualJumpCycleActive)

    fun hasAttackClick(schedulerReady: Boolean, postMovementBoundary: Boolean): Boolean =
        schedulerReady || postMovementBoundary && pendingReadyClick

    fun beginPipeline(): Boolean {
        if (pipelineInProgress) {
            return false
        }

        pipelineInProgress = true
        return true
    }

    fun finishPipeline(clickConsumed: Boolean) {
        pipelineInProgress = false
        if (clickConsumed) {
            pendingReadyClick = false
        }
    }

    fun markClickConsumed() {
        pendingReadyClick = false
    }

    fun reset() {
        clearCycle(clearPendingClick = true)
        pipelineInProgress = false
        physicalJumpHeld = false
        transformedJump = false
        lastPhysicalJumpHeld = false
        playerIdentity = null
        worldIdentity = null
        targetIdentity = null
    }

    private fun clearCycle(clearPendingClick: Boolean) {
        manualJumpCycleActive = false
        observedAirborne = false
        serverProvenFalling = false
        if (clearPendingClick) {
            pendingReadyClick = false
        }
    }
}
