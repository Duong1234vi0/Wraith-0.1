/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class j_4067_x
extends Enum<j_4067_x> {
    public static final /* enum */ j_4067_x n_1700_B = new j_4067_x(0, "options.particles.all");
    public static final /* enum */ j_4067_x J_1907_R = new j_4067_x(1, "options.particles.decreased");
    public static final /* enum */ j_4067_x R_4764_Y = new j_4067_x(2, "options.particles.minimal");
    private static final j_4067_x[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ j_4067_x[] v_4262_N;

    public static j_4067_x[] values() {
        return (j_4067_x[])v_4262_N.clone();
    }

    public static j_4067_x valueOf(String name) {
        return Enum.valueOf(j_4067_x.class, name);
    }

    private j_4067_x(int id, String resourceKeyIn) {
        this.P_1922_E = id;
        this.u_1723_Y = resourceKeyIn;
    }

    public String n_1700_B() {
        return this.u_1723_Y;
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public static j_4067_x n_1700_B(int id) {
        return G_564_y[u_530_F.J_1907_R(id, G_564_y.length)];
    }

    private static /* synthetic */ j_4067_x[] R_4764_Y() {
        return new j_4067_x[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = j_4067_x.R_4764_Y();
        G_564_y = (j_4067_x[])Arrays.stream(j_4067_x.values()).sorted(Comparator.comparingInt(j_4067_x::J_1907_R)).toArray(j_4067_x[]::new);
    }
}

