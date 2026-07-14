/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_434_g;
import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.e_933_M;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.m_1605_o;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.ShulkerModel;
import lightning.product.w_3785_E;

public class y_3560_r
extends RenderLayer<m_1605_o, ShulkerModel<m_1605_o>> {
    public y_3560_r(j_4203_m<m_1605_o, ShulkerModel<m_1605_o>> p_i50924_1_) {
        super(p_i50924_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, m_1605_o entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, 1.0, 0.0);
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        w_3785_E quaternion = entitylivingbaseIn.h_1640_b().u_1723_Y().J_1907_R();
        quaternion.P_1922_E();
        matrixStackIn.n_1700_B(quaternion);
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixStackIn.n_1700_B(0.0, -1.0, 0.0);
        e_933_M dyecolor = entitylivingbaseIn.f_2787_O();
        g_2336_b resourcelocation = dyecolor == null ? D_434_g.n_1700_B : D_434_g.t_1786_h[dyecolor.J_1907_R()];
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(resourcelocation));
        ((ShulkerModel)this.getEntityModel()).G_564_y().n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f));
        matrixStackIn.J_1907_R();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (m_1605_o)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


