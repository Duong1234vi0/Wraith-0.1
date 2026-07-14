/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;
import lightning.product.w_1454_v;

public class GlazedTerracottaBlock
extends HorizontalDirectionalBlock {
    public GlazedTerracottaBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{w_612_n});
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(w_612_n, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.P_1922_E;
    }
}


