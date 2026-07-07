package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomModels;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeCam;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleRotations;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, S extends LivingEntityRenderState,
        M extends EntityModel<? super S>> {

    @Shadow
    protected M model;

    @Unique
    private boolean liquid_bounce$customModelSubmitted;

    @Unique
    private Rotation liquid_bounce$getRenderRotation(ModuleRotations.BodyPart bodyPart, boolean previous) {
        if (ModuleRotations.INSTANCE.getRunning() && ModuleRotations.INSTANCE.isPartAllowed(bodyPart)) {
            Rotation rotation = previous
                    ? ModuleRotations.INSTANCE.getPrevModelRotation()
                    : ModuleRotations.INSTANCE.getModelRotation();

            if (rotation != null) {
                return rotation;
            }
        }

        if (ModuleFreeCam.INSTANCE.getRunning()) {
            return RotationManager.INSTANCE.getServerRotation();
        }

        return null;
    }

    @Unique
    private boolean liquid_bounce$canOverwriteRotation(ModuleRotations.BodyPart bodyPart) {
        return liquid_bounce$getRenderRotation(bodyPart, true) != null
                && liquid_bounce$getRenderRotation(bodyPart, false) != null;
    }

    @ModifyExpressionValue(
            method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;solveBodyRot(Lnet/minecraft/world/entity/LivingEntity;FF)F")
    )
    private float hookBodyYaw(float original, LivingEntity entity, S state, float tickDelta) {
        if (entity != Minecraft.getInstance().player
                || !liquid_bounce$canOverwriteRotation(ModuleRotations.BodyPart.BODY)) {
            return original;
        }

        Rotation previous = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.BODY, true);
        Rotation current = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.BODY, false);
        return Mth.rotLerp(tickDelta, previous.getYaw(), current.getYaw());
    }

    @ModifyExpressionValue(
            method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;rotLerp(FFF)F")
    )
    private float hookHeadYaw(float original, LivingEntity entity, S state, float tickDelta) {
        if (entity != Minecraft.getInstance().player
                || !liquid_bounce$canOverwriteRotation(ModuleRotations.BodyPart.HEAD)) {
            return original;
        }

        Rotation previous = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.HEAD, true);
        Rotation current = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.HEAD, false);
        return Mth.rotLerp(tickDelta, previous.getYaw(), current.getYaw());
    }

    @ModifyExpressionValue(
            method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getXRot(F)F")
    )
    private float hookPitch(float original, LivingEntity entity, S state, float tickDelta) {
        if (entity != Minecraft.getInstance().player
                || !liquid_bounce$canOverwriteRotation(ModuleRotations.BodyPart.HEAD)) {
            return original;
        }

        Rotation previous = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.HEAD, true);
        Rotation current = liquid_bounce$getRenderRotation(ModuleRotations.BodyPart.HEAD, false);
        return Mth.rotLerp(tickDelta, previous.getPitch(), current.getPitch());
    }

    @ModifyExpressionValue(
            method = "submit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;getRenderType(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/renderer/rendertype/RenderType;")
    )
    private RenderType hookCustomModelsHideVanillaBody(RenderType original, S state, PoseStack poseStack,
                                                       SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        return ModuleCustomModels.shouldUseRabbit(state) ? null : original;
    }

    @Inject(method = "submit", at = @At("HEAD"))
    private void hookCustomModelsStartSubmit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                                             CameraRenderState camera, CallbackInfo ci) {
        this.liquid_bounce$customModelSubmitted = false;
    }

    @Inject(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EntityRenderState;FF)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookCustomModelsRabbit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                                        CameraRenderState camera, CallbackInfo ci) {
        if (!this.liquid_bounce$customModelSubmitted
                && state instanceof AvatarRenderState avatarState
                && this.model instanceof PlayerModel playerModel
                && ModuleCustomModels.shouldUseRabbit(avatarState)) {
            ModuleCustomModels.renderRabbit(playerModel, poseStack, submitNodeCollector, avatarState);
        }
        this.liquid_bounce$customModelSubmitted = true;
    }

}
