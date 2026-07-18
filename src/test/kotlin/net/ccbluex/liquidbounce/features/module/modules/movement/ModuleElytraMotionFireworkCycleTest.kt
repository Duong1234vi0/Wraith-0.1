package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.elytraMotionAllowsKillAuraMainHandAction
import net.ccbluex.liquidbounce.utils.client.isSilentHotbarSelectionCompatible
import net.ccbluex.liquidbounce.utils.client.resolveSilentHotbarServerSlot
import net.minecraft.world.InteractionHand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModuleElytraMotionFireworkCycleTest {

    @Test
    fun hotbarLeaseKeepsLocalResolutionAndAuraAlignedUntilOneBoundaryRestore() {
        assertEquals(
            listOf(ElytraMotionFireworkPacketStep.HELD_FIREWORK, ElytraMotionFireworkPacketStep.USE_ITEM),
            elytraMotionHotbarStartSteps(fireworkSlot = 6, visibleSlot = 2),
        )
        assertEquals(6, resolveSilentHotbarServerSlot(enforcedSlot = 6, visibleSlot = 2))

        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 6, returnSlot = 2)
        state.completeUse(boundarySerial = 10, observed = true, accepted = true)

        assertTrue(state.pending!!.usePacketObserved)
        assertTrue(state.pending!!.interactionAccepted)
        assertTrue(state.isMainHandActionActive(currentSerial = 10))
        assertFalse(elytraMotionAllowsKillAuraMainHandAction(state.isMainHandActionActive(10)))
        assertSame(
            ElytraMotionFireworkRestoreDecision.WAIT,
            state.resolveRestore(player, level, 2, currentSerial = 10, expectedServerSlot = 6),
        )
        assertEquals(
            ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(slot = 2, emitsHeldPacket = true),
            state.resolveRestore(player, level, 2, currentSerial = 11, expectedServerSlot = 6),
        )

        state.pending!!.leaseOwned = false
        assertFalse(state.isMainHandActionActive(currentSerial = 11))
        assertTrue(elytraMotionAllowsKillAuraMainHandAction(state.isMainHandActionActive(11)))
    }

    @Test
    fun alreadySelectedFireworkUsesWithoutLeaseOrHeldPacket() {
        assertEquals(
            listOf(ElytraMotionFireworkPacketStep.USE_ITEM),
            elytraMotionHotbarStartSteps(fireworkSlot = 4, visibleSlot = 4),
        )
        assertEquals(4, resolveSilentHotbarServerSlot(enforcedSlot = null, visibleSlot = 4))
    }

    @Test
    fun anotherSilentHotbarOwnerCannotBeOverwrittenByFireworkLease() {
        val otherOwner = Any()
        val fireworkOwner = Any()

        assertFalse(
            isSilentHotbarSelectionCompatible(
                currentRequester = otherOwner,
                leaseRequester = null,
                requestedBy = fireworkOwner,
            )
        )
        assertFalse(
            isSilentHotbarSelectionCompatible(
                currentRequester = otherOwner,
                leaseRequester = otherOwner,
                requestedBy = fireworkOwner,
            )
        )
        assertTrue(
            isSilentHotbarSelectionCompatible(
                currentRequester = fireworkOwner,
                leaseRequester = fireworkOwner,
                requestedBy = fireworkOwner,
            )
        )
    }

    @Test
    fun timerOnlyConsumesAcceptedObservedUseResult() {
        val timer = ElytraMotionFireworkTimer(initialSuccessfulUseNanos = 0L)
        val elapsed = 600_000_000L

        assertTrue(timer.hasElapsed(elapsed))
        assertFalse(timer.record(FireworkUseResult.BLOCKED, elapsed))
        assertFalse(timer.record(FireworkUseResult.NO_ITEM, elapsed))
        assertFalse(timer.record(FireworkUseResult.FAILED, elapsed))
        assertFalse(timer.record(FireworkUseResult.STARTED_PENDING, elapsed))
        assertEquals(0L, timer.lastSuccessfulUseNanos())

        assertTrue(timer.record(FireworkUseResult.USED, elapsed))
        assertEquals(elapsed, timer.lastSuccessfulUseNanos())
        assertFalse(timer.hasElapsed(elapsed + 499_000_000L))
        assertTrue(timer.hasElapsed(elapsed + 500_000_000L))
    }

    @Test
    fun retryGuardAllowsAtMostOneAttemptPerPlayerGameTick() {
        val guard = ElytraMotionFireworkAttemptGuard()
        val player = Any()

        assertTrue(guard.tryAcquire(player, currentTick = 40))
        assertFalse(guard.tryAcquire(player, currentTick = 40))
        assertTrue(guard.tryAcquire(player, currentTick = 41))
        assertTrue(guard.tryAcquire(Any(), currentTick = 41))
    }

    @Test
    fun directOffhandWindowBlocksAuraButNeverClaimsMainHandWindow() {
        val window = ElytraMotionFireworkActionWindow(
            playerIdentity = Any(),
            levelIdentity = Any(),
            hand = InteractionHand.OFF_HAND,
            boundarySerial = 70,
            createdTick = 70,
            createdNanos = 0,
        )

        assertTrue(window.isActive(currentSerial = 70, mainHandOnly = false))
        assertFalse(window.isActive(currentSerial = 70, mainHandOnly = true))
        assertFalse(elytraMotionAllowsKillAuraMainHandAction(window.isActive(70, mainHandOnly = false)))
        assertTrue(elytraMotionAllowsKillAuraMainHandAction(window.isActive(71, mainHandOnly = false)))
    }

    @Test
    fun mainInventoryOrderIsSwapInLeaseUseBoundaryHeldRestoreThenSwapBack() {
        val player = Any()
        val level = Any()
        val cycle = pending(
            player = player,
            level = level,
            fireworkSlot = 3,
            returnSlot = 1,
            inventorySourceSlot = 20,
            stage = ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_IN,
            leaseOwned = false,
            source = FireworkSource.MAIN_INVENTORY,
        )
        val state = ElytraMotionFireworkCycleState()
        assertTrue(state.begin(cycle))

        cycle.inventorySwapObserved = true
        cycle.leaseOwned = true
        state.completeUse(boundarySerial = 80, observed = true, accepted = true)
        assertEquals(ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY, cycle.stage)
        assertTrue(state.isMainHandActionActive(80))
        assertEquals(
            ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(1, emitsHeldPacket = true),
            state.resolveRestore(player, level, 1, currentSerial = 81, expectedServerSlot = 3),
        )

        cycle.leaseOwned = false
        cycle.stage = ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK
        cycle.inventoryRestoreObserved = true
        assertEquals(ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK, cycle.stage)
        assertTrue(cycle.inventoryRestoreObserved)
    }

    @Test
    fun cancelledUseStillRestoresSafelyWithoutConsumingTimer() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 0)
        state.completeUse(boundarySerial = 30, observed = false, accepted = false)

        assertFalse(state.pending!!.usePacketObserved)
        assertFalse(state.pending!!.interactionAccepted)
        assertSame(
            ElytraMotionFireworkRestoreDecision.WAIT,
            state.resolveRestore(player, level, 0, currentSerial = 30, expectedServerSlot = 5),
        )
        assertEquals(
            ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(0, emitsHeldPacket = true),
            state.resolveRestore(player, level, 0, currentSerial = 31, expectedServerSlot = 5),
        )
        val timer = ElytraMotionFireworkTimer(0L)
        assertFalse(timer.record(FireworkUseResult.FAILED, 600_000_000L))
        assertTrue(timer.hasElapsed(600_000_000L))
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
    fun currentVisibleFireworkStillRunsOneSyncCallWithoutDuplicateHeldPacket() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 3)
        state.completeUse(boundarySerial = 50, observed = true, accepted = true)

        assertEquals(
            ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(5, emitsHeldPacket = false),
            state.resolveRestore(player, level, currentVisibleSlot = 5, currentSerial = 51, expectedServerSlot = 5),
        )
    }

    @Test
    fun manualVisibleSlotChangeBecomesDeferredSynchronizationTarget() {
        val player = Any()
        val level = Any()
        val state = stateWithPending(player, level, fireworkSlot = 5, returnSlot = 0)
        state.completeUse(boundarySerial = 40, observed = true, accepted = true)

        assertEquals(
            ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(8, emitsHeldPacket = true),
            state.resolveRestore(player, level, currentVisibleSlot = 8, currentSerial = 41, expectedServerSlot = 5),
        )
    }

    @Test
    fun lifecycleReplacementDropsStateWithoutRestoreIntoNewSession() {
        val originalPlayer = Any()
        val level = Any()
        val state = stateWithPending(originalPlayer, level, fireworkSlot = 5, returnSlot = 3)
        state.completeUse(boundarySerial = 60, observed = true, accepted = true)

        assertSame(
            ElytraMotionFireworkRestoreDecision.DROP,
            state.resolveRestore(Any(), level, currentVisibleSlot = 3, currentSerial = 61, expectedServerSlot = 5),
        )
        assertNull(state.pending)
    }

    @Test
    fun explicitClearReleasesPendingStateModel() {
        val state = stateWithPending(Any(), Any(), fireworkSlot = 5, returnSlot = 0)

        state.clear()

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
        inventorySourceSlot: Int? = null,
        stage: ElytraMotionFireworkCycleStage = ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY,
        leaseOwned: Boolean = true,
        source: FireworkSource = FireworkSource.HOTBAR,
    ) = ElytraMotionFireworkPendingCycle(
        playerIdentity = player,
        levelIdentity = level,
        fireworkSlot = fireworkSlot,
        capturedReturnSlot = returnSlot,
        inventorySourceSlot = inventorySourceSlot,
        originalTemporaryItem = null,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0,
        source = source,
        stage = stage,
        leaseOwned = leaseOwned,
    )
}
