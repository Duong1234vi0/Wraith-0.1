/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_933_M;
import lightning.product.g_88_D;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.AbstractBannerBlock;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;

public class b_1320_N
extends AbstractBannerBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.j_1564_a;
    private static final Map<e_933_M, T_2915_h> h_1847_R = Maps.newHashMap();
    private static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

    public b_1320_N(e_933_M color, q_4293_E.P_1922_E properties) {
        super(color, properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
        h_1847_R.put(color, this);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).R_4764_Y().J_1907_R();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, u_530_F.R_4764_Y((double)((180.0f + context.getPlacementYaw()) * 16.0f / 360.0f) + 0.5) & 0xF);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p), 16));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)state.n_1700_B(P_4830_p, mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p), 16));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    public static T_2915_h n_1700_B(e_933_M color) {
        return h_1847_R.getOrDefault(color, a_3742_W.t_2598_a);
    }
}


