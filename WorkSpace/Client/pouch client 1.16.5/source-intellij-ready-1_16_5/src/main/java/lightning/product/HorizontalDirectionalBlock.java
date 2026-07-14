/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;

public abstract class HorizontalDirectionalBlock
extends T_2915_h {
    public static final DirectionProperty w_612_n = BlockStateProperties.q_4610_l;

    protected HorizontalDirectionalBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(w_612_n, rot.n_1700_B(state.R_4764_Y(w_612_n)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(w_612_n)));
    }
}


