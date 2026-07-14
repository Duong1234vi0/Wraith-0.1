/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;

public final class Q_3744_j
extends Enum<Q_3744_j> {
    public static final /* enum */ Q_3744_j n_1700_B = new Q_3744_j(0);
    public static final /* enum */ Q_3744_j J_1907_R = new Q_3744_j(1);
    public static final /* enum */ Q_3744_j R_4764_Y = new Q_3744_j(2);
    public static final /* enum */ Q_3744_j G_564_y = new Q_3744_j(3);
    public static final /* enum */ Q_3744_j P_1922_E = new Q_3744_j(4);
    public static final /* enum */ Q_3744_j u_1723_Y = new Q_3744_j(5);
    public static final /* enum */ Q_3744_j v_4262_N = new Q_3744_j(6);
    private static final Q_3744_j[] w_1484_f;
    private final int t_148_a;
    private static final /* synthetic */ Q_3744_j[] s_956_w;

    public static Q_3744_j[] values() {
        return (Q_3744_j[])s_956_w.clone();
    }

    public static Q_3744_j valueOf(String name) {
        return Enum.valueOf(Q_3744_j.class, name);
    }

    private Q_3744_j(int id) {
        this.t_148_a = id;
    }

    public int n_1700_B() {
        return this.t_148_a;
    }

    public static Q_3744_j n_1700_B(int p_234254_0_) {
        return w_1484_f[p_234254_0_ % w_1484_f.length];
    }

    private static /* synthetic */ Q_3744_j[] J_1907_R() {
        return new Q_3744_j[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
    }

    static {
        s_956_w = Q_3744_j.J_1907_R();
        w_1484_f = (Q_3744_j[])Arrays.stream(Q_3744_j.values()).sorted(Comparator.comparingInt(Q_3744_j::n_1700_B)).toArray(Q_3744_j[]::new);
    }
}

