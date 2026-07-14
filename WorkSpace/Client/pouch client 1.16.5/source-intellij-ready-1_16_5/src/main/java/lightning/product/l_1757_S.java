/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.f_2403_E;
import lightning.product.u_530_F;

public final class l_1757_S {
    private l_1757_S() {
    }

    public static float n_1700_B(float[] w, int newest, int prev, int prev2, float jerkSum, float avgMotion) {
        boolean randomLike;
        if (w == null || w.length != 42) {
            return 0.0f;
        }
        float dy0 = w[newest + 1];
        float dy1 = w[prev + 1];
        float dy2 = w[prev2 + 1];
        boolean yawFlip01 = Math.signum(dy0) != 0.0f && Math.signum(dy1) != 0.0f && Math.signum(dy0) != Math.signum(dy1) && Math.abs(dy0) > 0.012f && Math.abs(dy1) > 0.012f;
        boolean yawFlip12 = Math.signum(dy1) != 0.0f && Math.signum(dy2) != 0.0f && Math.signum(dy1) != Math.signum(dy2) && Math.abs(dy1) > 0.012f && Math.abs(dy2) > 0.012f;
        boolean shakeLike = yawFlip01 || yawFlip12 || jerkSum > 0.24f;
        float a0 = Math.abs(dy0);
        float a1 = Math.abs(dy1);
        float a2 = Math.abs(dy2);
        float spread = Math.max(a0, Math.max(a1, a2)) - Math.min(Math.min(a0, a1), a2);
        boolean bl = randomLike = spread > 0.32f && avgMotion > 0.095f || spread > 0.22f && avgMotion > 0.14f || spread > 0.45f && jerkSum > 0.1f;
        float out = !shakeLike && jerkSum < 0.2f && !randomLike ? u_530_F.n_1700_B(jerkSum * 0.32f + avgMotion * 0.1f, 0.0f, 0.12f) : (shakeLike || randomLike ? u_530_F.n_1700_B(0.42f + jerkSum * 0.9f + avgMotion * 0.42f + spread * 0.07f, 0.52f, 1.0f) : u_530_F.n_1700_B(0.18f + jerkSum * 1.05f + avgMotion * 0.32f + spread * 0.05f, 0.15f, 1.0f));
        if (spread > 1.2f && !randomLike && !shakeLike && avgMotion < 0.11f) {
            out *= u_530_F.n_1700_B(1.0f / (0.9f + (spread - 1.2f) * 0.42f), 0.25f, 0.9f);
        }
        return u_530_F.n_1700_B(out, 0.0f, 1.0f);
    }

    public static float[] n_1700_B(float dy, float dp, float modelDy, float modelDp, float[] w, float strength) {
        if (strength <= 0.004f) {
            return new float[]{dy, dp};
        }
        float refYaw = modelDy;
        float refPitch = modelDp;
        if (w != null && w.length == 42) {
            int newest = 36;
            float tps = Math.max(5.0f, f_2403_E.J_1907_R());
            float wYawStep = w[newest + 1];
            float wPitchStep = w[newest + 5] / tps;
            refYaw = wYawStep * 0.4f + modelDy * 0.6f;
            refPitch = wPitchStep * 0.4f + modelDp * 0.6f;
        }
        float rdx = dy - refYaw;
        float rdy = dp - refPitch;
        float dist = (float)Math.hypot(rdx, rdy);
        float maxDist = u_530_F.n_1700_B(10.0f + 0.2f * (float)Math.hypot(refYaw, refPitch), 8.5f, 30.0f);
        float outY = dy;
        float outP = dp;
        if (dist > maxDist && dist > 1.0E-4f) {
            float s = maxDist / dist;
            outY = refYaw + rdx * s;
            outP = refPitch + rdy * s;
        }
        return new float[]{dy + (outY - dy) * strength, dp + (outP - dp) * strength};
    }
}

