/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public final class E_4346_v
extends Enum<E_4346_v> {
    public static final /* enum */ E_4346_v n_1700_B = new E_4346_v(0, "cape");
    public static final /* enum */ E_4346_v J_1907_R = new E_4346_v(1, "jacket");
    public static final /* enum */ E_4346_v R_4764_Y = new E_4346_v(2, "left_sleeve");
    public static final /* enum */ E_4346_v G_564_y = new E_4346_v(3, "right_sleeve");
    public static final /* enum */ E_4346_v P_1922_E = new E_4346_v(4, "left_pants_leg");
    public static final /* enum */ E_4346_v u_1723_Y = new E_4346_v(5, "right_pants_leg");
    public static final /* enum */ E_4346_v v_4262_N = new E_4346_v(6, "hat");
    private final int w_1484_f;
    private final int t_148_a;
    private final String s_956_w;
    private final x_282_a u_2550_I;
    private static final /* synthetic */ E_4346_v[] M_588_G;

    public static E_4346_v[] values() {
        return (E_4346_v[])M_588_G.clone();
    }

    public static E_4346_v valueOf(String name) {
        return Enum.valueOf(E_4346_v.class, name);
    }

    private E_4346_v(int partIdIn, String partNameIn) {
        this.w_1484_f = partIdIn;
        this.t_148_a = 1 << partIdIn;
        this.s_956_w = partNameIn;
        this.u_2550_I = new F_2904_S("options.modelPart." + partNameIn);
    }

    public int n_1700_B() {
        return this.t_148_a;
    }

    public String J_1907_R() {
        return this.s_956_w;
    }

    public x_282_a R_4764_Y() {
        return this.u_2550_I;
    }

    private static /* synthetic */ E_4346_v[] G_564_y() {
        return new E_4346_v[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
    }

    static {
        M_588_G = E_4346_v.G_564_y();
    }
}

