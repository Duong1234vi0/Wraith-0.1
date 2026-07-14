/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.c_1325_f;
import lightning.product.Context;

public final class l_2569_y
extends Enum<l_2569_y>
implements c_1325_f {
    public static final /* enum */ l_2569_y n_1700_B = new l_2569_y();
    private static final /* synthetic */ l_2569_y[] J_1907_R;

    public static l_2569_y[] values() {
        return (l_2569_y[])J_1907_R.clone();
    }

    public static l_2569_y valueOf(String name) {
        return Enum.valueOf(l_2569_y.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        if (V_4170_D.J_1907_R(center)) {
            int i = 0;
            if (V_4170_D.J_1907_R(north)) {
                ++i;
            }
            if (V_4170_D.J_1907_R(west)) {
                ++i;
            }
            if (V_4170_D.J_1907_R(east)) {
                ++i;
            }
            if (V_4170_D.J_1907_R(south)) {
                ++i;
            }
            if (i > 3) {
                if (center == 44) {
                    return 47;
                }
                if (center == 45) {
                    return 48;
                }
                if (center == 0) {
                    return 24;
                }
                if (center == 46) {
                    return 49;
                }
                if (center == 10) {
                    return 50;
                }
                return 24;
            }
        }
        return center;
    }

    private static /* synthetic */ l_2569_y[] n_1700_B() {
        return new l_2569_y[]{n_1700_B};
    }

    static {
        J_1907_R = l_2569_y.n_1700_B();
    }
}


