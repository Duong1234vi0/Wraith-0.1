/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.rotations;

import code.alphadlc.features.impl.combat.components.RotationsSystem;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.rotate.Rotation;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class SlothRotation
extends RotationsSystem
implements QuickImports {
    private class_1309 trackedTarget;
    private float currentYaw;
    private float currentPitch;
    private float velocityYaw;
    private float velocityPitch;
    private double aimPointX;
    private double aimPointY;
    private double aimPointZ;
    private float noiseAngle;
    private final float noiseAmplitude = 1.8f;
    private int hitPhase;
    private int hitTimer;
    private float pitchBeforeHit;
    private long firstSeenTime;
    private int reactionMs;
    private boolean reactionComplete;
    private float lastSentYaw;
    private float lastSentPitch;
    private float smoothYaw;
    private float smoothPitch;

    public void reset() {
        this.trackedTarget = null;
        this.velocityPitch = 0.0f;
        this.velocityYaw = 0.0f;
        this.aimPointZ = 0.0;
        this.aimPointY = 0.0;
        this.aimPointX = 0.0;
        this.noiseAngle = 0.0f;
        this.hitTimer = 0;
        this.hitPhase = 0;
        this.firstSeenTime = 0L;
        this.reactionComplete = false;
        this.reactionMs = 0;
        if (SlothRotation.mc.field_1724 != null) {
            this.currentYaw = SlothRotation.mc.field_1724.method_36454();
            this.currentPitch = SlothRotation.mc.field_1724.method_36455();
            this.lastSentYaw = this.currentYaw;
            this.lastSentPitch = this.currentPitch;
            this.smoothYaw = this.currentYaw;
            this.smoothPitch = this.currentPitch;
        } else {
            this.currentPitch = 0.0f;
            this.currentYaw = 0.0f;
            this.lastSentPitch = 0.0f;
            this.lastSentYaw = 0.0f;
            this.smoothPitch = 0.0f;
            this.smoothYaw = 0.0f;
        }
    }

    private float calcGcd() {
        double s = (Double)SlothRotation.mc.field_1690.method_42495().method_41753() * 0.6 + 0.2;
        return (float)(s * s * s * 1.2);
    }

    private void pickAimPoint(class_1309 entity) {
        class_238 box = entity.method_5829();
        double width = box.field_1320 - box.field_1323;
        double height = box.field_1325 - box.field_1322;
        double depth = box.field_1324 - box.field_1321;
        this.aimPointX = (Math.random() - 0.5) * width * 0.12;
        this.aimPointY = (Math.random() - 0.5) * height * 0.11;
        this.aimPointZ = (Math.random() - 0.5) * depth * 0.12;
    }

    public void onAttack() {
        this.hitPhase = 1;
        this.hitTimer = 0;
        this.pitchBeforeHit = this.currentPitch;
    }

    private float measureAngle(class_1309 entity) {
        if (SlothRotation.mc.field_1724 == null) {
            return 0.0f;
        }
        class_243 eyes = SlothRotation.mc.field_1724.method_33571();
        class_243 mid = entity.method_5829().method_1005();
        class_243 delta = mid.method_1020(eyes);
        float needYaw = (float)Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0f;
        float needPitch = (float)(-Math.toDegrees(Math.atan2(delta.field_1351, delta.method_37267())));
        float deltaYaw = Math.abs(class_3532.method_15393((float)(needYaw - SlothRotation.mc.field_1724.method_36454())));
        float deltaPitch = Math.abs(needPitch - SlothRotation.mc.field_1724.method_36455());
        return deltaYaw + deltaPitch;
    }

    private int computeReaction(float angle) {
        if (angle > 130.0f) {
            return 140 + (int)(Math.random() * 90.0);
        }
        if (angle > 70.0f) {
            return 90 + (int)(Math.random() * 60.0);
        }
        if (angle > 30.0f) {
            return 45 + (int)(Math.random() * 35.0);
        }
        return 12 + (int)(Math.random() * 20.0);
    }

    private boolean isMovingForward() {
        return SlothRotation.mc.field_1724 != null && SlothRotation.mc.field_1690.field_1894.method_1434();
    }

    private boolean isOvertakingTarget(class_1309 target) {
        if (SlothRotation.mc.field_1724 == null || target == null) {
            return false;
        }
        class_243 playerPos = SlothRotation.mc.field_1724.method_19538();
        class_243 targetPos = target.method_19538();
        class_243 playerVel = new class_243(SlothRotation.mc.field_1724.method_23317() - SlothRotation.mc.field_1724.field_6014, SlothRotation.mc.field_1724.method_23318() - SlothRotation.mc.field_1724.field_6036, SlothRotation.mc.field_1724.method_23321() - SlothRotation.mc.field_1724.field_5969);
        class_243 targetVel = new class_243(target.method_23317() - target.field_6014, target.method_23318() - target.field_6036, target.method_23321() - target.field_5969);
        class_243 toTarget = targetPos.method_1020(playerPos).method_1029();
        double playerSpeedToTarget = playerVel.method_1026(toTarget);
        double targetSpeedToPlayer = targetVel.method_1026(toTarget.method_1021(-1.0));
        double relativeSpeed = playerSpeedToTarget + targetSpeedToPlayer;
        double distance = Math.sqrt(Math.pow(playerPos.field_1352 - targetPos.field_1352, 2.0) + Math.pow(playerPos.field_1350 - targetPos.field_1350, 2.0));
        return relativeSpeed > 0.05 && distance < 4.0;
    }

    private float[] generateNoise(float distance) {
        this.noiseAngle += 0.042f + (float)(Math.random() * (double)0.018f);
        float scale = class_3532.method_15363((float)(distance / 4.5f), (float)0.25f, (float)1.0f);
        float amplitude = 1.8f * scale;
        float n1 = (float)Math.sin((double)this.noiseAngle * 0.87) * 0.38f;
        float n2 = (float)Math.sin((double)this.noiseAngle * 1.43 + 0.75) * 0.28f;
        float n3 = (float)Math.cos((double)this.noiseAngle * 1.18 + 0.35) * 0.32f;
        float n4 = (float)Math.cos((double)this.noiseAngle * 1.76 + 1.42) * 0.23f;
        float yawNoise = (n1 + n2) * amplitude;
        float pitchNoise = (n3 + n4) * amplitude * 0.52f;
        return new float[]{yawNoise += ((float)Math.random() - 0.5f) * amplitude * 0.13f, pitchNoise += ((float)Math.random() - 0.5f) * amplitude * 0.09f};
    }

    private float smoothStep(float value) {
        value = class_3532.method_15363((float)value, (float)0.0f, (float)1.0f);
        return value * value * (3.0f - 2.0f * value);
    }

    private float accelCurve(float value) {
        value = class_3532.method_15363((float)value, (float)0.0f, (float)1.0f);
        return 1.0f - (1.0f - value) * (1.0f - value);
    }

    private float springInterp(float current, float target, float velocity, float stiffness, float damping) {
        float diff = target - current;
        float acceleration = diff * stiffness - velocity * damping;
        return velocity + acceleration;
    }

    private float smoothLerp(float from, float to, float alpha) {
        alpha = class_3532.method_15363((float)alpha, (float)0.0f, (float)1.0f);
        float delta = class_3532.method_15393((float)(to - from));
        return from + delta * alpha;
    }

    private float calculateCurrentAngle(float targetYaw, float targetPitch) {
        float deltaYaw = Math.abs(class_3532.method_15393((float)(targetYaw - this.currentYaw)));
        float deltaPitch = Math.abs(targetPitch - this.currentPitch);
        return deltaYaw + deltaPitch;
    }

    @Override
    public void updateRotations(class_1309 target) {
        if (SlothRotation.mc.field_1724 == null || target == null) {
            return;
        }
        boolean playerFlying = SlothRotation.mc.field_1724.method_6128();
        if (this.trackedTarget != target) {
            this.trackedTarget = target;
            this.currentYaw = SlothRotation.mc.field_1724.method_36454();
            this.currentPitch = SlothRotation.mc.field_1724.method_36455();
            this.lastSentYaw = this.currentYaw;
            this.lastSentPitch = this.currentPitch;
            this.smoothYaw = this.currentYaw;
            this.smoothPitch = this.currentPitch;
            this.velocityPitch = 0.0f;
            this.velocityYaw = 0.0f;
            this.pickAimPoint(target);
            this.hitTimer = 0;
            this.hitPhase = 0;
            this.noiseAngle = (float)(Math.random() * Math.PI * 2.0);
            float angleDiff = this.measureAngle(target);
            this.reactionMs = this.computeReaction(angleDiff);
            this.firstSeenTime = System.currentTimeMillis();
            this.reactionComplete = false;
        }
        class_243 eyePos = SlothRotation.mc.field_1724.method_33571();
        class_243 targetCenter = this.getPredictedPoint(target, target.method_5829().method_1005());
        float distance = (float)eyePos.method_1022(targetCenter);
        float gcd = this.calcGcd();
        if (!this.reactionComplete) {
            long elapsed = System.currentTimeMillis() - this.firstSeenTime;
            if (elapsed < (long)this.reactionMs) {
                float jitterYaw = ((float)Math.random() - 0.5f) * 0.22f;
                float jitterPitch = ((float)Math.random() - 0.5f) * 0.14f;
                float outYaw = this.lastSentYaw + jitterYaw;
                float outPitch = class_3532.method_15363((float)(this.lastSentPitch + jitterPitch), (float)-89.0f, (float)89.0f);
                outYaw -= (outYaw - this.lastSentYaw) % gcd;
                outPitch -= (outPitch - this.lastSentPitch) % gcd;
                this.lastSentYaw = outYaw;
                this.lastSentPitch = outPitch;
                RotationStorage.update(new Rotation(outYaw, outPitch), 360.0f, 45.0f, 45.0f, 45.0f, 0, 1, false);
                return;
            }
            this.reactionComplete = true;
        }
        float[] noise = this.generateNoise(distance);
        if (this.hitPhase > 0) {
            ++this.hitTimer;
            int upDuration = 25;
            int downDuration = 20;
            float targetPitchUp = -89.0f;
            if (this.hitPhase == 1) {
                float progress = class_3532.method_15363((float)((float)this.hitTimer / (float)upDuration), (float)0.0f, (float)1.0f);
                float curved = this.accelCurve(progress);
                this.currentPitch = class_3532.method_16439((float)curved, (float)this.pitchBeforeHit, (float)targetPitchUp);
                if (this.hitTimer >= upDuration) {
                    this.hitPhase = 2;
                    this.hitTimer = 0;
                }
            } else if (this.hitPhase == 2) {
                float goal = this.pitchBeforeHit;
                float progress = class_3532.method_15363((float)((float)this.hitTimer / (float)downDuration), (float)0.0f, (float)1.0f);
                float curved = this.smoothStep(progress);
                this.currentPitch = class_3532.method_16439((float)curved, (float)targetPitchUp, (float)goal);
                if (this.hitTimer >= downDuration) {
                    this.hitPhase = 0;
                    this.hitTimer = 0;
                }
            }
            float outYaw = this.currentYaw + noise[0];
            float outPitch = class_3532.method_15363((float)(this.currentPitch + noise[1]), (float)-89.0f, (float)89.0f);
            outYaw -= (outYaw - this.lastSentYaw) % gcd;
            outPitch -= (outPitch - this.lastSentPitch) % gcd;
            this.lastSentYaw = outYaw;
            this.lastSentPitch = outPitch;
            RotationStorage.update(new Rotation(outYaw, outPitch), 360.0f, 45.0f, 45.0f, 45.0f, 0, 1, false);
            return;
        }
        if (Math.random() < 0.015) {
            this.pickAimPoint(target);
        }
        class_243 targetVel = new class_243(target.method_23317() - target.field_6014, target.method_23318() - target.field_6036, target.method_23321() - target.field_5969);
        int predictTicks = this.shouldUseElytraPredict(target) ? 0 : 2;
        class_243 predictedCenter = targetCenter.method_1019(targetVel.method_1021((double)predictTicks));
        class_243 aimPos = predictedCenter.method_1031(this.aimPointX, this.aimPointY, this.aimPointZ);
        class_243 direction = aimPos.method_1020(eyePos);
        float wantYaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(direction.field_1350, direction.field_1352)) - 90.0));
        float wantPitch = (float)(-Math.toDegrees(Math.atan2(direction.field_1351, direction.method_37267())));
        float diffYaw = class_3532.method_15393((float)(wantYaw - this.currentYaw));
        float diffPitch = wantPitch - this.currentPitch;
        float speedMultiplier = 1.0f;
        if (playerFlying) {
            float currentAngle = this.calculateCurrentAngle(wantYaw, wantPitch);
            if (currentAngle > 120.0f) {
                speedMultiplier = 0.18f;
            } else if (currentAngle > 80.0f) {
                t = (currentAngle - 80.0f) / 40.0f;
                speedMultiplier = class_3532.method_16439((float)this.smoothStep(t), (float)0.35f, (float)0.18f);
            } else if (currentAngle > 25.0f) {
                t = (currentAngle - 25.0f) / 55.0f;
                speedMultiplier = class_3532.method_16439((float)this.smoothStep(t), (float)0.65f, (float)0.35f);
            } else {
                speedMultiplier = 0.65f + 0.35f * (1.0f - currentAngle / 25.0f);
            }
        } else {
            boolean movingForward = this.isMovingForward();
            boolean overtaking = this.isOvertakingTarget(target);
            if (movingForward || overtaking) {
                speedMultiplier = 0.5f;
            }
        }
        float stiffness = (0.038f + (float)Math.random() * 0.009f) * speedMultiplier;
        float damping = 0.68f + 0.12f * (1.0f - speedMultiplier);
        float totalDiff = (float)Math.sqrt(diffYaw * diffYaw + diffPitch * diffPitch);
        if (totalDiff > 32.0f) {
            stiffness += 0.018f * speedMultiplier;
        } else if (totalDiff < 4.2f) {
            stiffness *= 0.48f;
        }
        this.velocityYaw = this.springInterp(this.currentYaw, this.currentYaw + diffYaw, this.velocityYaw, stiffness += class_3532.method_15363((float)((distance - 1.6f) / 7.5f), (float)0.0f, (float)0.045f) * speedMultiplier, damping);
        this.velocityPitch = this.springInterp(this.currentPitch, wantPitch, this.velocityPitch, stiffness * 0.87f, damping);
        float maxVelYaw = 7.5f * speedMultiplier;
        float maxVelPitch = 5.8f * speedMultiplier;
        this.velocityYaw = class_3532.method_15363((float)this.velocityYaw, (float)(-maxVelYaw), (float)maxVelYaw);
        this.velocityPitch = class_3532.method_15363((float)this.velocityPitch, (float)(-maxVelPitch), (float)maxVelPitch);
        this.currentYaw += this.velocityYaw;
        this.currentPitch += this.velocityPitch;
        this.currentPitch = class_3532.method_15363((float)this.currentPitch, (float)-89.0f, (float)89.0f);
        float smoothFactor = playerFlying ? 0.3f + speedMultiplier * 0.4f : 0.85f;
        this.smoothYaw = this.smoothLerp(this.smoothYaw, this.currentYaw, smoothFactor);
        this.smoothPitch = this.smoothLerp(this.smoothPitch, this.currentPitch, smoothFactor * 0.95f);
        float outYaw = this.smoothYaw + noise[0];
        float outPitch = class_3532.method_15363((float)(this.smoothPitch + noise[1]), (float)-89.0f, (float)89.0f);
        outYaw -= (outYaw - this.lastSentYaw) % gcd;
        outPitch -= (outPitch - this.lastSentPitch) % gcd;
        this.lastSentYaw = outYaw;
        this.lastSentPitch = outPitch;
        RotationStorage.update(new Rotation(outYaw, outPitch), 360.0f, 45.0f, 45.0f, 45.0f, 0, 1, false);
    }

    public float getLastSentYaw() {
        return this.lastSentYaw;
    }

    public float getLastSentPitch() {
        return this.lastSentPitch;
    }
}
