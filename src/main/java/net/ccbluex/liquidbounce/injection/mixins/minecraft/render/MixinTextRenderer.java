package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.ccbluex.liquidbounce.features.module.modules.misc.nameprotect.ModuleNameProtect;
import net.minecraft.client.gui.Font;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.FormattedCharSink;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Font.class)
public abstract class MixinTextRenderer {

    @ModifyArg(
            method = "prepareText(Ljava/lang/String;FFIZI)Lnet/minecraft/client/gui/Font$PreparedText;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/StringDecomposer;iterateFormatted(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lnet/minecraft/util/FormattedCharSink;)Z")
    )
    private String injectNameProtectA(String text) {
        return ModuleNameProtect.INSTANCE.replace(text);
    }

    @Redirect(
            method = "prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FormattedCharSequence;accept(Lnet/minecraft/util/FormattedCharSink;)Z")
    )
    private boolean injectNameProtectB(FormattedCharSequence orderedText, FormattedCharSink visitor) {
        if (ModuleNameProtect.INSTANCE.getRunning()) {
            return new ModuleNameProtect.NameProtectOrderedText(orderedText).accept(visitor);
        }

        return orderedText.accept(visitor);
    }

    @ModifyArg(method = "width(Ljava/lang/String;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/StringSplitter;stringWidth(Ljava/lang/String;)F"), index = 0)
    private @Nullable String injectNameProtectWidthA(@Nullable String text) {
        return text != null && ModuleNameProtect.INSTANCE.getRunning() ? ModuleNameProtect.INSTANCE.replace(text) : text;
    }

    @ModifyArg(method = "width(Lnet/minecraft/util/FormattedCharSequence;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/StringSplitter;stringWidth(Lnet/minecraft/util/FormattedCharSequence;)F"), index = 0)
    private FormattedCharSequence injectNameProtectWidthB(FormattedCharSequence text) {
        return ModuleNameProtect.INSTANCE.getRunning() ? new ModuleNameProtect.NameProtectOrderedText(text) : text;
    }

}
