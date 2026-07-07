package net.ccbluex.liquidbounce.injection.mixins.minecraft.gui;

import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.chat.GuiMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

/**
 * @author 00101110001100010111000101111
 * @since 12/18/2024
 **/
@Mixin(ChatComponent.class)
public interface MixinChatHudAccessor {
    @Invoker("getWidth")
    int invokeGetWidth();

    @Invoker("getScale")
    double invokeGetScale();

    @Invoker("getLineHeight")
    int invokeGetLineHeight();

    @Invoker("getLinesPerPage")
    int invokeGetLinesPerPage();

    @Accessor("trimmedMessages")
    List<GuiMessage.Line> getVisibleMessages();

    @Accessor("chatScrollbarPos")
    int getChatScrollbarPos();
}
