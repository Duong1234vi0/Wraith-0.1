/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_2364_U;
import lightning.product.RandomStrollGoal;
import lightning.product.H_2333_J;
import lightning.product.Attributes;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.AbstractIllager;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.SpellcasterIllager;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.RangedAttackMob;
import lightning.product.h_384_L;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.RangedBowAttackGoal;

public class y_2798_W
extends SpellcasterIllager
implements RangedAttackMob {
    private int h_1847_R;
    private final e_2866_D[][] Q_4569_t;

    public y_2798_W(t_5_h<? extends y_2798_W> type, b_4507_u worldIn) {
        super((t_5_h<? extends SpellcasterIllager>)type, worldIn);
        this.P_1922_E = 5;
        this.Q_4569_t = new e_2866_D[2][4];
        for (int i = 0; i < 4; ++i) {
            this.Q_4569_t[0][i] = e_2866_D.n_1700_B;
            this.Q_4569_t[1][i] = e_2866_D.n_1700_B;
        }
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new SpellcasterIllager.n_1700_B(this));
        this.s_956_w.n_1700_B(4, new J_1907_R());
        this.s_956_w.n_1700_B(5, new n_1700_B());
        this.s_956_w.n_1700_B(6, new RangedBowAttackGoal<y_2798_W>(this, 0.5, 20, 15.0f));
        this.s_956_w.n_1700_B(8, new RandomStrollGoal(this, 0.6));
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, a_3913_L.class, 3.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true).n_1700_B(300));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, false).n_1700_B(300));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, false).n_1700_B(300));
    }

    public static s_1415_m.n_1700_B U_1697_c() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.5).n_1700_B(Attributes.J_1907_R, 18.0).n_1700_B(Attributes.n_1700_B, 32.0);
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.R_1796_s));
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void a_() {
        super.a_();
    }

    @Override
    public I_4817_s h_2739_B() {
        return this.i_601_W().grow(3.0, 0.0, 3.0);
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.O_508_d.Y_259_p && this.F_3572_x()) {
            --this.h_1847_R;
            if (this.h_1847_R < 0) {
                this.h_1847_R = 0;
            }
            if (this.RealmsLongRunningMcoTaskScreen != 1 && this.RealmsWorldResetDto % 1200 != 0) {
                if (this.RealmsLongRunningMcoTaskScreen == this.i_2993_w - 1) {
                    this.h_1847_R = 3;
                    for (int k = 0; k < 4; ++k) {
                        this.Q_4569_t[0][k] = this.Q_4569_t[1][k];
                        this.Q_4569_t[1][k] = new e_2866_D(0.0, 0.0, 0.0);
                    }
                }
            } else {
                this.h_1847_R = 3;
                float f = -6.0f;
                int i = 13;
                for (int j = 0; j < 4; ++j) {
                    this.Q_4569_t[0][j] = this.Q_4569_t[1][j];
                    this.Q_4569_t[1][j] = new e_2866_D((double)(-6.0f + (float)this.RealmsWorldOptions.nextInt(13)) * 0.5, Math.max(0, this.RealmsWorldOptions.nextInt(6) - 4), (double)(-6.0f + (float)this.RealmsWorldOptions.nextInt(13)) * 0.5);
                }
                for (int l = 0; l < 16; ++l) {
                    this.O_508_d.n_1700_B(ParticleTypes.u_1723_Y, this.G_564_y(0.5), this.M_766_z(), this.u_1723_Y(0.5), 0.0, 0.0, 0.0);
                }
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.G_3540_E, this.r_2478_U(), 1.0f, 1.0f, false);
            }
        }
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.N_4890_q;
    }

    public e_2866_D[] c_3005_b(float p_193098_1_) {
        if (this.h_1847_R <= 0) {
            return this.Q_4569_t[1];
        }
        double d0 = ((float)this.h_1847_R - p_193098_1_) / 3.0f;
        d0 = Math.pow(d0, 0.25);
        e_2866_D[] avector3d = new e_2866_D[4];
        for (int i = 0; i < 4; ++i) {
            avector3d[i] = this.Q_4569_t[1][i].n_1700_B(1.0 - d0).P_1922_E(this.Q_4569_t[0][i].n_1700_B(d0));
        }
        return avector3d;
    }

    @Override
    public boolean Q_4569_t(N_4263_v entityIn) {
        if (super.Q_4569_t(entityIn)) {
            return true;
        }
        if (entityIn instanceof r_4811_B && ((r_4811_B)entityIn).F_2860_q() == MobType.G_564_y) {
            return this.L_1362_X() == null && entityIn.L_1362_X() == null;
        }
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.N_4890_q;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.z_2311_U;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.Q_1082_O;
    }

    @Override
    protected SoundEvent V_537_k() {
        return SoundEvents.q_2475_j;
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        Z_1993_T itemstack = this.u_1723_Y(this.R_4764_Y(H_2333_J.n_1700_B(this, Items.R_1796_s)));
        h_384_L abstractarrowentity = H_2333_J.n_1700_B(this, itemstack, distanceFactor);
        double d0 = target.O_3598_v() - this.O_3598_v();
        double d1 = target.P_1922_E(0.3333333333333333) - abstractarrowentity.X_2960_b();
        double d2 = target.l_2647_k() - this.l_2647_k();
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        abstractarrowentity.R_4764_Y(d0, d1 + d3 * (double)0.2f, d2, 1.6f, 14 - this.O_508_d.x_607_J().n_1700_B() * 4);
        this.n_1700_B(SoundEvents.V_1824_v, 1.0f, 1.0f / (this.M_3508_C().nextFloat() * 0.4f + 0.8f));
        this.O_508_d.a_(abstractarrowentity);
    }

    @Override
    public AbstractIllager.n_1700_B u_1723_Y() {
        if (this.ModuleCategory()) {
            return AbstractIllager.n_1700_B.R_4764_Y;
        }
        return this.P_2272_O() ? AbstractIllager.n_1700_B.G_564_y : AbstractIllager.n_1700_B.n_1700_B;
    }

    class J_1907_R
    extends SpellcasterIllager.R_4764_Y {
        private J_1907_R() {
            super(y_2798_W.this);
        }

        @Override
        public boolean n_1700_B() {
            if (!super.n_1700_B()) {
                return false;
            }
            return !y_2798_W.this.J_1907_R(MobEffects.h_1847_R);
        }

        @Override
        protected int v_4262_N() {
            return 20;
        }

        @Override
        protected int w_1484_f() {
            return 340;
        }

        @Override
        protected void s_956_w() {
            y_2798_W.this.n_1700_B(new k_2610_C(MobEffects.h_1847_R, 1200));
        }

        @Override
        @Nullable
        protected SoundEvent u_2550_I() {
            return SoundEvents.b_967_P;
        }

        @Override
        protected SpellcasterIllager.J_1907_R M_588_G() {
            return SpellcasterIllager.J_1907_R.P_1922_E;
        }
    }

    class n_1700_B
    extends SpellcasterIllager.R_4764_Y {
        private int P_1922_E;

        private n_1700_B() {
            super(y_2798_W.this);
        }

        @Override
        public boolean n_1700_B() {
            if (!super.n_1700_B()) {
                return false;
            }
            if (y_2798_W.this.t_148_a() == null) {
                return false;
            }
            if (y_2798_W.this.t_148_a().j_276_v() == this.P_1922_E) {
                return false;
            }
            return y_2798_W.this.O_508_d.J_1907_R(y_2798_W.this.b_2312_j()).n_1700_B(R_2450_T.R_4764_Y.ordinal());
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.P_1922_E = y_2798_W.this.t_148_a().j_276_v();
        }

        @Override
        protected int v_4262_N() {
            return 20;
        }

        @Override
        protected int w_1484_f() {
            return 180;
        }

        @Override
        protected void s_956_w() {
            y_2798_W.this.t_148_a().n_1700_B(new k_2610_C(MobEffects.Q_4569_t, 400));
        }

        @Override
        protected SoundEvent u_2550_I() {
            return SoundEvents.e_4654_Y;
        }

        @Override
        protected SpellcasterIllager.J_1907_R M_588_G() {
            return SpellcasterIllager.J_1907_R.u_1723_Y;
        }
    }
}



