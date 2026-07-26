/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.injection.mixins.minecraft.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.PlayerEquipmentChangeEvent;
import net.ccbluex.liquidbounce.event.events.PlayerAfterJumpEvent;
import net.ccbluex.liquidbounce.event.events.PlayerJumpEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.*;
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMovementContextManager;
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends MixinEntity {

    @Shadow
    public boolean jumping;

    @Shadow
    public abstract float getJumpPower();

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract void tick();

    @Shadow public abstract void swing(InteractionHand hand, boolean sendToSwingingEntity);

    @Shadow
    public abstract void setHealth(float health);

    @Inject(method = "setItemSlot", at = @At("HEAD"))
    private void hookPlayerEquipmentChange(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
        if ((Object) this instanceof Player player) {
            EventManager.INSTANCE.callEvent(new PlayerEquipmentChangeEvent(player, slot, stack));
        }
    }

    @Shadow
    public abstract boolean isFallFlying();

    @Shadow
    protected abstract boolean canGlide();

    @ModifyExpressionValue(
            method = "travelInAir",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getEffect(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/effect/MobEffectInstance;",
                    ordinal = 0
            ),
            require = 1,
            allow = 1
    )
    public MobEffectInstance hookTravelStatusEffect(MobEffectInstance original) {
        return original;
    }

    @ModifyExpressionValue(
            method = "getEffectiveGravity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z",
                    ordinal = 0
            ),
            require = 1,
            allow = 1
    )
    public boolean hookTravelStatusEffect(boolean original) {
        return original;
    }

    @Unique
    private PlayerJumpEvent jumpEvent;

    @Inject(method = "jumpFromGround", at = @At("HEAD"), cancellable = true)
    private void hookJumpEvent(CallbackInfo ci) {
        if ((Object) this != Minecraft.getInstance().player) {
            return;
        }

        jumpEvent = EventManager.INSTANCE.callEvent(new PlayerJumpEvent(getJumpPower(), this.getYRot()));
        if (jumpEvent.isCancelled()) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getJumpPower()F"))
    private float hookJumpEvent(float original) {
        // Replaces ((Object) this) != Minecraft.getInstance().player
        if (jumpEvent == null) {
            return original;
        }

        return jumpEvent.getMotion();
    }

    @ModifyExpressionValue(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getYRot()F"))
    private float hookJumpYaw(float original) {
        // Replaces ((Object) this) != Minecraft.getInstance().player
        if (jumpEvent == null) {
            return original;
        }

        return jumpEvent.getYaw();
    }

    @Inject(method = "jumpFromGround", at = @At("RETURN"))
    private void hookAfterJumpEvent(CallbackInfo ci) {
        jumpEvent = null;

        if ((Object) this != Minecraft.getInstance().player) {
            return;
        }

        EventManager.INSTANCE.callEvent(PlayerAfterJumpEvent.INSTANCE);
    }

    /**
     * Hook velocity rotation modification
     * <p>
     * Jump according to modified rotation. Prevents detection by movement sensitive anticheats.
     */
    @ModifyExpressionValue(method = "jumpFromGround", at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 hookFixRotation(Vec3 original) {
        var rotation = RotationManager.INSTANCE.getCurrentRotation();
        var rotationTarget = RotationManager.INSTANCE.getActiveRotationTarget();

        if ((Object) this != Minecraft.getInstance().player) {
            return original;
        }

        Float focusedFullYaw = FocusedFullMovementContextManager.jumpYaw();
        if (focusedFullYaw != null) {
            FocusedFullMovementContextManager.observeJumpYaw(focusedFullYaw);
            float yaw = focusedFullYaw * 0.017453292F;
            return new Vec3(-Mth.sin(yaw) * 0.2F, 0.0, Mth.cos(yaw) * 0.2F);
        }

        if (rotationTarget == null || rotationTarget.getMovementCorrection() == MovementCorrection.OFF ||
                rotationTarget.getMovementCorrection() == MovementCorrection.FOCUSED_FULL || rotation == null) {
            return original;
        }

        var yaw = rotation.getYaw() * 0.017453292F;

        return new Vec3(-Mth.sin(yaw) * 0.2F, 0.0, Mth.cos(yaw) * 0.2F);
    }

    @Inject(method = "push", at = @At("HEAD"), cancellable = true)
    private void hookNoPush(CallbackInfo callbackInfo) {
        if (!ModuleNoPush.canPush(NoPushBy.ENTITIES)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void hookTickMovement(CallbackInfo callbackInfo) {
        // 1.26.x no longer exposes the old jumping cooldown field here.
    }

    @Inject(method = "aiStep", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/LivingEntity;jumping:Z"))
    private void hookAirJump(CallbackInfo callbackInfo) {
    }

    @Unique
    private boolean previousElytra = false;

    @Inject(method = "updateFallFlying", at = @At("TAIL"))
    public void recastIfLanded(CallbackInfo callbackInfo) {
        if ((Object) this != Minecraft.getInstance().player) {
            return;
        }

        var elytra = isFallFlying();
        if (ModuleElytraRecast.INSTANCE.getRunning() && previousElytra && !elytra) {
            Minecraft.getInstance().getSoundManager().stop(SoundEvents.ELYTRA_FLYING.location(),
                    SoundSource.PLAYERS);
            ModuleElytraRecast.INSTANCE.recastElytra();
        }

        previousElytra = elytra;
    }

    /**
     * Gliding using modified-rotation
     */
    @ModifyExpressionValue(method = "updateFallFlyingMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getXRot()F"))
    private float hookModifyFallFlyingPitch(float original) {
        if ((Object) this != Minecraft.getInstance().player) {
            return original;
        }

        var rotation = RotationManager.INSTANCE.getCurrentRotation();
        var rotationTarget = RotationManager.INSTANCE.getActiveRotationTarget();

        if (rotation == null || rotationTarget == null || rotationTarget.getMovementCorrection() == MovementCorrection.OFF) {
            return original;
        }

        if (rotationTarget.getMovementCorrection() == MovementCorrection.FOCUSED_FULL &&
                !ModuleKillAura.isFocusedFullMoveFixActive()) {
            return original;
        }

        return rotation.getPitch();
    }

    /**
     * Gliding using modified-rotation
     */
    @ModifyExpressionValue(method = "updateFallFlyingMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getLookAngle()Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 hookModifyFallFlyingRotationVector(Vec3 original) {
        if ((Object) this != Minecraft.getInstance().player) {
            return original;
        }

        var rotation = RotationManager.INSTANCE.getCurrentRotation();
        var rotationTarget = RotationManager.INSTANCE.getActiveRotationTarget();

        if (rotation == null || rotationTarget == null || rotationTarget.getMovementCorrection() == MovementCorrection.OFF) {
            return original;
        }

        if (rotationTarget.getMovementCorrection() == MovementCorrection.FOCUSED_FULL &&
                !ModuleKillAura.isFocusedFullMoveFixActive()) {
            return original;
        }

        return rotation.getDirectionVector();
    }

}
