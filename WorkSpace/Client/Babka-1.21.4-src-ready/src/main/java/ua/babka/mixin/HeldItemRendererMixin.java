package ua.babka.mixin;

import com.google.common.base.MoreObjects;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.SwingAnimations;
import ua.babka.module.impl.render.ViewModel;
import ua.babka.render.shader.ShaderHandsRenderer;
import ua.babka.util.Instance;

@Mixin(value={HeldItemRenderer.class}, priority=900)
public abstract class HeldItemRendererMixin {
    private static final boolean HeldItemRendererMixin = FabricLoader.getInstance().isModLoaded("hold-my-items");
    @Shadow
    private ItemStack mainHand;
    @Shadow
    private float equipProgressMainHand;
    @Shadow
    private float prevEquipProgressMainHand;
    @Shadow
    private float prevEquipProgressOffHand;
    @Shadow
    private float equipProgressOffHand;
    @Shadow
    private ItemStack offHand;

    @Shadow
    protected abstract void renderFirstPersonItem(AbstractClientPlayerEntity var1, float var2, float var3, Hand var4, float var5, ItemStack var6, float var7, MatrixStack var8, VertexConsumerProvider var9, int var10);

    @Shadow
    protected abstract void swingArm(float var1, float var2, MatrixStack var3, int var4, Arm var5);

    @Shadow
    private static HeldItemRenderer.HandRenderType getHandRenderType(ClientPlayerEntity player) {
        throw new AssertionError();
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;push()V", shift=At.Shift.AFTER, ordinal=0)})
    public void injectAfterMatrixPushHandPosition(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (HeldItemRendererMixin) {
            return;
        }
        ViewModel viewModel = Instance.get(ViewModel.class);
        if (viewModel.isEnabled() && !item.isEmpty() && !item.contains(DataComponentTypes.MAP_ID)) {
            boolean isMainHand = hand == Hand.MAIN_HAND;
            Arm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            viewModel.applyHandPosition(matrices, arm);
        }
    }

    @Redirect(method={"renderFirstPersonItem"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;swingArm(FFLnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/util/Arm;)V", ordinal=2))
    public void redirectSwingArmForCustomAnim(HeldItemRenderer instance, float swingProgress, float equipProgress, MatrixStack matrices, int armX, Arm arm) {
        if (HeldItemRendererMixin) {
            this.swingArm(swingProgress, equipProgress, matrices, armX, arm);
            return;
        }
        SwingAnimations swingAnimation = Instance.get(SwingAnimations.class);
        if (swingAnimation.isEnabled() && swingAnimation.shouldUseCustom()) {
            if (arm == Arm.RIGHT) {
                swingAnimation.renderSwordAnimation(matrices, swingProgress, equipProgress, arm);
            } else {
                this.swingArm(swingProgress, equipProgress, matrices, armX, arm);
            }
        } else {
            this.swingArm(swingProgress, equipProgress, matrices, armX, arm);
        }
    }

    @Overwrite
    public void renderItem(float tickDelta, MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, ClientPlayerEntity player, int light) {
        float k2;
        float j2;
        ShaderHandsRenderer.getInstance().captureBeforeHands();
        if (HeldItemRendererMixin) {
            float k3;
            float j3;
            float f2 = player.getHandSwingProgress(tickDelta);
            Hand hand = (Hand)MoreObjects.firstNonNull((Object)player.preferredHand, (Object)Hand.MAIN_HAND);
            float g2 = player.getLerpedPitch(tickDelta);
            HeldItemRenderer.HandRenderType handRenderType = ua.babka.mixin.HeldItemRendererMixin.getHandRenderType(player);
            if (handRenderType.renderMainHand) {
                j3 = hand == Hand.MAIN_HAND ? f2 : 0.0f;
                k3 = 1.0f - MathHelper.lerp((float)tickDelta, (float)this.prevEquipProgressMainHand, (float)this.equipProgressMainHand);
                this.renderFirstPersonItem((AbstractClientPlayerEntity)player, tickDelta, g2, Hand.MAIN_HAND, j3, this.mainHand, k3, matrices, (VertexConsumerProvider)vertexConsumers, light);
            }
            if (handRenderType.renderOffHand) {
                j3 = hand == Hand.OFF_HAND ? f2 : 0.0f;
                k3 = 1.0f - MathHelper.lerp((float)tickDelta, (float)this.prevEquipProgressOffHand, (float)this.equipProgressOffHand);
                this.renderFirstPersonItem((AbstractClientPlayerEntity)player, tickDelta, g2, Hand.OFF_HAND, j3, this.offHand, k3, matrices, (VertexConsumerProvider)vertexConsumers, light);
            }
            vertexConsumers.draw();
            ShaderHandsRenderer.getInstance().captureAfterHands();
            return;
        }
        float f3 = player.getHandSwingProgress(tickDelta);
        Hand hand = (Hand)MoreObjects.firstNonNull((Object)player.preferredHand, (Object)Hand.MAIN_HAND);
        float g3 = player.getLerpedPitch(tickDelta);
        HeldItemRenderer.HandRenderType handRenderType = ua.babka.mixin.HeldItemRendererMixin.getHandRenderType(player);
        if (handRenderType.renderMainHand) {
            j2 = hand == Hand.MAIN_HAND ? f3 : 0.0f;
            k2 = 1.0f - MathHelper.lerp((float)tickDelta, (float)this.prevEquipProgressMainHand, (float)this.equipProgressMainHand);
            this.renderFirstPersonItem((AbstractClientPlayerEntity)player, tickDelta, g3, Hand.MAIN_HAND, j2, this.mainHand, k2, matrices, (VertexConsumerProvider)vertexConsumers, light);
        }
        if (handRenderType.renderOffHand) {
            j2 = hand == Hand.OFF_HAND ? f3 : 0.0f;
            k2 = 1.0f - MathHelper.lerp((float)tickDelta, (float)this.prevEquipProgressOffHand, (float)this.equipProgressOffHand);
            this.renderFirstPersonItem((AbstractClientPlayerEntity)player, tickDelta, g3, Hand.OFF_HAND, j2, this.offHand, k2, matrices, (VertexConsumerProvider)vertexConsumers, light);
        }
        vertexConsumers.draw();
        ShaderHandsRenderer.getInstance().captureAfterHands();
    }
}

