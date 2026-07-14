/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;

public class BushBlock
extends T_2915_h {
    protected BushBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(a_3742_W.t_148_a) || state.n_1700_B(a_3742_W.s_956_w) || state.n_1700_B(a_3742_W.u_2550_I) || state.n_1700_B(a_3742_W.M_588_G) || state.n_1700_B(a_3742_W.Z_735_d);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return this.v_4262_N(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return state.P_4830_p().R_4764_Y();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return type == t_3546_P.R_4764_Y && !this.R_4764_Y ? true : super.n_1700_B(state, worldIn, pos, type);
    }
}


