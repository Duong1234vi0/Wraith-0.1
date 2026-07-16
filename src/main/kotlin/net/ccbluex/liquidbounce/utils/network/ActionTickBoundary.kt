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
        internal val onObserved: ((ActionKind) -> Unit)?,
    ) {
        internal var observed = false
        internal var observedSerial = Long.MIN_VALUE

        internal fun matches(packet: Any): Boolean = when (kind) {
            ActionKind.USE_ITEM -> packet is ServerboundUseItemPacket
            ActionKind.USE_ITEM_ON -> packet is ServerboundUseItemOnPacket
            ActionKind.ATTACK -> packet is ServerboundAttackPacket
        }
    }

    /**
     * Bounded, action-scoped diagnostics used by the crystal attack/place reproduction.
     *
     * This is deliberately diagnostic-only: it does not own a boundary, send a packet, or alter
     * CrystalOptimizer behavior. The caller still makes the action-phase decision.
     */
    class CrystalSequenceDiagnostic internal constructor(
        internal val enabled: Boolean,
    ) {
        internal var context = ""
        internal var observedAttackSerial = Long.MIN_VALUE
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
    private var crystalSequenceDiagnosticCyclesRemaining = MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_CYCLES
    private var crystalSequenceDiagnosticMessagesRemaining = MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_MESSAGES

    private val pendingActions = mutableListOf<ActionToken>()
    private val expectedHeldItemChanges = ArrayDeque<HeldItemChangeExpectation>()
    private var activeCrystalSequenceDiagnostic: CrystalSequenceDiagnostic? = null

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

    fun claimCrystalSequenceDiagnostic(): CrystalSequenceDiagnostic {
        val enabled = crystalSequenceDiagnosticCyclesRemaining > 0 && crystalSequenceDiagnosticMessagesRemaining > 0
        if (enabled) {
            crystalSequenceDiagnosticCyclesRemaining--
        }

        return CrystalSequenceDiagnostic(enabled)
    }

    fun updateCrystalSequenceContext(diagnostic: CrystalSequenceDiagnostic, context: String) {
        diagnostic.context = context
    }

    fun <T> withCrystalSequenceDiagnostic(
        diagnostic: CrystalSequenceDiagnostic,
        action: () -> T,
    ): T {
        val previous = activeCrystalSequenceDiagnostic
        activeCrystalSequenceDiagnostic = diagnostic
        return try {
            action()
        } finally {
            activeCrystalSequenceDiagnostic = previous
        }
    }

    /** Allows CrystalOptimizer to add context only while AutoCrystal owns the synchronous action call. */
    fun crystalSequenceDiagnosticForCurrentAction(message: String) {
        activeCrystalSequenceDiagnostic?.let { crystalSequenceDiagnostic(it, message) }
    }

    fun crystalSequenceDiagnostic(diagnostic: CrystalSequenceDiagnostic, message: String) {
        logCrystalSequenceDiagnostic(diagnostic, message, warning = false)
    }

    fun crystalSequenceWarning(diagnostic: CrystalSequenceDiagnostic, message: String) {
        logCrystalSequenceDiagnostic(diagnostic, message, warning = true)
    }

    private fun logCrystalSequenceDiagnostic(
        diagnostic: CrystalSequenceDiagnostic,
        message: String,
        warning: Boolean,
    ) {
        if (!diagnostic.enabled || crystalSequenceDiagnosticMessagesRemaining <= 0) {
            return
        }

        crystalSequenceDiagnosticMessagesRemaining--
        val context = diagnostic.context.takeIf(String::isNotBlank)?.let { "$it " }.orEmpty()
        val formatted = "$CRYSTAL_SEQUENCE_DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} " +
            "serial=$completedTickSerial $context$message"
        if (warning) {
            logger.warn(formatted)
        } else {
            logger.info(formatted)
        }
    }

    /** Records only an action token explicitly created by ModuleAutoCrystal. */
    fun recordCrystalSequenceAction(diagnostic: CrystalSequenceDiagnostic, kind: ActionKind) {
        when (kind) {
            ActionKind.ATTACK -> diagnostic.observedAttackSerial = completedTickSerial
            ActionKind.USE_ITEM_ON -> if (
                diagnostic.observedAttackSerial != Long.MIN_VALUE &&
                !hasPassed(diagnostic.observedAttackSerial)
            ) {
                crystalSequenceWarning(
                    diagnostic,
                    "observed unsafe AutoCrystal-owned ATTACK -> USE_ITEM_ON before CLIENT_TICK_END " +
                        "attackSerial=${diagnostic.observedAttackSerial}"
                )
            }

            ActionKind.USE_ITEM -> Unit
        }

        crystalSequenceDiagnostic(diagnostic, "AutoCrystal-owned packet action=${kind.name}")
    }

    /**
     * This preflight is a diagnostic backstop. ModuleAutoCrystal's pending boundary is the real
     * prevention path, while this catches any future direct call that would otherwise recreate
     * ATTACK -> USE_ITEM_ON before CLIENT_TICK_END.
     */
    fun canSendCrystalSequenceBlockAction(diagnostic: CrystalSequenceDiagnostic): Boolean {
        val attackSerial = diagnostic.observedAttackSerial
        if (attackSerial == Long.MIN_VALUE || hasPassed(attackSerial)) {
            return true
        }

        crystalSequenceWarning(
            diagnostic,
            "unsafe AutoCrystal-owned ATTACK -> USE_ITEM_ON before CLIENT_TICK_END; suppressing USE_ITEM_ON " +
                "attackSerial=$attackSerial"
        )
        return false
    }

    fun beginAction(
        owner: String,
        kind: ActionKind,
        diagnosticsEnabled: Boolean,
        onObserved: ((ActionKind) -> Unit)? = null,
    ): ActionToken {
        val token = ActionToken(owner, kind, diagnosticsEnabled, onObserved)
        pendingActions += token
        diagnostic(diagnosticsEnabled, "$owner preparing ${kind.name}")
        return token
    }

    fun wasActionObserved(token: ActionToken): Boolean = token.observed

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
        token.onObserved?.invoke(token.kind)

        diagnostic(
            token.diagnosticsEnabled,
            "${token.owner} packet#$outgoingPacketSequence ${packet.javaClass.simpleName} " +
                "action=${token.kind.name}"
        )
    }

    private fun clearExpectations(resetDiagnosticBudget: Boolean) {
        pendingActions.clear()
        expectedHeldItemChanges.clear()
        activeCrystalSequenceDiagnostic = null
        if (resetDiagnosticBudget) {
            diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
            diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
            crystalSequenceDiagnosticCyclesRemaining = MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_CYCLES
            crystalSequenceDiagnosticMessagesRemaining = MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_MESSAGES
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
    private const val CRYSTAL_SEQUENCE_DIAGNOSTIC_PREFIX = "[CrystalSequenceDiag]"
    private const val MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_CYCLES = 32
    private const val MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_MESSAGES = MAX_CRYSTAL_SEQUENCE_DIAGNOSTIC_CYCLES * 16
}
