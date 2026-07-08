/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10017
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_10017;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_897.class})
public class EntityShadowMixin {
    @Inject(method={"getShadowRadius"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetShadowRadius(class_10017 state, CallbackInfoReturnable<Float> cir) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideShadows()) {
            cir.setReturnValue((Object)Float.valueOf(0.0f));
        }
    }
}
