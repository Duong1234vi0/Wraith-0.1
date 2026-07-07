package net.ccbluex.liquidbounce.injection.mixins.minecraft.text;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.lang.LanguageManager;
import net.ccbluex.liquidbounce.lang.LanguageText;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TranslatableContents.class)
public class MixinTranslatableTextContent {

    @ModifyExpressionValue(method = "decompose", at = @At(value = "INVOKE", target = "Lnet/minecraft/locale/Language;getInstance()Lnet/minecraft/locale/Language;"))
    private Language hookClientTranslations(Language original) {
        return (Object) this instanceof LanguageText ? LanguageManager.INSTANCE.getLanguage() : original;
    }

}
