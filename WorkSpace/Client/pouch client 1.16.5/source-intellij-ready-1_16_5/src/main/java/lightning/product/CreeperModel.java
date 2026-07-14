/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class CreeperModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;

    public CreeperModel() {
        this(0.0f);
    }

    public CreeperModel(float p_i46366_1_) {
        int i = 6;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, p_i46366_1_);
        this.n_1700_B.n_1700_B(0.0f, 6.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 32, 0);
        this.J_1907_R.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, p_i46366_1_ + 0.5f);
        this.J_1907_R.n_1700_B(0.0f, 6.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 16, 16);
        this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, p_i46366_1_);
        this.R_4764_Y.n_1700_B(0.0f, 6.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 0, 16);
        this.G_564_y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, p_i46366_1_);
        this.G_564_y.n_1700_B(-2.0f, 18.0f, 4.0f);
        this.P_1922_E = new e_4189_z(this, 0, 16);
        this.P_1922_E.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, p_i46366_1_);
        this.P_1922_E.n_1700_B(2.0f, 18.0f, 4.0f);
        this.u_1723_Y = new e_4189_z(this, 0, 16);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, p_i46366_1_);
        this.u_1723_Y.n_1700_B(-2.0f, 18.0f, -4.0f);
        this.v_4262_N = new e_4189_z(this, 0, 16);
        this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, p_i46366_1_);
        this.v_4262_N.n_1700_B(2.0f, 18.0f, -4.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
    }
}


