/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10017$class_10018
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_10017;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_897.class})
public class EntityRendererLeashMixin {
    @Inject(method={"renderLeash"}, at={@At(value="HEAD")}, cancellable=true)
    private static void hideFishingLeash(class_4587 matrices, class_4597 vertexConsumers, class_10017.class_10018 leashData, CallbackInfo ci) {
        class_310 mc = class_310.method_1551();
        if (mc.field_1724 == null || mc.field_1724.field_7513 == null) {
            return;
        }
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideFishingRod()) {
            ci.cancel();
        }
    }
}
