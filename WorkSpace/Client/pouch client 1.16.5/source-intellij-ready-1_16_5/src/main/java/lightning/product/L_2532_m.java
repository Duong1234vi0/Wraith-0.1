/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class L_2532_m
extends Enum<L_2532_m>
implements E_4700_p {
    public static final /* enum */ L_2532_m n_1700_B = new L_2532_m();
    public static final /* enum */ L_2532_m J_1907_R = new L_2532_m();
    private static final /* synthetic */ L_2532_m[] R_4764_Y;

    public static L_2532_m[] values() {
        return (L_2532_m[])R_4764_Y.clone();
    }

    public static L_2532_m valueOf(String name) {
        return Enum.valueOf(L_2532_m.class, name);
    }

    public String toString() {
        return this.n_1700_B();
    }

    @Override
    public String n_1700_B() {
        return this == n_1700_B ? "left" : "right";
    }

    private static /* synthetic */ L_2532_m[] J_1907_R() {
        return new L_2532_m[]{n_1700_B, J_1907_R};
    }

    static {
        R_4764_Y = L_2532_m.J_1907_R();
    }
}

