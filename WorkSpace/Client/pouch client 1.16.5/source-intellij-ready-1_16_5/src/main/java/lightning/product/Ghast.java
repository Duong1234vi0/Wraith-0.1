/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Random;
import lightning.product.LargeFireball;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.FlyingMob;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.MoveControl;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1835_e;

public class Ghast
extends FlyingMob
implements x_1835_e {
    private static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(Ghast.class, EntityDataSerializers.t_148_a);
    private int J_1907_R = 1;

    public Ghast(t_5_h<? extends Ghast> type, b_4507_u worldIn) {
        super((t_5_h<? extends FlyingMob>)type, worldIn);
        this.P_1922_E = 5;
        this.v_4262_N = new R_4764_Y(this);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(5, new G_564_y(this));
        this.s_956_w.n_1700_B(7, new J_1907_R(this));
        this.s_956_w.n_1700_B(7, new n_1700_B(this));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, p_213812_1_ -> Math.abs(p_213812_1_.X_2960_b() - this.X_2960_b()) <= 4.0));
    }

    public boolean u_1723_Y() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void w_1457_N(boolean attacking) {
        this.l_4537_E.J_1907_R(n_1700_B, attacking);
    }

    public int w_1484_f() {
        return this.J_1907_R;
    }

    @Override
    protected boolean B_1668_F() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (source.s_956_w() instanceof LargeFireball && source.u_2550_I() instanceof a_3913_L) {
            super.n_1700_B(source, 1000.0f);
            return true;
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
    }

    public static s_1415_m.n_1700_B Q_4569_t() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.J_1907_R, 100.0);
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.o_82_k;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.f_2787_O;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.h_973_D;
    }

    @Override
    protected float d_4500_Q() {
        return 5.0f;
    }

    public static boolean J_1907_R(t_5_h<Ghast> p_223368_0_, LevelAccessor p_223368_1_, a_3160_D reason, c_1514_x p_223368_3_, Random p_223368_4_) {
        return p_223368_1_.x_607_J() != R_2450_T.n_1700_B && p_223368_4_.nextInt(20) == 0 && Ghast.n_1700_B(p_223368_0_, p_223368_1_, reason, p_223368_3_, p_223368_4_);
    }

    @Override
    public int c_4037_x() {
        return 1;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("ExplosionPower", this.J_1907_R);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("ExplosionPower", 99)) {
            this.J_1907_R = compound.w_1484_f("ExplosionPower");
        }
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 2.6f;
    }

    static class R_4764_Y
    extends MoveControl {
        private final Ghast t_148_a;
        private int s_956_w;

        public R_4764_Y(Ghast ghast) {
            super(ghast);
            this.t_148_a = ghast;
        }

        @Override
        public void n_1700_B() {
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R && this.s_956_w-- <= 0) {
                this.s_956_w += this.t_148_a.M_3508_C().nextInt(5) + 2;
                e_2866_D vector3d = new e_2866_D(this.J_1907_R - this.t_148_a.O_3598_v(), this.R_4764_Y - this.t_148_a.X_2960_b(), this.G_564_y - this.t_148_a.l_2647_k());
                double d0 = vector3d.u_1723_Y();
                if (this.n_1700_B(vector3d = vector3d.G_564_y(), u_530_F.P_1922_E(d0))) {
                    this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().P_1922_E(vector3d.n_1700_B(0.1)));
                } else {
                    this.w_1484_f = MoveControl.n_1700_B.n_1700_B;
                }
            }
        }

        private boolean n_1700_B(e_2866_D p_220673_1_, int p_220673_2_) {
            I_4817_s axisalignedbb = this.t_148_a.i_601_W();
            for (int i = 1; i < p_220673_2_; ++i) {
                if (this.t_148_a.O_508_d.a_(this.t_148_a, axisalignedbb = axisalignedbb.offset(p_220673_1_))) continue;
                return false;
            }
            return true;
        }
    }

    static class G_564_y
    extends Goal {
        private final Ghast n_1700_B;

        public G_564_y(Ghast ghast) {
            this.n_1700_B = ghast;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            double d2;
            double d1;
            MoveControl movementcontroller = this.n_1700_B.A_4115_X();
            if (!movementcontroller.J_1907_R()) {
                return true;
            }
            double d0 = movementcontroller.G_564_y() - this.n_1700_B.O_3598_v();
            double d3 = d0 * d0 + (d1 = movementcontroller.P_1922_E() - this.n_1700_B.X_2960_b()) * d1 + (d2 = movementcontroller.u_1723_Y() - this.n_1700_B.l_2647_k()) * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }

        @Override
        public boolean J_1907_R() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            Random random = this.n_1700_B.M_3508_C();
            double d0 = this.n_1700_B.O_3598_v() + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            double d1 = this.n_1700_B.X_2960_b() + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            double d2 = this.n_1700_B.l_2647_k() + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.n_1700_B.A_4115_X().n_1700_B(d0, d1, d2, 1.0);
        }
    }

    static class J_1907_R
    extends Goal {
        private final Ghast n_1700_B;

        public J_1907_R(Ghast ghast) {
            this.n_1700_B = ghast;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            return true;
        }

        @Override
        public void P_1922_E() {
            if (this.n_1700_B.t_148_a() == null) {
                e_2866_D vector3d = this.n_1700_B.I_4348_c();
                this.n_1700_B.C_1162_e = this.n_1700_B.p_178_J = -((float)u_530_F.G_564_y(vector3d.J_1907_R, vector3d.G_564_y)) * 57.295776f;
            } else {
                r_4811_B livingentity = this.n_1700_B.t_148_a();
                double d0 = 64.0;
                if (livingentity.G_564_y((N_4263_v)this.n_1700_B) < 4096.0) {
                    double d1 = livingentity.O_3598_v() - this.n_1700_B.O_3598_v();
                    double d2 = livingentity.l_2647_k() - this.n_1700_B.l_2647_k();
                    this.n_1700_B.C_1162_e = this.n_1700_B.p_178_J = -((float)u_530_F.G_564_y(d1, d2)) * 57.295776f;
                }
            }
        }
    }

    static class n_1700_B
    extends Goal {
        private final Ghast J_1907_R;
        public int n_1700_B;

        public n_1700_B(Ghast ghast) {
            this.J_1907_R = ghast;
        }

        @Override
        public boolean n_1700_B() {
            return this.J_1907_R.t_148_a() != null;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B = 0;
        }

        @Override
        public void G_564_y() {
            this.J_1907_R.w_1457_N(false);
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = this.J_1907_R.t_148_a();
            double d0 = 64.0;
            if (livingentity.G_564_y((N_4263_v)this.J_1907_R) < 4096.0 && this.J_1907_R.c_3005_b(livingentity)) {
                b_4507_u world = this.J_1907_R.O_508_d;
                ++this.n_1700_B;
                if (this.n_1700_B == 10 && !this.J_1907_R.y_1700_S()) {
                    world.n_1700_B((a_3913_L)null, 1015, this.J_1907_R.b_2312_j(), 0);
                }
                if (this.n_1700_B == 20) {
                    double d1 = 4.0;
                    e_2866_D vector3d = this.J_1907_R.t_148_a(1.0f);
                    double d2 = livingentity.O_3598_v() - (this.J_1907_R.O_3598_v() + vector3d.J_1907_R * 4.0);
                    double d3 = livingentity.P_1922_E(0.5) - (0.5 + this.J_1907_R.P_1922_E(0.5));
                    double d4 = livingentity.l_2647_k() - (this.J_1907_R.l_2647_k() + vector3d.G_564_y * 4.0);
                    if (!this.J_1907_R.y_1700_S()) {
                        world.n_1700_B((a_3913_L)null, 1016, this.J_1907_R.b_2312_j(), 0);
                    }
                    LargeFireball fireballentity = new LargeFireball(world, this.J_1907_R, d2, d3, d4);
                    fireballentity.G_564_y = this.J_1907_R.w_1484_f();
                    fireballentity.J_1907_R(this.J_1907_R.O_3598_v() + vector3d.J_1907_R * 4.0, this.J_1907_R.P_1922_E(0.5) + 0.5, fireballentity.l_2647_k() + vector3d.G_564_y * 4.0);
                    world.a_(fireballentity);
                    this.n_1700_B = -40;
                }
            } else if (this.n_1700_B > 0) {
                --this.n_1700_B;
            }
            this.J_1907_R.w_1457_N(this.n_1700_B > 10);
        }
    }
}


