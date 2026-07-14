/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.X_1275_n;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class RavagerModel
extends ListModel<X_1275_n> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;

    public RavagerModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        int i = 16;
        float f = 0.0f;
        this.w_1484_f = new e_4189_z(this);
        this.w_1484_f.n_1700_B(0.0f, -7.0f, -1.5f);
        this.w_1484_f.n_1700_B(68, 73).n_1700_B(-5.0f, -1.0f, -18.0f, 10.0f, 10.0f, 18.0f, 0.0f);
        this.n_1700_B = new e_4189_z(this);
        this.n_1700_B.n_1700_B(0.0f, 16.0f, -17.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-8.0f, -20.0f, -14.0f, 16.0f, 20.0f, 16.0f, 0.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-2.0f, -6.0f, -18.0f, 4.0f, 8.0f, 4.0f, 0.0f);
        e_4189_z modelrenderer = new e_4189_z(this);
        modelrenderer.n_1700_B(-10.0f, -14.0f, -8.0f);
        modelrenderer.n_1700_B(74, 55).n_1700_B(0.0f, -14.0f, -2.0f, 2.0f, 14.0f, 4.0f, 0.0f);
        modelrenderer.u_1723_Y = 1.0995574f;
        this.n_1700_B.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(this);
        modelrenderer1.t_148_a = true;
        modelrenderer1.n_1700_B(8.0f, -14.0f, -8.0f);
        modelrenderer1.n_1700_B(74, 55).n_1700_B(0.0f, -14.0f, -2.0f, 2.0f, 14.0f, 4.0f, 0.0f);
        modelrenderer1.u_1723_Y = 1.0995574f;
        this.n_1700_B.J_1907_R(modelrenderer1);
        this.J_1907_R = new e_4189_z(this);
        this.J_1907_R.n_1700_B(0.0f, -2.0f, 2.0f);
        this.J_1907_R.n_1700_B(0, 36).n_1700_B(-8.0f, 0.0f, -16.0f, 16.0f, 3.0f, 16.0f, 0.0f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.w_1484_f.J_1907_R(this.n_1700_B);
        this.R_4764_Y = new e_4189_z(this);
        this.R_4764_Y.n_1700_B(0, 55).n_1700_B(-7.0f, -10.0f, -7.0f, 14.0f, 16.0f, 20.0f, 0.0f);
        this.R_4764_Y.n_1700_B(0, 91).n_1700_B(-6.0f, 6.0f, -7.0f, 12.0f, 13.0f, 18.0f, 0.0f);
        this.R_4764_Y.n_1700_B(0.0f, 1.0f, 2.0f);
        this.G_564_y = new e_4189_z(this, 96, 0);
        this.G_564_y.n_1700_B(-4.0f, 0.0f, -4.0f, 8.0f, 37.0f, 8.0f, 0.0f);
        this.G_564_y.n_1700_B(-8.0f, -13.0f, 18.0f);
        this.P_1922_E = new e_4189_z(this, 96, 0);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(-4.0f, 0.0f, -4.0f, 8.0f, 37.0f, 8.0f, 0.0f);
        this.P_1922_E.n_1700_B(8.0f, -13.0f, 18.0f);
        this.u_1723_Y = new e_4189_z(this, 64, 0);
        this.u_1723_Y.n_1700_B(-4.0f, 0.0f, -4.0f, 8.0f, 37.0f, 8.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-8.0f, -13.0f, -5.0f);
        this.v_4262_N = new e_4189_z(this, 64, 0);
        this.v_4262_N.t_148_a = true;
        this.v_4262_N.n_1700_B(-4.0f, 0.0f, -4.0f, 8.0f, 37.0f, 8.0f, 0.0f);
        this.v_4262_N.n_1700_B(8.0f, -13.0f, -5.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.w_1484_f, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N);
    }

    @Override
    public void n_1700_B(X_1275_n entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.R_4764_Y.u_1723_Y = 1.5707964f;
        float f = 0.4f * limbSwingAmount;
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * f;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * f;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * f;
        this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * f;
    }

    @Override
    public void n_1700_B(X_1275_n entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        int i = entityIn.V_537_k();
        int j = entityIn.ModuleCategory();
        int k = 20;
        int l = entityIn.U_1697_c();
        int i1 = 10;
        if (l > 0) {
            float f = u_530_F.P_1922_E((float)l - partialTick, 10.0f);
            float f1 = (1.0f + f) * 0.5f;
            float f2 = f1 * f1 * f1 * 12.0f;
            float f3 = f2 * u_530_F.n_1700_B(this.w_1484_f.u_1723_Y);
            this.w_1484_f.P_1922_E = -6.5f + f2;
            this.w_1484_f.G_564_y = -7.0f - f3;
            float f4 = u_530_F.n_1700_B(((float)l - partialTick) / 10.0f * (float)Math.PI * 0.25f);
            this.J_1907_R.u_1723_Y = 1.5707964f * f4;
            this.J_1907_R.u_1723_Y = l > 5 ? u_530_F.n_1700_B(((float)(-4 + l) - partialTick) / 4.0f) * (float)Math.PI * 0.4f : 0.15707964f * u_530_F.n_1700_B((float)Math.PI * ((float)l - partialTick) / 10.0f);
        } else {
            float f5 = -1.0f;
            float f6 = -1.0f * u_530_F.n_1700_B(this.w_1484_f.u_1723_Y);
            this.w_1484_f.R_4764_Y = 0.0f;
            this.w_1484_f.G_564_y = -7.0f - f6;
            this.w_1484_f.P_1922_E = 5.5f;
            boolean flag = i > 0;
            this.w_1484_f.u_1723_Y = flag ? 0.2199115f : 0.0f;
            this.J_1907_R.u_1723_Y = (float)Math.PI * (flag ? 0.05f : 0.01f);
            if (flag) {
                double d0 = (double)i / 40.0;
                this.w_1484_f.R_4764_Y = (float)Math.sin(d0 * 10.0) * 3.0f;
            } else if (j > 0) {
                float f7 = u_530_F.n_1700_B(((float)(20 - j) - partialTick) / 20.0f * (float)Math.PI * 0.25f);
                this.J_1907_R.u_1723_Y = 1.5707964f * f7;
            }
        }
    }
}



