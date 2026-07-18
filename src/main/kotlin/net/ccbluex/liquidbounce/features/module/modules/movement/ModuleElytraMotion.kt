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
import net.ccbluex.liquidbounce.event.events.OutgoingPacketPostSendEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket
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
        // The raw return belongs to the action cycle, even if the module is disabled before tick end.
        ElytraMotionDirectHotbarRestore
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
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            useFireworkFromHand(InteractionHand.OFF_HAND)
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
            is FireworkSlot.Hotbar -> useDirectHotbarFirework(firework.hotbarSlot, currentSlot)
            is FireworkSlot.Inventory -> {
                val temporarySlot = currentSlot % 8 + 1
                if (!clickSwap(firework.serverSlot, temporarySlot)) {
                    return
                }
                useTemporaryInventoryFirework(temporarySlot, currentSlot)
                clickSwap(firework.serverSlot, temporarySlot)
            }
        }
    }

    private fun useDirectHotbarFirework(fireworkSlot: Int, visibleSlot: Int) {
        val player = mc.player ?: return
        val level = mc.level ?: return
        val connection = mc.connection ?: return
        if (fireworkSlot !in 0..8 || visibleSlot !in 0..8 ||
            ElytraMotionDirectHotbarRestore.isPending) {
            return
        }

        if (fireworkSlot == visibleSlot) {
            useFireworkFromHand(InteractionHand.MAIN_HAND)
            return
        }

        val diagnosticsEnabled = ElytraMotionDirectHotbarRestore.claimDiagnosticCycle(
            fireworkSlot,
            visibleSlot,
        )
        val createdTick = player.tickCount
        val createdNanos = System.nanoTime()
        connection.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        ElytraMotionDirectHotbarRestore.diagnostic(
            diagnosticsEnabled,
            "initial raw HELD sent fireworkSlot=$fireworkSlot capturedSelectedSlot=$visibleSlot",
        )
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        ElytraMotionDirectHotbarRestore.diagnostic(
            diagnosticsEnabled,
            "raw USE_ITEM send call completed hand=MAIN_HAND",
        )

        check(
            ElytraMotionDirectHotbarRestore.arm(
                ElytraMotionDirectHotbarPendingRestore(
                    playerIdentity = player,
                    levelIdentity = level,
                    connectionIdentity = connection,
                    fireworkSlot = fireworkSlot,
                    capturedVisibleSlot = visibleSlot,
                    diagnosticsEnabled = diagnosticsEnabled,
                    createdTick = createdTick,
                    createdNanos = createdNanos,
                )
            )
        ) { "ElytraMotion direct-Hotbar restore ownership changed during synchronous Firework use" }
    }

    /** Main-inventory SWAP/use/SWAP-back behavior is outside the direct-Hotbar reproduction. */
    private fun useTemporaryInventoryFirework(fireworkSlot: Int, returnSlot: Int) {
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

private object ElytraMotionDirectHotbarRestore : EventListener {

    private val state = ElytraMotionDirectHotbarRestoreState()
    private var diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
    private var diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES

    override val running: Boolean
        get() = true

    val isPending: Boolean
        get() = state.pending != null

    fun claimDiagnosticCycle(fireworkSlot: Int, visibleSlot: Int): Boolean {
        if (diagnosticCyclesRemaining <= 0 || diagnosticMessagesRemaining <= 0) {
            return false
        }

        diagnosticCyclesRemaining--
        diagnostic(
            true,
            "cycle created fireworkSlot=$fireworkSlot capturedSelectedSlot=$visibleSlot",
        )
        return true
    }

    fun arm(restore: ElytraMotionDirectHotbarPendingRestore): Boolean = state.begin(restore)

    fun diagnostic(enabled: Boolean, message: String) {
        if (!enabled || diagnosticMessagesRemaining <= 0) {
            return
        }

        diagnosticMessagesRemaining--
        logger.info("$DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} $message")
    }

    @Suppress("unused")
    private val postSendHandler = handler<OutgoingPacketPostSendEvent> { event ->
        val pending = state.pending ?: return@handler
        when (val packet = event.packet) {
            is ServerboundClientTickEndPacket -> completeAfterNaturalTickEnd(pending)
            is ServerboundSetCarriedItemPacket -> diagnostic(
                pending.diagnosticsEnabled,
                "unexpected HELD before CLIENT_TICK_END slot=${packet.slot}",
            )
        }
    }

    private fun completeAfterNaturalTickEnd(pending: ElytraMotionDirectHotbarPendingRestore) {
        diagnostic(
            pending.diagnosticsEnabled,
            "natural CLIENT_TICK_END post-send observed",
        )

        val currentPlayer = mc.player
        val currentConnection = mc.connection
        val decision = state.completeAfterTickEnd(
            currentPlayerIdentity = currentPlayer,
            currentLevelIdentity = mc.level,
            currentConnectionIdentity = currentConnection,
            currentVisibleSlot = currentPlayer?.inventory?.selectedSlot ?: -1,
            currentTick = currentPlayer?.tickCount ?: Int.MAX_VALUE,
            nowNanos = System.nanoTime(),
        )

        when (decision) {
            is ElytraMotionDirectHotbarRestoreDecision.DROP -> diagnostic(
                pending.diagnosticsEnabled,
                "cycle clear reason=${decision.reason}",
            )

            is ElytraMotionDirectHotbarRestoreDecision.RESTORE -> {
                // completeAfterTickEnd clears ownership before this nested send can emit another post-send event.
                diagnostic(
                    pending.diagnosticsEnabled,
                    "final raw HELD return slot=${decision.slot}",
                )
                try {
                    checkNotNull(currentConnection).sendPacket(ServerboundSetCarriedItemPacket(decision.slot))
                } finally {
                    diagnostic(
                        pending.diagnosticsEnabled,
                        "cycle clear reason=restored after natural CLIENT_TICK_END",
                    )
                }
            }
        }
    }

    @Suppress("unused")
    private val cleanupHandler = handler<GameTickEvent> {
        val pending = state.pending ?: return@handler
        val currentPlayer = mc.player
        val reason = state.invalidReason(
            currentPlayerIdentity = currentPlayer,
            currentLevelIdentity = mc.level,
            currentConnectionIdentity = mc.connection,
            currentTick = currentPlayer?.tickCount ?: Int.MAX_VALUE,
            nowNanos = System.nanoTime(),
        ) ?: return@handler

        clear(reason, pending)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clear("world changed")
        resetDiagnosticBudget()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clear("disconnected")
        resetDiagnosticBudget()
    }

    private fun clear(reason: String, expected: ElytraMotionDirectHotbarPendingRestore? = null) {
        val pending = state.clear() ?: return
        check(expected == null || pending === expected) {
            "ElytraMotion direct-Hotbar restore ownership changed during cleanup"
        }
        diagnostic(pending.diagnosticsEnabled, "cycle clear reason=$reason")
    }

    private fun resetDiagnosticBudget() {
        diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
        diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
    }

    private const val DIAGNOSTIC_PREFIX = "[ElytraMotionFireworkOrder]"
    private const val MAX_DIAGNOSTIC_CYCLES = 4
    private const val MAX_DIAGNOSTIC_MESSAGES = MAX_DIAGNOSTIC_CYCLES * 8
}

internal data class ElytraMotionDirectHotbarPendingRestore(
    val playerIdentity: Any,
    val levelIdentity: Any,
    val connectionIdentity: Any,
    val fireworkSlot: Int,
    val capturedVisibleSlot: Int,
    val diagnosticsEnabled: Boolean,
    val createdTick: Int,
    val createdNanos: Long,
) {
    fun isTimedOut(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - createdTick > MAX_PENDING_TICKS || nowNanos - createdNanos > MAX_PENDING_NANOS

    private companion object {
        const val MAX_PENDING_TICKS = 20
        const val MAX_PENDING_NANOS = 1_000_000_000L
    }
}

internal sealed interface ElytraMotionDirectHotbarRestoreDecision {
    data class RESTORE(val slot: Int) : ElytraMotionDirectHotbarRestoreDecision
    data class DROP(val reason: String) : ElytraMotionDirectHotbarRestoreDecision
}

internal class ElytraMotionDirectHotbarRestoreState {
    var pending: ElytraMotionDirectHotbarPendingRestore? = null
        private set

    fun begin(restore: ElytraMotionDirectHotbarPendingRestore): Boolean {
        if (pending != null || restore.fireworkSlot !in 0..8 || restore.capturedVisibleSlot !in 0..8) {
            return false
        }

        pending = restore
        return true
    }

    fun invalidReason(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentConnectionIdentity: Any?,
        currentTick: Int,
        nowNanos: Long,
    ): String? {
        val restore = pending ?: return null
        return when {
            restore.playerIdentity !== currentPlayerIdentity -> "local player replaced"
            restore.levelIdentity !== currentLevelIdentity -> "level replaced"
            restore.connectionIdentity !== currentConnectionIdentity -> "connection replaced"
            restore.fireworkSlot !in 0..8 || restore.capturedVisibleSlot !in 0..8 -> "invalid stored slot"
            restore.isTimedOut(currentTick, nowNanos) -> "restore timeout"
            else -> null
        }
    }

    fun completeAfterTickEnd(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentConnectionIdentity: Any?,
        currentVisibleSlot: Int,
        currentTick: Int,
        nowNanos: Long,
    ): ElytraMotionDirectHotbarRestoreDecision {
        val restore = pending ?: return ElytraMotionDirectHotbarRestoreDecision.DROP("no pending restore")
        val invalidReason = invalidReason(
            currentPlayerIdentity,
            currentLevelIdentity,
            currentConnectionIdentity,
            currentTick,
            nowNanos,
        )
        if (invalidReason != null) {
            pending = null
            return ElytraMotionDirectHotbarRestoreDecision.DROP(invalidReason)
        }

        val restoreSlot = currentVisibleSlot.takeIf { it in 0..8 } ?: restore.capturedVisibleSlot
        if (restoreSlot !in 0..8) {
            pending = null
            return ElytraMotionDirectHotbarRestoreDecision.DROP("no valid visible return slot")
        }

        pending = null
        return ElytraMotionDirectHotbarRestoreDecision.RESTORE(restoreSlot)
    }

    fun clear(): ElytraMotionDirectHotbarPendingRestore? = pending.also { pending = null }
}
