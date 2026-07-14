/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.e_563_h;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;

public class RotatedPillarBlock
extends T_2915_h {
    public static final e_563_h<b_257_Y.n_1700_B> t_1786_h = BlockStateProperties.x_607_J;

    public RotatedPillarBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(t_1786_h, b_257_Y.n_1700_B.J_1907_R));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case G_564_y: 
            case J_1907_R: {
                switch (state.R_4764_Y(t_1786_h)) {
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(t_1786_h, b_257_Y.n_1700_B.R_4764_Y);
                    }
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(t_1786_h, b_257_Y.n_1700_B.n_1700_B);
                    }
                }
                return state;
            }
        }
        return state;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{t_1786_h});
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(t_1786_h, context.getFace().h_1847_R());
    }
}


