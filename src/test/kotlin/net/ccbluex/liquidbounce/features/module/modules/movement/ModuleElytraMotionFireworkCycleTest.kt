package net.ccbluex.liquidbounce.features.module.modules.movement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModuleElytraMotionFireworkCycleTest {

    @Test
    fun hotbarFireworkDefersExactlyOneReturnHeldPacketUntilNaturalBoundary() {
        assertEquals(
            listOf(
                ElytraMotionFireworkPacketStep.HELD_FIREWORK,
                ElytraMotionFireworkPacketStep.USE_ITEM,
            ),
            elytraMotionHotbarStartSteps(fireworkSlot = 6, visibleSlot = 2),
        )

        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 6, returnSlot = 2)
        state.completeUse(boundarySerial = 10, observed = true)

        assertSame(
            ElytraMotionFireworkRestoreDecision.WAIT,
            state.resolveRestore(player, level, currentVisibleSlot = 2, currentSerial = 10, expectedServerSlot = 6),
        )
        assertEquals(
            ElytraMotionFireworkRestoreDecision.SEND(2),
            state.resolveRestore(player, level, currentVisibleSlot = 2, currentSerial = 11, expectedServerSlot = 6),
        )
        state.clear()
        assertSame(
            ElytraMotionFireworkRestoreDecision.CLEAR,
            state.resolveRestore(player, level, currentVisibleSlot = 2, currentSerial = 12, expectedServerSlot = 2),
        )
    }

    @Test
    fun alreadySelectedFireworkUsesWithoutAnyHeldPacket() {
        assertEquals(
            listOf(ElytraMotionFireworkPacketStep.USE_ITEM),
            elytraMotionHotbarStartSteps(fireworkSlot = 4, visibleSlot = 4),
        )
    }

    @Test
    fun repeatedRequestCannotOverwritePendingCycle() {
        val player = Any()
        val level = Any()
        val state = ElytraMotionFireworkCycleState()
        val first = pending(player, level, fireworkSlot = 7, returnSlot = 1)
        val second = pending(player, level, fireworkSlot = 8, returnSlot = 3)

        assertTrue(state.begin(first))
        assertFalse(state.begin(second))
        assertSame(first, state.pending)
    }

    @Test
    fun moduleDisableDoesNotDiscardPendingRestore() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 7, returnSlot = 1)
        state.completeUse(boundarySerial = 20, observed = true)

        // Module disable deliberately has no state transition on the always-running cycle.
        assertEquals(
            ElytraMotionFireworkRestoreDecision.SEND(1),
            state.resolveRestore(player, level, currentVisibleSlot = 1, currentSerial = 21, expectedServerSlot = 7),
        )
    }

    @Test
    fun worldOrDisconnectCleanupDropsPendingStateWithoutRestore() {
        val state = stateWithPending(Any(), Any(), fireworkSlot = 5, returnSlot = 0)

        state.clear()

        assertNull(state.pending)
    }

    @Test
    fun cancelledUseStillWaitsForBoundaryThenRestores() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 0)
        state.completeUse(boundarySerial = 30, observed = false)

        assertFalse(state.pending!!.usePacketObserved)
        assertSame(
            ElytraMotionFireworkRestoreDecision.WAIT,
            state.resolveRestore(player, level, currentVisibleSlot = 0, currentSerial = 30, expectedServerSlot = 5),
        )
        assertEquals(
            ElytraMotionFireworkRestoreDecision.SEND(0),
            state.resolveRestore(player, level, currentVisibleSlot = 0, currentSerial = 31, expectedServerSlot = 5),
        )
    }

    @Test
    fun manualVisibleSlotChangeBecomesDeferredSynchronizationTarget() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 0)
        state.completeUse(boundarySerial = 40, observed = true)

        assertEquals(
            ElytraMotionFireworkRestoreDecision.SEND(8),
            state.resolveRestore(player, level, currentVisibleSlot = 8, currentSerial = 41, expectedServerSlot = 5),
        )
    }

    @Test
    fun invalidVisibleSlotFallsBackAndKnownServerSlotAvoidsDuplicate() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 3)
        state.completeUse(boundarySerial = 50, observed = true)

        assertSame(
            ElytraMotionFireworkRestoreDecision.CLEAR,
            state.resolveRestore(player, level, currentVisibleSlot = -1, currentSerial = 51, expectedServerSlot = 3),
        )
    }

    @Test
    fun playerReplacementClearsWithoutSendingIntoNewSession() {
        val originalPlayer = Any()
        val level = Any()
        val state = stateWithPending(originalPlayer, level, fireworkSlot = 5, returnSlot = 3)
        state.completeUse(boundarySerial = 60, observed = true)

        assertSame(
            ElytraMotionFireworkRestoreDecision.CLEAR,
            state.resolveRestore(Any(), level, currentVisibleSlot = 3, currentSerial = 61, expectedServerSlot = 5),
        )
        assertNull(state.pending)
    }

    private fun stateWithPending(
        player: Any,
        level: Any,
        fireworkSlot: Int,
        returnSlot: Int,
    ) = ElytraMotionFireworkCycleState().also {
        assertTrue(it.begin(pending(player, level, fireworkSlot, returnSlot)))
    }

    private fun pending(
        player: Any,
        level: Any,
        fireworkSlot: Int,
        returnSlot: Int,
    ) = ElytraMotionFireworkPendingCycle(
        playerIdentity = player,
        levelIdentity = level,
        fireworkSlot = fireworkSlot,
        capturedReturnSlot = returnSlot,
        inventorySourceSlot = null,
        originalTemporaryItem = null,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0,
    )
}
