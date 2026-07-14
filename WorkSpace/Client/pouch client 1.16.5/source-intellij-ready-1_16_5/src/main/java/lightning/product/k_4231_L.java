/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public final class k_4231_L
extends Enum<k_4231_L> {
    public static final /* enum */ k_4231_L n_1700_B = new k_4231_L(new F_2904_S("options.mainHand.left"));
    public static final /* enum */ k_4231_L J_1907_R = new k_4231_L(new F_2904_S("options.mainHand.right"));
    private final x_282_a R_4764_Y;
    private static final /* synthetic */ k_4231_L[] G_564_y;

    public static k_4231_L[] values() {
        return (k_4231_L[])G_564_y.clone();
    }

    public static k_4231_L valueOf(String name) {
        return Enum.valueOf(k_4231_L.class, name);
    }

    private k_4231_L(x_282_a nameIn) {
        this.R_4764_Y = nameIn;
    }

    public k_4231_L n_1700_B() {
        return this == n_1700_B ? J_1907_R : n_1700_B;
    }

    public String toString() {
        return this.R_4764_Y.getString();
    }

    public x_282_a J_1907_R() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ k_4231_L[] R_4764_Y() {
        return new k_4231_L[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = k_4231_L.R_4764_Y();
    }
}

