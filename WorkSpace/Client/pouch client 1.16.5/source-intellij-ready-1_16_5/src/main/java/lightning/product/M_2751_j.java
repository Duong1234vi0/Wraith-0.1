/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class M_2751_j
extends Enum<M_2751_j> {
    public static final /* enum */ M_2751_j n_1700_B = new M_2751_j(0);
    public static final /* enum */ M_2751_j J_1907_R = new M_2751_j(1);
    private final int R_4764_Y;
    private static final /* synthetic */ M_2751_j[] G_564_y;

    public static M_2751_j[] values() {
        return (M_2751_j[])G_564_y.clone();
    }

    public static M_2751_j valueOf(String name) {
        return Enum.valueOf(M_2751_j.class, name);
    }

    private M_2751_j(int id) {
        this.R_4764_Y = id;
    }

    public int n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ M_2751_j[] J_1907_R() {
        return new M_2751_j[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = M_2751_j.J_1907_R();
    }
}

