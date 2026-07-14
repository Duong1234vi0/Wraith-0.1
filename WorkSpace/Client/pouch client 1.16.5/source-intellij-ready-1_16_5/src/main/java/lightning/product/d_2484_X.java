/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class d_2484_X
extends Enum<d_2484_X>
implements E_4700_p {
    public static final /* enum */ d_2484_X n_1700_B = new d_2484_X("none");
    public static final /* enum */ d_2484_X J_1907_R = new d_2484_X("small");
    public static final /* enum */ d_2484_X R_4764_Y = new d_2484_X("large");
    private final String G_564_y;
    private static final /* synthetic */ d_2484_X[] P_1922_E;

    public static d_2484_X[] values() {
        return (d_2484_X[])P_1922_E.clone();
    }

    public static d_2484_X valueOf(String name) {
        return Enum.valueOf(d_2484_X.class, name);
    }

    private d_2484_X(String name) {
        this.G_564_y = name;
    }

    public String toString() {
        return this.G_564_y;
    }

    @Override
    public String n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ d_2484_X[] J_1907_R() {
        return new d_2484_X[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = d_2484_X.J_1907_R();
    }
}

