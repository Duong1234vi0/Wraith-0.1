/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.rotations;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.components.RotationsSystem;
import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.features.impl.combat.components.interpolation.AimPoint;
import code.alphadlc.features.impl.combat.components.interpolation.HitChance;
import code.alphadlc.features.impl.combat.components.interpolation.HumanAim;
import code.alphadlc.features.impl.combat.components.interpolation.Interpolator;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.rotate.Rotation;
import code.alphadlc.utils.features.rotate.RotationUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class LegitRotation
extends RotationsSystem
implements QuickImports {
    private static final float FOLLOW_YAW = 0.15f;
    private static final float FOLLOW_PITCH = 0.08f;
    private static final float MAX_ACCEL_YAW = 1.8f;
    private static final float MAX_ACCEL_PITCH = 0.7f;
    private static final float AIM_SMOOTH_YAW = 0.5f;
    private static final float AIM_SMOOTH_PITCH = 0.06f;
    private long lastUpdateMs = 0L;
    private int lastTargetId = -1;
    private float velYaw = 0.0f;
    private float velPitch = 0.0f;
    private float commitSmooth = 0.0f;
    private float aimYaw = 0.0f;
    private float aimPitch = 0.0f;
    private boolean haveAim = false;

    public void reset() {
        this.lastUpdateMs = 0L;
        this.lastTargetId = -1;
        this.velYaw = 0.0f;
        this.velPitch = 0.0f;
        this.commitSmooth = 0.0f;
        this.aimYaw = 0.0f;
        this.aimPitch = 0.0f;
        this.haveAim = false;
        HumanAim.reset();
        AimPoint.reset();
    }

    @Override
    public void updateRotations(class_1309 target) {
        if (target == null || LegitRotation.mc.field_1724 == null) {
            return;
        }
        long now = System.currentTimeMillis();
        float dt = this.lastUpdateMs == 0L ? 1.0f : class_3532.method_15363((float)((float)(now - this.lastUpdateMs) / 50.0f), (float)0.5f, (float)2.0f);
        this.lastUpdateMs = now;
        if (target.method_5628() != this.lastTargetId) {
            this.lastTargetId = target.method_5628();
            this.velYaw = 0.0f;
            this.velPitch = 0.0f;
            this.haveAim = false;
        }
        class_243 aimPoint = this.getPredictedPoint(target, AimPoint.get(target));
        class_241 desired = RotationUtils.getRotations(aimPoint);
        if (!this.haveAim) {
            this.aimYaw = desired.field_1343;
            this.aimPitch = desired.field_1342;
            this.haveAim = true;
        } else {
            this.aimYaw += class_3532.method_15393((float)(desired.field_1343 - this.aimYaw)) * class_3532.method_15363((float)(0.5f * dt), (float)0.0f, (float)1.0f);
            this.aimPitch += class_3532.method_15393((float)(desired.field_1342 - this.aimPitch)) * class_3532.method_15363((float)(0.06f * dt), (float)0.0f, (float)1.0f);
        }
        this.aimYaw = class_3532.method_15393((float)this.aimYaw);
        this.aimPitch = class_3532.method_15363((float)this.aimPitch, (float)-89.0f, (float)89.0f);
        float currentYaw = LegitRotation.mc.field_1724.method_36454();
        float currentPitch = LegitRotation.mc.field_1724.method_36455();
        float baseDiffYaw = class_3532.method_15393((float)(this.aimYaw - currentYaw));
        float baseDiffPitch = class_3532.method_15393((float)(this.aimPitch - currentPitch));
        float baseDist = (float)Math.hypot(baseDiffYaw, baseDiffPitch);
        boolean commit = AttackAura.getInstance() != null && AttackAura.getInstance().isLegitAimCommit();
        this.commitSmooth += ((commit ? 1.0f : 0.0f) - this.commitSmooth) * class_3532.method_15363((float)(0.15f * dt), (float)0.0f, (float)1.0f);
        float hitChance = HitChance.get(target);
        float tremorScale = (1.0f - 0.5f * this.commitSmooth) * (1.0f - 0.4f * hitChance);
        float jitterYaw = HumanAim.noiseYaw() * tremorScale;
        float jitterPitch = HumanAim.noisePitch() * tremorScale * 0.25f;
        float diffYaw = class_3532.method_15393((float)(this.aimYaw + jitterYaw - currentYaw));
        float diffPitch = class_3532.method_15393((float)(this.aimPitch + jitterPitch - currentPitch));
        float reaction = HumanAim.reactionFactor((class_1297)target);
        float fov = HumanAim.fovFactor(baseDiffYaw, baseDiffPitch);
        float speedJitter = HumanAim.speedJitter();
        float gate = reaction * fov * speedJitter;
        float speedFrac = HumanAim.bezierEase((float)Interpolator.EASE_BOTH.interpolate(0.0, 1.0, (double)class_3532.method_15363((float)(baseDist / 60.0f), (float)0.0f, (float)1.0f)));
        float maxSpeedYaw = (2.0f + speedFrac * 10.0f) * gate;
        float maxSpeedPitch = (0.6f + speedFrac * 2.5f) * gate;
        float stepYaw = this.follow(diffYaw, true, 0.15f, maxSpeedYaw, 1.8f, dt);
        float stepPitch = this.follow(diffPitch, false, 0.08f, maxSpeedPitch, 0.7f, dt);
        float newYaw = currentYaw + GCDUtil.getFixedRotation(stepYaw);
        float newPitch = class_3532.method_15363((float)(currentPitch + GCDUtil.getFixedRotation(stepPitch)), (float)-89.0f, (float)89.0f);
        AttackAura.otvodkaYaw = 0.0f;
        AttackAura.otvodkaPitch = 0.0f;
        RotationStorage.update(new Rotation(newYaw, newPitch), 360.0f, 360.0f, 40.0f, 35.0f, 1, 1, false);
    }

    private float follow(float error, boolean yawAxis, float followGain, float maxSpeed, float maxAccel, float dt) {
        float vel = yawAxis ? this.velYaw : this.velPitch;
        float desiredVel = class_3532.method_15363((float)(error * followGain), (float)(-maxSpeed), (float)maxSpeed);
        float dv = class_3532.method_15363((float)(desiredVel - vel), (float)(-maxAccel * dt), (float)(maxAccel * dt));
        vel += dv;
        if (yawAxis) {
            this.velYaw = vel;
        } else {
            this.velPitch = vel;
        }
        return vel * dt;
    }
}
