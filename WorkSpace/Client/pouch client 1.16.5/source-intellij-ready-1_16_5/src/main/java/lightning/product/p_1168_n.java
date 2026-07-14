/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.y_2012_u;

public class p_1168_n
extends T_2915_h {
    protected static final s_1395_c P_4830_p = y_2012_u.h_1847_R;

    protected p_1168_n(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return !this.multiplayerClientSuggestionProvider().n_1700_B((T_1316_M)context.getWorld(), context.getPos()) ? T_2915_h.n_1700_B(this.multiplayerClientSuggestionProvider(), a_3742_W.s_956_w.multiplayerClientSuggestionProvider(), context.getWorld(), context.getPos()) : super.n_1700_B(context);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.J_1907_R && !stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        y_2012_u.R_4764_Y(state, worldIn, pos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.up());
        return !blockstate.R_4764_Y().J_1907_R() || blockstate.J_1907_R() instanceof FenceGateBlock;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


