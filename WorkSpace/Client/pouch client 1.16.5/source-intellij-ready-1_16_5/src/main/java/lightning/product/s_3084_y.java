/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BaseCoralWallFanBlock;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class s_3084_y
extends BaseCoralWallFanBlock {
    private final T_2915_h Q_4569_t;

    protected s_3084_y(T_2915_h deadBlock, q_4293_E.P_1922_E builder) {
        super(builder);
        this.Q_4569_t = deadBlock;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        this.n_1700_B(state, (LevelAccessor)worldIn, pos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!s_3084_y.u_1723_Y(state, worldIn, pos)) {
            worldIn.n_1700_B(pos, (K_4074_S)((K_4074_S)this.Q_4569_t.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, state.R_4764_Y(h_1847_R)), 2);
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing.u_1723_Y() == stateIn.R_4764_Y(h_1847_R) && !stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        this.n_1700_B(stateIn, worldIn, currentPos);
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}


