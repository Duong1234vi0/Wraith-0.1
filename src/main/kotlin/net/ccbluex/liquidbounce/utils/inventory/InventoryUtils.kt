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
 *
 *
 */
@file:Suppress("TooManyFunctions", "WildcardImport")

package net.ccbluex.liquidbounce.utils.inventory
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.client.*
import net.ccbluex.liquidbounce.utils.input.shouldSwingHand
import net.ccbluex.liquidbounce.utils.item.isNothing
import net.ccbluex.liquidbounce.utils.kotlin.emptyEnumSet
import net.ccbluex.liquidbounce.utils.network.OpenInventorySilentlyPacket
import net.ccbluex.liquidbounce.utils.network.sendPacket
import net.minecraft.world.level.block.Blocks
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.item.ItemStack
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.InteractionHand
import java.util.*

/**
 * Constraints for inventory actions.
 * This can be used to ensure that the player is not moving or rotating while interacting with the inventory.
 * It Also allows setting delays for opening, clicking and closing the inventory.
 */
@Suppress("MagicNumber")
open class InventoryConstraints(
    name: String = "Constraints",
    defaultStartDelay: IntRange = 1..2,
    defaultClickDelay: IntRange = 2..4,
    defaultCloseDelay: IntRange = 1..2,
) : Configurable(name) {

    internal val startDelay by intRange("StartDelay", defaultStartDelay, 0..20, "ticks")
    internal val clickDelay by intRange("ClickDelay", defaultClickDelay, 0..20, "ticks")
    internal val closeDelay by intRange("CloseDelay", defaultCloseDelay, 0..20, "ticks")
    internal val missChance by intRange("MissChance", 0..0, 0..100, "%")

    internal val requirements by multiEnumChoice<InventoryRequirements>("Requires",
        default = emptyEnumSet(),
        choices = EnumSet.of(
            InventoryRequirements.NO_MOVEMENT,
            InventoryRequirements.NO_ROTATION
        ).also {
            if (this is PlayerInventoryConstraints) {
                it.add(InventoryRequirements.OPEN_INVENTORY)
            }
        }
    )

    /**
     * Whether the constraints are met, this will be checked before any inventory actions are performed.
     */
    fun passesRequirements(action: InventoryAction) =
        requirements.all { it.testRequirement(action) }

}

/**
 * Additional constraints for the player inventory. This should be used when interacting with the player inventory
 * instead of a generic container.
 */
class PlayerInventoryConstraints(
    name: String = "Constraints",
    defaultStartDelay: IntRange = 1..2,
    defaultClickDelay: IntRange = 2..4,
    defaultCloseDelay: IntRange = 1..2,
) : InventoryConstraints(name, defaultStartDelay, defaultClickDelay, defaultCloseDelay) {
    val requiresOpenInventory get() = InventoryRequirements.OPEN_INVENTORY in requirements
}

internal enum class InventoryRequirements(
    override val choiceName: String,
    val testRequirement: (action: InventoryAction) -> Boolean
) : NamedChoice {
    NO_MOVEMENT("NoMovement", { _ ->
        player.input.getMoveVector().lengthSquared() == 0.0f
    }),

    NO_ROTATION("NoRotation", { _ ->
        RotationManager.rotationMatchesPreviousRotation()
    }),

    /**
     * When this option is not enabled, the inventory will be opened silently
     * depending on the Minecraft version chosen using ViaFabricPlus.
     *
     * If the protocol contains [com.viaversion.viabackwards.protocol.v1_12to1_11_1.Protocol1_12To1_11_1]
     * and the client status packet is supported,
     * the inventory will be opened silently using [openInventorySilently].
     * Otherwise, the inventory will not have any open tracking and
     * the server will only know when clicking in the inventory.
     *
     * Closing will still be required to be done for any version.
     * Sad.
     * :(
     */
    OPEN_INVENTORY("InventoryOpen", { action ->
        !action.requiresPlayerInventoryOpen() || InventoryManager.isInventoryOpen
    })
}

fun hasInventorySpace() = player.inventory.main.any { it.isEmpty }

fun findEmptyStorageSlotsInInventory(): List<ItemSlot> {
    return (Slots.Inventory + Slots.Hotbar).filter { it.itemStack.isEmpty }
}

fun findNonEmptyStorageSlotsInInventory(): List<ItemSlot> {
    return (Slots.Inventory + Slots.Hotbar).filter { !it.itemStack.isEmpty }
}

fun findNonEmptySlotsInInventory(): List<ItemSlot> {
    return Slots.All.filter { !it.itemStack.isEmpty }
}

/**
 * Sends an open inventory packet with the help of ViaFabricPlus. This is only for older versions.
 */
fun openInventorySilently() {
    if (InventoryManager.isInventoryOpenServerSide || !usesViaFabricPlus) {
        return
    }

    network.sendPacket(
        OpenInventorySilentlyPacket(),
        onSuccess = { InventoryManager.isInventoryOpenServerSide = true },
        onFailure = { chat(markAsError("Failed to open inventory using ViaFabricPlus, report to developers!")) }
    )
}

fun closeInventorySilently() {
    network.sendPacket(ServerboundContainerClosePacket(0))
}

fun getSlotsInContainer(screen: AbstractContainerScreen<*>) =
    screen.screenHandler.slots
        .filter { it.inventory !== player.inventory }
        .map { ContainerItemSlot(it.id) }

fun findItemsInContainer(screen: AbstractContainerScreen<*>) =
    screen.screenHandler.slots
        .filter { !it.stack.isNothing() && it.inventory !== player.inventory }
        .map { ContainerItemSlot(it.id) }

fun useHotbarSlotOrOffhand(
    item: HotbarItemSlot,
    ticksUntilReset: Int = 1,
    yaw: Float = RotationManager.currentRotation?.yaw ?: player.yaw,
    pitch: Float = RotationManager.currentRotation?.pitch ?: player.pitch,
) = when (item) {
    OffHandSlot -> interactItem(InteractionHand.OFF_HAND, yaw, pitch)
    else -> {
        SilentHotbar.selectSlotSilently(null, item, ticksUntilReset)
        interactItem(InteractionHand.MAIN_HAND, yaw, pitch)
    }
}

fun interactItem(
    hand: InteractionHand,
    yaw: Float = RotationManager.currentRotation?.yaw ?: player.yaw,
    pitch: Float = RotationManager.currentRotation?.pitch ?: player.pitch,
) {
    interaction.interactItem(player, hand, yaw, pitch).takeIf { it.isAccepted }?.let {
        if (it.shouldSwingHand()) {
            player.swingHand(hand)
        }

        mc.firstPersonRenderer.resetEquipProgress(hand)
    }
}

fun findBlocksEndingWith(vararg targets: String) =
    BuiltInRegistries.BLOCK.filter { block -> targets.any { BuiltInRegistries.BLOCK.getKey(block).path.endsWith(it.lowercase()) } }

/**
 * Get the color of the armor on the player
 */
fun getArmorColor() = Slots.Armor.firstNotNullOfOrNull { slot ->
    val itemStack = slot.itemStack
    val color = itemStack.getArmorColor() ?: return@firstNotNullOfOrNull null

    Pair(slot, color)
}

/**
 * Get the color of the armor on the item stack
 *
 * @see [net.minecraft.client.render.entity.feature.ArmorFeatureRenderer.renderArmor]
 */
@Suppress("MagicNumber")
fun ItemStack.getArmorColor(): Int? {
    return if (has(DataComponents.DYED_COLOR)) {
        DyedItemColor.getOrDefault(this, -6265536) // #FFA06540
    } else {
        null
    }
}

/**
 * A list of blocks which may not be placed (apart from the usual checks), so inv cleaner and scaffold
 * won't count them as blocks
 */
val DISALLOWED_BLOCKS_TO_PLACE = hashSetOf(
    Blocks.TNT,
    Blocks.COBWEB,
    Blocks.NETHER_PORTAL,
)

/**
 * @see [BlockItemSelection.isBlockUnfavourable]
 */
val UNFAVORABLE_BLOCKS_TO_PLACE = hashSetOf(
    Blocks.CRAFTING_TABLE,
    Blocks.JIGSAW,
    Blocks.SMITHING_TABLE,
    Blocks.FLETCHING_TABLE,
    Blocks.ENCHANTING_TABLE,
    Blocks.CAULDRON,
    Blocks.MAGMA_BLOCK,
)
