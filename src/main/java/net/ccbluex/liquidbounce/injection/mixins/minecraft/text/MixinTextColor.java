package net.ccbluex.liquidbounce.injection.mixins.minecraft.text;

import net.ccbluex.liquidbounce.interfaces.ClientTextColorAdditions;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TextColor.class)
public class MixinTextColor implements ClientTextColorAdditions {

    @Shadow
    @Final
    private @Nullable String name;

    @Shadow
    @Final
    private int value;

    @Unique
    private boolean bypassesNameProtect = false;

    @Override
    public boolean liquid_bounce$doesBypassingNameProtect() {
        return bypassesNameProtect;
    }

    @Override
    public TextColor liquid_bounce$withNameProtectionBypass() {
        var textColor = new TextColor(this.value, this.name);
        ((ClientTextColorAdditions) (Object) textColor).liquid_bounce$setBypassingNameProtection(true);
        return textColor;
    }

    @Override
    public void liquid_bounce$setBypassingNameProtection(boolean bypassesNameProtect) {
        this.bypassesNameProtect = bypassesNameProtect;
    }

}
