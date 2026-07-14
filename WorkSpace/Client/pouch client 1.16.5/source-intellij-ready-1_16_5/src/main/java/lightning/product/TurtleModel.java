/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.t_4149_i;
import lightning.product.u_530_F;

public class TurtleModel<T extends t_4149_i>
extends QuadrupedModel<T> {
    private final e_4189_z v_4262_N;

    public TurtleModel(float p_i48834_1_) {
        super(12, p_i48834_1_, true, 120.0f, 0.0f, 9.0f, 6.0f, 120);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 3, 0);
        this.n_1700_B.n_1700_B(-3.0f, -1.0f, -3.0f, 6.0f, 5.0f, 6.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 19.0f, -10.0f);
        this.J_1907_R = new e_4189_z(this);
        this.J_1907_R.n_1700_B(7, 37).n_1700_B(-9.5f, 3.0f, -10.0f, 19.0f, 20.0f, 6.0f, 0.0f);
        this.J_1907_R.n_1700_B(31, 1).n_1700_B(-5.5f, 3.0f, -13.0f, 11.0f, 18.0f, 3.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, 11.0f, -10.0f);
        this.v_4262_N = new e_4189_z(this);
        this.v_4262_N.n_1700_B(70, 33).n_1700_B(-4.5f, 3.0f, -14.0f, 9.0f, 18.0f, 1.0f, 0.0f);
        this.v_4262_N.n_1700_B(0.0f, 11.0f, -10.0f);
        boolean i = true;
        this.R_4764_Y = new e_4189_z(this, 1, 23);
        this.R_4764_Y.n_1700_B(-2.0f, 0.0f, 0.0f, 4.0f, 1.0f, 10.0f, 0.0f);
        this.R_4764_Y.n_1700_B(-3.5f, 22.0f, 11.0f);
        this.G_564_y = new e_4189_z(this, 1, 12);
        this.G_564_y.n_1700_B(-2.0f, 0.0f, 0.0f, 4.0f, 1.0f, 10.0f, 0.0f);
        this.G_564_y.n_1700_B(3.5f, 22.0f, 11.0f);
        this.P_1922_E = new e_4189_z(this, 27, 30);
        this.P_1922_E.n_1700_B(-13.0f, 0.0f, -2.0f, 13.0f, 1.0f, 5.0f, 0.0f);
        this.P_1922_E.n_1700_B(-5.0f, 21.0f, -4.0f);
        this.u_1723_Y = new e_4189_z(this, 27, 24);
        this.u_1723_Y.n_1700_B(0.0f, 0.0f, -2.0f, 13.0f, 1.0f, 5.0f, 0.0f);
        this.u_1723_Y.n_1700_B(5.0f, 21.0f, -4.0f);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return Iterables.concat(super.J_1907_R(), (Iterable)ImmutableList.of((Object)this.v_4262_N));
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f * 0.6f) * 0.5f * limbSwingAmount;
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f * 0.6f + (float)Math.PI) * 0.5f * limbSwingAmount;
        this.P_1922_E.w_1484_f = u_530_F.J_1907_R(limbSwing * 0.6662f * 0.6f + (float)Math.PI) * 0.5f * limbSwingAmount;
        this.u_1723_Y.w_1484_f = u_530_F.J_1907_R(limbSwing * 0.6662f * 0.6f) * 0.5f * limbSwingAmount;
        this.P_1922_E.u_1723_Y = 0.0f;
        this.u_1723_Y.u_1723_Y = 0.0f;
        this.P_1922_E.v_4262_N = 0.0f;
        this.u_1723_Y.v_4262_N = 0.0f;
        this.R_4764_Y.v_4262_N = 0.0f;
        this.G_564_y.v_4262_N = 0.0f;
        this.v_4262_N.u_1723_Y = 1.5707964f;
        if (!((N_4263_v)entityIn).RowButton() && ((N_4263_v)entityIn).M_1641_O()) {
            float f = ((t_4149_i)entityIn).h_1640_b() ? 4.0f : 1.0f;
            float f1 = ((t_4149_i)entityIn).h_1640_b() ? 2.0f : 1.0f;
            float f2 = 5.0f;
            this.P_1922_E.v_4262_N = u_530_F.J_1907_R(f * limbSwing * 5.0f + (float)Math.PI) * 8.0f * limbSwingAmount * f1;
            this.P_1922_E.w_1484_f = 0.0f;
            this.u_1723_Y.v_4262_N = u_530_F.J_1907_R(f * limbSwing * 5.0f) * 8.0f * limbSwingAmount * f1;
            this.u_1723_Y.w_1484_f = 0.0f;
            this.R_4764_Y.v_4262_N = u_530_F.J_1907_R(limbSwing * 5.0f + (float)Math.PI) * 3.0f * limbSwingAmount;
            this.R_4764_Y.u_1723_Y = 0.0f;
            this.G_564_y.v_4262_N = u_530_F.J_1907_R(limbSwing * 5.0f) * 3.0f * limbSwingAmount;
            this.G_564_y.u_1723_Y = 0.0f;
        }
        this.v_4262_N.s_956_w = !this.M_182_A && ((t_4149_i)entityIn).y_4642_Y();
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        boolean flag = this.v_4262_N.s_956_w;
        if (flag) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, (double)-0.08f, 0.0);
        }
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        if (flag) {
            matrixStackIn.J_1907_R();
        }
    }
}


