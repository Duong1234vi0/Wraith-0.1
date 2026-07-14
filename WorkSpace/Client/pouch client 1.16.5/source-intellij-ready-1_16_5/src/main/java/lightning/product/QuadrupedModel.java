/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.N_4263_v;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class QuadrupedModel<T extends N_4263_v>
extends AgeableListModel<T> {
    protected e_4189_z n_1700_B = new e_4189_z(this, 0, 0);
    protected e_4189_z J_1907_R;
    protected e_4189_z R_4764_Y;
    protected e_4189_z G_564_y;
    protected e_4189_z P_1922_E;
    protected e_4189_z u_1723_Y;

    public QuadrupedModel(int p_i225948_1_, float p_i225948_2_, boolean p_i225948_3_, float p_i225948_4_, float p_i225948_5_, float p_i225948_6_, float p_i225948_7_, int p_i225948_8_) {
        super(p_i225948_3_, p_i225948_4_, p_i225948_5_, p_i225948_6_, p_i225948_7_, p_i225948_8_);
        this.n_1700_B.n_1700_B(-4.0f, -4.0f, -8.0f, 8.0f, 8.0f, 8.0f, p_i225948_2_);
        this.n_1700_B.n_1700_B(0.0f, 18 - p_i225948_1_, -6.0f);
        this.J_1907_R = new e_4189_z(this, 28, 8);
        this.J_1907_R.n_1700_B(-5.0f, -10.0f, -7.0f, 10.0f, 16.0f, 8.0f, p_i225948_2_);
        this.J_1907_R.n_1700_B(0.0f, 17 - p_i225948_1_, 2.0f);
        this.R_4764_Y = new e_4189_z(this, 0, 16);
        this.R_4764_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, (float)p_i225948_1_, 4.0f, p_i225948_2_);
        this.R_4764_Y.n_1700_B(-3.0f, 24 - p_i225948_1_, 7.0f);
        this.G_564_y = new e_4189_z(this, 0, 16);
        this.G_564_y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, (float)p_i225948_1_, 4.0f, p_i225948_2_);
        this.G_564_y.n_1700_B(3.0f, 24 - p_i225948_1_, 7.0f);
        this.P_1922_E = new e_4189_z(this, 0, 16);
        this.P_1922_E.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, (float)p_i225948_1_, 4.0f, p_i225948_2_);
        this.P_1922_E.n_1700_B(-3.0f, 24 - p_i225948_1_, -5.0f);
        this.u_1723_Y = new e_4189_z(this, 0, 16);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, (float)p_i225948_1_, 4.0f, p_i225948_2_);
        this.u_1723_Y.n_1700_B(3.0f, 24 - p_i225948_1_, -5.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.J_1907_R.u_1723_Y = 1.5707964f;
        this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
    }
}


