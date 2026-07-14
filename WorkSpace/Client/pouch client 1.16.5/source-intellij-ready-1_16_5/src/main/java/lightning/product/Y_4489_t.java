/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class Y_4489_t
extends Enum<Y_4489_t>
implements E_4700_p {
    public static final /* enum */ Y_4489_t n_1700_B = new Y_4489_t("compare");
    public static final /* enum */ Y_4489_t J_1907_R = new Y_4489_t("subtract");
    private final String R_4764_Y;
    private static final /* synthetic */ Y_4489_t[] G_564_y;

    public static Y_4489_t[] values() {
        return (Y_4489_t[])G_564_y.clone();
    }

    public static Y_4489_t valueOf(String name) {
        return Enum.valueOf(Y_4489_t.class, name);
    }

    private Y_4489_t(String name) {
        this.R_4764_Y = name;
    }

    public String toString() {
        return this.R_4764_Y;
    }

    @Override
    public String n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ Y_4489_t[] J_1907_R() {
        return new Y_4489_t[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = Y_4489_t.J_1907_R();
    }
}

