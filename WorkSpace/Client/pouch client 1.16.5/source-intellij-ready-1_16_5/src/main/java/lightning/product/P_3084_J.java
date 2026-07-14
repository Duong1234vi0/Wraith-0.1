/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class P_3084_J
extends Enum<P_3084_J> {
    public static final /* enum */ P_3084_J n_1700_B = new P_3084_J(0, "options.graphics.fast");
    public static final /* enum */ P_3084_J J_1907_R = new P_3084_J(1, "options.graphics.fancy");
    public static final /* enum */ P_3084_J R_4764_Y = new P_3084_J(2, "options.graphics.fabulous");
    private static final P_3084_J[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ P_3084_J[] v_4262_N;

    public static P_3084_J[] values() {
        return (P_3084_J[])v_4262_N.clone();
    }

    public static P_3084_J valueOf(String name) {
        return Enum.valueOf(P_3084_J.class, name);
    }

    private P_3084_J(int p_i232238_3_, String p_i232238_4_) {
        this.P_1922_E = p_i232238_3_;
        this.u_1723_Y = p_i232238_4_;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.u_1723_Y;
    }

    public P_3084_J R_4764_Y() {
        return P_3084_J.n_1700_B(this.n_1700_B() + 1);
    }

    public String toString() {
        switch (this.ordinal()) {
            case 0: {
                return "fast";
            }
            case 1: {
                return "fancy";
            }
            case 2: {
                return "fabulous";
            }
        }
        throw new IllegalArgumentException();
    }

    public static P_3084_J n_1700_B(int p_238163_0_) {
        return G_564_y[u_530_F.J_1907_R(p_238163_0_, G_564_y.length)];
    }

    private static /* synthetic */ P_3084_J[] G_564_y() {
        return new P_3084_J[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = P_3084_J.G_564_y();
        G_564_y = (P_3084_J[])Arrays.stream(P_3084_J.values()).sorted(Comparator.comparingInt(P_3084_J::n_1700_B)).toArray(P_3084_J[]::new);
    }
}

