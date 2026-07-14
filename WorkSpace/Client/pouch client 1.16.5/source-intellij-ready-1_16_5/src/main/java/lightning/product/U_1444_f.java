/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_4425_k;
import lightning.product.V_4170_D;
import lightning.product.Context;

public final class U_1444_f
extends Enum<U_1444_f>
implements T_4425_k {
    public static final /* enum */ U_1444_f n_1700_B = new U_1444_f();
    private static final /* synthetic */ U_1444_f[] J_1907_R;

    public static U_1444_f[] values() {
        return (U_1444_f[])J_1907_R.clone();
    }

    public static U_1444_f valueOf(String name) {
        return Enum.valueOf(U_1444_f.class, name);
    }

    @Override
    public int n_1700_B(Context context, int x, int southEast, int p_202792_4_, int p_202792_5_, int p_202792_6_) {
        if (!V_4170_D.J_1907_R(p_202792_6_) || V_4170_D.J_1907_R(p_202792_5_) && V_4170_D.J_1907_R(p_202792_4_) && V_4170_D.J_1907_R(x) && V_4170_D.J_1907_R(southEast)) {
            if (!V_4170_D.J_1907_R(p_202792_6_) && (V_4170_D.J_1907_R(p_202792_5_) || V_4170_D.J_1907_R(x) || V_4170_D.J_1907_R(p_202792_4_) || V_4170_D.J_1907_R(southEast)) && context.n_1700_B(5) == 0) {
                if (V_4170_D.J_1907_R(p_202792_5_)) {
                    return p_202792_6_ == 4 ? 4 : p_202792_5_;
                }
                if (V_4170_D.J_1907_R(x)) {
                    return p_202792_6_ == 4 ? 4 : x;
                }
                if (V_4170_D.J_1907_R(p_202792_4_)) {
                    return p_202792_6_ == 4 ? 4 : p_202792_4_;
                }
                if (V_4170_D.J_1907_R(southEast)) {
                    return p_202792_6_ == 4 ? 4 : southEast;
                }
            }
            return p_202792_6_;
        }
        int i = 1;
        int j = 1;
        if (!V_4170_D.J_1907_R(p_202792_5_) && context.n_1700_B(i++) == 0) {
            j = p_202792_5_;
        }
        if (!V_4170_D.J_1907_R(p_202792_4_) && context.n_1700_B(i++) == 0) {
            j = p_202792_4_;
        }
        if (!V_4170_D.J_1907_R(x) && context.n_1700_B(i++) == 0) {
            j = x;
        }
        if (!V_4170_D.J_1907_R(southEast) && context.n_1700_B(i++) == 0) {
            j = southEast;
        }
        if (context.n_1700_B(3) == 0) {
            return j;
        }
        return j == 4 ? 4 : p_202792_6_;
    }

    private static /* synthetic */ U_1444_f[] n_1700_B() {
        return new U_1444_f[]{n_1700_B};
    }

    static {
        J_1907_R = U_1444_f.n_1700_B();
    }
}


