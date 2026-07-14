/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_3540_E;
import lightning.product.I_4683_a;
import lightning.product.P_3504_Q;
import lightning.product.e_2866_D;
import lightning.product.l_1757_S;
import lightning.product.u_530_F;

public final class g_4841_c {
    private g_4841_c() {
    }

    public static n_1700_B n_1700_B(float nvRotYaw, float nvRotPitch, e_2866_D stableDiff, String selectedProfile, float[] neuroWindow, long neuroSnapshotSeq, float neuroSpeedScale, float gcd, float pitchMin) {
        float deltaPitch;
        float deltaYaw;
        double diffHxz = Math.hypot(stableDiff.J_1907_R, stableDiff.G_564_y);
        float closeFight = u_530_F.n_1700_B(1.0f - (float)(diffHxz / 0.42), 0.0f, 1.0f);
        float targetYaw = I_4683_a.n_1700_B(stableDiff);
        float targetPitch = I_4683_a.J_1907_R(stableDiff);
        float totalDeltaYaw = u_530_F.v_4262_N(targetYaw - nvRotYaw);
        float totalDeltaPitch = targetPitch - nvRotPitch;
        float turnMag = Math.max(Math.abs(totalDeltaYaw), Math.abs(totalDeltaPitch));
        float bigTurnEase = u_530_F.n_1700_B((turnMag - 22.0f) / 50.0f, 0.0f, 1.0f);
        P_3504_Q mlDelta = G_3540_E.n_1700_B(selectedProfile, neuroWindow);
        float modelRefYaw = 0.0f;
        float modelRefPitch = 0.0f;
        boolean hasNeuroSampleRef = false;
        if (!(mlDelta == null || Float.isNaN(mlDelta.t_148_a) || Float.isNaN(mlDelta.s_956_w) || Float.isInfinite(mlDelta.t_148_a) || Float.isInfinite(mlDelta.s_956_w))) {
            hasNeuroSampleRef = true;
            modelRefYaw = mlDelta.t_148_a;
            modelRefPitch = mlDelta.s_956_w;
            deltaYaw = mlDelta.t_148_a;
            deltaPitch = mlDelta.s_956_w;
            if (neuroWindow != null && neuroWindow.length == 42) {
                boolean jitterGate;
                int vpt = 6;
                int newest = 6 * vpt;
                int prev = 5 * vpt;
                int prev2 = 4 * vpt;
                float dYaw0 = Math.abs(neuroWindow[newest + 1]);
                float dYaw1 = Math.abs(neuroWindow[prev + 1]);
                float dYaw2 = Math.abs(neuroWindow[prev2 + 1]);
                float dPitch0 = Math.abs(neuroWindow[newest + 5] / 20.0f);
                float dPitch1 = Math.abs(neuroWindow[prev + 5] / 20.0f);
                float dPitch2 = Math.abs(neuroWindow[prev2 + 5] / 20.0f);
                float jerk = Math.abs(neuroWindow[newest + 4] / 20.0f) + Math.abs(neuroWindow[prev + 4] / 20.0f) + Math.abs(neuroWindow[prev2 + 4] / 20.0f);
                float peakYaw = Math.max(dYaw0, Math.max(dYaw1, dYaw2));
                float peakPitch = Math.max(dPitch0, Math.max(dPitch1, dPitch2));
                float avgMotion = (dYaw0 + dYaw1 + dYaw2 + dPitch0 + dPitch1 + dPitch2) / 3.0f;
                float yawSampleSpeed = (dYaw0 + dYaw1 + dYaw2) / 3.0f;
                float pitchSampleSpeed = (dPitch0 + dPitch1 + dPitch2) / 3.0f;
                float jitterScale = (1.0f - bigTurnEase) * (1.0f - closeFight * 0.98f);
                float sampleJitterAllow = l_1757_S.n_1700_B(neuroWindow, newest, prev, prev2, jerk, avgMotion);
                boolean bl = jitterGate = sampleJitterAllow > 0.035f && (jerk > 0.11f || avgMotion > 0.105f || sampleJitterAllow > 0.48f);
                if (jitterScale > 0.05f && jitterGate) {
                    float baseAmp = u_530_F.n_1700_B(jerk * 0.055f + avgMotion * 0.045f, 0.04f, 0.78f);
                    float jitterAmp = baseAmp * jitterScale * sampleJitterAllow;
                    float t = neuroSnapshotSeq;
                    deltaYaw += (float)Math.sin(t * 0.91f + 0.3f) * jitterAmp;
                    deltaPitch += (float)Math.cos(t * 1.07f + 0.15f) * (jitterAmp * 0.7f);
                }
                float yawCap = Math.max(6.0f, Math.min(120.0f, peakYaw * 3.4f + jerk * 1.8f + 5.0f));
                float pitchCap = Math.max(4.5f, Math.min(70.0f, peakPitch * 3.2f + jerk * 1.3f + 4.0f));
                yawCap = u_530_F.v_4262_N(bigTurnEase, yawCap, Math.max(yawCap, 55.0f));
                pitchCap = u_530_F.v_4262_N(bigTurnEase, pitchCap, Math.max(pitchCap, 35.0f));
                float impulseGate = 1.0f - bigTurnEase;
                if (impulseGate > 0.08f && Math.abs(totalDeltaYaw) < 35.0f) {
                    float minYawImpulse = u_530_F.n_1700_B(yawSampleSpeed * 0.35f, 0.0f, 2.8f) * impulseGate;
                    float minPitchImpulse = u_530_F.n_1700_B(pitchSampleSpeed * 0.35f, 0.0f, 2.1f) * impulseGate;
                    if (Math.abs(deltaYaw) > 0.0f && Math.abs(deltaYaw) < minYawImpulse) {
                        deltaYaw = Math.copySign(minYawImpulse, deltaYaw);
                    }
                    if (Math.abs(deltaPitch) > 0.0f && Math.abs(deltaPitch) < minPitchImpulse) {
                        deltaPitch = Math.copySign(minPitchImpulse, deltaPitch);
                    }
                }
                deltaYaw = u_530_F.n_1700_B(deltaYaw, -yawCap, yawCap);
                deltaPitch = u_530_F.n_1700_B(deltaPitch, -pitchCap, pitchCap);
            }
            float yawErrAbs = Math.abs(totalDeltaYaw);
            float pitchErrAbs = Math.abs(totalDeltaPitch);
            float absError = yawErrAbs + pitchErrAbs;
            float baseBlend = u_530_F.n_1700_B(0.1f + Math.max(0.0f, (absError - 14.0f) / 70.0f), 0.1f, 0.58f);
            float blendYaw = u_530_F.n_1700_B(baseBlend + yawErrAbs / 220.0f, 0.12f, 0.68f);
            float blendPitch = u_530_F.n_1700_B(baseBlend + pitchErrAbs / 165.0f, 0.12f, 0.7f);
            blendYaw = u_530_F.v_4262_N(bigTurnEase, blendYaw, Math.min(0.92f, blendYaw + 0.24f));
            blendPitch = u_530_F.v_4262_N(bigTurnEase, blendPitch, Math.min(0.9f, blendPitch + 0.18f));
            blendYaw = u_530_F.v_4262_N(closeFight, blendYaw, Math.min(0.94f, blendYaw + 0.28f));
            blendPitch = u_530_F.v_4262_N(closeFight, blendPitch, Math.min(0.92f, blendPitch + 0.22f));
            deltaYaw = u_530_F.v_4262_N(blendYaw, deltaYaw, totalDeltaYaw);
            deltaPitch = u_530_F.v_4262_N(blendPitch, deltaPitch, totalDeltaPitch);
            if (closeFight < 0.35f && yawErrAbs < 7.5f && pitchErrAbs < 6.5f) {
                deltaYaw += totalDeltaYaw * 0.22f;
                deltaPitch += totalDeltaPitch * 0.22f;
            }
        } else {
            deltaYaw = totalDeltaYaw * 0.35f;
            deltaPitch = totalDeltaPitch * 0.35f;
            deltaYaw = u_530_F.n_1700_B(deltaYaw, -30.0f, 30.0f);
            deltaPitch = u_530_F.n_1700_B(deltaPitch, -20.0f, 20.0f);
        }
        if (hasNeuroSampleRef) {
            float clampStrength = (1.0f - bigTurnEase) * (1.0f - closeFight * 0.85f);
            if (neuroWindow != null && neuroWindow.length == 42) {
                int vpt = 6;
                int newest = 6 * vpt;
                int prev = 5 * vpt;
                int prev2 = 4 * vpt;
                float jk = Math.abs(neuroWindow[newest + 4] / 20.0f) + Math.abs(neuroWindow[prev + 4] / 20.0f) + Math.abs(neuroWindow[prev2 + 4] / 20.0f);
                float d0 = Math.abs(neuroWindow[newest + 1]);
                float d1 = Math.abs(neuroWindow[prev + 1]);
                float d2 = Math.abs(neuroWindow[prev2 + 1]);
                float p0 = Math.abs(neuroWindow[newest + 5] / 20.0f);
                float p1 = Math.abs(neuroWindow[prev + 5] / 20.0f);
                float p2 = Math.abs(neuroWindow[prev2 + 5] / 20.0f);
                float avgM = (d0 + d1 + d2 + p0 + p1 + p2) / 3.0f;
                float allowRand = l_1757_S.n_1700_B(neuroWindow, newest, prev, prev2, jk, avgM);
                float relaxRand = u_530_F.n_1700_B((allowRand - 0.38f) / 0.55f, 0.0f, 1.0f);
                clampStrength *= 1.0f - relaxRand * 0.62f;
            }
            float[] capped = l_1757_S.n_1700_B(deltaYaw, deltaPitch, modelRefYaw, modelRefPitch, neuroWindow, clampStrength);
            deltaYaw = capped[0];
            deltaPitch = capped[1];
        }
        deltaYaw *= neuroSpeedScale;
        deltaPitch *= neuroSpeedScale;
        float yawStepCap = u_530_F.v_4262_N(bigTurnEase, 40.0f, 55.0f);
        float pitchStepCap = u_530_F.v_4262_N(bigTurnEase, 30.0f, 40.0f);
        deltaYaw = u_530_F.n_1700_B(deltaYaw, -yawStepCap, yawStepCap);
        deltaPitch = u_530_F.n_1700_B(deltaPitch, -pitchStepCap, pitchStepCap);
        float newYaw = nvRotYaw + deltaYaw;
        float newPitch = u_530_F.n_1700_B(nvRotPitch + deltaPitch, pitchMin, 90.0f);
        newYaw -= (newYaw - nvRotYaw) % gcd;
        newPitch -= (newPitch - nvRotPitch) % gcd;
        newPitch = u_530_F.n_1700_B(newPitch, pitchMin, 89.0f);
        return new n_1700_B(newYaw, newPitch);
    }

    public static final class n_1700_B {
        public final float n_1700_B;
        public final float J_1907_R;

        public n_1700_B(float newYaw, float newPitch) {
            this.n_1700_B = newYaw;
            this.J_1907_R = newPitch;
        }
    }
}

