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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeCam;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeLook;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleSmoothCamera;
import net.ccbluex.liquidbounce.features.module.modules.render.SmoothedCameraRotation;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class MixinCamera {

    @Shadow
    private boolean detached;
    @Shadow
    private @Nullable Entity entity;
    @Shadow
    private Vec3 position;
    @Shadow
    private float xRot;
    @Shadow
    private float yRot;

    @Shadow
    protected abstract void setRotation(float yRot, float xRot);

    @Shadow
    protected abstract float getMaxZoom(float maxZoom);

    @Shadow
    protected abstract void move(float zoom, float dy, float dx);

    @Shadow
    public abstract void setPosition(Vec3 pos);

    @Inject(method = "alignWithEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setPosition(DDD)V", shift = At.Shift.AFTER))
    private void hookFreeCamModifiedPosition(float partialTicks, CallbackInfo ci) {
        ModuleFreeCam.INSTANCE.applyCameraPosition(this.entity, partialTicks);
    }

    @Inject(method = "alignWithEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setPosition(DDD)V", shift = At.Shift.AFTER), cancellable = true)
    private void modifyCameraOrientationAfterPosition(float partialTicks, CallbackInfo ci) {
        modifyCameraOrientation(partialTicks, ci);
    }

    @Inject(method = "alignWithEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setPosition(Lnet/minecraft/world/phys/Vec3;)V", shift = At.Shift.AFTER), cancellable = true)
    private void modifyCameraOrientationAfterVectorPosition(float partialTicks, CallbackInfo ci) {
        modifyCameraOrientation(partialTicks, ci);
    }

    private void modifyCameraOrientation(float partialTicks, CallbackInfo ci) {
        if (this.entity instanceof LivingEntity livingEntity && livingEntity.isSleeping()) {
            ModuleSmoothCamera.resetState();
            return;
        }

        if (ModuleFreeCam.INSTANCE.getRunning()) {
            ModuleSmoothCamera.resetState();
            return;
        }

        var freeLook = ModuleFreeLook.INSTANCE.getRunning();
        var freeLookInvertedView = ModuleFreeLook.INSTANCE.isInvertedView();

        if (freeLook) {
            ModuleSmoothCamera.resetState();
            this.detached = true;

            var cameraYaw = ModuleFreeLook.INSTANCE.getCameraYaw();
            var cameraPitch = ModuleFreeLook.INSTANCE.getCameraPitch();

            if (freeLookInvertedView) {
                setRotation(cameraYaw + 180, -cameraPitch);
            } else {
                setRotation(cameraYaw, cameraPitch);
            }

            float scale = this.entity instanceof LivingEntity livingEntity ? livingEntity.getScale() : 1.0F;
            move(-getMaxZoom(4f * scale), 0.0f, 0.0f);

            ci.cancel();
            return;
        }

        var rotationTarget = RotationManager.INSTANCE.getActiveRotationTarget();
        var previousRotation = RotationManager.INSTANCE.getPreviousRotation();
        var currentRotation = RotationManager.INSTANCE.getCurrentRotation();

        var changeLook = rotationTarget != null &&
                rotationTarget.getMovementCorrection() == MovementCorrection.CHANGE_LOOK;
        if (currentRotation == null || previousRotation == null || !changeLook ||
                !RotationManager.INSTANCE.isRotatingAllowed(rotationTarget)) {
            if (ModuleSmoothCamera.INSTANCE.getRunning()) {
                SmoothedCameraRotation smoothedRotation = ModuleSmoothCamera.updateRotation(this.yRot, this.xRot);
                setRotation(smoothedRotation.getYaw(), smoothedRotation.getPitch());
            }
            return;
        }

        float targetYaw = Mth.lerp(partialTicks, previousRotation.getYaw(), currentRotation.getYaw());
        float targetPitch = Mth.lerp(partialTicks, previousRotation.getPitch(), currentRotation.getPitch());
        SmoothedCameraRotation smoothedRotation = ModuleSmoothCamera.INSTANCE.getRunning()
                ? ModuleSmoothCamera.updateRotation(targetYaw, targetPitch)
                : new SmoothedCameraRotation(targetYaw, targetPitch);

        setRotation(smoothedRotation.getYaw(), smoothedRotation.getPitch());
    }

    @Inject(method = "alignWithEntity", at = @At("TAIL"))
    private void applyFreeCamPlayerSelfRendering(float partialTicks, CallbackInfo ci) {
        if (ModuleFreeCam.INSTANCE.getRunning()) {
            ModuleSmoothCamera.resetState();
            this.detached = true;
            return;
        }

        if (ModuleSmoothCamera.INSTANCE.getRunning()) {
            setPosition(ModuleSmoothCamera.updatePosition(this.position));
        }
    }

}
