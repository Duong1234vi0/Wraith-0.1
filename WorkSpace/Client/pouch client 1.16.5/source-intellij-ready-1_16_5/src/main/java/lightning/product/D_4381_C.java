/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2534_D;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.MobType;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.q_4706_v;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;

public class D_4381_C
extends U_2534_D {
    private final q_4706_v Q_2552_b = new q_4706_v(this);
    private boolean C_2741_M;
    private int k_2293_S;

    public D_4381_C(t_5_h<? extends D_4381_C> p_i50235_1_, b_4507_u p_i50235_2_) {
        super((t_5_h<? extends U_2534_D>)p_i50235_1_, p_i50235_2_);
    }

    public static s_1415_m.n_1700_B h_1640_b() {
        return D_4381_C.BooleanSetting().n_1700_B(Attributes.n_1700_B, 15.0).n_1700_B(Attributes.G_564_y, 0.2f);
    }

    @Override
    protected void y_4642_Y() {
        this.n_1700_B(Attributes.P_4830_p).n_1700_B(this.O_3016_i());
    }

    @Override
    protected void c_2086_l() {
    }

    @Override
    protected SoundEvent z_4693_k() {
        super.z_4693_k();
        return ((N_4263_v)this).n_1700_B(FluidTags.J_1907_R) ? SoundEvents.Y_3066_B : SoundEvents.g_1096_r;
    }

    @Override
    protected SoundEvent u_796_y() {
        super.u_796_y();
        return SoundEvents.j_2461_G;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        super.P_1922_E(damageSourceIn);
        return SoundEvents.Y_3588_g;
    }

    @Override
    protected SoundEvent F_1410_V() {
        if (this.e_1992_r) {
            if (!this.H_1883_T()) {
                return SoundEvents.Q_1036_Q;
            }
            ++this.Y_259_p;
            if (this.Y_259_p > 5 && this.Y_259_p % 3 == 0) {
                return SoundEvents.C_3528_u;
            }
            if (this.Y_259_p <= 5) {
                return SoundEvents.Q_1036_Q;
            }
        }
        return SoundEvents.r_2687_x;
    }

    @Override
    protected void v_4262_N(float volume) {
        if (this.e_1992_r) {
            super.v_4262_N(0.3f);
        } else {
            super.v_4262_N(Math.min(0.1f, volume * 25.0f));
        }
    }

    @Override
    protected void ModeSetting() {
        if (this.RowButton()) {
            this.n_1700_B(SoundEvents.S_3844_E, 0.4f, 1.0f);
        } else {
            super.ModeSetting();
        }
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    public double s_1671_u() {
        return super.s_1671_u() - 0.1875;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.V_1176_p() && this.k_2293_S++ >= 18000) {
            this.Ops();
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("SkeletonTrap", this.V_1176_p());
        compound.J_1907_R("SkeletonTrapTime", this.k_2293_S);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("SkeletonTrap"));
        this.k_2293_S = compound.w_1484_f("SkeletonTrapTime");
    }

    @Override
    public boolean d_4007_L() {
        return true;
    }

    @Override
    protected float M_2562_s() {
        return 0.96f;
    }

    public boolean V_1176_p() {
        return this.C_2741_M;
    }

    public void w_1457_N(boolean trap) {
        if (trap != this.C_2741_M) {
            this.C_2741_M = trap;
            if (trap) {
                this.s_956_w.n_1700_B(1, this.Q_2552_b);
            } else {
                this.s_956_w.n_1700_B(this.Q_2552_b);
            }
        }
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.PlayerInfo.n_1700_B(p_241840_1_);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (!this.o_4117_e()) {
            return m_3054_I.R_4764_Y;
        }
        if (this.d_()) {
            return super.J_1907_R(p_230254_1_, p_230254_2_);
        }
        if (p_230254_1_.z_3000_g()) {
            this.u_1723_Y(p_230254_1_);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (this.H_1883_T()) {
            return super.J_1907_R(p_230254_1_, p_230254_2_);
        }
        if (!itemstack.n_1700_B()) {
            if (itemstack.J_1907_R() == Items.Z_361_l && !this.G_564_y()) {
                this.u_1723_Y(p_230254_1_);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            m_3054_I actionresulttype = itemstack.n_1700_B(p_230254_1_, (r_4811_B)this, p_230254_2_);
            if (actionresulttype.n_1700_B()) {
                return actionresulttype;
            }
        }
        this.v_4262_N(p_230254_1_);
        return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
    }
}


