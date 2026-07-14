/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MobEffects;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.h_384_L;
import lightning.product.k_2610_C;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;

public class SpectralArrow
extends h_384_L {
    private int P_1922_E = 200;

    public SpectralArrow(t_5_h<? extends SpectralArrow> p_i50158_1_, b_4507_u p_i50158_2_) {
        super((t_5_h<? extends h_384_L>)p_i50158_1_, p_i50158_2_);
    }

    public SpectralArrow(b_4507_u worldIn, r_4811_B shooter) {
        super(t_5_h.w_612_n, shooter, worldIn);
    }

    public SpectralArrow(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.w_612_n, x, y, z, worldIn);
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p && !this.n_1700_B) {
            this.O_508_d.n_1700_B(ParticleTypes.n_3318_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected Z_1993_T w_1484_f() {
        return new Z_1993_T(Items.g_2783_J);
    }

    @Override
    protected void n_1700_B(r_4811_B living) {
        super.n_1700_B(living);
        k_2610_C effectinstance = new k_2610_C(MobEffects.k_2293_S, this.P_1922_E, 0);
        living.n_1700_B(effectinstance);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.P_1922_E("Duration")) {
            this.P_1922_E = compound.w_1484_f("Duration");
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Duration", this.P_1922_E);
    }
}


