/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.utils.network

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.network.protocol.game.ServerboundAttackPacket
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import java.util.ArrayDeque

/**
 * Tracks the real client packet boundary that Grim uses to clear its per-action packet-order state.
 *
 * This is intentionally observational. It neither sends, cancels, nor reorders packets. Callers must
 * ask [canSendPostActionHeldItemChange] before they perform their own deferred selected-slot restore.
 */
object ActionTickBoundary : EventListener {

    enum class ActionKind {
        USE_ITEM,
        USE_ITEM_ON,
        ATTACK,
    }

    class ActionToken internal constructor(
        val owner: String,
        internal val kind: ActionKind,
        val diagnosticsEnabled: Boolean,
    ) {
        internal var observed = false
        internal var observedSerial = Long.MIN_VALUE

        internal fun matches(packet: Any): Boolean = when (kind) {
            ActionKind.USE_ITEM -> packet is ServerboundUseItemPacket
            ActionKind.USE_ITEM_ON -> packet is ServerboundUseItemOnPacket
            ActionKind.ATTACK -> packet is ServerboundAttackPacket
        }
    }

    class HeldItemChangeExpectation internal constructor(
        internal val owner: String,
        internal val purpose: String,
        internal val action: ActionToken?,
        internal val postActionBoundarySerial: Long?,
        internal val diagnosticsEnabled: Boolean,
        internal val allowed: Boolean,
    )

    private var completedTickSerial = 0L
    private var outgoingPacketSequence = 0L
    private var diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
    private var diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES

    private val pendingActions = mutableListOf<ActionToken>()
    private val expectedHeldItemChanges = ArrayDeque<HeldItemChangeExpectation>()

    /**
     * This observer must retain its lifecycle handlers even while a module is disabled, so stale
     * expectations cannot cross a disconnect or world replacement.
     */
    override val running: Boolean
        get() = true

    fun currentSerial(): Long = completedTickSerial

    fun hasPassed(serial: Long): Boolean = serial != Long.MIN_VALUE && completedTickSerial > serial

    /**
     * Diagnostics are deliberately bounded per world/session. A cycle owns all of its follow-up
     * logs, including the matching natural client tick-end packet.
     */
    fun claimDiagnosticCycle(): Boolean {
        if (diagnosticCyclesRemaining <= 0 || diagnosticMessagesRemaining <= 0) {
            return false
        }

        diagnosticCyclesRemaining--
        return true
    }

    fun beginAction(owner: String, kind: ActionKind, diagnosticsEnabled: Boolean): ActionToken {
        val token = ActionToken(owner, kind, diagnosticsEnabled)
        pendingActions += token
        diagnostic(diagnosticsEnabled, "$owner preparing ${kind.name}")
        return token
    }

    fun actionCallCompleted(token: ActionToken) {
        diagnostic(
            token.diagnosticsEnabled,
            "${token.owner} ${token.kind.name} call completed observed=${token.observed} " +
                "actionSerial=${token.observedSerial}"
        )
    }

    fun cancelAction(token: ActionToken) {
        pendingActions.remove(token)
        expectedHeldItemChanges.removeIf { it.action === token }
    }

    /**
     * Marks the next selected-slot synchronization emitted synchronously by the caller. Expectations
     * are only used for the module-owned sequences that opt in, so unrelated vanilla slot packets are
     * never logged or validated here.
     */
    fun expectHeldItemChange(
        owner: String,
        purpose: String,
        action: ActionToken? = null,
        postActionBoundarySerial: Long? = null,
        diagnosticsEnabled: Boolean,
    ): HeldItemChangeExpectation {
        // A caller normally supplies [postActionBoundarySerial] for a deferred restore. The
        // action-token fallback additionally turns an accidental action-then-slot-change path
        // into a no-send outcome before the local selected slot is mutated.
        val requiredBoundarySerial = postActionBoundarySerial ?: action
            ?.takeIf { it.observed }
            ?.observedSerial
        if (requiredBoundarySerial != null && !canSendPostActionHeldItemChange(
                owner,
                requiredBoundarySerial,
                diagnosticsEnabled
            )
        ) {
            return HeldItemChangeExpectation(
                owner,
                purpose,
                action,
                postActionBoundarySerial,
                diagnosticsEnabled,
                allowed = false
            )
        }

        return HeldItemChangeExpectation(
            owner,
            purpose,
            action,
            postActionBoundarySerial,
            diagnosticsEnabled,
            allowed = true
        ).also(expectedHeldItemChanges::addLast)
    }

    fun completeHeldItemChangeAttempt(expectation: HeldItemChangeExpectation) {
        expectedHeldItemChanges.remove(expectation)
    }

    /**
     * The only active prevention path: do not mutate the local selected slot or call
     * ensureHasSentCarriedItem until Grim's natural action boundary has actually left the client.
     */
    fun canSendPostActionHeldItemChange(
        owner: String,
        actionBoundarySerial: Long,
        diagnosticsEnabled: Boolean,
    ): Boolean {
        if (hasPassed(actionBoundarySerial)) {
            return true
        }

        logger.error(
            "$DIAGNOSTIC_PREFIX $owner prevented unsafe HELD_ITEM_CHANGE " +
                "completedSerial=$completedTickSerial actionSerial=$actionBoundarySerial"
        )
        diagnostic(diagnosticsEnabled, "$owner retaining pending post-action slot change")
        return false
    }

    fun diagnostic(enabled: Boolean, message: String) {
        logDiagnostic(
            enabled,
            "tick=${mc.player?.tickCount ?: -1} serial=$completedTickSerial $message"
        )
    }

    @Suppress("unused")
    private val outgoingPacketHandler = handler<OutgoingPacketSentEvent> { event ->
        outgoingPacketSequence++

        when (val packet = event.packet) {
            is ServerboundClientTickEndPacket -> observeClientTickEnd()
            is ServerboundSetCarriedItemPacket -> observeHeldItemChange(packet)
            else -> observeOwnedAction(packet)
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearExpectations(resetDiagnosticBudget = true)
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearExpectations(resetDiagnosticBudget = true)
    }

    private fun observeClientTickEnd() {
        val hasDiagnosticCycle = pendingActions.any { it.diagnosticsEnabled } ||
            expectedHeldItemChanges.any { it.diagnosticsEnabled }

        completedTickSerial++
        if (hasDiagnosticCycle) {
            logDiagnostic(
                enabled = true,
                "CLIENT_TICK_END serial=$completedTickSerial packet#$outgoingPacketSequence " +
                    "ServerboundClientTickEndPacket"
            )
        }

        // Every owner expectation is synchronous with the caller's packet send. Any unmatched
        // expectation cannot be allowed to observe a later, unrelated packet in the next action tick.
        pendingActions.clear()
        expectedHeldItemChanges.clear()
    }

    private fun observeHeldItemChange(packet: ServerboundSetCarriedItemPacket) {
        if (expectedHeldItemChanges.isEmpty()) {
            return
        }

        val expectation = expectedHeldItemChanges.removeFirst()

        val action = expectation.action
        val unsafeAfterAction = expectation.postActionBoundarySerial?.let { !hasPassed(it) } == true ||
            action?.let { it.observed && !hasPassed(it.observedSerial) } == true

        if (unsafeAfterAction) {
            logger.error(
                "$DIAGNOSTIC_PREFIX ${expectation.owner} observed unsafe HELD_ITEM_CHANGE " +
                    "sequence=$outgoingPacketSequence slot=${packet.slot} purpose=${expectation.purpose} " +
                    "serial=$completedTickSerial"
            )
        }

        diagnostic(
            expectation.diagnosticsEnabled,
            "${expectation.owner} packet#$outgoingPacketSequence " +
                "ServerboundSetCarriedItemPacket slot=${packet.slot} purpose=${expectation.purpose}"
        )
    }

    private fun observeOwnedAction(packet: Any) {
        val token = pendingActions.firstOrNull { !it.observed && it.matches(packet) } ?: return
        token.observed = true
        token.observedSerial = completedTickSerial

        diagnostic(
            token.diagnosticsEnabled,
            "${token.owner} packet#$outgoingPacketSequence ${packet.javaClass.simpleName} " +
                "action=${token.kind.name}"
        )
    }

    private fun clearExpectations(resetDiagnosticBudget: Boolean) {
        pendingActions.clear()
        expectedHeldItemChanges.clear()
        if (resetDiagnosticBudget) {
            diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
            diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
        }
    }

    private fun logDiagnostic(enabled: Boolean, message: String) {
        if (!enabled || diagnosticMessagesRemaining <= 0) {
            return
        }

        diagnosticMessagesRemaining--
        logger.info("$DIAGNOSTIC_PREFIX $message")
    }

    private const val DIAGNOSTIC_PREFIX = "[ActionOrderDiag]"
    private const val MAX_DIAGNOSTIC_CYCLES = 32
    private const val MAX_DIAGNOSTIC_MESSAGES = MAX_DIAGNOSTIC_CYCLES * 16
}
