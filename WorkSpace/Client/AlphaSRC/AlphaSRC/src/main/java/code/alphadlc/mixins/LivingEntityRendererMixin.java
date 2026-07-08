/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_10042
 *  net.minecraft.class_10055
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1921
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_465
 *  net.minecraft.class_583
 *  net.minecraft.class_591
 *  net.minecraft.class_922
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.render.EntityColorEvent;
import code.alphadlc.features.impl.render.Chams;
import code.alphadlc.features.impl.render.CustomModelModule;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1921;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_465;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_922.class})
public abstract class LivingEntityRendererMixin
implements QuickImports {
    @Shadow
    protected class_583<?> field_4737;

    @Shadow
    @Nullable
    protected abstract class_1921 method_24302(class_10042 var1, boolean var2, boolean var3, boolean var4);

    @Redirect(method={"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/render/RenderLayer;"))
    private class_1921 renderHook(class_922 instance, class_10042 state, boolean showBody, boolean translucent, boolean showOutline) {
        EntityColorEvent event;
        if (!translucent && state.field_53329 == 0.6f && (event = SafeEvents.dispatch(() -> new EntityColorEvent(-1))) != null && event.isCancelled()) {
            translucent = true;
        }
        return this.method_24302(state, showBody, translucent, showOutline);
    }

    @Inject(method={"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V")})
    private void AlphaDLC$hidePlayerModel(class_10042 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        class_1297 entity;
        class_583<?> class_5832;
        class_10055 playerState;
        block6: {
            block5: {
                if (!(state instanceof class_10055)) break block5;
                playerState = (class_10055)state;
                class_5832 = this.field_4737;
                if (class_5832 instanceof class_591) break block6;
            }
            return;
        }
        class_591 playerModel = (class_591)class_5832;
        class_1297 class_12972 = entity = LivingEntityRendererMixin.mc.field_1687 == null ? null : LivingEntityRendererMixin.mc.field_1687.method_8469(playerState.field_53528);
        if (!(entity instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)entity;
        if (CustomModelModule.getModelType(player) != null) {
            this.hidePlayerModel(playerModel);
        }
    }

    @Redirect(method={"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"))
    private void renderModelHook(class_583<?> instance, class_4587 matrixStack, class_4588 vertexConsumer, int i, int j, int l, @Local(ordinal=0, argsOnly=true) class_10042 renderState) {
        EntityColorEvent event;
        if (renderState instanceof class_10055) {
            class_10055 playerState = (class_10055)renderState;
            if (instance instanceof class_591) {
                class_1297 entity;
                class_591 playerModel = (class_591)instance;
                if (LivingEntityRendererMixin.mc.field_1687 != null && (entity = LivingEntityRendererMixin.mc.field_1687.method_8469(playerState.field_53528)) instanceof class_1657) {
                    class_1657 player = (class_1657)entity;
                    Chams chams = Chams.getInstance();
                    if (chams != null && chams.shouldRender(player) && CustomModelModule.getModelType(player) == null) {
                        chams.renderPlayer(matrixStack, player, playerModel);
                        return;
                    }
                }
            }
        }
        int color = l;
        if (renderState.field_53461 && (event = SafeEvents.dispatch(() -> new EntityColorEvent(l))) != null) {
            color = event.getColor();
        }
        instance.method_62100(matrixStack, vertexConsumer, i, j, color);
    }

    private void hidePlayerModel(class_591 playerModel) {
        playerModel.field_3398.field_3665 = false;
        playerModel.field_3394.field_3665 = false;
        playerModel.field_3391.field_3665 = false;
        playerModel.field_3401.field_3665 = false;
        playerModel.field_27433.field_3665 = false;
        playerModel.field_3392.field_3665 = false;
        playerModel.field_3397.field_3665 = false;
        playerModel.field_3483.field_3665 = false;
        playerModel.field_3484.field_3665 = false;
        playerModel.field_3486.field_3665 = false;
        playerModel.field_3482.field_3665 = false;
        playerModel.field_3479.field_3665 = false;
    }

    @ModifyExpressionValue(method={"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;lerpAngleDegrees(FFF)F")})
    private float lerpAngleDegreesHook(float original, @Local(ordinal=0, argsOnly=true) class_1309 entity, @Local(ordinal=0, argsOnly=true) float delta) {
        if (LivingEntityRendererMixin.mc.field_1724 == null) {
            return original;
        }
        TurnsConnection controller = TurnsConnection.INSTANCE;
        if (entity.equals((Object)LivingEntityRendererMixin.mc.field_1724) && controller.getPreviousRotation().getYaw() != LivingEntityRendererMixin.mc.field_1724.method_36454() && !(LivingEntityRendererMixin.mc.field_1755 instanceof class_465)) {
            return class_3532.method_17821((float)delta, (float)controller.getPreviousRotation().getYaw(), (float)controller.getRotation().getYaw());
        }
        return original;
    }

    @ModifyExpressionValue(method={"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getLerpedPitch(F)F")})
    private float getLerpedPitchHook(float original, @Local(ordinal=0, argsOnly=true) class_1309 entity, @Local(ordinal=0, argsOnly=true) float delta) {
        if (LivingEntityRendererMixin.mc.field_1724 == null) {
            return original;
        }
        TurnsConnection controller = TurnsConnection.INSTANCE;
        if (entity.equals((Object)LivingEntityRendererMixin.mc.field_1724) && controller.getPreviousRotation().getPitch() != LivingEntityRendererMixin.mc.field_1724.method_36455() && !(LivingEntityRendererMixin.mc.field_1755 instanceof class_465)) {
            return class_3532.method_16439((float)delta, (float)controller.getPreviousRotation().getPitch(), (float)controller.getRotation().getPitch());
        }
        return original;
    }
}
