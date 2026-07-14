/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.VillagerHeadModel;
import lightning.product.Z_530_i;
import lightning.product.e_4189_z;
import lightning.product.n_1658_l;
import lightning.product.AnimationUtils;

public class ZombieVillagerModel<T extends F_4355_q>
extends n_1658_l<T>
implements VillagerHeadModel {
    private e_4189_z M_588_G;

    public ZombieVillagerModel(float p_i51058_1_, boolean p_i51058_2_) {
        super(p_i51058_1_, 0.0f, 64, p_i51058_2_ ? 32 : 64);
        if (p_i51058_2_) {
            this.n_1700_B = new e_4189_z(this, 0, 0);
            this.n_1700_B.n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 8.0f, 8.0f, p_i51058_1_);
            this.R_4764_Y = new e_4189_z(this, 16, 16);
            this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, p_i51058_1_ + 0.1f);
            this.u_1723_Y = new e_4189_z(this, 0, 16);
            this.u_1723_Y.n_1700_B(-2.0f, 12.0f, 0.0f);
            this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_ + 0.1f);
            this.v_4262_N = new e_4189_z(this, 0, 16);
            this.v_4262_N.t_148_a = true;
            this.v_4262_N.n_1700_B(2.0f, 12.0f, 0.0f);
            this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_ + 0.1f);
        } else {
            this.n_1700_B = new e_4189_z(this, 0, 0);
            this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, p_i51058_1_);
            this.n_1700_B.n_1700_B(24, 0).n_1700_B(-1.0f, -3.0f, -6.0f, 2.0f, 4.0f, 2.0f, p_i51058_1_);
            this.J_1907_R = new e_4189_z(this, 32, 0);
            this.J_1907_R.n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, p_i51058_1_ + 0.5f);
            this.M_588_G = new e_4189_z(this);
            this.M_588_G.n_1700_B(30, 47).n_1700_B(-8.0f, -8.0f, -6.0f, 16.0f, 16.0f, 1.0f, p_i51058_1_);
            this.M_588_G.u_1723_Y = -1.5707964f;
            this.J_1907_R.J_1907_R(this.M_588_G);
            this.R_4764_Y = new e_4189_z(this, 16, 20);
            this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 12.0f, 6.0f, p_i51058_1_);
            this.R_4764_Y.n_1700_B(0, 38).n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 18.0f, 6.0f, p_i51058_1_ + 0.05f);
            this.G_564_y = new e_4189_z(this, 44, 22);
            this.G_564_y.n_1700_B(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_);
            this.G_564_y.n_1700_B(-5.0f, 2.0f, 0.0f);
            this.P_1922_E = new e_4189_z(this, 44, 22);
            this.P_1922_E.t_148_a = true;
            this.P_1922_E.n_1700_B(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_);
            this.P_1922_E.n_1700_B(5.0f, 2.0f, 0.0f);
            this.u_1723_Y = new e_4189_z(this, 0, 22);
            this.u_1723_Y.n_1700_B(-2.0f, 12.0f, 0.0f);
            this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_);
            this.v_4262_N = new e_4189_z(this, 0, 22);
            this.v_4262_N.t_148_a = true;
            this.v_4262_N.n_1700_B(2.0f, 12.0f, 0.0f);
            this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51058_1_);
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        AnimationUtils.n_1700_B(this.P_1922_E, this.G_564_y, ((Z_530_i)entityIn).P_2272_O(), this.h_1847_R, ageInTicks);
    }

    @Override
    public void n_1700_B(boolean p_217146_1_) {
        this.n_1700_B.s_956_w = p_217146_1_;
        this.J_1907_R.s_956_w = p_217146_1_;
        this.M_588_G.s_956_w = p_217146_1_;
    }
}


