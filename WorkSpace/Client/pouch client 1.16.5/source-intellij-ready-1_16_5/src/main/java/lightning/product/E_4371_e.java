/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1325_f;
import lightning.product.Context;

public final class E_4371_e
extends Enum<E_4371_e>
implements c_1325_f {
    public static final /* enum */ E_4371_e n_1700_B = new E_4371_e();
    private static final /* synthetic */ E_4371_e[] J_1907_R;

    public static E_4371_e[] values() {
        return (E_4371_e[])J_1907_R.clone();
    }

    public static E_4371_e valueOf(String name) {
        return Enum.valueOf(E_4371_e.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        int i = E_4371_e.R_4764_Y(center);
        return i == E_4371_e.R_4764_Y(east) && i == E_4371_e.R_4764_Y(north) && i == E_4371_e.R_4764_Y(west) && i == E_4371_e.R_4764_Y(south) ? -1 : 7;
    }

    private static int R_4764_Y(int p_151630_0_) {
        return p_151630_0_ >= 2 ? 2 + (p_151630_0_ & 1) : p_151630_0_;
    }

    private static /* synthetic */ E_4371_e[] n_1700_B() {
        return new E_4371_e[]{n_1700_B};
    }

    static {
        J_1907_R = E_4371_e.n_1700_B();
    }
}


