/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BubbleColumnBlock;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;

public class MagmaBlock
extends T_2915_h {
    public MagmaBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!entityIn.r_3651_U() && entityIn instanceof r_4811_B && !K_4096_w.t_148_a((r_4811_B)entityIn)) {
            entityIn.n_1700_B(P_11_z.P_1922_E, 1.0f);
        }
        super.n_1700_B(worldIn, pos, entityIn);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        BubbleColumnBlock.n_1700_B((LevelAccessor)worldIn, pos.up(), true);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.J_1907_R && facingState.n_1700_B(a_3742_W.c_3005_b)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 20);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        c_1514_x blockpos = pos.up();
        if (worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R)) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.V_1665_T, D_38_f.P_1922_E, 0.5f, 2.6f + (worldIn.w_1457_N.nextFloat() - worldIn.w_1457_N.nextFloat()) * 0.8f);
            worldIn.n_1700_B(ParticleTypes.d_2461_k, (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.25, (double)blockpos.getZ() + 0.5, 8, 0.5, 0.25, 0.5, 0.0);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        worldIn.u_2550_I().n_1700_B(pos, this, 20);
    }
}


