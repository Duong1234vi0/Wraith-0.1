/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.D_38_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.J_548_T;
import lightning.product.Snowball;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.AbstractGolem;
import lightning.product.R_1815_U;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Shearable;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.g_1941_L;
import lightning.product.RangedAttackMob;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;
import lightning.product.x_1835_e;

public class N_1077_C
extends AbstractGolem
implements Shearable,
RangedAttackMob {
    private static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(N_1077_C.class, EntityDataSerializers.n_1700_B);

    public N_1077_C(t_5_h<? extends N_1077_C> type, b_4507_u worldIn) {
        super((t_5_h<? extends AbstractGolem>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new J_548_T(this, 1.25, 20, 10.0f));
        this.s_956_w.n_1700_B(2, new g_1941_L((PathfinderMob)this, 1.0, 1.0000001E-5f));
        this.s_956_w.n_1700_B(3, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(4, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new NearestAttackableTargetGoal<Z_530_i>(this, Z_530_i.class, 10, true, false, p_213621_0_ -> p_213621_0_ instanceof x_1835_e));
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 4.0).n_1700_B(Attributes.G_564_y, 0.2f);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)16);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Pumpkin", this.y_4642_Y());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("Pumpkin")) {
            this.w_1457_N(compound.t_1786_h("Pumpkin"));
        }
    }

    @Override
    public boolean e_1231_S() {
        return true;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p) {
            int i = u_530_F.R_4764_Y(this.O_3598_v());
            int j = u_530_F.R_4764_Y(this.X_2960_b());
            int k = u_530_F.R_4764_Y(this.l_2647_k());
            c_1514_x c_1514_x2 = new c_1514_x(i, 0, k);
            c_1514_x c_1514_x3 = new c_1514_x(i, j, k);
            if (this.O_508_d.P_1922_E(c_1514_x2).n_1700_B(c_1514_x3) > 1.0f) {
                this.n_1700_B(P_11_z.R_4764_Y, 1.0f);
            }
            if (!this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                return;
            }
            K_4074_S blockstate = a_3742_W.X_290_I.multiplayerClientSuggestionProvider();
            for (int l = 0; l < 4; ++l) {
                i = u_530_F.R_4764_Y(this.O_3598_v() + (double)((float)(l % 2 * 2 - 1) * 0.25f));
                c_1514_x blockpos = new c_1514_x(i, j = u_530_F.R_4764_Y(this.X_2960_b()), k = u_530_F.R_4764_Y(this.l_2647_k() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25f)));
                if (!this.O_508_d.getBlockState(blockpos).v_4262_N() || !(this.O_508_d.P_1922_E(blockpos).n_1700_B(blockpos) < 0.8f) || !blockstate.n_1700_B((T_1316_M)this.O_508_d, blockpos)) continue;
                this.O_508_d.J_1907_R(blockpos, blockstate);
            }
        }
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        Snowball snowballentity = new Snowball(this.O_508_d, this);
        double d0 = target.X_2048_Y() - (double)1.1f;
        double d1 = target.O_3598_v() - this.O_3598_v();
        double d2 = d0 - snowballentity.X_2960_b();
        double d3 = target.l_2647_k() - this.l_2647_k();
        float f = u_530_F.n_1700_B(d1 * d1 + d3 * d3) * 0.2f;
        snowballentity.R_4764_Y(d1, d2 + (double)f, d3, 1.6f, 12.0f);
        this.n_1700_B(SoundEvents.X_3584_U, 1.0f, 0.4f / (this.M_3508_C().nextFloat() * 0.4f + 0.8f));
        this.O_508_d.a_(snowballentity);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 1.7f;
    }

    @Override
    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.LightPredicate && this.n_1700_B()) {
            this.n_1700_B(D_38_f.w_1484_f);
            if (!this.O_508_d.Y_259_p) {
                itemstack.n_1700_B(1, p_230254_1_, (T p_213622_1_) -> p_213622_1_.G_564_y(p_230254_2_));
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public void n_1700_B(D_38_f category) {
        this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.C_4998_y, category, 1.0f, 1.0f);
        if (!this.O_508_d.v_4276_D()) {
            this.w_1457_N(false);
            this.n_1700_B(new Z_1993_T(Items.T_2971_J), 1.7f);
        }
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && this.y_4642_Y();
    }

    public boolean y_4642_Y() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 0x10) != 0;
    }

    public void w_1457_N(boolean pumpkinEquipped) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        if (pumpkinEquipped) {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 | 0x10));
        } else {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 & 0xFFFFFFEF));
        }
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return SoundEvents.BubbleColumnBlock;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.CactusBlock;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.BushBlock;
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.75f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }
}


