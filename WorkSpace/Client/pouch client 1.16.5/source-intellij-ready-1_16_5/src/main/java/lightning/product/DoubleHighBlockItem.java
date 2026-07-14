/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;

public class DoubleHighBlockItem
extends v_1669_V {
    public DoubleHighBlockItem(T_2915_h blockIn, q_1613_l.n_1700_B builder) {
        super(blockIn, builder);
    }

    @Override
    protected boolean n_1700_B(BlockPlaceContext context, K_4074_S state) {
        context.getWorld().n_1700_B(context.getPos().up(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 27);
        return super.n_1700_B(context, state);
    }
}


