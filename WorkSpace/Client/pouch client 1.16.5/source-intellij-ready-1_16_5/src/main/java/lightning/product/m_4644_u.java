/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.security.SecureRandom;
import lightning.product.V_772_m;
import lightning.product.e_2866_D;
import lightning.product.h_3066_J;
import lightning.product.u_530_F;
import lombok.Generated;

public class m_4644_u {
    public static final float n_1700_B = 11.6f;
    private static final float J_1907_R = 40.0f;
    private static final float R_4764_Y = 0.08f;
    private static final float G_564_y = 0.06f;
    private static final int P_1922_E = 4;
    private static final int u_1723_Y = 3;
    private static final float v_4262_N = 0.85f;
    private static final int w_1484_f = 1;
    private static final int t_148_a = 6;
    private static final int[] s_956_w = new int[]{1};
    private final SecureRandom u_2550_I = new SecureRandom();
    private boolean M_588_G;
    private float P_4830_p;
    private float h_1847_R;
    private n_1700_B Q_4569_t = lightning.product.m_4644_u$n_1700_B.n_1700_B;
    private int M_182_A;
    private int t_1786_h;
    private float multiplayerClientSuggestionProvider = 1.0f;
    private int w_1457_N;
    private int Y_601_j;
    private float Y_259_p;
    private float Q_2552_b = 2.4f;
    private float C_2741_M = 0.42f;
    private float k_2293_S = 9.0f;
    private float q_2307_F = 5.0f;
    private float Z_875_P = 0.075f;
    private float c_3005_b = 0.035f;
    private float H_2857_Y = 10.0f;
    private float A_4115_X = 87.0f;
    private long Y_1740_V;

    private float n_1700_B(float from, float to, float t) {
        return from + u_530_F.v_4262_N(to - from) * t;
    }

    private float n_1700_B() {
        float t = (float)(System.currentTimeMillis() - this.Y_1740_V) * 0.01f;
        return (float)(Math.sin(t / this.Z_875_P) * (double)this.k_2293_S + Math.cos(t / this.c_3005_b + this.multiplayerClientSuggestionProvider * 1.7f) * (double)this.q_2307_F);
    }

    private float n_1700_B(float aimYaw) {
        return aimYaw + this.multiplayerClientSuggestionProvider * 40.0f + this.n_1700_B() * 0.75f;
    }

    private int n_1700_B(int mean, float sigma, int min, int max) {
        int ticks = mean + Math.round((float)this.u_2550_I.nextGaussian() * sigma);
        return u_530_F.n_1700_B(ticks, min, max);
    }

    private int J_1907_R() {
        return this.n_1700_B(3, 0.85f, 1, 6);
    }

    private void R_4764_Y() {
        this.C_2741_M = u_530_F.n_1700_B(0.28f + this.u_2550_I.nextFloat() * 0.34f, 0.22f, 0.62f);
        this.Q_2552_b = u_530_F.n_1700_B(1.6f + (float)Math.abs(this.u_2550_I.nextGaussian()) * 1.1f, 1.2f, 4.8f);
    }

    private float[] J_1907_R(float distanceScale) {
        this.Y_259_p += 0.044f + this.u_2550_I.nextFloat() * 0.02f;
        float amp = this.Q_2552_b * distanceScale;
        float nYaw = (float)(Math.sin((double)this.Y_259_p * 0.91) * (double)amp * (double)0.42f + Math.sin((double)this.Y_259_p * 1.47 + (double)0.6f) * (double)amp * (double)0.28f + this.u_2550_I.nextGaussian() * (double)amp * (double)0.18f);
        float nPitch = (float)(Math.cos((double)this.Y_259_p * 1.12 + (double)0.35f) * (double)amp * (double)0.22f + Math.cos((double)this.Y_259_p * 1.71 + (double)1.1f) * (double)amp * (double)0.16f + this.u_2550_I.nextGaussian() * (double)amp * (double)0.12f);
        return new float[]{nYaw, nPitch};
    }

    private int G_564_y() {
        return s_956_w[this.Y_601_j % s_956_w.length];
    }

    private void P_1922_E() {
        ++this.w_1457_N;
        if (this.w_1457_N >= this.G_564_y()) {
            this.multiplayerClientSuggestionProvider = -this.multiplayerClientSuggestionProvider;
            this.w_1457_N = 0;
            ++this.Y_601_j;
        }
    }

    private void u_1723_Y() {
        this.Q_4569_t = lightning.product.m_4644_u$n_1700_B.J_1907_R;
        this.M_182_A = this.J_1907_R();
        this.t_1786_h = 0;
        this.Y_259_p = (float)(this.u_2550_I.nextDouble() * Math.PI * 2.0);
        this.R_4764_Y();
    }

    private void J_1907_R(float aimYaw, float aimPitch, float distance) {
        float distScale = u_530_F.n_1700_B(1.0f - distance / 6.0f, 0.55f, 1.0f);
        float[] noise = this.J_1907_R(distScale);
        if (this.u_2550_I.nextFloat() < 0.12f) {
            this.R_4764_Y();
        }
        float targetYaw = aimYaw + noise[0];
        float targetPitch = aimPitch + noise[1];
        float lerp = this.C_2741_M * u_530_F.n_1700_B(0.85f + this.u_2550_I.nextFloat() * 0.3f, 0.75f, 1.15f);
        this.P_4830_p = this.n_1700_B(this.P_4830_p, targetYaw, lerp);
        this.h_1847_R += (targetPitch - this.h_1847_R) * (lerp * 0.88f);
    }

    public void n_1700_B(V_772_m player) {
        this.M_588_G = false;
        this.Q_4569_t = lightning.product.m_4644_u$n_1700_B.n_1700_B;
        this.M_182_A = 0;
        this.t_1786_h = 0;
        this.multiplayerClientSuggestionProvider = 1.0f;
        this.w_1457_N = 0;
        this.Y_601_j = 0;
        this.Y_259_p = 0.0f;
        this.Y_1740_V = System.currentTimeMillis();
        if (player != null) {
            this.h_1847_R = Math.max(player.f_4016_n, 11.6f);
            this.P_4830_p = player.p_178_J;
            this.M_588_G = true;
        }
    }

    public void n_1700_B(float aimYaw, float aimPitch) {
        this.P_1922_E();
        this.t_1786_h = 4;
        if (this.Q_4569_t != lightning.product.m_4644_u$n_1700_B.J_1907_R) {
            this.u_1723_Y();
        } else {
            this.M_182_A = Math.max(this.M_182_A, this.J_1907_R());
            this.R_4764_Y();
        }
    }

    public J_1907_R n_1700_B(V_772_m player, float aimYaw, float aimPitch, boolean inRange, float distance) {
        float turnSpeed;
        if (!this.M_588_G && player != null) {
            this.n_1700_B(player);
        }
        aimPitch = u_530_F.n_1700_B(Math.max(aimPitch, 11.6f), 11.6f, 47.0f);
        switch (this.Q_4569_t.ordinal()) {
            case 1: {
                this.J_1907_R(aimYaw, aimPitch, distance);
                turnSpeed = this.A_4115_X * u_530_F.n_1700_B(0.82f + this.u_2550_I.nextFloat() * 0.28f, 0.75f, 1.1f);
                --this.M_182_A;
                if (this.M_182_A > 0) break;
                this.Q_4569_t = lightning.product.m_4644_u$n_1700_B.n_1700_B;
                this.t_1786_h = 4;
                this.Y_1740_V = System.currentTimeMillis();
                turnSpeed = this.H_2857_Y;
                break;
            }
            case 0: {
                float targetBehind = this.n_1700_B(aimYaw);
                this.P_4830_p = this.n_1700_B(this.P_4830_p, targetBehind, 0.08f);
                this.h_1847_R += (aimPitch - this.h_1847_R) * 0.06f;
                turnSpeed = this.H_2857_Y;
                if (this.t_1786_h > 0) {
                    --this.t_1786_h;
                    break;
                }
                if (!inRange || this.M_182_A > 0) break;
                this.u_1723_Y();
                turnSpeed = this.A_4115_X;
                break;
            }
            default: {
                turnSpeed = this.H_2857_Y;
            }
        }
        return this.R_4764_Y(aimYaw, aimPitch, turnSpeed);
    }

    private J_1907_R R_4764_Y(float aimYaw, float aimPitch, float turnSpeed) {
        this.h_1847_R = u_530_F.n_1700_B(this.h_1847_R, 11.6f, 47.0f);
        this.P_4830_p = h_3066_J.n_1700_B(this.P_4830_p);
        this.h_1847_R = h_3066_J.n_1700_B(this.h_1847_R);
        return new J_1907_R(this.P_4830_p, this.h_1847_R, aimYaw, aimPitch, turnSpeed);
    }

    public static float n_1700_B(e_2866_D toTarget) {
        float geom = (float)(-Math.toDegrees(Math.atan2(toTarget.R_4764_Y, Math.hypot(toTarget.J_1907_R, toTarget.G_564_y))));
        return u_530_F.n_1700_B(Math.max(geom, 11.6f), 11.6f, 47.0f);
    }

    public static float J_1907_R(e_2866_D toTarget) {
        return (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(toTarget.G_564_y, toTarget.J_1907_R)) - 90.0);
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.m_4644_u$n_1700_B.n_1700_B();
        }
    }

    public static final class J_1907_R {
        private final float n_1700_B;
        private final float J_1907_R;
        private final float R_4764_Y;
        private final float G_564_y;
        private final float P_1922_E;

        public J_1907_R(float viewYaw, float viewPitch, float aimYaw, float aimPitch, float turnSpeed) {
            this.n_1700_B = viewYaw;
            this.J_1907_R = viewPitch;
            this.R_4764_Y = aimYaw;
            this.G_564_y = aimPitch;
            this.P_1922_E = turnSpeed;
        }

        @Generated
        public float n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public float J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public float R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public float G_564_y() {
            return this.G_564_y;
        }

        @Generated
        public float P_1922_E() {
            return this.P_1922_E;
        }
    }
}


