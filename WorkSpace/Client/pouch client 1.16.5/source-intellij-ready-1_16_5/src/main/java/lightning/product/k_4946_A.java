/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.joml.Vector2f
 */
package lightning.product;

import java.security.SecureRandom;
import lightning.product.A_4514_U;
import lightning.product.T_1170_t;
import lightning.product.e_2866_D;
import lightning.product.h_3066_J;
import lightning.product.m_38_G;
import lightning.product.q_4361_M;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lombok.Generated;
import org.joml.Vector2f;

public class k_4946_A
implements A_4514_U,
m_38_G {
    private boolean J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private static final float P_1922_E = 44.6f;
    private static final float u_1723_Y = T_1170_t.n_1700_B(48.29577951308232);
    private SecureRandom v_4262_N = new SecureRandom();
    private e_2866_D w_1484_f = e_2866_D.n_1700_B;
    private e_2866_D t_148_a = e_2866_D.n_1700_B;
    private float s_956_w;
    private long u_2550_I = 0L;
    private float M_588_G;
    private float P_4830_p;
    private float h_1847_R;
    private float Q_4569_t;
    private double M_182_A;
    private double t_1786_h;

    private float n_1700_B(float min, float max) {
        float rand1 = this.v_4262_N.nextFloat();
        float rand2 = this.v_4262_N.nextFloat();
        return min + (max - min) * ((rand1 + rand2) / 2.0f);
    }

    private float J_1907_R(float min, float max) {
        double randA = this.v_4262_N.nextDouble();
        double randB = this.v_4262_N.nextDouble();
        double randC = this.v_4262_N.nextGaussian() * (double)0.02f;
        double smoothFactor = Math.pow(randA, 1.1 + this.v_4262_N.nextDouble() * 1.0);
        double mixFactor = (randB * 0.87 + 0.1) * (Math.log1p(randA * 3.0) * 0.74 + 0.42);
        return (float)((double)min + (double)(max - min) * smoothFactor * mixFactor + randC);
    }

    private float R_4764_Y(float min, float max) {
        return min + (max - min) * (float)Math.random();
    }

    private float G_564_y(float min, float max) {
        return min + (max - min) * this.v_4262_N.nextFloat();
    }

    private float P_1922_E(float min, float max) {
        switch (this.v_4262_N.nextInt(1)) {
            case 0: {
                return this.n_1700_B(min, max);
            }
            case 1: {
                return this.J_1907_R(min, max);
            }
            case 2: {
                return this.R_4764_Y(min, max);
            }
        }
        return this.G_564_y(min, max);
    }

    private long n_1700_B(long min, long max) {
        return this.v_4262_N.nextLong(min, max);
    }

    private void u_1723_Y() {
        this.v_4262_N = new SecureRandom();
    }

    private float n_1700_B(float pDelta, float pStart, float pEnd) {
        return u_530_F.v_4262_N(pDelta, pStart, pEnd);
    }

    private float n_1700_B(float pitch) {
        float min = this.R_4764_Y ? 44.6f : -64.9f;
        return u_530_F.n_1700_B(pitch, min, 64.9f);
    }

    @Override
    public void n_1700_B(Vector2f vector2f, Vector2f prevRotation, Vector2f setter) {
        float distance = Math.abs(u_530_F.v_4262_N(vector2f.x - prevRotation.x));
        float baseSpeed = this.P_1922_E(0.8f, 0.96f);
        float dynamicSpeed = baseSpeed * (1.0f + Math.min(distance, 100.0f) / 160.0f);
        float yawSpeed = this.P_1922_E(dynamicSpeed * 0.525f, dynamicSpeed * 0.71f);
        float pitchSpeed = this.P_1922_E(dynamicSpeed * 0.25f, dynamicSpeed * 0.45f);
        float targetYaw = u_530_F.v_4262_N(vector2f.x - prevRotation.x) + prevRotation.x;
        if (yawSpeed >= this.v_4262_N.nextFloat(0.88f, 0.889f) && (yawSpeed *= 0.75f) >= 1.0f) {
            yawSpeed = u_530_F.n_1700_B(yawSpeed, 0.0f, this.P_1922_E(0.65f, 0.96f));
        }
        float yaw = this.n_1700_B(yawSpeed, prevRotation.x, targetYaw);
        float pitch = this.n_1700_B(this.n_1700_B(pitchSpeed, prevRotation.y, vector2f.y));
        prevRotation.x = h_3066_J.n_1700_B(yaw);
        prevRotation.y = h_3066_J.n_1700_B(pitch);
    }

    public void n_1700_B() {
        this.u_1723_Y();
        this.w_1484_f = e_2866_D.n_1700_B;
        this.t_148_a = e_2866_D.n_1700_B;
        this.s_956_w = this.P_1922_E(1.25f, 1.85f);
        this.M_588_G = this.P_1922_E(0.48f, 0.55f);
        this.P_4830_p = this.P_1922_E(0.38f, 0.55f);
        this.h_1847_R = 0.0f;
        this.Q_4569_t = 0.0f;
        this.J_1907_R = false;
        this.M_182_A = this.n_1700_B(40L, 100L);
        this.t_1786_h = this.n_1700_B(60L, 100L);
    }

    public static float n_1700_B(double d) {
        return (float)(d * (double)u_1723_Y);
    }

    private Vector2f n_1700_B(e_2866_D aYc2) {
        return new Vector2f(u_530_F.v_4262_N(k_4946_A.n_1700_B(u_530_F.G_564_y(aYc2.G_564_y, aYc2.J_1907_R)) - 90.0f), -k_4946_A.n_1700_B(u_530_F.G_564_y(aYc2.R_4764_Y, Math.hypot(aYc2.J_1907_R, aYc2.G_564_y))));
    }

    private Vector2f n_1700_B(Vector2f aYb2, e_2866_D aYc2) {
        Vector2f aYb3 = this.n_1700_B(aYc2);
        return new Vector2f(u_530_F.v_4262_N(aYb3.x - aYb2.x), aYb3.y - aYb2.y);
    }

    private boolean n_1700_B(r_4811_B target, float yaw, float pitch, float attackDistance) {
        return q_4361_M.n_1700_B(target, yaw, pitch, attackDistance) == target;
    }

    private float n_1700_B(r_4811_B target, float missYaw, float hitYaw, float pitch, float attackDistance) {
        float left = missYaw;
        float right = hitYaw;
        for (int i = 0; i < 8; ++i) {
            float mid = left + (right - left) * 0.62f;
            if (this.n_1700_B(target, mid, pitch, attackDistance)) {
                right = mid;
                continue;
            }
            left = mid;
        }
        return right;
    }

    private float J_1907_R(r_4811_B target, float yaw, float missPitch, float hitPitch, float attackDistance) {
        float low = missPitch;
        float high = hitPitch;
        for (int i = 0; i < 8; ++i) {
            float mid = low + (high - low) * 0.5f;
            if (this.n_1700_B(target, yaw, mid, attackDistance)) {
                high = mid;
                continue;
            }
            low = mid;
        }
        return this.n_1700_B(high);
    }

    private float R_4764_Y(r_4811_B target, float searchYaw, float fallbackYaw, float pitch, float attackDistance) {
        if (this.n_1700_B(target, searchYaw, pitch, attackDistance)) {
            return searchYaw;
        }
        float searchStep = 1.0f;
        float maxSearch = 180.0f;
        float unwrappedFallbackYaw = searchYaw + u_530_F.v_4262_N(fallbackYaw - searchYaw);
        for (float diff = 1.0f; diff <= 180.0f; diff += 1.0f) {
            float positiveYaw = searchYaw + diff;
            float negativeYaw = searchYaw - diff;
            boolean positiveHit = this.n_1700_B(target, positiveYaw, pitch, attackDistance);
            boolean negativeHit = this.n_1700_B(target, negativeYaw, pitch, attackDistance);
            if (!positiveHit && !negativeHit) continue;
            float chosenYaw = positiveHit && negativeHit ? (Math.abs(positiveYaw - unwrappedFallbackYaw) <= Math.abs(negativeYaw - unwrappedFallbackYaw) ? positiveYaw : negativeYaw) : (positiveHit ? positiveYaw : negativeYaw);
            float refinedYaw = this.n_1700_B(target, searchYaw, chosenYaw, pitch, attackDistance);
            return refinedYaw;
        }
        return fallbackYaw;
    }

    private float G_564_y(r_4811_B target, float yaw, float searchPitch, float fallbackPitch, float attackDistance) {
        if (this.n_1700_B(target, yaw, searchPitch, attackDistance)) {
            return this.n_1700_B(searchPitch);
        }
        float searchStep = 0.5f;
        float maxSearch = 90.0f;
        for (float diff = 0.5f; diff <= 90.0f; diff += 0.5f) {
            float positivePitch = this.n_1700_B(searchPitch + diff);
            float negativePitch = this.n_1700_B(searchPitch - diff);
            boolean positiveHit = this.n_1700_B(target, yaw, positivePitch, attackDistance);
            boolean negativeHit = this.n_1700_B(target, yaw, negativePitch, attackDistance);
            if (!positiveHit && !negativeHit) continue;
            float chosenPitch = positiveHit && negativeHit ? (Math.abs(positivePitch - fallbackPitch) <= Math.abs(negativePitch - fallbackPitch) ? positivePitch : negativePitch) : (positiveHit ? positivePitch : negativePitch);
            return this.J_1907_R(target, yaw, searchPitch, chosenPitch, attackDistance);
        }
        return this.n_1700_B(fallbackPitch);
    }

    private Vector2f n_1700_B(r_4811_B target, float yaw, float pitch, float yawJitter, float pitchJitter, float attackDistance) {
        float multiplier = this.P_1922_E(0.25f, 1.0f);
        for (int i = 0; i < this.v_4262_N.nextInt(3, 7); ++i) {
            float jitteredYaw = yaw + yawJitter * multiplier;
            float jitteredPitch = this.n_1700_B(pitch + pitchJitter * multiplier);
            if (this.n_1700_B(target, jitteredYaw, jitteredPitch, attackDistance)) {
                return new Vector2f(jitteredYaw, jitteredPitch);
            }
            multiplier *= this.P_1922_E(0.15f, 0.75f);
        }
        return new Vector2f(yaw, pitch);
    }

    public void n_1700_B(r_4811_B ajE2, Vector2f aYb2, boolean snap, float attackDistance) {
        float finalYaw;
        float pitch;
        boolean isPitchNotRaycasted = q_4361_M.n_1700_B(ajE2, T_1170_t.n_1700_B((r_4811_B)ajE2).t_148_a, aYb2.y + this.Q_4569_t, attackDistance) != ajE2;
        boolean isYawNotRaycasted = q_4361_M.n_1700_B(ajE2, aYb2.x, T_1170_t.n_1700_B((r_4811_B)ajE2).s_956_w, attackDistance) != ajE2;
        boolean isInHitbox = ajE2.i_601_W().intersects(k_4946_A.n_1700_B.Y_259_p.i_601_W());
        e_2866_D rotateVector = this.n_1700_B(ajE2, snap);
        Vector2f rot = this.n_1700_B(aYb2, rotateVector);
        float distanceToTarget = (float)rotateVector.u_1723_Y();
        float normalizedDistance = u_530_F.n_1700_B(distanceToTarget / Math.max(attackDistance, 1.0f), 0.0f, 1.0f);
        float yawRandomScale = u_530_F.v_4262_N(normalizedDistance, 1.15f, 0.45f);
        float yaw = (float)Math.ceil(snap && (isYawNotRaycasted || this.v_4262_N.nextBoolean()) ? (double)this.n_1700_B(4L, 10L) : (double)((float)this.n_1700_B(25L, 40L) * yawRandomScale) * Math.cos((double)System.currentTimeMillis() / this.M_182_A));
        float f = pitch = this.R_4764_Y ? 0.0f : (float)Math.ceil((double)this.n_1700_B(5L, 15L) * Math.sin((double)System.currentTimeMillis() / this.t_1786_h));
        if (!this.R_4764_Y && k_4946_A.n_1700_B.Y_259_p.RealmsWorldResetDto % this.v_4262_N.nextInt(25, 125) == 0) {
            this.t_1786_h = this.n_1700_B(75L, 140L);
        }
        if (k_4946_A.n_1700_B.Y_259_p.RealmsWorldResetDto % this.v_4262_N.nextInt(25, 105) == 0) {
            this.M_182_A = this.n_1700_B(75L, 140L);
        }
        if (snap) {
            yaw = this.v_4262_N.nextFloat();
            if (!this.R_4764_Y) {
                pitch = this.v_4262_N.nextFloat();
            }
        } else {
            if (this.v_4262_N.nextBoolean()) {
                yaw += this.v_4262_N.nextFloat();
            }
            if (!this.R_4764_Y && this.v_4262_N.nextBoolean()) {
                pitch += this.v_4262_N.nextFloat();
            }
        }
        float nextPitchLerping = this.R_4764_Y ? 0.0f : (snap && isPitchNotRaycasted ? this.n_1700_B(this.P_1922_E(0.55f, 1.29f), this.Q_4569_t, pitch) : this.n_1700_B(this.P_1922_E(0.17f, 2.642f), this.Q_4569_t, pitch));
        float nextYawLerping = snap && isYawNotRaycasted ? this.n_1700_B(this.P_1922_E(0.25f, 1.49f), this.h_1847_R, yaw) : this.n_1700_B(this.P_1922_E(0.27f, 1.542f), this.h_1847_R, yaw);
        if (!(this.G_564_y || this.R_4764_Y || this.J_1907_R || k_4946_A.n_1700_B.Y_259_p.RealmsWorldResetDto % this.v_4262_N.nextInt(550, 760) != 0)) {
            this.J_1907_R = true;
        }
        float targetYaw = (float)Math.toDegrees(Math.atan2(rotateVector.G_564_y, rotateVector.J_1907_R)) - 90.0f;
        float fallbackPitch = this.n_1700_B(aYb2.y + rot.y);
        float searchPitch = this.R_4764_Y ? fallbackPitch : this.n_1700_B(aYb2.y + (isPitchNotRaycasted ? rot.y : 0.0f) + nextPitchLerping);
        float searchYaw = aYb2.x + nextYawLerping;
        if (snap && !this.J_1907_R) {
            float fixedPitch;
            float resolvedYaw = this.R_4764_Y(ajE2, searchYaw, targetYaw, searchPitch, attackDistance);
            float resolvedPitch = this.G_564_y(ajE2, resolvedYaw, searchPitch, fallbackPitch, attackDistance);
            Vector2f instantRotation = this.n_1700_B(ajE2, resolvedYaw, resolvedPitch, nextYawLerping, this.R_4764_Y ? 0.0f : nextPitchLerping, attackDistance);
            this.Q_4569_t = this.R_4764_Y ? 0.0f : nextPitchLerping;
            this.h_1847_R = nextYawLerping;
            float fixedYaw = h_3066_J.J_1907_R(instantRotation.x);
            if (this.n_1700_B(ajE2, fixedYaw, fixedPitch = h_3066_J.J_1907_R(this.n_1700_B(instantRotation.y)), attackDistance)) {
                aYb2.x = fixedYaw;
                aYb2.y = fixedPitch;
            } else {
                aYb2.x = instantRotation.x;
                aYb2.y = this.n_1700_B(instantRotation.y);
            }
            return;
        }
        if (this.J_1907_R && !this.G_564_y) {
            nextYawLerping *= this.P_1922_E(1.55f, 4.35f);
            if (!this.R_4764_Y) {
                nextPitchLerping *= this.P_1922_E(1.35f, 2.1f);
            }
        } else if (isYawNotRaycasted) {
            targetYaw = this.R_4764_Y(ajE2, searchYaw, targetYaw, searchPitch, attackDistance);
        } else if (rotateVector.J_1907_R * rotateVector.J_1907_R + rotateVector.G_564_y * rotateVector.G_564_y < 0.1225) {
            targetYaw = aYb2.x;
        }
        if (Math.abs(u_530_F.v_4262_N((finalYaw = aYb2.x + u_530_F.v_4262_N(targetYaw - aYb2.x)) - aYb2.x)) > 145.0f) {
            finalYaw = aYb2.x + (u_530_F.v_4262_N(finalYaw - aYb2.x) > 0.0f ? 145.0f : -145.0f);
        }
        float shortestPitchPath = rot.y;
        float finalPitch = this.R_4764_Y ? fallbackPitch : this.n_1700_B(aYb2.y + (isPitchNotRaycasted ? shortestPitchPath : 0.0f));
        this.Q_4569_t = this.R_4764_Y ? 0.0f : nextPitchLerping;
        this.h_1847_R = nextYawLerping;
        if (Math.abs(u_530_F.v_4262_N((finalYaw += this.h_1847_R) - aYb2.x)) > 88.0f) {
            finalYaw = aYb2.x + (u_530_F.v_4262_N(finalYaw - aYb2.x) > 0.0f ? 88.0f : -88.0f);
        }
        if (!this.R_4764_Y && !isPitchNotRaycasted && this.v_4262_N.nextBoolean()) {
            this.n_1700_B(new Vector2f(finalYaw, aYb2.y + this.v_4262_N.nextFloat()), aYb2, null);
            return;
        }
        float outPitch = this.R_4764_Y ? finalPitch : this.n_1700_B(isInHitbox && !isPitchNotRaycasted ? aYb2.y + nextYawLerping / (float)this.n_1700_B(2L, 8L) : finalPitch + this.Q_4569_t);
        this.n_1700_B(new Vector2f(isInHitbox && !isYawNotRaycasted ? aYb2.x + nextYawLerping / (float)this.n_1700_B(2L, 8L) : finalYaw, outPitch), aYb2, null);
    }

    public e_2866_D n_1700_B(r_4811_B ajE2, boolean snap) {
        double yDiff;
        this.M_588_G = u_530_F.v_4262_N(this.P_1922_E(0.75f, 1.04f), this.M_588_G, this.P_1922_E(0.55f, 0.95f));
        this.P_4830_p = u_530_F.v_4262_N(this.P_1922_E(0.645f, 0.75f), this.P_4830_p, this.P_1922_E(0.45f, 1.05f));
        if (this.t_148_a == e_2866_D.n_1700_B) {
            this.t_148_a = k_4946_A.n_1700_B(ajE2, 1.0f);
        }
        e_2866_D selfEyePos = k_4946_A.n_1700_B.Y_259_p.u_2550_I(1.0f);
        if (this.w_1484_f == e_2866_D.n_1700_B) {
            this.w_1484_f = selfEyePos;
        }
        boolean isDistanceDif = (yDiff = Math.abs(ajE2.X_2960_b() - k_4946_A.n_1700_B.Y_259_p.X_2960_b())) > 1.0;
        if (System.currentTimeMillis() - this.u_2550_I > (snap ? this.n_1700_B(25L, 67L) : this.n_1700_B(87L, 185L))) {
            this.u_2550_I = System.currentTimeMillis();
            double preferredY = ajE2.X_2960_b() + (double)(ajE2.v_165_F() / 2.0f);
            double newTargetY = u_530_F.G_564_y((double)this.P_1922_E(0.29f, isDistanceDif ? 0.45f : 0.69f), this.t_148_a.R_4764_Y, preferredY);
            this.t_148_a = new e_2866_D(this.t_148_a.J_1907_R, newTargetY, this.t_148_a.G_564_y);
        }
        if (System.currentTimeMillis() - this.u_2550_I > (snap ? this.n_1700_B(25L, 67L) : this.n_1700_B(55L, 115L))) {
            double newSelfY = u_530_F.G_564_y((double)this.P_1922_E(0.15f, 0.35f), this.w_1484_f.R_4764_Y, selfEyePos.R_4764_Y);
            this.w_1484_f = new e_2866_D(this.w_1484_f.J_1907_R, newSelfY, this.w_1484_f.G_564_y);
        }
        this.w_1484_f = new e_2866_D(u_530_F.G_564_y((double)this.M_588_G, this.w_1484_f.J_1907_R, selfEyePos.J_1907_R), this.w_1484_f.R_4764_Y, u_530_F.G_564_y((double)this.P_4830_p, this.w_1484_f.G_564_y, selfEyePos.G_564_y));
        this.t_148_a = new e_2866_D(u_530_F.G_564_y((double)this.M_588_G, this.t_148_a.J_1907_R, ajE2.O_3598_v()), this.t_148_a.R_4764_Y, u_530_F.G_564_y((double)this.P_4830_p, this.t_148_a.G_564_y, ajE2.l_2647_k()));
        return this.t_148_a.G_564_y(this.w_1484_f);
    }

    private static e_2866_D n_1700_B(r_4811_B entity, float partialTicks) {
        return new e_2866_D(u_530_F.G_564_y((double)partialTicks, entity.q_1982_R, entity.O_3598_v()), u_530_F.G_564_y((double)partialTicks, entity.dtoRealmsServerAddress, entity.X_2960_b()), u_530_F.G_564_y((double)partialTicks, entity.w_612_n, entity.l_2647_k()));
    }

    @Generated
    public k_4946_A() {
    }

    @Generated
    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(boolean allowGhostAttack) {
        this.J_1907_R = allowGhostAttack;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public void J_1907_R(boolean stablePitch) {
        this.R_4764_Y = stablePitch;
    }

    @Generated
    public boolean G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public void R_4764_Y(boolean disableGhostAttack) {
        this.G_564_y = disableGhostAttack;
    }

    @Generated
    public SecureRandom P_1922_E() {
        return this.v_4262_N;
    }
}


