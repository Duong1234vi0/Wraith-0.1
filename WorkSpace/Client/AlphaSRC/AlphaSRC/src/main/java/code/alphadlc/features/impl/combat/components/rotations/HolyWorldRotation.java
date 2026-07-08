/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.rotations;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.components.RotationsSystem;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.math.MathUtils;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class HolyWorldRotation
extends RotationsSystem
implements QuickImports {
    private class_1309 trackedTarget;
    private long lastFrameNanos;
    private float velocityYaw;
    private float velocityPitch;
    private float impactYaw;
    private float impactPitch;
    private float gcdRemainderYaw;
    private float gcdRemainderPitch;
    private float lastYaw;
    private float lastPitch = Float.NaN;
    private float playerDeltaYaw;
    private float playerDeltaPitch;
    private float lastAppliedYawDelta;
    private float lastAppliedPitchDelta;
    private double smoothedTargetX;
    private double smoothedTargetY;
    private double smoothedTargetZ;
    private boolean hasSmoothedTarget;
    private double lastEyeX;
    private double lastEyeY;
    private double lastEyeZ;
    private final float[] gaussianNoise = new float[2];
    private final float[] impactOffset = new float[2];
    private final float[] gcdStep = new float[2];
    private final float[] targetAngles = new float[2];
    private float aimSpeed;
    private float aimSpeedTimer;
    private float aimSpeedInterval;
    private boolean aimSpeedFastPhase;
    private float aimSpeedFastMin;
    private float aimSpeedFastMax;
    private float aimSpeedSlowMin;
    private float aimSpeedSlowMax;
    private float aimSpeedFastValue;
    private float aimSpeedSlowValue;
    private float mouseSensitivityFactor;
    private float mouseSensitivityCap;
    private float mouseSensitivityRamp;
    private float closeDistance;
    private float farDistance;
    private float closeSpeedScale;
    private float currentAimSpeed;
    private float currentAimSpeedOffset;

    @Override
    public void updateRotations(class_1309 target) {
        this.trackedTarget = target;
    }

    public void onRender(float partialTicks) {
        float adjustedDistance;
        if (!this.isActive()) {
            if (this.trackedTarget != null || this.hasSmoothedTarget) {
                this.resetState();
            }
            return;
        }
        if (HolyWorldRotation.mc.field_1724 == null || HolyWorldRotation.mc.field_1687 == null || HolyWorldRotation.mc.field_1724.method_29504()) {
            return;
        }
        long now = System.nanoTime();
        float delta = this.computeDeltaSeconds(now);
        this.lastFrameNanos = now;
        if (delta < 1.0E-4f || delta > 0.1f) {
            delta = 0.016666668f;
        }
        this.updatePlayerDeltas();
        this.lastAppliedYawDelta = 0.0f;
        this.lastAppliedPitchDelta = 0.0f;
        if (this.trackedTarget == null || delta < 1.0E-4f) {
            this.decayMotion(delta);
            this.storeLastAngles();
            return;
        }
        class_243 eyePos = HolyWorldRotation.mc.field_1724.method_33571();
        this.lastEyeX = eyePos.field_1352;
        this.lastEyeY = eyePos.field_1351;
        this.lastEyeZ = eyePos.field_1350;
        this.updateTargetAngles(this.trackedTarget, partialTicks, delta);
        float yawError = this.targetAngles[0];
        float pitchError = this.targetAngles[1];
        if (!Float.isFinite(yawError) || !Float.isFinite(pitchError)) {
            this.decayMotion(delta);
            return;
        }
        float planarDistance = (float)Math.sqrt(yawError * yawError + pitchError * pitchError);
        if (planarDistance < 0.1f) {
            this.decayMotion(delta);
            this.storeLastAngles();
            return;
        }
        float invDistance = (adjustedDistance = (float)Math.sqrt(yawError * yawError + (pitchError += -1.75f) * pitchError)) > 1.0E-4f ? 1.0f / adjustedDistance : 0.0f;
        float dirYaw = yawError * invDistance;
        float dirPitch = pitchError * invDistance;
        float distanceScale = Math.min(adjustedDistance / 10.0f, 1.0f);
        float moveYaw = dirYaw * 600.0f * distanceScale;
        float movePitch = dirPitch * 600.0f * distanceScale * 0.23f;
        float aimFactor = this.computeAimFactor(adjustedDistance, delta);
        float impactStrength = 0.13f * aimFactor;
        if (impactStrength > 1.0E-4f) {
            this.applyImpact(moveYaw, movePitch, impactStrength, delta);
            moveYaw += this.impactOffset[0];
            movePitch += this.impactOffset[1] * 2.0f;
        }
        moveYaw = this.correctOvershoot(moveYaw, this.playerDeltaYaw, delta);
        movePitch = this.correctOvershoot(movePitch, this.playerDeltaPitch, delta);
        this.smoothVelocity(moveYaw, movePitch, delta);
        this.applyClientRotation(delta);
        this.storeLastAngles();
    }

    public void reset() {
        this.trackedTarget = null;
        this.resetState();
        this.randomizeParameters();
    }

    private boolean isActive() {
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.isState() && "HolyWorld".equals(aura.rotationType.get());
    }

    private void resetState() {
        this.trackedTarget = null;
        this.hasSmoothedTarget = false;
        this.gcdRemainderPitch = 0.0f;
        this.gcdRemainderYaw = 0.0f;
        this.impactPitch = 0.0f;
        this.impactYaw = 0.0f;
        this.lastPitch = Float.NaN;
        this.lastYaw = Float.NaN;
        this.playerDeltaPitch = 0.0f;
        this.playerDeltaYaw = 0.0f;
        this.lastAppliedPitchDelta = 0.0f;
        this.lastAppliedYawDelta = 0.0f;
        this.currentAimSpeedOffset = 0.0f;
        this.velocityPitch = 0.0f;
        this.velocityYaw = 0.0f;
        this.lastFrameNanos = 0L;
        this.smoothedTargetX = 0.0;
        this.smoothedTargetY = 0.0;
        this.smoothedTargetZ = 0.0;
    }

    private void randomizeParameters() {
        this.aimSpeedFastMin = 0.1f + MathUtils.random(0.0f, 0.5f);
        this.aimSpeedFastMax = 0.5f + MathUtils.random(0.0f, 0.75f);
        this.aimSpeedSlowMin = 0.5f + MathUtils.random(0.0f, 0.5f);
        this.aimSpeedSlowMax = 3.0f + MathUtils.random(0.0f, 0.25f);
        this.aimSpeedFastValue = 0.5f + MathUtils.random(0.0f, 0.7f);
        this.aimSpeedSlowValue = 0.08f + MathUtils.random(0.0f, 0.12f);
        this.mouseSensitivityFactor = 0.018f + MathUtils.random(0.0f, 0.005f);
        this.mouseSensitivityCap = 0.25f + MathUtils.random(0.0f, 0.05f);
        this.closeDistance = 2.0f + MathUtils.random(0.0f, 0.5f);
        this.farDistance = 6.0f + MathUtils.random(0.0f, 3.0f);
        this.closeSpeedScale = 0.15f + MathUtils.random(0.0f, 0.05f);
        this.aimSpeedInterval = MathUtils.random(this.aimSpeedSlowMin, this.aimSpeedSlowMax);
    }

    private float computeDeltaSeconds(long now) {
        if (this.lastFrameNanos == 0L) {
            return 0.016666668f;
        }
        long elapsed = now - this.lastFrameNanos;
        if (elapsed <= 0L) {
            return 0.016666668f;
        }
        return Math.min((float)elapsed / 1.0E9f, 0.1f);
    }

    private float computeAimFactor(float distance, float delta) {
        this.aimSpeedTimer += delta;
        if (this.aimSpeedTimer >= this.aimSpeedInterval) {
            this.aimSpeedFastPhase = !this.aimSpeedFastPhase;
            this.aimSpeedTimer = 0.0f;
            this.aimSpeedInterval = this.aimSpeedFastPhase ? MathUtils.random(this.aimSpeedFastMin, this.aimSpeedFastMax) : MathUtils.random(this.aimSpeedSlowMin, this.aimSpeedSlowMax);
        }
        float blendSpeed = this.aimSpeedFastPhase ? 7.0f : 2.5f;
        float targetSpeed = this.aimSpeedFastPhase ? this.aimSpeedFastValue : this.aimSpeedSlowValue;
        this.currentAimSpeed += (targetSpeed - this.currentAimSpeed) * (1.0f - (float)Math.exp(-blendSpeed * delta));
        float speed = this.currentAimSpeed;
        float ramp = this.mouseSensitivityFactor * (7.0f / Math.max(30.0f, 1.0f));
        this.currentAimSpeedOffset = class_3532.method_15363((float)(this.currentAimSpeedOffset + delta * ramp), (float)0.0f, (float)this.mouseSensitivityCap);
        speed += this.currentAimSpeedOffset;
        if (distance < this.closeDistance) {
            speed *= this.closeSpeedScale;
        } else if (distance < this.farDistance) {
            float blend = (distance - this.closeDistance) / (this.farDistance - this.closeDistance);
            speed *= this.closeSpeedScale + (1.0f - this.closeSpeedScale) * blend;
        }
        return Math.max(speed, 0.0f);
    }

    private void applyClientRotation(float delta) {
        if (HolyWorldRotation.mc.field_1724 == null) {
            return;
        }
        float yawStep = this.velocityYaw * delta;
        float pitchStep = this.velocityPitch * delta;
        this.applyGcdSteps(yawStep, pitchStep);
        float appliedYaw = this.gcdStep[0];
        float appliedPitch = this.gcdStep[1];
        if (!Float.isFinite(appliedYaw)) {
            appliedYaw = 0.0f;
        }
        if (!Float.isFinite(appliedPitch)) {
            appliedPitch = 0.0f;
        }
        float newYaw = HolyWorldRotation.mc.field_1724.method_36454() + appliedYaw;
        float newPitch = class_3532.method_15363((float)(HolyWorldRotation.mc.field_1724.method_36455() + appliedPitch), (float)-90.0f, (float)90.0f);
        HolyWorldRotation.mc.field_1724.method_36456(newYaw);
        HolyWorldRotation.mc.field_1724.method_36457(newPitch);
        HolyWorldRotation.mc.field_1724.field_6241 = newYaw;
        HolyWorldRotation.mc.field_1724.field_6283 = newYaw;
        this.lastAppliedYawDelta = appliedYaw;
        this.lastAppliedPitchDelta = appliedPitch;
        this.lastYaw = newYaw;
        this.lastPitch = newPitch;
    }

    private void smoothVelocity(float targetYawVelocity, float targetPitchVelocity, float delta) {
        float blend = class_3532.method_15363((float)(1.0f - (float)Math.exp(-20.0f * delta)), (float)0.05f, (float)0.95f);
        this.velocityYaw = HolyWorldRotation.lerp(this.velocityYaw, targetYawVelocity, blend);
        this.velocityPitch = HolyWorldRotation.lerp(this.velocityPitch, targetPitchVelocity, blend * 0.75f);
        float maxYaw = Math.max(Math.abs(this.targetAngles[0]) / delta * 1.5f, 1.0f);
        float maxPitch = Math.max(Math.abs(this.targetAngles[1]) / delta * 1.5f, 1.0f);
        this.velocityYaw = class_3532.method_15363((float)this.velocityYaw, (float)(-maxYaw), (float)maxYaw);
        this.velocityPitch = class_3532.method_15363((float)this.velocityPitch, (float)(-maxPitch), (float)maxPitch);
    }

    private void updateTargetAngles(class_1309 target, float partialTicks, float delta) {
        if (target == null || HolyWorldRotation.mc.field_1724 == null) {
            this.targetAngles[0] = 0.0f;
            this.targetAngles[1] = 0.0f;
            return;
        }
        double x = class_3532.method_16436((double)partialTicks, (double)target.field_6038, (double)target.method_23317());
        double y = class_3532.method_16436((double)partialTicks, (double)target.field_5971, (double)target.method_23318());
        double z = class_3532.method_16436((double)partialTicks, (double)target.field_5989, (double)target.method_23321());
        double aimY = y + (double)(target.method_17682() * 0.5f);
        if (!this.hasSmoothedTarget) {
            this.smoothedTargetX = x;
            this.smoothedTargetY = aimY;
            this.smoothedTargetZ = z;
            this.hasSmoothedTarget = true;
        } else {
            float blend = class_3532.method_15363((float)((float)(1.0 - Math.exp(-25.0 * (double)delta))), (float)0.05f, (float)0.95f);
            this.smoothedTargetX += (x - this.smoothedTargetX) * (double)blend;
            this.smoothedTargetY += (aimY - this.smoothedTargetY) * (double)blend;
            this.smoothedTargetZ += (z - this.smoothedTargetZ) * (double)blend;
        }
        double deltaX = this.smoothedTargetX - this.lastEyeX;
        double deltaY = this.smoothedTargetY - this.lastEyeY;
        double deltaZ = this.smoothedTargetZ - this.lastEyeZ;
        double horizontal = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        if (horizontal < (double)1.0E-4f && Math.abs(deltaY) < (double)1.0E-4f) {
            this.targetAngles[0] = 0.0f;
            this.targetAngles[1] = 0.0f;
            return;
        }
        float targetYaw = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0f;
        float targetPitch = class_3532.method_15363((float)((float)(-Math.toDegrees(Math.atan2(deltaY, horizontal)))), (float)-90.0f, (float)90.0f);
        this.targetAngles[0] = class_3532.method_15393((float)(targetYaw - HolyWorldRotation.mc.field_1724.method_36454()));
        this.targetAngles[1] = targetPitch - HolyWorldRotation.mc.field_1724.method_36455();
    }

    private void applyImpact(float yawVelocity, float pitchVelocity, float strength, float delta) {
        float decay = (float)Math.exp(-delta / 0.04f);
        this.regenerateGaussianNoise();
        this.impactYaw = this.impactYaw * decay + this.gaussianNoise[0] * strength * Math.abs(yawVelocity) * (1.0f - decay);
        this.impactPitch = this.impactPitch * decay + this.gaussianNoise[1] * strength * Math.abs(pitchVelocity) * (1.0f - decay);
        this.impactOffset[0] = this.impactYaw;
        this.impactOffset[1] = this.impactPitch;
    }

    private void decayMotion(float delta) {
        float decay = (float)Math.exp(-20.0f * delta);
        this.velocityYaw *= decay;
        this.velocityPitch *= decay;
        this.gcdRemainderYaw *= decay;
        this.gcdRemainderPitch *= decay;
        this.impactYaw *= decay;
        this.impactPitch *= decay;
    }

    private void applyGcdSteps(float yawDelta, float pitchDelta) {
        float gcd = this.getMouseGcdStep();
        if (gcd < 1.0E-4f) {
            this.gcdStep[0] = yawDelta;
            this.gcdStep[1] = pitchDelta;
            return;
        }
        this.gcdRemainderYaw += yawDelta;
        this.gcdRemainderPitch += pitchDelta;
        float snappedYaw = (float)Math.round(this.gcdRemainderYaw / gcd) * gcd;
        float snappedPitch = (float)Math.round(this.gcdRemainderPitch / gcd) * gcd;
        this.gcdRemainderYaw = class_3532.method_15363((float)(this.gcdRemainderYaw - snappedYaw), (float)(-gcd * 2.0f), (float)(gcd * 2.0f));
        this.gcdRemainderPitch = class_3532.method_15363((float)(this.gcdRemainderPitch - snappedPitch), (float)(-gcd * 2.0f), (float)(gcd * 2.0f));
        this.gcdStep[0] = snappedYaw;
        this.gcdStep[1] = snappedPitch;
    }

    private float correctOvershoot(float velocity, float playerDelta, float delta) {
        float playerVelocity;
        if (Math.abs(velocity) < 1.0E-4f) {
            return velocity;
        }
        float f = playerVelocity = delta > 1.0E-4f ? playerDelta / delta : 0.0f;
        if (Math.abs(playerVelocity) < 0.1f) {
            return velocity;
        }
        if (velocity > 0.0f == playerVelocity > 0.0f) {
            return velocity * 0.95f;
        }
        float reduction = 1.0f - class_3532.method_15363((float)(Math.abs(playerVelocity) / Math.abs(velocity)), (float)0.0f, (float)0.8f);
        return velocity * reduction;
    }

    private void updatePlayerDeltas() {
        if (!Float.isNaN(this.lastYaw) && HolyWorldRotation.mc.field_1724 != null) {
            this.playerDeltaYaw = class_3532.method_15393((float)(HolyWorldRotation.mc.field_1724.method_36454() - this.lastYaw)) - this.lastAppliedYawDelta;
            this.playerDeltaPitch = HolyWorldRotation.mc.field_1724.method_36455() - this.lastPitch - this.lastAppliedPitchDelta;
        } else {
            this.playerDeltaYaw = 0.0f;
            this.playerDeltaPitch = 0.0f;
        }
    }

    private void storeLastAngles() {
        if (HolyWorldRotation.mc.field_1724 != null) {
            this.lastYaw = HolyWorldRotation.mc.field_1724.method_36454();
            this.lastPitch = HolyWorldRotation.mc.field_1724.method_36455();
        }
    }

    private void regenerateGaussianNoise() {
        double u = Math.max(1.0E-10, Math.random());
        double v = Math.random();
        double magnitude = Math.sqrt(-2.0 * Math.log(u));
        this.gaussianNoise[0] = (float)(magnitude * Math.cos(Math.PI * 2 * v));
        this.gaussianNoise[1] = (float)(magnitude * Math.sin(Math.PI * 2 * v));
    }

    private float getMouseGcdStep() {
        double sensitivity = (Double)HolyWorldRotation.mc.field_1690.method_42495().method_41753() * 0.6 + 0.2;
        return (float)(sensitivity * sensitivity * sensitivity * 1.2);
    }

    private static float lerp(float start, float end, float factor) {
        return start + (end - start) * factor;
    }
}
