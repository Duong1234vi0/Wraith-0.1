/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public abstract sealed class F_4094_N
extends Enum<F_4094_N> {
    public static final /* enum */ F_4094_N n_1700_B = new F_4094_N(){

        @Override
        public boolean n_1700_B(K_4074_S p_241854_1_, BlockGetter p_241854_2_, c_1514_x p_241854_3_, b_257_Y p_241854_4_) {
            return T_2915_h.n_1700_B(p_241854_1_.M_588_G(p_241854_2_, p_241854_3_), p_241854_4_);
        }
    };
    public static final /* enum */ F_4094_N J_1907_R = new F_4094_N(){
        private final int G_564_y = 1;
        private final s_1395_c P_1922_E = T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 10.0, 9.0);

        @Override
        public boolean n_1700_B(K_4074_S p_241854_1_, BlockGetter p_241854_2_, c_1514_x p_241854_3_, b_257_Y p_241854_4_) {
            return !x_268_Y.R_4764_Y(p_241854_1_.M_588_G(p_241854_2_, p_241854_3_).n_1700_B(p_241854_4_), this.P_1922_E, BooleanOp.R_4764_Y);
        }
    };
    public static final /* enum */ F_4094_N R_4764_Y = new F_4094_N(){
        private final int G_564_y = 2;
        private final s_1395_c P_1922_E = x_268_Y.n_1700_B(x_268_Y.J_1907_R(), T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), BooleanOp.P_1922_E);

        @Override
        public boolean n_1700_B(K_4074_S p_241854_1_, BlockGetter p_241854_2_, c_1514_x p_241854_3_, b_257_Y p_241854_4_) {
            return !x_268_Y.R_4764_Y(p_241854_1_.M_588_G(p_241854_2_, p_241854_3_).n_1700_B(p_241854_4_), this.P_1922_E, BooleanOp.R_4764_Y);
        }
    };
    private static final /* synthetic */ F_4094_N[] G_564_y;

    public static F_4094_N[] values() {
        return (F_4094_N[])G_564_y.clone();
    }

    public static F_4094_N valueOf(String name) {
        return Enum.valueOf(F_4094_N.class, name);
    }

    public abstract boolean n_1700_B(K_4074_S var1, BlockGetter var2, c_1514_x var3, b_257_Y var4);

    private static /* synthetic */ F_4094_N[] n_1700_B() {
        return new F_4094_N[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        G_564_y = F_4094_N.n_1700_B();
    }
}


