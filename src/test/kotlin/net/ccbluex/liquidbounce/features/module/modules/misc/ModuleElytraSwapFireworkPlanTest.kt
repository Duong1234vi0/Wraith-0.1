package net.ccbluex.liquidbounce.features.module.modules.misc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
}
