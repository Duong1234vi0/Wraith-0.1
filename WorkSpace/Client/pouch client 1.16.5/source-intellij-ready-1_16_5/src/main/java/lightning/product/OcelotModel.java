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

public class OcelotModel<T extends N_4263_v>
extends AgeableListModel<T> {
    protected final e_4189_z n_1700_B;
    protected final e_4189_z J_1907_R;
    protected final e_4189_z R_4764_Y;
    protected final e_4189_z G_564_y;
    protected final e_4189_z P_1922_E;
    protected final e_4189_z u_1723_Y;
    protected final e_4189_z v_4262_N = new e_4189_z(this);
    protected final e_4189_z w_1484_f;
    protected int t_148_a = 1;

    public OcelotModel(float p_i51064_1_) {
        super(true, 10.0f, 4.0f);
        this.v_4262_N.n_1700_B("main", -2.5f, -2.0f, -3.0f, 5, 4, 5, p_i51064_1_, 0, 0);
        this.v_4262_N.n_1700_B("nose", -1.5f, 0.0f, -4.0f, 3, 2, 2, p_i51064_1_, 0, 24);
        this.v_4262_N.n_1700_B("ear1", -2.0f, -3.0f, 0.0f, 1, 1, 2, p_i51064_1_, 0, 10);
        this.v_4262_N.n_1700_B("ear2", 1.0f, -3.0f, 0.0f, 1, 1, 2, p_i51064_1_, 6, 10);
        this.v_4262_N.n_1700_B(0.0f, 15.0f, -9.0f);
        this.w_1484_f = new e_4189_z(this, 20, 0);
        this.w_1484_f.n_1700_B(-2.0f, 3.0f, -8.0f, 4.0f, 16.0f, 6.0f, p_i51064_1_);
        this.w_1484_f.n_1700_B(0.0f, 12.0f, -10.0f);
        this.P_1922_E = new e_4189_z(this, 0, 15);
        this.P_1922_E.n_1700_B(-0.5f, 0.0f, 0.0f, 1.0f, 8.0f, 1.0f, p_i51064_1_);
        this.P_1922_E.u_1723_Y = 0.9f;
        this.P_1922_E.n_1700_B(0.0f, 15.0f, 8.0f);
        this.u_1723_Y = new e_4189_z(this, 4, 15);
        this.u_1723_Y.n_1700_B(-0.5f, 0.0f, 0.0f, 1.0f, 8.0f, 1.0f, p_i51064_1_);
        this.u_1723_Y.n_1700_B(0.0f, 20.0f, 14.0f);
        this.n_1700_B = new e_4189_z(this, 8, 13);
        this.n_1700_B.n_1700_B(-1.0f, 0.0f, 1.0f, 2.0f, 6.0f, 2.0f, p_i51064_1_);
        this.n_1700_B.n_1700_B(1.1f, 18.0f, 5.0f);
        this.J_1907_R = new e_4189_z(this, 8, 13);
        this.J_1907_R.n_1700_B(-1.0f, 0.0f, 1.0f, 2.0f, 6.0f, 2.0f, p_i51064_1_);
        this.J_1907_R.n_1700_B(-1.1f, 18.0f, 5.0f);
        this.R_4764_Y = new e_4189_z(this, 40, 0);
        this.R_4764_Y.n_1700_B(-1.0f, 0.0f, 0.0f, 2.0f, 10.0f, 2.0f, p_i51064_1_);
        this.R_4764_Y.n_1700_B(1.2f, 14.1f, -5.0f);
        this.G_564_y = new e_4189_z(this, 40, 0);
        this.G_564_y.n_1700_B(-1.0f, 0.0f, 0.0f, 2.0f, 10.0f, 2.0f, p_i51064_1_);
        this.G_564_y.n_1700_B(-1.2f, 14.1f, -5.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.v_4262_N);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.w_1484_f, (Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.v_4262_N.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.v_4262_N.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        if (this.t_148_a != 3) {
            this.w_1484_f.u_1723_Y = 1.5707964f;
            if (this.t_148_a == 2) {
                this.n_1700_B.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * limbSwingAmount;
                this.J_1907_R.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + 0.3f) * limbSwingAmount;
                this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI + 0.3f) * limbSwingAmount;
                this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * limbSwingAmount;
                this.u_1723_Y.u_1723_Y = 1.7278761f + 0.31415927f * u_530_F.J_1907_R(limbSwing) * limbSwingAmount;
            } else {
                this.n_1700_B.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * limbSwingAmount;
                this.J_1907_R.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * limbSwingAmount;
                this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * limbSwingAmount;
                this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * limbSwingAmount;
                this.u_1723_Y.u_1723_Y = this.t_148_a == 1 ? 1.7278761f + 0.7853982f * u_530_F.J_1907_R(limbSwing) * limbSwingAmount : 1.7278761f + 0.47123894f * u_530_F.J_1907_R(limbSwing) * limbSwingAmount;
            }
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.w_1484_f.G_564_y = 12.0f;
        this.w_1484_f.P_1922_E = -10.0f;
        this.v_4262_N.G_564_y = 15.0f;
        this.v_4262_N.P_1922_E = -9.0f;
        this.P_1922_E.G_564_y = 15.0f;
        this.P_1922_E.P_1922_E = 8.0f;
        this.u_1723_Y.G_564_y = 20.0f;
        this.u_1723_Y.P_1922_E = 14.0f;
        this.R_4764_Y.G_564_y = 14.1f;
        this.R_4764_Y.P_1922_E = -5.0f;
        this.G_564_y.G_564_y = 14.1f;
        this.G_564_y.P_1922_E = -5.0f;
        this.n_1700_B.G_564_y = 18.0f;
        this.n_1700_B.P_1922_E = 5.0f;
        this.J_1907_R.G_564_y = 18.0f;
        this.J_1907_R.P_1922_E = 5.0f;
        this.P_1922_E.u_1723_Y = 0.9f;
        if (((N_4263_v)entityIn).Z_875_P()) {
            this.w_1484_f.G_564_y += 1.0f;
            this.v_4262_N.G_564_y += 2.0f;
            this.P_1922_E.G_564_y += 1.0f;
            this.u_1723_Y.G_564_y += -4.0f;
            this.u_1723_Y.P_1922_E += 2.0f;
            this.P_1922_E.u_1723_Y = 1.5707964f;
            this.u_1723_Y.u_1723_Y = 1.5707964f;
            this.t_148_a = 0;
        } else if (((N_4263_v)entityIn).o_2341_D()) {
            this.u_1723_Y.G_564_y = this.P_1922_E.G_564_y;
            this.u_1723_Y.P_1922_E += 2.0f;
            this.P_1922_E.u_1723_Y = 1.5707964f;
            this.u_1723_Y.u_1723_Y = 1.5707964f;
            this.t_148_a = 2;
        } else {
            this.t_148_a = 1;
        }
    }
}


