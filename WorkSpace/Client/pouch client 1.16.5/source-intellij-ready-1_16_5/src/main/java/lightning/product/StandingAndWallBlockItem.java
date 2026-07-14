/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;

public class StandingAndWallBlockItem
extends v_1669_V {
    protected final T_2915_h n_1700_B;

    public StandingAndWallBlockItem(T_2915_h floorBlock, T_2915_h wallBlockIn, q_1613_l.n_1700_B propertiesIn) {
        super(floorBlock, propertiesIn);
        this.n_1700_B = wallBlockIn;
    }

    @Override
    @Nullable
    protected K_4074_S R_4764_Y(BlockPlaceContext context) {
        K_4074_S blockstate = this.n_1700_B.n_1700_B(context);
        K_4074_S blockstate1 = null;
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : context.G_564_y()) {
            K_4074_S blockstate2;
            if (direction == b_257_Y.J_1907_R) continue;
            K_4074_S k_4074_S = blockstate2 = direction == b_257_Y.n_1700_B ? this.v_4262_N().n_1700_B(context) : blockstate;
            if (blockstate2 == null || !blockstate2.n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            blockstate1 = blockstate2;
            break;
        }
        return blockstate1 != null && iworldreader.n_1700_B(blockstate1, blockpos, CollisionContext.J_1907_R()) ? blockstate1 : null;
    }

    @Override
    public void n_1700_B(Map<T_2915_h, q_1613_l> blockToItemMap, q_1613_l itemIn) {
        super.n_1700_B(blockToItemMap, itemIn);
        blockToItemMap.put(this.n_1700_B, itemIn);
    }
}


