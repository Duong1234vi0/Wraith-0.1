/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C1Transformer;
import lightning.product.Context;

public final class W_1802_D
extends Enum<W_1802_D>
implements C1Transformer {
    public static final /* enum */ W_1802_D n_1700_B = new W_1802_D();
    private static final /* synthetic */ W_1802_D[] J_1907_R;

    public static W_1802_D[] values() {
        return (W_1802_D[])J_1907_R.clone();
    }

    public static W_1802_D valueOf(String name) {
        return Enum.valueOf(W_1802_D.class, name);
    }

    @Override
    public int n_1700_B(Context context, int value) {
        return context.n_1700_B(10) == 0 && value == 21 ? 168 : value;
    }

    private static /* synthetic */ W_1802_D[] n_1700_B() {
        return new W_1802_D[]{n_1700_B};
    }

    static {
        J_1907_R = W_1802_D.n_1700_B();
    }
}


