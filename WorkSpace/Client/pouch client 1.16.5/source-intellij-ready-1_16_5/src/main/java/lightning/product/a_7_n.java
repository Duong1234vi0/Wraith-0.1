/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1325_f;
import lightning.product.Context;

public final class a_7_n
extends Enum<a_7_n>
implements c_1325_f {
    public static final /* enum */ a_7_n n_1700_B = new a_7_n();
    private static final /* synthetic */ a_7_n[] J_1907_R;

    public static a_7_n[] values() {
        return (a_7_n[])J_1907_R.clone();
    }

    public static a_7_n valueOf(String name) {
        return Enum.valueOf(a_7_n.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        boolean flag1;
        boolean flag = west == east;
        boolean bl = flag1 = north == south;
        if (flag == flag1) {
            if (flag) {
                return context.n_1700_B(2) == 0 ? east : north;
            }
            return center;
        }
        return flag ? east : north;
    }

    private static /* synthetic */ a_7_n[] n_1700_B() {
        return new a_7_n[]{n_1700_B};
    }

    static {
        J_1907_R = a_7_n.n_1700_B();
    }
}


