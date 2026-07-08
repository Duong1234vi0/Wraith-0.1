/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10042
 *  net.minecraft.class_10055
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_310
 *  net.minecraft.class_3882
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_583
 *  net.minecraft.class_976
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.AlphaDLC;
import code.alphadlc.features.impl.render.ChinaHat;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_3882;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.minecraft.class_976;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_976.class})
public abstract class MixinHeadFeatureRenderer<S extends class_10042, M extends class_583<S>>
extends class_3887<S, M> {
    public MixinHeadFeatureRenderer(class_3883<S, M> context) {
        super(context);
    }

    @Inject(method={"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/LivingEntityRenderState;FF)V"}, at={@At(value="HEAD")})
    public void onRenderHead(class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, S livingEntityRenderState, float f, float g, CallbackInfo ci) {
        class_1297 entity;
        ChinaHat chinaHat = AlphaDLC.getInstance().getModuleProvider().get(ChinaHat.class);
        if (chinaHat != null && chinaHat.isState() && livingEntityRenderState instanceof class_10055 && (entity = class_310.method_1551().field_1687.method_8469(((class_10055)livingEntityRenderState).field_53528)) instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            chinaHat.render(matrixStack, vertexConsumerProvider, player, (class_3882)this.method_17165());
        }
    }
}
