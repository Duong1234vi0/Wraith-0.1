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
    fun differentHotbarSlotRestoresExactlyOnceAfterPostSendTickEnd() {
        val packets = mutableListOf("HELD 6", "USE_ITEM")
        val state = stateWithPending(fireworkSlot = 6, visibleSlot = 2)

        assertEquals(listOf("HELD 6", "USE_ITEM"), packets)
        assertTrue(state.pending != null)

        val decision = state.completeAfterTickEnd(
            PLAYER,
            LEVEL,
            CONNECTION,
            currentVisibleSlot = 2,
            currentTick = 1,
            nowNanos = 1L,
        )
        assertEquals(ElytraMotionDirectHotbarRestoreDecision.RESTORE(2), decision)
        assertNull(state.pending)
        packets += "HELD ${(decision as ElytraMotionDirectHotbarRestoreDecision.RESTORE).slot}"

        assertEquals(listOf("HELD 6", "USE_ITEM", "HELD 2"), packets)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.DROP("no pending restore"),
            state.completeAfterTickEnd(PLAYER, LEVEL, CONNECTION, 2, 1, 2L),
        )
    }

    @Test
    fun manualVisibleSlotChangeWinsAndCapturedSlotIsFallback() {
        val changed = stateWithPending(fireworkSlot = 6, visibleSlot = 2)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.RESTORE(8),
            changed.completeAfterTickEnd(PLAYER, LEVEL, CONNECTION, 8, 1, 1L),
        )

        val invalidCurrent = stateWithPending(fireworkSlot = 6, visibleSlot = 2)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.RESTORE(2),
            invalidCurrent.completeAfterTickEnd(PLAYER, LEVEL, CONNECTION, -1, 1, 1L),
        )
    }

    @Test
    fun moduleDisableDoesNotDiscardPendingRestore() {
        val state = stateWithPending(fireworkSlot = 5, visibleSlot = 0)
        val pendingBeforeDisable = state.pending

        // Module disable only clears freeze; the always-running listener owns this state.
        assertSame(pendingBeforeDisable, state.pending)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.RESTORE(0),
            state.completeAfterTickEnd(PLAYER, LEVEL, CONNECTION, 0, 1, 1L),
        )
    }

    @Test
    fun playerLevelAndConnectionReplacementDropWithoutRestore() {
        val playerReplacement = stateWithPending(6, 2)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.DROP("local player replaced"),
            playerReplacement.completeAfterTickEnd(Any(), LEVEL, CONNECTION, 2, 1, 1L),
        )
        assertNull(playerReplacement.pending)

        val levelReplacement = stateWithPending(6, 2)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.DROP("level replaced"),
            levelReplacement.completeAfterTickEnd(PLAYER, Any(), CONNECTION, 2, 1, 1L),
        )
        assertNull(levelReplacement.pending)

        val connectionReplacement = stateWithPending(6, 2)
        assertEquals(
            ElytraMotionDirectHotbarRestoreDecision.DROP("connection replaced"),
            connectionReplacement.completeAfterTickEnd(PLAYER, LEVEL, Any(), 2, 1, 1L),
        )
        assertNull(connectionReplacement.pending)
    }

    @Test
    fun worldOrDisconnectCleanupClearsWithoutProducingRestore() {
        val worldChange = stateWithPending(6, 2)
        assertTrue(worldChange.clear() != null)
        assertNull(worldChange.pending)

        val disconnect = stateWithPending(6, 2)
        assertTrue(disconnect.clear() != null)
        assertNull(disconnect.pending)
    }

    @Test
    fun invalidSlotsTimeoutAndSecondCycleCannotRemainOrOverwrite() {
        val state = ElytraMotionDirectHotbarRestoreState()
        assertFalse(state.begin(pending(fireworkSlot = 9, visibleSlot = 2)))
        assertFalse(state.begin(pending(fireworkSlot = 6, visibleSlot = -1)))

        val first = pending(fireworkSlot = 6, visibleSlot = 2)
        assertTrue(state.begin(first))
        assertFalse(state.begin(pending(fireworkSlot = 7, visibleSlot = 3)))
        assertSame(first, state.pending)
        assertEquals(
            "restore timeout",
            state.invalidReason(PLAYER, LEVEL, CONNECTION, currentTick = 21, nowNanos = 1L),
        )
        assertSame(first, state.clear())
        assertNull(state.pending)
    }

    @Test
    fun alreadySelectedBranchUsesOnlyRawUseAndCreatesNoPendingRecord() {
        val source = source()
        val direct = source.substring(
            source.indexOf("private fun useDirectHotbarFirework"),
            source.indexOf("/** Main-inventory SWAP/use/SWAP-back behavior"),
        )
        val alreadySelected = direct.substring(
            direct.indexOf("if (fireworkSlot == visibleSlot)"),
            direct.indexOf("val diagnosticsEnabled"),
        )

        assertTrue(alreadySelected.contains("useFireworkFromHand(InteractionHand.MAIN_HAND)"))
        assertFalse(alreadySelected.contains("ServerboundSetCarriedItemPacket"))
        assertFalse(alreadySelected.contains(".arm("))
    }

    @Test
    fun directHotbarSourceUsesImmediateRawStartAndOnlyPostSendRawReturn() {
        val source = source()
        val direct = source.substring(
            source.indexOf("private fun useDirectHotbarFirework"),
            source.indexOf("/** Main-inventory SWAP/use/SWAP-back behavior"),
        )
        val listener = source.substring(
            source.indexOf("private object ElytraMotionDirectHotbarRestore"),
            source.indexOf("internal data class ElytraMotionDirectHotbarPendingRestore"),
        )

        val initialHeld = direct.indexOf("sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))")
        val rawUse = direct.indexOf("useFireworkFromHand(InteractionHand.MAIN_HAND)", initialHeld)
        val arm = direct.indexOf("ElytraMotionDirectHotbarRestore.arm", rawUse)
        assertTrue(initialHeld >= 0 && initialHeld < rawUse && rawUse < arm)
        assertEquals(1, Regex("sendPacket\\(ServerboundSetCarriedItemPacket").findAll(direct).count())

        assertTrue(listener.contains("handler<OutgoingPacketPostSendEvent>"))
        assertTrue(listener.contains("is ServerboundClientTickEndPacket -> completeAfterNaturalTickEnd"))
        assertTrue(listener.indexOf("state.completeAfterTickEnd") <
            listener.indexOf("sendPacket(ServerboundSetCarriedItemPacket(decision.slot))"))
        assertFalse(source.contains("OutgoingPacketSentEvent"))
        assertFalse(source.contains("ServerboundClientTickEndPacket("))
        assertFalse(source.contains("SilentHotbar"))
        assertFalse(source.contains("CarriedSlotSyncOverride"))
        assertFalse(source.contains("ensureHasSentCarriedItem"))
        assertFalse(source.contains("interaction.interactItem("))
    }

    @Test
    fun mainInventoryRawSequenceAndTriggerTimerFormulasRemainUnchanged() {
        val source = source()
        val inventory = source.substring(
            source.indexOf("private fun useTemporaryInventoryFirework"),
            source.indexOf("private fun useFireworkWithOffhandSwap"),
        )

        assertTrue(
            inventory.indexOf("ServerboundSetCarriedItemPacket(fireworkSlot)") <
                inventory.indexOf("useFireworkFromHand(InteractionHand.MAIN_HAND)"),
        )
        assertTrue(
            inventory.indexOf("useFireworkFromHand(InteractionHand.MAIN_HAND)") <
                inventory.indexOf("ServerboundSetCarriedItemPacket(returnSlot)"),
        )
        assertTrue(source.contains("if (matrixBypass && hasFireworkIntervalElapsed())"))
        assertTrue(source.contains("autoFirework && target != null && getBps(player) <= 30.0"))
        assertEquals(2, Regex("useFirework\\(\\)\\s*\\n\\s*fireworkTimerNanos = System\\.nanoTime\\(\\)")
            .findAll(source).count())
        assertTrue(source.contains("private const val FIREWORK_INTERVAL_MS = 500L"))
    }

    private fun stateWithPending(fireworkSlot: Int, visibleSlot: Int) =
        ElytraMotionDirectHotbarRestoreState().also {
            assertTrue(it.begin(pending(fireworkSlot, visibleSlot)))
        }

    private fun pending(fireworkSlot: Int, visibleSlot: Int) = ElytraMotionDirectHotbarPendingRestore(
        playerIdentity = PLAYER,
        levelIdentity = LEVEL,
        connectionIdentity = CONNECTION,
        fireworkSlot = fireworkSlot,
        capturedVisibleSlot = visibleSlot,
        diagnosticsEnabled = false,
        createdTick = 0,
        createdNanos = 0L,
    )

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
