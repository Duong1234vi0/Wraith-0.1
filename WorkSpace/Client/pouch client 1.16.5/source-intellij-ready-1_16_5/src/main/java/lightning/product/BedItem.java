/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;

public class BedItem
extends v_1669_V {
    public BedItem(T_2915_h blockIn, q_1613_l.n_1700_B properties) {
        super(blockIn, properties);
    }

    @Override
    protected boolean n_1700_B(BlockPlaceContext context, K_4074_S state) {
        return context.getWorld().n_1700_B(context.getPos(), state, 26);
    }
}


