/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.a_3913_L;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;

public class GameMasterBlockItem
extends v_1669_V {
    public GameMasterBlockItem(T_2915_h blockIn, q_1613_l.n_1700_B builder) {
        super(blockIn, builder);
    }

    @Override
    @Nullable
    protected K_4074_S R_4764_Y(BlockPlaceContext context) {
        a_3913_L playerentity = context.getPlayer();
        return playerentity != null && !playerentity.ModuleManager() ? null : super.R_4764_Y(context);
    }
}



