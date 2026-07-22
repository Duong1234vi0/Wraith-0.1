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
 * Latches GrimTick to completed local movement cycles.
 *
 * A fast cycle is always followed by a completed vanilla recovery cycle. If a
 * cycle never reaches its network POST boundary, the next cycle fails closed
 * as recovery instead of advancing from an unrelated game tick.
 */
internal class GrimTickNoSlowController(
    private val warmupRecoveryCycles: Int = DEFAULT_WARMUP_RECOVERY_CYCLES,
) {

    enum class Phase {
        INACTIVE,
        WARMUP,
        FAST,
        RECOVERY,
    }

    private var playerIdentity: Any? = null
    private var handIdentity: Any? = null
    private var itemIdentity: Any? = null
    private var useSessionId = 0
    private var settingsFingerprint = 0

    private var currentCycleId = NO_CYCLE
    private var cycleOpen = false
    private var latchedBypass = false
    private var completedWarmupRecoveries = 0
    private var nextCycleFast = false

    var phase: Phase = Phase.INACTIVE
        private set

    fun beginCycle(
        cycleId: Long,
        active: Boolean,
        eligible: Boolean,
        playerIdentity: Any?,
        handIdentity: Any?,
        itemIdentity: Any?,
        useSessionId: Int,
        settingsFingerprint: Int,
    ): Boolean {
        if (cycleOpen && cycleId == currentCycleId) {
            return latchedBypass
        }

        val missedCompletion = cycleOpen
        cycleOpen = false
        latchedBypass = false

        if (!active || !eligible || playerIdentity == null || handIdentity == null || itemIdentity == null) {
            reset()
            return false
        }

        if (!sameUseSession(playerIdentity, handIdentity, itemIdentity, useSessionId, settingsFingerprint)) {
            resetProgress()
            this.playerIdentity = playerIdentity
            this.handIdentity = handIdentity
            this.itemIdentity = itemIdentity
            this.useSessionId = useSessionId
            this.settingsFingerprint = settingsFingerprint
        }

        currentCycleId = cycleId
        cycleOpen = true

        latchedBypass = when {
            missedCompletion -> false
            completedWarmupRecoveries < warmupRecoveryCycles -> false
            nextCycleFast -> true
            else -> false
        }

        phase = when {
            latchedBypass -> Phase.FAST
            completedWarmupRecoveries < warmupRecoveryCycles -> Phase.WARMUP
            else -> Phase.RECOVERY
        }

        return latchedBypass
    }

    fun decision(): Boolean = cycleOpen && latchedBypass

    /** Returns true only for the first matching completion. */
    fun completeCycle(cycleId: Long): Boolean {
        if (!cycleOpen || cycleId != currentCycleId) {
            return false
        }

        cycleOpen = false
        if (latchedBypass) {
            nextCycleFast = false
        } else {
            if (completedWarmupRecoveries < warmupRecoveryCycles) {
                completedWarmupRecoveries++
            }
            nextCycleFast = completedWarmupRecoveries >= warmupRecoveryCycles
        }
        latchedBypass = false
        return true
    }

    fun reset() {
        playerIdentity = null
        handIdentity = null
        itemIdentity = null
        useSessionId = 0
        settingsFingerprint = 0
        resetProgress()
    }

    private fun sameUseSession(
        playerIdentity: Any,
        handIdentity: Any,
        itemIdentity: Any,
        useSessionId: Int,
        settingsFingerprint: Int,
    ) = this.playerIdentity === playerIdentity &&
        this.handIdentity === handIdentity &&
        this.itemIdentity === itemIdentity &&
        this.useSessionId == useSessionId &&
        this.settingsFingerprint == settingsFingerprint

    private fun resetProgress() {
        currentCycleId = NO_CYCLE
        cycleOpen = false
        latchedBypass = false
        completedWarmupRecoveries = 0
        nextCycleFast = false
        phase = Phase.INACTIVE
    }

    companion object {
        const val DEFAULT_WARMUP_RECOVERY_CYCLES = 2
        private const val NO_CYCLE = Long.MIN_VALUE
    }
}
