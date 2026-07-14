/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.F_4355_q;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Bat;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.i_2154_H;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;

public class L_2467_I
extends T_2915_h {
    private static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(3.0, 0.0, 3.0, 12.0, 7.0, 12.0);
    private static final s_1395_c M_182_A = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);
    public static final g_88_D P_4830_p = BlockStateProperties.e_1992_r;
    public static final g_88_D h_1847_R = BlockStateProperties.UploadStatus;

    public L_2467_I(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0)).n_1700_B(h_1847_R, 1));
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        this.n_1700_B(worldIn, pos, entityIn, 100);
        super.n_1700_B(worldIn, pos, entityIn);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        if (!(entityIn instanceof F_4355_q)) {
            this.n_1700_B(worldIn, pos, entityIn, 3);
        }
        super.n_1700_B(worldIn, pos, entityIn, fallDistance);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v trampler, int chances) {
        K_4074_S blockstate;
        if (this.n_1700_B(worldIn, trampler) && !worldIn.Y_259_p && worldIn.w_1457_N.nextInt(chances) == 0 && (blockstate = worldIn.getBlockState(pos)).n_1700_B(a_3742_W.d_560_A)) {
            this.n_1700_B(worldIn, pos, blockstate);
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.FrostedIceBlock, D_38_f.P_1922_E, 0.7f, 0.9f + worldIn.w_1457_N.nextFloat() * 0.2f);
        int i = state.R_4764_Y(h_1847_R);
        if (i <= 1) {
            worldIn.J_1907_R(pos, false);
        } else {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, i - 1), 2);
            worldIn.R_4764_Y(2001, pos, T_2915_h.s_956_w(state));
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (this.n_1700_B(worldIn) && L_2467_I.n_1700_B(worldIn, pos)) {
            int i = state.R_4764_Y(P_4830_p);
            if (i < 2) {
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.FungusBlock, D_38_f.P_1922_E, 0.7f, 0.9f + random.nextFloat() * 0.2f);
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i + 1), 2);
            } else {
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.A_3138_X, D_38_f.P_1922_E, 0.7f, 0.9f + random.nextFloat() * 0.2f);
                worldIn.n_1700_B(pos, false);
                for (int j = 0; j < state.R_4764_Y(h_1847_R); ++j) {
                    worldIn.R_4764_Y(2001, pos, T_2915_h.s_956_w(state));
                    t_4149_i turtleentity = t_5_h.l_4537_E.n_1700_B(worldIn);
                    turtleentity.b_(-24000);
                    turtleentity.v_4262_N(pos);
                    turtleentity.J_1907_R((double)pos.getX() + 0.3 + (double)j * 0.2, pos.getY(), (double)pos.getZ() + 0.3, 0.0f, 0.0f);
                    worldIn.a_(turtleentity);
                }
            }
        }
    }

    public static boolean n_1700_B(BlockGetter reader, c_1514_x blockReader) {
        return L_2467_I.J_1907_R(reader, blockReader.down());
    }

    public static boolean J_1907_R(BlockGetter reader, c_1514_x pos) {
        return reader.getBlockState(pos).n_1700_B(BlockTags.A_4115_X);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (L_2467_I.n_1700_B(worldIn, pos) && !worldIn.Y_259_p) {
            worldIn.R_4764_Y(2005, pos, 0);
        }
    }

    private boolean n_1700_B(b_4507_u worldIn) {
        float f = worldIn.G_564_y(1.0f);
        if ((double)f < 0.69 && (double)f > 0.65) {
            return true;
        }
        return worldIn.w_1457_N.nextInt(500) == 0;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, a_3913_L player, c_1514_x pos, K_4074_S state, @Nullable i_2154_H te, Z_1993_T stack) {
        super.n_1700_B(worldIn, player, pos, state, te, stack);
        this.n_1700_B(worldIn, pos, state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        return useContext.getItem().J_1907_R() == this.u_1723_Y() && state.R_4764_Y(h_1847_R) < 4 ? true : super.n_1700_B(state, useContext);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos());
        return blockstate.n_1700_B(this) ? (K_4074_S)blockstate.n_1700_B(h_1847_R, Math.min(4, blockstate.R_4764_Y(h_1847_R) + 1)) : super.n_1700_B(context);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return state.R_4764_Y(h_1847_R) > 1 ? M_182_A : Q_4569_t;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    private boolean n_1700_B(b_4507_u worldIn, N_4263_v trampler) {
        if (!(trampler instanceof t_4149_i) && !(trampler instanceof Bat)) {
            if (!(trampler instanceof r_4811_B)) {
                return false;
            }
            return trampler instanceof a_3913_L || worldIn.H_1990_U().J_1907_R(A_2352_Z.J_1907_R);
        }
        return false;
    }
}


