/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;

public class SnowLayerBlock
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.D_60_a;
    protected static final s_1395_c[] h_1847_R = new s_1395_c[]{x_268_Y.n_1700_B(), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    protected SnowLayerBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 1));
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return state.R_4764_Y(P_4830_p) < 5;
            }
            case J_1907_R: {
                return false;
            }
            case R_4764_Y: {
                return false;
            }
        }
        return false;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p) - 1];
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        if (!(blockstate.n_1700_B(a_3742_W.O_1795_e) || blockstate.n_1700_B(a_3742_W.ServerHelper) || blockstate.n_1700_B(a_3742_W.N_4890_q))) {
            if (!blockstate.n_1700_B(a_3742_W.B_1335_M) && !blockstate.n_1700_B(a_3742_W.C_415_h)) {
                return T_2915_h.n_1700_B(blockstate.u_2550_I(worldIn, pos.down()), b_257_Y.J_1907_R) || blockstate.J_1907_R() == this && blockstate.R_4764_Y(P_4830_p) == 8;
            }
            return true;
        }
        return false;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.getLightFor(K_4719_o.J_1907_R, pos) > 11) {
            SnowLayerBlock.G_564_y(state, worldIn, pos);
            worldIn.n_1700_B(pos, false);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        int i = state.R_4764_Y(P_4830_p);
        if (useContext.getItem().J_1907_R() == this.u_1723_Y() && i < 8) {
            if (useContext.J_1907_R()) {
                return useContext.getFace() == b_257_Y.J_1907_R;
            }
            return true;
        }
        return i == 1;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos());
        if (blockstate.n_1700_B(this)) {
            int i = blockstate.R_4764_Y(P_4830_p);
            return (K_4074_S)blockstate.n_1700_B(P_4830_p, Math.min(8, i + 1));
        }
        return super.n_1700_B(context);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}



