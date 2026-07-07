/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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
package net.ccbluex.liquidbounce.utils.client
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.block.SwingMode
import net.ccbluex.liquidbounce.utils.input.shouldSwingHand
import net.ccbluex.liquidbounce.utils.inventory.OffHandSlot
import net.minecraft.client.player.LocalPlayer
import net.minecraft.client.multiplayer.MultiPlayerGameMode
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionHand
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.level.GameType
import org.apache.commons.lang3.mutable.MutableObject
import java.util.*

@Suppress("LongParameterList")
fun clickBlockWithSlot(
    player: LocalPlayer,
    rayTraceResult: BlockHitResult,
    slot: Int,
    swingMode: SwingMode,
    switchMode: SwitchMode = SwitchMode.SILENT,
    sequenced: Boolean = true
) {
    val hand = if (slot == OffHandSlot.hotbarSlotForServer) {
        InteractionHand.OFF_HAND
    } else {
        InteractionHand.MAIN_HAND
    }

    val prevHotbarSlot = player.inventory.selectedSlot
    if (hand == InteractionHand.MAIN_HAND) {
        if (switchMode == SwitchMode.NONE && slot != prevHotbarSlot) {
            // the slot is not selected and we can't switch
            return
        }

        player.inventory.selectedSlot = slot

        if (slot != prevHotbarSlot) {
            player.connection.sendPacket(ServerboundSetCarriedItemPacket(slot))
        }
    }

    if (sequenced) {
        interaction.sendSequencedPacket(world) { sequence ->
            ServerboundUseItemOnPacket(hand, rayTraceResult, sequence)
        }
    } else {
        network.sendPacket(ServerboundUseItemOnPacket(hand, rayTraceResult, 0))
    }

    val itemUsageContext = UseOnContext(player, hand, rayTraceResult)

    val itemStack = player.inventory.getStack(slot)

    val actionResult: InteractionResult

    if (player.isCreative) {
        val i = itemStack.count
        actionResult = itemStack.useOn(itemUsageContext)
        itemStack.count = i
    } else {
        actionResult = itemStack.useOn(itemUsageContext)
    }

    if (actionResult.shouldSwingHand()) {
        swingMode.swing(hand)
    }

    if (slot != prevHotbarSlot && hand == InteractionHand.MAIN_HAND && switchMode == SwitchMode.SILENT) {
        player.connection.sendPacket(ServerboundSetCarriedItemPacket(prevHotbarSlot))
    }

    player.inventory.selectedSlot = prevHotbarSlot
}

/**
 * [MultiPlayerGameMode.interactItem] but with custom rotations.
 */
fun MultiPlayerGameMode.interactItem(
    player: Player,
    hand: InteractionHand,
    yaw: Float,
    pitch: Float
): InteractionResult {
    if (localPlayerMode == GameType.SPECTATOR) {
        return InteractionResult.PASS
    }

    this.ensureHasSentCarriedItem()
    val mutableObject = MutableObject<InteractionResult>()
    this.sendSequencedPacket(world) { sequence: Int ->
        val playerInteractItemC2SPacket = ServerboundUseItemPacket(hand, sequence, yaw, pitch)
        val itemStack = player.getStackInHand(hand)
        if (player.cooldowns.isOnCooldown(itemStack)) {
            mutableObject.setValue(InteractionResult.PASS)
            return@sendSequencedPacket playerInteractItemC2SPacket
        }

        val typedActionResult = itemStack.use(world, player, hand)
        val itemStack2 = if (typedActionResult is InteractionResult.Success) {
            Objects.requireNonNullElseGet<ItemStack>(
                typedActionResult.heldItemTransformedTo()
            ) { player.getStackInHand(hand) } as ItemStack
        } else {
            player.getStackInHand(hand)
        }

        if (itemStack2 != itemStack) {
            player.setStackInHand(hand, itemStack2)
        }

        mutableObject.setValue(typedActionResult)
        return@sendSequencedPacket playerInteractItemC2SPacket
    }

    return mutableObject.value
}

fun handlePacket(packet: Packet<*>) =
    runCatching { (packet as Packet<ClientGamePacketListener>).handle(mc.connection!!) }

fun sendPacketSilently(packet: Packet<*>) {
    // hack fix for the packet handler not being called on Rotation Manager for tracking
    val packetEvent = PacketEvent(TransferOrigin.OUTGOING, packet, false)
    RotationManager.packetHandler.handler(packetEvent)
    mc.connection?.connection?.send(packetEvent.packet, null)
}

enum class SwitchMode(override val choiceName: String) : NamedChoice {
    NONE("None"),
    SILENT("Silent")
}

enum class MovePacketType(override val choiceName: String, val generatePacket: () -> ServerboundMovePlayerPacket)
    : NamedChoice {
    ON_GROUND_ONLY("OnGroundOnly", {
        ServerboundMovePlayerPacket.StatusOnly(player.onGround(), player.horizontalCollision)
    }),
    POSITION_AND_ON_GROUND("PositionAndOnGround", {
        ServerboundMovePlayerPacket.Pos(player.x, player.y, player.z, player.onGround(),
            player.horizontalCollision)
    }),
    LOOK_AND_ON_GROUND("LookAndOnGround", {
        ServerboundMovePlayerPacket.Rot(player.yaw, player.pitch, player.onGround(), player.horizontalCollision)
    }),
    FULL("Full", {
        ServerboundMovePlayerPacket.PosRot(player.x, player.y, player.z, player.yaw, player.pitch, player.onGround(),
            player.horizontalCollision)
    });
}
