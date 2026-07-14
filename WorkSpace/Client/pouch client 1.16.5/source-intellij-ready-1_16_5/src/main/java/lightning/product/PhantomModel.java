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

public class PhantomModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;

    public PhantomModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 8);
        this.n_1700_B.n_1700_B(-3.0f, -2.0f, -8.0f, 5.0f, 3.0f, 9.0f);
        this.u_1723_Y = new e_4189_z(this, 3, 20);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, 0.0f, 3.0f, 2.0f, 6.0f);
        this.u_1723_Y.n_1700_B(0.0f, -2.0f, 1.0f);
        this.n_1700_B.J_1907_R(this.u_1723_Y);
        this.v_4262_N = new e_4189_z(this, 4, 29);
        this.v_4262_N.n_1700_B(-1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 6.0f);
        this.v_4262_N.n_1700_B(0.0f, 0.5f, 6.0f);
        this.u_1723_Y.J_1907_R(this.v_4262_N);
        this.J_1907_R = new e_4189_z(this, 23, 12);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f, 6.0f, 2.0f, 9.0f);
        this.J_1907_R.n_1700_B(2.0f, -2.0f, -8.0f);
        this.R_4764_Y = new e_4189_z(this, 16, 24);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f, 13.0f, 1.0f, 9.0f);
        this.R_4764_Y.n_1700_B(6.0f, 0.0f, 0.0f);
        this.J_1907_R.J_1907_R(this.R_4764_Y);
        this.G_564_y = new e_4189_z(this, 23, 12);
        this.G_564_y.t_148_a = true;
        this.G_564_y.n_1700_B(-6.0f, 0.0f, 0.0f, 6.0f, 2.0f, 9.0f);
        this.G_564_y.n_1700_B(-3.0f, -2.0f, -8.0f);
        this.P_1922_E = new e_4189_z(this, 16, 24);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(-13.0f, 0.0f, 0.0f, 13.0f, 1.0f, 9.0f);
        this.P_1922_E.n_1700_B(-6.0f, 0.0f, 0.0f);
        this.G_564_y.J_1907_R(this.P_1922_E);
        this.J_1907_R.w_1484_f = 0.1f;
        this.R_4764_Y.w_1484_f = 0.1f;
        this.G_564_y.w_1484_f = -0.1f;
        this.P_1922_E.w_1484_f = -0.1f;
        this.n_1700_B.u_1723_Y = -0.1f;
        e_4189_z modelrenderer = new e_4189_z(this, 0, 0);
        modelrenderer.n_1700_B(-4.0f, -2.0f, -5.0f, 7.0f, 3.0f, 5.0f);
        modelrenderer.n_1700_B(0.0f, 1.0f, -7.0f);
        modelrenderer.u_1723_Y = 0.2f;
        this.n_1700_B.J_1907_R(modelrenderer);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.n_1700_B.J_1907_R(this.G_564_y);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ((float)(((N_4263_v)entityIn).j_276_v() * 3) + ageInTicks) * 0.13f;
        float f1 = 16.0f;
        this.J_1907_R.w_1484_f = u_530_F.J_1907_R(f) * 16.0f * ((float)Math.PI / 180);
        this.R_4764_Y.w_1484_f = u_530_F.J_1907_R(f) * 16.0f * ((float)Math.PI / 180);
        this.G_564_y.w_1484_f = -this.J_1907_R.w_1484_f;
        this.P_1922_E.w_1484_f = -this.R_4764_Y.w_1484_f;
        this.u_1723_Y.u_1723_Y = -(5.0f + u_530_F.J_1907_R(f * 2.0f) * 5.0f) * ((float)Math.PI / 180);
        this.v_4262_N.u_1723_Y = -(5.0f + u_530_F.J_1907_R(f * 2.0f) * 5.0f) * ((float)Math.PI / 180);
    }
}


