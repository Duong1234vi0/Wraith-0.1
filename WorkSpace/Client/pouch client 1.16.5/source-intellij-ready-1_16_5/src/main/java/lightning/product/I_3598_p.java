/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_482_I;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class I_3598_p
extends N_482_I {
    private static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);

    protected I_3598_p(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }
}


