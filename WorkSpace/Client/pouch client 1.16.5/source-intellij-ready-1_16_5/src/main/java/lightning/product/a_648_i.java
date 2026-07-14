/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2FloatMap
 *  it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.N_1216_z;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.j_2011_l;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.WorldlyContainer;
import lightning.product.q_1803_e;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class a_648_i
extends T_2915_h
implements j_2011_l {
    public static final g_88_D P_4830_p = BlockStateProperties.Ops;
    public static final Object2FloatMap<q_1803_e> h_1847_R = new Object2FloatOpenHashMap();
    private static final s_1395_c Q_4569_t = x_268_Y.J_1907_R();
    private static final s_1395_c[] M_182_A = j_3341_s.n_1700_B(new s_1395_c[9], (T shapes) -> {
        for (int i = 0; i < 8; ++i) {
            shapes[i] = x_268_Y.n_1700_B(Q_4569_t, T_2915_h.n_1700_B(2.0, Math.max(2, 1 + i * 2), 2.0, 14.0, 16.0, 14.0), BooleanOp.P_1922_E);
        }
        shapes[8] = shapes[7];
    });

    public static void J_1907_R() {
        h_1847_R.defaultReturnValue(-1.0f);
        float f = 0.3f;
        float f1 = 0.5f;
        float f2 = 0.65f;
        float f3 = 0.85f;
        float f4 = 1.0f;
        a_648_i.n_1700_B(0.3f, Items.t_4219_U);
        a_648_i.n_1700_B(0.3f, Items.k_3961_g);
        a_648_i.n_1700_B(0.3f, Items.Ops);
        a_648_i.n_1700_B(0.3f, Items.PlayerInfo);
        a_648_i.n_1700_B(0.3f, Items.V_1446_Y);
        a_648_i.n_1700_B(0.3f, Items.h_4320_q);
        a_648_i.n_1700_B(0.3f, Items.k_2293_S);
        a_648_i.n_1700_B(0.3f, Items.q_2307_F);
        a_648_i.n_1700_B(0.3f, Items.Z_875_P);
        a_648_i.n_1700_B(0.3f, Items.c_3005_b);
        a_648_i.n_1700_B(0.3f, Items.H_2857_Y);
        a_648_i.n_1700_B(0.3f, Items.A_4115_X);
        a_648_i.n_1700_B(0.3f, Items.MushroomBlock);
        a_648_i.n_1700_B(0.3f, Items.MinMaxBounds);
        a_648_i.n_1700_B(0.3f, Items.F_1410_V);
        a_648_i.n_1700_B(0.3f, Items.y_2772_m);
        a_648_i.n_1700_B(0.3f, Items.y_2836_h);
        a_648_i.n_1700_B(0.3f, Items.WrappedMinMaxBounds);
        a_648_i.n_1700_B(0.3f, Items.F_2624_D);
        a_648_i.n_1700_B(0.3f, Items.D_265_n);
        a_648_i.n_1700_B(0.3f, Items.G_4691_Q);
        a_648_i.n_1700_B(0.5f, Items.f_4705_f);
        a_648_i.n_1700_B(0.5f, Items.g_4841_c);
        a_648_i.n_1700_B(0.5f, Items.T_437_o);
        a_648_i.n_1700_B(0.5f, Items.RealmsPersistence);
        a_648_i.n_1700_B(0.5f, Items.B_1146_q);
        a_648_i.n_1700_B(0.5f, Items.f_3449_S);
        a_648_i.n_1700_B(0.5f, Items.u_55_V);
        a_648_i.n_1700_B(0.5f, Items.JsonUtils);
        a_648_i.n_1700_B(0.5f, Items.B_368_w);
        a_648_i.n_1700_B(0.65f, Items.RealmsDefaultUncaughtExceptionHandler);
        a_648_i.n_1700_B(0.65f, Items.l_2995_s);
        a_648_i.n_1700_B(0.65f, Items.m_891_U);
        a_648_i.n_1700_B(0.65f, Items.T_2971_J);
        a_648_i.n_1700_B(0.65f, Items.k_3129_Y);
        a_648_i.n_1700_B(0.65f, Items.E_738_L);
        a_648_i.n_1700_B(0.65f, Items.s_3401_U);
        a_648_i.n_1700_B(0.65f, Items.BaseCoralWallFanBlock);
        a_648_i.n_1700_B(0.65f, Items.M_712_N);
        a_648_i.n_1700_B(0.65f, Items.l_683_e);
        a_648_i.n_1700_B(0.65f, Items.V_3441_j);
        a_648_i.n_1700_B(0.65f, Items.RealmsSettingsScreen);
        a_648_i.n_1700_B(0.65f, Items.f_1043_S);
        a_648_i.n_1700_B(0.65f, Items.J_2061_p);
        a_648_i.n_1700_B(0.65f, Items.F_4247_a);
        a_648_i.n_1700_B(0.65f, Items.J_739_q);
        a_648_i.n_1700_B(0.65f, Items.g_1096_r);
        a_648_i.n_1700_B(0.65f, Items.C_1162_e);
        a_648_i.n_1700_B(0.65f, Items.D_4361_a);
        a_648_i.n_1700_B(0.65f, Items.D_3746_J);
        a_648_i.n_1700_B(0.65f, Items.C_290_v);
        a_648_i.n_1700_B(0.65f, Items.w_728_N);
        a_648_i.n_1700_B(0.65f, Items.J_4256_G);
        a_648_i.n_1700_B(0.65f, Items.RealmsLongConfirmationScreen);
        a_648_i.n_1700_B(0.65f, Items.RealmsLongRunningMcoTaskScreen);
        a_648_i.n_1700_B(0.65f, Items.i_2993_w);
        a_648_i.n_1700_B(0.65f, Items.RealmsParentalConsentScreen);
        a_648_i.n_1700_B(0.65f, Items.O_2151_c);
        a_648_i.n_1700_B(0.65f, Items.s_1671_u);
        a_648_i.n_1700_B(0.65f, Items.RealmsResetNormalWorldScreen);
        a_648_i.n_1700_B(0.65f, Items.C_3538_G);
        a_648_i.n_1700_B(0.65f, Items.A_3959_N);
        a_648_i.n_1700_B(0.65f, Items.G_424_k);
        a_648_i.n_1700_B(0.65f, Items.S_4022_R);
        a_648_i.n_1700_B(0.65f, Items.E_2115_e);
        a_648_i.n_1700_B(0.65f, Items.W_1707_M);
        a_648_i.n_1700_B(0.65f, Items.I_4683_a);
        a_648_i.n_1700_B(0.65f, Items.n_2689_l);
        a_648_i.n_1700_B(0.65f, Items.N_4890_q);
        a_648_i.n_1700_B(0.85f, Items.AntiSurround);
        a_648_i.n_1700_B(0.85f, Items.E_3343_g);
        a_648_i.n_1700_B(0.85f, Items.i_789_Q);
        a_648_i.n_1700_B(0.85f, Items.TrashTalk);
        a_648_i.n_1700_B(0.85f, Items.UseTracker);
        a_648_i.n_1700_B(0.85f, Items.m_3828_C);
        a_648_i.n_1700_B(0.85f, Items.DirectionalBlock);
        a_648_i.n_1700_B(0.85f, Items.B_1335_M);
        a_648_i.n_1700_B(1.0f, Items.I_3736_z);
        a_648_i.n_1700_B(1.0f, Items.y_2012_u);
    }

    private static void n_1700_B(float chance, q_1803_e itemIn) {
        h_1847_R.put((Object)itemIn.u_1723_Y(), chance);
    }

    public a_648_i(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    public static void n_1700_B(b_4507_u world, c_1514_x pos, boolean success) {
        K_4074_S blockstate = world.getBlockState(pos);
        world.n_1700_B(pos.getX(), (double)pos.getY(), (double)pos.getZ(), success ? SoundEvents.l_4088_R : SoundEvents.O_2934_T, D_38_f.P_1922_E, 1.0f, 1.0f, false);
        double d0 = blockstate.s_956_w(world, pos).n_1700_B(b_257_Y.n_1700_B.J_1907_R, 0.5, 0.5) + 0.03125;
        double d1 = 0.13125f;
        double d2 = 0.7375f;
        Random random = world.e_4240_b();
        for (int i = 0; i < 10; ++i) {
            double d3 = random.nextGaussian() * 0.02;
            double d4 = random.nextGaussian() * 0.02;
            double d5 = random.nextGaussian() * 0.02;
            world.n_1700_B(ParticleTypes.x_607_J, (double)pos.getX() + (double)0.13125f + (double)0.7375f * (double)random.nextFloat(), (double)pos.getY() + d0 + (double)random.nextFloat() * (1.0 - d0), (double)pos.getZ() + (double)0.13125f + (double)0.7375f * (double)random.nextFloat(), d3, d4, d5);
        }
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return M_182_A[state.R_4764_Y(P_4830_p)];
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return Q_4569_t;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return M_182_A[0];
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (state.R_4764_Y(P_4830_p) == 7) {
            worldIn.u_2550_I().n_1700_B(pos, state.J_1907_R(), 20);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        int i = state.R_4764_Y(P_4830_p);
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        if (i < 8 && h_1847_R.containsKey((Object)itemstack.J_1907_R())) {
            if (i < 7 && !worldIn.Y_259_p) {
                K_4074_S blockstate = a_648_i.n_1700_B(state, (LevelAccessor)worldIn, pos, itemstack);
                worldIn.R_4764_Y(1500, pos, state != blockstate ? 1 : 0);
                if (!player.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (i == 8) {
            a_648_i.R_4764_Y(state, worldIn, pos);
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public static K_4074_S n_1700_B(K_4074_S state, e_3591_l world, Z_1993_T stack, c_1514_x pos) {
        int i = state.R_4764_Y(P_4830_p);
        if (i < 7 && h_1847_R.containsKey((Object)stack.J_1907_R())) {
            K_4074_S blockstate = a_648_i.n_1700_B(state, (LevelAccessor)world, pos, stack);
            stack.v_4262_N(1);
            return blockstate;
        }
        return state;
    }

    public static K_4074_S R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        if (!world.Y_259_p) {
            float f = 0.7f;
            double d0 = (double)(world.w_1457_N.nextFloat() * 0.7f) + (double)0.15f;
            double d1 = (double)(world.w_1457_N.nextFloat() * 0.7f) + 0.06000000238418579 + 0.6;
            double d2 = (double)(world.w_1457_N.nextFloat() * 0.7f) + (double)0.15f;
            n_1494_c itementity = new n_1494_c(world, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, new Z_1993_T(Items.r_1970_q));
            itementity.t_148_a();
            world.a_(itementity);
        }
        K_4074_S blockstate = a_648_i.R_4764_Y(state, (LevelAccessor)world, pos);
        world.n_1700_B((a_3913_L)null, pos, SoundEvents.O_1309_Q, D_38_f.P_1922_E, 1.0f, 1.0f);
        return blockstate;
    }

    private static K_4074_S R_4764_Y(K_4074_S state, LevelAccessor world, c_1514_x pos) {
        K_4074_S blockstate = (K_4074_S)state.n_1700_B(P_4830_p, 0);
        world.n_1700_B(pos, blockstate, 3);
        return blockstate;
    }

    private static K_4074_S n_1700_B(K_4074_S state, LevelAccessor world, c_1514_x pos, Z_1993_T stack) {
        int i = state.R_4764_Y(P_4830_p);
        float f = h_1847_R.getFloat((Object)stack.J_1907_R());
        if (!(i == 0 && f > 0.0f || world.e_4240_b().nextDouble() < (double)f)) {
            return state;
        }
        int j = i + 1;
        K_4074_S blockstate = (K_4074_S)state.n_1700_B(P_4830_p, j);
        world.n_1700_B(pos, blockstate, 3);
        if (j == 7) {
            world.u_2550_I().n_1700_B(pos, state.J_1907_R(), 20);
        }
        return blockstate;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(P_4830_p) == 7) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p), 3);
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.Z_735_d, D_38_f.P_1922_E, 1.0f, 1.0f);
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

    @Override
    public WorldlyContainer n_1700_B(K_4074_S state, LevelAccessor world, c_1514_x pos) {
        int i = state.R_4764_Y(P_4830_p);
        if (i == 8) {
            return new J_1907_R(state, world, pos, new Z_1993_T(Items.r_1970_q));
        }
        return (WorldlyContainer)((Object)(i < 7 ? new R_4764_Y(state, world, pos) : new n_1700_B()));
    }

    static class J_1907_R
    extends N_1216_z
    implements WorldlyContainer {
        private final K_4074_S n_1700_B;
        private final LevelAccessor J_1907_R;
        private final c_1514_x R_4764_Y;
        private boolean G_564_y;

        public J_1907_R(K_4074_S state, LevelAccessor world, c_1514_x pos, Z_1993_T stack) {
            super(stack);
            this.n_1700_B = state;
            this.J_1907_R = world;
            this.R_4764_Y = pos;
        }

        @Override
        public int J_() {
            return 1;
        }

        @Override
        public int[] n_1700_B(b_257_Y side) {
            int[] nArray;
            if (side == b_257_Y.n_1700_B) {
                int[] nArray2 = new int[1];
                nArray = nArray2;
                nArray2[0] = 0;
            } else {
                nArray = new int[]{};
            }
            return nArray;
        }

        @Override
        public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
            return false;
        }

        @Override
        public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
            return !this.G_564_y && direction == b_257_Y.n_1700_B && stack.J_1907_R() == Items.r_1970_q;
        }

        @Override
        public void J_1907_R() {
            a_648_i.R_4764_Y(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
            this.G_564_y = true;
        }
    }

    static class R_4764_Y
    extends N_1216_z
    implements WorldlyContainer {
        private final K_4074_S n_1700_B;
        private final LevelAccessor J_1907_R;
        private final c_1514_x R_4764_Y;
        private boolean G_564_y;

        public R_4764_Y(K_4074_S state, LevelAccessor world, c_1514_x pos) {
            super(1);
            this.n_1700_B = state;
            this.J_1907_R = world;
            this.R_4764_Y = pos;
        }

        @Override
        public int J_() {
            return 1;
        }

        @Override
        public int[] n_1700_B(b_257_Y side) {
            int[] nArray;
            if (side == b_257_Y.J_1907_R) {
                int[] nArray2 = new int[1];
                nArray = nArray2;
                nArray2[0] = 0;
            } else {
                nArray = new int[]{};
            }
            return nArray;
        }

        @Override
        public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
            return !this.G_564_y && direction == b_257_Y.J_1907_R && h_1847_R.containsKey((Object)itemStackIn.J_1907_R());
        }

        @Override
        public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
            return false;
        }

        @Override
        public void J_1907_R() {
            Z_1993_T itemstack = this.s_956_w(0);
            if (!itemstack.n_1700_B()) {
                this.G_564_y = true;
                K_4074_S blockstate = a_648_i.n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y, itemstack);
                this.J_1907_R.R_4764_Y(1500, this.R_4764_Y, blockstate != this.n_1700_B ? 1 : 0);
                this.u_2550_I(0);
            }
        }
    }

    static class n_1700_B
    extends N_1216_z
    implements WorldlyContainer {
        public n_1700_B() {
            super(0);
        }

        @Override
        public int[] n_1700_B(b_257_Y side) {
            return new int[0];
        }

        @Override
        public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
            return false;
        }

        @Override
        public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
            return false;
        }
    }
}



