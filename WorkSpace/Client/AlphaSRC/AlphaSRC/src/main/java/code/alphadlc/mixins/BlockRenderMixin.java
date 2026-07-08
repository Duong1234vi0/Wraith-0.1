/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1920
 *  net.minecraft.class_2248
 *  net.minecraft.class_2261
 *  net.minecraft.class_2302
 *  net.minecraft.class_2320
 *  net.minecraft.class_2338
 *  net.minecraft.class_2476
 *  net.minecraft.class_2680
 *  net.minecraft.class_3481
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_5819
 *  net.minecraft.class_776
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.NoRender;
import net.minecraft.class_1920;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2302;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2476;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5819;
import net.minecraft.class_776;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_776.class})
public class BlockRenderMixin {
    @Inject(method={"renderBlock(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZLnet/minecraft/util/math/random/Random;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void hideVegetation(class_2680 state, class_2338 pos, class_1920 world, class_4587 matrices, class_4588 vertexConsumer, boolean cull, class_5819 random, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideVegetation() && BlockRenderMixin.isVegetationBlock(state)) {
            ci.cancel();
        }
    }

    private static boolean isVegetationBlock(class_2680 blockState) {
        if (blockState == null) {
            return false;
        }
        class_2248 block = blockState.method_26204();
        if (block instanceof class_2261 || block instanceof class_2302 || block instanceof class_2476 || block instanceof class_2320) {
            return true;
        }
        return blockState.method_26164(class_3481.field_15503) || blockState.method_26164(class_3481.field_20339) || blockState.method_26164(class_3481.field_15462) || blockState.method_26164(class_3481.field_20341);
    }
}
