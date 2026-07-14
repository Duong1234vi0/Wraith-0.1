/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C1Transformer;
import lightning.product.Context;

public final class v_3489_Y
extends Enum<v_3489_Y>
implements C1Transformer {
    public static final /* enum */ v_3489_Y n_1700_B = new v_3489_Y();
    private static final /* synthetic */ v_3489_Y[] J_1907_R;

    public static v_3489_Y[] values() {
        return (v_3489_Y[])J_1907_R.clone();
    }

    public static v_3489_Y valueOf(String name) {
        return Enum.valueOf(v_3489_Y.class, name);
    }

    @Override
    public int n_1700_B(Context context, int value) {
        return context.n_1700_B(57) == 0 && value == 1 ? 129 : value;
    }

    private static /* synthetic */ v_3489_Y[] n_1700_B() {
        return new v_3489_Y[]{n_1700_B};
    }

    static {
        J_1907_R = v_3489_Y.n_1700_B();
    }
}


