package net.ccbluex.liquidbounce.utils.client

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ServerCarriedSlotLeaseTest {

    @Test
    fun serverLeaseOverridesCarriedSynchronizationWithoutChangingLocalSelection() {
        val player = Any()
        val level = Any()
        val state = ServerCarriedSlotLeaseState()

        assertTrue(state.claim(OWNER, 6, player, level, 10, 1_000L, ordinarySilentHotbarActive = false))
        assertEquals(6, state.resolveSlot(2, player, level, 10, 2_000L))

        // Local selected-item resolution remains on the ordinary SilentHotbar path.
        assertEquals(2, resolveSilentHotbarServerSlot(enforcedSlot = null, visibleSlot = 2))
    }

    @Test
    fun serverLeaseResolutionPrecedesOrdinarySilentHotbarFallback() {
        assertEquals(6, resolveServerCarriedSlot(enforcedSlot = 6, fallbackSlot = 4))
        assertEquals(4, resolveServerCarriedSlot(enforcedSlot = null, fallbackSlot = 4))
        assertEquals(4, resolveSilentHotbarServerSlot(enforcedSlot = 4, visibleSlot = 2))
    }

    @Test
    fun ordinarySilentHotbarSelectionBlocksServerLeaseClaim() {
        val state = ServerCarriedSlotLeaseState()
        assertFalse(
            state.claim(OWNER, 6, Any(), Any(), 10, 1_000L, ordinarySilentHotbarActive = true)
        )
    }

    @Test
    fun anotherServerLeaseOwnerCannotBeOverwrittenOrReleased() {
        val player = Any()
        val level = Any()
        val state = ServerCarriedSlotLeaseState()
        val otherOwner = Any()

        assertTrue(state.claim(OWNER, 6, player, level, 10, 1_000L, ordinarySilentHotbarActive = false))
        assertFalse(state.claim(otherOwner, 4, player, level, 10, 2_000L, ordinarySilentHotbarActive = false))
        assertFalse(state.release(otherOwner))
        assertTrue(state.isOwnedBy(OWNER))
        assertEquals(6, state.resolveSlot(2, player, level, 10, 3_000L))
    }

    @Test
    fun releaseRestoresOrdinarySilentHotbarFallback() {
        val player = Any()
        val level = Any()
        val state = ServerCarriedSlotLeaseState()

        assertTrue(state.claim(OWNER, 6, player, level, 10, 1_000L, ordinarySilentHotbarActive = false))
        assertTrue(state.release(OWNER))
        assertFalse(state.isOwnedBy(OWNER))
        assertEquals(4, state.resolveSlot(4, player, level, 10, 2_000L))
    }

    @Test
    fun identityReplacementDropsLeaseWithoutResolvingIntoNewSession() {
        val player = Any()
        val level = Any()
        val state = ServerCarriedSlotLeaseState()

        assertTrue(state.claim(OWNER, 6, player, level, 10, 1_000L, ordinarySilentHotbarActive = false))
        assertEquals(2, state.resolveSlot(2, Any(), level, 11, 2_000L))
        assertFalse(state.isOwnedBy(OWNER))
    }

    @Test
    fun boundedTimeoutCannotLeaveServerLeaseActive() {
        val player = Any()
        val level = Any()
        val state = ServerCarriedSlotLeaseState()

        assertTrue(state.claim(OWNER, 6, player, level, 10, 1_000L, ordinarySilentHotbarActive = false))
        assertTrue(state.dropIfInvalid(player, level, currentTick = 111, nowNanos = 2_000L))
        assertFalse(state.isOwnedBy(OWNER))
        assertEquals(2, state.resolveSlot(2, player, level, 111, 3_000L))
    }

    private companion object {
        val OWNER = Any()
    }
}
