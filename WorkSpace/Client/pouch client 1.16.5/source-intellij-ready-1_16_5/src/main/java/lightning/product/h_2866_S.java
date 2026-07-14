/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class h_2866_S
extends Enum<h_2866_S> {
    public static final /* enum */ h_2866_S n_1700_B = new h_2866_S(4259712);
    public static final /* enum */ h_2866_S J_1907_R = new h_2866_S(0xFF3030);
    public static final /* enum */ h_2866_S R_4764_Y = new h_2866_S(2138367);
    private final int G_564_y;
    private static final /* synthetic */ h_2866_S[] P_1922_E;

    public static h_2866_S[] values() {
        return (h_2866_S[])P_1922_E.clone();
    }

    public static h_2866_S valueOf(String name) {
        return Enum.valueOf(h_2866_S.class, name);
    }

    private h_2866_S(int color) {
        this.G_564_y = color;
    }

    public int n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ h_2866_S[] J_1907_R() {
        return new h_2866_S[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = h_2866_S.J_1907_R();
    }
}

