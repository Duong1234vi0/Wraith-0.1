/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_742
 *  net.minecraft.class_8685
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.CustomCape;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_742.class})
public class AbstractClientPlayerEntityMixin {
    @Inject(method={"getSkinTextures"}, at={@At(value="RETURN")}, cancellable=true)
    private void customCape$getSkinTextures(CallbackInfoReturnable<class_8685> cir) {
        class_742 player = (class_742)this;
        CustomCape customCape = CustomCape.getInstance();
        if (customCape == null || !customCape.shouldApply(player)) {
            return;
        }
        cir.setReturnValue((Object)CustomCape.withCustomCape((class_8685)cir.getReturnValue()));
    }
}
