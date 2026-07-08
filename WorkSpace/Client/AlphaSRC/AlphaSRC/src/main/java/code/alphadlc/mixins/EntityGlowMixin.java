/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1297.class})
public class EntityGlowMixin {
    @Inject(method={"isGlowing"}, at={@At(value="HEAD")}, cancellable=true)
    private void onIsGlowing(CallbackInfoReturnable<Boolean> cir) {
        NoRender noRender;
        class_1297 self = (class_1297)this;
        if (self instanceof class_1657 && (noRender = NoRender.getInstance()) != null && noRender.shouldHidePlayerGlow()) {
            cir.setReturnValue((Object)false);
        }
    }
}
