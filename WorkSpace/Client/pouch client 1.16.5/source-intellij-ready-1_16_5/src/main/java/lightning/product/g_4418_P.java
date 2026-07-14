/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class g_4418_P
extends Enum<g_4418_P> {
    public static final /* enum */ g_4418_P n_1700_B = new g_4418_P(0, "options.chat.visibility.full");
    public static final /* enum */ g_4418_P J_1907_R = new g_4418_P(1, "options.chat.visibility.system");
    public static final /* enum */ g_4418_P R_4764_Y = new g_4418_P(2, "options.chat.visibility.hidden");
    private static final g_4418_P[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ g_4418_P[] v_4262_N;

    public static g_4418_P[] values() {
        return (g_4418_P[])v_4262_N.clone();
    }

    public static g_4418_P valueOf(String name) {
        return Enum.valueOf(g_4418_P.class, name);
    }

    private g_4418_P(int p_i50176_3_, String p_i50176_4_) {
        this.P_1922_E = p_i50176_3_;
        this.u_1723_Y = p_i50176_4_;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.u_1723_Y;
    }

    public static g_4418_P n_1700_B(int p_221252_0_) {
        return G_564_y[u_530_F.J_1907_R(p_221252_0_, G_564_y.length)];
    }

    private static /* synthetic */ g_4418_P[] R_4764_Y() {
        return new g_4418_P[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = g_4418_P.R_4764_Y();
        G_564_y = (g_4418_P[])Arrays.stream(g_4418_P.values()).sorted(Comparator.comparingInt(g_4418_P::n_1700_B)).toArray(g_4418_P[]::new);
    }
}

