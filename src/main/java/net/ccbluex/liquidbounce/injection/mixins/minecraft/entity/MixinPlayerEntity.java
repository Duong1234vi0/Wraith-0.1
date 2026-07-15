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
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.CriticalAttackEvent;
import net.ccbluex.liquidbounce.event.events.PlayerSafeWalkEvent;
import net.ccbluex.liquidbounce.event.events.PlayerStrideEvent;
import net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer.FakePlayer;
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleKeepSprint;
import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.modes.CriticalsNoGround;
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleSprint;
import net.ccbluex.liquidbounce.features.module.modules.world.ModuleNoSlowBreak;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayerEntity extends MixinLivingEntity {

    @Shadow
    public abstract void tick();

    @Shadow
    public abstract SoundSource getSoundSource();

    /**
     * Hook player stride event
     */
    @ModifyVariable(method = "aiStep", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;yBob:F", shift = At.Shift.BEFORE, ordinal = 0), index = 1, ordinal = 0, require = 0, allow = 1)
    private float hookStrideForce(float strideForce) {
        final PlayerStrideEvent event = new PlayerStrideEvent(strideForce);
        EventManager.INSTANCE.callEvent(event);
        return event.getStrideForce();
    }

    /**
     * Hook safe walk event
     */
    @ModifyReturnValue(method = "isStayingOnGroundSurface", at = @At("RETURN"))
    private boolean hookSafeWalk(boolean original) {
        final var event = EventManager.INSTANCE.callEvent(new PlayerSafeWalkEvent());
        return original || event.isSafeWalk();
    }

    /**
     * Hook velocity rotation modification
     * <p>
     * There are a few velocity changes when attacking an entity, which could be easily detected by anti-cheats when a different server-side rotation is applied.
     */
    @ModifyExpressionValue(method = {"causeExtraKnockback",
            "doSweepAttack"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getYRot()F"))
    private float hookFixRotation(float original) {
        if ((Object) this != Minecraft.getInstance().player) {
            return original;
        }

        var rotationManager = RotationManager.INSTANCE;
        var rotation = rotationManager.getCurrentRotation();
        var rotationTarget = rotationManager.getActiveRotationTarget();

        if (rotationTarget == null || rotationTarget.getMovementCorrection() == MovementCorrection.OFF || rotation == null) {
            return original;
        }

        return rotation.getYaw();
    }

    @Inject(method = "isMobilityRestricted", at = @At("HEAD"), cancellable = true)
    private void hookSprintIgnoreBlindness(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == Minecraft.getInstance().player && ModuleSprint.INSTANCE.getShouldIgnoreBlindness()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;isSpectator()Z",
            ordinal = 1,
            shift = At.Shift.BEFORE))
    private void hookNoClip(CallbackInfo ci) {
    }

    @ModifyExpressionValue(method = "getDestroySpeed", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;hasEffect(Lnet/minecraft/core/Holder;)Z"))
    private boolean injectFatigueNoSlow(boolean original) {
        if ((Object) this == Minecraft.getInstance().player && ModuleNoSlowBreak.getMiningFatigue()) {
            return false;
        }

        return original;
    }


    @ModifyExpressionValue(method = "getDestroySpeed", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean injectWaterNoSlow(boolean original) {
        if ((Object) this == Minecraft.getInstance().player && ModuleNoSlowBreak.getWater()) {
            return false;
        }

        return original;
    }

    @ModifyExpressionValue(method = "getDestroySpeed", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;onGround()Z"))
    private boolean injectOnAirNoSlow(boolean original) {
        if ((Object) this == Minecraft.getInstance().player) {
            if (ModuleNoSlowBreak.getOnAir()) {
                return true;
            }

            if (CriticalsNoGround.INSTANCE.getRunning()) {
                return false;
            }
        }

        return original;
    }

    @SuppressWarnings({"UnreachableCode", "ConstantValue"})
    @Redirect(method = "causeExtraKnockback", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;multiply(DDD)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 hookSlowVelocity(Vec3 instance, double x, double y, double z) {
        if ((Object) this == Minecraft.getInstance().player && ModuleKeepSprint.INSTANCE.getRunning()) {
            x = z = ModuleKeepSprint.INSTANCE.getMotion();
        }

        return instance.multiply(x, y, z);
    }

    @SuppressWarnings("UnreachableCode")
    @WrapWithCondition(method = "causeExtraKnockback", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setSprinting(Z)V", ordinal = 0))
    private boolean hookSlowVelocity(Player instance, boolean b) {
        if ((Object) this == Minecraft.getInstance().player) {
            ModuleKeepSprint.INSTANCE.setSprinting(b);
            return !ModuleKeepSprint.INSTANCE.getRunning() || b;
        }

        return true;
    }

    @SuppressWarnings({"UnreachableCode", "ConstantValue"})
    @ModifyExpressionValue(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSprinting()Z"))
    private boolean hookSlowVelocity(boolean original) {
        if ((Object) this == Minecraft.getInstance().player && ModuleKeepSprint.INSTANCE.getRunning()) {
            return ModuleKeepSprint.INSTANCE.getSprinting();
        }

        return original;
    }

    @Inject(
            method = "attackVisualEffects(Lnet/minecraft/world/entity/Entity;ZZZZF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;crit(Lnet/minecraft/world/entity/Entity;)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookCriticalAttack(Entity target, boolean criticalAttack, boolean sweepAttack, boolean fullStrengthAttack,
            boolean stabAttack, float magicBoost, CallbackInfo ci) {
        if ((Object) this != Minecraft.getInstance().player) {
            return;
        }

        if (!(target instanceof LivingEntity livingTarget) || !livingTarget.isAlive()) {
            return;
        }

        EventManager.INSTANCE.callEvent(new CriticalAttackEvent(Player.class.cast(this), livingTarget));
    }

    @ModifyReturnValue(method = "entityInteractionRange", at = @At("RETURN"))
    private double hookEntityInteractionRange(double original) {
        return original;
    }

    @ModifyReturnValue(method = "blockInteractionRange", at = @At("RETURN"))
    private double hookBlockInteractionRange(double original) {
        return original;
    }

    @Unique
    private Entity liquid_bounce$attackSoundTarget;

    @Inject(method = "attack", at = @At("HEAD"))
    private void hookAttackSoundTarget(Entity target, CallbackInfo ci) {
        liquid_bounce$attackSoundTarget = target;
    }

    @Inject(method = "attack", at = @At("RETURN"))
    private void hookAttackSoundTargetReset(Entity target, CallbackInfo ci) {
        liquid_bounce$attackSoundTarget = null;
    }

    @Inject(method = "playServerSideSound", at = @At("HEAD"))
    private void hookPlaySound(SoundEvent sound, CallbackInfo ci) {
        liquid_bounce$playSoundIfFakePlayer(liquid_bounce$attackSoundTarget, sound);
    }

    /**
     * When the target is a fake player, this method will play a client side sound.
     */
    @Unique
    private void liquid_bounce$playSoundIfFakePlayer(Entity target, SoundEvent soundEvent) {
        if (target instanceof FakePlayer) {
            level().playSound(Player.class.cast(this), getX(), getY(), getZ(), soundEvent, getSoundSource(), 1F, 1F);
        }
    }

}
