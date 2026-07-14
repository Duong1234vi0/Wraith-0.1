/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.Z_530_i;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.HoglinBase;

public class HoglinModel<T extends Z_530_i>
extends AgeableListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;

    public HoglinModel() {
        super(true, 8.0f, 6.0f, 1.9f, 2.0f, 24.0f);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.G_564_y = new e_4189_z(this);
        this.G_564_y.n_1700_B(0.0f, 7.0f, 0.0f);
        this.G_564_y.n_1700_B(1, 1).n_1700_B(-8.0f, -7.0f, -13.0f, 16.0f, 14.0f, 26.0f);
        this.t_148_a = new e_4189_z(this);
        this.t_148_a.n_1700_B(0.0f, -14.0f, -5.0f);
        this.t_148_a.n_1700_B(90, 33).n_1700_B(0.0f, 0.0f, -9.0f, 0.0f, 10.0f, 19.0f, 0.001f);
        this.G_564_y.J_1907_R(this.t_148_a);
        this.n_1700_B = new e_4189_z(this);
        this.n_1700_B.n_1700_B(0.0f, 2.0f, -12.0f);
        this.n_1700_B.n_1700_B(61, 1).n_1700_B(-7.0f, -3.0f, -19.0f, 14.0f, 6.0f, 19.0f);
        this.J_1907_R = new e_4189_z(this);
        this.J_1907_R.n_1700_B(-6.0f, -2.0f, -3.0f);
        this.J_1907_R.n_1700_B(1, 1).n_1700_B(-6.0f, -1.0f, -2.0f, 6.0f, 1.0f, 4.0f);
        this.J_1907_R.w_1484_f = -0.69813174f;
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.R_4764_Y = new e_4189_z(this);
        this.R_4764_Y.n_1700_B(6.0f, -2.0f, -3.0f);
        this.R_4764_Y.n_1700_B(1, 6).n_1700_B(0.0f, -1.0f, -2.0f, 6.0f, 1.0f, 4.0f);
        this.R_4764_Y.w_1484_f = 0.69813174f;
        this.n_1700_B.J_1907_R(this.R_4764_Y);
        e_4189_z modelrenderer = new e_4189_z(this);
        modelrenderer.n_1700_B(-7.0f, 2.0f, -12.0f);
        modelrenderer.n_1700_B(10, 13).n_1700_B(-1.0f, -11.0f, -1.0f, 2.0f, 11.0f, 2.0f);
        this.n_1700_B.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(this);
        modelrenderer1.n_1700_B(7.0f, 2.0f, -12.0f);
        modelrenderer1.n_1700_B(1, 13).n_1700_B(-1.0f, -11.0f, -1.0f, 2.0f, 11.0f, 2.0f);
        this.n_1700_B.J_1907_R(modelrenderer1);
        this.n_1700_B.u_1723_Y = 0.87266463f;
        int i = 14;
        int j = 11;
        this.P_1922_E = new e_4189_z(this);
        this.P_1922_E.n_1700_B(-4.0f, 10.0f, -8.5f);
        this.P_1922_E.n_1700_B(66, 42).n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 14.0f, 6.0f);
        this.u_1723_Y = new e_4189_z(this);
        this.u_1723_Y.n_1700_B(4.0f, 10.0f, -8.5f);
        this.u_1723_Y.n_1700_B(41, 42).n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 14.0f, 6.0f);
        this.v_4262_N = new e_4189_z(this);
        this.v_4262_N.n_1700_B(-5.0f, 13.0f, 10.0f);
        this.v_4262_N.n_1700_B(21, 45).n_1700_B(-2.5f, 0.0f, -2.5f, 5.0f, 11.0f, 5.0f);
        this.w_1484_f = new e_4189_z(this);
        this.w_1484_f.n_1700_B(5.0f, 13.0f, 10.0f);
        this.w_1484_f.n_1700_B(0, 45).n_1700_B(-2.5f, 0.0f, -2.5f, 5.0f, 11.0f, 5.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.J_1907_R.w_1484_f = -0.69813174f - limbSwingAmount * u_530_F.n_1700_B(limbSwing);
        this.R_4764_Y.w_1484_f = 0.69813174f + limbSwingAmount * u_530_F.n_1700_B(limbSwing);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        int i = ((HoglinBase)entityIn).h_1640_b();
        float f = 1.0f - (float)u_530_F.n_1700_B(10 - 2 * i) / 10.0f;
        this.n_1700_B.u_1723_Y = u_530_F.v_4262_N(f, 0.87266463f, -0.34906584f);
        if (((r_4811_B)entityIn).d_()) {
            this.n_1700_B.G_564_y = u_530_F.v_4262_N(f, 2.0f, 5.0f);
            this.t_148_a.P_1922_E = -3.0f;
        } else {
            this.n_1700_B.G_564_y = 2.0f;
            this.t_148_a.P_1922_E = -7.0f;
        }
        float f1 = 1.2f;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing) * 1.2f * limbSwingAmount;
        this.v_4262_N.u_1723_Y = this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing + (float)Math.PI) * 1.2f * limbSwingAmount;
        this.w_1484_f.u_1723_Y = this.P_1922_E.u_1723_Y;
    }
}


