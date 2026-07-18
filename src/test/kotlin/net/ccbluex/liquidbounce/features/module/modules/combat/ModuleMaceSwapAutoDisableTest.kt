package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModuleMaceSwapAutoDisableTest {

    @Test
    fun elytraPredictionUpdateRunsAfterAuraAndBecomesNextTickState() {
        var predictionScale = 2.0
        var attackObservedScale = Double.NaN
        val handlers = listOf(
            FIRST_PRIORITY to { attackObservedScale = predictionScale },
            ELYTRA_TARGET_POST_AURA_PRIORITY to { predictionScale = 5.0 },
        )

        handlers.sortedByDescending { it.first }.forEach { it.second() }

        assertTrue(ELYTRA_TARGET_POST_AURA_PRIORITY < FIRST_PRIORITY)
        assertEquals(2.0, attackObservedScale)
        assertEquals(5.0, predictionScale)
    }

    @Test
    fun postAuraSyncOnlyMarksRestoreReadyAfterAura() {
        val oldOrder = mutableListOf("switchBack", "autoDisable", "attack")
        val correctedOrder = mutableListOf<String>()
        val handlers = listOf(
            FIRST_PRIORITY to { correctedOrder += "attack" },
            MACE_SWAP_POST_AURA_PRIORITY to {
                runVCoreMaceSwapPostAuraSync(
                    processSwitchBack = { correctedOrder += "restoreReady" },
                    processAutoDisable = { correctedOrder += "autoDisable" },
                )
            },
        )

        handlers.sortedByDescending { it.first }.forEach { it.second() }

        assertEquals(listOf("switchBack", "autoDisable", "attack"), oldOrder)
        assertTrue(MACE_SWAP_POST_AURA_PRIORITY < FIRST_PRIORITY)
        assertEquals(listOf("attack", "restoreReady", "autoDisable"), correctedOrder)
    }

    @Test
    fun preAuraRestoreRunsBeforeSequencesAndAura() {
        val order = mutableListOf<String>()
        val handlers = listOf(
            MACE_SWAP_PRE_AURA_PRIORITY to { order += "restore" },
            FIRST_PRIORITY to { order += "aura" },
            MACE_SWAP_POST_AURA_PRIORITY to { order += "restoreReady" },
        )

        handlers.sortedByDescending { it.first }.forEach { it.second() }

        assertTrue(MACE_SWAP_PRE_AURA_PRIORITY > FIRST_PRIORITY)
        assertEquals(
            listOf("restore", "aura", "restoreReady"),
            order,
        )
    }

    @Test
    fun autoDisableFalseOnlyClearsThePostAttackTrigger() {
        assertEquals(
            MaceSwapAutoDisableAction.CLEAR_TRIGGER,
            maceSwapAutoDisableAction(false, postAttackTriggered = true, isSwitching = false, switchBack = false),
        )
    }

    @Test
    fun switchBackFalseDisablesImmediatelyAfterTheTrigger() {
        assertEquals(
            MaceSwapAutoDisableAction.DISABLE,
            maceSwapAutoDisableAction(true, postAttackTriggered = true, isSwitching = false, switchBack = false),
        )
    }

    @Test
    fun visibleSwitchBackWaitsUntilSwitchingCompletes() {
        assertEquals(
            MaceSwapAutoDisableAction.WAIT,
            maceSwapAutoDisableAction(true, postAttackTriggered = true, isSwitching = true, switchBack = true),
        )
        assertEquals(
            MaceSwapAutoDisableAction.DISABLE,
            maceSwapAutoDisableAction(true, postAttackTriggered = true, isSwitching = false, switchBack = true),
        )
    }

    @Test
    fun silentSwitchBackDisablesAfterItsImmediateRestore() {
        assertEquals(
            MaceSwapAutoDisableAction.DISABLE,
            maceSwapAutoDisableAction(true, postAttackTriggered = true, isSwitching = false, switchBack = true),
        )
    }
}
