/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class n_1769_f
extends Enum<n_1769_f>
implements E_4700_p {
    public static final /* enum */ n_1769_f n_1700_B = new n_1769_f("top");
    public static final /* enum */ n_1769_f J_1907_R = new n_1769_f("bottom");
    public static final /* enum */ n_1769_f R_4764_Y = new n_1769_f("double");
    private final String G_564_y;
    private static final /* synthetic */ n_1769_f[] P_1922_E;

    public static n_1769_f[] values() {
        return (n_1769_f[])P_1922_E.clone();
    }

    public static n_1769_f valueOf(String name) {
        return Enum.valueOf(n_1769_f.class, name);
    }

    private n_1769_f(String name) {
        this.G_564_y = name;
    }

    public String toString() {
        return this.G_564_y;
    }

    @Override
    public String n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ n_1769_f[] J_1907_R() {
        return new n_1769_f[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = n_1769_f.J_1907_R();
    }
}

