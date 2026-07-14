/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class K_2069_m
extends Enum<K_2069_m> {
    public static final /* enum */ K_2069_m n_1700_B = new K_2069_m(0, "options.off");
    public static final /* enum */ K_2069_m J_1907_R = new K_2069_m(1, "options.clouds.fast");
    public static final /* enum */ K_2069_m R_4764_Y = new K_2069_m(2, "options.clouds.fancy");
    private static final K_2069_m[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ K_2069_m[] v_4262_N;

    public static K_2069_m[] values() {
        return (K_2069_m[])v_4262_N.clone();
    }

    public static K_2069_m valueOf(String name) {
        return Enum.valueOf(K_2069_m.class, name);
    }

    private K_2069_m(int id, String keyIn) {
        this.P_1922_E = id;
        this.u_1723_Y = keyIn;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.u_1723_Y;
    }

    public static K_2069_m n_1700_B(int id) {
        return G_564_y[u_530_F.J_1907_R(id, G_564_y.length)];
    }

    private static /* synthetic */ K_2069_m[] R_4764_Y() {
        return new K_2069_m[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = K_2069_m.R_4764_Y();
        G_564_y = (K_2069_m[])Arrays.stream(K_2069_m.values()).sorted(Comparator.comparingInt(K_2069_m::n_1700_B)).toArray(K_2069_m[]::new);
    }
}

