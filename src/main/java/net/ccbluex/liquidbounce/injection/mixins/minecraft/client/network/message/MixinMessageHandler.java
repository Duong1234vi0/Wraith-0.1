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
 */
package net.ccbluex.liquidbounce.injection.mixins.minecraft.client.network.message;

import com.mojang.authlib.GameProfile;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.ChatReceiveEvent;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.Instant;

@SuppressWarnings("CancellableInjectionUsage")
@Mixin(ChatListener.class)
public class MixinMessageHandler {

    @Shadow
    private long previousMessageTime;

    @Inject(method = "handleDisguisedChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/chat/ChatListener;handleMessage(Lnet/minecraft/network/chat/MessageSignature;Ljava/util/function/BooleanSupplier;)V", shift = At.Shift.BEFORE), cancellable = true)
    private void injectDisguisedChatMessage(Component message, ChatType.Bound boundChatType, CallbackInfo ci) {
        var result = liquid_bounce$emitChatEvent(boundChatType, message, ChatReceiveEvent.ChatType.DISGUISED_CHAT_MESSAGE);
        if (result) {
            previousMessageTime = Util.getMillis();
            ci.cancel();
        }
    }

    @Inject(method = "showMessageToPlayer", at = @At("HEAD"), cancellable = true)
    private void injectChatMessage(ChatType.Bound boundChatType, PlayerChatMessage message, Component decoratedMessage, GameProfile sender, boolean onlyShowSecure, Instant received, CallbackInfoReturnable<Boolean> cir) {
        var result = liquid_bounce$emitChatEvent(boundChatType, decoratedMessage, ChatReceiveEvent.ChatType.CHAT_MESSAGE);
        if (result) {
            previousMessageTime = Util.getMillis();
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "handleSystemMessage", at = @At(value = "HEAD"), cancellable = true)
    private void injectGameMessage(Component message, boolean remote, CallbackInfo ci) {
        var result = liquid_bounce$emitChatEvent(null, message, ChatReceiveEvent.ChatType.GAME_MESSAGE);
        if (result) {
            previousMessageTime = Util.getMillis();
            ci.cancel();
        }
    }

    @Unique
    private boolean liquid_bounce$emitChatEvent(ChatType.Bound parameters, Component text, ChatReceiveEvent.ChatType type) {
        var event = new ChatReceiveEvent(text.getString(), text, type, inputText -> {
            if (parameters != null) {
                return parameters.decorate(inputText);
            } else {
                return inputText;
            }
        });
        EventManager.INSTANCE.callEvent(event);
        return event.isCancelled();
    }

}
