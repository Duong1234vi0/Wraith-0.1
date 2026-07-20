package net.ccbluex.liquidbounce.features.module.modules.combat

import java.nio.file.Files
import java.nio.file.Path
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.minecraft.world.InteractionHand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EMaceFireworkPhaseCoordinatorTest {

    @Test
    fun preAuraPriorityRunsAfterOlderRestoresAndBeforeAura() {
        assertTrue(MACE_SWAP_PRE_AURA_PRIORITY > EMACE_FIREWORK_PRE_AURA_PRIORITY)
        assertTrue(EMACE_FIREWORK_PRE_AURA_PRIORITY > FIRST_PRIORITY)
    }

    @Test
    fun peakAssistHotbarPlanKeepsRawProfileWithoutSwing() {
        val plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, fireworkSlot = 7, visibleSlot = 1)

        assertEquals(EMaceFireworkSource.HOTBAR, plan.source)
        assertEquals(InteractionHand.MAIN_HAND, plan.useHand)
        assertTrue(plan.initialHeldRequired)
        assertTrue(plan.heldRestorationRequired)
        assertFalse(plan.swingRequired)
        assertFalse(plan.inventorySwapInRequired)
    }

    @Test
    fun vectorHotbarPlanRetainsItsExplicitSwing() {
        val plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.FIREWORK_VECTOR, fireworkSlot = 4, visibleSlot = 0)

        assertTrue(plan.swingRequired)
        assertTrue(plan.requiresPostUseRestoration)
    }

    @Test
    fun directOffhandPlanNeverCreatesHeldRestoration() {
        val plan = EMaceFireworkPlan.directHand(
            EMaceFireworkProfile.PEAK_ASSIST,
            InteractionHand.OFF_HAND,
            visibleSlot = 2,
        )

        assertEquals(EMaceFireworkSource.OFFHAND, plan.source)
        assertFalse(plan.initialHeldRequired)
        assertFalse(plan.inventorySwapInRequired)
        assertFalse(plan.requiresPostUseRestoration)
        assertNull(plan.fireworkHotbarSlot)
    }

    @Test
    fun alreadySelectedMainHandPlanNeverCreatesHeldRestoration() {
        val plan = EMaceFireworkPlan.directHand(
            EMaceFireworkProfile.FIREWORK_VECTOR,
            InteractionHand.MAIN_HAND,
            visibleSlot = 4,
        )

        assertEquals(EMaceFireworkSource.HOTBAR, plan.source)
        assertEquals(InteractionHand.MAIN_HAND, plan.useHand)
        assertFalse(plan.initialHeldRequired)
        assertFalse(plan.requiresPostUseRestoration)
        assertTrue(plan.swingRequired)
        assertNull(plan.fireworkHotbarSlot)
    }

    @Test
    fun offhandSwapUsesInventoryRestorationWithoutHeldPackets() {
        val plan = EMaceFireworkPlan.offhandSwap(
            EMaceFireworkProfile.PEAK_ASSIST,
            sourceSlot = 35,
            visibleSlot = 3,
        )

        assertEquals(EMaceFireworkSource.OFFHAND_SWAP, plan.source)
        assertEquals(InteractionHand.OFF_HAND, plan.useHand)
        assertTrue(plan.inventorySwapInRequired)
        assertTrue(plan.inventorySwapBackRequired)
        assertFalse(plan.initialHeldRequired)
        assertTrue(plan.requiresPostUseRestoration)
    }

    @Test
    fun mainInventoryPlansPreserveProfileTemporarySlotsAndSwingDifference() {
        val peakAssist = EMaceFireworkPlan.mainInventory(
            EMaceFireworkProfile.PEAK_ASSIST,
            sourceSlot = 35,
            temporarySlot = 2,
            visibleSlot = 1,
        )
        val vector = EMaceFireworkPlan.mainInventory(
            EMaceFireworkProfile.FIREWORK_VECTOR,
            sourceSlot = 35,
            temporarySlot = 1,
            visibleSlot = 1,
        )

        assertEquals(2, peakAssist.temporaryHotbarSlot)
        assertEquals(2, peakAssist.fireworkHotbarSlot)
        assertFalse(peakAssist.swingRequired)
        assertEquals(1, vector.temporaryHotbarSlot)
        assertEquals(1, vector.fireworkHotbarSlot)
        assertTrue(vector.swingRequired)
        assertTrue(peakAssist.inventorySwapBackRequired)
        assertTrue(vector.inventorySwapBackRequired)
    }

    @Test
    fun conflictQueuedUseWaitsForNaturalBoundarySerial() {
        val state = EMaceFireworkCycleState()
        val cycle = cycle(
            plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0),
            queued = true,
            requestSerial = 12,
        )
        assertTrue(state.begin(cycle))

        assertEquals(EMaceFireworkCycleDecision.WAIT, state.preparePreAura(serial = 12))
        assertEquals(EMaceFireworkCycleDecision.EXECUTE_USE, state.preparePreAura(serial = 13))
    }

    @Test
    fun useRestorationRequiresBoundaryAndRunsAtNextPreAuraPhase() {
        val state = EMaceFireworkCycleState()
        val cycle = cycle(
            plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.FIREWORK_VECTOR, 6, 2),
            queued = false,
            requestSerial = 20,
        )
        assertTrue(state.begin(cycle))

        cycle.rawUseSent = true
        cycle.useBoundarySerial = 20
        assertTrue(state.markUseSent(cycle))
        assertFalse(state.observeNaturalBoundary(serial = 20, tick = 100))
        assertTrue(state.observeNaturalBoundary(serial = 21, tick = 101))

        assertEquals(
            EMaceFireworkCycleDecision.RESTORE(heldSlot = 5, swapBack = false),
            state.preparePreAura(serial = 21, visibleSlot = 5),
        )
    }

    @Test
    fun cycleCannotBeOverwrittenByAnotherProfile() {
        val state = EMaceFireworkCycleState()
        val first = cycle(
            plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0),
            queued = true,
            requestSerial = 12,
        )
        val second = cycle(
            plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.FIREWORK_VECTOR, 7, 0),
            queued = true,
            requestSerial = 12,
        )

        assertTrue(state.begin(first))
        assertFalse(state.begin(second))
        assertEquals(EMaceFireworkProfile.PEAK_ASSIST, state.pending?.plan?.profile)
    }

    @Test
    fun lifecycleDropsQueuedUseWithoutSendingIntoNewSession() {
        val playerReplacement = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = true, 12)
        )
        assertEquals(
            EMaceFireworkCycleDecision.DROP("local player replaced"),
            playerReplacement.preparePreAura(currentPlayerIdentity = Any()),
        )
        assertNull(playerReplacement.pending)

        val levelReplacement = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = true, 12)
        )
        assertEquals(
            EMaceFireworkCycleDecision.DROP("level replaced"),
            levelReplacement.preparePreAura(currentLevelIdentity = Any()),
        )
        assertNull(levelReplacement.pending)

        val connectionReplacement = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = true, 12)
        )
        assertEquals(
            EMaceFireworkCycleDecision.DROP("connection replaced"),
            connectionReplacement.preparePreAura(currentConnectionIdentity = Any()),
        )
        assertNull(connectionReplacement.pending)

        val moduleDisable = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = true, 12)
        )
        assertEquals(
            EMaceFireworkCycleDecision.DROP("EMace disabled before use"),
            moduleDisable.preparePreAura(emaceRunning = false),
        )
        assertNull(moduleDisable.pending)

        val timeout = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = true, 12)
        )
        assertEquals(
            EMaceFireworkCycleDecision.DROP("cycle timeout"),
            timeout.preparePreAura(currentTick = 121),
        )
        assertNull(timeout.pending)
    }

    @Test
    fun invalidSlotAndPacketCancellationPathsDoNotAdvanceCycle() {
        val invalidSlot = EMaceFireworkCycleState()
        assertFalse(
            invalidSlot.begin(
                cycle(
                    plan = EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, visibleSlot = -1),
                    queued = true,
                    requestSerial = 12,
                )
            )
        )

        val cancellation = stateWithPending(
            cycle(EMaceFireworkPlan.hotbar(EMaceFireworkProfile.PEAK_ASSIST, 8, 0), queued = false, 12)
        )
        val cycle = cancellation.pending ?: error("expected pending cycle")

        assertFalse(cancellation.markUseSent(cycle))

        val coordinator = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/EMaceFireworkPhaseCoordinator.kt")
        assertTrue(coordinator.contains("rollbackBeforeUse(cycle, \"initial HELD packet cancelled\")"))
        assertTrue(coordinator.contains("rollbackBeforeUse(cycle, \"raw USE_ITEM packet cancelled\")"))
        assertTrue(coordinator.contains("private val worldChangeHandler"))
        assertTrue(coordinator.contains("private val disconnectHandler"))
        assertTrue(coordinator.contains("target lost before use"))
    }

    @Test
    fun coordinatorPostSendOnlyObservesAndGameTickRestores() {
        val coordinator = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/EMaceFireworkPhaseCoordinator.kt")
        val postSend = coordinator.substring(
            coordinator.indexOf("private val postSendHandler"),
            coordinator.indexOf("private val worldChangeHandler"),
        )
        val restore = coordinator.substring(
            coordinator.indexOf("private fun restoreAndClear"),
            coordinator.indexOf("private fun rollbackBeforeUse"),
        )

        assertTrue(postSend.contains("handler<OutgoingPacketPostSendEvent>"))
        assertTrue(postSend.contains("state.observeNaturalBoundary"))
        assertFalse(postSend.contains("sendPacket("))
        assertFalse(postSend.contains("clickSwap("))
        assertTrue(restore.contains("ServerboundSetCarriedItemPacket(slot)"))
        assertTrue(restore.indexOf("ServerboundSetCarriedItemPacket(slot)") < restore.indexOf("clickSwap("))
    }

    @Test
    fun sourceSearchOrderAndForbiddenPathsRemainProfileSpecific() {
        val coordinator = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/EMaceFireworkPhaseCoordinator.kt")
        val emace = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleEMaceHelper.kt")
        val actionBoundary = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/network/ActionTickBoundary.kt")

        assertTrue(coordinator.contains("ActionTickBoundary.hasHeldItemChangeConflictInCurrentBoundary()"))
        assertTrue(coordinator.indexOf("for (slot in 35 downTo 9)") < coordinator.indexOf("for (slot in 8 downTo 0)"))
        assertTrue(coordinator.indexOf("private fun findVectorHotbar") < coordinator.indexOf("private fun findVectorInventory"))
        assertTrue(coordinator.substring(
            coordinator.indexOf("private fun findVectorHotbar"),
            coordinator.indexOf("private fun findVectorInventory"),
        ).contains("for (slot in 0..8)"))
        assertTrue(coordinator.contains("visibleSlot % 8 + 1"))
        assertTrue(coordinator.contains("network.sendPacket(ServerboundSwingPacket(plan.useHand))"))
        assertFalse(coordinator.contains("interaction.interactItem"))
        assertFalse(coordinator.contains("SilentHotbar"))
        assertFalse(coordinator.contains("ServerboundClientTickEndPacket("))

        assertTrue(emace.contains("EMaceFireworkPhaseCoordinator.requestPeakAssist(emaceOwned = true)"))
        assertTrue(emace.contains("EMaceFireworkPhaseCoordinator.requestFireworkVector()"))
        assertTrue(emace.contains("fireworkTriggered = true"))
        assertFalse(emace.contains("private fun useFireWorkSilent"))
        assertFalse(emace.contains("ServerboundSetCarriedItemPacket"))

        assertTrue(actionBoundary.contains("fun hasObservedAttackInCurrentBoundary()"))
        assertTrue(actionBoundary.contains("fun hasObservedUseInCurrentBoundary()"))
        assertTrue(actionBoundary.contains("fun hasHeldItemChangeConflictInCurrentBoundary()"))
        assertTrue(actionBoundary.contains("is ServerboundAttackPacket -> lastObservedAttackSerial = completedTickSerial"))
        assertTrue(actionBoundary.contains("is ServerboundUseItemPacket, is ServerboundUseItemOnPacket"))
    }

    @Test
    fun elytraMotionAndArmorSwapCoordinationRemainNarrow() {
        val elytraMotion = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt")
        val emace = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleEMaceHelper.kt")
        val killaura = source(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt"
        )
        val armorSwap = emace.substring(
            emace.indexOf("private fun handleArmorSwap"),
            emace.indexOf("private fun swapToChestplate"),
        )

        assertTrue(elytraMotion.contains("ModuleEMaceHelper.hasPendingSelectedSlotFireworkCycle()"))
        assertTrue(emace.contains("fun hasPendingSelectedSlotFireworkCycle()"))
        assertTrue(armorSwap.contains("EMaceFireworkPhaseCoordinator.isPending"))
        assertFalse(armorSwap.contains("ServerboundSetCarriedItemPacket"))
        assertTrue(emace.contains("getChestPlateSlot()?.let(::swapToChestplate)"))
        assertTrue(emace.contains("interaction.clickSlot(player.screenHandler.syncId, sourceId, 0, ContainerInput.PICKUP, player)"))
        assertTrue(emace.contains("interaction.clickSlot(player.screenHandler.syncId, chestId, 0, ContainerInput.PICKUP, player)"))
        assertTrue(killaura.contains("clickScheduler.attack"))
        assertFalse(killaura.contains("shouldDeferAuraActionInCurrentBoundary"))
    }

    private fun cycle(
        plan: EMaceFireworkPlan,
        queued: Boolean,
        requestSerial: Long,
    ) = EMaceFireworkCycle(
        plan = plan,
        playerIdentity = PLAYER,
        levelIdentity = LEVEL,
        connectionIdentity = CONNECTION,
        targetIdentity = null,
        emaceOwned = true,
        stage = EMaceFireworkCycleStage.WAITING_TO_USE_AFTER_CONFLICT,
        requestBoundarySerial = requestSerial,
        requestTick = 100,
        createdNanos = 1L,
        conflictingAttackObserved = queued,
        conflictingUseObserved = false,
        queuedAfterConflict = queued,
        diagnosticsEnabled = false,
    )

    private fun stateWithPending(cycle: EMaceFireworkCycle) =
        EMaceFireworkCycleState().also { assertTrue(it.begin(cycle)) }

    private fun EMaceFireworkCycleState.preparePreAura(
        serial: Long,
        visibleSlot: Int = 0,
    ): EMaceFireworkCycleDecision = preparePreAura(
        currentPlayerIdentity = PLAYER,
        currentLevelIdentity = LEVEL,
        currentConnectionIdentity = CONNECTION,
        currentTargetIdentity = null,
        emaceRunning = true,
        currentVisibleSlot = visibleSlot,
        currentBoundarySerial = serial,
        currentTick = 100,
        nowNanos = 1L,
    )

    private fun EMaceFireworkCycleState.preparePreAura(
        currentPlayerIdentity: Any? = PLAYER,
        currentLevelIdentity: Any? = LEVEL,
        currentConnectionIdentity: Any? = CONNECTION,
        currentTargetIdentity: Any? = null,
        currentTick: Int = 100,
        emaceRunning: Boolean = true,
    ): EMaceFireworkCycleDecision = preparePreAura(
        currentPlayerIdentity = currentPlayerIdentity,
        currentLevelIdentity = currentLevelIdentity,
        currentConnectionIdentity = currentConnectionIdentity,
        currentTargetIdentity = currentTargetIdentity as? net.minecraft.world.entity.Entity,
        emaceRunning = emaceRunning,
        currentVisibleSlot = 0,
        currentBoundarySerial = 12,
        currentTick = currentTick,
        nowNanos = 1L,
    )

    private fun source(path: String) = Files.readString(Path.of(path))

    private companion object {
        val PLAYER = Any()
        val LEVEL = Any()
        val CONNECTION = Any()
    }
}
