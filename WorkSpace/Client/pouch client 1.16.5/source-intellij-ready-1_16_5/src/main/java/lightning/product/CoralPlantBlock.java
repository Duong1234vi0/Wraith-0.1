/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_482_I;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class CoralPlantBlock
extends N_482_I {
    private final T_2915_h Q_4569_t;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 15.0, 14.0);

    protected CoralPlantBlock(T_2915_h deadBlock, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Q_4569_t = deadBlock;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        this.n_1700_B(state, (LevelAccessor)worldIn, pos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!CoralPlantBlock.u_1723_Y(state, worldIn, pos)) {
            worldIn.n_1700_B(pos, (K_4074_S)this.Q_4569_t.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false), 2);
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

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }
}


