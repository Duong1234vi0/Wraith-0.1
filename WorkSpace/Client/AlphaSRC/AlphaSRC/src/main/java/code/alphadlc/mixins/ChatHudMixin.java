/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_303
 *  net.minecraft.class_338
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.interactions.path.BaritoneHelper;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_338.class})
public class ChatHudMixin {
    @Inject(method={"addMessage(Lnet/minecraft/text/Text;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddMessage(class_2561 message, CallbackInfo ci) {
        if (BaritoneHelper.isSuppressChat() && BaritoneHelper.isBaritoneMessage(message.getString())) {
            ci.cancel();
        }
    }

    @Inject(method={"addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAddHudLine(class_303 message, CallbackInfo ci) {
        if (BaritoneHelper.isSuppressChat() && BaritoneHelper.isBaritoneMessage(message.comp_893().getString())) {
            ci.cancel();
        }
    }
}
