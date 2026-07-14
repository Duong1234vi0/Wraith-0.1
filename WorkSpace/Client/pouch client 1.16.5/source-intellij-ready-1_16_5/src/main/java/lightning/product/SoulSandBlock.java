/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BubbleColumnBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_268_Y;

public class SoulSandBlock
extends T_2915_h {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

    public SoulSandBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return x_268_Y.J_1907_R();
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return x_268_Y.J_1907_R();
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        BubbleColumnBlock.n_1700_B((LevelAccessor)worldIn, pos.up(), false);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.J_1907_R && facingState.n_1700_B(a_3742_W.c_3005_b)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 20);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        worldIn.u_2550_I().n_1700_B(pos, this, 20);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


