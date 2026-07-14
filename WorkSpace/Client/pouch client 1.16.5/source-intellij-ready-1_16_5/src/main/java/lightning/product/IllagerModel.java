/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.T_4002_g;
import lightning.product.AbstractIllager;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.k_4231_L;
import lightning.product.AnimationUtils;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_720_O;

public class IllagerModel<T extends AbstractIllager>
extends ListModel<T>
implements T_4002_g,
w_720_O {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;

    public IllagerModel(float scaleFactor, float p_i47227_2_, int textureWidthIn, int textureHeightIn) {
        this.n_1700_B = new e_4189_z(this).J_1907_R(textureWidthIn, textureHeightIn);
        this.n_1700_B.n_1700_B(0.0f, 0.0f + p_i47227_2_, 0.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, scaleFactor);
        this.J_1907_R = new e_4189_z(this, 32, 0).J_1907_R(textureWidthIn, textureHeightIn);
        this.J_1907_R.n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 12.0f, 8.0f, scaleFactor + 0.45f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.J_1907_R.s_956_w = false;
        e_4189_z modelrenderer = new e_4189_z(this).J_1907_R(textureWidthIn, textureHeightIn);
        modelrenderer.n_1700_B(0.0f, p_i47227_2_ - 2.0f, 0.0f);
        modelrenderer.n_1700_B(24, 0).n_1700_B(-1.0f, -1.0f, -6.0f, 2.0f, 4.0f, 2.0f, scaleFactor);
        this.n_1700_B.J_1907_R(modelrenderer);
        this.R_4764_Y = new e_4189_z(this).J_1907_R(textureWidthIn, textureHeightIn);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f + p_i47227_2_, 0.0f);
        this.R_4764_Y.n_1700_B(16, 20).n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 12.0f, 6.0f, scaleFactor);
        this.R_4764_Y.n_1700_B(0, 38).n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 18.0f, 6.0f, scaleFactor + 0.5f);
        this.G_564_y = new e_4189_z(this).J_1907_R(textureWidthIn, textureHeightIn);
        this.G_564_y.n_1700_B(0.0f, 0.0f + p_i47227_2_ + 2.0f, 0.0f);
        this.G_564_y.n_1700_B(44, 22).n_1700_B(-8.0f, -2.0f, -2.0f, 4.0f, 8.0f, 4.0f, scaleFactor);
        e_4189_z modelrenderer1 = new e_4189_z(this, 44, 22).J_1907_R(textureWidthIn, textureHeightIn);
        modelrenderer1.t_148_a = true;
        modelrenderer1.n_1700_B(4.0f, -2.0f, -2.0f, 4.0f, 8.0f, 4.0f, scaleFactor);
        this.G_564_y.J_1907_R(modelrenderer1);
        this.G_564_y.n_1700_B(40, 38).n_1700_B(-4.0f, 2.0f, -2.0f, 8.0f, 4.0f, 4.0f, scaleFactor);
        this.P_1922_E = new e_4189_z(this, 0, 22).J_1907_R(textureWidthIn, textureHeightIn);
        this.P_1922_E.n_1700_B(-2.0f, 12.0f + p_i47227_2_, 0.0f);
        this.P_1922_E.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, scaleFactor);
        this.u_1723_Y = new e_4189_z(this, 0, 22).J_1907_R(textureWidthIn, textureHeightIn);
        this.u_1723_Y.t_148_a = true;
        this.u_1723_Y.n_1700_B(2.0f, 12.0f + p_i47227_2_, 0.0f);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, scaleFactor);
        this.v_4262_N = new e_4189_z(this, 40, 46).J_1907_R(textureWidthIn, textureHeightIn);
        this.v_4262_N.n_1700_B(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, scaleFactor);
        this.v_4262_N.n_1700_B(-5.0f, 2.0f + p_i47227_2_, 0.0f);
        this.w_1484_f = new e_4189_z(this, 40, 46).J_1907_R(textureWidthIn, textureHeightIn);
        this.w_1484_f.t_148_a = true;
        this.w_1484_f.n_1700_B(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, scaleFactor);
        this.w_1484_f.n_1700_B(5.0f, 2.0f + p_i47227_2_, 0.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.R_4764_Y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.G_564_y, (Object)this.v_4262_N, (Object)this.w_1484_f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag;
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.G_564_y.G_564_y = 3.0f;
        this.G_564_y.P_1922_E = -1.0f;
        this.G_564_y.u_1723_Y = -0.75f;
        if (this.Q_4569_t) {
            this.v_4262_N.u_1723_Y = -0.62831855f;
            this.v_4262_N.v_4262_N = 0.0f;
            this.v_4262_N.w_1484_f = 0.0f;
            this.w_1484_f.u_1723_Y = -0.62831855f;
            this.w_1484_f.v_4262_N = 0.0f;
            this.w_1484_f.w_1484_f = 0.0f;
            this.P_1922_E.u_1723_Y = -1.4137167f;
            this.P_1922_E.v_4262_N = 0.31415927f;
            this.P_1922_E.w_1484_f = 0.07853982f;
            this.u_1723_Y.u_1723_Y = -1.4137167f;
            this.u_1723_Y.v_4262_N = -0.31415927f;
            this.u_1723_Y.w_1484_f = -0.07853982f;
        } else {
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 2.0f * limbSwingAmount * 0.5f;
            this.v_4262_N.v_4262_N = 0.0f;
            this.v_4262_N.w_1484_f = 0.0f;
            this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
            this.w_1484_f.v_4262_N = 0.0f;
            this.w_1484_f.w_1484_f = 0.0f;
            this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
            this.P_1922_E.v_4262_N = 0.0f;
            this.P_1922_E.w_1484_f = 0.0f;
            this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
            this.u_1723_Y.v_4262_N = 0.0f;
            this.u_1723_Y.w_1484_f = 0.0f;
        }
        AbstractIllager.n_1700_B abstractillagerentity$armpose = ((AbstractIllager)entityIn).u_1723_Y();
        if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.J_1907_R) {
            if (((r_4811_B)entityIn).A_2714_y().n_1700_B()) {
                AnimationUtils.n_1700_B(this.w_1484_f, this.v_4262_N, true, this.h_1847_R, ageInTicks);
            } else {
                AnimationUtils.n_1700_B(this.v_4262_N, this.w_1484_f, entityIn, this.h_1847_R, ageInTicks);
            }
        } else if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.R_4764_Y) {
            this.v_4262_N.P_1922_E = 0.0f;
            this.v_4262_N.R_4764_Y = -5.0f;
            this.w_1484_f.P_1922_E = 0.0f;
            this.w_1484_f.R_4764_Y = 5.0f;
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(ageInTicks * 0.6662f) * 0.25f;
            this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(ageInTicks * 0.6662f) * 0.25f;
            this.v_4262_N.w_1484_f = 2.3561945f;
            this.w_1484_f.w_1484_f = -2.3561945f;
            this.v_4262_N.v_4262_N = 0.0f;
            this.w_1484_f.v_4262_N = 0.0f;
        } else if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.G_564_y) {
            this.v_4262_N.v_4262_N = -0.1f + this.n_1700_B.v_4262_N;
            this.v_4262_N.u_1723_Y = -1.5707964f + this.n_1700_B.u_1723_Y;
            this.w_1484_f.u_1723_Y = -0.9424779f + this.n_1700_B.u_1723_Y;
            this.w_1484_f.v_4262_N = this.n_1700_B.v_4262_N - 0.4f;
            this.w_1484_f.w_1484_f = 1.5707964f;
        } else if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.P_1922_E) {
            AnimationUtils.n_1700_B(this.v_4262_N, this.w_1484_f, this.n_1700_B, true);
        } else if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.u_1723_Y) {
            AnimationUtils.n_1700_B(this.v_4262_N, this.w_1484_f, entityIn, true);
        } else if (abstractillagerentity$armpose == AbstractIllager.n_1700_B.v_4262_N) {
            this.v_4262_N.P_1922_E = 0.0f;
            this.v_4262_N.R_4764_Y = -5.0f;
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(ageInTicks * 0.6662f) * 0.05f;
            this.v_4262_N.w_1484_f = 2.670354f;
            this.v_4262_N.v_4262_N = 0.0f;
            this.w_1484_f.P_1922_E = 0.0f;
            this.w_1484_f.R_4764_Y = 5.0f;
            this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(ageInTicks * 0.6662f) * 0.05f;
            this.w_1484_f.w_1484_f = -2.3561945f;
            this.w_1484_f.v_4262_N = 0.0f;
        }
        this.G_564_y.s_956_w = flag = abstractillagerentity$armpose == AbstractIllager.n_1700_B.n_1700_B;
        this.w_1484_f.s_956_w = !flag;
        this.v_4262_N.s_956_w = !flag;
    }

    private e_4189_z n_1700_B(k_4231_L p_191216_1_) {
        return p_191216_1_ == k_4231_L.n_1700_B ? this.w_1484_f : this.v_4262_N;
    }

    public e_4189_z J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public e_4189_z R_4764_Y() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(k_4231_L sideIn, g_221_o matrixStackIn) {
        this.n_1700_B(sideIn).n_1700_B(matrixStackIn);
    }
}


