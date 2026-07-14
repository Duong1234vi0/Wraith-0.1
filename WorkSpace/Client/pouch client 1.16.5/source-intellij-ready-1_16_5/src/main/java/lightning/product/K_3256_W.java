/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.F_2203_T;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.FaceAttachedHorizontalDirectionalBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.DustParticleOptions;
import lightning.product.x_1688_C;

public class K_3256_W
extends FaceAttachedHorizontalDirectionalBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.C_2741_M;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(5.0, 4.0, 10.0, 11.0, 12.0, 16.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(5.0, 4.0, 0.0, 11.0, 12.0, 6.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(10.0, 4.0, 5.0, 16.0, 12.0, 11.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 4.0, 5.0, 6.0, 12.0, 11.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(5.0, 0.0, 4.0, 11.0, 6.0, 12.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(4.0, 0.0, 5.0, 12.0, 6.0, 11.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(5.0, 10.0, 4.0, 11.0, 16.0, 12.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(4.0, 10.0, 5.0, 12.0, 16.0, 11.0);

    protected K_3256_W(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(P_4830_p, false)).n_1700_B(RealmsServerPing, F_2203_T.J_1907_R));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch ((F_2203_T)state.R_4764_Y(RealmsServerPing)) {
            case n_1700_B: {
                switch (state.R_4764_Y(w_612_n).h_1847_R()) {
                    case n_1700_B: {
                        return w_1457_N;
                    }
                }
                return multiplayerClientSuggestionProvider;
            }
            case J_1907_R: {
                switch (state.R_4764_Y(w_612_n)) {
                    case u_1723_Y: {
                        return t_1786_h;
                    }
                    case P_1922_E: {
                        return M_182_A;
                    }
                    case G_564_y: {
                        return Q_4569_t;
                    }
                }
                return h_1847_R;
            }
        }
        switch (state.R_4764_Y(w_612_n).h_1847_R()) {
            case n_1700_B: {
                return Y_259_p;
            }
        }
        return Y_601_j;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            K_4074_S blockstate1 = (K_4074_S)state.n_1700_B(P_4830_p);
            if (blockstate1.R_4764_Y(P_4830_p).booleanValue()) {
                K_3256_W.n_1700_B(blockstate1, (LevelAccessor)worldIn, pos, 1.0f);
            }
            return m_3054_I.n_1700_B;
        }
        K_4074_S blockstate = this.R_4764_Y(state, worldIn, pos);
        float f = blockstate.R_4764_Y(P_4830_p) != false ? 0.6f : 0.5f;
        worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.RussianRoulette, D_38_f.P_1922_E, 0.3f, f);
        return m_3054_I.J_1907_R;
    }

    public K_4074_S R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        state = (K_4074_S)state.n_1700_B(P_4830_p);
        world.n_1700_B(pos, state, 3);
        this.P_1922_E(state, world, pos);
        return state;
    }

    private static void n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos, float alpha) {
        b_257_Y direction = state.R_4764_Y(w_612_n).u_1723_Y();
        b_257_Y direction1 = K_3256_W.w_1484_f(state).u_1723_Y();
        double d0 = (double)pos.getX() + 0.5 + 0.1 * (double)direction.t_148_a() + 0.2 * (double)direction1.t_148_a();
        double d1 = (double)pos.getY() + 0.5 + 0.1 * (double)direction.s_956_w() + 0.2 * (double)direction1.s_956_w();
        double d2 = (double)pos.getZ() + 0.5 + 0.1 * (double)direction.u_2550_I() + 0.2 * (double)direction1.u_2550_I();
        worldIn.n_1700_B(new DustParticleOptions(1.0f, 0.0f, 0.0f, alpha), d0, d1, d2, 0.0, 0.0, 0.0);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue() && rand.nextFloat() < 0.25f) {
            K_3256_W.n_1700_B(stateIn, (LevelAccessor)worldIn, pos, 0.5f);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            if (state.R_4764_Y(P_4830_p).booleanValue()) {
                this.P_1922_E(state, worldIn, pos);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p) != false ? 15 : 0;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p) != false && K_3256_W.w_1484_f(blockState) == side ? 15 : 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    private void P_1922_E(K_4074_S state, b_4507_u world, c_1514_x pos) {
        world.J_1907_R(pos, this);
        world.J_1907_R(pos.offset(K_3256_W.w_1484_f(state).u_1723_Y()), this);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(RealmsServerPing, w_612_n, P_4830_p);
    }
}



