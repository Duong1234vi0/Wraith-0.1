package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.Chams;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;

@Mixin(value={LivingEntityRenderer.class})
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>>
extends EntityRenderer<T, S>
implements FeatureRendererContext<S, M>,
IMinecraft {
    @Shadow
    private static float clampBodyYaw(LivingEntity entity, float degrees, float tickDelta) {
        return 0.0f;
    }

    @Shadow
    public static boolean shouldFlipUpsideDown(LivingEntity entity) {
        return false;
    }

    protected LivingEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Overwrite
    public void updateRenderState(T livingEntity, S livingEntityRenderState, float f2) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f2);
        float yaw = MathHelper.lerpAngleDegrees((float)f2, (float)((LivingEntity)livingEntity).prevHeadYaw, (float)((LivingEntity)livingEntity).headYaw);
        ((LivingEntityRenderState)livingEntityRenderState).bodyYaw = LivingEntityRendererMixin.clampBodyYaw(livingEntity, yaw, f2);
        ((LivingEntityRenderState)livingEntityRenderState).yawDegrees = MathHelper.wrapDegrees((float)(yaw - ((LivingEntityRenderState)livingEntityRenderState).bodyYaw));
        ((LivingEntityRenderState)livingEntityRenderState).pitch = livingEntity.getLerpedPitch(f2);
        ((LivingEntityRenderState)livingEntityRenderState).customName = livingEntity.getCustomName();
        ((LivingEntityRenderState)livingEntityRenderState).flipUpsideDown = LivingEntityRendererMixin.shouldFlipUpsideDown(livingEntity);
        if (((LivingEntityRenderState)livingEntityRenderState).flipUpsideDown) {
            ((LivingEntityRenderState)livingEntityRenderState).pitch *= -1.0f;
            ((LivingEntityRenderState)livingEntityRenderState).yawDegrees *= -1.0f;
        }
        if (!livingEntity.hasVehicle() && livingEntity.isAlive()) {
            ((LivingEntityRenderState)livingEntityRenderState).limbFrequency = ((LivingEntity)livingEntity).limbAnimator.getPos(f2);
            ((LivingEntityRenderState)livingEntityRenderState).limbAmplitudeMultiplier = ((LivingEntity)livingEntity).limbAnimator.getSpeed(f2);
        } else {
            ((LivingEntityRenderState)livingEntityRenderState).limbFrequency = 0.0f;
            ((LivingEntityRenderState)livingEntityRenderState).limbAmplitudeMultiplier = 0.0f;
        }
        Entity entity = livingEntity.getVehicle();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity2 = (LivingEntity)entity;
            ((LivingEntityRenderState)livingEntityRenderState).headItemAnimationProgress = livingEntity2.limbAnimator.getPos(f2);
        } else {
            ((LivingEntityRenderState)livingEntityRenderState).headItemAnimationProgress = ((LivingEntityRenderState)livingEntityRenderState).limbFrequency;
        }
        ((LivingEntityRenderState)livingEntityRenderState).baseScale = livingEntity.getScale();
        ((LivingEntityRenderState)livingEntityRenderState).ageScale = livingEntity.getScaleFactor();
        ((LivingEntityRenderState)livingEntityRenderState).pose = livingEntity.getPose();
        ((LivingEntityRenderState)livingEntityRenderState).sleepingDirection = livingEntity.getSleepingDirection();
        if (((LivingEntityRenderState)livingEntityRenderState).sleepingDirection != null) {
            ((LivingEntityRenderState)livingEntityRenderState).standingEyeHeight = livingEntity.getEyeHeight(EntityPose.STANDING);
        }
        ((LivingEntityRenderState)livingEntityRenderState).shaking = livingEntity.isFrozen();
        ((LivingEntityRenderState)livingEntityRenderState).baby = livingEntity.isBaby();
        ((LivingEntityRenderState)livingEntityRenderState).touchingWater = livingEntity.isTouchingWater();
        ((LivingEntityRenderState)livingEntityRenderState).usingRiptide = livingEntity.isUsingRiptide();
        ((LivingEntityRenderState)livingEntityRenderState).hurt = ((LivingEntity)livingEntity).hurtTime > 0 || ((LivingEntity)livingEntity).deathTime > 0;
        ((LivingEntityRenderState)livingEntityRenderState).deathTime = ((LivingEntity)livingEntity).deathTime > 0 ? (float)((LivingEntity)livingEntity).deathTime + f2 : 0.0f;
        ((LivingEntityRenderState)livingEntityRenderState).invisibleToPlayer = ((LivingEntityRenderState)livingEntityRenderState).invisible && livingEntity.isInvisibleTo((PlayerEntity)MinecraftClient.getInstance().player);
        ((LivingEntityRenderState)livingEntityRenderState).hasOutline = MinecraftClient.getInstance().hasOutline(livingEntity);
    }

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRender(S state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (state instanceof PlayerEntityRenderState) {
            PlayerEntity player;
            Entity entity;
            PlayerEntityRenderState playerState = (PlayerEntityRenderState)state;
            Chams chams = Instance.get(Chams.class);
            if (chams != null && chams.isEnabled() && !Chams.PREVIEW_MODE && (entity = MinecraftClient.getInstance().world.getEntityById(playerState.id)) instanceof PlayerEntity && chams.shouldHideBaseModel(player = (PlayerEntity)entity)) {
                ci.cancel();
            }
        }
    }
}

