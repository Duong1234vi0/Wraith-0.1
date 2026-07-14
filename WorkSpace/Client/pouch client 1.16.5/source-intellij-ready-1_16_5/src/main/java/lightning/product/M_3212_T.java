/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;
import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public final class M_3212_T
extends Enum<M_3212_T>
implements E_4700_p {
    public static final /* enum */ M_3212_T n_1700_B = new M_3212_T("save");
    public static final /* enum */ M_3212_T J_1907_R = new M_3212_T("load");
    public static final /* enum */ M_3212_T R_4764_Y = new M_3212_T("corner");
    public static final /* enum */ M_3212_T G_564_y = new M_3212_T("data");
    private final String P_1922_E;
    private final x_282_a u_1723_Y;
    private static final /* synthetic */ M_3212_T[] v_4262_N;

    public static M_3212_T[] values() {
        return (M_3212_T[])v_4262_N.clone();
    }

    public static M_3212_T valueOf(String name) {
        return Enum.valueOf(M_3212_T.class, name);
    }

    private M_3212_T(String name) {
        this.P_1922_E = name;
        this.u_1723_Y = new F_2904_S("structure_block.mode_info." + name);
    }

    @Override
    public String n_1700_B() {
        return this.P_1922_E;
    }

    public x_282_a J_1907_R() {
        return this.u_1723_Y;
    }

    private static /* synthetic */ M_3212_T[] R_4764_Y() {
        return new M_3212_T[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        v_4262_N = M_3212_T.R_4764_Y();
    }
}

