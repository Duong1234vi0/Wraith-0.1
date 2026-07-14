/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class K_4719_o
extends Enum<K_4719_o> {
    public static final /* enum */ K_4719_o n_1700_B = new K_4719_o(15);
    public static final /* enum */ K_4719_o J_1907_R = new K_4719_o(0);
    public final int R_4764_Y;
    private static final /* synthetic */ K_4719_o[] G_564_y;

    public static K_4719_o[] values() {
        return (K_4719_o[])G_564_y.clone();
    }

    public static K_4719_o valueOf(String name) {
        return Enum.valueOf(K_4719_o.class, name);
    }

    private K_4719_o(int defaultLightValueIn) {
        this.R_4764_Y = defaultLightValueIn;
    }

    private static /* synthetic */ K_4719_o[] n_1700_B() {
        return new K_4719_o[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = K_4719_o.n_1700_B();
    }
}

