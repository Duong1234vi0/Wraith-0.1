/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10444
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.ItemReplacer;
import net.minecraft.class_10444;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_10444.class})
public class ItemRenderStateMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void alphadlc$itemReplacerRender(class_4587 matrixStack, class_4597 buffer, int combinedLight, int combinedOverlay, CallbackInfo ci) {
        if (ItemReplacer.renderPending(matrixStack, buffer, combinedLight, combinedOverlay)) {
            ci.cancel();
        }
    }
}
