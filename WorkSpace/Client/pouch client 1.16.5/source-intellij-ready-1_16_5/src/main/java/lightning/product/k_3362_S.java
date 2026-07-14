/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_4425_k;
import lightning.product.V_4170_D;
import lightning.product.Context;

public final class k_3362_S
extends Enum<k_3362_S>
implements T_4425_k {
    public static final /* enum */ k_3362_S n_1700_B = new k_3362_S();
    private static final /* synthetic */ k_3362_S[] J_1907_R;

    public static k_3362_S[] values() {
        return (k_3362_S[])J_1907_R.clone();
    }

    public static k_3362_S valueOf(String name) {
        return Enum.valueOf(k_3362_S.class, name);
    }

    @Override
    public int n_1700_B(Context context, int x, int southEast, int p_202792_4_, int p_202792_5_, int p_202792_6_) {
        return V_4170_D.J_1907_R(p_202792_6_) && V_4170_D.J_1907_R(p_202792_5_) && V_4170_D.J_1907_R(x) && V_4170_D.J_1907_R(p_202792_4_) && V_4170_D.J_1907_R(southEast) && context.n_1700_B(100) == 0 ? 14 : p_202792_6_;
    }

    private static /* synthetic */ k_3362_S[] n_1700_B() {
        return new k_3362_S[]{n_1700_B};
    }

    static {
        J_1907_R = k_3362_S.n_1700_B();
    }
}


