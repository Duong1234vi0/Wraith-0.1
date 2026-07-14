/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.I_3598_p;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class CoralFanBlock
extends I_3598_p {
    private final T_2915_h h_1847_R;

    protected CoralFanBlock(T_2915_h deadBlock, q_4293_E.P_1922_E builder) {
        super(builder);
        this.h_1847_R = deadBlock;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        this.n_1700_B(state, (LevelAccessor)worldIn, pos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!CoralFanBlock.u_1723_Y(state, worldIn, pos)) {
            worldIn.n_1700_B(pos, (K_4074_S)this.h_1847_R.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false), 2);
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        this.n_1700_B(stateIn, worldIn, currentPos);
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}


