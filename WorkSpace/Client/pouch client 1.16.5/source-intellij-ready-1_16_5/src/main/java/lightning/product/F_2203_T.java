/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class F_2203_T
extends Enum<F_2203_T>
implements E_4700_p {
    public static final /* enum */ F_2203_T n_1700_B = new F_2203_T("floor");
    public static final /* enum */ F_2203_T J_1907_R = new F_2203_T("wall");
    public static final /* enum */ F_2203_T R_4764_Y = new F_2203_T("ceiling");
    private final String G_564_y;
    private static final /* synthetic */ F_2203_T[] P_1922_E;

    public static F_2203_T[] values() {
        return (F_2203_T[])P_1922_E.clone();
    }

    public static F_2203_T valueOf(String name) {
        return Enum.valueOf(F_2203_T.class, name);
    }

    private F_2203_T(String name) {
        this.G_564_y = name;
    }

    @Override
    public String n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ F_2203_T[] J_1907_R() {
        return new F_2203_T[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = F_2203_T.J_1907_R();
    }
}

