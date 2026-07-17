package net.ccbluex.liquidbounce.features.module.modules.combat

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModuleMaceSwapAutoDisableTest {

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
