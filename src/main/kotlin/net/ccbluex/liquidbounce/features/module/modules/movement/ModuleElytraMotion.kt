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
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.CarriedSlotSyncOverride
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.multiplayer.MultiPlayerGameMode
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

/** Helps stabilize elytra targeting movement. */
object ModuleElytraMotion : ClientModule("ElytraMotion", Category.MOVEMENT) {

    private val attackDistance by float("AttackDistance", 3.0f, 0.1f..5.0f)
    private val autoFirework by boolean("AutoFirework", false)
    private val matrixBypass by boolean("MatrixBypass", false)

    var freeze = false
        private set

    private var fireworkTimerNanos = System.nanoTime()

    init {
        // A valid post-use carried-slot restore must survive disabling this module.
        ElytraMotionHotbarRestoreCycle
    }

    override fun disable() {
        freeze = false
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: run {
            freeze = false
            return@handler
        }

        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
            return@handler
        }

        if (!player.isFallFlying) {
            freeze = false
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && hasFireworkIntervalElapsed()) {
                useFirework()
                fireworkTimerNanos = System.nanoTime()
            }
        } else if (autoFirework && target != null && getBps(player) <= 30.0 &&
            hasFireworkIntervalElapsed()) {
            useFirework()
            fireworkTimerNanos = System.nanoTime()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
        } else if (freeze) {
            // The live mixin replaces LocalPlayer.move's movement argument with this value.
            event.movement = Vec3.ZERO
        }
    }

    fun check(target: LivingEntity?, elytraTarget: ModuleElytraTarget): Boolean {
        val player = mc.player ?: return false
        return target != null && player.isFallFlying && !elytraTarget.shouldTarget(target) &&
            target.distanceTo(player) < attackDistance
    }

    private fun useFirework() {
        val player = mc.player ?: return
        val gameMode = mc.gameMode ?: return
        val level = mc.level ?: return
        if (player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            useFireworkFromHand(InteractionHand.OFF_HAND)
            return
        }

        if (ElytraMotionHotbarRestoreCycle.isPending) {
            return
        }

        val firework = findFireworkInventoryFirst() ?: return
        val currentSlot = player.inventory.selectedSlot
        if (currentSlot !in 0..8) {
            return
        }

        if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
            useFireworkWithOffhandSwap(firework.serverSlot)
            return
        }

        when (firework) {
            is FireworkSlot.Hotbar -> useDirectHotbarFirework(
                gameMode = gameMode,
                level = level,
                player = player,
                fireworkSlot = firework.hotbarSlot,
                visibleSlot = currentSlot,
            )
            is FireworkSlot.Inventory -> {
                val temporarySlot = currentSlot % 8 + 1
                if (!clickSwap(firework.serverSlot, temporarySlot)) {
                    return
                }
                useInventorySwappedFireworkFromHotbar(temporarySlot, currentSlot)
                clickSwap(firework.serverSlot, temporarySlot)
            }
        }
    }

    private fun useDirectHotbarFirework(
        gameMode: MultiPlayerGameMode,
        level: ClientLevel,
        player: LocalPlayer,
        fireworkSlot: Int,
        visibleSlot: Int,
    ) {
        if (fireworkSlot !in 0..8 || visibleSlot !in 0..8 ||
            ElytraMotionHotbarRestoreCycle.isPending) {
            return
        }

        if (!elytraMotionNeedsCarriedSlotSync(fireworkSlot, visibleSlot)) {
            useFireworkFromHand(InteractionHand.MAIN_HAND)
            return
        }

        ElytraMotionHotbarRestoreCycle.start(
            gameMode = gameMode,
            level = level,
            player = player,
            fireworkSlot = fireworkSlot,
            visibleSlot = visibleSlot,
            rotation = useRotation(),
        )
    }

    /** Main-inventory behavior is intentionally unchanged and unaudited for the direct-Hotbar reproduction. */
    private fun useInventorySwappedFireworkFromHotbar(fireworkSlot: Int, returnSlot: Int) {
        if (fireworkSlot !in 0..8 || returnSlot !in 0..8) {
            return
        }

        network.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        network.sendPacket(ServerboundSetCarriedItemPacket(returnSlot))
    }

    private fun useFireworkWithOffhandSwap(sourceSlot: Int) {
        if (!clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)) {
            return
        }
        useFireworkFromHand(InteractionHand.OFF_HAND)
        clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)
    }

    private fun useFireworkFromHand(hand: InteractionHand) {
        val level = mc.level ?: return
        val rotation = useRotation()
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
    }

    private fun useRotation(): Rotation {
        return ModuleKillAura.activeServerFacingRotationForCurrentTarget()
            ?: mc.player?.rotation
            ?: Rotation.ZERO
    }

    private fun findFireworkInventoryFirst(): FireworkSlot? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Inventory(slot)
            }
        }
        for (slot in 8 downTo 0) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Hotbar(slot, HOTBAR_CONTAINER_OFFSET + slot)
            }
        }
        return null
    }

    private fun clickSwap(slot: Int, button: Int): Boolean {
        val player = mc.player ?: return false
        if (slot !in player.screenHandler.slots.indices ||
            button !in 0..8 && button != OFFHAND_SWAP_BUTTON) {
            return false
        }

        interaction.clickSlot(player.screenHandler.syncId, slot, button, ContainerInput.SWAP, player)
        return true
    }

    private fun getBps(entity: LivingEntity): Double {
        val dx = entity.x - entity.xo
        val dy = entity.y - entity.yo
        val dz = entity.z - entity.zo
        return sqrt(dx * dx + dy * dy + dz * dz) * 20.0
    }

    private sealed class FireworkSlot(open val serverSlot: Int) {
        data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : FireworkSlot(serverSlot)
        data class Inventory(override val serverSlot: Int) : FireworkSlot(serverSlot)
    }

    private const val FIREWORK_INTERVAL_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
    private const val HOTBAR_CONTAINER_OFFSET = 36
    private const val OFFHAND_SWAP_BUTTON = 40

    private fun hasFireworkIntervalElapsed() =
        System.nanoTime() - fireworkTimerNanos >= FIREWORK_INTERVAL_MS * NANOS_PER_MILLISECOND
}

private object ElytraMotionHotbarRestoreCycle : EventListener {

    private val state = ElytraMotionHotbarRestoreState()
    private var lastWaitLoggedSerial = Long.MIN_VALUE

    override val running: Boolean
        get() = true

    val isPending: Boolean
        get() = state.pending != null

    fun start(
        gameMode: MultiPlayerGameMode,
        level: ClientLevel,
        player: LocalPlayer,
        fireworkSlot: Int,
        visibleSlot: Int,
        rotation: Rotation,
    ): Boolean {
        if (state.pending != null || fireworkSlot !in 0..8 || visibleSlot !in 0..8 ||
            mc.player !== player || mc.level !== level || mc.gameMode !== gameMode) {
            return false
        }

        val diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
        val initialBoundarySerial = ActionTickBoundary.currentSerial()
        val createdTick = player.tickCount
        val createdNanos = System.nanoTime()
        val action = ActionTickBoundary.beginAction(
            owner = ACTION_OWNER,
            kind = ActionTickBoundary.ActionKind.USE_ITEM,
            diagnosticsEnabled = diagnosticsEnabled,
        )
        val initialHeldExpectation = ActionTickBoundary.expectHeldItemChange(
            owner = ACTION_OWNER,
            purpose = "source=HOTBAR initial Firework slot synchronization",
            action = action,
            diagnosticsEnabled = diagnosticsEnabled,
        )
        if (!initialHeldExpectation.allowed) {
            ActionTickBoundary.cancelAction(action)
            return false
        }

        ActionTickBoundary.diagnostic(
            diagnosticsEnabled,
            "ElytraMotion source=HOTBAR fireworkSlot=$fireworkSlot visibleSlot=$visibleSlot " +
                "initialHeldExpectation=true boundarySerial=$initialBoundarySerial",
        )
        try {
            CarriedSlotSyncOverride.withSlot(CARRIED_SLOT_OWNER, fireworkSlot) {
                gameMode.ensureHasSentCarriedItem()
            }
        } finally {
            ActionTickBoundary.completeHeldItemChangeAttempt(initialHeldExpectation)
        }

        try {
            gameMode.sendSequencedPacket(level) { sequence ->
                ServerboundUseItemPacket(
                    InteractionHand.MAIN_HAND,
                    sequence,
                    rotation.yaw,
                    rotation.pitch,
                )
            }
        } finally {
            ActionTickBoundary.actionCallCompleted(action)
            val postUseBoundarySerial = ActionTickBoundary.currentSerial()
            val rawUseObserved = ActionTickBoundary.wasActionObserved(action)
            check(
                state.begin(
                    ElytraMotionHotbarPendingRestore(
                        playerIdentity = player,
                        levelIdentity = level,
                        fireworkSlot = fireworkSlot,
                        capturedVisibleSlot = visibleSlot,
                        postUseBoundarySerial = postUseBoundarySerial,
                        diagnosticsEnabled = diagnosticsEnabled,
                        createdTick = createdTick,
                        createdNanos = createdNanos,
                    )
                )
            ) { "ElytraMotion Hotbar restore ownership changed during synchronous Firework use" }
            lastWaitLoggedSerial = Long.MIN_VALUE
            ActionTickBoundary.diagnostic(
                diagnosticsEnabled,
                "ElytraMotion source=HOTBAR rawUseObserved=$rawUseObserved " +
                    "boundarySerial=$postUseBoundarySerial",
            )
        }

        return true
    }

    @Suppress("unused")
    private val restoreHandler = handler<GameTickEvent> {
        val pending = state.pending ?: return@handler
        val currentPlayer = mc.player
        val currentLevel = mc.level
        val currentTick = currentPlayer?.tickCount ?: Int.MAX_VALUE
        val nowNanos = System.nanoTime()
        val decision = state.resolveRestore(
            currentPlayerIdentity = currentPlayer,
            currentLevelIdentity = currentLevel,
            currentVisibleSlot = currentPlayer?.inventory?.selectedSlot ?: -1,
            boundaryPassed = ActionTickBoundary.hasPassed(pending.postUseBoundarySerial),
            currentTick = currentTick,
            nowNanos = nowNanos,
        )

        when (decision) {
            ElytraMotionHotbarRestoreDecision.WAIT -> {
                val currentSerial = ActionTickBoundary.currentSerial()
                if (lastWaitLoggedSerial != currentSerial) {
                    lastWaitLoggedSerial = currentSerial
                    ActionTickBoundary.diagnostic(
                        pending.diagnosticsEnabled,
                        "ElytraMotion source=HOTBAR waiting boundarySerial=${pending.postUseBoundarySerial}",
                    )
                }
            }

            is ElytraMotionHotbarRestoreDecision.DROP -> clear(decision.reason)
            is ElytraMotionHotbarRestoreDecision.RESTORE -> restore(pending, decision.slot)
        }
    }

    private fun restore(pending: ElytraMotionHotbarPendingRestore, restoreSlot: Int) {
        val gameMode = mc.gameMode ?: return
        val player = mc.player ?: return
        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = ACTION_OWNER,
                actionBoundarySerial = pending.postUseBoundarySerial,
                diagnosticsEnabled = pending.diagnosticsEnabled,
            )) {
            return
        }

        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = ACTION_OWNER,
            purpose = "source=HOTBAR restore visible slot",
            postActionBoundarySerial = pending.postUseBoundarySerial,
            diagnosticsEnabled = pending.diagnosticsEnabled,
        )
        if (!expectation.allowed) {
            return
        }

        try {
            if (player.inventory.selectedSlot !in 0..8) {
                player.inventory.selectedSlot = restoreSlot
            }
            // The initial overridden ensure call changed carriedIndex to the Firework slot. This
            // normal ensure therefore emits the one return packet for the current visible slot.
            gameMode.ensureHasSentCarriedItem()
        } finally {
            ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        }
        ActionTickBoundary.diagnostic(
            pending.diagnosticsEnabled,
            "ElytraMotion source=HOTBAR finalRestoreSlot=$restoreSlot " +
                "boundarySerial=${pending.postUseBoundarySerial}",
        )
        clear("restored visible slot")
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clear("world changed")
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clear("disconnected")
    }

    private fun clear(reason: String) {
        val pending = state.clear() ?: return
        lastWaitLoggedSerial = Long.MIN_VALUE
        ActionTickBoundary.diagnostic(
            pending.diagnosticsEnabled,
            "ElytraMotion source=HOTBAR cycleClearReason=$reason fireworkSlot=${pending.fireworkSlot} " +
                "visibleSlot=${pending.capturedVisibleSlot}",
        )
    }

    private const val ACTION_OWNER = "ElytraMotionFirework"
    private val CARRIED_SLOT_OWNER = Any()
}

internal fun elytraMotionNeedsCarriedSlotSync(fireworkSlot: Int, visibleSlot: Int): Boolean =
    fireworkSlot != visibleSlot

internal data class ElytraMotionHotbarPendingRestore(
    val playerIdentity: Any,
    val levelIdentity: Any,
    val fireworkSlot: Int,
    val capturedVisibleSlot: Int,
    val postUseBoundarySerial: Long,
    val diagnosticsEnabled: Boolean,
    val createdTick: Int,
    val createdNanos: Long,
) {
    fun isTimedOut(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - createdTick > MAX_PENDING_TICKS || nowNanos - createdNanos > MAX_PENDING_NANOS

    private companion object {
        const val MAX_PENDING_TICKS = 100
        const val MAX_PENDING_NANOS = 10_000_000_000L
    }
}

internal sealed interface ElytraMotionHotbarRestoreDecision {
    data object WAIT : ElytraMotionHotbarRestoreDecision
    data class RESTORE(val slot: Int) : ElytraMotionHotbarRestoreDecision
    data class DROP(val reason: String) : ElytraMotionHotbarRestoreDecision
}

internal class ElytraMotionHotbarRestoreState {
    var pending: ElytraMotionHotbarPendingRestore? = null
        private set

    fun begin(restore: ElytraMotionHotbarPendingRestore): Boolean {
        if (pending != null || restore.fireworkSlot !in 0..8 || restore.capturedVisibleSlot !in 0..8 ||
            restore.postUseBoundarySerial == Long.MIN_VALUE) {
            return false
        }
        pending = restore
        return true
    }

    fun resolveRestore(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentVisibleSlot: Int,
        boundaryPassed: Boolean,
        currentTick: Int,
        nowNanos: Long,
    ): ElytraMotionHotbarRestoreDecision {
        val restore = pending ?: return ElytraMotionHotbarRestoreDecision.DROP("no pending restore")
        if (restore.playerIdentity !== currentPlayerIdentity || restore.levelIdentity !== currentLevelIdentity) {
            return ElytraMotionHotbarRestoreDecision.DROP("player or level replaced")
        }
        if (restore.fireworkSlot !in 0..8 || restore.capturedVisibleSlot !in 0..8) {
            return ElytraMotionHotbarRestoreDecision.DROP("invalid stored slot")
        }
        if (restore.isTimedOut(currentTick, nowNanos)) {
            return ElytraMotionHotbarRestoreDecision.DROP("restore timeout")
        }
        if (!boundaryPassed) {
            return ElytraMotionHotbarRestoreDecision.WAIT
        }

        val restoreSlot = currentVisibleSlot.takeIf { it in 0..8 } ?: restore.capturedVisibleSlot
        return if (restoreSlot in 0..8) {
            ElytraMotionHotbarRestoreDecision.RESTORE(restoreSlot)
        } else {
            ElytraMotionHotbarRestoreDecision.DROP("no valid visible return slot")
        }
    }

    fun clear(): ElytraMotionHotbarPendingRestore? = pending.also { pending = null }
}
