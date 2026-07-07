package net.ccbluex.liquidbounce.injection.mixins.minecraft.gui;

import kotlin.Unit;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import net.ccbluex.liquidbounce.features.module.modules.misc.ModuleItemScroller;
import net.ccbluex.liquidbounce.features.module.modules.movement.inventorymove.ModuleInventoryMove;
import net.ccbluex.liquidbounce.utils.client.Chronometer;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class MixinHandledScreen<T extends AbstractContainerMenu> extends MixinScreen {

    @Shadow
    @Final
    protected T menu;

    @Shadow
    @Nullable
    protected abstract Slot getHoveredSlot(double mouseX, double mouseY);

    @Shadow
    private ItemStack lastQuickMoved;

    @Shadow
    protected abstract void slotClicked(Slot slot, int id, int button, ContainerInput actionType);

    @Shadow
    private boolean skipNextRelease;

    @Shadow
    private @Nullable Slot lastClickSlot;

    @Shadow
    protected int leftPos;

    @Shadow
    protected int topPos;

    @Unique
    @Final
    private final Chronometer chronometer = new Chronometer();

    @Inject(method = "extractContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;extractSlots(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V", shift = At.Shift.AFTER))
    private void hookDrawSlot(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var cursorStack = this.menu.getCarried();
        var slot = getHoveredSlot(mouseX, mouseY);
        if (!cursorStack.isEmpty() || slot == null) {
            return;
        }

        if (matchingItemScrollerMoveConditions(mouseX, mouseY)) {
            this.lastQuickMoved = slot.hasItem() ? slot.getItem().copy() : ItemStack.EMPTY;

            ModuleItemScroller.getClickMode().getAction().invoke(this.menu, slot, (callbackSlot, slotId, mouseButton, actionType) -> {
                this.slotClicked(callbackSlot, slotId, mouseButton, actionType);
                return Unit.INSTANCE;
            });

            this.skipNextRelease = true;
            this.lastClickSlot = slot;

            chronometer.reset();
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void hookMouseClicked(MouseButtonEvent click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
        /*
         * We move the item by itself, we don't need this action by Minecraft
         */
        if (matchingItemScrollerMoveConditions(click.x(), click.y())) {
            cir.cancel();
        }
    }

    @Unique
    private boolean matchingItemScrollerMoveConditions(double mouseX, double mouseY) {
        if (this.minecraft == null) {
            return false;
        }

        var window = this.minecraft.getWindow();

        return (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT)
                        || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT))
                && getHoveredSlot(mouseX, mouseY) != null
                && ModuleItemScroller.INSTANCE.getRunning()
                && GLFW.glfwGetMouseButton(window.handle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS
                && chronometer.hasAtLeastElapsed(RandomKt.nextInt(Random.Default, ModuleItemScroller.getDelay()) * 50L);
    }

    @Inject(method = "slotClicked(Lnet/minecraft/world/inventory/Slot;IILnet/minecraft/world/inventory/ContainerInput;)V", at = @At("HEAD"), cancellable = true)
    private void cancelMouseClick(Slot slot, int slotId, int button, ContainerInput actionType, CallbackInfo ci) {
        var inventoryMove = ModuleInventoryMove.INSTANCE;
        if ((Object) this instanceof InventoryScreen && inventoryMove.getRunning() && inventoryMove.getDoNotAllowClicking()) {
            ci.cancel();
        }
    }

}
