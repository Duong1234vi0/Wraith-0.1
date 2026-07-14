/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.w_1454_v;

public class StructureVoidBlock
extends T_2915_h {
    private static final s_1395_c P_4830_p = T_2915_h.n_1700_B(5.0, 5.0, 5.0, 11.0, 11.0, 11.0);

    protected StructureVoidBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.n_1700_B;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public float P_1922_E(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return 1.0f;
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }
}


