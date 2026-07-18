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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.MovementInputEvent;
import net.ccbluex.liquidbounce.event.events.SprintEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.inventorymove.ModuleInventoryMove;
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura;
import net.ccbluex.liquidbounce.features.module.modules.movement.noslow.ModuleNoSlow;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection;
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMoveFix;
import net.ccbluex.liquidbounce.utils.input.InputTracker;
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Input;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class MixinKeyboardInput extends MixinInput {

    @Shadow
    @Final
    private Options options;

    @Unique
    private float previousFullForward = 0.0f;

    @Unique
    private float previousFullSideways = 0.0f;

    @Unique
    private boolean previousFullMoveFix = false;

    /**
     * Hook inventory move module
     */
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z"))
    private boolean hookInventoryMove(KeyMapping instance, Operation<Boolean> original) {
        return original.call(instance) ||
                ModuleInventoryMove.INSTANCE.shouldHandleInputs(instance)
                        && InputTracker.INSTANCE.isPressedOnAny(instance);
    }

    /**
     * Later in the code, the sprint key is checked for being pressed. We need to update the state of the key
     * as well.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void hookInventoryMoveSprint(CallbackInfo ci) {
        if (ModuleInventoryMove.INSTANCE.shouldHandleInputs(this.options.keySprint)) {
            this.options.keySprint.setDown(InputTracker.INSTANCE.isPressedOnAny(this.options.keySprint));
        }
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void hookNoSlowMatrixPassengerInput(CallbackInfo ci) {
        if (!ModuleNoSlow.INSTANCE.shouldApplyMatrixKeyboardInput()) {
            return;
        }

        KeyboardInput keyboardInput = (KeyboardInput) (Object) this;
        Vec2 moveVector = keyboardInput.getMoveVector();
        float multiplier = 5.0f * ModuleNoSlow.INSTANCE.matrixInputMultiplier(moveVector.y, moveVector.x);
        ((MixinClientInputAccessor) this).liquid_bounce$setMoveVector(new Vec2(
                moveVector.x * multiplier,
                moveVector.y * multiplier
        ));
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "NEW", target = "(ZZZZZZZ)Lnet/minecraft/world/entity/player/Input;"))
    private Input modifyInput(Input original) {
        this.initial = original;

        var event = new MovementInputEvent(new DirectionalInput(original), original.jump(), original.shift());
        EventManager.INSTANCE.callEvent(event);
        var untransformedDirectionalInput = event.getDirectionalInput();
        var directionalInput = transformDirection(untransformedDirectionalInput);

        var sprintEvent = new SprintEvent(directionalInput, original.sprint(), SprintEvent.Source.INPUT);
        EventManager.INSTANCE.callEvent(sprintEvent);

        // Store the untransformed input for later use
        this.untransformed = new Input(
                untransformedDirectionalInput.getForwards(),
                untransformedDirectionalInput.getBackwards(),
                untransformedDirectionalInput.getLeft(),
                untransformedDirectionalInput.getRight(),
                event.getJump(),
                event.getSneak(),
                sprintEvent.getSprint()
        );

        return new Input(
                directionalInput.getForwards(),
                directionalInput.getBackwards(),
                directionalInput.getLeft(),
                directionalInput.getRight(),
                event.getJump(),
                event.getSneak(),
                sprintEvent.getSprint()
        );
    }

    @Unique
    private DirectionalInput transformDirection(DirectionalInput input) {
        var player = Minecraft.getInstance().player;
        var rotation = RotationManager.INSTANCE.getCurrentRotation();
        var configurable = RotationManager.INSTANCE.getActiveRotationTarget();
        var movementCorrection = configurable != null ? configurable.getMovementCorrection() : MovementCorrection.OFF;

        float z = KeyboardInput.calculateImpulse(input.getForwards(), input.getBackwards());
        float x = KeyboardInput.calculateImpulse(input.getLeft(), input.getRight());

        if (configurable == null || rotation == null || player == null) {
            resetFullMoveFixState();
            return input;
        }

        if (movementCorrection == MovementCorrection.FOCUSED_FULL) {
            if (!ModuleKillAura.isFocusedFullMoveFixActive()) {
                resetFullMoveFixState();
                return input;
            }

            var entity = configurable.getEntity();
            if (entity == null || (z == 0.0f && x == 0.0f)) {
                resetFullMoveFixState();
                return input;
            }

            resetFullMoveFixState();
            // #Edit By Codex: Focused Full now follows VCore's quantized target-position input search.
            float directionYaw = FocusedFullMoveFix.quantizedTargetYaw(
                    player.getX(), player.getEyeY(), player.getZ(),
                    entity.getX(), entity.getY(), entity.getZ(),
                    Minecraft.getInstance().options.sensitivity().get()
            );
            float[] movementInput = FocusedFullMoveFix.selectInput(rotation.getYaw(), directionYaw, z, x);
            return new DirectionalInput(movementInput[0], movementInput[1]);
        }

        if (movementCorrection == MovementCorrection.FULL) {
            var entity = configurable.getEntity();
            if (entity == null || (z == 0.0f && x == 0.0f)) {
                resetFullMoveFixState();
                return input;
            }

            float directionYaw = getDirectionYawTo(player, entity);
            float[] movementInput = getCorrectedInputSmooth(
                    rotation.getYaw(),
                    directionYaw,
                    z,
                    x,
                    previousFullMoveFix ? previousFullForward : 0.0f,
                    previousFullMoveFix ? previousFullSideways : 0.0f
            );

            updateFullMoveFixState(movementInput[0], movementInput[1]);
            return new DirectionalInput(movementInput[0], movementInput[1]);
        }

        resetFullMoveFixState();

        if (movementCorrection != MovementCorrection.SILENT) {
            return input;
        }

        float deltaYaw = player.getYRot() - rotation.getYaw();

        float newX = x * Mth.cos(deltaYaw * 0.017453292f) - z *
                Mth.sin(deltaYaw * 0.017453292f);
        float newZ = z * Mth.cos(deltaYaw * 0.017453292f) + x *
                Mth.sin(deltaYaw * 0.017453292f);

        var movementSideways = Math.round(newX);
        var movementForward = Math.round(newZ);

        return new DirectionalInput(movementForward, movementSideways);
    }

    @Unique
    private static float getDirectionYawTo(LocalPlayer player, Entity entity) {
        var box = entity.getBoundingBox();
        double targetX = (box.minX + box.maxX) * 0.5;
        double targetZ = (box.minZ + box.maxZ) * 0.5;
        double deltaX = targetX - player.getX();
        double deltaZ = targetZ - player.getZ();

        return (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0f);
    }

    @Unique
    private static double getMoveDirection(float rotationYaw, float moveForward, float moveStrafing) {
        float yaw = rotationYaw;
        if (moveForward < 0.0f) {
            yaw += 180.0f;
        }

        float forward = 1.0f;
        if (moveForward < 0.0f) {
            forward = -0.5f;
        } else if (moveForward > 0.0f) {
            forward = 0.5f;
        }

        if (moveStrafing > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (moveStrafing < 0.0f) {
            yaw += 90.0f * forward;
        }

        return Math.toRadians(yaw);
    }

    @Unique
    private static float[] getCorrectedInput(float rotationYaw, float directionYaw, float forward, float strafe) {
        if (forward == 0.0f && strafe == 0.0f) {
            return new float[]{0.0f, 0.0f};
        }

        double targetAngle = Mth.wrapDegrees(Math.toDegrees(getMoveDirection(directionYaw, forward, strafe)));

        float closestForward = 0.0f;
        float closestStrafe = 0.0f;
        float closestDifference = Float.MAX_VALUE;

        for (float predictedForward = -1.0f; predictedForward <= 1.0f; predictedForward += 1.0f) {
            for (float predictedStrafe = -1.0f; predictedStrafe <= 1.0f; predictedStrafe += 1.0f) {
                if (predictedForward == 0.0f && predictedStrafe == 0.0f) {
                    continue;
                }

                double predictedAngle = Mth.wrapDegrees(
                        Math.toDegrees(getMoveDirection(rotationYaw, predictedForward, predictedStrafe))
                );
                float difference = Math.abs(Mth.wrapDegrees((float) (targetAngle - predictedAngle)));

                if (difference < closestDifference) {
                    closestDifference = difference;
                    closestForward = predictedForward;
                    closestStrafe = predictedStrafe;
                }
            }
        }

        return new float[]{closestForward, closestStrafe};
    }

    @Unique
    private static float[] getCorrectedInputSmooth(
            float rotationYaw,
            float directionYaw,
            float forward,
            float strafe,
            float previousForward,
            float previousStrafe
    ) {
        float[] corrected = getCorrectedInput(rotationYaw, directionYaw, forward, strafe);
        if ((forward == 0.0f && strafe == 0.0f) || (previousForward == 0.0f && previousStrafe == 0.0f)) {
            return corrected;
        }

        double targetAngle = Mth.wrapDegrees(Math.toDegrees(getMoveDirection(directionYaw, forward, strafe)));
        double correctedAngle = Mth.wrapDegrees(Math.toDegrees(getMoveDirection(rotationYaw, corrected[0], corrected[1])));
        double previousAngle = Mth.wrapDegrees(Math.toDegrees(getMoveDirection(rotationYaw, previousForward, previousStrafe)));
        float correctedDifference = Math.abs(Mth.wrapDegrees((float) (targetAngle - correctedAngle)));
        float previousDifference = Math.abs(Mth.wrapDegrees((float) (targetAngle - previousAngle)));

        return previousDifference <= correctedDifference + 6.0f
                ? new float[]{previousForward, previousStrafe}
                : corrected;
    }

    @Unique
    private void updateFullMoveFixState(float forward, float sideways) {
        previousFullForward = forward;
        previousFullSideways = sideways;
        previousFullMoveFix = true;
    }

    @Unique
    private void resetFullMoveFixState() {
        previousFullForward = 0.0f;
        previousFullSideways = 0.0f;
        previousFullMoveFix = false;
    }

}
