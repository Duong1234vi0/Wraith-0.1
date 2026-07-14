/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.c_1325_f;
import lightning.product.Context;

public final class C_4611_m
extends Enum<C_4611_m>
implements c_1325_f {
    public static final /* enum */ C_4611_m n_1700_B = new C_4611_m();
    private static final /* synthetic */ C_4611_m[] J_1907_R;

    public static C_4611_m[] values() {
        return (C_4611_m[])J_1907_R.clone();
    }

    public static C_4611_m valueOf(String name) {
        return Enum.valueOf(C_4611_m.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        return V_4170_D.J_1907_R(center) && V_4170_D.J_1907_R(north) && V_4170_D.J_1907_R(west) && V_4170_D.J_1907_R(east) && V_4170_D.J_1907_R(south) && context.n_1700_B(2) == 0 ? 1 : center;
    }

    private static /* synthetic */ C_4611_m[] n_1700_B() {
        return new C_4611_m[]{n_1700_B};
    }

    static {
        J_1907_R = C_4611_m.n_1700_B();
    }
}


