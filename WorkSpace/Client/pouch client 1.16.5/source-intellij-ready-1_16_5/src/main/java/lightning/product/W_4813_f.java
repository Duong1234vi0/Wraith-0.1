/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public final class W_4813_f
extends Enum<W_4813_f> {
    public static final /* enum */ W_4813_f n_1700_B = new W_4813_f("task", 0, D_4024_W.u_2550_I);
    public static final /* enum */ W_4813_f J_1907_R = new W_4813_f("challenge", 26, D_4024_W.u_1723_Y);
    public static final /* enum */ W_4813_f R_4764_Y = new W_4813_f("goal", 52, D_4024_W.u_2550_I);
    private final String G_564_y;
    private final int P_1922_E;
    private final D_4024_W u_1723_Y;
    private final x_282_a v_4262_N;
    private static final /* synthetic */ W_4813_f[] w_1484_f;

    public static W_4813_f[] values() {
        return (W_4813_f[])w_1484_f.clone();
    }

    public static W_4813_f valueOf(String name) {
        return Enum.valueOf(W_4813_f.class, name);
    }

    private W_4813_f(String nameIn, int iconIn, D_4024_W formatIn) {
        this.G_564_y = nameIn;
        this.P_1922_E = iconIn;
        this.u_1723_Y = formatIn;
        this.v_4262_N = new F_2904_S("advancements.toast." + nameIn);
    }

    public String n_1700_B() {
        return this.G_564_y;
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public static W_4813_f n_1700_B(String nameIn) {
        for (W_4813_f frametype : W_4813_f.values()) {
            if (!frametype.G_564_y.equals(nameIn)) continue;
            return frametype;
        }
        throw new IllegalArgumentException("Unknown frame type '" + nameIn + "'");
    }

    public D_4024_W R_4764_Y() {
        return this.u_1723_Y;
    }

    public x_282_a G_564_y() {
        return this.v_4262_N;
    }

    private static /* synthetic */ W_4813_f[] P_1922_E() {
        return new W_4813_f[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        w_1484_f = W_4813_f.P_1922_E();
    }
}

