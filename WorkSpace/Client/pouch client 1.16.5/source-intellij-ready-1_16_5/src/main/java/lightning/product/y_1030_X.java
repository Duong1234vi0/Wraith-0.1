/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class y_1030_X
extends Enum<y_1030_X>
implements E_4700_p {
    public static final /* enum */ y_1030_X n_1700_B = new y_1030_X("up");
    public static final /* enum */ y_1030_X J_1907_R = new y_1030_X("side");
    public static final /* enum */ y_1030_X R_4764_Y = new y_1030_X("none");
    private final String G_564_y;
    private static final /* synthetic */ y_1030_X[] P_1922_E;

    public static y_1030_X[] values() {
        return (y_1030_X[])P_1922_E.clone();
    }

    public static y_1030_X valueOf(String name) {
        return Enum.valueOf(y_1030_X.class, name);
    }

    private y_1030_X(String name) {
        this.G_564_y = name;
    }

    public String toString() {
        return this.n_1700_B();
    }

    @Override
    public String n_1700_B() {
        return this.G_564_y;
    }

    public boolean J_1907_R() {
        return this != R_4764_Y;
    }

    private static /* synthetic */ y_1030_X[] R_4764_Y() {
        return new y_1030_X[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = y_1030_X.R_4764_Y();
    }
}

