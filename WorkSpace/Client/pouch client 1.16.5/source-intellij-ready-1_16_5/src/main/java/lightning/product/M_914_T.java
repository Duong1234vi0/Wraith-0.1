/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4388_s;
import lightning.product.FluidTags;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.G_3246_f;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.J_2548_M;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.PathfinderMob;
import lightning.product.n_3832_I;
import lightning.product.BlockTags;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.Endermite;
import lightning.product.y_4711_y;

public class M_914_T
extends Monster
implements G_3246_f {
    private static final UUID n_1700_B = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final U_1880_G J_1907_R = new U_1880_G(n_1700_B, "Attacking speed boost", (double)0.15f, U_1880_G.n_1700_B.n_1700_B);
    private static final h_256_u<Optional<K_4074_S>> R_4764_Y = C_4114_x.n_1700_B(M_914_T.class, EntityDataSerializers.w_1484_f);
    private static final h_256_u<Boolean> h_1847_R = C_4114_x.n_1700_B(M_914_T.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(M_914_T.class, EntityDataSerializers.t_148_a);
    private static final Predicate<r_4811_B> M_182_A = p_213626_0_ -> p_213626_0_ instanceof Endermite && ((Endermite)p_213626_0_).y_4642_Y();
    private int t_1786_h = Integer.MIN_VALUE;
    private int multiplayerClientSuggestionProvider;
    private static final J_2548_M w_1457_N = TimeUtil.n_1700_B(20, 39);
    private int Y_601_j;
    private UUID Y_259_p;

    public M_914_T(t_5_h<? extends M_914_T> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
        this.RealmsServerPing = 1.0f;
        this.n_1700_B(I_1869_h.w_1484_f, -1.0f);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new R_4764_Y(this));
        this.s_956_w.n_1700_B(2, new b_4953_N(this, 1.0, false));
        this.s_956_w.n_1700_B(7, new g_1941_L((PathfinderMob)this, 1.0, 0.0f));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.s_956_w.n_1700_B(10, new J_1907_R(this));
        this.s_956_w.n_1700_B(11, new G_564_y(this));
        this.u_2550_I.n_1700_B(1, new n_1700_B(this, this::c_));
        this.u_2550_I.n_1700_B(2, new g_3408_G(this, new Class[0]));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<Endermite>(this, Endermite.class, 10, true, false, M_182_A));
        this.u_2550_I.n_1700_B(4, new ResetUniversalAngerTargetGoal<M_914_T>(this, false));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 40.0).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.u_1723_Y, 7.0).n_1700_B(Attributes.J_1907_R, 64.0);
    }

    @Override
    public void R_4764_Y(@Nullable r_4811_B entitylivingbaseIn) {
        super.R_4764_Y(entitylivingbaseIn);
        A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
        if (entitylivingbaseIn == null) {
            this.multiplayerClientSuggestionProvider = 0;
            this.l_4537_E.J_1907_R(h_1847_R, false);
            this.l_4537_E.J_1907_R(Q_4569_t, false);
            modifiableattributeinstance.G_564_y(J_1907_R);
        } else {
            this.multiplayerClientSuggestionProvider = this.RealmsWorldResetDto;
            this.l_4537_E.J_1907_R(h_1847_R, true);
            if (!modifiableattributeinstance.n_1700_B(J_1907_R)) {
                modifiableattributeinstance.J_1907_R(J_1907_R);
            }
        }
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(R_4764_Y, Optional.empty());
        this.l_4537_E.n_1700_B(h_1847_R, false);
        this.l_4537_E.n_1700_B(Q_4569_t, false);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(w_1457_N.n_1700_B(this.RealmsWorldOptions));
    }

    @Override
    public void n_1700_B(int time) {
        this.Y_601_j = time;
    }

    @Override
    public int n_1700_B() {
        return this.Y_601_j;
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.Y_259_p = target;
    }

    @Override
    public UUID G_564_y() {
        return this.Y_259_p;
    }

    public void V_1176_p() {
        if (this.RealmsWorldResetDto >= this.t_1786_h + 400) {
            this.t_1786_h = this.RealmsWorldResetDto;
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k(), SoundEvents.J_4125_o, this.r_2478_U(), 2.5f, 1.0f, false);
            }
        }
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (h_1847_R.equals(key) && this.h_973_D() && this.O_508_d.Y_259_p) {
            this.V_1176_p();
        }
        super.n_1700_B(key);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        K_4074_S blockstate = this.J_3635_s();
        if (blockstate != null) {
            compound.n_1700_B("carriedBlockState", n_3832_I.n_1700_B(blockstate));
        }
        this.a_(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        K_4074_S blockstate = null;
        if (compound.R_4764_Y("carriedBlockState", 10) && (blockstate = n_3832_I.R_4764_Y(compound.M_182_A("carriedBlockState"))).v_4262_N()) {
            blockstate = null;
        }
        this.R_4764_Y(blockstate);
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    private boolean u_1723_Y(a_3913_L player) {
        Z_1993_T itemstack = player.l_1268_F.J_1907_R.get(3);
        if (itemstack.J_1907_R() == a_3742_W.X_2048_Y.u_1723_Y()) {
            return false;
        }
        e_2866_D vector3d = player.t_148_a(1.0f).G_564_y();
        e_2866_D vector3d1 = new e_2866_D(this.O_3598_v() - player.O_3598_v(), this.X_2048_Y() - player.X_2048_Y(), this.l_2647_k() - player.l_2647_k());
        double d0 = vector3d1.u_1723_Y();
        double d1 = vector3d.J_1907_R(vector3d1 = vector3d1.G_564_y());
        return d1 > 1.0 - 0.025 / d0 ? player.c_3005_b(this) : false;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 2.55f;
    }

    @Override
    public void Y_1740_V() {
        if (this.O_508_d.Y_259_p) {
            for (int i = 0; i < 2; ++i) {
                this.O_508_d.n_1700_B(ParticleTypes.g_221_o, this.G_564_y(0.5), this.M_766_z() - 0.25, this.v_4262_N(0.5), (this.RealmsWorldOptions.nextDouble() - 0.5) * 2.0, -this.RealmsWorldOptions.nextDouble(), (this.RealmsWorldOptions.nextDouble() - 0.5) * 2.0);
            }
        }
        this.F_3572_x = false;
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B((e_3591_l)this.O_508_d, true);
        }
        super.Y_1740_V();
    }

    @Override
    public boolean e_1231_S() {
        return true;
    }

    @Override
    protected void X_933_l() {
        float f;
        if (this.O_508_d.q_4610_l() && this.RealmsWorldResetDto >= this.multiplayerClientSuggestionProvider + 600 && (f = this.RealmsConfirmScreen()) > 0.5f && this.O_508_d.canSeeSky(this.b_2312_j()) && this.RealmsWorldOptions.nextFloat() * 30.0f < (f - 0.4f) * 2.0f) {
            this.R_4764_Y((r_4811_B)null);
            this.y_2447_C();
        }
        super.X_933_l();
    }

    protected boolean y_2447_C() {
        if (!this.O_508_d.v_4276_D() && this.RealmsLongRunningMcoTaskScreen()) {
            double d0 = this.O_3598_v() + (this.RealmsWorldOptions.nextDouble() - 0.5) * 64.0;
            double d1 = this.X_2960_b() + (double)(this.RealmsWorldOptions.nextInt(64) - 32);
            double d2 = this.l_2647_k() + (this.RealmsWorldOptions.nextDouble() - 0.5) * 64.0;
            return this.M_182_A(d0, d1, d2);
        }
        return false;
    }

    private boolean n_1700_B(N_4263_v p_70816_1_) {
        e_2866_D vector3d = new e_2866_D(this.O_3598_v() - p_70816_1_.O_3598_v(), this.P_1922_E(0.5) - p_70816_1_.X_2048_Y(), this.l_2647_k() - p_70816_1_.l_2647_k());
        vector3d = vector3d.G_564_y();
        double d0 = 16.0;
        double d1 = this.O_3598_v() + (this.RealmsWorldOptions.nextDouble() - 0.5) * 8.0 - vector3d.J_1907_R * 16.0;
        double d2 = this.X_2960_b() + (double)(this.RealmsWorldOptions.nextInt(16) - 8) - vector3d.R_4764_Y * 16.0;
        double d3 = this.l_2647_k() + (this.RealmsWorldOptions.nextDouble() - 0.5) * 8.0 - vector3d.G_564_y * 16.0;
        return this.M_182_A(d1, d2, d3);
    }

    private boolean M_182_A(double x, double y, double z) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(x, y, z);
        while (blockpos$mutable.getY() > 0 && !this.O_508_d.getBlockState(blockpos$mutable).R_4764_Y().R_4764_Y()) {
            blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
        }
        K_4074_S blockstate = this.O_508_d.getBlockState(blockpos$mutable);
        boolean flag = blockstate.R_4764_Y().R_4764_Y();
        boolean flag1 = blockstate.P_4830_p().n_1700_B(FluidTags.J_1907_R);
        if (flag && !flag1) {
            boolean flag2 = this.n_1700_B(x, y, z, true);
            if (flag2 && !this.y_1700_S()) {
                this.O_508_d.n_1700_B((a_3913_L)null, this.r_715_M, this.A_1038_p, this.i_1637_u, SoundEvents.A_229_v, this.r_2478_U(), 1.0f, 1.0f);
                this.n_1700_B(SoundEvents.A_229_v, 1.0f, 1.0f);
            }
            return flag2;
        }
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.o_82_k() ? SoundEvents.Z_2812_M : SoundEvents.b_3528_u;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.g_46_E;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.I_4477_R;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        super.n_1700_B(source, looting, recentlyHitIn);
        K_4074_S blockstate = this.J_3635_s();
        if (blockstate != null) {
            this.n_1700_B(blockstate.J_1907_R());
        }
    }

    public void R_4764_Y(@Nullable K_4074_S state) {
        this.l_4537_E.J_1907_R(R_4764_Y, Optional.ofNullable(state));
    }

    @Nullable
    public K_4074_S J_3635_s() {
        return this.l_4537_E.n_1700_B(R_4764_Y).orElse(null);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (source instanceof y_4711_y) {
            for (int i = 0; i < 64; ++i) {
                if (!this.y_2447_C()) continue;
                return true;
            }
            return false;
        }
        boolean flag = super.n_1700_B(source, amount);
        if (!this.O_508_d.v_4276_D() && !(source.u_2550_I() instanceof r_4811_B) && this.RealmsWorldOptions.nextInt(10) != 0) {
            this.y_2447_C();
        }
        return flag;
    }

    public boolean o_82_k() {
        return this.l_4537_E.n_1700_B(h_1847_R);
    }

    public boolean h_973_D() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    public void f_2787_O() {
        this.l_4537_E.J_1907_R(Q_4569_t, true);
    }

    @Override
    public boolean e_2887_G() {
        return super.e_2887_G() || this.J_3635_s() != null;
    }

    static class R_4764_Y
    extends Goal {
        private final M_914_T n_1700_B;
        private r_4811_B J_1907_R;

        public R_4764_Y(M_914_T endermanIn) {
            this.n_1700_B = endermanIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            this.J_1907_R = this.n_1700_B.t_148_a();
            if (!(this.J_1907_R instanceof a_3913_L)) {
                return false;
            }
            double d0 = this.J_1907_R.G_564_y((N_4263_v)this.n_1700_B);
            return d0 > 256.0 ? false : this.n_1700_B.u_1723_Y((a_3913_L)this.J_1907_R);
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.e_4240_b().h_1847_R();
        }

        @Override
        public void P_1922_E() {
            this.n_1700_B.c_3005_b().n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2048_Y(), this.J_1907_R.l_2647_k());
        }
    }

    static class J_1907_R
    extends Goal {
        private final M_914_T n_1700_B;

        public J_1907_R(M_914_T p_i45843_1_) {
            this.n_1700_B = p_i45843_1_;
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.J_3635_s() == null) {
                return false;
            }
            if (!this.n_1700_B.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                return false;
            }
            return this.n_1700_B.M_3508_C().nextInt(2000) == 0;
        }

        @Override
        public void P_1922_E() {
            Random random = this.n_1700_B.M_3508_C();
            b_4507_u world = this.n_1700_B.O_508_d;
            int i = u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() - 1.0 + random.nextDouble() * 2.0);
            int j = u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() + random.nextDouble() * 2.0);
            int k = u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() - 1.0 + random.nextDouble() * 2.0);
            c_1514_x blockpos = new c_1514_x(i, j, k);
            K_4074_S blockstate = world.getBlockState(blockpos);
            c_1514_x blockpos1 = blockpos.down();
            K_4074_S blockstate1 = world.getBlockState(blockpos1);
            K_4074_S blockstate2 = this.n_1700_B.J_3635_s();
            if (blockstate2 != null && this.n_1700_B(world, blockpos, blockstate2 = T_2915_h.J_1907_R(blockstate2, this.n_1700_B.O_508_d, blockpos), blockstate, blockstate1, blockpos1)) {
                world.n_1700_B(blockpos, blockstate2, 3);
                this.n_1700_B.R_4764_Y((K_4074_S)null);
            }
        }

        private boolean n_1700_B(b_4507_u p_220836_1_, c_1514_x p_220836_2_, K_4074_S p_220836_3_, K_4074_S p_220836_4_, K_4074_S p_220836_5_, c_1514_x p_220836_6_) {
            return p_220836_4_.v_4262_N() && !p_220836_5_.v_4262_N() && !p_220836_5_.n_1700_B(a_3742_W.Z_875_P) && p_220836_5_.multiplayerClientSuggestionProvider(p_220836_1_, p_220836_6_) && p_220836_3_.n_1700_B((T_1316_M)p_220836_1_, p_220836_2_) && p_220836_1_.n_1700_B((N_4263_v)this.n_1700_B, I_4817_s.fromVector(e_2866_D.J_1907_R(p_220836_2_))).isEmpty();
        }
    }

    static class G_564_y
    extends Goal {
        private final M_914_T n_1700_B;

        public G_564_y(M_914_T endermanIn) {
            this.n_1700_B = endermanIn;
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.J_3635_s() != null) {
                return false;
            }
            if (!this.n_1700_B.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                return false;
            }
            return this.n_1700_B.M_3508_C().nextInt(20) == 0;
        }

        @Override
        public void P_1922_E() {
            Random random = this.n_1700_B.M_3508_C();
            b_4507_u world = this.n_1700_B.O_508_d;
            int i = u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() - 2.0 + random.nextDouble() * 4.0);
            int j = u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() + random.nextDouble() * 3.0);
            int k = u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() - 2.0 + random.nextDouble() * 4.0);
            c_1514_x blockpos = new c_1514_x(i, j, k);
            K_4074_S blockstate = world.getBlockState(blockpos);
            T_2915_h block = blockstate.J_1907_R();
            e_2866_D vector3d = new e_2866_D((double)u_530_F.R_4764_Y(this.n_1700_B.O_3598_v()) + 0.5, (double)j + 0.5, (double)u_530_F.R_4764_Y(this.n_1700_B.l_2647_k()) + 0.5);
            e_2866_D vector3d1 = new e_2866_D((double)i + 0.5, (double)j + 0.5, (double)k + 0.5);
            BlockHitResult blockraytraceresult = world.n_1700_B(new ClipContext(vector3d, vector3d1, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, this.n_1700_B));
            boolean flag = blockraytraceresult.n_1700_B().equals(blockpos);
            if (block.n_1700_B(BlockTags.g_164_R) && flag) {
                world.n_1700_B(blockpos, false);
                this.n_1700_B.R_4764_Y(blockstate.J_1907_R().multiplayerClientSuggestionProvider());
            }
        }
    }

    static class n_1700_B
    extends NearestAttackableTargetGoal<a_3913_L> {
        private final M_914_T t_148_a;
        private a_3913_L s_956_w;
        private int u_2550_I;
        private int M_588_G;
        private final TargetingConditions P_4830_p;
        private final TargetingConditions h_1847_R = new TargetingConditions().R_4764_Y();

        public n_1700_B(M_914_T p_i241912_1_, @Nullable Predicate<r_4811_B> p_i241912_2_) {
            super(p_i241912_1_, a_3913_L.class, 10, false, false, p_i241912_2_);
            this.t_148_a = p_i241912_1_;
            this.P_4830_p = new TargetingConditions().n_1700_B(this.u_2550_I()).n_1700_B((r_4811_B p_220790_1_) -> p_i241912_1_.u_1723_Y((a_3913_L)p_220790_1_));
        }

        @Override
        public boolean n_1700_B() {
            this.s_956_w = this.t_148_a.O_508_d.n_1700_B(this.P_4830_p, this.t_148_a);
            return this.s_956_w != null;
        }

        @Override
        public void R_4764_Y() {
            this.u_2550_I = 5;
            this.M_588_G = 0;
            this.t_148_a.f_2787_O();
        }

        @Override
        public void G_564_y() {
            this.s_956_w = null;
            super.G_564_y();
        }

        @Override
        public boolean J_1907_R() {
            if (this.s_956_w != null) {
                if (!this.t_148_a.u_1723_Y(this.s_956_w)) {
                    return false;
                }
                this.t_148_a.n_1700_B((N_4263_v)this.s_956_w, 10.0f, 10.0f);
                return true;
            }
            return this.R_4764_Y != null && this.h_1847_R.n_1700_B(this.t_148_a, this.R_4764_Y) ? true : super.J_1907_R();
        }

        @Override
        public void P_1922_E() {
            if (this.t_148_a.t_148_a() == null) {
                super.n_1700_B((r_4811_B)null);
            }
            if (this.s_956_w != null) {
                if (--this.u_2550_I <= 0) {
                    this.R_4764_Y = this.s_956_w;
                    this.s_956_w = null;
                    super.R_4764_Y();
                }
            } else {
                if (this.R_4764_Y != null && !this.t_148_a.y_2772_m()) {
                    if (this.t_148_a.u_1723_Y((a_3913_L)this.R_4764_Y)) {
                        if (this.R_4764_Y.G_564_y((N_4263_v)this.t_148_a) < 16.0) {
                            this.t_148_a.y_2447_C();
                        }
                        this.M_588_G = 0;
                    } else if (this.R_4764_Y.G_564_y((N_4263_v)this.t_148_a) > 256.0 && this.M_588_G++ >= 30 && this.t_148_a.n_1700_B((N_4263_v)this.R_4764_Y)) {
                        this.M_588_G = 0;
                    }
                }
                super.P_1922_E();
            }
        }
    }
}


