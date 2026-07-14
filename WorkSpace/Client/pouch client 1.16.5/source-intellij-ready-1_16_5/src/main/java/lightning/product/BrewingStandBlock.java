/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_4091_N;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;

public class BrewingStandBlock
extends BaseEntityBlock {
    public static final U_1266_O[] P_4830_p = new U_1266_O[]{BlockStateProperties.u_2550_I, BlockStateProperties.M_588_G, BlockStateProperties.P_4830_p};
    protected static final s_1395_c h_1847_R = x_268_Y.n_1700_B(T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 2.0, 15.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 14.0, 9.0));

    public BrewingStandBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p[0], false)).n_1700_B(P_4830_p[1], false)).n_1700_B(P_4830_p[2], false));
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new A_4091_N();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof A_4091_N) {
            player.n_1700_B((A_4091_N)tileentity);
            player.J_1907_R(Stats.g_2268_R);
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof A_4091_N) {
            ((A_4091_N)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        double d0 = (double)pos.getX() + 0.4 + (double)rand.nextFloat() * 0.2;
        double d1 = (double)pos.getY() + 0.7 + (double)rand.nextFloat() * 0.3;
        double d2 = (double)pos.getZ() + 0.4 + (double)rand.nextFloat() * 0.2;
        worldIn.n_1700_B(ParticleTypes.B_1668_F, d0, d1, d2, 0.0, 0.0, 0.0);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof A_4091_N) {
                K_3065_y.n_1700_B(worldIn, pos, (Container)((A_4091_N)tileentity));
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return a_2900_S.n_1700_B(worldIn.getTileEntity(pos));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p[0], P_4830_p[1], P_4830_p[2]);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


