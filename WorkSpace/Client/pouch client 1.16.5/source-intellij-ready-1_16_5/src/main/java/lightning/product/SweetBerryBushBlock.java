/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.t_5_h;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class SweetBerryBushBlock
extends BushBlock
implements BonemealableBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.r_715_M;
    private static final s_1395_c h_1847_R = T_2915_h.n_1700_B(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public SweetBerryBushBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(Items.D_265_n);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (state.R_4764_Y(P_4830_p) == 0) {
            return h_1847_R;
        }
        return state.R_4764_Y(P_4830_p) < 3 ? Q_4569_t : super.n_1700_B(state, worldIn, pos, context);
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) < 3;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i = state.R_4764_Y(P_4830_p);
        if (i < 3 && random.nextInt(5) == 0 && worldIn.n_1700_B(pos.up(), 0) >= 9) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i + 1), 2);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (entityIn instanceof r_4811_B && entityIn.f_4016_n() != t_5_h.A_4115_X && entityIn.f_4016_n() != t_5_h.P_1922_E) {
            entityIn.n_1700_B(state, new e_2866_D(0.8f, 0.75, 0.8f));
            if (!(worldIn.Y_259_p || state.R_4764_Y(P_4830_p) <= 0 || entityIn.q_1982_R == entityIn.O_3598_v() && entityIn.w_612_n == entityIn.l_2647_k())) {
                double d0 = Math.abs(entityIn.O_3598_v() - entityIn.q_1982_R);
                double d1 = Math.abs(entityIn.l_2647_k() - entityIn.w_612_n);
                if (d0 >= (double)0.003f || d1 >= (double)0.003f) {
                    entityIn.n_1700_B(P_11_z.Y_259_p, 1.0f);
                }
            }
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        boolean flag;
        int i = state.R_4764_Y(P_4830_p);
        boolean bl = flag = i == 3;
        if (!flag && player.R_4764_Y(handIn).J_1907_R() == Items.r_1970_q) {
            return m_3054_I.R_4764_Y;
        }
        if (i > 1) {
            int j = 1 + worldIn.w_1457_N.nextInt(2);
            SweetBerryBushBlock.n_1700_B(worldIn, pos, new Z_1993_T(Items.D_265_n, j + (flag ? 1 : 0)));
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.l_683_e, D_38_f.P_1922_E, 1.0f, 0.8f + worldIn.w_1457_N.nextFloat() * 0.4f);
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 1), 2);
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return super.n_1700_B(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return state.R_4764_Y(P_4830_p) < 3;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        int i = Math.min(3, state.R_4764_Y(P_4830_p) + 1);
        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i), 2);
    }
}


