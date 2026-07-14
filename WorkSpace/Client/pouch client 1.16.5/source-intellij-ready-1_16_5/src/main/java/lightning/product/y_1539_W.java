/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;

public final class y_1539_W
extends Enum<y_1539_W>
implements E_4700_p {
    public static final /* enum */ y_1539_W n_1700_B = new y_1539_W("normal");
    public static final /* enum */ y_1539_W J_1907_R = new y_1539_W("sticky");
    private final String R_4764_Y;
    private static final /* synthetic */ y_1539_W[] G_564_y;

    public static y_1539_W[] values() {
        return (y_1539_W[])G_564_y.clone();
    }

    public static y_1539_W valueOf(String name) {
        return Enum.valueOf(y_1539_W.class, name);
    }

    private y_1539_W(String name) {
        this.R_4764_Y = name;
    }

    public String toString() {
        return this.R_4764_Y;
    }

    @Override
    public String n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ y_1539_W[] J_1907_R() {
        return new y_1539_W[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = y_1539_W.J_1907_R();
    }
}

