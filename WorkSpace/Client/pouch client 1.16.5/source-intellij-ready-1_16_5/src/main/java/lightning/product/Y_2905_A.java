/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.DaylightDetectorBlockEntity;
import lightning.product.x_1688_C;

public class Y_2905_A
extends BaseEntityBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.q_1982_R;
    public static final U_1266_O h_1847_R = BlockStateProperties.M_182_A;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);

    public Y_2905_A(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0)).n_1700_B(h_1847_R, false));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t;
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p);
    }

    public static void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        if (world.G_624_v().J_1907_R()) {
            int i = world.getLightFor(K_4719_o.n_1700_B, pos) - world.d_2427_y();
            float f = world.P_1922_E(1.0f);
            boolean flag = state.R_4764_Y(h_1847_R);
            if (flag) {
                i = 15 - i;
            } else if (i > 0) {
                float f1 = f < (float)Math.PI ? 0.0f : (float)Math.PI * 2;
                f += (f1 - f) * 0.2f;
                i = Math.round((float)i * u_530_F.J_1907_R(f));
            }
            i = u_530_F.n_1700_B(i, 0, 15);
            if (state.R_4764_Y(P_4830_p) != i) {
                world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i), 3);
            }
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (player.V_537_k()) {
            if (worldIn.Y_259_p) {
                return m_3054_I.n_1700_B;
            }
            K_4074_S blockstate = (K_4074_S)state.n_1700_B(h_1847_R);
            worldIn.n_1700_B(pos, blockstate, 4);
            Y_2905_A.R_4764_Y(blockstate, worldIn, pos);
            return m_3054_I.J_1907_R;
        }
        return super.n_1700_B(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new DaylightDetectorBlockEntity();
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }
}


