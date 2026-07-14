/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class p_1429_o
extends Enum<p_1429_o>
implements E_4700_p {
    public static final /* enum */ p_1429_o n_1700_B = new p_1429_o("single", 0);
    public static final /* enum */ p_1429_o J_1907_R = new p_1429_o("left", 2);
    public static final /* enum */ p_1429_o R_4764_Y = new p_1429_o("right", 1);
    public static final p_1429_o[] G_564_y;
    private final String P_1922_E;
    private final int u_1723_Y;
    private static final /* synthetic */ p_1429_o[] v_4262_N;

    public static p_1429_o[] values() {
        return (p_1429_o[])v_4262_N.clone();
    }

    public static p_1429_o valueOf(String name) {
        return Enum.valueOf(p_1429_o.class, name);
    }

    private p_1429_o(String name, int oppositeIn) {
        this.P_1922_E = name;
        this.u_1723_Y = oppositeIn;
    }

    @Override
    public String n_1700_B() {
        return this.P_1922_E;
    }

    public p_1429_o J_1907_R() {
        return G_564_y[this.u_1723_Y];
    }

    private static /* synthetic */ p_1429_o[] R_4764_Y() {
        return new p_1429_o[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = p_1429_o.R_4764_Y();
        G_564_y = p_1429_o.values();
    }
}

