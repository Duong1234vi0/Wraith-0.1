/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class g_3212_H
extends Enum<g_3212_H>
implements E_4700_p {
    public static final /* enum */ g_3212_H n_1700_B = new g_3212_H();
    public static final /* enum */ g_3212_H J_1907_R = new g_3212_H();
    private static final /* synthetic */ g_3212_H[] R_4764_Y;

    public static g_3212_H[] values() {
        return (g_3212_H[])R_4764_Y.clone();
    }

    public static g_3212_H valueOf(String name) {
        return Enum.valueOf(g_3212_H.class, name);
    }

    public String toString() {
        return this.n_1700_B();
    }

    @Override
    public String n_1700_B() {
        return this == n_1700_B ? "upper" : "lower";
    }

    private static /* synthetic */ g_3212_H[] J_1907_R() {
        return new g_3212_H[]{n_1700_B, J_1907_R};
    }

    static {
        R_4764_Y = g_3212_H.J_1907_R();
    }
}

