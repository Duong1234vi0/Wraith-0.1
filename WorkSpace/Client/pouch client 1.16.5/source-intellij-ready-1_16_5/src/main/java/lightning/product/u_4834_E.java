/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.AbstractSkullBlock;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.SkullBlock;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;

public class u_4834_E
extends AbstractSkullBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    private static final Map<b_257_Y, s_1395_c> h_1847_R = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, (Object)T_2915_h.n_1700_B(4.0, 4.0, 8.0, 12.0, 12.0, 16.0), (Object)b_257_Y.G_564_y, (Object)T_2915_h.n_1700_B(4.0, 4.0, 0.0, 12.0, 12.0, 8.0), (Object)b_257_Y.u_1723_Y, (Object)T_2915_h.n_1700_B(0.0, 4.0, 4.0, 8.0, 12.0, 12.0), (Object)b_257_Y.P_1922_E, (Object)T_2915_h.n_1700_B(8.0, 4.0, 4.0, 16.0, 12.0, 12.0)));

    protected u_4834_E(SkullBlock.n_1700_B type, q_4293_E.P_1922_E properties) {
        super(type, properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public String P_4830_p() {
        return this.u_1723_Y().J_1907_R();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R.get(state.R_4764_Y(P_4830_p));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y[] adirection;
        K_4074_S blockstate = this.multiplayerClientSuggestionProvider();
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : adirection = context.G_564_y()) {
            if (!direction.h_1847_R().G_564_y()) continue;
            b_257_Y direction1 = direction.u_1723_Y();
            blockstate = (K_4074_S)blockstate.n_1700_B(P_4830_p, direction1);
            if (iblockreader.getBlockState(blockpos.offset(direction)).n_1700_B(context)) continue;
            return blockstate;
        }
        return null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


