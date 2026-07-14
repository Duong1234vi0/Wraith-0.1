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

public class DolphinModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;

    public DolphinModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        float f = 18.0f;
        float f1 = -8.0f;
        this.n_1700_B = new e_4189_z(this, 22, 0);
        this.n_1700_B.n_1700_B(-4.0f, -7.0f, 0.0f, 8.0f, 7.0f, 13.0f);
        this.n_1700_B.n_1700_B(0.0f, 22.0f, -5.0f);
        e_4189_z modelrenderer = new e_4189_z(this, 51, 0);
        modelrenderer.n_1700_B(-0.5f, 0.0f, 8.0f, 1.0f, 4.0f, 5.0f);
        modelrenderer.u_1723_Y = 1.0471976f;
        this.n_1700_B.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(this, 48, 20);
        modelrenderer1.t_148_a = true;
        modelrenderer1.n_1700_B(-0.5f, -4.0f, 0.0f, 1.0f, 4.0f, 7.0f);
        modelrenderer1.n_1700_B(2.0f, -2.0f, 4.0f);
        modelrenderer1.u_1723_Y = 1.0471976f;
        modelrenderer1.w_1484_f = 2.0943952f;
        this.n_1700_B.J_1907_R(modelrenderer1);
        e_4189_z modelrenderer2 = new e_4189_z(this, 48, 20);
        modelrenderer2.n_1700_B(-0.5f, -4.0f, 0.0f, 1.0f, 4.0f, 7.0f);
        modelrenderer2.n_1700_B(-2.0f, -2.0f, 4.0f);
        modelrenderer2.u_1723_Y = 1.0471976f;
        modelrenderer2.w_1484_f = -2.0943952f;
        this.n_1700_B.J_1907_R(modelrenderer2);
        this.J_1907_R = new e_4189_z(this, 0, 19);
        this.J_1907_R.n_1700_B(-2.0f, -2.5f, 0.0f, 4.0f, 5.0f, 11.0f);
        this.J_1907_R.n_1700_B(0.0f, -2.5f, 11.0f);
        this.J_1907_R.u_1723_Y = -0.10471976f;
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.R_4764_Y = new e_4189_z(this, 19, 20);
        this.R_4764_Y.n_1700_B(-5.0f, -0.5f, 0.0f, 10.0f, 1.0f, 6.0f);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 9.0f);
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.J_1907_R.J_1907_R(this.R_4764_Y);
        e_4189_z modelrenderer3 = new e_4189_z(this, 0, 0);
        modelrenderer3.n_1700_B(-4.0f, -3.0f, -3.0f, 8.0f, 7.0f, 6.0f);
        modelrenderer3.n_1700_B(0.0f, -4.0f, -3.0f);
        e_4189_z modelrenderer4 = new e_4189_z(this, 0, 13);
        modelrenderer4.n_1700_B(-1.0f, 2.0f, -7.0f, 2.0f, 2.0f, 4.0f);
        modelrenderer3.J_1907_R(modelrenderer4);
        this.n_1700_B.J_1907_R(modelrenderer3);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        if (N_4263_v.R_4764_Y(((N_4263_v)entityIn).I_4348_c()) > 1.0E-7) {
            this.n_1700_B.u_1723_Y += -0.05f + -0.05f * u_530_F.J_1907_R(ageInTicks * 0.3f);
            this.J_1907_R.u_1723_Y = -0.1f * u_530_F.J_1907_R(ageInTicks * 0.3f);
            this.R_4764_Y.u_1723_Y = -0.2f * u_530_F.J_1907_R(ageInTicks * 0.3f);
        }
    }
}


