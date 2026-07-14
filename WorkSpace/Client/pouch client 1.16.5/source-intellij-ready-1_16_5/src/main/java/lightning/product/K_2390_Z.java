/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.Potions;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.DyeableLeatherItem;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_4889_F;
import lightning.product.s_1395_c;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.x_2414_j;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class K_2390_Z
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.k_3961_g;
    private static final s_1395_c Q_4569_t = K_2390_Z.n_1700_B(2.0, 4.0, 2.0, 14.0, 16.0, 14.0);
    protected static final s_1395_c h_1847_R = x_268_Y.n_1700_B(x_268_Y.J_1907_R(), x_268_Y.n_1700_B(K_2390_Z.n_1700_B(0.0, 0.0, 4.0, 16.0, 3.0, 12.0), K_2390_Z.n_1700_B(4.0, 0.0, 0.0, 12.0, 3.0, 16.0), K_2390_Z.n_1700_B(2.0, 0.0, 2.0, 14.0, 3.0, 14.0), Q_4569_t), BooleanOp.P_1922_E);

    public K_2390_Z(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return Q_4569_t;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        int i = state.R_4764_Y(P_4830_p);
        float f = (float)pos.getY() + (6.0f + (float)(3 * i)) / 16.0f;
        if (!worldIn.Y_259_p && entityIn.RealmsPersistence() && i > 0 && entityIn.X_2960_b() <= (double)f) {
            entityIn.RealmsServerPing();
            this.n_1700_B(worldIn, pos, state, i - 1);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        DyeableLeatherItem idyeablearmoritem;
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        if (itemstack.n_1700_B()) {
            return m_3054_I.R_4764_Y;
        }
        int i = state.R_4764_Y(P_4830_p);
        q_1613_l item = itemstack.J_1907_R();
        if (item == Items.W_2770_z) {
            if (i < 3 && !worldIn.Y_259_p) {
                if (!player.C_415_h.G_564_y) {
                    player.n_1700_B(handIn, new Z_1993_T(Items.G_1539_D));
                }
                player.J_1907_R(Stats.X_933_l);
                this.n_1700_B(worldIn, pos, state, 3);
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.J_4256_G, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (item == Items.G_1539_D) {
            if (i == 3 && !worldIn.Y_259_p) {
                if (!player.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                    if (itemstack.n_1700_B()) {
                        player.n_1700_B(handIn, new Z_1993_T(Items.W_2770_z));
                    } else if (!player.l_1268_F.P_1922_E(new Z_1993_T(Items.W_2770_z))) {
                        player.n_1700_B(new Z_1993_T(Items.W_2770_z), false);
                    }
                }
                player.J_1907_R(Stats.Z_976_R);
                this.n_1700_B(worldIn, pos, state, 0);
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.i_2993_w, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (item == Items.Y_3588_g) {
            if (i > 0 && !worldIn.Y_259_p) {
                if (!player.C_415_h.G_564_y) {
                    Z_1993_T itemstack4 = L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), Potions.J_1907_R);
                    player.J_1907_R(Stats.Z_976_R);
                    itemstack.v_4262_N(1);
                    if (itemstack.n_1700_B()) {
                        player.n_1700_B(handIn, itemstack4);
                    } else if (!player.l_1268_F.P_1922_E(itemstack4)) {
                        player.n_1700_B(itemstack4, false);
                    } else if (player instanceof B_4088_l) {
                        ((B_4088_l)player).n_1700_B(player.o_1800_r);
                    }
                }
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.c_132_F, D_38_f.P_1922_E, 1.0f, 1.0f);
                this.n_1700_B(worldIn, pos, state, i - 1);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (item == Items.j_2461_G && L_1875_m.G_564_y(itemstack) == Potions.J_1907_R) {
            if (i < 3 && !worldIn.Y_259_p) {
                if (!player.C_415_h.G_564_y) {
                    Z_1993_T itemstack3 = new Z_1993_T(Items.Y_3588_g);
                    player.J_1907_R(Stats.Z_976_R);
                    player.n_1700_B(handIn, itemstack3);
                    if (player instanceof B_4088_l) {
                        ((B_4088_l)player).n_1700_B(player.o_1800_r);
                    }
                }
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.M_2677_i, D_38_f.P_1922_E, 1.0f, 1.0f);
                this.n_1700_B(worldIn, pos, state, i + 1);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (i > 0 && item instanceof DyeableLeatherItem && (idyeablearmoritem = (DyeableLeatherItem)((Object)item)).c_(itemstack) && !worldIn.Y_259_p) {
            idyeablearmoritem.e_(itemstack);
            this.n_1700_B(worldIn, pos, state, i - 1);
            player.J_1907_R(Stats.H_1990_U);
            return m_3054_I.n_1700_B;
        }
        if (i > 0 && item instanceof x_2414_j) {
            if (r_4889_F.J_1907_R(itemstack) > 0 && !worldIn.Y_259_p) {
                Z_1993_T itemstack2 = itemstack.t_148_a();
                itemstack2.P_1922_E(1);
                r_4889_F.R_4764_Y(itemstack2);
                player.J_1907_R(Stats.N_2525_X);
                if (!player.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                    this.n_1700_B(worldIn, pos, state, i - 1);
                }
                if (itemstack.n_1700_B()) {
                    player.n_1700_B(handIn, itemstack2);
                } else if (!player.l_1268_F.P_1922_E(itemstack2)) {
                    player.n_1700_B(itemstack2, false);
                } else if (player instanceof B_4088_l) {
                    ((B_4088_l)player).n_1700_B(player.o_1800_r);
                }
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (i > 0 && item instanceof v_1669_V) {
            T_2915_h block = ((v_1669_V)item).v_4262_N();
            if (block instanceof Y_3462_U && !worldIn.v_4276_D()) {
                Z_1993_T itemstack1 = new Z_1993_T(a_3742_W.k_1052_R, 1);
                if (itemstack.h_1847_R()) {
                    itemstack1.R_4764_Y(itemstack.Q_4569_t().v_4262_N());
                }
                player.n_1700_B(handIn, itemstack1);
                this.n_1700_B(worldIn, pos, state, i - 1);
                player.J_1907_R(Stats.c_4037_x);
                return m_3054_I.n_1700_B;
            }
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, int level) {
        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, u_530_F.n_1700_B(level, 0, 3)), 2);
        worldIn.R_4764_Y(pos, this);
    }

    @Override
    public void R_4764_Y(b_4507_u worldIn, c_1514_x pos) {
        K_4074_S blockstate;
        float f;
        if (worldIn.w_1457_N.nextInt(20) == 1 && !((f = worldIn.P_1922_E(pos).n_1700_B(pos)) < 0.15f) && (blockstate = worldIn.getBlockState(pos)).R_4764_Y(P_4830_p) < 3) {
            worldIn.n_1700_B(pos, (K_4074_S)blockstate.n_1700_B(P_4830_p), 2);
        }
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return blockState.R_4764_Y(P_4830_p);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


