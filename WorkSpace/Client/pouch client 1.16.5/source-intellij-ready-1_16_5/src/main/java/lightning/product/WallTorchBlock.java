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
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.TorchBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.ParticleOptions;
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
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public class WallTorchBlock
extends TorchBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    private static final Map<b_257_Y, s_1395_c> h_1847_R = Maps.newEnumMap((Map)ImmutableMap.of((Object)b_257_Y.R_4764_Y, (Object)T_2915_h.n_1700_B(5.5, 3.0, 11.0, 10.5, 13.0, 16.0), (Object)b_257_Y.G_564_y, (Object)T_2915_h.n_1700_B(5.5, 3.0, 0.0, 10.5, 13.0, 5.0), (Object)b_257_Y.P_1922_E, (Object)T_2915_h.n_1700_B(11.0, 3.0, 5.5, 16.0, 13.0, 10.5), (Object)b_257_Y.u_1723_Y, (Object)T_2915_h.n_1700_B(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)));

    protected WallTorchBlock(q_4293_E.P_1922_E properties, ParticleOptions particleData) {
        super(properties, particleData);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public String P_4830_p() {
        return this.u_1723_Y().J_1907_R();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return WallTorchBlock.w_1484_f(state);
    }

    public static s_1395_c w_1484_f(K_4074_S state) {
        return h_1847_R.get(state.R_4764_Y(P_4830_p));
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        c_1514_x blockpos = pos.offset(direction.u_1723_Y());
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return blockstate.G_564_y((BlockGetter)worldIn, blockpos, direction);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y[] adirection;
        K_4074_S blockstate = this.multiplayerClientSuggestionProvider();
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : adirection = context.G_564_y()) {
            b_257_Y direction1;
            if (!direction.h_1847_R().G_564_y() || !(blockstate = (K_4074_S)blockstate.n_1700_B(P_4830_p, direction1 = direction.u_1723_Y())).n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            return blockstate;
        }
        return null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing.u_1723_Y() == stateIn.R_4764_Y(P_4830_p) && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : stateIn;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        b_257_Y direction = stateIn.R_4764_Y(P_4830_p);
        double d0 = (double)pos.getX() + 0.5;
        double d1 = (double)pos.getY() + 0.7;
        double d2 = (double)pos.getZ() + 0.5;
        double d3 = 0.22;
        double d4 = 0.27;
        b_257_Y direction1 = direction.u_1723_Y();
        worldIn.n_1700_B(ParticleTypes.B_1668_F, d0 + 0.27 * (double)direction1.t_148_a(), d1 + 0.22, d2 + 0.27 * (double)direction1.u_2550_I(), 0.0, 0.0, 0.0);
        worldIn.n_1700_B(this.t_1786_h, d0 + 0.27 * (double)direction1.t_148_a(), d1 + 0.22, d2 + 0.27 * (double)direction1.u_2550_I(), 0.0, 0.0, 0.0);
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


