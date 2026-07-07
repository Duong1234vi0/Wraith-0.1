package net.ccbluex.liquidbounce.utils.entity

import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.platform.Window
import com.mojang.blaze3d.vertex.PoseStack
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState
import java.util.WeakHashMap

object ButtonWidget {
    fun builder(message: Component, onPress: () -> Unit): Button.Builder =
        Button.builder(message) { onPress() }
}

fun Button.Builder.dimensions(x: Int, y: Int, width: Int, height: Int): Button.Builder =
    bounds(x, y, width, height)

private val drawContextMatrices = WeakHashMap<DrawContext, PoseStack>()

val DrawContext.matrices: PoseStack
    get() = drawContextMatrices.getOrPut(this) { PoseStack() }

fun drawContextForMatrices(matrices: PoseStack): DrawContext? =
    drawContextMatrices.entries.firstOrNull { it.value === matrices }?.key

fun DrawContext.drawBorder(x: Int, y: Int, width: Int, height: Int, color: Int) {
    fill(x, y, x + width, y + 1, color)
    fill(x, y + height - 1, x + width, y + height, color)
    fill(x, y, x + 1, y + height, color)
    fill(x + width - 1, y, x + width, y + height, color)
}

fun DrawContext.drawItem(stack: ItemStack, x: Int, y: Int) = item(stack, x, y)

fun DrawContext.drawTextWithShadow(font: Font, text: String, x: Int, y: Int, color: Int) =
    text(font, text, x, y, color, true)

fun DrawContext.drawText(font: Font, text: String, x: Int, y: Int, color: Int, shadow: Boolean) =
    text(font, text, x, y, color, shadow)

fun DrawContext.drawText(font: Font, text: Component, x: Int, y: Int, color: Int, shadow: Boolean) =
    text(font, text, x, y, color, shadow)

fun DrawContext.drawStackOverlay(font: net.minecraft.client.gui.Font, stack: ItemStack, x: Int, y: Int) =
    itemDecorations(font, stack, x, y)

fun DrawContext.drawStackOverlay(
    font: net.minecraft.client.gui.Font,
    stack: ItemStack,
    x: Int,
    y: Int,
    countText: String?
) = itemDecorations(font, stack, x, y, countText)

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun DrawContext.drawTooltip(
    font: net.minecraft.client.gui.Font,
    lines: List<Component>,
    tooltipData: Any?,
    x: Int,
    y: Int
) = Unit

fun DrawContext.drawTooltip(font: net.minecraft.client.gui.Font, text: Component, x: Int, y: Int) =
    setTooltipForNextFrame(font, text, x, y)

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun DrawContext.drawTexture(
    renderType: Any?,
    texture: Identifier,
    x: Int,
    y: Int,
    u: Float,
    v: Float,
    width: Int,
    height: Int,
    textureWidth: Int,
    textureHeight: Int
) = Unit

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun DrawContext.drawGuiTexture(renderType: Any?, texture: Identifier, x: Int, y: Int, width: Int, height: Int) = Unit

fun DrawContext.drawItem(stack: ItemStack, x: Int, y: Int, seed: Int) = item(stack, x, y, seed)

fun DrawContext.drawItemWithoutEntity(stack: ItemStack, x: Int, y: Int, seed: Int) = item(stack, x, y, seed)

val Minecraft.textRenderer: net.minecraft.client.gui.Font
    get() = font

val Screen.textRenderer: Font
    get() = Minecraft.getInstance().font

@Suppress("UnusedReceiverParameter")
fun <T> Screen.addDrawableChild(widget: T): T = widget

fun Font.getWidth(text: String): Int = width(text)

fun Font.getWidth(text: Component): Int = width(text)

val Minecraft.scaledWidth: Int
    get() = window.guiScaledWidth

val Minecraft.scaledHeight: Int
    get() = window.guiScaledHeight

val Window.scaledWidth: Int
    get() = guiScaledWidth

val Window.scaledHeight: Int
    get() = guiScaledHeight

val Minecraft.currentFps: Int
    get() = fps

val Minecraft.framebuffer
    get() = mainRenderTarget

val RenderTarget.textureWidth: Int
    get() = width

val RenderTarget.textureHeight: Int
    get() = height

val RenderTarget.viewportWidth: Int
    get() = width

val RenderTarget.viewportHeight: Int
    get() = height

val RenderTarget.colorAttachment: Int
    get() = 0

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun RenderTarget.setClearColor(red: Float, green: Float, blue: Float, alpha: Float) = Unit

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun RenderTarget.clear() = Unit

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun RenderTarget.beginWrite(setViewport: Boolean) = Unit

@Suppress("UnusedReceiverParameter")
fun RenderTarget.endWrite() = Unit

@Suppress("UnusedReceiverParameter")
fun RenderTarget.beginRead() = Unit

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun RenderTarget.drawInternal(width: Int, height: Int) = Unit

@Suppress("UnusedReceiverParameter")
fun RenderTarget.delete() = Unit

val <T : AbstractContainerMenu> AbstractContainerScreen<T>.screenHandler: T
    get() = getMenu()

val Player.screenHandler: AbstractContainerMenu
    get() = containerMenu

val Player.currentScreenHandler: AbstractContainerMenu
    get() = containerMenu

val Player.playerScreenHandler: InventoryMenu
    get() = inventoryMenu

val AbstractContainerMenu.cursorStack: ItemStack
    get() = carried

val Slot.backgroundSprite: Identifier?
    get() = noItemIcon

fun Slot.hasStack(): Boolean = hasItem()

@Suppress("UnusedReceiverParameter")
fun Slot.canBeHighlighted(): Boolean = true

@Suppress("UnusedReceiverParameter")
fun Slot.disablesDynamicDisplay(): Boolean = false

val Font.fontHeight: Int
    get() = lineHeight

val LocalPlayer.armorItems: Iterable<ItemStack>
    get() = listOf(
        getItemBySlot(EquipmentSlot.HEAD),
        getItemBySlot(EquipmentSlot.CHEST),
        getItemBySlot(EquipmentSlot.LEGS),
        getItemBySlot(EquipmentSlot.FEET)
    )

val LivingEntity.armorItems: Iterable<ItemStack>
    get() = listOf(
        getItemBySlot(EquipmentSlot.HEAD),
        getItemBySlot(EquipmentSlot.CHEST),
        getItemBySlot(EquipmentSlot.LEGS),
        getItemBySlot(EquipmentSlot.FEET)
    )

val LivingEntity.statusEffects: Collection<net.minecraft.world.effect.MobEffectInstance>
    get() = activeEffects

val Inventory.main: MutableList<ItemStack>
    get() = nonEquipmentItems

val Inventory.armorItems: List<ItemStack>
    get() = listOf(getItem(39), getItem(38), getItem(37), getItem(36))

val Inventory.armor: List<ItemStack>
    get() = armorItems

val Inventory.offHand: List<ItemStack>
    get() = listOf(getItem(40))

val Inventory.mainHandItem: ItemStack
    get() = getSelectedItem()

val Inventory.mainHandStack: ItemStack
    get() = getSelectedItem()

val Inventory.offhandItem: ItemStack
    get() = getItem(40)

val Inventory.offHandStack: ItemStack
    get() = getItem(40)

var Inventory.selectedSlot: Int
    get() = getSelectedSlot()
    set(value) {
        setSelectedSlot(value)
    }

fun Inventory.getStack(slot: Int): ItemStack = getItem(slot)

fun Inventory.getArmorStack(slot: Int): ItemStack = getItem(36 + slot)

val Slot.id: Int
    get() = index

val Slot.stack: ItemStack
    get() = item

val Slot.inventory
    get() = container

val Slot.isEnabled: Boolean
    get() = isActive

val AbstractContainerMenu.syncId: Int
    get() = containerId

val AbstractContainerMenu.revision: Int
    get() = stateId

val Item.defaultStack: ItemStack
    get() = defaultInstance

val Item.maxCount: Int
    get() = defaultInstance.maxStackSize

fun ItemStack.isOf(item: Item): Boolean = `is`(item)

val ItemStack.item: Item
    get() = getItem()

val ItemEntity.stack: ItemStack
    get() = item

val ItemStack.tooltipData: Any?
    get() = null

val ItemStack.count: Int
    get() = getCount()

val ItemStack.isEmpty: Boolean
    get() = isEmpty()

val ItemStack.isDamageable: Boolean
    get() = isDamageableItem()

val ItemStack.maxDamage: Int
    get() = getMaxDamage()

val ItemStack.damage: Int
    get() = damageValue

val ItemStack.maxCount: Int
    get() = maxStackSize

val ItemStack.willBreakNextUse: Boolean
    get() = isDamageableItem && damageValue >= maxDamage - 1

fun ItemStack.willBreakNextUse(): Boolean = willBreakNextUse

fun ItemStack.getMaxUseTime(user: LivingEntity): Int = getUseDuration(user)

fun ItemStack.getMiningSpeedMultiplier(state: BlockState): Float = getDestroySpeed(state)

val ItemStack.componentsCompat
    get() = components

fun ItemStack.has(component: net.minecraft.core.component.DataComponentType<*>): Boolean = components.has(component)

fun ItemStack.hasEnchantments(): Boolean = isEnchanted
