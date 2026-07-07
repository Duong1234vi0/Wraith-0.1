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
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.EventState;
import net.ccbluex.liquidbounce.event.events.*;
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleEntityControl;
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleNoPush;
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleSprint;
import net.ccbluex.liquidbounce.features.module.modules.movement.NoPushBy;
import net.ccbluex.liquidbounce.features.module.modules.movement.noslow.ModuleNoSlow;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeCam;
import net.ccbluex.liquidbounce.interfaces.ClientPlayerEntityAddition;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation;
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.ClipContext;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class MixinClientPlayerEntity extends MixinPlayerEntity implements ClientPlayerEntityAddition {

    @Shadow
    public ClientInput input;

    @Shadow
    public abstract boolean isUnderWater();

    @Unique
    private PlayerNetworkMovementTickEvent eventMotion;

    @Unique
    private int onGroundTicks = 0;
    @Unique
    private int airTicks = 0;

    /**
     * Hook entity tick event
     */
    @Inject(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V",
            shift = At.Shift.BEFORE,
            ordinal = 0),
            cancellable = true)
    private void hookTickEvent(CallbackInfo ci) {
        var tickEvent = new PlayerTickEvent();
        EventManager.INSTANCE.callEvent(tickEvent);

        if (tickEvent.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V",
            shift = At.Shift.AFTER,
            ordinal = 0))
    private void hookPostTickEvent(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(PlayerPostTickEvent.INSTANCE);
    }

    /**
     * Hook entity movement tick event
     */
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void hookMovementTickEvent(CallbackInfo callbackInfo) {
        EventManager.INSTANCE.callEvent(PlayerMovementTickEvent.INSTANCE);
    }

    /**
     * Hook entity movement tick event at HEAD and call out PRE tick movement event
     */
    @Inject(method = "sendPosition", at = @At("HEAD"))
    private void hookMovementPre(CallbackInfo callbackInfo) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        eventMotion = new PlayerNetworkMovementTickEvent(EventState.PRE, player.getX(), player.getY(), player.getZ(), player.onGround());
        EventManager.INSTANCE.callEvent(eventMotion);
    }

    @ModifyExpressionValue(method = "sendPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getX()D"))
    private double modifyXPosition(double original) {
        return eventMotion.getX();
    }

    @ModifyExpressionValue(method = "sendPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getY()D"))
    private double modifyYPosition(double original) {
        return eventMotion.getY();
    }

    @ModifyExpressionValue(method = "sendPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getZ()D"))
    private double modifyZPosition(double original) {
        return eventMotion.getZ();
    }

    @ModifyExpressionValue(method = "sendPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;onGround()Z"))
    private boolean modifyOnGround(boolean original) {
        return eventMotion.getGround();
    }

    /**
     * Hook entity movement tick event at RETURN and call out POST tick movement event
     */
    @Inject(method = "sendPosition", at = @At("RETURN"))
    private void hookMovementPost(CallbackInfo callbackInfo) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        EventManager.INSTANCE.callEvent(new PlayerNetworkMovementTickEvent(EventState.POST, player.getX(), player.getY(), player.getZ(), player.onGround()));
    }

    /**
     * Hook push out function tick at HEAD and call out push out event, which is able to stop the cancel the execution.
     */
    @Inject(method = "moveTowardsClosestSpace", at = @At("HEAD"), cancellable = true)
    private void hookPushOut(double x, double z, CallbackInfo ci) {
        if (!ModuleNoPush.canPush(NoPushBy.BLOCKS)) {
            ci.cancel();
            return;
        }

        final PlayerPushOutEvent pushOutEvent = new PlayerPushOutEvent();
        EventManager.INSTANCE.callEvent(pushOutEvent);
        if (pushOutEvent.isCancelled()) {
            ci.cancel();
        }
    }

    /**
     * Hook move function to modify movement
     */
    @ModifyVariable(method = "move", at = @At("HEAD"), name = "arg2", ordinal = 0, index = 2, argsOnly = true)
    private Vec3 hookMove(Vec3 movement, MoverType type) {
        return EventManager.INSTANCE.callEvent(new PlayerMoveEvent(type, movement)).getMovement();
    }

    /**
     * Hook counter for on ground and air ticks
     */
    @Inject(method = "move", at = @At("RETURN"))
    private void hookGroundAirTimeCounters(CallbackInfo ci) {
        if (this.onGround()) {
            onGroundTicks++;
            airTicks = 0;
        } else {
            airTicks++;
            onGroundTicks = 0;
        }
    }

    @Override
    public int liquid_bounce$getOnGroundTicks() {
        return onGroundTicks;
    }

    @Override
    public int liquid_bounce$getAirTicks() {
        return airTicks;
    }

    /**
     * Hook custom sneaking multiplier
     */
    @ModifyExpressionValue(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttributeValue(Lnet/minecraft/core/Holder;)D"))
    private double hookCustomSneakingMultiplier(double original) {
        var playerSneakMultiplier = new PlayerSneakMultiplier(original);
        EventManager.INSTANCE.callEvent(playerSneakMultiplier);
        return playerSneakMultiplier.getMultiplier();
    }

    /**
     * Hook custom multiplier
     */
    @WrapOperation(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec2;scale(F)Lnet/minecraft/world/phys/Vec2;", ordinal = 1))
    private Vec2 hookCustomMultiplier(Vec2 instance, float value, Operation<Vec2> original) {
        var playerUseMultiplier = new PlayerUseMultiplier(value, value);
        EventManager.INSTANCE.callEvent(playerUseMultiplier);
        return new Vec2(
            instance.x * playerUseMultiplier.getSideways(),
            instance.y * playerUseMultiplier.getForward()
        );
    }

    @ModifyExpressionValue(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", ordinal = 0))
    private boolean hookNoSlowItemUse(boolean original) {
        if (ModuleNoSlow.INSTANCE.getRunning() && ModuleNoSlow.INSTANCE.canNoSlow()) {
            return false;
        }
        return original;
    }

    @Inject(method = "isSlowDueToUsingItem", at = @At("HEAD"), cancellable = true)
    private void hookNoSlowPoseSlowdown(CallbackInfoReturnable<Boolean> cir) {
    }

    // Silent rotations (Rotation Manager)

    @ModifyExpressionValue(method = {"sendPosition", "tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getYRot()F"))
    private float hookSilentRotationYaw(float original) {
        Rotation rotation = RotationManager.INSTANCE.movementPacketRotation();
        if (rotation == null) {
            return original;
        }

        return rotation.getYaw();
    }

    @ModifyExpressionValue(method = {"sendPosition", "tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getXRot()F"))
    private float hookSilentRotationPitch(float original) {
        Rotation rotation = RotationManager.INSTANCE.movementPacketRotation();
        if (rotation == null) {
            return original;
        }

        return rotation.getPitch();
    }

    @ModifyExpressionValue(
            method = "pick(Lnet/minecraft/world/entity/Entity;DDF)Lnet/minecraft/world/phys/HitResult;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getViewVector(F)Lnet/minecraft/world/phys/Vec3;")
    )
    private static Vec3 hookPickRotationVector(Vec3 original, Entity cameraEntity, double blockInteractionRange,
                                               double entityInteractionRange, float partialTicks) {
        if (cameraEntity != Minecraft.getInstance().player) {
            return original;
        }

        Rotation rotation = RotationManager.INSTANCE.getCurrentRotation();
        return rotation != null ? rotation.getDirectionVector() : original;
    }

    @ModifyExpressionValue(
            method = "pick(Lnet/minecraft/world/entity/Entity;DDF)Lnet/minecraft/world/phys/HitResult;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;")
    )
    private static HitResult hookPickBlockRaycast(HitResult original, Entity cameraEntity, double blockInteractionRange,
                                                  double entityInteractionRange, float partialTicks) {
        if (cameraEntity != Minecraft.getInstance().player) {
            return original;
        }

        Rotation rotation = RotationManager.INSTANCE.getCurrentRotation();
        if (rotation == null) {
            return original;
        }

        Vec3 from = cameraEntity.getEyePosition(partialTicks);
        Vec3 direction = rotation.getDirectionVector();
        double maxDistance = Math.max(blockInteractionRange, entityInteractionRange);
        Vec3 to = from.add(direction.x * maxDistance, direction.y * maxDistance, direction.z * maxDistance);
        HitResult hitResult = cameraEntity.level().clip(new ClipContext(
                from, to, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, cameraEntity
        ));

        if (hitResult == null) {
            return original;
        }

        return hitResult;
    }

    @ModifyReturnValue(method = "isAutoJumpEnabled", at = @At("RETURN"))
    private boolean injectLegitStep(boolean original) {
        return new MinecraftAutoJumpEvent(original).getAutoJump();
    }

    @Inject(method = "swing", at = @At("HEAD"), cancellable = true)
    private void swingHand(InteractionHand hand, CallbackInfo ci) {
        var swingEvent = EventManager.INSTANCE.callEvent(new SwingHandEvent(hand));
        if (swingEvent.isCancelled()) {
            ci.cancel();
            return;
        }

    }

    @ModifyReturnValue(method = "getJumpRidingScale", at = @At("RETURN"))
    private float hookMountJumpStrength(float original) {
        if (ModuleEntityControl.getEnforceJumpStrength()) {
            return 1f;
        }

        return original;
    }

    @ModifyExpressionValue(method = "aiStep", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Abilities;mayfly:Z", opcode = Opcodes.GETFIELD))
    private boolean hookFreeCamPreventCreativeFly(boolean original) {
        return !ModuleFreeCam.INSTANCE.getRunning() && original;
    }

    @ModifyVariable(method = "sendPosition", at = @At("STORE"), ordinal = 1)
    private boolean hookFreeCamPreventRotations(boolean bl4) {
        // Prevent rotation changes when free cam is active, unless a rotation is being set via the rotation manager
        return (!ModuleFreeCam.INSTANCE.getRunning() ||
                RotationManager.INSTANCE.getCurrentRotation() != null) && bl4;
    }

    @ModifyExpressionValue(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Input;sprint()Z"))
    private boolean hookSprintStart(boolean original) {
        var event = new SprintEvent(new DirectionalInput(input), original, SprintEvent.Source.MOVEMENT_TICK);
        EventManager.INSTANCE.callEvent(event);
        return event.getSprint();
    }

    @ModifyExpressionValue(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;canStartSprinting()Z"))
    private boolean hookSprintStop(boolean original) {
        var event = new SprintEvent(new DirectionalInput(input), original, SprintEvent.Source.MOVEMENT_TICK);
        EventManager.INSTANCE.callEvent(event);
        return event.getSprint();
    }

    @ModifyExpressionValue(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSlowDueToUsingItem()Z"))
    private boolean hookNoSlowSprintAffectStart(boolean original) {
        return original;
    }

    @ModifyExpressionValue(method = "isSprintingPossible", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEnoughFoodToDoExhaustiveManoeuvres()Z"))
    private boolean hookSprintIgnoreHunger(boolean original) {
        return ModuleSprint.INSTANCE.getShouldIgnoreHunger() || original;
    }

    @ModifyExpressionValue(method = "shouldStopRunSprinting", at = @At(value = "FIELD", target = "Lnet/minecraft/client/player/LocalPlayer;horizontalCollision:Z", opcode = Opcodes.GETFIELD))
    private boolean hookSprintIgnoreCollision(boolean original) {
        return !ModuleSprint.INSTANCE.getShouldIgnoreCollision() && original;
    }

    // Restores the per-tick sprint-maintain SprintEvent (MOVEMENT_TICK) that 1.21.4 yarn
    // fired via `canSprint()` inside `tickMovement`. In Mojmap the equivalent decision
    // point is `shouldStopRunSprinting()` with inverted semantics, so we flip the flag
    // on the way in and on the way out to keep SprintEvent semantics identical.
    @ModifyReturnValue(method = "shouldStopRunSprinting", at = @At("RETURN"))
    private boolean hookSprintStopMaintain(boolean original) {
        final SprintEvent event = new SprintEvent(new DirectionalInput(input), !original, SprintEvent.Source.MOVEMENT_TICK);
        EventManager.INSTANCE.callEvent(event);
        return !event.getSprint();
    }

    @ModifyExpressionValue(method = "shouldStopRunSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSlowDueToUsingItem()Z"), require = 0)
    private boolean hookNoSlowSprintAffectStop(boolean original) {
        return original;
    }

    @ModifyReturnValue(method = "canStartSprinting", at = @At("RETURN"))
    private boolean hookIsWalking(boolean original) {
        if (!ModuleSprint.INSTANCE.getShouldSprintOmnidirectional()) {
            return original;
        }

        var moveVector = input.getMoveVector();
        var hasMovement = Math.abs(moveVector.y) > 1.0E-5F ||
                Math.abs(moveVector.x) > 1.0E-5F;
        var isWalking = (double) Math.abs(moveVector.y) >= 0.8 ||
                (double) Math.abs(moveVector.x) >= 0.8;
        return this.isUnderWater() ? hasMovement : isWalking;
    }

    @ModifyExpressionValue(method = "sendIsSprintingIfNeeded", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;isSprinting()Z")
    )
    private boolean hookNetworkSprint(boolean original) {
        var event = new SprintEvent(new DirectionalInput(input), original, SprintEvent.Source.NETWORK);
        EventManager.INSTANCE.callEvent(event);
        return event.getSprint();
    }

    @ModifyExpressionValue(method = "sendIsSprintingIfNeeded", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;isSneaking()Z"), require = 0)
    private boolean hookNetworkSneak(boolean original) {
        var event = new SneakNetworkEvent(new DirectionalInput(input), original);
        EventManager.INSTANCE.callEvent(event);
        return event.getSneak();
    }

}
