/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.D_4024_W;
import lightning.product.x_282_a;

public abstract class BossEvent {
    private final UUID n_1700_B;
    protected x_282_a R_4764_Y;
    protected float G_564_y;
    protected n_1700_B P_1922_E;
    protected J_1907_R u_1723_Y;
    protected boolean v_4262_N;
    protected boolean w_1484_f;
    protected boolean t_148_a;

    public BossEvent(UUID uniqueIdIn, x_282_a nameIn, n_1700_B colorIn, J_1907_R overlayIn) {
        this.n_1700_B = uniqueIdIn;
        this.R_4764_Y = nameIn;
        this.P_1922_E = colorIn;
        this.u_1723_Y = overlayIn;
        this.G_564_y = 1.0f;
    }

    public UUID w_1484_f() {
        return this.n_1700_B;
    }

    public x_282_a t_148_a() {
        return this.R_4764_Y;
    }

    public void n_1700_B(x_282_a nameIn) {
        this.R_4764_Y = nameIn;
    }

    public float n_1700_B() {
        return this.G_564_y;
    }

    public void n_1700_B(float percentIn) {
        this.G_564_y = percentIn;
    }

    public n_1700_B s_956_w() {
        return this.P_1922_E;
    }

    public void n_1700_B(n_1700_B colorIn) {
        this.P_1922_E = colorIn;
    }

    public J_1907_R u_2550_I() {
        return this.u_1723_Y;
    }

    public void n_1700_B(J_1907_R overlayIn) {
        this.u_1723_Y = overlayIn;
    }

    public boolean M_588_G() {
        return this.v_4262_N;
    }

    public BossEvent n_1700_B(boolean darkenSkyIn) {
        this.v_4262_N = darkenSkyIn;
        return this;
    }

    public boolean P_4830_p() {
        return this.w_1484_f;
    }

    public BossEvent J_1907_R(boolean playEndBossMusicIn) {
        this.w_1484_f = playEndBossMusicIn;
        return this;
    }

    public BossEvent R_4764_Y(boolean createFogIn) {
        this.t_148_a = createFogIn;
        return this;
    }

    public boolean h_1847_R() {
        return this.t_148_a;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("pink", D_4024_W.P_4830_p);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("blue", D_4024_W.s_956_w);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("red", D_4024_W.P_1922_E);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("green", D_4024_W.u_2550_I);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("yellow", D_4024_W.Q_4569_t);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("purple", D_4024_W.J_1907_R);
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B("white", D_4024_W.M_182_A);
        private final String w_1484_f;
        private final D_4024_W t_148_a;
        private static final /* synthetic */ n_1700_B[] s_956_w;

        public static n_1700_B[] values() {
            return (n_1700_B[])s_956_w.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String name, D_4024_W formatting) {
            this.w_1484_f = name;
            this.t_148_a = formatting;
        }

        public D_4024_W n_1700_B() {
            return this.t_148_a;
        }

        public String J_1907_R() {
            return this.w_1484_f;
        }

        public static n_1700_B n_1700_B(String name) {
            for (n_1700_B bossinfo$color : lightning.product.BossEvent$n_1700_B.values()) {
                if (!bossinfo$color.w_1484_f.equals(name)) continue;
                return bossinfo$color;
            }
            return v_4262_N;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            s_956_w = lightning.product.BossEvent$n_1700_B.R_4764_Y();
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("progress");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("notched_6");
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("notched_10");
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("notched_12");
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R("notched_20");
        private final String u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String name) {
            this.u_1723_Y = name;
        }

        public String n_1700_B() {
            return this.u_1723_Y;
        }

        public static J_1907_R n_1700_B(String name) {
            for (J_1907_R bossinfo$overlay : lightning.product.BossEvent$J_1907_R.values()) {
                if (!bossinfo$overlay.u_1723_Y.equals(name)) continue;
                return bossinfo$overlay;
            }
            return n_1700_B;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            v_4262_N = lightning.product.BossEvent$J_1907_R.J_1907_R();
        }
    }
}


