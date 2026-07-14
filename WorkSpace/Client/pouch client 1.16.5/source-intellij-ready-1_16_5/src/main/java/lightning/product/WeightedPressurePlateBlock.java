/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.w_4067_V;

public class WeightedPressurePlateBlock
extends w_4067_V {
    public static final g_88_D M_182_A = BlockStateProperties.q_1982_R;
    private final int t_1786_h;

    protected WeightedPressurePlateBlock(int maxWeight, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(M_182_A, 0));
        this.t_1786_h = maxWeight;
    }

    @Override
    protected int J_1907_R(b_4507_u worldIn, c_1514_x pos) {
        int i = Math.min(worldIn.n_1700_B(N_4263_v.class, Q_4569_t.offset(pos)).size(), this.t_1786_h);
        if (i > 0) {
            float f = (float)Math.min(this.t_1786_h, i) / (float)this.t_1786_h;
            return u_530_F.u_1723_Y(f * 15.0f);
        }
        return 0;
    }

    @Override
    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
        worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.BlockFly, D_38_f.P_1922_E, 0.3f, 0.90000004f);
    }

    @Override
    protected void J_1907_R(LevelAccessor worldIn, c_1514_x pos) {
        worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.Blink, D_38_f.P_1922_E, 0.3f, 0.75f);
    }

    @Override
    protected int v_4262_N(K_4074_S state) {
        return state.R_4764_Y(M_182_A);
    }

    @Override
    protected K_4074_S n_1700_B(K_4074_S state, int strength) {
        return (K_4074_S)state.n_1700_B(M_182_A, strength);
    }

    @Override
    protected int J_1907_R() {
        return 10;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{M_182_A});
    }
}



