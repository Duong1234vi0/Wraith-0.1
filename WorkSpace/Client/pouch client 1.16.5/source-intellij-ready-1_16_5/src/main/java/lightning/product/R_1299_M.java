/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.FlyingMoveControl;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.R_4053_F;
import lightning.product.SitWhenOrderedToGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.FollowMobGoal;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Y_4462_y;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.FlyingPathNavigation;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_4739_a;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.k_2610_C;
import lightning.product.ShoulderRidingEntity;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.w_3686_I;
import lightning.product.x_1688_C;

public class R_1299_M
extends ShoulderRidingEntity
implements R_4053_F {
    private static final h_256_u<Integer> Y_601_j = C_4114_x.n_1700_B(R_1299_M.class, EntityDataSerializers.J_1907_R);
    private static final Predicate<Z_530_i> Y_259_p = new Predicate<Z_530_i>(){

        public boolean n_1700_B(@Nullable Z_530_i p_test_1_) {
            return p_test_1_ != null && k_2293_S.containsKey(p_test_1_.f_4016_n());
        }

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((Z_530_i)object);
        }
    };
    private static final q_1613_l Q_2552_b = Items.B_1335_M;
    private static final Set<q_1613_l> C_2741_M = Sets.newHashSet((Object[])new q_1613_l[]{Items.G_4691_Q, Items.y_2836_h, Items.WrappedMinMaxBounds, Items.MushroomBlock});
    private static final Map<t_5_h<?>, SoundEvent> k_2293_S = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_200609_0_) -> {
        p_200609_0_.put(t_5_h.u_1723_Y, SoundEvents.S_1165_y);
        p_200609_0_.put(t_5_h.t_148_a, SoundEvents.W_2756_H);
        p_200609_0_.put(t_5_h.P_4830_p, SoundEvents.E_738_L);
        p_200609_0_.put(t_5_h.t_1786_h, SoundEvents.R_1796_s);
        p_200609_0_.put(t_5_h.multiplayerClientSuggestionProvider, SoundEvents.g_24_p);
        p_200609_0_.put(t_5_h.Y_601_j, SoundEvents.T_797_O);
        p_200609_0_.put(t_5_h.Q_2552_b, SoundEvents.d_560_A);
        p_200609_0_.put(t_5_h.C_2741_M, SoundEvents.k_2273_q);
        p_200609_0_.put(t_5_h.Y_1740_V, SoundEvents.D_1621_L);
        p_200609_0_.put(t_5_h.x_607_J, SoundEvents.ServerHandshakePacketListener);
        p_200609_0_.put(t_5_h.e_4240_b, SoundEvents.q_4124_m);
        p_200609_0_.put(t_5_h.d_2427_y, SoundEvents.m_396_H);
        p_200609_0_.put(t_5_h.z_1737_N, SoundEvents.S_234_U);
        p_200609_0_.put(t_5_h.B_1668_F, SoundEvents.B_707_U);
        p_200609_0_.put(t_5_h.r_715_M, SoundEvents.N_1833_W);
        p_200609_0_.put(t_5_h.i_1637_u, SoundEvents.a_2727_J);
        p_200609_0_.put(t_5_h.Ping, SoundEvents.D_1410_T);
        p_200609_0_.put(t_5_h.p_178_J, SoundEvents.Y_3623_f);
        p_200609_0_.put(t_5_h.e_1992_r, SoundEvents.z_4066_l);
        p_200609_0_.put(t_5_h.Ops, SoundEvents.Y_4293_u);
        p_200609_0_.put(t_5_h.t_4219_U, SoundEvents.z_283_n);
        p_200609_0_.put(t_5_h.V_1446_Y, SoundEvents.a_1887_j);
        p_200609_0_.put(t_5_h.V_1225_t, SoundEvents.n_2412_y);
        p_200609_0_.put(t_5_h.RealmsServerPing, SoundEvents.W_2756_H);
        p_200609_0_.put(t_5_h.M_1641_O, SoundEvents.i_1894_C);
        p_200609_0_.put(t_5_h.F_2624_D, SoundEvents.u_4724_w);
        p_200609_0_.put(t_5_h.y_1700_S, SoundEvents.H_1952_g);
        p_200609_0_.put(t_5_h.RetryCallException, SoundEvents.w_2152_d);
        p_200609_0_.put(t_5_h.r_3651_U, SoundEvents.Z_1243_X);
        p_200609_0_.put(t_5_h.RowButton, SoundEvents.r_976_u);
        p_200609_0_.put(t_5_h.S_980_j, SoundEvents.E_390_U);
        p_200609_0_.put(t_5_h.R_3077_Z, SoundEvents.Z_256_c);
        p_200609_0_.put(t_5_h.M_2677_i, SoundEvents.N_2592_G);
    });
    public float h_1847_R;
    public float Q_4569_t;
    public float M_182_A;
    public float t_1786_h;
    private float q_2307_F = 1.0f;
    private boolean Z_875_P;
    private c_1514_x c_3005_b;

    public R_1299_M(t_5_h<? extends R_1299_M> type, b_4507_u worldIn) {
        super((t_5_h<? extends ShoulderRidingEntity>)type, worldIn);
        this.v_4262_N = new FlyingMoveControl(this, 10, false);
        this.n_1700_B(I_1869_h.M_588_G, -1.0f);
        this.n_1700_B(I_1869_h.P_4830_p, -1.0f);
        this.n_1700_B(I_1869_h.k_2293_S, -1.0f);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.Y_601_j(this.RealmsWorldOptions.nextInt(5));
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(false);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean d_() {
        return false;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new PanicGoal(this, 1.25));
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(2, new SitWhenOrderedToGoal(this));
        this.s_956_w.n_1700_B(2, new f_4739_a(this, 1.0, 5.0f, 1.0f, true));
        this.s_956_w.n_1700_B(2, new Y_4462_y(this, 1.0));
        this.s_956_w.n_1700_B(3, new w_3686_I(this));
        this.s_956_w.n_1700_B(3, new FollowMobGoal(this, 1.0, 3.0f, 7.0f));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 6.0).n_1700_B(Attributes.P_1922_E, 0.4f).n_1700_B(Attributes.G_564_y, 0.2f);
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn);
        flyingpathnavigator.n_1700_B(false);
        flyingpathnavigator.R_4764_Y(true);
        flyingpathnavigator.J_1907_R(true);
        return flyingpathnavigator;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.6f;
    }

    @Override
    public void Y_1740_V() {
        if (this.c_3005_b == null || !this.c_3005_b.withinDistance(this.s_4990_V(), 3.46) || !this.O_508_d.getBlockState(this.c_3005_b).n_1700_B(a_3742_W.r_2478_U)) {
            this.Z_875_P = false;
            this.c_3005_b = null;
        }
        if (this.O_508_d.w_1457_N.nextInt(400) == 0) {
            R_1299_M.n_1700_B(this.O_508_d, this);
        }
        super.Y_1740_V();
        this.V_537_k();
    }

    @Override
    public void n_1700_B(c_1514_x pos, boolean isPartying) {
        this.c_3005_b = pos;
        this.Z_875_P = isPartying;
    }

    public boolean h_1640_b() {
        return this.Z_875_P;
    }

    private void V_537_k() {
        this.t_1786_h = this.h_1847_R;
        this.M_182_A = this.Q_4569_t;
        this.Q_4569_t = (float)((double)this.Q_4569_t + (double)(!this.e_1992_r && !this.y_2772_m() ? 4 : -1) * 0.3);
        this.Q_4569_t = u_530_F.n_1700_B(this.Q_4569_t, 0.0f, 1.0f);
        if (!this.e_1992_r && this.q_2307_F < 1.0f) {
            this.q_2307_F = 1.0f;
        }
        this.q_2307_F = (float)((double)this.q_2307_F * 0.9);
        e_2866_D vector3d = this.I_4348_c();
        if (!this.e_1992_r && vector3d.R_4764_Y < 0.0) {
            this.v_4262_N(vector3d.G_564_y(1.0, 0.6, 1.0));
        }
        this.h_1847_R += this.q_2307_F * 2.0f;
    }

    public static boolean n_1700_B(b_4507_u worldIn, N_4263_v parrotIn) {
        if (parrotIn.RealmsLongRunningMcoTaskScreen() && !parrotIn.y_1700_S() && worldIn.w_1457_N.nextInt(2) == 0) {
            Z_530_i mobentity;
            List<Z_530_i> list = worldIn.n_1700_B(Z_530_i.class, parrotIn.i_601_W().grow(20.0), Y_259_p);
            if (!list.isEmpty() && !(mobentity = list.get(worldIn.w_1457_N.nextInt(list.size()))).y_1700_S()) {
                SoundEvent soundevent = R_1299_M.J_1907_R(mobentity.f_4016_n());
                worldIn.n_1700_B((a_3913_L)null, parrotIn.O_3598_v(), parrotIn.X_2960_b(), parrotIn.l_2647_k(), soundevent, parrotIn.r_2478_U(), 0.7f, R_1299_M.n_1700_B(worldIn.w_1457_N));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (!this.U_3758_B() && C_2741_M.contains(itemstack.J_1907_R())) {
            if (!p_230254_1_.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.R_4912_F, this.r_2478_U(), 1.0f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
            }
            if (!this.O_508_d.Y_259_p) {
                if (this.RealmsWorldOptions.nextInt(10) == 0) {
                    this.u_1723_Y(p_230254_1_);
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)7);
                } else {
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)6);
                }
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (itemstack.J_1907_R() == Q_2552_b) {
            if (!p_230254_1_.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            this.n_1700_B(new k_2610_C(MobEffects.w_1457_N, 900));
            if (p_230254_1_.G_624_v() || !this.P_925_e()) {
                this.n_1700_B(P_11_z.n_1700_B(p_230254_1_), Float.MAX_VALUE);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (!this.y_2447_C() && this.U_3758_B() && this.w_1484_f(p_230254_1_)) {
            if (!this.O_508_d.Y_259_p) {
                this.k_2293_S(!this.D_3612_q());
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return false;
    }

    public static boolean J_1907_R(t_5_h<R_1299_M> parrotIn, LevelAccessor worldIn, a_3160_D reason, c_1514_x p_223317_3_, Random random) {
        K_4074_S blockstate = worldIn.getBlockState(p_223317_3_.down());
        return (blockstate.n_1700_B(BlockTags.d_2427_y) || blockstate.n_1700_B(a_3742_W.t_148_a) || blockstate.n_1700_B(BlockTags.w_1457_N) || blockstate.n_1700_B(a_3742_W.n_1700_B)) && worldIn.n_1700_B(p_223317_3_, 0) > 8;
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        return false;
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return null;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        return entityIn.n_1700_B(P_11_z.R_4764_Y(this), 3.0f);
    }

    @Override
    @Nullable
    public SoundEvent z_4693_k() {
        return R_1299_M.n_1700_B(this.O_508_d, this.O_508_d.w_1457_N);
    }

    public static SoundEvent n_1700_B(b_4507_u p_234212_0_, Random p_234212_1_) {
        if (p_234212_0_.x_607_J() != R_2450_T.n_1700_B && p_234212_1_.nextInt(1000) == 0) {
            ArrayList list = Lists.newArrayList(k_2293_S.keySet());
            return R_1299_M.J_1907_R((t_5_h)list.get(p_234212_1_.nextInt(list.size())));
        }
        return SoundEvents.f_4340_D;
    }

    private static SoundEvent J_1907_R(t_5_h<?> type) {
        return k_2293_S.getOrDefault(type, SoundEvents.f_4340_D);
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.o_977_F;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.A_2204_Z;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.s_1124_y, 0.15f, 1.0f);
    }

    @Override
    protected float w_1484_f(float volume) {
        this.n_1700_B(SoundEvents.S_315_z, 0.15f, 1.0f);
        return volume + this.Q_4569_t / 2.0f;
    }

    @Override
    protected boolean RealmsDefaultUncaughtExceptionHandler() {
        return true;
    }

    @Override
    protected float O_2761_o() {
        return R_1299_M.n_1700_B(this.RealmsWorldOptions);
    }

    public static float n_1700_B(Random random) {
        return (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.v_4262_N;
    }

    @Override
    public boolean w_728_N() {
        return true;
    }

    @Override
    protected void Z_875_P(N_4263_v entityIn) {
        if (!(entityIn instanceof a_3913_L)) {
            super.Z_875_P(entityIn);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        this.k_2293_S(false);
        return super.n_1700_B(source, amount);
    }

    public int V_1176_p() {
        return u_530_F.n_1700_B((int)this.l_4537_E.n_1700_B(Y_601_j), 0, 4);
    }

    public void Y_601_j(int variantIn) {
        this.l_4537_E.J_1907_R(Y_601_j, variantIn);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Y_601_j, 0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Variant", this.V_1176_p());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Y_601_j(compound.w_1484_f("Variant"));
    }

    public boolean y_2447_C() {
        return !this.e_1992_r;
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.5f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }
}


