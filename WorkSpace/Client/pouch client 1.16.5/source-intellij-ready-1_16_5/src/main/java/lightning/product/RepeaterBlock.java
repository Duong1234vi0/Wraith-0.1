/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.DustParticleOptions;
import lightning.product.u_782_h;
import lightning.product.x_1688_C;

public class RepeaterBlock
extends u_782_h {
    public static final U_1266_O P_4830_p = BlockStateProperties.w_1457_N;
    public static final g_88_D M_182_A = BlockStateProperties.f_4016_n;

    protected RepeaterBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(M_182_A, 1)).n_1700_B(P_4830_p, false)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (!player.C_415_h.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(M_182_A), 3);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    protected int w_1484_f(K_4074_S state) {
        return state.R_4764_Y(M_182_A) * 2;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = super.n_1700_B(context);
        return (K_4074_S)blockstate.n_1700_B(P_4830_p, this.n_1700_B((T_1316_M)context.getWorld(), context.getPos(), blockstate));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return !worldIn.v_4276_D() && facing.h_1847_R() != stateIn.R_4764_Y(w_612_n).h_1847_R() ? (K_4074_S)stateIn.n_1700_B(P_4830_p, this.n_1700_B((T_1316_M)worldIn, currentPos, stateIn)) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn, c_1514_x pos, K_4074_S state) {
        return this.J_1907_R(worldIn, pos, state) > 0;
    }

    @Override
    protected boolean t_148_a(K_4074_S state) {
        return RepeaterBlock.P_4830_p(state);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(Q_4569_t).booleanValue()) {
            b_257_Y direction = stateIn.R_4764_Y(w_612_n);
            double d0 = (double)pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
            double d1 = (double)pos.getY() + 0.4 + (rand.nextDouble() - 0.5) * 0.2;
            double d2 = (double)pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
            float f = -5.0f;
            if (rand.nextBoolean()) {
                f = stateIn.R_4764_Y(M_182_A) * 2 - 1;
            }
            double d3 = (f /= 16.0f) * (float)direction.t_148_a();
            double d4 = f * (float)direction.u_2550_I();
            worldIn.n_1700_B(DustParticleOptions.n_1700_B, d0 + d3, d1, d2 + d4, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, M_182_A, P_4830_p, Q_4569_t);
    }
}


