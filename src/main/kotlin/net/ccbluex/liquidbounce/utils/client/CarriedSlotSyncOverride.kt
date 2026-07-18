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

/**
 * Overrides only the selected-slot read inside `MultiPlayerGameMode.ensureHasSentCarriedItem()`.
 * The override is synchronous and never changes local inventory or ordinary SilentHotbar state.
 */
object CarriedSlotSyncOverride {

    @PublishedApi
    internal val state = CarriedSlotSyncOverrideState()

    inline fun <T> withSlot(owner: Any, slot: Int, block: () -> T): T {
        state.install(owner, slot, mc.isSameThread)
        return try {
            block()
        } finally {
            state.remove(owner)
        }
    }

    fun resolveSlot(normalServerSlot: Int): Int {
        check(mc.isSameThread) { "Carried-slot synchronization must run on the Minecraft thread" }
        return state.resolveSlot(normalServerSlot)
    }
}

@PublishedApi
internal class CarriedSlotSyncOverrideState {

    private data class Override(
        val owner: Any,
        val slot: Int,
    )

    private var activeOverride: Override? = null

    @PublishedApi
    internal fun install(owner: Any, slot: Int, mainThread: Boolean) {
        check(mainThread) { "Carried-slot synchronization must run on the Minecraft thread" }
        require(slot in 0..8) { "Carried-slot synchronization requires a Hotbar slot" }
        check(activeOverride == null) { "A carried-slot synchronization override is already active" }
        activeOverride = Override(owner, slot)
    }

    @PublishedApi
    internal fun remove(owner: Any) {
        val active = activeOverride
        check(active?.owner === owner) { "Only the active owner may remove the carried-slot override" }
        activeOverride = null
    }

    fun resolveSlot(normalServerSlot: Int): Int = activeOverride?.slot ?: normalServerSlot

    internal inline fun <T> withSlot(
        owner: Any,
        slot: Int,
        mainThread: Boolean = true,
        block: () -> T,
    ): T {
        install(owner, slot, mainThread)
        return try {
            block()
        } finally {
            remove(owner)
        }
    }
}
