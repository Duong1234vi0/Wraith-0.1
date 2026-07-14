/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class g_2783_J
extends BushBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 3.0, 14.0);

    public g_2783_J(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(BlockTags.UploadStatus) || state.n_1700_B(a_3742_W.v_165_F) || super.v_4262_N(state, worldIn, pos);
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.J_1907_R;
    }
}


