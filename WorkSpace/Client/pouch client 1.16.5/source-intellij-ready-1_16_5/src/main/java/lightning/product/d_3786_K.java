/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.F_4355_q;
import lightning.product.MobEffects;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class d_3786_K
extends F_4355_q {
    public d_3786_K(t_5_h<? extends d_3786_K> type, b_4507_u worldIn) {
        super((t_5_h<? extends F_4355_q>)type, worldIn);
    }

    public static boolean n_1700_B(t_5_h<d_3786_K> p_223334_0_, ServerLevelAccessor p_223334_1_, a_3160_D reason, c_1514_x p_223334_3_, Random p_223334_4_) {
        return d_3786_K.J_1907_R(p_223334_0_, p_223334_1_, reason, p_223334_3_, p_223334_4_) && (reason == a_3160_D.R_4764_Y || p_223334_1_.canSeeSky(p_223334_3_));
    }

    @Override
    protected boolean z_() {
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.E_2115_e;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.n_2689_l;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.I_4683_a;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.g_4841_c;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = super.q_2307_F(entityIn);
        if (flag && this.A_2714_y().n_1700_B() && entityIn instanceof r_4811_B) {
            float f = this.O_508_d.J_1907_R(this.b_2312_j()).J_1907_R();
            ((r_4811_B)entityIn).n_1700_B(new k_2610_C(MobEffects.t_1786_h, 140 * (int)f));
        }
        return flag;
    }

    @Override
    protected boolean J_3635_s() {
        return true;
    }

    @Override
    protected void h_973_D() {
        this.J_1907_R(t_5_h.R_3077_Z);
        if (!this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, 1041, this.b_2312_j(), 0);
        }
    }

    @Override
    protected Z_1993_T y_2447_C() {
        return Z_1993_T.J_1907_R;
    }
}


