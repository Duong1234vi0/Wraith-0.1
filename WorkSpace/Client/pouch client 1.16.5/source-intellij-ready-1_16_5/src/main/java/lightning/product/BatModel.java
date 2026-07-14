/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.Bat;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class BatModel
extends ListModel<Bat> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;

    public BatModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f);
        e_4189_z modelrenderer = new e_4189_z(this, 24, 0);
        modelrenderer.n_1700_B(-4.0f, -6.0f, -2.0f, 3.0f, 4.0f, 1.0f);
        this.n_1700_B.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(this, 24, 0);
        modelrenderer1.t_148_a = true;
        modelrenderer1.n_1700_B(1.0f, -6.0f, -2.0f, 3.0f, 4.0f, 1.0f);
        this.n_1700_B.J_1907_R(modelrenderer1);
        this.J_1907_R = new e_4189_z(this, 0, 16);
        this.J_1907_R.n_1700_B(-3.0f, 4.0f, -3.0f, 6.0f, 12.0f, 6.0f);
        this.J_1907_R.n_1700_B(0, 34).n_1700_B(-5.0f, 16.0f, 0.0f, 10.0f, 6.0f, 1.0f);
        this.R_4764_Y = new e_4189_z(this, 42, 0);
        this.R_4764_Y.n_1700_B(-12.0f, 1.0f, 1.5f, 10.0f, 16.0f, 1.0f);
        this.P_1922_E = new e_4189_z(this, 24, 16);
        this.P_1922_E.n_1700_B(-12.0f, 1.0f, 1.5f);
        this.P_1922_E.n_1700_B(-8.0f, 1.0f, 0.0f, 8.0f, 12.0f, 1.0f);
        this.G_564_y = new e_4189_z(this, 42, 0);
        this.G_564_y.t_148_a = true;
        this.G_564_y.n_1700_B(2.0f, 1.0f, 1.5f, 10.0f, 16.0f, 1.0f);
        this.u_1723_Y = new e_4189_z(this, 24, 16);
        this.u_1723_Y.t_148_a = true;
        this.u_1723_Y.n_1700_B(12.0f, 1.0f, 1.5f);
        this.u_1723_Y.n_1700_B(0.0f, 1.0f, 0.0f, 8.0f, 12.0f, 1.0f);
        this.J_1907_R.J_1907_R(this.R_4764_Y);
        this.J_1907_R.J_1907_R(this.G_564_y);
        this.R_4764_Y.J_1907_R(this.P_1922_E);
        this.G_564_y.J_1907_R(this.u_1723_Y);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R);
    }

    @Override
    public void n_1700_B(Bat entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn.w_1484_f()) {
            this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
            this.n_1700_B.v_4262_N = (float)Math.PI - netHeadYaw * ((float)Math.PI / 180);
            this.n_1700_B.w_1484_f = (float)Math.PI;
            this.n_1700_B.n_1700_B(0.0f, -2.0f, 0.0f);
            this.R_4764_Y.n_1700_B(-3.0f, 0.0f, 3.0f);
            this.G_564_y.n_1700_B(3.0f, 0.0f, 3.0f);
            this.J_1907_R.u_1723_Y = (float)Math.PI;
            this.R_4764_Y.u_1723_Y = -0.15707964f;
            this.R_4764_Y.v_4262_N = -1.2566371f;
            this.P_1922_E.v_4262_N = -1.7278761f;
            this.G_564_y.u_1723_Y = this.R_4764_Y.u_1723_Y;
            this.G_564_y.v_4262_N = -this.R_4764_Y.v_4262_N;
            this.u_1723_Y.v_4262_N = -this.P_1922_E.v_4262_N;
        } else {
            this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
            this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
            this.n_1700_B.w_1484_f = 0.0f;
            this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
            this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f);
            this.G_564_y.n_1700_B(0.0f, 0.0f, 0.0f);
            this.J_1907_R.u_1723_Y = 0.7853982f + u_530_F.J_1907_R(ageInTicks * 0.1f) * 0.15f;
            this.J_1907_R.v_4262_N = 0.0f;
            this.R_4764_Y.v_4262_N = u_530_F.J_1907_R(ageInTicks * 1.3f) * (float)Math.PI * 0.25f;
            this.G_564_y.v_4262_N = -this.R_4764_Y.v_4262_N;
            this.P_1922_E.v_4262_N = this.R_4764_Y.v_4262_N * 0.5f;
            this.u_1723_Y.v_4262_N = -this.R_4764_Y.v_4262_N * 0.5f;
        }
    }
}


