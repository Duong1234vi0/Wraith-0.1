/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.P_11_z;
import lightning.product.U_2534_D;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.MobType;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;

public class ZombieHorse
extends U_2534_D {
    public ZombieHorse(t_5_h<? extends ZombieHorse> p_i50233_1_, b_4507_u p_i50233_2_) {
        super((t_5_h<? extends U_2534_D>)p_i50233_1_, p_i50233_2_);
    }

    public static s_1415_m.n_1700_B h_1640_b() {
        return ZombieHorse.BooleanSetting().n_1700_B(Attributes.n_1700_B, 15.0).n_1700_B(Attributes.G_564_y, 0.2f);
    }

    @Override
    protected void y_4642_Y() {
        this.n_1700_B(Attributes.P_4830_p).n_1700_B(this.O_3016_i());
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    protected SoundEvent z_4693_k() {
        super.z_4693_k();
        return SoundEvents.T_1808_R;
    }

    @Override
    protected SoundEvent u_796_y() {
        super.u_796_y();
        return SoundEvents.u_4834_E;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        super.P_1922_E(damageSourceIn);
        return SoundEvents.WallTorchBlock;
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.RealmsScreenWithCallback.n_1700_B(p_241840_1_);
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

    @Override
    protected void c_2086_l() {
    }
}


