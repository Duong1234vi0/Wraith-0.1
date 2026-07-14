/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL$TypeReference
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import lightning.product.References;

public final class o_1967_f
extends Enum<o_1967_f> {
    public static final /* enum */ o_1967_f n_1700_B = new o_1967_f(References.n_1700_B);
    public static final /* enum */ o_1967_f J_1907_R = new o_1967_f(References.J_1907_R);
    public static final /* enum */ o_1967_f R_4764_Y = new o_1967_f(References.R_4764_Y);
    public static final /* enum */ o_1967_f G_564_y = new o_1967_f(References.G_564_y);
    public static final /* enum */ o_1967_f P_1922_E = new o_1967_f(References.P_1922_E);
    public static final /* enum */ o_1967_f u_1723_Y = new o_1967_f(References.u_1723_Y);
    public static final /* enum */ o_1967_f v_4262_N = new o_1967_f(References.v_4262_N);
    public static final /* enum */ o_1967_f w_1484_f = new o_1967_f(References.w_1484_f);
    public static final /* enum */ o_1967_f t_148_a = new o_1967_f(References.t_148_a);
    public static final /* enum */ o_1967_f s_956_w = new o_1967_f(References.s_956_w);
    public static final /* enum */ o_1967_f u_2550_I = new o_1967_f(References.q_2307_F);
    private final DSL.TypeReference M_588_G;
    private static final /* synthetic */ o_1967_f[] P_4830_p;

    public static o_1967_f[] values() {
        return (o_1967_f[])P_4830_p.clone();
    }

    public static o_1967_f valueOf(String name) {
        return Enum.valueOf(o_1967_f.class, name);
    }

    private o_1967_f(DSL.TypeReference reference) {
        this.M_588_G = reference;
    }

    public DSL.TypeReference n_1700_B() {
        return this.M_588_G;
    }

    private static /* synthetic */ o_1967_f[] J_1907_R() {
        return new o_1967_f[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I};
    }

    static {
        P_4830_p = o_1967_f.J_1907_R();
    }
}


