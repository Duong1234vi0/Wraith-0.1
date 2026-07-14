/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import lightning.product.c_4037_x;

public class A_1471_w {
    private static A_1471_w n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final boolean v_4262_N;
    private final boolean w_1484_f;

    private A_1471_w(boolean separateBlendIn, boolean opaqueIn, int srcColorFactorIn, int destColorFactorIn, int srcAlphaFactorIn, int destAlphaFactorIn, int blendFunctionIn) {
        this.v_4262_N = separateBlendIn;
        this.J_1907_R = srcColorFactorIn;
        this.G_564_y = destColorFactorIn;
        this.R_4764_Y = srcAlphaFactorIn;
        this.P_1922_E = destAlphaFactorIn;
        this.w_1484_f = opaqueIn;
        this.u_1723_Y = blendFunctionIn;
    }

    public A_1471_w() {
        this(false, true, 1, 0, 1, 0, 32774);
    }

    public A_1471_w(int srcFactor, int dstFactor, int blendFunctionIn) {
        this(false, false, srcFactor, dstFactor, srcFactor, dstFactor, blendFunctionIn);
    }

    public A_1471_w(int srcColorFactorIn, int destColorFactorIn, int srcAlphaFactorIn, int destAlphaFactorIn, int blendFunctionIn) {
        this(true, false, srcColorFactorIn, destColorFactorIn, srcAlphaFactorIn, destAlphaFactorIn, blendFunctionIn);
    }

    public void n_1700_B() {
        if (!this.equals(n_1700_B)) {
            if (n_1700_B == null || this.w_1484_f != n_1700_B.J_1907_R()) {
                n_1700_B = this;
                if (this.w_1484_f) {
                    c_4037_x.Y_259_p();
                    return;
                }
                c_4037_x.Y_601_j();
            }
            c_4037_x.R_4764_Y(this.u_1723_Y);
            if (this.v_4262_N) {
                c_4037_x.J_1907_R(this.J_1907_R, this.G_564_y, this.R_4764_Y, this.P_1922_E);
            } else {
                c_4037_x.J_1907_R(this.J_1907_R, this.G_564_y);
            }
        }
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof A_1471_w)) {
            return false;
        }
        A_1471_w jsonblendingmode = (A_1471_w)p_equals_1_;
        if (this.u_1723_Y != jsonblendingmode.u_1723_Y) {
            return false;
        }
        if (this.P_1922_E != jsonblendingmode.P_1922_E) {
            return false;
        }
        if (this.G_564_y != jsonblendingmode.G_564_y) {
            return false;
        }
        if (this.w_1484_f != jsonblendingmode.w_1484_f) {
            return false;
        }
        if (this.v_4262_N != jsonblendingmode.v_4262_N) {
            return false;
        }
        if (this.R_4764_Y != jsonblendingmode.R_4764_Y) {
            return false;
        }
        return this.J_1907_R == jsonblendingmode.J_1907_R;
    }

    public int hashCode() {
        int i = this.J_1907_R;
        i = 31 * i + this.R_4764_Y;
        i = 31 * i + this.G_564_y;
        i = 31 * i + this.P_1922_E;
        i = 31 * i + this.u_1723_Y;
        i = 31 * i + (this.v_4262_N ? 1 : 0);
        return 31 * i + (this.w_1484_f ? 1 : 0);
    }

    public boolean J_1907_R() {
        return this.w_1484_f;
    }

    public static int n_1700_B(String funcName) {
        String s = funcName.trim().toLowerCase(Locale.ROOT);
        if ("add".equals(s)) {
            return 32774;
        }
        if ("subtract".equals(s)) {
            return 32778;
        }
        if ("reversesubtract".equals(s)) {
            return 32779;
        }
        if ("reverse_subtract".equals(s)) {
            return 32779;
        }
        if ("min".equals(s)) {
            return 32775;
        }
        return "max".equals(s) ? 32776 : 32774;
    }

    public static int J_1907_R(String factorName) {
        String s = factorName.trim().toLowerCase(Locale.ROOT);
        s = s.replaceAll("_", "");
        s = s.replaceAll("one", "1");
        s = s.replaceAll("zero", "0");
        if ("0".equals(s = s.replaceAll("minus", "-"))) {
            return 0;
        }
        if ("1".equals(s)) {
            return 1;
        }
        if ("srccolor".equals(s)) {
            return 768;
        }
        if ("1-srccolor".equals(s)) {
            return 769;
        }
        if ("dstcolor".equals(s)) {
            return 774;
        }
        if ("1-dstcolor".equals(s)) {
            return 775;
        }
        if ("srcalpha".equals(s)) {
            return 770;
        }
        if ("1-srcalpha".equals(s)) {
            return 771;
        }
        if ("dstalpha".equals(s)) {
            return 772;
        }
        return "1-dstalpha".equals(s) ? 773 : -1;
    }
}

