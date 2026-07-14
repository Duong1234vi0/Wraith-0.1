/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;

public final class H_4868_c
extends Enum<H_4868_c> {
    public static final /* enum */ H_4868_c n_1700_B = new H_4868_c(0);
    public static final /* enum */ H_4868_c J_1907_R = new H_4868_c(1);
    public static final /* enum */ H_4868_c R_4764_Y = new H_4868_c(2);
    public static final /* enum */ H_4868_c G_564_y = new H_4868_c(3);
    public static final /* enum */ H_4868_c P_1922_E = new H_4868_c(4);
    private static final H_4868_c[] u_1723_Y;
    private final int v_4262_N;
    private static final /* synthetic */ H_4868_c[] w_1484_f;

    public static H_4868_c[] values() {
        return (H_4868_c[])w_1484_f.clone();
    }

    public static H_4868_c valueOf(String name) {
        return Enum.valueOf(H_4868_c.class, name);
    }

    private H_4868_c(int id) {
        this.v_4262_N = id;
    }

    public int n_1700_B() {
        return this.v_4262_N;
    }

    public static H_4868_c n_1700_B(int p_234248_0_) {
        return u_1723_Y[p_234248_0_ % u_1723_Y.length];
    }

    private static /* synthetic */ H_4868_c[] J_1907_R() {
        return new H_4868_c[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
    }

    static {
        w_1484_f = H_4868_c.J_1907_R();
        u_1723_Y = (H_4868_c[])Arrays.stream(H_4868_c.values()).sorted(Comparator.comparingInt(H_4868_c::n_1700_B)).toArray(H_4868_c[]::new);
    }
}

