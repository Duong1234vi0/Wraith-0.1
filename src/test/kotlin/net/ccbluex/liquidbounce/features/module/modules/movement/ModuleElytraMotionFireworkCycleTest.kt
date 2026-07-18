package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.utils.client.CarriedSlotSyncOverrideState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModuleElytraMotionFireworkCycleTest {

    @Test
    fun differentHotbarSlotSynchronizesCacheThenRestoresOnceAfterBoundary() {
        val override = CarriedSlotSyncOverrideState()
        val interaction = FakeInteractionManager(initialCarriedSlot = 2)
        val visibleSlot = 2
        val fireworkSlot = 6

        override.withSlot(OWNER, fireworkSlot) {
            interaction.ensureHasSentCarriedItem(override.resolveSlot(visibleSlot))
        }
        interaction.rawUseItem()

        assertEquals(listOf("HELD 6", "USE_ITEM"), interaction.packets)
        assertEquals(6, interaction.carriedSlot)
        assertEquals(visibleSlot, override.resolveSlot(visibleSlot))

        val state = stateWithPending(
            player = PLAYER,
            level = LEVEL,
            fireworkSlot = fireworkSlot,
            visibleSlot = visibleSlot,
            boundarySerial = 10,
        )
        assertSame(
            ElytraMotionHotbarRestoreDecision.WAIT,
            state.resolveRestore(PLAYER, LEVEL, visibleSlot, false, 10, 1_000L),
        )
        assertEquals(listOf("HELD 6", "USE_ITEM"), interaction.packets)

        assertEquals(
            ElytraMotionHotbarRestoreDecision.RESTORE(visibleSlot),
            state.resolveRestore(PLAYER, LEVEL, visibleSlot, true, 11, 2_000L),
        )
        interaction.ensureHasSentCarriedItem(override.resolveSlot(visibleSlot))
        interaction.ensureHasSentCarriedItem(override.resolveSlot(visibleSlot))

        assertEquals(listOf("HELD 6", "USE_ITEM", "HELD 2"), interaction.packets)
        assertEquals(visibleSlot, interaction.carriedSlot)
    }

    @Test
    fun oneShotOverrideLeavesAllLocalSlotResolutionUnchanged() {
        val override = CarriedSlotSyncOverrideState()
        val visibleSlot = 1
        val ordinarySilentHotbarSlot = 4
        val localSelectedItemSlot = visibleSlot
        val heldItemRenderSlot = visibleSlot

        override.withSlot(OWNER, 7) {
            assertEquals(7, override.resolveSlot(ordinarySilentHotbarSlot))
            assertEquals(visibleSlot, localSelectedItemSlot)
            assertEquals(visibleSlot, heldItemRenderSlot)
        }

        assertEquals(ordinarySilentHotbarSlot, override.resolveSlot(ordinarySilentHotbarSlot))
        assertEquals(visibleSlot, localSelectedItemSlot)
        assertEquals(visibleSlot, heldItemRenderSlot)
    }

    @Test
    fun overrideIsMainThreadOnlyOwnerScopedAndAlwaysRemoved() {
        val override = CarriedSlotSyncOverrideState()
        val otherOwner = Any()

        assertThrows(IllegalStateException::class.java) {
            override.withSlot(OWNER, 5, mainThread = false) { Unit }
        }
        assertThrows(IllegalArgumentException::class.java) {
            override.withSlot(OWNER, 9) { Unit }
        }

        override.withSlot(OWNER, 5) {
            assertThrows(IllegalStateException::class.java) {
                override.withSlot(otherOwner, 3) { Unit }
            }
            assertThrows(IllegalStateException::class.java) {
                override.withSlot(OWNER, 5) { Unit }
            }
            assertEquals(5, override.resolveSlot(2))
        }
        assertEquals(2, override.resolveSlot(2))

        assertThrows(ExpectedFailure::class.java) {
            override.withSlot(OWNER, 8) { throw ExpectedFailure() }
        }
        assertEquals(2, override.resolveSlot(2))
    }

    @Test
    fun alreadySelectedFireworkUsesOnlyRawUseWithoutPendingRestore() {
        val interaction = FakeInteractionManager(initialCarriedSlot = 4)

        assertFalse(elytraMotionNeedsCarriedSlotSync(fireworkSlot = 4, visibleSlot = 4))
        interaction.rawUseItem()

        assertEquals(listOf("USE_ITEM"), interaction.packets)
        assertEquals(4, interaction.carriedSlot)
        assertNull(ElytraMotionHotbarRestoreState().pending)
    }

    @Test
    fun cancelledRawUseStillWaitsForBoundaryAndRestores() {
        val state = stateWithPending(PLAYER, LEVEL, fireworkSlot = 5, visibleSlot = 0, boundarySerial = 30)

        assertSame(
            ElytraMotionHotbarRestoreDecision.WAIT,
            state.resolveRestore(PLAYER, LEVEL, 0, false, 30, 1_000L),
        )
        assertEquals(
            ElytraMotionHotbarRestoreDecision.RESTORE(0),
            state.resolveRestore(PLAYER, LEVEL, 0, true, 31, 2_000L),
        )
    }

    @Test
    fun disableDoesNotDiscardPendingRestore() {
        val state = stateWithPending(PLAYER, LEVEL, fireworkSlot = 5, visibleSlot = 0, boundarySerial = 20)
        val pendingBeforeDisable = state.pending

        // Module disable has no state mutation hook; the always-running cycle retains ownership.
        assertSame(pendingBeforeDisable, state.pending)
        assertEquals(
            ElytraMotionHotbarRestoreDecision.RESTORE(0),
            state.resolveRestore(PLAYER, LEVEL, 0, true, 21, 2_000L),
        )
    }

    @Test
    fun currentVisibleSlotWinsAndCapturedSlotIsFallback() {
        val state = stateWithPending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = 2, boundarySerial = 40)

        assertEquals(
            ElytraMotionHotbarRestoreDecision.RESTORE(8),
            state.resolveRestore(PLAYER, LEVEL, 8, true, 41, 2_000L),
        )
        assertEquals(
            ElytraMotionHotbarRestoreDecision.RESTORE(2),
            state.resolveRestore(PLAYER, LEVEL, -1, true, 41, 2_000L),
        )
    }

    @Test
    fun secondCycleCannotOverwritePendingRestore() {
        val state = ElytraMotionHotbarRestoreState()
        val first = pending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = 2, boundarySerial = 10)
        val second = pending(PLAYER, LEVEL, fireworkSlot = 7, visibleSlot = 3, boundarySerial = 10)

        assertTrue(state.begin(first))
        assertFalse(state.begin(second))
        assertSame(first, state.pending)
    }

    @Test
    fun replacementAndLifecycleClearNeverProduceRestoreDecision() {
        val state = stateWithPending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = 2, boundarySerial = 10)

        assertEquals(
            ElytraMotionHotbarRestoreDecision.DROP("player or level replaced"),
            state.resolveRestore(Any(), LEVEL, 2, true, 11, 2_000L),
        )
        assertSame(state.pending, state.clear())
        assertNull(state.pending)

        val worldChangeState = stateWithPending(PLAYER, LEVEL, 6, 2, 10)
        assertTrue(worldChangeState.clear() != null)
        assertNull(worldChangeState.pending)

        val disconnectState = stateWithPending(PLAYER, LEVEL, 6, 2, 10)
        assertTrue(disconnectState.clear() != null)
        assertNull(disconnectState.pending)
    }

    @Test
    fun invalidSlotsAndBoundedTimeoutCannotRemainPendingForever() {
        val state = ElytraMotionHotbarRestoreState()
        assertFalse(state.begin(pending(PLAYER, LEVEL, fireworkSlot = 9, visibleSlot = 2, boundarySerial = 10)))
        assertFalse(state.begin(pending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = -1, boundarySerial = 10)))
        assertFalse(state.begin(pending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = 2, boundarySerial = Long.MIN_VALUE)))

        assertTrue(state.begin(pending(PLAYER, LEVEL, fireworkSlot = 6, visibleSlot = 2, boundarySerial = 10)))
        assertEquals(
            ElytraMotionHotbarRestoreDecision.DROP("restore timeout"),
            state.resolveRestore(PLAYER, LEVEL, 2, false, currentTick = 101, nowNanos = 1_000L),
        )
        state.clear()
        assertNull(state.pending)
    }

    private fun stateWithPending(
        player: Any,
        level: Any,
        fireworkSlot: Int,
        visibleSlot: Int,
        boundarySerial: Long,
    ) = ElytraMotionHotbarRestoreState().also {
        assertTrue(it.begin(pending(player, level, fireworkSlot, visibleSlot, boundarySerial)))
    }

    private fun pending(
        player: Any,
        level: Any,
        fireworkSlot: Int,
        visibleSlot: Int,
        boundarySerial: Long,
    ) = ElytraMotionHotbarPendingRestore(
        playerIdentity = player,
        levelIdentity = level,
        fireworkSlot = fireworkSlot,
        capturedVisibleSlot = visibleSlot,
        postUseBoundarySerial = boundarySerial,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0L,
    )

    private class FakeInteractionManager(initialCarriedSlot: Int) {
        val packets = mutableListOf<String>()
        var carriedSlot = initialCarriedSlot
            private set

        fun ensureHasSentCarriedItem(selectedSlot: Int) {
            if (selectedSlot == carriedSlot) {
                return
            }
            carriedSlot = selectedSlot
            packets += "HELD $selectedSlot"
        }

        fun rawUseItem() {
            packets += "USE_ITEM"
        }
    }

    private class ExpectedFailure : RuntimeException()

    private companion object {
        val OWNER = Any()
        val PLAYER = Any()
        val LEVEL = Any()
    }
}
