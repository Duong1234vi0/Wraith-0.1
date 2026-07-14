/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CropBlock;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.q_1803_e;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;

public class q_3398_T
extends CropBlock {
    private static final s_1395_c[] P_4830_p = new s_1395_c[]{T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 3.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 5.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 7.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)};

    public q_3398_T(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    protected q_1803_e s_956_w() {
        return Items.l_683_e;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p[state.R_4764_Y(this.J_1907_R())];
    }
}


