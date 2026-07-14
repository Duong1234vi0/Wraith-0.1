/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import lightning.product.u_530_F;

public final class E_4704_H
extends Enum<E_4704_H> {
    public static final /* enum */ E_4704_H n_1700_B = new E_4704_H(0, "options.off");
    public static final /* enum */ E_4704_H J_1907_R = new E_4704_H(1, "options.attack.crosshair");
    public static final /* enum */ E_4704_H R_4764_Y = new E_4704_H(2, "options.attack.hotbar");
    private static final E_4704_H[] G_564_y;
    private final int P_1922_E;
    private final String u_1723_Y;
    private static final /* synthetic */ E_4704_H[] v_4262_N;

    public static E_4704_H[] values() {
        return (E_4704_H[])v_4262_N.clone();
    }

    public static E_4704_H valueOf(String name) {
        return Enum.valueOf(E_4704_H.class, name);
    }

    private E_4704_H(int id, String keyIn) {
        this.P_1922_E = id;
        this.u_1723_Y = keyIn;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.u_1723_Y;
    }

    public static E_4704_H n_1700_B(int id) {
        return G_564_y[u_530_F.J_1907_R(id, G_564_y.length)];
    }

    private static /* synthetic */ E_4704_H[] R_4764_Y() {
        return new E_4704_H[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = E_4704_H.R_4764_Y();
        G_564_y = (E_4704_H[])Arrays.stream(E_4704_H.values()).sorted(Comparator.comparingInt(E_4704_H::n_1700_B)).toArray(E_4704_H[]::new);
    }
}

