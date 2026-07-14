/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.q_4293_E;

public class HalfTransparentBlock
extends T_2915_h {
    protected HalfTransparentBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, K_4074_S adjacentBlockState, b_257_Y side) {
        return adjacentBlockState.n_1700_B(this) ? true : super.n_1700_B(state, adjacentBlockState, side);
    }
}


