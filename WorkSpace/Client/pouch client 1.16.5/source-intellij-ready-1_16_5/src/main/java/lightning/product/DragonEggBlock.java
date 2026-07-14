/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.FallingBlock;

public class DragonEggBlock
extends FallingBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public DragonEggBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        this.R_4764_Y(state, worldIn, pos);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        this.R_4764_Y(state, worldIn, pos);
    }

    private void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        for (int i = 0; i < 1000; ++i) {
            c_1514_x blockpos = pos.add(world.w_1457_N.nextInt(16) - world.w_1457_N.nextInt(16), world.w_1457_N.nextInt(8) - world.w_1457_N.nextInt(8), world.w_1457_N.nextInt(16) - world.w_1457_N.nextInt(16));
            if (!world.getBlockState(blockpos).v_4262_N()) continue;
            if (world.Y_259_p) {
                for (int j = 0; j < 128; ++j) {
                    double d0 = world.w_1457_N.nextDouble();
                    float f = (world.w_1457_N.nextFloat() - 0.5f) * 0.2f;
                    float f1 = (world.w_1457_N.nextFloat() - 0.5f) * 0.2f;
                    float f2 = (world.w_1457_N.nextFloat() - 0.5f) * 0.2f;
                    double d1 = u_530_F.G_564_y(d0, (double)blockpos.getX(), (double)pos.getX()) + (world.w_1457_N.nextDouble() - 0.5) + 0.5;
                    double d2 = u_530_F.G_564_y(d0, (double)blockpos.getY(), (double)pos.getY()) + world.w_1457_N.nextDouble() - 0.5;
                    double d3 = u_530_F.G_564_y(d0, (double)blockpos.getZ(), (double)pos.getZ()) + (world.w_1457_N.nextDouble() - 0.5) + 0.5;
                    world.n_1700_B(ParticleTypes.g_221_o, d1, d2, d3, (double)f, (double)f1, f2);
                }
            } else {
                world.n_1700_B(blockpos, state, 2);
                world.n_1700_B(pos, false);
            }
            return;
        }
    }

    @Override
    protected int J_1907_R() {
        return 5;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


