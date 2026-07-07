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

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent;
import net.ccbluex.liquidbounce.event.events.OverlayMessageEvent;
import net.ccbluex.liquidbounce.event.events.PerspectiveEvent;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeCam;
import net.minecraft.client.CameraType;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinInGameHud {

    // #Edit By Codex: expose HUD overlay rendering on the 1.26 Gui extraction path.
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void hookOverlayRenderEvent(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new OverlayRenderEvent(
                context,
                tickCounter.getGameTimeDeltaPartialTick(false)
        ));
    }

    @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
    private void hookFreeCamRenderCrosshairInThirdPerson(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (ModuleFreeCam.INSTANCE.getRunning() && ModuleFreeCam.INSTANCE.shouldDisableCameraInteract()) {
            ci.cancel();
        }
    }

    @Inject(method = "setOverlayMessage", at = @At("HEAD"), cancellable = true)
    private void hookSetOverlayMessage(Component message, boolean tinted, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new OverlayMessageEvent(message, tinted));
    }

    @ModifyExpressionValue(method = "extractCrosshair",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;getCameraType()Lnet/minecraft/client/CameraType;"
            )
    )
    private CameraType hookPerspectiveEventOnCrosshair(CameraType original) {
        return EventManager.INSTANCE.callEvent(new PerspectiveEvent(original)).getPerspective();
    }

    @ModifyExpressionValue(method = "extractCameraOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;getCameraType()Lnet/minecraft/client/CameraType;"
            )
    )
    private CameraType hookPerspectiveEventOnMiscOverlays(CameraType original) {
        return EventManager.INSTANCE.callEvent(new PerspectiveEvent(original)).getPerspective();
    }

}
