package net.ccbluex.liquidbounce.features.module.modules.misc

import com.mojang.blaze3d.platform.InputConstants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.lwjgl.glfw.GLFW

class ModuleElytraSwapFireworkPlanTest {

    @Test
    fun mainInventoryUseIsPrecededByTemporaryHeldSlotAndRestoredAfterward() {
        assertEquals(
            listOf(
                ElytraSwapFireworkAction.SWAP,
                ElytraSwapFireworkAction.CLOSE,
                ElytraSwapFireworkAction.HELD_TEMPORARY,
                ElytraSwapFireworkAction.USE,
                ElytraSwapFireworkAction.HELD_ORIGINAL,
                ElytraSwapFireworkAction.SWAP,
                ElytraSwapFireworkAction.CLOSE,
            ),
            elytraSwapMainInventoryFireworkActions(),
        )
    }

    @Test
    fun mainHandUseOffhandRouteClosesBothTransactions() {
        assertEquals(
            listOf(
                ElytraSwapFireworkAction.SWAP,
                ElytraSwapFireworkAction.CLOSE,
                ElytraSwapFireworkAction.USE,
                ElytraSwapFireworkAction.SWAP,
                ElytraSwapFireworkAction.CLOSE,
            ),
            elytraSwapOffhandFireworkActions(),
        )
    }

    @Test
    fun startFireworkUseRunsAfterTheObservedStartCommand() {
        val order = mutableListOf("START_FALL_FLYING")

        handleElytraSwapStartFireworkPostSend(isStartFallFlying = true, startFireworkEnabled = true) {
            order += "USE_ITEM"
        }

        assertEquals(listOf("START_FALL_FLYING", "USE_ITEM"), order)
    }

    @Test
    fun mousePressIsEventDrivenAndNeverPolledAsAHeldKeyboardKey() {
        val mouse = InputConstants.Type.MOUSE.getOrCreate(4)

        assertFalse(shouldPollElytraSwapKeyOnTick(mouse))
        assertTrue(matchesElytraSwapMousePress(mouse, 4, GLFW.GLFW_PRESS, screenOpen = false))
        assertFalse(matchesElytraSwapMousePress(mouse, 4, GLFW.GLFW_REPEAT, screenOpen = false))
        assertFalse(matchesElytraSwapMousePress(mouse, 4, GLFW.GLFW_PRESS, screenOpen = true))
    }

    @Test
    fun keyboardAndScancodeBindingsRemainHeldPollInputs() {
        assertTrue(shouldPollElytraSwapKeyOnTick(InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_G)))
        assertTrue(shouldPollElytraSwapKeyOnTick(InputConstants.Type.SCANCODE.getOrCreate(42)))
    }
}
