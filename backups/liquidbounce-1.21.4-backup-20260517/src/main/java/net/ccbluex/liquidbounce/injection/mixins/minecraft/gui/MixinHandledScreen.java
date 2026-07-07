package net.ccbluex.liquidbounce.injection.mixins.minecraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import kotlin.Unit;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import net.ccbluex.liquidbounce.features.module.modules.misc.ModuleItemScroller;
import net.ccbluex.liquidbounce.features.module.modules.movement.inventorymove.ModuleInventoryMove;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleInventoryAnimation;
import net.ccbluex.liquidbounce.utils.client.Chronometer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Util;
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

@Mixin(HandledScreen.class)
public abstract class MixinHandledScreen<T extends ScreenHandler> extends MixinScreen {

    @Shadow
    @Final
    protected T handler;

    @Shadow
    @Nullable
    protected abstract Slot getSlotAt(double mouseX, double mouseY);

    @Shadow
    private ItemStack quickMovingStack;

    @Shadow
    protected abstract void onMouseClick(Slot slot, int id, int button, SlotActionType actionType);

    @Shadow
    private boolean cancelNextRelease;

    @Shadow
    private @Nullable Slot lastClickedSlot;

    @Shadow
    private int lastClickedButton;

    @Shadow
    private long lastButtonClickTime;

    @Unique
    @Final
    private final Chronometer chronometer = new Chronometer();

    @Unique
    private float inventoryAnimationProgress = 0f;

    @Unique
    private boolean inventoryAnimationClosing = false;

    @Unique
    private boolean inventoryAnimationBypassClose = false;

    @Unique
    private boolean inventoryAnimationScheduledClose = false;

    @Unique
    private boolean inventoryAnimationMatrixPushed = false;

    @Unique
    private boolean inventoryAnimationCursorHidden = false;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void hookInventoryAnimationPre(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        inventoryAnimationMatrixPushed = false;

        if (inventoryAnimationScheduledClose) {
            ci.cancel();
            return;
        }

        if (!isInventoryAnimationActive()) {
            return;
        }

        float speed = ModuleInventoryAnimation.INSTANCE.getAnimationSpeed();

        if (inventoryAnimationClosing) {
            inventoryAnimationProgress = inventoryAnimationFast(inventoryAnimationProgress, 0f, speed * 3f);

            if (inventoryAnimationProgress < 0.05f && !inventoryAnimationCursorHidden && this.client != null) {
                inventoryAnimationCursorHidden = true;
                GLFW.glfwSetInputMode(
                        this.client.getWindow().getHandle(),
                        GLFW.GLFW_CURSOR,
                        GLFW.GLFW_CURSOR_HIDDEN
                );
            }

            if (inventoryAnimationProgress < 0.005f) {
                inventoryAnimationScheduledClose = true;
                inventoryAnimationBypassClose = true;
                showInventoryAnimationCursor();

                if (this.client != null) {
                    this.client.execute(((Screen) (Object) this)::close);
                }

                ci.cancel();
                return;
            }
        } else {
            inventoryAnimationProgress = Math.min(1f, inventoryAnimationFast(inventoryAnimationProgress, 1f, speed));
            if (inventoryAnimationProgress >= 0.999f) {
                return;
            }
        }

        context.getMatrices().push();
        inventoryAnimationMatrixPushed = true;
        applyInventoryAnimationTransform(context, applyInventoryAnimationEasing(inventoryAnimationProgress));
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void hookInventoryAnimationPost(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (inventoryAnimationMatrixPushed) {
            context.getMatrices().pop();
            inventoryAnimationMatrixPushed = false;
        }

        if (!isInventoryAnimationActive()
                || !ModuleInventoryAnimation.INSTANCE.shouldDimBackground()
                || inventoryAnimationScheduledClose) {
            return;
        }

        if (inventoryAnimationProgress >= 0.999f && !inventoryAnimationClosing) {
            return;
        }

        float alpha = 1f - applyInventoryAnimationEasing(inventoryAnimationProgress);
        if (alpha > 0.005f) {
            int color = Math.min(255, (int) (alpha * 255f)) << 24;
            RenderSystem.disableDepthTest();
            context.fill(0, 0, this.width, this.height, color);
            RenderSystem.enableDepthTest();
        }
    }

    @Inject(method = "close", at = @At("HEAD"), cancellable = true)
    private void hookInventoryAnimationClose(CallbackInfo ci) {
        if (inventoryAnimationBypassClose) {
            return;
        }

        if (!isInventoryAnimationActive()) {
            return;
        }

        if (inventoryAnimationClosing || inventoryAnimationScheduledClose) {
            ci.cancel();
            return;
        }

        if (inventoryAnimationProgress > 0.05f) {
            inventoryAnimationClosing = true;
            ci.cancel();
        }
    }

    @Inject(method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V", at = @At("HEAD"), cancellable = true)
    private void cancelMouseClick(Slot slot, int slotId, int button, SlotActionType actionType, CallbackInfo ci) {
        var inventoryMove = ModuleInventoryMove.INSTANCE;
        if ((Object) this instanceof InventoryScreen && inventoryMove.getRunning() && inventoryMove.getDoNotAllowClicking()) {
            ci.cancel();
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawSlots(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
    private void hookDrawSlot(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var cursorStack = this.handler.getCursorStack();
        var slot = getSlotAt(mouseX, mouseY);
        if (!cursorStack.isEmpty() || slot == null) {
            return;
        }

        if (matchingItemScrollerMoveConditions(mouseX, mouseY)) {
            this.quickMovingStack = slot.hasStack() ? slot.getStack().copy() : ItemStack.EMPTY;

            ModuleItemScroller.getClickMode().getAction().invoke(this.handler, slot, (callbackSlot, slotId, mouseButton, actionType) -> {
                this.onMouseClick(callbackSlot, slotId, mouseButton, actionType);
                return Unit.INSTANCE;
            });

            this.cancelNextRelease = true;

            this.lastClickedSlot = slot;
            this.lastButtonClickTime = Util.getMeasuringTimeMs();
            this.lastClickedButton = GLFW.GLFW_MOUSE_BUTTON_1;

            chronometer.reset();
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void hookMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        /*
         * We move the item by itself, we don't need this action by Minecraft
         */
        if (matchingItemScrollerMoveConditions((int) mouseX, (int) mouseY)) {
            cir.cancel();
        }
    }

    @Unique
    private boolean matchingItemScrollerMoveConditions(int mouseX, int mouseY) {
        var handle = this.client.getWindow().getHandle();

        return (InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_SHIFT)
                        || InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT_SHIFT))
                && getSlotAt(mouseX, mouseY) != null
                && ModuleItemScroller.INSTANCE.getRunning()
                && GLFW.glfwGetMouseButton(handle, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS
                && chronometer.hasAtLeastElapsed(RandomKt.nextInt(Random.Default, ModuleItemScroller.getDelay()) * 50L);
    }

    @Unique
    private boolean isInventoryAnimationActive() {
        return ModuleInventoryAnimation.INSTANCE.shouldAnimate((Screen) (Object) this);
    }

    @Unique
    private float inventoryAnimationEaseOutCubic(float value) {
        value = inventoryAnimationClamp01(value);
        return 1f - (float) Math.pow(1f - value, 3);
    }

    @Unique
    private float inventoryAnimationEaseOutBack(float value) {
        value = inventoryAnimationClamp01(value);
        float c1 = 1.70158f;
        float c3 = c1 + 1f;
        return 1f + c3 * (float) Math.pow(value - 1f, 3) + c1 * (float) Math.pow(value - 1f, 2);
    }

    @Unique
    private float applyInventoryAnimationEasing(float value) {
        if (inventoryAnimationClosing) {
            return inventoryAnimationEaseOutCubic(value);
        }

        return switch (ModuleInventoryAnimation.INSTANCE.getAnimationType()) {
            case BOUNCE -> Math.max(0.001f, inventoryAnimationEaseOutBack(value));
            default -> inventoryAnimationEaseOutCubic(value);
        };
    }

    @Unique
    private void applyInventoryAnimationTransform(DrawContext context, float value) {
        float centerX = this.width / 2f;
        float centerY = this.height / 2f;

        switch (ModuleInventoryAnimation.INSTANCE.getAnimationType()) {
            case SCALE, BOUNCE -> {
                context.getMatrices().translate(centerX, centerY, 0.0);
                context.getMatrices().scale(Math.max(0.001f, value), Math.max(0.001f, value), 1f);
                context.getMatrices().translate(-centerX, -centerY, 0.0);
            }

            case SLIDE_UP -> context.getMatrices().translate(0.0, this.height * (1.0 - value), 0.0);
            case SLIDE_DOWN -> context.getMatrices().translate(0.0, -this.height * (1.0 - value), 0.0);
            case SLIDE_LEFT -> context.getMatrices().translate(this.width * (1.0 - value), 0.0, 0.0);
            case SLIDE_RIGHT -> context.getMatrices().translate(-this.width * (1.0 - value), 0.0, 0.0);

            case FLIP -> {
                context.getMatrices().translate(centerX, centerY, 0.0);
                context.getMatrices().scale(Math.max(0.001f, value), 1f, 1f);
                context.getMatrices().translate(-centerX, -centerY, 0.0);
            }

            case WARP -> {
                float scale = inventoryAnimationClosing ? value : (1.15f - 0.15f * value);
                context.getMatrices().translate(centerX, centerY, 0.0);
                context.getMatrices().scale(Math.max(0.001f, scale), Math.max(0.001f, scale), 1f);
                context.getMatrices().translate(-centerX, -centerY, 0.0);
            }

            case GLITCH -> {
                float shake = (1f - value) * 35f * (float) Math.sin(value * 28f);
                float scale = 0.88f + 0.12f * value;
                context.getMatrices().translate(shake, 0.0, 0.0);
                context.getMatrices().translate(centerX, centerY, 0.0);
                context.getMatrices().scale(scale, scale, 1f);
                context.getMatrices().translate(-centerX, -centerY, 0.0);
            }
        }
    }

    @Unique
    private float inventoryAnimationFast(float current, float target, float speed) {
        int fps = this.client == null ? 60 : this.client.getCurrentFps();
        float delta = fps > 5 ? 1.0f / fps : 0.016f;
        float factor = Math.max(0f, Math.min(delta * speed, 1f));
        return (1f - factor) * current + factor * target;
    }

    @Unique
    private float inventoryAnimationClamp01(float value) {
        return Math.max(0f, Math.min(1f, value));
    }

    @Unique
    private void showInventoryAnimationCursor() {
        if (this.client != null) {
            GLFW.glfwSetInputMode(this.client.getWindow().getHandle(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }

        inventoryAnimationCursorHidden = false;
    }

}
