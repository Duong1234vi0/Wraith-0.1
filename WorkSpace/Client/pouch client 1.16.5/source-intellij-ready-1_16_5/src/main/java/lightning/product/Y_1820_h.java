/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class Y_1820_h
extends Enum<Y_1820_h>
implements E_4700_p {
    public static final /* enum */ Y_1820_h n_1700_B = new Y_1820_h("none");
    public static final /* enum */ Y_1820_h J_1907_R = new Y_1820_h("low");
    public static final /* enum */ Y_1820_h R_4764_Y = new Y_1820_h("tall");
    private final String G_564_y;
    private static final /* synthetic */ Y_1820_h[] P_1922_E;

    public static Y_1820_h[] values() {
        return (Y_1820_h[])P_1922_E.clone();
    }

    public static Y_1820_h valueOf(String name) {
        return Enum.valueOf(Y_1820_h.class, name);
    }

    private Y_1820_h(String name) {
        this.G_564_y = name;
    }

    public String toString() {
        return this.n_1700_B();
    }

    @Override
    public String n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ Y_1820_h[] J_1907_R() {
        return new Y_1820_h[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = Y_1820_h.J_1907_R();
    }
}

