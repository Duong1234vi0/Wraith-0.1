/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.D_2364_U;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.MoveControl;
import lightning.product.WorldGenLevel;
import lightning.product.ParticleOptions;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Y_1387_d;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.WorldgenRandom;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.o_4810_o;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1835_e;
import lightning.product.x_282_a;

public class A_268_Q
extends Z_530_i
implements x_1835_e {
    private static final h_256_u<Integer> h_1847_R = C_4114_x.n_1700_B(A_268_Q.class, EntityDataSerializers.J_1907_R);
    public float n_1700_B;
    public float J_1907_R;
    public float R_4764_Y;
    private boolean Q_4569_t;

    public A_268_Q(t_5_h<? extends A_268_Q> type, b_4507_u worldIn) {
        super((t_5_h<? extends Z_530_i>)type, worldIn);
        this.v_4262_N = new P_1922_E(this);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new R_4764_Y(this));
        this.s_956_w.n_1700_B(2, new n_1700_B(this));
        this.s_956_w.n_1700_B(3, new J_1907_R(this));
        this.s_956_w.n_1700_B(5, new G_564_y(this));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, p_213811_1_ -> Math.abs(p_213811_1_.X_2960_b() - this.X_2960_b()) <= 4.0));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, 1);
    }

    protected void n_1700_B(int size, boolean resetHealth) {
        this.l_4537_E.J_1907_R(h_1847_R, size);
        this.t_4219_U();
        this.g_();
        this.n_1700_B(Attributes.n_1700_B).n_1700_B(size * size);
        this.n_1700_B(Attributes.G_564_y).n_1700_B(0.2f + 0.1f * (float)size);
        this.n_1700_B(Attributes.u_1723_Y).n_1700_B(size);
        if (resetHealth) {
            this.t_1786_h(this.L_1733_J());
        }
        this.P_1922_E = size;
    }

    public int o_82_k() {
        return this.l_4537_E.n_1700_B(h_1847_R);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Size", this.o_82_k() - 1);
        compound.n_1700_B("wasOnGround", this.Q_4569_t);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        int i = compound.w_1484_f("Size");
        if (i < 0) {
            i = 0;
        }
        this.n_1700_B(i + 1, false);
        super.J_1907_R(compound);
        this.Q_4569_t = compound.t_1786_h("wasOnGround");
    }

    public boolean h_973_D() {
        return this.o_82_k() <= 1;
    }

    protected ParticleOptions w_1484_f() {
        return ParticleTypes.z_1737_N;
    }

    @Override
    protected boolean B_1668_F() {
        return this.o_82_k() > 0;
    }

    @Override
    public void v_() {
        this.J_1907_R += (this.n_1700_B - this.J_1907_R) * 0.5f;
        this.R_4764_Y = this.J_1907_R;
        super.v_();
        if (this.e_1992_r && !this.Q_4569_t) {
            int i = this.o_82_k();
            for (int j = 0; j < i * 8; ++j) {
                float f = this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
                float f1 = this.RealmsWorldOptions.nextFloat() * 0.5f + 0.5f;
                float f2 = u_530_F.n_1700_B(f) * (float)i * 0.5f * f1;
                float f3 = u_530_F.J_1907_R(f) * (float)i * 0.5f * f1;
                this.O_508_d.n_1700_B(this.w_1484_f(), this.O_3598_v() + (double)f2, this.X_2960_b(), this.l_2647_k() + (double)f3, 0.0, 0.0, 0.0);
            }
            this.n_1700_B(this.y_2447_C(), this.d_4500_Q(), ((this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            this.n_1700_B = -0.5f;
        } else if (!this.e_1992_r && this.Q_4569_t) {
            this.n_1700_B = 1.0f;
        }
        this.Q_4569_t = this.e_1992_r;
        this.y_4642_Y();
    }

    protected void y_4642_Y() {
        this.n_1700_B *= 0.6f;
    }

    protected int Q_4569_t() {
        return this.RealmsWorldOptions.nextInt(20) + 10;
    }

    @Override
    public void g_() {
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        super.g_();
        this.J_1907_R(d0, d1, d2);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (h_1847_R.equals(key)) {
            this.g_();
            this.p_178_J = this.f_3449_S;
            this.C_1162_e = this.f_3449_S;
            if (this.RowButton() && this.RealmsWorldOptions.nextInt(20) == 0) {
                this.c_132_F();
            }
        }
        super.n_1700_B(key);
    }

    public t_5_h<? extends A_268_Q> f_4016_n() {
        return super.f_4016_n();
    }

    @Override
    public void Ops() {
        int i = this.o_82_k();
        if (!this.O_508_d.Y_259_p && i > 1 && this.Z_2812_M()) {
            x_282_a itextcomponent = this.k_2302_P();
            boolean flag = this.n_473_l();
            float f = (float)i / 4.0f;
            int j = i / 2;
            int k = 2 + this.RealmsWorldOptions.nextInt(3);
            for (int l = 0; l < k; ++l) {
                float f1 = ((float)(l % 2) - 0.5f) * f;
                float f2 = ((float)(l / 2) - 0.5f) * f;
                A_268_Q slimeentity = this.f_4016_n().n_1700_B(this.O_508_d);
                if (this.s_2632_s()) {
                    slimeentity.T_3594_S();
                }
                slimeentity.n_1700_B(itextcomponent);
                slimeentity.G_564_y(flag);
                slimeentity.Q_4569_t(this.P_925_e());
                slimeentity.n_1700_B(j, true);
                slimeentity.J_1907_R(this.O_3598_v() + (double)f1, this.X_2960_b() + 0.5, this.l_2647_k() + (double)f2, this.RealmsWorldOptions.nextFloat() * 360.0f, 0.0f);
                this.O_508_d.a_(slimeentity);
            }
        }
        super.Ops();
    }

    @Override
    public void P_1922_E(N_4263_v entityIn) {
        super.P_1922_E(entityIn);
        if (entityIn instanceof D_2364_U && this.h_1640_b()) {
            this.w_1484_f((r_4811_B)entityIn);
        }
    }

    @Override
    public void c_(a_3913_L entityIn) {
        if (this.h_1640_b()) {
            this.w_1484_f(entityIn);
        }
    }

    protected void w_1484_f(r_4811_B entityIn) {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            int i = this.o_82_k();
            if (this.G_564_y((N_4263_v)entityIn) < 0.6 * (double)i * 0.6 * (double)i && this.c_3005_b(entityIn) && entityIn.n_1700_B(P_11_z.R_4764_Y(this), this.V_1176_p())) {
                this.n_1700_B(SoundEvents.z_1100_b, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                this.n_1700_B((r_4811_B)this, (N_4263_v)entityIn);
            }
        }
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.625f * sizeIn.J_1907_R;
    }

    protected boolean h_1640_b() {
        return !this.h_973_D() && this.w_1457_N();
    }

    protected float V_1176_p() {
        return (float)this.J_1907_R(Attributes.u_1723_Y);
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.h_973_D() ? SoundEvents.BlastFurnaceBlock : SoundEvents.b_1557_h;
    }

    @Override
    protected SoundEvent u_796_y() {
        return this.h_973_D() ? SoundEvents.u_954_J : SoundEvents.e_2973_e;
    }

    protected SoundEvent y_2447_C() {
        return this.h_973_D() ? SoundEvents.P_2976_u : SoundEvents.q_4293_E;
    }

    @Override
    protected g_2336_b g_221_o() {
        return this.o_82_k() == 1 ? this.f_4016_n().w_1484_f() : o_4810_o.n_1700_B;
    }

    public static boolean R_4764_Y(t_5_h<A_268_Q> p_223366_0_, LevelAccessor p_223366_1_, a_3160_D reason, c_1514_x p_223366_3_, Random randomIn) {
        if (p_223366_1_.x_607_J() != R_2450_T.n_1700_B) {
            boolean flag;
            if (Objects.equals(p_223366_1_.n_1700_B(p_223366_3_), Optional.of(biomeBiomes.v_4262_N)) && p_223366_3_.getY() > 50 && p_223366_3_.getY() < 70 && randomIn.nextFloat() < 0.5f && randomIn.nextFloat() < p_223366_1_.Y_1740_V() && p_223366_1_.u_2550_I(p_223366_3_) <= randomIn.nextInt(8)) {
                return A_268_Q.n_1700_B(p_223366_0_, p_223366_1_, reason, p_223366_3_, randomIn);
            }
            if (!(p_223366_1_ instanceof WorldGenLevel)) {
                return false;
            }
            Y_1387_d chunkpos = new Y_1387_d(p_223366_3_);
            boolean bl = flag = WorldgenRandom.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y, ((WorldGenLevel)p_223366_1_).n_1700_B(), 987234911L).nextInt(10) == 0;
            if (randomIn.nextInt(10) == 0 && flag && p_223366_3_.getY() < 40) {
                return A_268_Q.n_1700_B(p_223366_0_, p_223366_1_, reason, p_223366_3_, randomIn);
            }
        }
        return false;
    }

    @Override
    protected float d_4500_Q() {
        return 0.4f * (float)this.o_82_k();
    }

    @Override
    public int Z_976_R() {
        return 0;
    }

    protected boolean f_2787_O() {
        return this.o_82_k() > 0;
    }

    @Override
    protected void e_837_t() {
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R, this.E_453_w(), vector3d.G_564_y);
        this.LongRunningTask = true;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        int i = this.RealmsWorldOptions.nextInt(3);
        if (i < 2 && this.RealmsWorldOptions.nextFloat() < 0.5f * difficultyIn.R_4764_Y()) {
            ++i;
        }
        int j = 1 << i;
        this.n_1700_B(j, true);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private float u_1723_Y() {
        float f = this.h_973_D() ? 1.4f : 0.8f;
        return ((this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f) * f;
    }

    protected SoundEvent J_3635_s() {
        return this.h_973_D() ? SoundEvents.T_2915_h : SoundEvents.AbstractBannerBlock;
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return super.n_1700_B(poseIn).n_1700_B(0.255f * (float)this.o_82_k());
    }

    static class P_1922_E
    extends MoveControl {
        private float t_148_a;
        private int s_956_w;
        private final A_268_Q u_2550_I;
        private boolean M_588_G;

        public P_1922_E(A_268_Q slimeIn) {
            super(slimeIn);
            this.u_2550_I = slimeIn;
            this.t_148_a = 180.0f * slimeIn.p_178_J / (float)Math.PI;
        }

        public void n_1700_B(float yRotIn, boolean aggressive) {
            this.t_148_a = yRotIn;
            this.M_588_G = aggressive;
        }

        public void n_1700_B(double speedIn) {
            this.P_1922_E = speedIn;
            this.w_1484_f = MoveControl.n_1700_B.J_1907_R;
        }

        @Override
        public void n_1700_B() {
            this.n_1700_B.f_3449_S = this.n_1700_B.p_178_J = this.n_1700_B(this.n_1700_B.p_178_J, this.t_148_a, 90.0f);
            this.n_1700_B.C_1162_e = this.n_1700_B.p_178_J;
            if (this.w_1484_f != MoveControl.n_1700_B.J_1907_R) {
                this.n_1700_B.C_2741_M(0.0f);
            } else {
                this.w_1484_f = MoveControl.n_1700_B.n_1700_B;
                if (this.n_1700_B.M_1641_O()) {
                    this.n_1700_B.w_1457_N((float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.G_564_y)));
                    if (this.s_956_w-- <= 0) {
                        this.s_956_w = this.u_2550_I.Q_4569_t();
                        if (this.M_588_G) {
                            this.s_956_w /= 3;
                        }
                        this.u_2550_I.t_4043_B().n_1700_B();
                        if (this.u_2550_I.f_2787_O()) {
                            this.u_2550_I.n_1700_B(this.u_2550_I.J_3635_s(), this.u_2550_I.d_4500_Q(), this.u_2550_I.u_1723_Y());
                        }
                    } else {
                        this.u_2550_I.L_1362_X = 0.0f;
                        this.u_2550_I.L_4248_u = 0.0f;
                        this.n_1700_B.w_1457_N(0.0f);
                    }
                } else {
                    this.n_1700_B.w_1457_N((float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.G_564_y)));
                }
            }
        }
    }

    static class R_4764_Y
    extends Goal {
        private final A_268_Q n_1700_B;

        public R_4764_Y(A_268_Q slimeIn) {
            this.n_1700_B = slimeIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
            slimeIn.e_4240_b().R_4764_Y(true);
        }

        @Override
        public boolean n_1700_B() {
            return (this.n_1700_B.RowButton() || this.n_1700_B.W_3464_O()) && this.n_1700_B.A_4115_X() instanceof P_1922_E;
        }

        @Override
        public void P_1922_E() {
            if (this.n_1700_B.M_3508_C().nextFloat() < 0.8f) {
                this.n_1700_B.t_4043_B().n_1700_B();
            }
            ((P_1922_E)this.n_1700_B.A_4115_X()).n_1700_B(1.2);
        }
    }

    static class n_1700_B
    extends Goal {
        private final A_268_Q n_1700_B;
        private int J_1907_R;

        public n_1700_B(A_268_Q slimeIn) {
            this.n_1700_B = slimeIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            if (livingentity == null) {
                return false;
            }
            if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
                return false;
            }
            return livingentity instanceof a_3913_L && ((a_3913_L)livingentity).C_415_h.n_1700_B ? false : this.n_1700_B.A_4115_X() instanceof P_1922_E;
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 300;
            super.R_4764_Y();
        }

        @Override
        public boolean J_1907_R() {
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            if (livingentity == null) {
                return false;
            }
            if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
                return false;
            }
            if (livingentity instanceof a_3913_L && ((a_3913_L)livingentity).C_415_h.n_1700_B) {
                return false;
            }
            return --this.J_1907_R > 0;
        }

        @Override
        public void P_1922_E() {
            this.n_1700_B.n_1700_B((N_4263_v)this.n_1700_B.t_148_a(), 10.0f, 10.0f);
            ((P_1922_E)this.n_1700_B.A_4115_X()).n_1700_B(this.n_1700_B.p_178_J, this.n_1700_B.h_1640_b());
        }
    }

    static class J_1907_R
    extends Goal {
        private final A_268_Q n_1700_B;
        private float J_1907_R;
        private int R_4764_Y;

        public J_1907_R(A_268_Q slimeIn) {
            this.n_1700_B = slimeIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            return this.n_1700_B.t_148_a() == null && (this.n_1700_B.e_1992_r || this.n_1700_B.RowButton() || this.n_1700_B.W_3464_O() || this.n_1700_B.J_1907_R(MobEffects.q_2307_F)) && this.n_1700_B.A_4115_X() instanceof P_1922_E;
        }

        @Override
        public void P_1922_E() {
            if (--this.R_4764_Y <= 0) {
                this.R_4764_Y = 40 + this.n_1700_B.M_3508_C().nextInt(60);
                this.J_1907_R = this.n_1700_B.M_3508_C().nextInt(360);
            }
            ((P_1922_E)this.n_1700_B.A_4115_X()).n_1700_B(this.J_1907_R, false);
        }
    }

    static class G_564_y
    extends Goal {
        private final A_268_Q n_1700_B;

        public G_564_y(A_268_Q slimeIn) {
            this.n_1700_B = slimeIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            return !this.n_1700_B.y_2772_m();
        }

        @Override
        public void P_1922_E() {
            ((P_1922_E)this.n_1700_B.A_4115_X()).n_1700_B(1.0);
        }
    }
}


