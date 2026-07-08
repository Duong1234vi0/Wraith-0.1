/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_742
 *  net.minecraft.class_759
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.item.HandAnimationEvent;
import code.alphadlc.events.item.HandOffsetEvent;
import code.alphadlc.events.render.ItemRendererEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_759.class})
public abstract class HeldItemRendererMixin {
    @Inject(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;push()V", shift=At.Shift.AFTER)})
    private void renderFirstPersonItemHook(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 stack, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        class_1306 arm = hand == class_1268.field_5808 ? player.method_6068() : player.method_6068().method_5928();
        SafeEvents.dispatch(() -> new HandOffsetEvent(matrices, stack, hand, player, swingProgress, equipProgress, tickDelta, arm));
    }

    @WrapOperation(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
    private void itemRenderHook(class_759 instance, class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, Operation<Void> original) {
        ItemRendererEvent event = SafeEvents.dispatch(() -> new ItemRendererEvent(player, item, hand));
        original.call(new Object[]{instance, event != null ? event.getPlayer() : player, Float.valueOf(tickDelta), Float.valueOf(pitch), event != null ? event.getHand() : hand, Float.valueOf(swingProgress), event != null ? event.getStack() : item, Float.valueOf(equipProgress), matrices, vertexConsumers, light});
    }

    @WrapOperation(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;swingArm(FFLnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/util/Arm;)V", ordinal=2)})
    private void handAnimationHook(class_759 instance, float swingProgress, float equipProgress, class_4587 matrices, int armX, class_1306 arm, Operation<Void> original, @Local(ordinal=0, argsOnly=true) class_742 player, @Local(ordinal=0, argsOnly=true) float tickDelta, @Local(ordinal=0, argsOnly=true) class_1268 hand, @Local(ordinal=0, argsOnly=true) class_1799 stack) {
        HandAnimationEvent event = SafeEvents.dispatch(() -> new HandAnimationEvent(matrices, hand, swingProgress, player, stack, arm, equipProgress, tickDelta));
        if (event == null || !event.isCancelled()) {
            original.call(new Object[]{instance, Float.valueOf(swingProgress), Float.valueOf(equipProgress), matrices, armX, arm});
        }
    }
}
