/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class h_2829_o
extends Enum<h_2829_o>
implements E_4700_p {
    public static final /* enum */ h_2829_o n_1700_B = new h_2829_o("head");
    public static final /* enum */ h_2829_o J_1907_R = new h_2829_o("foot");
    private final String R_4764_Y;
    private static final /* synthetic */ h_2829_o[] G_564_y;

    public static h_2829_o[] values() {
        return (h_2829_o[])G_564_y.clone();
    }

    public static h_2829_o valueOf(String name) {
        return Enum.valueOf(h_2829_o.class, name);
    }

    private h_2829_o(String name) {
        this.R_4764_Y = name;
    }

    public String toString() {
        return this.R_4764_Y;
    }

    @Override
    public String n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ h_2829_o[] J_1907_R() {
        return new h_2829_o[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = h_2829_o.J_1907_R();
    }
}

