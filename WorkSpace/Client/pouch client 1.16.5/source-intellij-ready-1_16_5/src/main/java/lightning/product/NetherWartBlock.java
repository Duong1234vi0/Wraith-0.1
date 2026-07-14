/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;

public class NetherWartBlock
extends BushBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.r_715_M;
    private static final s_1395_c[] h_1847_R = new s_1395_c[]{T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 5.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 11.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)};

    protected NetherWartBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(a_3742_W.C_415_h);
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) < 3;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i = state.R_4764_Y(P_4830_p);
        if (i < 3 && random.nextInt(10) == 0) {
            state = (K_4074_S)state.n_1700_B(P_4830_p, i + 1);
            worldIn.n_1700_B(pos, state, 2);
        }
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(Items.g_1096_r);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


