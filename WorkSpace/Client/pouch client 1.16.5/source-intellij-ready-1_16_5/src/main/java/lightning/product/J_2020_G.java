/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class J_2020_G {
    private final n_1700_B n_1700_B;
    private byte J_1907_R;
    private byte R_4764_Y;
    private byte G_564_y;
    private final x_282_a P_1922_E;

    public J_2020_G(n_1700_B type, byte x, byte y, byte rotation, @Nullable x_282_a customName) {
        this.n_1700_B = type;
        this.J_1907_R = x;
        this.R_4764_Y = y;
        this.G_564_y = rotation;
        this.P_1922_E = customName;
    }

    public byte n_1700_B() {
        return this.n_1700_B.n_1700_B();
    }

    public n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    public byte R_4764_Y() {
        return this.J_1907_R;
    }

    public byte G_564_y() {
        return this.R_4764_Y;
    }

    public byte P_1922_E() {
        return this.G_564_y;
    }

    public boolean u_1723_Y() {
        return this.n_1700_B.J_1907_R();
    }

    @Nullable
    public x_282_a v_4262_N() {
        return this.P_1922_E;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof J_2020_G)) {
            return false;
        }
        J_2020_G mapdecoration = (J_2020_G)p_equals_1_;
        if (this.n_1700_B != mapdecoration.n_1700_B) {
            return false;
        }
        if (this.G_564_y != mapdecoration.G_564_y) {
            return false;
        }
        if (this.J_1907_R != mapdecoration.J_1907_R) {
            return false;
        }
        if (this.R_4764_Y != mapdecoration.R_4764_Y) {
            return false;
        }
        return Objects.equals(this.P_1922_E, mapdecoration.P_1922_E);
    }

    public int hashCode() {
        int i = this.n_1700_B.n_1700_B();
        i = 31 * i + this.J_1907_R;
        i = 31 * i + this.R_4764_Y;
        i = 31 * i + this.G_564_y;
        return 31 * i + Objects.hashCode(this.P_1922_E);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(false);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(true);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(false);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(false);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(true);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(true);
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B(false);
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B(false);
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B(true, 5393476);
        public static final /* enum */ n_1700_B s_956_w = new n_1700_B(true, 3830373);
        public static final /* enum */ n_1700_B u_2550_I = new n_1700_B(true);
        public static final /* enum */ n_1700_B M_588_G = new n_1700_B(true);
        public static final /* enum */ n_1700_B P_4830_p = new n_1700_B(true);
        public static final /* enum */ n_1700_B h_1847_R = new n_1700_B(true);
        public static final /* enum */ n_1700_B Q_4569_t = new n_1700_B(true);
        public static final /* enum */ n_1700_B M_182_A = new n_1700_B(true);
        public static final /* enum */ n_1700_B t_1786_h = new n_1700_B(true);
        public static final /* enum */ n_1700_B multiplayerClientSuggestionProvider = new n_1700_B(true);
        public static final /* enum */ n_1700_B w_1457_N = new n_1700_B(true);
        public static final /* enum */ n_1700_B Y_601_j = new n_1700_B(true);
        public static final /* enum */ n_1700_B Y_259_p = new n_1700_B(true);
        public static final /* enum */ n_1700_B Q_2552_b = new n_1700_B(true);
        public static final /* enum */ n_1700_B C_2741_M = new n_1700_B(true);
        public static final /* enum */ n_1700_B k_2293_S = new n_1700_B(true);
        public static final /* enum */ n_1700_B q_2307_F = new n_1700_B(true);
        public static final /* enum */ n_1700_B Z_875_P = new n_1700_B(true);
        public static final /* enum */ n_1700_B c_3005_b = new n_1700_B(true);
        private final byte H_2857_Y = (byte)this.ordinal();
        private final boolean A_4115_X;
        private final int Y_1740_V;
        private static final /* synthetic */ n_1700_B[] t_4043_B;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_4043_B.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(boolean renderedOnFrame) {
            this(renderedOnFrame, -1);
        }

        private n_1700_B(boolean renderedOnFrame, int mapColor) {
            this.A_4115_X = renderedOnFrame;
            this.Y_1740_V = mapColor;
        }

        public byte n_1700_B() {
            return this.H_2857_Y;
        }

        public boolean J_1907_R() {
            return this.A_4115_X;
        }

        public boolean R_4764_Y() {
            return this.Y_1740_V >= 0;
        }

        public int G_564_y() {
            return this.Y_1740_V;
        }

        public static n_1700_B n_1700_B(byte iconByte) {
            return lightning.product.J_2020_G$n_1700_B.values()[u_530_F.n_1700_B((int)iconByte, 0, lightning.product.J_2020_G$n_1700_B.values().length - 1)];
        }

        private static /* synthetic */ n_1700_B[] P_1922_E() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j, Y_259_p, Q_2552_b, C_2741_M, k_2293_S, q_2307_F, Z_875_P, c_3005_b};
        }

        static {
            t_4043_B = lightning.product.J_2020_G$n_1700_B.P_1922_E();
        }
    }
}


