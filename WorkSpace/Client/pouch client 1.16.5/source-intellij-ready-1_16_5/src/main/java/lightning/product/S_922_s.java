/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_268_Q;
import lightning.product.FluidTags;
import lightning.product.Attributes;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.Monster;
import lightning.product.o_4810_o;
import lightning.product.r_109_r;
import lightning.product.s_1415_m;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;

public class S_922_s
extends A_268_Q {
    public S_922_s(t_5_h<? extends S_922_s> type, b_4507_u worldIn) {
        super((t_5_h<? extends A_268_Q>)type, worldIn);
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.2f);
    }

    public static boolean J_1907_R(t_5_h<S_922_s> p_223367_0_, LevelAccessor p_223367_1_, a_3160_D p_223367_2_, c_1514_x p_223367_3_, Random p_223367_4_) {
        return p_223367_1_.x_607_J() != R_2450_T.n_1700_B;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this) && !worldIn.G_564_y(this.i_601_W());
    }

    @Override
    protected void n_1700_B(int size, boolean resetHealth) {
        super.n_1700_B(size, resetHealth);
        this.n_1700_B(Attributes.t_148_a).n_1700_B(size * 3);
    }

    @Override
    public float RealmsConfirmScreen() {
        return 1.0f;
    }

    @Override
    protected ParticleOptions w_1484_f() {
        return ParticleTypes.c_3005_b;
    }

    @Override
    protected g_2336_b g_221_o() {
        return this.h_973_D() ? o_4810_o.n_1700_B : this.f_4016_n().w_1484_f();
    }

    @Override
    public boolean RealmsPersistence() {
        return false;
    }

    @Override
    protected int Q_4569_t() {
        return super.Q_4569_t() * 4;
    }

    @Override
    protected void y_4642_Y() {
        this.n_1700_B *= 0.9f;
    }

    @Override
    protected void e_837_t() {
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(vector3d.J_1907_R, this.E_453_w() + (float)this.o_82_k() * 0.1f, vector3d.G_564_y);
        this.LongRunningTask = true;
    }

    @Override
    protected void R_4764_Y(r_109_r<Fluid> fluidTag) {
        if (fluidTag == FluidTags.R_4764_Y) {
            e_2866_D vector3d = this.I_4348_c();
            this.h_1847_R(vector3d.J_1907_R, 0.22f + (float)this.o_82_k() * 0.05f, vector3d.G_564_y);
            this.LongRunningTask = true;
        } else {
            super.R_4764_Y(fluidTag);
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected boolean h_1640_b() {
        return this.w_1457_N();
    }

    @Override
    protected float V_1176_p() {
        return super.V_1176_p() + 2.0f;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.h_973_D() ? SoundEvents.M_1321_u : SoundEvents.f_360_U;
    }

    @Override
    protected SoundEvent u_796_y() {
        return this.h_973_D() ? SoundEvents.q_3386_W : SoundEvents.w_1672_Y;
    }

    @Override
    protected SoundEvent y_2447_C() {
        return this.h_973_D() ? SoundEvents.C_2712_Y : SoundEvents.I_1654_f;
    }

    @Override
    protected SoundEvent J_3635_s() {
        return SoundEvents.Y_4144_v;
    }
}


