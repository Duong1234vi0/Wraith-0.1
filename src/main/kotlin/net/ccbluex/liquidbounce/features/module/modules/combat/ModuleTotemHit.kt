/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.getEntityById
import net.ccbluex.liquidbounce.utils.entity.mainHandItem
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.item.isSword
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.game.ServerboundAttackPacket
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items

/** Strict Prestige TotemHit port with Grim-safe post-attack restoration. */
object ModuleTotemHit : ClientModule("TotemHit", Category.COMBAT) {
    init {
        // The pending restoration listener intentionally outlives a module toggle.
        TotemHitCycle
    }
}

/**
 * Owns one intercepted attack cycle. It always runs so disabling TotemHit after an attack cannot
 * strand the local player on the Sword before the mandatory natural packet boundary has passed.
 */
private object TotemHitCycle : EventListener {

    private data class PendingCycle(
        val player: LocalPlayer,
        val level: ClientLevel,
        val previousSlot: Int,
        val swordSlot: Int,
        val attackedEntityId: Int,
        val attackPacket: ServerboundAttackPacket,
        val initiationBoundarySerial: Long,
        val diagnosticsEnabled: Boolean,
        var attackObserved: Boolean = false,
        var attackBoundarySerial: Long = Long.MIN_VALUE,
        var lastWaitLoggedSerial: Long = Long.MIN_VALUE,
    )

    private var pendingCycle: PendingCycle? = null

    override val running: Boolean
        get() = true

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.OUTGOING || !ModuleTotemHit.running || pendingCycle != null) {
            return@handler
        }

        val attackPacket = event.packet as? ServerboundAttackPacket ?: return@handler
        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (level.getEntityById(attackPacket.entityId()) !is Player ||
            localPlayer.mainHandItem.item != Items.TOTEM_OF_UNDYING
        ) {
            return@handler
        }

        val swordSlot = Slots.Hotbar.firstOrNull { it.itemStack.isSword } ?: return@handler
        val previousSlot = localPlayer.inventory.selectedSlot
        val diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
        val cycle = PendingCycle(
            player = localPlayer,
            level = level,
            previousSlot = previousSlot,
            swordSlot = swordSlot.hotbarSlot,
            attackedEntityId = attackPacket.entityId(),
            attackPacket = attackPacket,
            initiationBoundarySerial = ActionTickBoundary.currentSerial(),
            diagnosticsEnabled = diagnosticsEnabled
        )

        localPlayer.inventory.selectedSlot = cycle.swordSlot
        interaction.ensureHasSentCarriedItem()
        pendingCycle = cycle
        ActionTickBoundary.diagnostic(
            diagnosticsEnabled,
            "TotemHit selected Sword slot=${cycle.swordSlot} previousSlot=${cycle.previousSlot} " +
                "targetId=${cycle.attackedEntityId} boundarySerial=${cycle.initiationBoundarySerial}"
        )

        // #Edit By Codex 2026-07-17: do not replay the intercepted attack. The original packet and
        // its caller-owned MAIN_HAND animation remain the one valid 1.9+ ATTACK -> ANIMATION sequence.
    }

    @Suppress("unused")
    private val attackObservedHandler = handler<OutgoingPacketSentEvent> { event ->
        val cycle = pendingCycle ?: return@handler
        val packet = event.packet as? ServerboundAttackPacket ?: return@handler
        if (cycle.attackObserved || packet !== cycle.attackPacket || packet.entityId() != cycle.attackedEntityId) {
            return@handler
        }

        cycle.attackObserved = true
        cycle.attackBoundarySerial = ActionTickBoundary.currentSerial()
        ActionTickBoundary.diagnostic(
            cycle.diagnosticsEnabled,
            "TotemHit original attack observed targetId=${cycle.attackedEntityId} " +
                "boundarySerial=${cycle.attackBoundarySerial}"
        )
    }

    @Suppress("unused")
    private val restorationHandler = handler<GameTickEvent> {
        val cycle = pendingCycle ?: return@handler
        val localPlayer = mc.player
        val level = mc.level
        if (localPlayer !== cycle.player || level !== cycle.level) {
            pendingCycle = null
            return@handler
        }

        if (cycle.previousSlot !in 0..8) {
            pendingCycle = null
            return@handler
        }

        if (!cycle.attackObserved) {
            // A later PacketEvent handler can cancel the original attack. Still wait for the same
            // natural tick boundary before undoing the visible selection, without claiming an attack happened.
            restoreAfterBoundary(cycle, cycle.initiationBoundarySerial, "restore after cancelled attack")
            return@handler
        }

        restoreAfterBoundary(cycle, cycle.attackBoundarySerial, "restore previous slot")
    }

    private fun restoreAfterBoundary(cycle: PendingCycle, boundarySerial: Long, purpose: String) {
        if (!ActionTickBoundary.hasPassed(boundarySerial)) {
            if (cycle.lastWaitLoggedSerial != ActionTickBoundary.currentSerial()) {
                cycle.lastWaitLoggedSerial = ActionTickBoundary.currentSerial()
                ActionTickBoundary.diagnostic(
                    cycle.diagnosticsEnabled,
                    "TotemHit waiting for CLIENT_TICK_END boundarySerial=$boundarySerial"
                )
            }
            return
        }

        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = "TotemHit",
                actionBoundarySerial = boundarySerial,
                diagnosticsEnabled = cycle.diagnosticsEnabled
            )
        ) {
            return
        }

        val localPlayer = cycle.player
        if (localPlayer.inventory.selectedSlot == cycle.previousSlot) {
            pendingCycle = null
            return
        }

        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = "TotemHit",
            purpose = purpose,
            postActionBoundarySerial = boundarySerial,
            diagnosticsEnabled = cycle.diagnosticsEnabled
        )
        if (!expectation.allowed) {
            return
        }

        // #Edit By Codex 2026-07-17: Grim keeps attack state through the natural CLIENT_TICK_END,
        // so the visible restore cannot send a HELD_ITEM_CHANGE until that boundary has passed.
        localPlayer.inventory.selectedSlot = cycle.previousSlot
        interaction.ensureHasSentCarriedItem()
        ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        ActionTickBoundary.diagnostic(
            cycle.diagnosticsEnabled,
            "TotemHit restored previous slot=${cycle.previousSlot} boundarySerial=$boundarySerial"
        )
        pendingCycle = null
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        pendingCycle = null
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        pendingCycle = null
    }
}
