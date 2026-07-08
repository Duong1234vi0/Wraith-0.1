/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10055
 *  net.minecraft.class_1007
 *  net.minecraft.class_2561
 *  net.minecraft.class_3883
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_591
 *  net.minecraft.class_742
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.CustomCape;
import code.alphadlc.features.impl.render.NameTags;
import code.alphadlc.features.impl.render.custommodels.CustomModelFeatureRenderer;
import code.alphadlc.mixins.LivingEntityRendererAccessor;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_2561;
import net.minecraft.class_3883;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1007.class})
public class PlayerEntityRendererMixin {
    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void AlphaDLC$addCustomModelFeature(class_5617.class_5618 context, boolean slim, CallbackInfo ci) {
        ((LivingEntityRendererAccessor)((Object)this)).callAddFeature(new CustomModelFeatureRenderer((class_3883<class_10055, class_591>)((class_1007)this)));
    }

    @Inject(method={"renderLabelIfPresent(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void render(class_10055 playerEntityRenderState, class_2561 text, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
        NameTags nameTags = NameTags.getInstance();
        if (nameTags != null && nameTags.state) {
            ci.cancel();
        }
    }

    @Inject(method={"updateRenderState"}, at={@At(value="RETURN")})
    private void customCape$updateRenderState(class_742 player, class_10055 state, float tickDelta, CallbackInfo ci) {
        CustomCape customCape = CustomCape.getInstance();
        if (customCape == null || !customCape.shouldApply(player)) {
            return;
        }
        state.field_53520 = CustomCape.withCustomCape(player.method_52814());
        state.field_53532 = true;
    }
}
