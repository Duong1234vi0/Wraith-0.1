/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_2364_U;
import lightning.product.D_2530_r;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPattern;
import lightning.product.N_1077_C;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.BlockMaterialPredicate;
import lightning.product.U_3554_Q;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_91_Z;
import lightning.product.g_3049_G;
import lightning.product.DirectionProperty;
import lightning.product.BlockInWorld;
import lightning.product.q_4293_E;
import lightning.product.Material;
import lightning.product.t_5_h;
import lightning.product.v_3760_Q;

public class z_127_w
extends HorizontalDirectionalBlock
implements D_2530_r {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    @Nullable
    private BlockPattern h_1847_R;
    @Nullable
    private BlockPattern Q_4569_t;
    @Nullable
    private BlockPattern M_182_A;
    @Nullable
    private BlockPattern t_1786_h;
    private static final Predicate<K_4074_S> multiplayerClientSuggestionProvider = state -> state != null && (state.n_1700_B(a_3742_W.X_2048_Y) || state.n_1700_B(a_3742_W.l_2647_k));

    protected z_127_w(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(worldIn, pos);
        }
    }

    public boolean n_1700_B(T_1316_M reader, c_1514_x pos) {
        return this.J_1907_R().n_1700_B(reader, pos) != null || this.s_956_w().n_1700_B(reader, pos) != null;
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos) {
        block9: {
            BlockPattern.J_1907_R blockpattern$patternhelper;
            block8: {
                blockpattern$patternhelper = this.t_148_a().n_1700_B(world, pos);
                if (blockpattern$patternhelper == null) break block8;
                for (int i = 0; i < this.t_148_a().J_1907_R(); ++i) {
                    BlockInWorld cachedblockinfo = blockpattern$patternhelper.n_1700_B(0, i, 0);
                    world.n_1700_B(cachedblockinfo.G_564_y(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
                    world.R_4764_Y(2001, cachedblockinfo.G_564_y(), T_2915_h.s_956_w(cachedblockinfo.n_1700_B()));
                }
                N_1077_C snowgolementity = t_5_h.q_1982_R.n_1700_B(world);
                c_1514_x blockpos1 = blockpattern$patternhelper.n_1700_B(0, 2, 0).G_564_y();
                snowgolementity.J_1907_R((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.05, (double)blockpos1.getZ() + 0.5, 0.0f, 0.0f);
                world.a_(snowgolementity);
                for (B_4088_l serverplayerentity : world.n_1700_B(B_4088_l.class, snowgolementity.i_601_W().grow(5.0))) {
                    U_3554_Q.h_1847_R.n_1700_B(serverplayerentity, snowgolementity);
                }
                for (int l = 0; l < this.t_148_a().J_1907_R(); ++l) {
                    BlockInWorld cachedblockinfo3 = blockpattern$patternhelper.n_1700_B(0, l, 0);
                    world.n_1700_B(cachedblockinfo3.G_564_y(), a_3742_W.n_1700_B);
                }
                break block9;
            }
            blockpattern$patternhelper = this.Y_601_j().n_1700_B(world, pos);
            if (blockpattern$patternhelper == null) break block9;
            for (int j = 0; j < this.Y_601_j().R_4764_Y(); ++j) {
                for (int k = 0; k < this.Y_601_j().J_1907_R(); ++k) {
                    BlockInWorld cachedblockinfo2 = blockpattern$patternhelper.n_1700_B(j, k, 0);
                    world.n_1700_B(cachedblockinfo2.G_564_y(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
                    world.R_4764_Y(2001, cachedblockinfo2.G_564_y(), T_2915_h.s_956_w(cachedblockinfo2.n_1700_B()));
                }
            }
            c_1514_x blockpos = blockpattern$patternhelper.n_1700_B(1, 2, 0).G_564_y();
            D_2364_U irongolementity = t_5_h.v_4276_D.n_1700_B(world);
            irongolementity.Y_601_j(true);
            irongolementity.J_1907_R((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.05, (double)blockpos.getZ() + 0.5, 0.0f, 0.0f);
            world.a_(irongolementity);
            for (B_4088_l serverplayerentity1 : world.n_1700_B(B_4088_l.class, irongolementity.i_601_W().grow(5.0))) {
                U_3554_Q.h_1847_R.n_1700_B(serverplayerentity1, irongolementity);
            }
            for (int i1 = 0; i1 < this.Y_601_j().R_4764_Y(); ++i1) {
                for (int j1 = 0; j1 < this.Y_601_j().J_1907_R(); ++j1) {
                    BlockInWorld cachedblockinfo1 = blockpattern$patternhelper.n_1700_B(i1, j1, 0);
                    world.n_1700_B(cachedblockinfo1.G_564_y(), a_3742_W.n_1700_B);
                }
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().u_1723_Y());
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    private BlockPattern J_1907_R() {
        if (this.h_1847_R == null) {
            this.h_1847_R = e_91_Z.n_1700_B().n_1700_B(" ", "#", "#").n_1700_B('#', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_697_B))).J_1907_R();
        }
        return this.h_1847_R;
    }

    private BlockPattern t_148_a() {
        if (this.Q_4569_t == null) {
            this.Q_4569_t = e_91_Z.n_1700_B().n_1700_B("^", "#", "#").n_1700_B('^', BlockInWorld.n_1700_B(multiplayerClientSuggestionProvider)).n_1700_B('#', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.l_697_B))).J_1907_R();
        }
        return this.Q_4569_t;
    }

    private BlockPattern s_956_w() {
        if (this.M_182_A == null) {
            this.M_182_A = e_91_Z.n_1700_B().n_1700_B("~ ~", "###", "~#~").n_1700_B('#', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.H_1883_T))).n_1700_B('~', BlockInWorld.n_1700_B(BlockMaterialPredicate.n_1700_B(Material.n_1700_B))).J_1907_R();
        }
        return this.M_182_A;
    }

    private BlockPattern Y_601_j() {
        if (this.t_1786_h == null) {
            this.t_1786_h = e_91_Z.n_1700_B().n_1700_B("~^~", "###", "~#~").n_1700_B('^', BlockInWorld.n_1700_B(multiplayerClientSuggestionProvider)).n_1700_B('#', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.H_1883_T))).n_1700_B('~', BlockInWorld.n_1700_B(BlockMaterialPredicate.n_1700_B(Material.n_1700_B))).J_1907_R();
        }
        return this.t_1786_h;
    }
}


