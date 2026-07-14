/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class X_3584_U
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.RealmsClientConfig;
    protected static final s_1395_c[] h_1847_R = new s_1395_c[]{T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(3.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(5.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(7.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(9.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(11.0, 0.0, 1.0, 15.0, 8.0, 15.0), T_2915_h.n_1700_B(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)};

    protected X_3584_U(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            Z_1993_T itemstack = player.R_4764_Y(handIn);
            if (this.n_1700_B((LevelAccessor)worldIn, pos, state, player).n_1700_B()) {
                return m_3054_I.n_1700_B;
            }
            if (itemstack.n_1700_B()) {
                return m_3054_I.J_1907_R;
            }
        }
        return this.n_1700_B((LevelAccessor)worldIn, pos, state, player);
    }

    private m_3054_I n_1700_B(LevelAccessor world, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!player.w_1457_N(false)) {
            return m_3054_I.R_4764_Y;
        }
        player.J_1907_R(Stats.g_164_R);
        player.P_2295_B().n_1700_B(2, 0.1f);
        int i = state.R_4764_Y(P_4830_p);
        if (i < 6) {
            world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i + 1), 3);
        } else {
            world.n_1700_B(pos, false);
        }
        return m_3054_I.n_1700_B;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).R_4764_Y().J_1907_R();
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return (7 - blockState.R_4764_Y(P_4830_p)) * 2;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


