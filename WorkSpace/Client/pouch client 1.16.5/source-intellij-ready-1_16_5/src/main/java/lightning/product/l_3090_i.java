/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.OcelotAttackGoal;
import lightning.product.K_4074_S;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.LeapAtTargetGoal;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.TemptGoal;
import lightning.product.AvoidEntityGoal;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.X_4861_v;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.SimpleParticleType;
import lightning.product.BlockTags;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;

public class l_3090_i
extends Animal {
    private static final b_3278_X h_1847_R = b_3278_X.n_1700_B(Items.ServerAdvancementManager, Items.C_3304_p);
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(l_3090_i.class, EntityDataSerializers.t_148_a);
    private n_1700_B<a_3913_L> M_182_A;
    private J_1907_R t_1786_h;

    public l_3090_i(t_5_h<? extends l_3090_i> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.h_1640_b();
    }

    private boolean V_1176_p() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    private void w_1457_N(boolean trusting) {
        this.l_4537_E.J_1907_R(Q_4569_t, trusting);
        this.h_1640_b();
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Trusting", this.V_1176_p());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("Trusting"));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, false);
    }

    @Override
    protected void M_182_A() {
        this.t_1786_h = new J_1907_R(this, 0.6, h_1847_R, true);
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(3, this.t_1786_h);
        this.s_956_w.n_1700_B(7, new LeapAtTargetGoal(this, 0.3f));
        this.s_956_w.n_1700_B(8, new OcelotAttackGoal(this));
        this.s_956_w.n_1700_B(9, new BreedGoal(this, 0.8));
        this.s_956_w.n_1700_B(10, new g_1941_L((PathfinderMob)this, 0.8, 1.0000001E-5f));
        this.s_956_w.n_1700_B(11, new LookAtPlayerGoal(this, a_3913_L.class, 10.0f));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<X_4861_v>((Z_530_i)this, X_4861_v.class, false));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<t_4149_i>(this, t_4149_i.class, 10, false, false, t_4149_i.h_1847_R));
    }

    @Override
    public void X_933_l() {
        if (this.A_4115_X().J_1907_R()) {
            double d0 = this.A_4115_X().R_4764_Y();
            if (d0 == 0.6) {
                this.J_1907_R(I_1170_F.u_1723_Y);
                this.b_(false);
            } else if (d0 == 1.33) {
                this.J_1907_R(I_1170_F.n_1700_B);
                this.b_(true);
            } else {
                this.J_1907_R(I_1170_F.n_1700_B);
                this.b_(false);
            }
        } else {
            this.J_1907_R(I_1170_F.n_1700_B);
            this.b_(false);
        }
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.V_1176_p() && this.RealmsWorldResetDto > 2400;
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.u_1723_Y, 3.0);
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return SoundEvents.TotemPop;
    }

    @Override
    public int v_4276_D() {
        return 900;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.ThirdPerson;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.Tracers;
    }

    private float y_2447_C() {
        return (float)this.J_1907_R(Attributes.u_1723_Y);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        return entityIn.n_1700_B(P_11_z.R_4764_Y(this), this.y_2447_C());
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return this.n_1700_B(source) ? false : super.n_1700_B(source, amount);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if ((this.t_1786_h == null || this.t_1786_h.w_1484_f()) && !this.V_1176_p() && this.u_2550_I(itemstack) && p_230254_1_.G_564_y((N_4263_v)this) < 9.0) {
            this.n_1700_B(p_230254_1_, itemstack);
            if (!this.O_508_d.Y_259_p) {
                if (this.RealmsWorldOptions.nextInt(3) == 0) {
                    this.w_1457_N(true);
                    this.Y_601_j(true);
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)41);
                } else {
                    this.Y_601_j(false);
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)40);
                }
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 41) {
            this.Y_601_j(true);
        } else if (id == 40) {
            this.Y_601_j(false);
        } else {
            super.n_1700_B(id);
        }
    }

    private void Y_601_j(boolean p_213527_1_) {
        SimpleParticleType iparticledata = ParticleTypes.e_4240_b;
        if (!p_213527_1_) {
            iparticledata = ParticleTypes.B_1668_F;
        }
        for (int i = 0; i < 7; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            this.O_508_d.n_1700_B(iparticledata, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    protected void h_1640_b() {
        if (this.M_182_A == null) {
            this.M_182_A = new n_1700_B<a_3913_L>(this, a_3913_L.class, 16.0f, 0.8, 1.33);
        }
        this.s_956_w.n_1700_B(this.M_182_A);
        if (!this.V_1176_p()) {
            this.s_956_w.n_1700_B(4, this.M_182_A);
        }
    }

    public l_3090_i J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.s_2632_s.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return h_1847_R.n_1700_B(stack);
    }

    public static boolean J_1907_R(t_5_h<l_3090_i> p_223319_0_, LevelAccessor p_223319_1_, a_3160_D p_223319_2_, c_1514_x p_223319_3_, Random p_223319_4_) {
        return p_223319_4_.nextInt(3) != 0;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        if (worldIn.P_1922_E(this) && !worldIn.G_564_y(this.i_601_W())) {
            c_1514_x blockpos = this.b_2312_j();
            if (blockpos.getY() < worldIn.d_2461_k()) {
                return false;
            }
            K_4074_S blockstate = worldIn.getBlockState(blockpos.down());
            if (blockstate.n_1700_B(a_3742_W.t_148_a) || blockstate.n_1700_B(BlockTags.d_2427_y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(1.0f);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.5f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    static class J_1907_R
    extends TemptGoal {
        private final l_3090_i R_4764_Y;

        public J_1907_R(l_3090_i ocelotIn, double speedIn, b_3278_X temptItemsIn, boolean p_i50036_5_) {
            super((PathfinderMob)ocelotIn, speedIn, temptItemsIn, p_i50036_5_);
            this.R_4764_Y = ocelotIn;
        }

        @Override
        protected boolean v_4262_N() {
            return super.v_4262_N() && !this.R_4764_Y.V_1176_p();
        }
    }

    static class n_1700_B<T extends r_4811_B>
    extends AvoidEntityGoal<T> {
        private final l_3090_i t_148_a;

        public n_1700_B(l_3090_i ocelotIn, Class<T> p_i50037_2_, float p_i50037_3_, double p_i50037_4_, double p_i50037_6_) {
            super(ocelotIn, p_i50037_2_, p_i50037_3_, p_i50037_4_, p_i50037_6_, I_408_V.P_1922_E::test);
            this.t_148_a = ocelotIn;
        }

        @Override
        public boolean n_1700_B() {
            return !this.t_148_a.V_1176_p() && super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return !this.t_148_a.V_1176_p() && super.J_1907_R();
        }
    }
}



