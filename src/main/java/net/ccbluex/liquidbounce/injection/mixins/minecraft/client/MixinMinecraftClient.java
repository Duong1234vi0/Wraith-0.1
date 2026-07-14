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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.common.GlobalFramebuffer;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.*;
import net.ccbluex.liquidbounce.features.misc.HideAppearance;
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraAutoBlock;
import net.ccbluex.liquidbounce.features.module.modules.exploit.ModuleMultiActions;
import net.ccbluex.liquidbounce.features.module.modules.misc.ModuleMiddleClickAction;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleAnimations;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleXRay;
import net.ccbluex.liquidbounce.render.engine.RenderingFlags;
import net.ccbluex.liquidbounce.utils.client.vfp.VfpCompatibility;
import net.ccbluex.liquidbounce.utils.combat.CombatManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.SharedConstants;
import net.minecraft.client.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.language.I18n;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.HitResult;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static net.ccbluex.liquidbounce.utils.client.ProtocolUtilKt.getUsesViaFabricPlus;

@Mixin(Minecraft.class)
public abstract class MixinMinecraftClient {

    @Shadow
    @Nullable
    public LocalPlayer player;
    @Shadow
    @Final
    public Options options;
    @Shadow
    @Nullable
    private IntegratedServer singleplayerServer;
    @Shadow
    private int rightClickDelay;
    @Shadow
    @Nullable
    public MultiPlayerGameMode gameMode;

    @Shadow
    @Nullable
    public abstract ClientPacketListener getConnection();

    @Shadow
    public abstract @org.jetbrains.annotations.Nullable ServerData getCurrentServer();

    @Shadow
    public abstract Window getWindow();

    @Shadow
    public abstract void setScreen(@org.jetbrains.annotations.Nullable Screen screen);

    @Shadow
    public abstract int getFps();

    @Shadow
    public abstract User getUser();

    @Shadow
    @org.jetbrains.annotations.Nullable
    public Screen screen;

    @Shadow
    private void continueAttack(boolean breaking) {
    }

    @Shadow
    private @org.jetbrains.annotations.Nullable Overlay overlay;

    @Shadow
    @org.jetbrains.annotations.Nullable
    public ClientLevel level;

    @Shadow
    @Nullable
    public HitResult hitResult;

    @Shadow
    public int missTime;

    /**
     * Entry point of our hacked client
     *
     * @param callback not needed
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void startClient(CallbackInfo callback) {
        EventManager.INSTANCE.callEvent(ClientStartEvent.INSTANCE);
    }

    /**
     * Exit point of our hacked client
     *
     * @param callback not needed
     */
    @Inject(method = "destroy", at = @At("HEAD"))
    private void stopClient(CallbackInfo callback) {
        EventManager.INSTANCE.callEvent(ClientShutdownEvent.INSTANCE);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onSessionInit(CallbackInfo callback) {
        EventManager.INSTANCE.callEvent(new SessionEvent(getUser()));
    }

    /**
     * Modify window title to our client title.
     * Example: LiquidBounce v1.0.0 | 1.16.3
     *
     * @param callback our window title
     *                 <p>
     *                 todo: modify constant Minecraft instead
     */
    @Inject(method = "createTitle", at = @At(
            value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            ordinal = 1),
            cancellable = true)
    private void getClientTitle(CallbackInfoReturnable<String> callback) {
        if (HideAppearance.INSTANCE.isHidingNow()) {
            return;
        }

        LiquidBounce.INSTANCE.getLogger().debug("Modifying window title");

        StringBuilder titleBuilder = new StringBuilder(LiquidBounce.CLIENT_NAME);
        titleBuilder.append(" v");
        titleBuilder.append(LiquidBounce.INSTANCE.getClientVersion());
        titleBuilder.append(" ");

        if (LiquidBounce.IN_DEVELOPMENT) {
            titleBuilder.append("(dev) ");
        }

        titleBuilder.append(LiquidBounce.INSTANCE.getClientCommit());

        titleBuilder.append(" | ");

        // ViaFabricPlus compatibility
        if (getUsesViaFabricPlus()) {
            var protocolVersion = VfpCompatibility.INSTANCE.unsafeGetProtocolVersion();

            if (protocolVersion != null) {
                titleBuilder.append(protocolVersion.getName());
            } else {
                titleBuilder.append(SharedConstants.getCurrentVersion().name());
            }
        } else {
            titleBuilder.append(SharedConstants.getCurrentVersion().name());
        }

        ClientPacketListener clientPlayNetworkHandler = this.getConnection();
        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isConnected()) {
            titleBuilder.append(" - ");
            ServerData serverInfo = this.getCurrentServer();
            if (this.singleplayerServer != null && !this.singleplayerServer.isPublished()) {
                titleBuilder.append(I18n.get("title.singleplayer"));
            } else if (serverInfo != null && serverInfo.isRealm()) {
                titleBuilder.append(I18n.get("title.multiplayer.realms"));
            } else if (this.singleplayerServer == null && (serverInfo == null || !serverInfo.isLan())) {
                titleBuilder.append(I18n.get("title.multiplayer.other"));
            } else {
                titleBuilder.append(I18n.get("title.multiplayer.lan"));
            }
        }

        callback.setReturnValue(titleBuilder.toString());
    }

    /**
     * Fixes recursive screen opening,
     * this is usually caused by another mod such as Lunar Client.
     * Can also happen when opening a screen during [ScreenEvent].
     */
    @Unique
    private boolean recursiveScreenOpening = false;

    /**
     * Handle opening screens
     *
     * @param screen       to be opened (null = no screen at all)
     * @param callbackInfo callback
     */
    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void hookScreen(Screen screen, CallbackInfo callbackInfo) {
        if (recursiveScreenOpening) {
            return;
        }

        try {
            recursiveScreenOpening = true;

            var event = EventManager.INSTANCE.callEvent(new ScreenEvent(screen));
            if (event.isCancelled()) {
                callbackInfo.cancel();
            }
        } finally {
            recursiveScreenOpening = false;
        }

        // #Edit By Codex 2026-06-22: keep vanilla title/accessibility screens instead of replacing them.
    }

    /**
     * Hook game tick event at HEAD
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void hookTickEvent(CallbackInfo callbackInfo) {
        EventManager.INSTANCE.callEvent(GameTickEvent.INSTANCE);
    }

    /**
     * Hook game render task queue event
     */
    @Inject(method = "renderFrame", at = @At("HEAD"))
    private void hookRenderTaskQueue(boolean advanceGameTime, CallbackInfo callbackInfo) {
        EventManager.INSTANCE.callEvent(GameRenderTaskQueueEvent.INSTANCE);
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    private void hookPacketProcess(boolean advanceGameTime, CallbackInfo callbackInfo) {
        EventManager.INSTANCE.callEvent(TickPacketProcessEvent.INSTANCE);
    }

    /**
     * Hook input handling
     */
    @Inject(method = "handleKeybinds", at = @At("RETURN"))
    private void hookHandleInputEvent(CallbackInfo callbackInfo) {
        EventManager.INSTANCE.callEvent(InputHandleEvent.INSTANCE);
    }

    /**
     * Hook item use cooldown
     */
    @Inject(method = "startUseItem", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;rightClickDelay:I", shift = At.Shift.AFTER))
    private void hookItemUseCooldown(CallbackInfo callbackInfo) {
        UseCooldownEvent useCooldownEvent = new UseCooldownEvent(rightClickDelay);
        EventManager.INSTANCE.callEvent(useCooldownEvent);
        rightClickDelay = useCooldownEvent.getCooldown();
    }

    @Inject(method = "pickBlockOrEntity", at = @At("HEAD"), cancellable = true)
    private void hookItemPick(CallbackInfo ci) {
        if (ModuleMiddleClickAction.Pearl.INSTANCE.cancelPick()) {
            ci.cancel();
        }
    }

    @Inject(method = "shouldEntityAppearGlowing", cancellable = true, at = @At("HEAD"))
    private void injectOutlineESPFix(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (RenderingFlags.isCurrentlyRenderingEntityOutline().get()) {
            cir.setReturnValue(true);
        }
    }

    @ModifyExpressionValue(method = "startAttack",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;missTime:I", ordinal = 0))
    private int injectNoMissCooldown(int original) {
        return original;
    }

    @WrapWithCondition(method = "startAttack", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/Minecraft;missTime:I", ordinal = 1))
    private boolean disableAttackCooldown(Minecraft instance, int value) {
        return true;
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void injectCombatPause(CallbackInfoReturnable<Boolean> cir) {
        if (player == null || hitResult == null || hitResult.getType() == HitResult.Type.MISS) {
            return;
        }

        if (CombatManager.INSTANCE.getShouldPauseCombat()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "startAttack", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"
    ))
    private void markNewAnimationAttackSwing(CallbackInfoReturnable<Boolean> cir) {
        ModuleAnimations.INSTANCE.markNextNewSwing(InteractionHand.MAIN_HAND, false, true);
    }

    @Redirect(method = "startUseItem", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"
    ))
    private void markNewAnimationInteractionSwing(LocalPlayer instance, InteractionHand hand) {
        ModuleAnimations.INSTANCE.markNextNewSwing(hand, true, true);
        instance.swing(hand);
    }

    @Inject(method = "setLevel", at = @At("HEAD"))
    private void hookWorldChangeEvent(ClientLevel world, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new WorldChangeEvent(world));
    }

    @Inject(method = "onResourceLoadFinished", at = @At("HEAD"))
    private void onFinishedLoading(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(ResourceReloadEvent.INSTANCE);
    }

    @ModifyExpressionValue(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean injectMultiActionsBreakingWhileUsing(boolean original) {
        return original && !ModuleMultiActions.mayBreakWhileUsing();
    }

    @ModifyExpressionValue(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;isDestroying()Z"))
    private boolean injectMultiActionsPlacingWhileBreaking(boolean original) {
        return original && !ModuleMultiActions.mayPlaceWhileBreaking();
    }

    @ModifyExpressionValue(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", ordinal = 0))
    private boolean injectMultiActionsAttackingWhileUsingAndEnforcedBlockingState(boolean isUsingItem) {
        if (isUsingItem) {
            if (!this.options.keyUse.isDown() && !(KillAuraAutoBlock.INSTANCE.getRunning() && KillAuraAutoBlock.INSTANCE.getBlockingStateEnforced())) {
                this.gameMode.releaseUsingItem(this.player);
            }

            if (!ModuleMultiActions.mayAttackWhileUsing()) {
                this.options.keyAttack.clickCount = 0;
            }

            this.options.keyPickItem.clickCount = 0;
            this.options.keyUse.clickCount = 0;
        }

        return false;
    }

    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V", at = @At("HEAD"))
    private void handleDisconnection(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(DisconnectEvent.INSTANCE);
    }

}
