/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RandomLookAroundGoal;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.ItemUtils;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.TemptGoal;
import lightning.product.FloatGoal;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.x_1688_C;

public class Cow
extends Animal {
    public Cow(t_5_h<? extends Cow> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 2.0));
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(3, new TemptGoal((PathfinderMob)this, 1.25, b_3278_X.n_1700_B(Items.V_3441_j), false));
        this.s_956_w.n_1700_B(4, new v_2621_q(this, 1.25));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(7, new RandomLookAroundGoal(this));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.G_564_y, 0.2f);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.S_3139_t;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.t_3452_g;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.k_2302_P;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.I_1407_m, 0.15f, 1.0f);
    }

    @Override
    protected float d_4500_Q() {
        return 0.4f;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.G_1539_D && !this.d_()) {
            p_230254_1_.n_1700_B(SoundEvents.V_118_c, 1.0f, 1.0f);
            Z_1993_T itemstack1 = ItemUtils.n_1700_B(itemstack, p_230254_1_, Items.H_2506_c.Y_601_j());
            p_230254_1_.n_1700_B(p_230254_2_, itemstack1);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    public Cow J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.M_588_G.n_1700_B(p_241840_1_);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? sizeIn.J_1907_R * 0.95f : 1.3f;
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }
}


