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
package net.ccbluex.liquidbounce.utils.client

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler

/**
 * Temporarily changes only the slot read by `MultiPlayerGameMode.ensureHasSentCarriedItem()`.
 * It deliberately does not participate in local selected-item or held-item resolution.
 */
object ServerCarriedSlotLease : EventListener {

    private val state = ServerCarriedSlotLeaseState()

    override val running: Boolean
        get() = true

    fun claim(owner: Any, slot: Int, player: Any, level: Any): Boolean {
        val currentPlayer = mc.player ?: return false
        val currentLevel = mc.level ?: return false
        if (player !== currentPlayer || level !== currentLevel) {
            return false
        }

        return state.claim(
            owner = owner,
            slot = slot,
            player = player,
            level = level,
            createdTick = currentPlayer.tickCount,
            createdNanos = System.nanoTime(),
            ordinarySilentHotbarActive = SilentHotbar.isSlotModified(),
        )
    }

    fun release(owner: Any) {
        state.release(owner)
    }

    fun isOwnedBy(owner: Any): Boolean = state.isOwnedBy(owner)

    fun resolveSlot(originalSlot: Int): Int = state.resolveSlot(
        originalSlot = originalSlot,
        currentPlayer = mc.player,
        currentLevel = mc.level,
        currentTick = mc.player?.tickCount ?: Int.MAX_VALUE,
        nowNanos = System.nanoTime(),
    )

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent>(priority = 1002) {
        state.dropIfInvalid(
            currentPlayer = mc.player,
            currentLevel = mc.level,
            currentTick = mc.player?.tickCount ?: Int.MAX_VALUE,
            nowNanos = System.nanoTime(),
        )
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        state.clear()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        state.clear()
    }
}

internal fun resolveServerCarriedSlot(enforcedSlot: Int?, fallbackSlot: Int): Int =
    enforcedSlot ?: fallbackSlot

internal class ServerCarriedSlotLeaseState {

    private data class Lease(
        val owner: Any,
        val enforcedSlot: Int,
        val playerIdentity: Any,
        val levelIdentity: Any,
        val createdTick: Int,
        val createdNanos: Long,
    )

    private var lease: Lease? = null

    fun claim(
        owner: Any,
        slot: Int,
        player: Any,
        level: Any,
        createdTick: Int,
        createdNanos: Long,
        ordinarySilentHotbarActive: Boolean,
    ): Boolean {
        val current = lease
        if (current != null) {
            return current.owner === owner && current.enforcedSlot == slot &&
                current.playerIdentity === player && current.levelIdentity === level
        }
        if (ordinarySilentHotbarActive || slot !in 0..8) {
            return false
        }

        lease = Lease(owner, slot, player, level, createdTick, createdNanos)
        return true
    }

    fun release(owner: Any): Boolean {
        if (lease?.owner !== owner) {
            return false
        }
        lease = null
        return true
    }

    fun isOwnedBy(owner: Any): Boolean = lease?.owner === owner

    fun resolveSlot(
        originalSlot: Int,
        currentPlayer: Any?,
        currentLevel: Any?,
        currentTick: Int,
        nowNanos: Long,
    ): Int {
        dropIfInvalid(currentPlayer, currentLevel, currentTick, nowNanos)
        return resolveServerCarriedSlot(lease?.enforcedSlot, originalSlot)
    }

    fun dropIfInvalid(
        currentPlayer: Any?,
        currentLevel: Any?,
        currentTick: Int,
        nowNanos: Long,
    ): Boolean {
        val current = lease ?: return false
        val invalidIdentity = current.playerIdentity !== currentPlayer || current.levelIdentity !== currentLevel
        val expired = currentTick - current.createdTick > MAX_LEASE_TICKS ||
            nowNanos - current.createdNanos > MAX_LEASE_NANOS
        if (!invalidIdentity && !expired) {
            return false
        }

        lease = null
        return true
    }

    fun clear() {
        lease = null
    }

    private companion object {
        const val MAX_LEASE_TICKS = 100
        const val MAX_LEASE_NANOS = 10_000_000_000L
    }
}
