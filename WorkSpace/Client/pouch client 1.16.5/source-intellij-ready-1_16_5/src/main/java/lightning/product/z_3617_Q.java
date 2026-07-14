/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockHitResult;
import lightning.product.RedstoneTorchBlock;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_3591_l;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.DustParticleOptions;
import lightning.product.v_1669_V;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class z_3617_Q
extends T_2915_h {
    public static final U_1266_O P_4830_p = RedstoneTorchBlock.P_4830_p;

    public z_3617_Q(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        z_3617_Q.R_4764_Y(state, worldIn, pos);
        super.n_1700_B(state, worldIn, pos, player);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        z_3617_Q.R_4764_Y(worldIn.getBlockState(pos), worldIn, pos);
        super.n_1700_B(worldIn, pos, entityIn);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            z_3617_Q.n_1700_B(worldIn, pos);
        } else {
            z_3617_Q.R_4764_Y(state, worldIn, pos);
        }
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        return itemstack.J_1907_R() instanceof v_1669_V && new BlockPlaceContext(player, handIn, itemstack, hit).n_1700_B() ? m_3054_I.R_4764_Y : m_3054_I.n_1700_B;
    }

    private static void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        z_3617_Q.n_1700_B(world, pos);
        if (!state.R_4764_Y(P_4830_p).booleanValue()) {
            world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 3);
        }
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, false), 3);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
        super.n_1700_B(state, worldIn, pos, stack);
        if (K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) == 0) {
            int i = 1 + worldIn.w_1457_N.nextInt(5);
            this.n_1700_B(worldIn, pos, i);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            z_3617_Q.n_1700_B(worldIn, pos);
        }
    }

    private static void n_1700_B(b_4507_u world, c_1514_x worldIn) {
        double d0 = 0.5625;
        Random random = world.w_1457_N;
        for (b_257_Y direction : b_257_Y.values()) {
            c_1514_x blockpos = worldIn.offset(direction);
            if (world.getBlockState(blockpos).t_148_a(world, blockpos)) continue;
            b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
            double d1 = direction$axis == b_257_Y.n_1700_B.n_1700_B ? 0.5 + 0.5625 * (double)direction.t_148_a() : (double)random.nextFloat();
            double d2 = direction$axis == b_257_Y.n_1700_B.J_1907_R ? 0.5 + 0.5625 * (double)direction.s_956_w() : (double)random.nextFloat();
            double d3 = direction$axis == b_257_Y.n_1700_B.R_4764_Y ? 0.5 + 0.5625 * (double)direction.u_2550_I() : (double)random.nextFloat();
            world.n_1700_B(DustParticleOptions.n_1700_B, (double)worldIn.getX() + d1, (double)worldIn.getY() + d2, (double)worldIn.getZ() + d3, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


