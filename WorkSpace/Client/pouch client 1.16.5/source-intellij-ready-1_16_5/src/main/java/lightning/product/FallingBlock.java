/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.W_4464_I;
import lightning.product.X_426_i;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.Material;

public class FallingBlock
extends T_2915_h {
    public FallingBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        worldIn.u_2550_I().n_1700_B(pos, this, this.J_1907_R());
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        worldIn.u_2550_I().n_1700_B(currentPos, this, this.J_1907_R());
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (FallingBlock.t_148_a(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
            W_4464_I fallingblockentity = new W_4464_I(worldIn, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, worldIn.getBlockState(pos));
            this.n_1700_B(fallingblockentity);
            worldIn.a_(fallingblockentity);
        }
    }

    protected void n_1700_B(W_4464_I fallingEntity) {
    }

    protected int J_1907_R() {
        return 2;
    }

    public static boolean t_148_a(K_4074_S state) {
        Material material = state.R_4764_Y();
        return state.v_4262_N() || state.n_1700_B(BlockTags.j_276_v) || material.n_1700_B() || material.P_1922_E();
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S fallingState, K_4074_S hitState, W_4464_I fallingBlock) {
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, W_4464_I fallingBlock) {
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        c_1514_x blockpos;
        if (rand.nextInt(16) == 0 && FallingBlock.t_148_a(worldIn.getBlockState(blockpos = pos.down()))) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() - 0.05;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.n_1700_B(new X_426_i(ParticleTypes.k_2293_S, stateIn), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    public int v_4262_N(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return -16777216;
    }
}


