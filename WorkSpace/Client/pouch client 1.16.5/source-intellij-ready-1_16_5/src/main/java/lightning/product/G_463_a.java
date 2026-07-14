/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class G_463_a
extends Enum<G_463_a> {
    public static final /* enum */ G_463_a n_1700_B = new G_463_a(0, "options.ao.off");
    public static final /* enum */ G_463_a J_1907_R = new G_463_a(1, "options.ao.min");
    public static final /* enum */ G_463_a R_4764_Y = new G_463_a(2, "options.ao.max");
    private static final G_463_a[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ G_463_a[] v_4262_N;

    public static G_463_a[] values() {
        return (G_463_a[])v_4262_N.clone();
    }

    public static G_463_a valueOf(String name) {
        return Enum.valueOf(G_463_a.class, name);
    }

    private G_463_a(int idIn, String resourceKeyIn) {
        this.P_1922_E = idIn;
        this.u_1723_Y = resourceKeyIn;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.u_1723_Y;
    }

    public static G_463_a n_1700_B(int valueIn) {
        return G_564_y[u_530_F.J_1907_R(valueIn, G_564_y.length)];
    }

    private static /* synthetic */ G_463_a[] R_4764_Y() {
        return new G_463_a[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = G_463_a.R_4764_Y();
        G_564_y = (G_463_a[])Arrays.stream(G_463_a.values()).sorted(Comparator.comparingInt(G_463_a::n_1700_B)).toArray(G_463_a[]::new);
    }
}

