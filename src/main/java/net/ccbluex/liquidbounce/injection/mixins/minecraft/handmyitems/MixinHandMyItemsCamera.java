package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.CameraAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class MixinHandMyItemsCamera implements CameraAccessor {

    @Shadow
    @Final
    private Quaternionf rotation;
    @Shadow
    @Final
    private Vector3f forwards;
    @Shadow
    @Final
    private Vector3f up;
    @Shadow
    @Final
    private Vector3f left;
    @Shadow
    private float xRot;
    @Shadow
    private float yRot;
    @Shadow
    private int matrixPropertiesDirty;

    @Shadow
    public abstract void setPosition(Vec3 position);

    @Unique
    private float hmi$pitchOffset;
    @Unique
    private float hmi$yawOffset;
    @Unique
    private float hmi$rollOffset;
    @Unique
    private float hmi$xOffset;
    @Unique
    private float hmi$yOffset;
    @Unique
    private float hmi$zOffset;

    @Override
    public void hMI5_0$applyRotation() {
        if (!HandMyItemsRuntime.isActive()) {
            return;
        }
        this.rotation.add(new Quaternionf().rotationX(this.hmi$pitchOffset * Mth.DEG_TO_RAD));
        this.rotation.add(new Quaternionf().rotationY(this.hmi$yawOffset * Mth.DEG_TO_RAD));
        this.rotation.add(new Quaternionf().rotationZ(this.hmi$rollOffset * Mth.DEG_TO_RAD));
        this.hmi$refreshDirectionVectors();
    }

    @Override
    public void hMI5_0$setRotationValues(float pitch, float yaw, float roll) {
        this.hmi$pitchOffset = pitch;
        this.hmi$yawOffset = yaw;
        this.hmi$rollOffset = roll;
    }

    @Override
    public void hMI5_0$setPosValues(float x, float y, float z) {
        this.hmi$xOffset = x;
        this.hmi$yOffset = y;
        this.hmi$zOffset = z;
    }

    @Inject(method = "setRotation", at = @At("HEAD"), cancellable = true)
    private void hmi$applyRotationOffsets(float yRot, float xRot, CallbackInfo ci) {
        if (!HandMyItemsRuntime.isActive()) {
            this.hmi$clearOffsets();
            return;
        }
        if (FabricLoader.getInstance().isModLoaded("do_a_barrel_roll")) {
            return;
        }

        this.xRot = xRot;
        this.yRot = yRot;
        this.rotation.rotationYXZ(
            (float) Math.PI - (yRot + this.hmi$yawOffset) * Mth.DEG_TO_RAD,
            -xRot * Mth.DEG_TO_RAD + this.hmi$pitchOffset * Mth.DEG_TO_RAD,
            this.hmi$rollOffset * Mth.DEG_TO_RAD
        );
        this.hmi$refreshDirectionVectors();
        ci.cancel();
    }

    @Inject(method = "setPosition(DDD)V", at = @At("HEAD"), cancellable = true)
    private void hmi$applyPositionOffsets(double x, double y, double z, CallbackInfo ci) {
        if (!HandMyItemsRuntime.isActive() || FabricLoader.getInstance().isModLoaded("do_a_barrel_roll")) {
            return;
        }
        this.setPosition(new Vec3(x + this.hmi$xOffset, y + this.hmi$yOffset, z + this.hmi$zOffset));
        ci.cancel();
    }

    @Unique
    private void hmi$refreshDirectionVectors() {
        new Vector3f(0.0F, 0.0F, -1.0F).rotate(this.rotation, this.forwards);
        new Vector3f(0.0F, 1.0F, 0.0F).rotate(this.rotation, this.up);
        new Vector3f(-1.0F, 0.0F, 0.0F).rotate(this.rotation, this.left);
        this.matrixPropertiesDirty |= 3;
    }

    @Unique
    private void hmi$clearOffsets() {
        this.hmi$pitchOffset = 0.0F;
        this.hmi$yawOffset = 0.0F;
        this.hmi$rollOffset = 0.0F;
        this.hmi$xOffset = 0.0F;
        this.hmi$yOffset = 0.0F;
        this.hmi$zOffset = 0.0F;
    }
}
