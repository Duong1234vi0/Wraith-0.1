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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.gui;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleInventoryAnimation;
import net.ccbluex.liquidbounce.utils.client.RunnableClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public abstract class MixinScreen {
    @Shadow
    protected Font font;
    @Shadow
    public int height;
    @Shadow
    public int width;

    @Shadow
    protected abstract <T extends GuiEventListener & Renderable> T addRenderableWidget(T drawableElement);

    @Final
    @Shadow
    @Nullable
    protected Minecraft minecraft;

    @Shadow
    private boolean initialized;

    /**
     * Allows the execution of {@link RunnableClickEvent}.
     */
    @Inject(method = "defaultHandleClickEvent", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", ordinal = 0, shift = At.Shift.BEFORE, remap = false), cancellable = true)
    private static void hookExecuteClickEvents(ClickEvent clickEvent, Minecraft client, Screen screenAfterRun, CallbackInfo ci) {
        if (clickEvent instanceof RunnableClickEvent runnableClickEvent) {
            runnableClickEvent.run();
            ci.cancel();
        }
    }

    @Unique
    public boolean liquidbounce$screenInitialized() {
        return initialized;
    }

    // ----------------------------------------------------------------------
    // #Edit By Claude 2026-06-17: InventoryAnimation — VCore 0.8 port.
    //
    // VCore's effect is a single time-based easeOutBack scale-up from the screen
    // centre when a container screen opens (250ms). No close zoom-out, no dim, no
    // per-type modes, no cursor hiding — the ThunderHack complexity was removed.
    //
    // 1.26.2 note (kept from the previous fix): the inventory WINDOW chrome is
    // drawn in extractBackground() and the ITEMS in extractRenderState() — two
    // separate passes. Wrapping the Screen-level orchestrator
    // extractRenderStateWithTooltipAndSubtitles() (which runs both back-to-back)
    // makes BOTH scale together with one transform.
    // ----------------------------------------------------------------------

    @Unique
    private long inventoryAnimationOpenTime = 0L;
    @Unique
    private boolean inventoryAnimationMatrixPushed = false;

    @Inject(method = "extractRenderStateWithTooltipAndSubtitles", at = @At("HEAD"))
    private void hookInventoryAnimationPre(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        inventoryAnimationMatrixPushed = false;

        if (!ModuleInventoryAnimation.INSTANCE.shouldAnimate((Screen) (Object) this)) {
            return;
        }

        if (inventoryAnimationOpenTime == 0L) {
            inventoryAnimationOpenTime = System.currentTimeMillis();
        }

        float progress = Math.min(1f,
                (System.currentTimeMillis() - inventoryAnimationOpenTime)
                        / ModuleInventoryAnimation.INSTANCE.getDurationMillis());
        if (progress >= 1f) {
            return;
        }

        float scale = ModuleInventoryAnimation.INSTANCE.backOutScale(progress);
        float centerX = this.width / 2f;
        float centerY = this.height / 2f;

        context.pose().pushMatrix();
        context.pose().translate(centerX, centerY);
        context.pose().scale(scale, scale);
        context.pose().translate(-centerX, -centerY);
        inventoryAnimationMatrixPushed = true;
    }

    @Inject(method = "extractRenderStateWithTooltipAndSubtitles", at = @At("TAIL"))
    private void hookInventoryAnimationPost(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (inventoryAnimationMatrixPushed) {
            context.pose().popMatrix();
            inventoryAnimationMatrixPushed = false;
        }
    }
}
