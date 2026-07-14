/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.C0Transformer;
import lightning.product.Context;

public final class y_2188_j
extends Enum<y_2188_j>
implements C0Transformer {
    public static final /* enum */ y_2188_j n_1700_B = new y_2188_j();
    private static final /* synthetic */ y_2188_j[] J_1907_R;

    public static y_2188_j[] values() {
        return (y_2188_j[])J_1907_R.clone();
    }

    public static y_2188_j valueOf(String name) {
        return Enum.valueOf(y_2188_j.class, name);
    }

    @Override
    public int n_1700_B(Context context, int value) {
        return V_4170_D.J_1907_R(value) ? value : context.n_1700_B(299999) + 2;
    }

    private static /* synthetic */ y_2188_j[] n_1700_B() {
        return new y_2188_j[]{n_1700_B};
    }

    static {
        J_1907_R = y_2188_j.n_1700_B();
    }
}


