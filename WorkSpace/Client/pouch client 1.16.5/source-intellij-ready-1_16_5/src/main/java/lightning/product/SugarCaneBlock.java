/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public class SugarCaneBlock
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.Ping;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    protected SugarCaneBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.u_1723_Y(pos.up())) {
            int i = 1;
            while (worldIn.getBlockState(pos.down(i)).n_1700_B(this)) {
                ++i;
            }
            if (i < 3) {
                int j = state.R_4764_Y(P_4830_p);
                if (j == 15) {
                    worldIn.J_1907_R(pos.up(), this.multiplayerClientSuggestionProvider());
                    worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 0), 4);
                } else {
                    worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, j + 1), 4);
                }
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        if (blockstate.J_1907_R() == this) {
            return true;
        }
        if (blockstate.n_1700_B(a_3742_W.t_148_a) || blockstate.n_1700_B(a_3742_W.s_956_w) || blockstate.n_1700_B(a_3742_W.u_2550_I) || blockstate.n_1700_B(a_3742_W.M_588_G) || blockstate.n_1700_B(a_3742_W.A_4115_X) || blockstate.n_1700_B(a_3742_W.Y_1740_V)) {
            c_1514_x blockpos = pos.down();
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                K_4074_S blockstate1 = worldIn.getBlockState(blockpos.offset(direction));
                FluidState fluidstate = worldIn.getFluidState(blockpos.offset(direction));
                if (!fluidstate.n_1700_B(FluidTags.J_1907_R) && !blockstate1.n_1700_B(a_3742_W.LeaveTracker)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}



