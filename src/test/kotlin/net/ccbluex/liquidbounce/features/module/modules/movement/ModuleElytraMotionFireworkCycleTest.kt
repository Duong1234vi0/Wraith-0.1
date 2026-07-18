package net.ccbluex.liquidbounce.features.module.modules.movement

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModuleElytraMotionFireworkCycleTest {

    @Test
    fun directHotbarWaitsForBoundaryThenNextGameTickRestore() {
        val packets = mutableListOf("HELD 6", "USE_ITEM")
        val state = stateWithPending(directCycle(fireworkSlot = 6, visibleSlot = 2))

        assertEquals(ElytraMotionFireworkCycleDecision.WAIT, prepare(state, currentVisibleSlot = 2))
        assertEquals(listOf("HELD 6", "USE_ITEM"), packets)

        assertTrue(state.observeNaturalBoundary(serial = 41L, tick = 10))
        packets += "CLIENT_TICK_END"
        assertEquals(listOf("HELD 6", "USE_ITEM", "CLIENT_TICK_END"), packets)
        assertTrue(state.pending?.boundaryObserved == true)
        assertEquals(41L, state.pending?.boundarySerial)

        val decision = prepare(state, currentVisibleSlot = 2)
        assertEquals(ElytraMotionFireworkCycleDecision.RESTORE(2), decision)
        val pending = state.pending!!
        assertTrue(pending.heldRestoreClaimed)
        assertFalse(pending.heldRestorationCompleted)

        packets += "HELD ${(decision as ElytraMotionFireworkCycleDecision.RESTORE).slot}"
        assertTrue(state.markHeldRestorationCompleted(pending))
        assertFalse(state.markHeldRestorationCompleted(pending))
        assertSame(pending, state.clear())
        assertNull(state.pending)
        assertEquals(
            listOf("HELD 6", "USE_ITEM", "CLIENT_TICK_END", "HELD 2"),
            packets,
        )
    }

    @Test
    fun mainInventoryRestoresHeldThenClaimsExactlyOneSwapBack() {
        val packets = mutableListOf("SWAP_IN 35 3", "HELD 3", "USE_ITEM")
        val state = stateWithPending(inventoryCycle(inventorySlot = 35, temporarySlot = 3, visibleSlot = 2))
        val pending = state.pending!!

        assertTrue(pending.inventorySwapInCompleted)
        assertTrue(pending.inventorySwapBackPending)
        assertTrue(state.observeNaturalBoundary(serial = 9L, tick = 4))
        packets += "CLIENT_TICK_END"

        val decision = prepare(state, currentVisibleSlot = 2)
        assertEquals(ElytraMotionFireworkCycleDecision.RESTORE(2), decision)
        packets += "HELD 2"
        assertTrue(state.markHeldRestorationCompleted(pending))
        assertTrue(state.claimInventorySwapBack(pending))
        assertFalse(state.claimInventorySwapBack(pending))
        packets += "SWAP_BACK 35 3"

        assertTrue(pending.heldRestorationCompleted)
        assertFalse(pending.inventorySwapBackPending)
        assertTrue(pending.inventorySwapBackCompleted)
        assertEquals(
            listOf(
                "SWAP_IN 35 3",
                "HELD 3",
                "USE_ITEM",
                "CLIENT_TICK_END",
                "HELD 2",
                "SWAP_BACK 35 3",
            ),
            packets,
        )
    }

    @Test
    fun manualVisibleSlotChangeWinsAndCapturedSlotIsFallback() {
        val changed = stateWithObservedBoundary(directCycle(6, 2))
        assertEquals(ElytraMotionFireworkCycleDecision.RESTORE(8), prepare(changed, 8))

        val invalidCurrent = stateWithObservedBoundary(directCycle(6, 2))
        assertEquals(ElytraMotionFireworkCycleDecision.RESTORE(2), prepare(invalidCurrent, -1))
    }

    @Test
    fun moduleDisableDoesNotDiscardAlwaysRunningCycle() {
        val state = stateWithPending(directCycle(5, 0))
        val pendingBeforeDisable = state.pending

        // Module disable only clears movement freeze; the always-running owner retains this cycle.
        assertSame(pendingBeforeDisable, state.pending)
        assertTrue(state.observeNaturalBoundary(1L, 1))
        assertEquals(ElytraMotionFireworkCycleDecision.RESTORE(0), prepare(state, 0))
    }

    @Test
    fun playerLevelAndConnectionReplacementDropWithoutRestore() {
        val playerReplacement = stateWithPending(directCycle(6, 2))
        assertEquals(
            ElytraMotionFireworkCycleDecision.DROP("local player replaced"),
            playerReplacement.prepareNextTickRestore(Any(), LEVEL, CONNECTION, 2, 1, 1L),
        )
        assertNull(playerReplacement.pending)

        val levelReplacement = stateWithPending(directCycle(6, 2))
        assertEquals(
            ElytraMotionFireworkCycleDecision.DROP("level replaced"),
            levelReplacement.prepareNextTickRestore(PLAYER, Any(), CONNECTION, 2, 1, 1L),
        )
        assertNull(levelReplacement.pending)

        val connectionReplacement = stateWithPending(directCycle(6, 2))
        assertEquals(
            ElytraMotionFireworkCycleDecision.DROP("connection replaced"),
            connectionReplacement.prepareNextTickRestore(PLAYER, LEVEL, Any(), 2, 1, 1L),
        )
        assertNull(connectionReplacement.pending)
    }

    @Test
    fun worldOrDisconnectCleanupClearsWithoutProducingRestore() {
        val worldChange = stateWithPending(directCycle(6, 2))
        assertTrue(worldChange.clear() != null)
        assertNull(worldChange.pending)

        val disconnect = stateWithPending(inventoryCycle(35, 3, 2))
        assertTrue(disconnect.clear() != null)
        assertNull(disconnect.pending)
    }

    @Test
    fun invalidSlotsTimeoutDuplicateBoundaryAndSecondCycleAreRejected() {
        val state = ElytraMotionFireworkCycleState()
        assertFalse(state.begin(directCycle(fireworkSlot = 9, visibleSlot = 2)))
        assertFalse(state.begin(directCycle(fireworkSlot = 6, visibleSlot = -1)))
        assertFalse(state.begin(inventoryCycle(inventorySlot = 8, temporarySlot = 3, visibleSlot = 2)))

        val first = directCycle(6, 2)
        assertTrue(state.begin(first))
        assertFalse(state.begin(directCycle(7, 3)))
        assertSame(first, state.pending)
        assertTrue(state.observeNaturalBoundary(1L, 1))
        assertFalse(state.observeNaturalBoundary(2L, 1))

        val timedOut = stateWithPending(directCycle(6, 2))
        assertEquals(
            ElytraMotionFireworkCycleDecision.DROP("cycle timeout"),
            timedOut.prepareNextTickRestore(PLAYER, LEVEL, CONNECTION, 2, 21, 1L),
        )
        assertNull(timedOut.pending)
    }

    @Test
    fun directHotbarSourceKeepsHeldAndRawUseImmediateButArmsAfterUse() {
        val direct = section("private fun useDirectHotbarFirework", "private fun useTemporaryInventoryFirework")
        val initialHeld = direct.indexOf("sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))")
        val rawUse = direct.indexOf("useFireworkFromHand(InteractionHand.MAIN_HAND)", initialHeld)
        val arm = direct.indexOf("ElytraMotionFireworkCycleOwner.arm", rawUse)

        assertTrue(initialHeld >= 0 && initialHeld < rawUse && rawUse < arm)
        assertEquals(1, Regex("sendPacket\\(ServerboundSetCarriedItemPacket").findAll(direct).count())
        assertFalse(direct.substring(rawUse, arm).contains("ServerboundSetCarriedItemPacket"))

        val alreadySelected = direct.substring(
            direct.indexOf("if (fireworkSlot == visibleSlot)"),
            direct.indexOf("val diagnosticsEnabled"),
        )
        assertTrue(alreadySelected.contains("useFireworkFromHand(InteractionHand.MAIN_HAND)"))
        assertFalse(alreadySelected.contains("ServerboundSetCarriedItemPacket"))
        assertFalse(alreadySelected.contains(".arm("))
    }

    @Test
    fun postSendListenerOnlyObservesAndNextGameTickHandlerOwnsPackets() {
        val listener = section("private object ElytraMotionFireworkCycleOwner", "internal enum class")
        val postSend = listener.substring(
            listener.indexOf("private val postSendHandler"),
            listener.indexOf("private val restoreHandler"),
        )
        val restore = listener.substring(
            listener.indexOf("private val restoreHandler"),
            listener.indexOf("private val worldChangeHandler"),
        )

        assertTrue(postSend.contains("handler<OutgoingPacketPostSendEvent>"))
        assertTrue(postSend.contains("state.observeNaturalBoundary"))
        assertTrue(postSend.contains("restoration deferred to next GameTick HEAD"))
        assertFalse(postSend.contains("sendPacket("))
        assertFalse(postSend.contains("clickElytraMotionSwap("))

        assertTrue(restore.contains("handler<GameTickEvent>(priority = (FIRST_PRIORITY + 3).toShort())"))
        val heldRestore = restore.indexOf("sendPacket(ServerboundSetCarriedItemPacket(decision.slot))")
        val swapBack = restore.indexOf("ModuleElytraMotion.clickElytraMotionSwap(")
        assertTrue(heldRestore >= 0 && heldRestore < swapBack)
        assertTrue(restore.indexOf("prepareNextTickRestore") < heldRestore)
    }

    @Test
    fun mainInventoryUseDefersBothReturnActionsAndPreservesSearchAndTimerSemantics() {
        val source = source()
        val inventory = section("private fun useTemporaryInventoryFirework", "private fun useFireworkWithOffhandSwap")
        val swapIn = inventory.indexOf("clickElytraMotionSwap(originalInventorySlot, temporaryHotbarSlot)")
        val initialHeld = inventory.indexOf("sendPacket(ServerboundSetCarriedItemPacket(temporaryHotbarSlot))")
        val rawUse = inventory.indexOf("useFireworkFromHand(InteractionHand.MAIN_HAND)")
        val arm = inventory.indexOf("ElytraMotionFireworkCycleOwner.arm")

        assertTrue(swapIn >= 0 && swapIn < initialHeld && initialHeld < rawUse && rawUse < arm)
        assertEquals(1, Regex("clickElytraMotionSwap\\(").findAll(inventory).count())
        assertEquals(1, Regex("ServerboundSetCarriedItemPacket").findAll(inventory).count())
        assertTrue(source.indexOf("for (slot in 35 downTo 9)") < source.indexOf("for (slot in 8 downTo 0)"))
        assertTrue(source.contains("val temporarySlot = currentSlot % 8 + 1"))
        assertTrue(source.contains("if (matrixBypass && hasFireworkIntervalElapsed())"))
        assertTrue(source.contains("autoFirework && target != null && getBps(player) <= 30.0"))
        assertEquals(
            2,
            Regex("useFirework\\(\\)\\s*\\n\\s*fireworkTimerNanos = System\\.nanoTime\\(\\)")
                .findAll(source).count(),
        )
        assertTrue(source.contains("private const val FIREWORK_INTERVAL_MS = 500L"))
    }

    @Test
    fun offhandAndForbiddenPathsRemainAbsent() {
        val source = source()
        val directOffhand = source.substring(
            source.indexOf("if (player.offhandItem.item == Items.FIREWORK_ROCKET)"),
            source.indexOf("val firework = findFireworkInventoryFirst()"),
        )

        assertTrue(directOffhand.contains("useFireworkFromHand(InteractionHand.OFF_HAND)"))
        assertFalse(directOffhand.contains("ServerboundSetCarriedItemPacket"))
        assertFalse(source.contains("OutgoingPacketSentEvent"))
        assertFalse(source.contains("ServerboundClientTickEndPacket("))
        assertFalse(source.contains("SilentHotbar"))
        assertFalse(source.contains("CarriedSlotSyncOverride"))
        assertFalse(source.contains("ServerCarriedSlotLease"))
        assertFalse(source.contains("ensureHasSentCarriedItem"))
        assertFalse(source.contains("interaction.interactItem("))
        assertFalse(source.contains("InteractionResult"))
    }

    private fun stateWithPending(cycle: ElytraMotionFireworkCycle) =
        ElytraMotionFireworkCycleState().also { assertTrue(it.begin(cycle)) }

    private fun stateWithObservedBoundary(cycle: ElytraMotionFireworkCycle) =
        stateWithPending(cycle).also { assertTrue(it.observeNaturalBoundary(1L, 1)) }

    private fun prepare(
        state: ElytraMotionFireworkCycleState,
        currentVisibleSlot: Int,
    ) = state.prepareNextTickRestore(
        PLAYER,
        LEVEL,
        CONNECTION,
        currentVisibleSlot,
        currentTick = 1,
        nowNanos = 1L,
    )

    private fun directCycle(fireworkSlot: Int, visibleSlot: Int) = ElytraMotionFireworkCycle(
        source = ElytraMotionFireworkSource.HOTBAR,
        playerIdentity = PLAYER,
        levelIdentity = LEVEL,
        connectionIdentity = CONNECTION,
        originalInventorySlot = fireworkSlot,
        fireworkHotbarSlot = fireworkSlot,
        capturedVisibleSlot = visibleSlot,
        temporaryHotbarSlot = null,
        inventorySwapInCompleted = false,
        rawUseSent = true,
        inventorySwapBackPending = false,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0L,
    )

    private fun inventoryCycle(
        inventorySlot: Int,
        temporarySlot: Int,
        visibleSlot: Int,
    ) = ElytraMotionFireworkCycle(
        source = ElytraMotionFireworkSource.MAIN_INVENTORY,
        playerIdentity = PLAYER,
        levelIdentity = LEVEL,
        connectionIdentity = CONNECTION,
        originalInventorySlot = inventorySlot,
        fireworkHotbarSlot = temporarySlot,
        capturedVisibleSlot = visibleSlot,
        temporaryHotbarSlot = temporarySlot,
        inventorySwapInCompleted = true,
        rawUseSent = true,
        inventorySwapBackPending = true,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0L,
    )

    private fun section(start: String, end: String): String {
        val source = source()
        return source.substring(source.indexOf(start), source.indexOf(end))
    }

    private fun source() = Files.readString(
        Path.of(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt"
        )
    )

    private companion object {
        val PLAYER = Any()
        val LEVEL = Any()
        val CONNECTION = Any()
    }
}
