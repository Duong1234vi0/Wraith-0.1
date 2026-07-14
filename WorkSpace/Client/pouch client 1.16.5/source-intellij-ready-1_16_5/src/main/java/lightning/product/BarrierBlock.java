/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;

public class BarrierBlock
extends T_2915_h {
    protected BarrierBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return true;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.n_1700_B;
    }

    @Override
    public float P_1922_E(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return 1.0f;
    }
}


