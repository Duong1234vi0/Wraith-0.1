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
package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

/**
 * Latches one GrimTick decision until the matching natural client tick-end has
 * returned from Minecraft's normal send path.
 */
internal class GrimTickNoSlowController(
    private val warmupRecoveryCycles: Int = DEFAULT_WARMUP_RECOVERY_CYCLES,
) {

    enum class Phase {
        INACTIVE,
        WARMUP,
        FAST,
        RECOVERY,
        RECOVERY_CONFIRM,
    }

    enum class BoundaryIssue {
        NONE,
        MISSING_TICK_END,
        DUPLICATE_TICK_END,
        UNEXPECTED_TICK_END,
    }

    private var playerIdentity: Any? = null
    private var handIdentity: Any? = null
    private var itemIdentity: Any? = null
    private var useSessionId = 0
    private var selectedSlot = Int.MIN_VALUE
    private var settingsFingerprint = 0

    private var expectedTickEndSerial = NO_TICK
    private var lastCompletedTickEndSerial = NO_TICK
    private var cycleOpen = false
    private var latchedBypass = false
    private var completedWarmupRecoveries = 0
    private var nextPhase = Phase.WARMUP

    var phase: Phase = Phase.INACTIVE
        private set

    var boundaryIssue: BoundaryIssue = BoundaryIssue.NONE
        private set

    fun beginCycle(
        tickEndSerial: Long,
        active: Boolean,
        eligible: Boolean,
        playerIdentity: Any?,
        handIdentity: Any?,
        itemIdentity: Any?,
        useSessionId: Int,
        selectedSlot: Int,
        settingsFingerprint: Int,
    ): Boolean {
        boundaryIssue = BoundaryIssue.NONE
        val missedTickEnd = cycleOpen

        if (!active || !eligible || playerIdentity == null || handIdentity == null || itemIdentity == null) {
            reset()
            return false
        }

        if (!sameUseSession(
                playerIdentity,
                handIdentity,
                itemIdentity,
                useSessionId,
                selectedSlot,
                settingsFingerprint,
            )
        ) {
            resetProgress()
            this.playerIdentity = playerIdentity
            this.handIdentity = handIdentity
            this.itemIdentity = itemIdentity
            this.useSessionId = useSessionId
            this.selectedSlot = selectedSlot
            this.settingsFingerprint = settingsFingerprint
        } else if (missedTickEnd) {
            boundaryIssue = BoundaryIssue.MISSING_TICK_END
            failClosed()
        }

        expectedTickEndSerial = tickEndSerial
        cycleOpen = true
        phase = nextPhase
        latchedBypass = phase == Phase.FAST
        return latchedBypass
    }

    fun decision(): Boolean = cycleOpen && latchedBypass

    fun isRecoveryCycle(): Boolean = cycleOpen && when (phase) {
        Phase.WARMUP, Phase.RECOVERY, Phase.RECOVERY_CONFIRM -> true
        Phase.INACTIVE, Phase.FAST -> false
    }

    /** Returns true only for the first matching natural tick-end completion. */
    fun completeAtNaturalTickEnd(tickEndSerial: Long): Boolean {
        boundaryIssue = BoundaryIssue.NONE
        if (!cycleOpen) {
            boundaryIssue = if (tickEndSerial == lastCompletedTickEndSerial) {
                BoundaryIssue.DUPLICATE_TICK_END
            } else {
                BoundaryIssue.UNEXPECTED_TICK_END
            }
            return false
        }
        if (tickEndSerial != expectedTickEndSerial) {
            boundaryIssue = BoundaryIssue.UNEXPECTED_TICK_END
            failClosed()
            return false
        }

        cycleOpen = false
        lastCompletedTickEndSerial = tickEndSerial
        when (phase) {
            Phase.WARMUP -> {
                completedWarmupRecoveries++
                nextPhase = if (completedWarmupRecoveries >= warmupRecoveryCycles) Phase.FAST else Phase.WARMUP
            }

            Phase.FAST -> nextPhase = Phase.RECOVERY
            Phase.RECOVERY -> nextPhase = Phase.RECOVERY_CONFIRM
            Phase.RECOVERY_CONFIRM -> nextPhase = Phase.FAST
            Phase.INACTIVE -> nextPhase = Phase.WARMUP
        }
        latchedBypass = false
        return true
    }

    fun reset() {
        playerIdentity = null
        handIdentity = null
        itemIdentity = null
        useSessionId = 0
        selectedSlot = Int.MIN_VALUE
        settingsFingerprint = 0
        resetProgress()
    }

    private fun sameUseSession(
        playerIdentity: Any,
        handIdentity: Any,
        itemIdentity: Any,
        useSessionId: Int,
        selectedSlot: Int,
        settingsFingerprint: Int,
    ) = this.playerIdentity === playerIdentity &&
        this.handIdentity === handIdentity &&
        this.itemIdentity === itemIdentity &&
        this.useSessionId == useSessionId &&
        this.selectedSlot == selectedSlot &&
        this.settingsFingerprint == settingsFingerprint

    private fun failClosed() {
        cycleOpen = false
        latchedBypass = false
        expectedTickEndSerial = NO_TICK
        nextPhase = if (completedWarmupRecoveries < warmupRecoveryCycles) {
            completedWarmupRecoveries = 0
            Phase.WARMUP
        } else {
            Phase.RECOVERY
        }
        phase = Phase.INACTIVE
    }

    private fun resetProgress() {
        expectedTickEndSerial = NO_TICK
        lastCompletedTickEndSerial = NO_TICK
        cycleOpen = false
        latchedBypass = false
        completedWarmupRecoveries = 0
        nextPhase = Phase.WARMUP
        phase = Phase.INACTIVE
        boundaryIssue = BoundaryIssue.NONE
    }

    companion object {
        const val DEFAULT_WARMUP_RECOVERY_CYCLES = 2
        private const val NO_TICK = Long.MIN_VALUE
    }
}
