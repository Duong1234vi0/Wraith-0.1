/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.PathNavigation;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_3856_V;
import lightning.product.D_38_f;
import lightning.product.RandomStrollGoal;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.TurtleNodeEvaluator;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.L_2467_I;
import lightning.product.L_461_d;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.MoveToBlockGoal;
import lightning.product.MoveControl;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1972_S;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.MobType;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.n_4637_L;
import lightning.product.Goal;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LightningBolt;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class t_4149_i
extends Animal {
    private static final h_256_u<c_1514_x> Q_4569_t = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.M_588_G);
    private static final h_256_u<Boolean> M_182_A = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> t_1786_h = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<c_1514_x> multiplayerClientSuggestionProvider = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.M_588_G);
    private static final h_256_u<Boolean> w_1457_N = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> Y_601_j = C_4114_x.n_1700_B(t_4149_i.class, EntityDataSerializers.t_148_a);
    private int Y_259_p;
    public static final Predicate<r_4811_B> h_1847_R = p_213616_0_ -> p_213616_0_.d_() && !p_213616_0_.RowButton();

    public t_4149_i(t_5_h<? extends t_4149_i> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.n_1700_B(I_1869_h.w_1484_f, 0.0f);
        this.v_4262_N = new P_1922_E(this);
        this.RealmsServerPing = 1.0f;
    }

    public void v_4262_N(c_1514_x position) {
        this.l_4537_E.J_1907_R(Q_4569_t, position);
    }

    private c_1514_x y_2447_C() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    private void w_1484_f(c_1514_x position) {
        this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, position);
    }

    private c_1514_x J_3635_s() {
        return this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider);
    }

    public boolean y_4642_Y() {
        return this.l_4537_E.n_1700_B(M_182_A);
    }

    private void w_1457_N(boolean hasEgg) {
        this.l_4537_E.J_1907_R(M_182_A, hasEgg);
    }

    public boolean h_1640_b() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    private void Y_601_j(boolean isDigging) {
        this.Y_259_p = isDigging ? 1 : 0;
        this.l_4537_E.J_1907_R(t_1786_h, isDigging);
    }

    private boolean V_537_k() {
        return this.l_4537_E.n_1700_B(w_1457_N);
    }

    private void Y_259_p(boolean isGoingHome) {
        this.l_4537_E.J_1907_R(w_1457_N, isGoingHome);
    }

    private boolean c_2086_l() {
        return this.l_4537_E.n_1700_B(Y_601_j);
    }

    private void Q_2552_b(boolean isTravelling) {
        this.l_4537_E.J_1907_R(Y_601_j, isTravelling);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, c_1514_x.ZERO);
        this.l_4537_E.n_1700_B(M_182_A, false);
        this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider, c_1514_x.ZERO);
        this.l_4537_E.n_1700_B(w_1457_N, false);
        this.l_4537_E.n_1700_B(Y_601_j, false);
        this.l_4537_E.n_1700_B(t_1786_h, false);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("HomePosX", this.y_2447_C().getX());
        compound.J_1907_R("HomePosY", this.y_2447_C().getY());
        compound.J_1907_R("HomePosZ", this.y_2447_C().getZ());
        compound.n_1700_B("HasEgg", this.y_4642_Y());
        compound.J_1907_R("TravelPosX", this.J_3635_s().getX());
        compound.J_1907_R("TravelPosY", this.J_3635_s().getY());
        compound.J_1907_R("TravelPosZ", this.J_3635_s().getZ());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        int i = compound.w_1484_f("HomePosX");
        int j = compound.w_1484_f("HomePosY");
        int k = compound.w_1484_f("HomePosZ");
        this.v_4262_N(new c_1514_x(i, j, k));
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("HasEgg"));
        int l = compound.w_1484_f("TravelPosX");
        int i1 = compound.w_1484_f("TravelPosY");
        int j1 = compound.w_1484_f("TravelPosZ");
        this.w_1484_f(new c_1514_x(l, i1, j1));
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.v_4262_N(this.b_2312_j());
        this.w_1484_f(c_1514_x.ZERO);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean J_1907_R(t_5_h<t_4149_i> p_223322_0_, LevelAccessor p_223322_1_, a_3160_D reason, c_1514_x p_223322_3_, Random p_223322_4_) {
        return p_223322_3_.getY() < p_223322_1_.d_2461_k() + 4 && L_2467_I.n_1700_B(p_223322_1_, p_223322_3_) && p_223322_1_.n_1700_B(p_223322_3_, 0) > 8;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new v_4262_N(this, 1.2));
        this.s_956_w.n_1700_B(1, new G_564_y(this, 1.0));
        this.s_956_w.n_1700_B(1, new R_4764_Y(this, 1.0));
        this.s_956_w.n_1700_B(2, new w_1484_f(this, 1.1, a_3742_W.RowButton.u_1723_Y()));
        this.s_956_w.n_1700_B(3, new J_1907_R(this, 1.0));
        this.s_956_w.n_1700_B(4, new n_1700_B(this, 1.0));
        this.s_956_w.n_1700_B(7, new t_148_a(this, 1.0));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(9, new s_956_w(this, 1.0, 100));
    }

    public static s_1415_m.n_1700_B V_1176_p() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 30.0).n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    public boolean Y_776_s() {
        return false;
    }

    @Override
    public boolean P_328_a() {
        return true;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.P_1922_E;
    }

    @Override
    public int v_4276_D() {
        return 200;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return !this.RowButton() && this.e_1992_r && !this.d_() ? SoundEvents.h_935_G : super.z_4693_k();
    }

    @Override
    protected void v_4262_N(float volume) {
        super.v_4262_N(volume * 1.5f);
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.GrindstoneBlock;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.d_() ? SoundEvents.GlazedTerracottaBlock : SoundEvents.N_3347_G;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return this.d_() ? SoundEvents.v_4620_e : SoundEvents.s_3834_w;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        SoundEvent soundevent = this.d_() ? SoundEvents.GravelBlock : SoundEvents.p_1168_n;
        this.n_1700_B(soundevent, 0.15f, 1.0f);
    }

    @Override
    public boolean o_82_k() {
        return super.o_82_k() && !this.y_4642_Y();
    }

    @Override
    protected float R_3908_n() {
        return this.V_1225_t + 0.15f;
    }

    @Override
    public float S_4258_d() {
        return this.d_() ? 0.3f : 1.0f;
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new u_1723_Y(this, worldIn);
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.l_4537_E.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R() == a_3742_W.RowButton.u_1723_Y();
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        if (!this.V_537_k() && worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R)) {
            return 10.0f;
        }
        return L_2467_I.n_1700_B(worldIn, pos) ? 10.0f : worldIn.w_1484_f(pos) - 0.5f;
    }

    @Override
    public void Y_1740_V() {
        c_1514_x blockpos;
        super.Y_1740_V();
        if (this.RealmsLongRunningMcoTaskScreen() && this.h_1640_b() && this.Y_259_p >= 1 && this.Y_259_p % 5 == 0 && L_2467_I.n_1700_B(this.O_508_d, blockpos = this.b_2312_j())) {
            this.O_508_d.R_4764_Y(2001, blockpos, T_2915_h.s_956_w(a_3742_W.A_4115_X.multiplayerClientSuggestionProvider()));
        }
    }

    @Override
    protected void y_() {
        super.y_();
        if (!this.d_() && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
            this.n_1700_B(Items.o_977_F, 1);
        }
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.w_1457_N() && this.RowButton()) {
            this.n_1700_B(0.1f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.9));
            if (!(this.t_148_a() != null || this.V_537_k() && this.y_2447_C().withinDistance(this.s_4990_V(), 20.0))) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.005, 0.0));
            }
        } else {
            super.w_1484_f(travelVector);
        }
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return false;
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        this.n_1700_B(P_11_z.J_1907_R, Float.MAX_VALUE);
    }

    static class P_1922_E
    extends MoveControl {
        private final t_4149_i t_148_a;

        P_1922_E(t_4149_i turtleIn) {
            super(turtleIn);
            this.t_148_a = turtleIn;
        }

        private void v_4262_N() {
            if (this.t_148_a.RowButton()) {
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, 0.005, 0.0));
                if (!this.t_148_a.y_2447_C().withinDistance(this.t_148_a.s_4990_V(), 16.0)) {
                    this.t_148_a.w_1457_N(Math.max(this.t_148_a.l_2995_s() / 2.0f, 0.08f));
                }
                if (this.t_148_a.d_()) {
                    this.t_148_a.w_1457_N(Math.max(this.t_148_a.l_2995_s() / 3.0f, 0.06f));
                }
            } else if (this.t_148_a.e_1992_r) {
                this.t_148_a.w_1457_N(Math.max(this.t_148_a.l_2995_s() / 2.0f, 0.06f));
            }
        }

        @Override
        public void n_1700_B() {
            this.v_4262_N();
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R && !this.t_148_a.e_4240_b().M_588_G()) {
                double d0 = this.J_1907_R - this.t_148_a.O_3598_v();
                double d1 = this.R_4764_Y - this.t_148_a.X_2960_b();
                double d2 = this.G_564_y - this.t_148_a.l_2647_k();
                double d3 = u_530_F.n_1700_B(d0 * d0 + d1 * d1 + d2 * d2);
                float f = (float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f;
                this.t_148_a.C_1162_e = this.t_148_a.p_178_J = this.n_1700_B(this.t_148_a.p_178_J, f, 90.0f);
                float f1 = (float)(this.P_1922_E * this.t_148_a.J_1907_R(Attributes.G_564_y));
                this.t_148_a.w_1457_N(u_530_F.v_4262_N(0.125f, this.t_148_a.l_2995_s(), f1));
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, (double)this.t_148_a.l_2995_s() * (d1 /= d3) * 0.1, 0.0));
            } else {
                this.t_148_a.w_1457_N(0.0f);
            }
        }
    }

    static class v_4262_N
    extends PanicGoal {
        v_4262_N(t_4149_i turtle, double speedIn) {
            super(turtle, speedIn);
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.q_817_e() == null && !this.n_1700_B.RealmsPersistence()) {
                return false;
            }
            c_1514_x blockpos = this.n_1700_B(this.n_1700_B.O_508_d, this.n_1700_B, 7, 4);
            if (blockpos != null) {
                this.R_4764_Y = blockpos.getX();
                this.G_564_y = blockpos.getY();
                this.P_1922_E = blockpos.getZ();
                return true;
            }
            return this.v_4262_N();
        }
    }

    static class G_564_y
    extends BreedGoal {
        private final t_4149_i G_564_y;

        G_564_y(t_4149_i turtle, double speedIn) {
            super(turtle, speedIn);
            this.G_564_y = turtle;
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && !this.G_564_y.y_4642_Y();
        }

        @Override
        protected void v_4262_N() {
            B_4088_l serverplayerentity = this.n_1700_B.f_2787_O();
            if (serverplayerentity == null && this.R_4764_Y.f_2787_O() != null) {
                serverplayerentity = this.R_4764_Y.f_2787_O();
            }
            if (serverplayerentity != null) {
                serverplayerentity.J_1907_R(Stats.q_4610_l);
                U_3554_Q.Q_4569_t.n_1700_B(serverplayerentity, this.n_1700_B, this.R_4764_Y, null);
            }
            this.G_564_y.w_1457_N(true);
            this.n_1700_B.U_1697_c();
            this.R_4764_Y.U_1697_c();
            Random random = this.n_1700_B.M_3508_C();
            if (this.J_1907_R.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
                this.J_1907_R.a_(new n_4637_L(this.J_1907_R, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), random.nextInt(7) + 1));
            }
        }
    }

    static class R_4764_Y
    extends MoveToBlockGoal {
        private final t_4149_i v_4262_N;

        R_4764_Y(t_4149_i turtle, double speedIn) {
            super(turtle, speedIn, 16);
            this.v_4262_N = turtle;
        }

        @Override
        public boolean n_1700_B() {
            return this.v_4262_N.y_4642_Y() && this.v_4262_N.y_2447_C().withinDistance(this.v_4262_N.s_4990_V(), 9.0) ? super.n_1700_B() : false;
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && this.v_4262_N.y_4642_Y() && this.v_4262_N.y_2447_C().withinDistance(this.v_4262_N.s_4990_V(), 9.0);
        }

        @Override
        public void P_1922_E() {
            super.P_1922_E();
            c_1514_x blockpos = this.v_4262_N.b_2312_j();
            if (!this.v_4262_N.RowButton() && this.M_588_G()) {
                if (this.v_4262_N.Y_259_p < 1) {
                    this.v_4262_N.Y_601_j(true);
                } else if (this.v_4262_N.Y_259_p > 200) {
                    b_4507_u world = this.v_4262_N.O_508_d;
                    world.n_1700_B((a_3913_L)null, blockpos, SoundEvents.GrassBlock, D_38_f.P_1922_E, 0.3f, 0.9f + world.w_1457_N.nextFloat() * 0.2f);
                    world.n_1700_B(this.P_1922_E.up(), (K_4074_S)a_3742_W.d_560_A.multiplayerClientSuggestionProvider().n_1700_B(L_2467_I.h_1847_R, this.v_4262_N.RealmsWorldOptions.nextInt(4) + 1), 3);
                    this.v_4262_N.w_1457_N(false);
                    this.v_4262_N.Y_601_j(false);
                    this.v_4262_N.w_1457_N(600);
                }
                if (this.v_4262_N.h_1640_b()) {
                    ++this.v_4262_N.Y_259_p;
                }
            }
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            return !worldIn.u_1723_Y(pos.up()) ? false : L_2467_I.J_1907_R(worldIn, pos);
        }
    }

    static class w_1484_f
    extends Goal {
        private static final TargetingConditions n_1700_B = new TargetingConditions().n_1700_B(10.0).J_1907_R().n_1700_B();
        private final t_4149_i J_1907_R;
        private final double R_4764_Y;
        private a_3913_L G_564_y;
        private int P_1922_E;
        private final Set<q_1613_l> u_1723_Y;

        w_1484_f(t_4149_i turtle, double speedIn, q_1613_l temptItem) {
            this.J_1907_R = turtle;
            this.R_4764_Y = speedIn;
            this.u_1723_Y = Sets.newHashSet((Object[])new q_1613_l[]{temptItem});
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            if (this.P_1922_E > 0) {
                --this.P_1922_E;
                return false;
            }
            this.G_564_y = this.J_1907_R.O_508_d.n_1700_B(n_1700_B, this.J_1907_R);
            if (this.G_564_y == null) {
                return false;
            }
            return this.n_1700_B(this.G_564_y.A_2714_y()) || this.n_1700_B(this.G_564_y.S_4035_N());
        }

        private boolean n_1700_B(Z_1993_T p_203131_1_) {
            return this.u_1723_Y.contains(p_203131_1_.J_1907_R());
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B();
        }

        @Override
        public void G_564_y() {
            this.G_564_y = null;
            this.J_1907_R.e_4240_b().h_1847_R();
            this.P_1922_E = 100;
        }

        @Override
        public void P_1922_E() {
            this.J_1907_R.c_3005_b().n_1700_B(this.G_564_y, (float)(this.J_1907_R.H_1990_U() + 20), (float)this.J_1907_R.Z_976_R());
            if (this.J_1907_R.G_564_y((N_4263_v)this.G_564_y) < 6.25) {
                this.J_1907_R.e_4240_b().h_1847_R();
            } else {
                this.J_1907_R.e_4240_b().n_1700_B((N_4263_v)this.G_564_y, this.R_4764_Y);
            }
        }
    }

    static class J_1907_R
    extends MoveToBlockGoal {
        private final t_4149_i v_4262_N;

        private J_1907_R(t_4149_i turtle, double speedIn) {
            super(turtle, turtle.d_() ? 2.0 : speedIn, 24);
            this.v_4262_N = turtle;
            this.u_1723_Y = -1;
        }

        @Override
        public boolean J_1907_R() {
            return !this.v_4262_N.RowButton() && this.G_564_y <= 1200 && this.n_1700_B(this.v_4262_N.O_508_d, this.P_1922_E);
        }

        @Override
        public boolean n_1700_B() {
            if (this.v_4262_N.d_() && !this.v_4262_N.RowButton()) {
                return super.n_1700_B();
            }
            return !this.v_4262_N.V_537_k() && !this.v_4262_N.RowButton() && !this.v_4262_N.y_4642_Y() ? super.n_1700_B() : false;
        }

        @Override
        public boolean u_2550_I() {
            return this.G_564_y % 160 == 0;
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            return worldIn.getBlockState(pos).n_1700_B(a_3742_W.c_3005_b);
        }
    }

    static class n_1700_B
    extends Goal {
        private final t_4149_i n_1700_B;
        private final double J_1907_R;
        private boolean R_4764_Y;
        private int G_564_y;

        n_1700_B(t_4149_i turtle, double speedIn) {
            this.n_1700_B = turtle;
            this.J_1907_R = speedIn;
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.d_()) {
                return false;
            }
            if (this.n_1700_B.y_4642_Y()) {
                return true;
            }
            if (this.n_1700_B.M_3508_C().nextInt(700) != 0) {
                return false;
            }
            return !this.n_1700_B.y_2447_C().withinDistance(this.n_1700_B.s_4990_V(), 64.0);
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.Y_259_p(true);
            this.R_4764_Y = false;
            this.G_564_y = 0;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.Y_259_p(false);
        }

        @Override
        public boolean J_1907_R() {
            return !this.n_1700_B.y_2447_C().withinDistance(this.n_1700_B.s_4990_V(), 7.0) && !this.R_4764_Y && this.G_564_y <= 600;
        }

        @Override
        public void P_1922_E() {
            c_1514_x blockpos = this.n_1700_B.y_2447_C();
            boolean flag = blockpos.withinDistance(this.n_1700_B.s_4990_V(), 16.0);
            if (flag) {
                ++this.G_564_y;
            }
            if (this.n_1700_B.e_4240_b().M_588_G()) {
                e_2866_D vector3d = e_2866_D.R_4764_Y(blockpos);
                e_2866_D vector3d1 = W_3371_U.n_1700_B(this.n_1700_B, 16, 3, vector3d, 0.3141592741012573);
                if (vector3d1 == null) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 8, 7, vector3d);
                }
                if (vector3d1 != null && !flag && !this.n_1700_B.O_508_d.getBlockState(new c_1514_x(vector3d1)).n_1700_B(a_3742_W.c_3005_b)) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 16, 5, vector3d);
                }
                if (vector3d1 == null) {
                    this.R_4764_Y = true;
                    return;
                }
                this.n_1700_B.e_4240_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.J_1907_R);
            }
        }
    }

    static class t_148_a
    extends Goal {
        private final t_4149_i n_1700_B;
        private final double J_1907_R;
        private boolean R_4764_Y;

        t_148_a(t_4149_i turtle, double speedIn) {
            this.n_1700_B = turtle;
            this.J_1907_R = speedIn;
        }

        @Override
        public boolean n_1700_B() {
            return !this.n_1700_B.V_537_k() && !this.n_1700_B.y_4642_Y() && this.n_1700_B.RowButton();
        }

        @Override
        public void R_4764_Y() {
            int i = 512;
            int j = 4;
            Random random = this.n_1700_B.RealmsWorldOptions;
            int k = random.nextInt(1025) - 512;
            int l = random.nextInt(9) - 4;
            int i1 = random.nextInt(1025) - 512;
            if ((double)l + this.n_1700_B.X_2960_b() > (double)(this.n_1700_B.O_508_d.d_2461_k() - 1)) {
                l = 0;
            }
            c_1514_x blockpos = new c_1514_x((double)k + this.n_1700_B.O_3598_v(), (double)l + this.n_1700_B.X_2960_b(), (double)i1 + this.n_1700_B.l_2647_k());
            this.n_1700_B.w_1484_f(blockpos);
            this.n_1700_B.Q_2552_b(true);
            this.R_4764_Y = false;
        }

        @Override
        public void P_1922_E() {
            if (this.n_1700_B.e_4240_b().M_588_G()) {
                e_2866_D vector3d = e_2866_D.R_4764_Y(this.n_1700_B.J_3635_s());
                e_2866_D vector3d1 = W_3371_U.n_1700_B(this.n_1700_B, 16, 3, vector3d, 0.3141592741012573);
                if (vector3d1 == null) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 8, 7, vector3d);
                }
                if (vector3d1 != null) {
                    int i = u_530_F.R_4764_Y(vector3d1.J_1907_R);
                    int j = u_530_F.R_4764_Y(vector3d1.G_564_y);
                    int k = 34;
                    if (!this.n_1700_B.O_508_d.n_1700_B(i - 34, 0, j - 34, i + 34, 0, j + 34)) {
                        vector3d1 = null;
                    }
                }
                if (vector3d1 == null) {
                    this.R_4764_Y = true;
                    return;
                }
                this.n_1700_B.e_4240_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.J_1907_R);
            }
        }

        @Override
        public boolean J_1907_R() {
            return !this.n_1700_B.e_4240_b().M_588_G() && !this.R_4764_Y && !this.n_1700_B.V_537_k() && !this.n_1700_B.P_2295_B() && !this.n_1700_B.y_4642_Y();
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.Q_2552_b(false);
            super.G_564_y();
        }
    }

    static class s_956_w
    extends RandomStrollGoal {
        private final t_4149_i w_1484_f;

        private s_956_w(t_4149_i turtle, double speedIn, int chance) {
            super(turtle, speedIn, chance);
            this.w_1484_f = turtle;
        }

        @Override
        public boolean n_1700_B() {
            return !this.n_1700_B.RowButton() && !this.w_1484_f.V_537_k() && !this.w_1484_f.y_4642_Y() ? super.n_1700_B() : false;
        }
    }

    static class u_1723_Y
    extends c_1972_S {
        u_1723_Y(t_4149_i turtle, b_4507_u worldIn) {
            super(turtle, worldIn);
        }

        @Override
        protected boolean J_1907_R() {
            return true;
        }

        @Override
        protected D_3856_V n_1700_B(int p_179679_1_) {
            this.M_182_A = new TurtleNodeEvaluator();
            return new D_3856_V(this.M_182_A, p_179679_1_);
        }

        @Override
        public boolean n_1700_B(c_1514_x pos) {
            t_4149_i turtleentity;
            if (this.J_1907_R instanceof t_4149_i && (turtleentity = (t_4149_i)this.J_1907_R).c_2086_l()) {
                return this.R_4764_Y.getBlockState(pos).n_1700_B(a_3742_W.c_3005_b);
            }
            return !this.R_4764_Y.getBlockState(pos.down()).v_4262_N();
        }
    }
}


