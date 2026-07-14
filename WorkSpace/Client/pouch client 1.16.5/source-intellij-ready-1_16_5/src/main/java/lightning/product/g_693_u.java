/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.C1Transformer;
import lightning.product.Context;

public final class g_693_u
extends Enum<g_693_u>
implements C1Transformer {
    public static final /* enum */ g_693_u n_1700_B = new g_693_u();
    private static final /* synthetic */ g_693_u[] J_1907_R;

    public static g_693_u[] values() {
        return (g_693_u[])J_1907_R.clone();
    }

    public static g_693_u valueOf(String name) {
        return Enum.valueOf(g_693_u.class, name);
    }

    @Override
    public int n_1700_B(Context context, int value) {
        if (V_4170_D.J_1907_R(value)) {
            return value;
        }
        int i = context.n_1700_B(6);
        if (i == 0) {
            return 4;
        }
        return i == 1 ? 3 : 1;
    }

    private static /* synthetic */ g_693_u[] n_1700_B() {
        return new g_693_u[]{n_1700_B};
    }

    static {
        J_1907_R = g_693_u.n_1700_B();
    }
}


