/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ai.onnxruntime.OrtException
 */
package lightning.product;

import ai.onnxruntime.OrtException;
import lightning.product.M_4609_z;
import lightning.product.O_726_g;
import lightning.product.P_3504_Q;
import lightning.product.W_1707_M;
import lightning.product.e_2866_D;

public final class G_3540_E {
    private G_3540_E() {
    }

    public static P_3504_Q n_1700_B(String profileName, float[] window21) {
        if (window21 == null || window21.length != 42) {
            return null;
        }
        M_4609_z model = W_1707_M.n_1700_B(profileName);
        if (model == null) {
            return null;
        }
        try {
            float[] out = model.n_1700_B(window21);
            if (out == null || out.length < 2) {
                return null;
            }
            return G_3540_E.n_1700_B(window21, out[0], out[1]);
        }
        catch (OrtException e) {}
        finally {
            return null;
        }
    }

    private static P_3504_Q n_1700_B(float[] window, float yawDelta, float pitchDelta) {
        boolean snapStyle;
        int vpt = 6;
        int newest = 6 * vpt;
        int prev = 5 * vpt;
        int prev2 = 4 * vpt;
        float dy0 = window[newest + 1];
        float dy1 = window[prev + 1];
        float dy2 = window[prev2 + 1];
        float dYaw0 = Math.abs(dy0);
        float dPitch0 = Math.abs(window[newest + 5] / 20.0f);
        float dYaw1 = Math.abs(dy1);
        float dPitch1 = Math.abs(window[prev + 5] / 20.0f);
        float dYaw2 = Math.abs(dy2);
        float dPitch2 = Math.abs(window[prev2 + 5] / 20.0f);
        float recentMotion = dYaw0 + dPitch0 + dYaw1 + dPitch1 + dYaw2 + dPitch2;
        float peakYaw = Math.max(dYaw0, Math.max(dYaw1, dYaw2));
        float peakPitch = Math.max(dPitch0, Math.max(dPitch1, dPitch2));
        boolean yawFlip01 = Math.signum(dy0) != 0.0f && Math.signum(dy1) != 0.0f && Math.signum(dy0) != Math.signum(dy1) && Math.abs(dy0) > 0.014f && Math.abs(dy1) > 0.014f;
        boolean yawFlip12 = Math.signum(dy1) != 0.0f && Math.signum(dy2) != 0.0f && Math.signum(dy1) != Math.signum(dy2) && Math.abs(dy1) > 0.014f && Math.abs(dy2) > 0.014f;
        float jerk0 = Math.abs(window[newest + 4] / 20.0f);
        float jerk1 = Math.abs(window[prev + 4] / 20.0f);
        float jerk2 = Math.abs(window[prev2 + 4] / 20.0f);
        float oscEnergy = jerk0 + jerk1 + jerk2;
        boolean hasShakePattern = yawFlip01 || yawFlip12 || oscEnergy > 0.22f;
        float outMag = Math.abs(yawDelta) + Math.abs(pitchDelta);
        if (!hasShakePattern && recentMotion < 0.02f && outMag < 0.12f) {
            return new P_3504_Q(0.0f, 0.0f);
        }
        boolean bl = snapStyle = peakYaw > 2.2f || peakPitch > 1.4f || jerk0 + jerk1 + jerk2 > 1.45f;
        if (snapStyle) {
            float minYawImpulse = Math.min(3.6f, peakYaw * 0.55f + 0.22f);
            float minPitchImpulse = Math.min(2.7f, peakPitch * 0.55f + 0.16f);
            if (Math.abs(yawDelta) > 0.0f && Math.abs(yawDelta) < minYawImpulse) {
                yawDelta = Math.copySign(minYawImpulse, yawDelta);
            }
            if (Math.abs(pitchDelta) > 0.0f && Math.abs(pitchDelta) < minPitchImpulse) {
                pitchDelta = Math.copySign(minPitchImpulse, pitchDelta);
            }
        }
        float yawCap = Math.max(8.0f, Math.min(120.0f, peakYaw * 3.2f + oscEnergy * 1.8f + 8.0f));
        float pitchCap = Math.max(6.0f, Math.min(90.0f, peakPitch * 3.0f + oscEnergy * 1.35f + 6.0f));
        yawDelta = Math.max(-yawCap, Math.min(yawCap, yawDelta));
        pitchDelta = Math.max(-pitchCap, Math.min(pitchCap, pitchDelta));
        return new P_3504_Q(yawDelta, pitchDelta);
    }

    public static O_726_g n_1700_B(e_2866_D currentVector, e_2866_D previousVector, e_2866_D targetVector, P_3504_Q velocityDelta, e_2866_D playerDiff, e_2866_D targetDiff, float distance, int hurtTime, int age) {
        return new O_726_g(currentVector, previousVector, targetVector, velocityDelta, playerDiff, targetDiff, distance, hurtTime, age);
    }
}

