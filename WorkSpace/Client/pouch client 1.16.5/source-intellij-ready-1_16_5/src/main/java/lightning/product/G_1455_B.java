/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.PathNavigation;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.RandomStrollGoal;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.LookControl;
import lightning.product.Squid;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.MoveControl;
import lightning.product.T_1316_M;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.ElderGuardian;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1972_S;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.MoveTowardsRestrictionGoal;
import lightning.product.MobType;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class G_1455_B
extends Monster {
    private static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(G_1455_B.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> R_4764_Y = C_4114_x.n_1700_B(G_1455_B.class, EntityDataSerializers.J_1907_R);
    private float h_1847_R;
    private float Q_4569_t;
    private float M_182_A;
    private float t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private r_4811_B w_1457_N;
    private int Y_601_j;
    private boolean Y_259_p;
    protected RandomStrollGoal J_1907_R;

    public G_1455_B(t_5_h<? extends G_1455_B> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
        this.P_1922_E = 10;
        this.n_1700_B(I_1869_h.w_1484_f, 0.0f);
        this.v_4262_N = new J_1907_R(this);
        this.Q_4569_t = this.h_1847_R = this.RealmsWorldOptions.nextFloat();
    }

    @Override
    protected void M_182_A() {
        MoveTowardsRestrictionGoal movetowardsrestrictiongoal = new MoveTowardsRestrictionGoal(this, 1.0);
        this.J_1907_R = new RandomStrollGoal(this, 1.0, 80);
        this.s_956_w.n_1700_B(4, new n_1700_B(this));
        this.s_956_w.n_1700_B(5, movetowardsrestrictiongoal);
        this.s_956_w.n_1700_B(7, this.J_1907_R);
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, G_1455_B.class, 12.0f, 0.01f));
        this.s_956_w.n_1700_B(9, new RandomLookAroundGoal(this));
        this.J_1907_R.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        movetowardsrestrictiongoal.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<r_4811_B>(this, r_4811_B.class, 10, true, false, new R_4764_Y(this)));
    }

    public static s_1415_m.n_1700_B y_2447_C() {
        return Monster.o_4117_e().n_1700_B(Attributes.u_1723_Y, 6.0).n_1700_B(Attributes.G_564_y, 0.5).n_1700_B(Attributes.J_1907_R, 16.0).n_1700_B(Attributes.n_1700_B, 30.0);
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new c_1972_S(this, worldIn);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
        this.l_4537_E.n_1700_B(R_4764_Y, 0);
    }

    @Override
    public boolean P_328_a() {
        return true;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.P_1922_E;
    }

    public boolean J_3635_s() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    private void w_1457_N(boolean moving) {
        this.l_4537_E.J_1907_R(n_1700_B, moving);
    }

    public int y_4642_Y() {
        return 80;
    }

    private void n_1700_B(int entityId) {
        this.l_4537_E.J_1907_R(R_4764_Y, entityId);
    }

    public boolean o_82_k() {
        return this.l_4537_E.n_1700_B(R_4764_Y) != 0;
    }

    @Nullable
    public r_4811_B h_973_D() {
        if (!this.o_82_k()) {
            return null;
        }
        if (this.O_508_d.Y_259_p) {
            if (this.w_1457_N != null) {
                return this.w_1457_N;
            }
            N_4263_v entity = this.O_508_d.J_1907_R(this.l_4537_E.n_1700_B(R_4764_Y));
            if (entity instanceof r_4811_B) {
                this.w_1457_N = (r_4811_B)entity;
                return this.w_1457_N;
            }
            return null;
        }
        return this.t_148_a();
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (R_4764_Y.equals(key)) {
            this.Y_601_j = 0;
            this.w_1457_N = null;
        }
    }

    @Override
    public int v_4276_D() {
        return 160;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.S_980_j() ? SoundEvents.O_3016_i : SoundEvents.b_2037_V;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.S_980_j() ? SoundEvents.p_3749_n : SoundEvents.P_3676_m;
    }

    @Override
    protected SoundEvent u_796_y() {
        return this.S_980_j() ? SoundEvents.k_1608_N : SoundEvents.s_3815_K;
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.5f;
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R) ? 10.0f + worldIn.w_1484_f(pos) - 0.5f : super.n_1700_B(pos, worldIn);
    }

    @Override
    public void Y_1740_V() {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            if (this.O_508_d.Y_259_p) {
                this.Q_4569_t = this.h_1847_R;
                if (!this.RowButton()) {
                    this.M_182_A = 2.0f;
                    e_2866_D vector3d = this.I_4348_c();
                    if (vector3d.R_4764_Y > 0.0 && this.Y_259_p && !this.y_1700_S()) {
                        this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.V_1176_p(), this.r_2478_U(), 1.0f, 1.0f, false);
                    }
                    this.Y_259_p = vector3d.R_4764_Y < 0.0 && this.O_508_d.n_1700_B(this.b_2312_j().down(), (N_4263_v)this);
                } else {
                    this.M_182_A = this.J_3635_s() ? (this.M_182_A < 0.5f ? 4.0f : (this.M_182_A += (0.5f - this.M_182_A) * 0.1f)) : (this.M_182_A += (0.125f - this.M_182_A) * 0.2f);
                }
                this.h_1847_R += this.M_182_A;
                this.multiplayerClientSuggestionProvider = this.t_1786_h;
                this.t_1786_h = !this.S_980_j() ? this.RealmsWorldOptions.nextFloat() : (this.J_3635_s() ? (this.t_1786_h += (0.0f - this.t_1786_h) * 0.25f) : (this.t_1786_h += (1.0f - this.t_1786_h) * 0.06f));
                if (this.J_3635_s() && this.RowButton()) {
                    e_2866_D vector3d1 = this.t_148_a(0.0f);
                    for (int i = 0; i < 2; ++i) {
                        this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, this.G_564_y(0.5) - vector3d1.J_1907_R * 1.5, this.M_766_z() - vector3d1.R_4764_Y * 1.5, this.v_4262_N(0.5) - vector3d1.G_564_y * 1.5, 0.0, 0.0, 0.0);
                    }
                }
                if (this.o_82_k()) {
                    r_4811_B livingentity;
                    if (this.Y_601_j < this.y_4642_Y()) {
                        ++this.Y_601_j;
                    }
                    if ((livingentity = this.h_973_D()) != null) {
                        this.c_3005_b().n_1700_B(livingentity, 90.0f, 90.0f);
                        this.c_3005_b().n_1700_B();
                        double d5 = this.A_4115_X(0.0f);
                        double d0 = livingentity.O_3598_v() - this.O_3598_v();
                        double d1 = livingentity.P_1922_E(0.5) - this.X_2048_Y();
                        double d2 = livingentity.l_2647_k() - this.l_2647_k();
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        double d4 = this.RealmsWorldOptions.nextDouble();
                        while (d4 < d3) {
                            this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, this.O_3598_v() + d0 * (d4 += 1.8 - d5 + this.RealmsWorldOptions.nextDouble() * (1.7 - d5)), this.X_2048_Y() + d1 * d4, this.l_2647_k() + d2 * d4, 0.0, 0.0, 0.0);
                        }
                    }
                }
            }
            if (this.S_980_j()) {
                this.w_1484_f(300);
            } else if (this.e_1992_r) {
                this.v_4262_N(this.I_4348_c().J_1907_R((this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.4f, 0.5, (this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.4f));
                this.p_178_J = this.RealmsWorldOptions.nextFloat() * 360.0f;
                this.e_1992_r = false;
                this.LongRunningTask = true;
            }
            if (this.o_82_k()) {
                this.p_178_J = this.f_3449_S;
            }
        }
        super.Y_1740_V();
    }

    protected SoundEvent V_1176_p() {
        return SoundEvents.N_2266_w;
    }

    public float c_3005_b(float p_175471_1_) {
        return u_530_F.v_4262_N(p_175471_1_, this.Q_4569_t, this.h_1847_R);
    }

    public float H_2857_Y(float p_175469_1_) {
        return u_530_F.v_4262_N(p_175469_1_, this.multiplayerClientSuggestionProvider, this.t_1786_h);
    }

    public float A_4115_X(float p_175477_1_) {
        return ((float)this.Y_601_j + p_175477_1_) / (float)this.y_4642_Y();
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this);
    }

    public static boolean J_1907_R(t_5_h<? extends G_1455_B> p_223329_0_, LevelAccessor p_223329_1_, a_3160_D reason, c_1514_x p_223329_3_, Random p_223329_4_) {
        return !(p_223329_4_.nextInt(20) != 0 && p_223329_1_.v_4262_N(p_223329_3_) || p_223329_1_.x_607_J() == R_2450_T.n_1700_B || reason != a_3160_D.R_4764_Y && !p_223329_1_.getFluidState(p_223329_3_).n_1700_B(FluidTags.J_1907_R));
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (!this.J_3635_s() && !source.Y_601_j() && source.s_956_w() instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)source.s_956_w();
            if (!source.G_564_y()) {
                livingentity.n_1700_B(P_11_z.n_1700_B(this), 2.0f);
            }
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.w_1484_f();
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public int Z_976_R() {
        return 180;
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.w_1457_N() && this.RowButton()) {
            this.n_1700_B(0.1f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.9));
            if (!this.J_3635_s() && this.t_148_a() == null) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.005, 0.0));
            }
        } else {
            super.w_1484_f(travelVector);
        }
    }

    static class J_1907_R
    extends MoveControl {
        private final G_1455_B t_148_a;

        public J_1907_R(G_1455_B guardian) {
            super(guardian);
            this.t_148_a = guardian;
        }

        @Override
        public void n_1700_B() {
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R && !this.t_148_a.e_4240_b().M_588_G()) {
                e_2866_D vector3d = new e_2866_D(this.J_1907_R - this.t_148_a.O_3598_v(), this.R_4764_Y - this.t_148_a.X_2960_b(), this.G_564_y - this.t_148_a.l_2647_k());
                double d0 = vector3d.u_1723_Y();
                double d1 = vector3d.J_1907_R / d0;
                double d2 = vector3d.R_4764_Y / d0;
                double d3 = vector3d.G_564_y / d0;
                float f = (float)(u_530_F.G_564_y(vector3d.G_564_y, vector3d.J_1907_R) * 57.2957763671875) - 90.0f;
                this.t_148_a.C_1162_e = this.t_148_a.p_178_J = this.n_1700_B(this.t_148_a.p_178_J, f, 90.0f);
                float f1 = (float)(this.P_1922_E * this.t_148_a.J_1907_R(Attributes.G_564_y));
                float f2 = u_530_F.v_4262_N(0.125f, this.t_148_a.l_2995_s(), f1);
                this.t_148_a.w_1457_N(f2);
                double d4 = Math.sin((double)(this.t_148_a.RealmsWorldResetDto + this.t_148_a.j_276_v()) * 0.5) * 0.05;
                double d5 = Math.cos(this.t_148_a.p_178_J * ((float)Math.PI / 180));
                double d6 = Math.sin(this.t_148_a.p_178_J * ((float)Math.PI / 180));
                double d7 = Math.sin((double)(this.t_148_a.RealmsWorldResetDto + this.t_148_a.j_276_v()) * 0.75) * 0.05;
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(d4 * d5, d7 * (d6 + d5) * 0.25 + (double)f2 * d2 * 0.1, d4 * d6));
                LookControl lookcontroller = this.t_148_a.c_3005_b();
                double d8 = this.t_148_a.O_3598_v() + d1 * 2.0;
                double d9 = this.t_148_a.X_2048_Y() + d2 / d0;
                double d10 = this.t_148_a.l_2647_k() + d3 * 2.0;
                double d11 = lookcontroller.G_564_y();
                double d12 = lookcontroller.P_1922_E();
                double d13 = lookcontroller.u_1723_Y();
                if (!lookcontroller.R_4764_Y()) {
                    d11 = d8;
                    d12 = d9;
                    d13 = d10;
                }
                this.t_148_a.c_3005_b().n_1700_B(u_530_F.G_564_y(0.125, d11, d8), u_530_F.G_564_y(0.125, d12, d9), u_530_F.G_564_y(0.125, d13, d10), 10.0f, 40.0f);
                this.t_148_a.w_1457_N(true);
            } else {
                this.t_148_a.w_1457_N(0.0f);
                this.t_148_a.w_1457_N(false);
            }
        }
    }

    static class n_1700_B
    extends Goal {
        private final G_1455_B n_1700_B;
        private int J_1907_R;
        private final boolean R_4764_Y;

        public n_1700_B(G_1455_B guardian) {
            this.n_1700_B = guardian;
            this.R_4764_Y = guardian instanceof ElderGuardian;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            return livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen();
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && (this.R_4764_Y || this.n_1700_B.G_564_y((N_4263_v)this.n_1700_B.t_148_a()) > 9.0);
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = -10;
            this.n_1700_B.e_4240_b().h_1847_R();
            this.n_1700_B.c_3005_b().n_1700_B(this.n_1700_B.t_148_a(), 90.0f, 90.0f);
            this.n_1700_B.LongRunningTask = true;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.n_1700_B(0);
            this.n_1700_B.R_4764_Y((r_4811_B)null);
            this.n_1700_B.J_1907_R.w_1484_f();
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            this.n_1700_B.e_4240_b().h_1847_R();
            this.n_1700_B.c_3005_b().n_1700_B(livingentity, 90.0f, 90.0f);
            if (!this.n_1700_B.c_3005_b(livingentity)) {
                this.n_1700_B.R_4764_Y((r_4811_B)null);
            } else {
                ++this.J_1907_R;
                if (this.J_1907_R == 0) {
                    this.n_1700_B.n_1700_B(this.n_1700_B.t_148_a().j_276_v());
                    if (!this.n_1700_B.y_1700_S()) {
                        this.n_1700_B.O_508_d.n_1700_B((N_4263_v)this.n_1700_B, (byte)21);
                    }
                } else if (this.J_1907_R >= this.n_1700_B.y_4642_Y()) {
                    float f = 1.0f;
                    if (this.n_1700_B.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                        f += 2.0f;
                    }
                    if (this.R_4764_Y) {
                        f += 2.0f;
                    }
                    livingentity.n_1700_B(P_11_z.R_4764_Y(this.n_1700_B, this.n_1700_B), f);
                    livingentity.n_1700_B(P_11_z.R_4764_Y(this.n_1700_B), (float)this.n_1700_B.J_1907_R(Attributes.u_1723_Y));
                    this.n_1700_B.R_4764_Y((r_4811_B)null);
                }
                super.P_1922_E();
            }
        }
    }

    static class R_4764_Y
    implements Predicate<r_4811_B> {
        private final G_1455_B n_1700_B;

        public R_4764_Y(G_1455_B guardian) {
            this.n_1700_B = guardian;
        }

        public boolean n_1700_B(@Nullable r_4811_B p_test_1_) {
            return (p_test_1_ instanceof a_3913_L || p_test_1_ instanceof Squid) && p_test_1_.G_564_y((N_4263_v)this.n_1700_B) > 9.0;
        }

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((r_4811_B)object);
        }
    }
}


