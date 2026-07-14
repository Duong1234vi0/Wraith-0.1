/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class l_1530_z
extends Enum<l_1530_z>
implements E_4700_p {
    public static final /* enum */ l_1530_z n_1700_B = new l_1530_z("floor");
    public static final /* enum */ l_1530_z J_1907_R = new l_1530_z("ceiling");
    public static final /* enum */ l_1530_z R_4764_Y = new l_1530_z("single_wall");
    public static final /* enum */ l_1530_z G_564_y = new l_1530_z("double_wall");
    private final String P_1922_E;
    private static final /* synthetic */ l_1530_z[] u_1723_Y;

    public static l_1530_z[] values() {
        return (l_1530_z[])u_1723_Y.clone();
    }

    public static l_1530_z valueOf(String name) {
        return Enum.valueOf(l_1530_z.class, name);
    }

    private l_1530_z(String name) {
        this.P_1922_E = name;
    }

    @Override
    public String n_1700_B() {
        return this.P_1922_E;
    }

    private static /* synthetic */ l_1530_z[] J_1907_R() {
        return new l_1530_z[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        u_1723_Y = l_1530_z.J_1907_R();
    }
}

