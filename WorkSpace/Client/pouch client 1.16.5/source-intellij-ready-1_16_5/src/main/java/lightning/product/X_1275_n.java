/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.PathNavigation;
import lightning.product.D_2364_U;
import lightning.product.D_3856_V;
import lightning.product.LeavesBlock;
import lightning.product.BlockGetter;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.AbstractIllager;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.i_2099_H;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.EntityTypeTags;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;

public class X_1275_n
extends W_4304_a {
    private static final Predicate<N_4263_v> R_4764_Y = p_213685_0_ -> p_213685_0_.RealmsLongRunningMcoTaskScreen() && !(p_213685_0_ instanceof X_1275_n);
    private int h_1847_R;
    private int Q_4569_t;
    private int M_182_A;

    public X_1275_n(t_5_h<? extends X_1275_n> p_i50197_1_, b_4507_u p_i50197_2_) {
        super((t_5_h<? extends W_4304_a>)p_i50197_1_, p_i50197_2_);
        this.RealmsServerPing = 1.0f;
        this.P_1922_E = 20;
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(4, new n_1700_B());
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 0.4));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
        this.u_2550_I.n_1700_B(2, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(4, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, true));
        this.u_2550_I.n_1700_B(4, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
    }

    @Override
    protected void q_4610_l() {
        boolean flag = !(this.n_3864_h() instanceof Z_530_i) || this.n_3864_h().f_4016_n().n_1700_B(EntityTypeTags.R_4764_Y);
        boolean flag1 = !(this.l_3609_d() instanceof g_1462_f);
        this.s_956_w.n_1700_B(Goal.n_1700_B.n_1700_B, flag);
        this.s_956_w.n_1700_B(Goal.n_1700_B.R_4764_Y, flag && flag1);
        this.s_956_w.n_1700_B(Goal.n_1700_B.J_1907_R, flag);
        this.s_956_w.n_1700_B(Goal.n_1700_B.G_564_y, flag);
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 100.0).n_1700_B(Attributes.G_564_y, 0.3).n_1700_B(Attributes.R_4764_Y, 0.75).n_1700_B(Attributes.u_1723_Y, 12.0).n_1700_B(Attributes.v_4262_N, 1.5).n_1700_B(Attributes.J_1907_R, 32.0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("AttackTick", this.h_1847_R);
        compound.J_1907_R("StunTick", this.Q_4569_t);
        compound.J_1907_R("RoarTick", this.M_182_A);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.h_1847_R = compound.w_1484_f("AttackTick");
        this.Q_4569_t = compound.w_1484_f("StunTick");
        this.M_182_A = compound.w_1484_f("RoarTick");
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.s_4405_m;
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new J_1907_R(this, worldIn);
    }

    @Override
    public int H_1990_U() {
        return 45;
    }

    @Override
    public double s_1671_u() {
        return 2.1;
    }

    @Override
    public boolean g_2268_R() {
        return !this.n_473_l() && this.n_3864_h() instanceof r_4811_B;
    }

    @Override
    @Nullable
    public N_4263_v n_3864_h() {
        return this.o_3599_Z().isEmpty() ? null : this.o_3599_Z().get(0);
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.RealmsLongRunningMcoTaskScreen()) {
            if (this.W_3729_Q()) {
                this.n_1700_B(Attributes.G_564_y).n_1700_B(0.0);
            } else {
                double d0 = this.t_148_a() != null ? 0.35 : 0.3;
                double d1 = this.n_1700_B(Attributes.G_564_y).J_1907_R();
                this.n_1700_B(Attributes.G_564_y).n_1700_B(u_530_F.G_564_y(0.1, d1, d0));
            }
            if (this.D_60_a && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                boolean flag = false;
                I_4817_s axisalignedbb = this.i_601_W().grow(0.2);
                for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(u_530_F.R_4764_Y(axisalignedbb.minX), u_530_F.R_4764_Y(axisalignedbb.minY), u_530_F.R_4764_Y(axisalignedbb.minZ), u_530_F.R_4764_Y(axisalignedbb.maxX), u_530_F.R_4764_Y(axisalignedbb.maxY), u_530_F.R_4764_Y(axisalignedbb.maxZ))) {
                    K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
                    T_2915_h block = blockstate.J_1907_R();
                    if (!(block instanceof LeavesBlock)) continue;
                    flag = this.O_508_d.n_1700_B(blockpos, true, this) || flag;
                }
                if (!flag && this.e_1992_r) {
                    this.e_837_t();
                }
            }
            if (this.M_182_A > 0) {
                --this.M_182_A;
                if (this.M_182_A == 10) {
                    this.Module();
                }
            }
            if (this.h_1847_R > 0) {
                --this.h_1847_R;
            }
            if (this.Q_4569_t > 0) {
                --this.Q_4569_t;
                this.p_1458_L();
                if (this.Q_4569_t == 0) {
                    this.n_1700_B(SoundEvents.ServerAdvancementManager, 1.0f, 1.0f);
                    this.M_182_A = 20;
                }
            }
        }
    }

    private void p_1458_L() {
        if (this.RealmsWorldOptions.nextInt(6) == 0) {
            double d0 = this.O_3598_v() - (double)this.C_415_h() * Math.sin(this.C_1162_e * ((float)Math.PI / 180)) + (this.RealmsWorldOptions.nextDouble() * 0.6 - 0.3);
            double d1 = this.X_2960_b() + (double)this.v_165_F() - 0.3;
            double d2 = this.l_2647_k() + (double)this.C_415_h() * Math.cos(this.C_1162_e * ((float)Math.PI / 180)) + (this.RealmsWorldOptions.nextDouble() * 0.6 - 0.3);
            this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, d0, d1, d2, 0.4980392156862745, 0.5137254901960784, 0.5725490196078431);
        }
    }

    @Override
    protected boolean W_3729_Q() {
        return super.W_3729_Q() || this.h_1847_R > 0 || this.Q_4569_t > 0 || this.M_182_A > 0;
    }

    @Override
    public boolean c_3005_b(N_4263_v entityIn) {
        return this.Q_4569_t <= 0 && this.M_182_A <= 0 ? super.c_3005_b(entityIn) : false;
    }

    @Override
    protected void P_1922_E(r_4811_B entityIn) {
        if (this.M_182_A == 0) {
            if (this.RealmsWorldOptions.nextDouble() < 0.5) {
                this.Q_4569_t = 40;
                this.n_1700_B(SoundEvents.AdvancementList, 1.0f, 1.0f);
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)39);
                entityIn.P_1922_E((N_4263_v)this);
            } else {
                this.n_1700_B((N_4263_v)entityIn);
            }
            entityIn.Ops = true;
        }
    }

    private void Module() {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            for (N_4263_v entity : this.O_508_d.n_1700_B(r_4811_B.class, this.i_601_W().grow(4.0), R_4764_Y)) {
                if (!(entity instanceof AbstractIllager)) {
                    entity.n_1700_B(P_11_z.R_4764_Y(this), 6.0f);
                }
                this.n_1700_B(entity);
            }
            e_2866_D vector3d = this.i_601_W().getCenter();
            for (int i = 0; i < 40; ++i) {
                double d0 = this.RealmsWorldOptions.nextGaussian() * 0.2;
                double d1 = this.RealmsWorldOptions.nextGaussian() * 0.2;
                double d2 = this.RealmsWorldOptions.nextGaussian() * 0.2;
                this.O_508_d.n_1700_B(ParticleTypes.z_4693_k, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, d0, d1, d2);
            }
        }
    }

    private void n_1700_B(N_4263_v p_213688_1_) {
        double d0 = p_213688_1_.O_3598_v() - this.O_3598_v();
        double d1 = p_213688_1_.l_2647_k() - this.l_2647_k();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
        p_213688_1_.w_1484_f(d0 / d2 * 4.0, 0.2, d1 / d2 * 4.0);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 4) {
            this.h_1847_R = 10;
            this.n_1700_B(SoundEvents.y_4842_Z, 1.0f, 1.0f);
        } else if (id == 39) {
            this.Q_4569_t = 40;
        }
        super.n_1700_B(id);
    }

    public int U_1697_c() {
        return this.h_1847_R;
    }

    public int V_537_k() {
        return this.Q_4569_t;
    }

    public int ModuleCategory() {
        return this.M_182_A;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        this.h_1847_R = 10;
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)4);
        this.n_1700_B(SoundEvents.y_4842_Z, 1.0f, 1.0f);
        return super.q_2307_F(entityIn);
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return SoundEvents.k_1366_K;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.w_2223_C;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.X_1303_p;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.A_2629_w, 0.15f, 1.0f);
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return !worldIn.G_564_y(this.i_601_W());
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
    }

    @Override
    public boolean c_2086_l() {
        return false;
    }

    class n_1700_B
    extends b_4953_N {
        public n_1700_B() {
            super(X_1275_n.this, 1.0, true);
        }

        @Override
        protected double n_1700_B(r_4811_B attackTarget) {
            float f = X_1275_n.this.C_415_h() - 0.1f;
            return f * 2.0f * f * 2.0f + attackTarget.C_415_h();
        }
    }

    static class J_1907_R
    extends i_2099_H {
        public J_1907_R(Z_530_i p_i50754_1_, b_4507_u p_i50754_2_) {
            super(p_i50754_1_, p_i50754_2_);
        }

        @Override
        protected D_3856_V n_1700_B(int p_179679_1_) {
            this.M_182_A = new R_4764_Y();
            return new D_3856_V(this.M_182_A, p_179679_1_);
        }
    }

    static class R_4764_Y
    extends Z_535_q {
        private R_4764_Y() {
        }

        @Override
        protected I_1869_h n_1700_B(BlockGetter p_215744_1_, boolean p_215744_2_, boolean p_215744_3_, c_1514_x p_215744_4_, I_1869_h p_215744_5_) {
            return p_215744_5_ == I_1869_h.Q_2552_b ? I_1869_h.J_1907_R : super.n_1700_B(p_215744_1_, p_215744_2_, p_215744_3_, p_215744_4_, p_215744_5_);
        }
    }
}



