/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.I_3598_p;
import lightning.product.Fluids;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class BaseCoralWallFanBlock
extends I_3598_p {
    public static final DirectionProperty h_1847_R = HorizontalDirectionalBlock.w_612_n;
    private static final Map<b_257_Y, s_1395_c> Q_4569_t = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, (Object)T_2915_h.n_1700_B(0.0, 4.0, 5.0, 16.0, 12.0, 16.0), (Object)b_257_Y.G_564_y, (Object)T_2915_h.n_1700_B(0.0, 4.0, 0.0, 16.0, 12.0, 11.0), (Object)b_257_Y.P_1922_E, (Object)T_2915_h.n_1700_B(5.0, 4.0, 0.0, 16.0, 12.0, 16.0), (Object)b_257_Y.u_1723_Y, (Object)T_2915_h.n_1700_B(0.0, 4.0, 0.0, 11.0, 12.0, 16.0)));

    protected BaseCoralWallFanBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, b_257_Y.R_4764_Y)).n_1700_B(P_4830_p, true));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t.get(state.R_4764_Y(h_1847_R));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(h_1847_R, rot.n_1700_B(state.R_4764_Y(h_1847_R)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(h_1847_R)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, P_4830_p);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing.u_1723_Y() == stateIn.R_4764_Y(h_1847_R) && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : stateIn;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = state.R_4764_Y(h_1847_R);
        c_1514_x blockpos = pos.offset(direction.u_1723_Y());
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return blockstate.G_564_y((BlockGetter)worldIn, blockpos, direction);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y[] adirection;
        K_4074_S blockstate = super.n_1700_B(context);
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : adirection = context.G_564_y()) {
            if (!direction.h_1847_R().G_564_y() || !(blockstate = (K_4074_S)blockstate.n_1700_B(h_1847_R, direction.u_1723_Y())).n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            return blockstate;
        }
        return null;
    }
}


