/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.MoveTowardsRestrictionGoal;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.s_4438_s;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class G_2149_k
extends Monster {
    private float n_1700_B = 0.5f;
    private int J_1907_R;
    private static final h_256_u<Byte> R_4764_Y = C_4114_x.n_1700_B(G_2149_k.class, EntityDataSerializers.n_1700_B);

    public G_2149_k(t_5_h<? extends G_2149_k> type, b_4507_u world) {
        super((t_5_h<? extends Monster>)type, world);
        this.n_1700_B(I_1869_h.w_1484_f, -1.0f);
        this.n_1700_B(I_1869_h.v_4262_N, 8.0f);
        this.n_1700_B(I_1869_h.M_588_G, 0.0f);
        this.n_1700_B(I_1869_h.P_4830_p, 0.0f);
        this.P_1922_E = 10;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(4, new n_1700_B(this));
        this.s_956_w.n_1700_B(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.s_956_w.n_1700_B(7, new g_1941_L((PathfinderMob)this, 1.0, 0.0f));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.u_1723_Y, 6.0).n_1700_B(Attributes.G_564_y, 0.23f).n_1700_B(Attributes.J_1907_R, 48.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(R_4764_Y, (byte)0);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.F_1410_V;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.F_2624_D;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.l_4537_E;
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    public void Y_1740_V() {
        if (!this.e_1992_r && this.I_4348_c().R_4764_Y < 0.0) {
            this.v_4262_N(this.I_4348_c().G_564_y(1.0, 0.6, 1.0));
        }
        if (this.O_508_d.Y_259_p) {
            if (this.RealmsWorldOptions.nextInt(24) == 0 && !this.y_1700_S()) {
                this.O_508_d.n_1700_B(this.O_3598_v() + 0.5, this.X_2960_b() + 0.5, this.l_2647_k() + 0.5, SoundEvents.S_4022_R, this.r_2478_U(), 1.0f + this.RealmsWorldOptions.nextFloat(), this.RealmsWorldOptions.nextFloat() * 0.7f + 0.3f, false);
            }
            for (int i = 0; i < 2; ++i) {
                this.O_508_d.n_1700_B(ParticleTypes.d_2461_k, this.G_564_y(0.5), this.M_766_z(), this.v_4262_N(0.5), 0.0, 0.0, 0.0);
            }
        }
        super.Y_1740_V();
    }

    @Override
    public boolean e_1231_S() {
        return true;
    }

    @Override
    protected void X_933_l() {
        r_4811_B livingentity;
        --this.J_1907_R;
        if (this.J_1907_R <= 0) {
            this.J_1907_R = 100;
            this.n_1700_B = 0.5f + (float)this.RealmsWorldOptions.nextGaussian() * 3.0f;
        }
        if ((livingentity = this.t_148_a()) != null && livingentity.X_2048_Y() > this.X_2048_Y() + (double)this.n_1700_B && this.n_1700_B(livingentity)) {
            e_2866_D vector3d = this.I_4348_c();
            this.v_4262_N(this.I_4348_c().J_1907_R(0.0, ((double)0.3f - vector3d.R_4764_Y) * (double)0.3f, 0.0));
            this.LongRunningTask = true;
        }
        super.X_933_l();
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean RealmsPersistence() {
        return this.y_4642_Y();
    }

    private boolean y_4642_Y() {
        return (this.l_4537_E.n_1700_B(R_4764_Y) & 1) != 0;
    }

    private void w_1457_N(boolean onFire) {
        byte b0 = this.l_4537_E.n_1700_B(R_4764_Y);
        b0 = onFire ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE);
        this.l_4537_E.J_1907_R(R_4764_Y, b0);
    }

    static class n_1700_B
    extends Goal {
        private final G_2149_k n_1700_B;
        private int J_1907_R;
        private int R_4764_Y;
        private int G_564_y;

        public n_1700_B(G_2149_k blazeIn) {
            this.n_1700_B = blazeIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            return livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen() && this.n_1700_B.n_1700_B(livingentity);
        }

        @Override
        public void R_4764_Y() {
            this.J_1907_R = 0;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.w_1457_N(false);
            this.G_564_y = 0;
        }

        @Override
        public void P_1922_E() {
            --this.R_4764_Y;
            r_4811_B livingentity = this.n_1700_B.t_148_a();
            if (livingentity != null) {
                boolean flag = this.n_1700_B.n_3318_d().n_1700_B(livingentity);
                this.G_564_y = flag ? 0 : ++this.G_564_y;
                double d0 = this.n_1700_B.G_564_y((N_4263_v)livingentity);
                if (d0 < 4.0) {
                    if (!flag) {
                        return;
                    }
                    if (this.R_4764_Y <= 0) {
                        this.R_4764_Y = 20;
                        this.n_1700_B.q_2307_F(livingentity);
                    }
                    this.n_1700_B.A_4115_X().n_1700_B(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k(), 1.0);
                } else if (d0 < this.v_4262_N() * this.v_4262_N() && flag) {
                    double d1 = livingentity.O_3598_v() - this.n_1700_B.O_3598_v();
                    double d2 = livingentity.P_1922_E(0.5) - this.n_1700_B.P_1922_E(0.5);
                    double d3 = livingentity.l_2647_k() - this.n_1700_B.l_2647_k();
                    if (this.R_4764_Y <= 0) {
                        ++this.J_1907_R;
                        if (this.J_1907_R == 1) {
                            this.R_4764_Y = 60;
                            this.n_1700_B.w_1457_N(true);
                        } else if (this.J_1907_R <= 4) {
                            this.R_4764_Y = 6;
                        } else {
                            this.R_4764_Y = 100;
                            this.J_1907_R = 0;
                            this.n_1700_B.w_1457_N(false);
                        }
                        if (this.J_1907_R > 1) {
                            float f = u_530_F.R_4764_Y(u_530_F.n_1700_B(d0)) * 0.5f;
                            if (!this.n_1700_B.y_1700_S()) {
                                this.n_1700_B.O_508_d.n_1700_B((a_3913_L)null, 1018, this.n_1700_B.b_2312_j(), 0);
                            }
                            for (int i = 0; i < 1; ++i) {
                                s_4438_s smallfireballentity = new s_4438_s(this.n_1700_B.O_508_d, this.n_1700_B, d1 + this.n_1700_B.M_3508_C().nextGaussian() * (double)f, d2, d3 + this.n_1700_B.M_3508_C().nextGaussian() * (double)f);
                                smallfireballentity.J_1907_R(smallfireballentity.O_3598_v(), this.n_1700_B.P_1922_E(0.5) + 0.5, smallfireballentity.l_2647_k());
                                this.n_1700_B.O_508_d.a_(smallfireballentity);
                            }
                        }
                    }
                    this.n_1700_B.c_3005_b().n_1700_B(livingentity, 10.0f, 10.0f);
                } else if (this.G_564_y < 5) {
                    this.n_1700_B.A_4115_X().n_1700_B(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k(), 1.0);
                }
                super.P_1922_E();
            }
        }

        private double v_4262_N() {
            return this.n_1700_B.J_1907_R(Attributes.J_1907_R);
        }
    }
}


