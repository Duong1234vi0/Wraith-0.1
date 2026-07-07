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
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.OverlayMessageEvent;
import net.ccbluex.liquidbounce.event.events.PerspectiveEvent;
import net.ccbluex.liquidbounce.features.module.modules.render.DoRender;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleAntiBlind;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeCam;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud;
import net.ccbluex.liquidbounce.features.module.modules.player.ModuleClickAction;
import net.ccbluex.liquidbounce.interfaces.DrawContextAddition;
import net.ccbluex.liquidbounce.render.engine.BlurEffectRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Window;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Final
    @Unique
    private static final Identifier liquid_bounce$PUMPKIN_BLUR = Identifier.ofVanilla("misc/pumpkinblur");

    @Final
    @Shadow
    private static Identifier POWDER_SNOW_OUTLINE;

    /**
     * Hook render hud event at the top layer
     */
    @Inject(method = "renderMainHud", at = @At("HEAD"))
    private void hookRenderEventStart(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        BlurEffectRenderer.INSTANCE.startOverlayDrawing(context, tickCounter.getTickDelta(false));
    }

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void hookRenderSpyglassOverlay(DrawContext context, float scale, CallbackInfo ci) {
        if (!ModuleAntiBlind.canRender(DoRender.SPYGLASS_OVERLAY)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private void injectPumpkinBlur(DrawContext context, Identifier texture, float opacity, CallbackInfo callback) {
        if (!ModuleAntiBlind.INSTANCE.getRunning()) {
            return;
        }

        if (!ModuleAntiBlind.canRender(DoRender.PUMPKIN_BLUR) && liquid_bounce$PUMPKIN_BLUR.equals(texture)) {
            callback.cancel();
            return;
        }

        if (!ModuleAntiBlind.canRender(DoRender.POWDER_SNOW_FOG) && POWDER_SNOW_OUTLINE.equals(texture)) {
            callback.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void hookFreeCamRenderCrosshairInThirdPerson(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ModuleFreeCam.INSTANCE.getRunning() && ModuleFreeCam.INSTANCE.shouldDisableCameraInteract()) {
            ci.cancel();
        }
    }

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V", ordinal = 0))
    private void centerCrosshair(DrawContext drawContext, Function<Identifier, RenderLayer> renderLayers, Identifier sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (!ModuleHud.INSTANCE.getCenteredCrosshair()) {
            original.call(drawContext, renderLayers, sprite, x, y, width, height);
            return;
        }

        Window window = MinecraftClient.getInstance().getWindow();
        double scaleFactor = window.getScaleFactor();
        double scaledCenterX = (window.getFramebufferWidth() / scaleFactor) / 2.0;
        double scaledCenterY = (window.getFramebufferHeight() / scaleFactor) / 2.0;
        ((DrawContextAddition) drawContext).liquid_bounce$drawTexture(
                renderLayers, sprite,
                Math.round((scaledCenterX - 7.5) * 4.0) * 0.25f,
                Math.round((scaledCenterY - 7.5) * 4.0) * 0.25f,
                width, height
        );
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void hookRenderPortalOverlay(CallbackInfo ci) {
        if (!ModuleAntiBlind.canRender(DoRender.PORTAL_OVERLAY)) {
            ci.cancel();
        }
    }

    @Inject(method = "setOverlayMessage", at = @At("HEAD"), cancellable = true)
    private void hookSetOverlayMessage(Text message, boolean tinted, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new OverlayMessageEvent(message, tinted));
    }

    @ModifyExpressionValue(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private boolean hookOffhandItem(boolean original) {
        return original;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 5
    )
    private ItemStack hookClickActionPearlVisualSpoof(ItemStack stack) {
        return ModuleClickAction.INSTANCE.getRenderedHotbarStack(stack);
    }

    @ModifyExpressionValue(method = "renderCrosshair",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/GameOptions;getPerspective()Lnet/minecraft/client/option/Perspective;"
            )
    )
    private Perspective hookPerspectiveEventOnCrosshair(Perspective original) {
        return EventManager.INSTANCE.callEvent(new PerspectiveEvent(original)).getPerspective();
    }

    @ModifyExpressionValue(method = "renderMiscOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/GameOptions;getPerspective()Lnet/minecraft/client/option/Perspective;"
            )
    )
    private Perspective hookPerspectiveEventOnMiscOverlays(Perspective original) {
        return EventManager.INSTANCE.callEvent(new PerspectiveEvent(original)).getPerspective();
    }

    @Inject(method = "renderTitleAndSubtitle", at = @At("HEAD"), cancellable = true)
    private void hookRenderTitleAndSubtitle(CallbackInfo ci) {
        if (!ModuleAntiBlind.canRender(DoRender.TITLE)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
    private void hookNauseaOverlay(DrawContext context, float distortionStrength, CallbackInfo ci) {
        if (!ModuleAntiBlind.canRender(DoRender.NAUSEA)) {
            ci.cancel();
        }
    }

}
