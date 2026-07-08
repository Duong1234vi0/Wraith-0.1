/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4184
 *  net.minecraft.class_5636
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4184.class})
public class CameraSubmersionMixin {
    @Inject(method={"getSubmersionType"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetSubmersionType(CallbackInfoReturnable<class_5636> cir) {
        NoRender noRender = NoRender.getInstance();
        if (noRender == null || !noRender.isState()) {
            return;
        }
        class_5636 type = (class_5636)cir.getReturnValue();
        if (type == null) {
            return;
        }
        if (type == class_5636.field_27886 && noRender.shouldHideWaterOverlay()) {
            cir.setReturnValue((Object)class_5636.field_27888);
            return;
        }
        if (type == class_5636.field_27885 && noRender.shouldHideLavaOverlay()) {
            cir.setReturnValue((Object)class_5636.field_27888);
        }
    }
}
