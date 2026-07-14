/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;

public final class j_956_y
extends Enum<j_956_y> {
    public static final /* enum */ j_956_y n_1700_B = new j_956_y(D_4024_W.s_956_w);
    public static final /* enum */ j_956_y J_1907_R = new j_956_y(D_4024_W.P_4830_p);
    public static final /* enum */ j_956_y R_4764_Y = new j_956_y(D_4024_W.s_956_w);
    private final D_4024_W G_564_y;
    private static final /* synthetic */ j_956_y[] P_1922_E;

    public static j_956_y[] values() {
        return (j_956_y[])P_1922_E.clone();
    }

    public static j_956_y valueOf(String name) {
        return Enum.valueOf(j_956_y.class, name);
    }

    private j_956_y(D_4024_W color) {
        this.G_564_y = color;
    }

    public D_4024_W n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ j_956_y[] J_1907_R() {
        return new j_956_y[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = j_956_y.J_1907_R();
    }
}

