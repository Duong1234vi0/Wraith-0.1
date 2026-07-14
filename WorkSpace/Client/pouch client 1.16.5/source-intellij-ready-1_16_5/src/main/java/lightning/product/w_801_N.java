/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class w_801_N
extends Enum<w_801_N>
implements E_4700_p {
    public static final /* enum */ w_801_N n_1700_B = new w_801_N("north_south");
    public static final /* enum */ w_801_N J_1907_R = new w_801_N("east_west");
    public static final /* enum */ w_801_N R_4764_Y = new w_801_N("ascending_east");
    public static final /* enum */ w_801_N G_564_y = new w_801_N("ascending_west");
    public static final /* enum */ w_801_N P_1922_E = new w_801_N("ascending_north");
    public static final /* enum */ w_801_N u_1723_Y = new w_801_N("ascending_south");
    public static final /* enum */ w_801_N v_4262_N = new w_801_N("south_east");
    public static final /* enum */ w_801_N w_1484_f = new w_801_N("south_west");
    public static final /* enum */ w_801_N t_148_a = new w_801_N("north_west");
    public static final /* enum */ w_801_N s_956_w = new w_801_N("north_east");
    private final String u_2550_I;
    private static final /* synthetic */ w_801_N[] M_588_G;

    public static w_801_N[] values() {
        return (w_801_N[])M_588_G.clone();
    }

    public static w_801_N valueOf(String name) {
        return Enum.valueOf(w_801_N.class, name);
    }

    private w_801_N(String p_i225774_3_) {
        this.u_2550_I = p_i225774_3_;
    }

    public String toString() {
        return this.u_2550_I;
    }

    public boolean J_1907_R() {
        return this == P_1922_E || this == R_4764_Y || this == u_1723_Y || this == G_564_y;
    }

    @Override
    public String n_1700_B() {
        return this.u_2550_I;
    }

    private static /* synthetic */ w_801_N[] R_4764_Y() {
        return new w_801_N[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w};
    }

    static {
        M_588_G = w_801_N.R_4764_Y();
    }
}

