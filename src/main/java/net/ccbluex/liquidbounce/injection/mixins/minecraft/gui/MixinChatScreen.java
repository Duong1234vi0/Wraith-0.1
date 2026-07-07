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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.gui;

import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.ChatSendEvent;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.multiplayer.chat.GuiMessage;
import net.minecraft.util.FormattedCharSink;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChatScreen.class)
public abstract class MixinChatScreen extends MixinScreen {

    /**
     * Handle user chat messages
     *
     * @param chatText chat message by client user
     */
    @Inject(method = "handleChatInput", at = @At("HEAD"), cancellable = true)
    private void handleChatMessage(String chatText, boolean addToHistory, CallbackInfo ci) {
        ChatSendEvent chatSendEvent = new ChatSendEvent(chatText);

        EventManager.INSTANCE.callEvent(chatSendEvent);

        if (chatSendEvent.isCancelled()) {
            minecraft.gui.getChat().addRecentChat(chatText);
            ci.cancel();
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void hookMouseClicked(MouseButtonEvent click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
        Integer activeMessage = getActiveMessage(click);

        if (activeMessage == null) {
            return;
        }

        var chatHud = (MixinChatHudAccessor) this.minecraft.gui.getChat();
        var visibleMessages = chatHud.getVisibleMessages();
        if (activeMessage < 0 || activeMessage >= visibleMessages.size()) {
            return;
        }

        List<GuiMessage.Line> messageParts = new ArrayList<>();
        GuiMessage parent = visibleMessages.get(activeMessage).parent();
        for (int index = activeMessage; index >= 0; index--) {
            GuiMessage.Line line = visibleMessages.get(index);
            if (line.parent() != parent) {
                break;
            }

            messageParts.add(0, line);
            if (line.endOfEntry()) {
                break;
            }
        }

        if (!messageParts.isEmpty()) {
            copyMessage(messageParts, click.button());
        }
    }

    @Unique
    private void copyMessage(List<GuiMessage.Line> messageParts, int button) {
        final StringBuilder builder = new StringBuilder();

        FormattedCharSink visitor = (index, style, codePoint) -> {
            builder.append((char) codePoint);
            return true;
        };

        for (GuiMessage.Line line : messageParts) {
            line.content().accept(visitor);
        }

        if (isPressed(GLFW.GLFW_KEY_LEFT_SHIFT, GLFW.GLFW_KEY_RIGHT_SHIFT) && button == GLFW.GLFW_MOUSE_BUTTON_1) {
            minecraft.keyboardHandler.setClipboard(builder.toString());
        } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
            if (minecraft.screen instanceof ChatScreen chat) {
                ((MixinChatScreenAccessor) chat).getInput().setValue(builder.toString());
            }
        }
    }

    @Unique
    private boolean isPressed(int... keys) {
        for (int key : keys) {
            if (GLFW.glfwGetKey(minecraft.getWindow().handle(), key) == GLFW.GLFW_PRESS) {
                return true;
            }
        }

        return false;
    }

    @Unique
    private @Nullable Integer getActiveMessage(MouseButtonEvent click) {
        var chatHud = (MixinChatHudAccessor) this.minecraft.gui.getChat();
        var visibleMessages = chatHud.getVisibleMessages();
        if (visibleMessages.isEmpty()) {
            return null;
        }

        double chatScale = chatHud.invokeGetScale();
        if (chatScale <= 0.0) {
            return null;
        }

        int chatWidth = (int) Math.ceil(chatHud.invokeGetWidth() / chatScale);
        double localMouseX = click.x() / chatScale - 4.0;
        if (localMouseX < 0.0 || localMouseX > chatWidth) {
            return null;
        }

        int lineHeight = chatHud.invokeGetLineHeight();
        if (lineHeight <= 0) {
            return null;
        }

        int guiHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int chatBottom = (int) Math.floor((guiHeight - 40) / chatScale);
        double localMouseY = chatBottom - click.y() / chatScale;
        if (localMouseY < 0.0) {
            return null;
        }

        int lineIndex = (int) Math.floor(localMouseY / lineHeight);
        int visibleLineCount = Math.min(chatHud.invokeGetLinesPerPage(), visibleMessages.size() - chatHud.getChatScrollbarPos());
        if (lineIndex < 0 || lineIndex >= visibleLineCount) {
            return null;
        }

        int messageIndex = lineIndex + chatHud.getChatScrollbarPos();
        return messageIndex >= 0 && messageIndex < visibleMessages.size() ? messageIndex : null;
    }
}
