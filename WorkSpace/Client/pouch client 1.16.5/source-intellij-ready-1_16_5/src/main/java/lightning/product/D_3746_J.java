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
import lightning.product.CropBlock;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.StemGrownBlock;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;

public class D_3746_J
extends BushBlock
implements BonemealableBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.i_1637_u;
    protected static final s_1395_c[] h_1847_R = new s_1395_c[]{T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 2.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 4.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 6.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 8.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 10.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 12.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 14.0, 9.0), T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)};
    private final StemGrownBlock Q_4569_t;

    protected D_3746_J(StemGrownBlock crop, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Q_4569_t = crop;
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R[state.R_4764_Y(P_4830_p)];
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(a_3742_W.Z_735_d);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        float f;
        if (worldIn.n_1700_B(pos, 0) >= 9 && random.nextInt((int)(25.0f / (f = CropBlock.n_1700_B(this, worldIn, pos))) + 1) == 0) {
            int i = state.R_4764_Y(P_4830_p);
            if (i < 7) {
                state = (K_4074_S)state.n_1700_B(P_4830_p, i + 1);
                worldIn.n_1700_B(pos, state, 2);
            } else {
                b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(random);
                c_1514_x blockpos = pos.offset(direction);
                K_4074_S blockstate = worldIn.getBlockState(blockpos.down());
                if (worldIn.getBlockState(blockpos).v_4262_N() && (blockstate.n_1700_B(a_3742_W.Z_735_d) || blockstate.n_1700_B(a_3742_W.s_956_w) || blockstate.n_1700_B(a_3742_W.u_2550_I) || blockstate.n_1700_B(a_3742_W.M_588_G) || blockstate.n_1700_B(a_3742_W.t_148_a))) {
                    worldIn.J_1907_R(blockpos, this.Q_4569_t.multiplayerClientSuggestionProvider());
                    worldIn.J_1907_R(pos, (K_4074_S)this.Q_4569_t.t_148_a().multiplayerClientSuggestionProvider().n_1700_B(HorizontalDirectionalBlock.w_612_n, direction));
                }
            }
        }
    }

    @Nullable
    protected q_1613_l J_1907_R() {
        if (this.Q_4569_t == a_3742_W.A_3244_K) {
            return Items.WrappedMinMaxBounds;
        }
        return this.Q_4569_t == a_3742_W.E_3343_g ? Items.y_2836_h : null;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        q_1613_l item = this.J_1907_R();
        return item == null ? Z_1993_T.J_1907_R : new Z_1993_T(item);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return state.R_4764_Y(P_4830_p) != 7;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        int i = Math.min(7, state.R_4764_Y(P_4830_p) + u_530_F.n_1700_B(worldIn.w_1457_N, 2, 5));
        K_4074_S blockstate = (K_4074_S)state.n_1700_B(P_4830_p, i);
        worldIn.n_1700_B(pos, blockstate, 2);
        if (i == 7) {
            blockstate.J_1907_R(worldIn, pos, worldIn.w_1457_N);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    public StemGrownBlock t_148_a() {
        return this.Q_4569_t;
    }
}


