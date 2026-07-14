/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CrossedArmsItemLayer;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.WitchModel;
import lightning.product.Z_1993_T;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.Items;
import lightning.product.r_4811_B;

public class O_3276_Y<T extends r_4811_B>
extends CrossedArmsItemLayer<T, WitchModel<T>> {
    public O_3276_Y(j_4203_m<T, WitchModel<T>> p_i50916_1_) {
        super(p_i50916_1_);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack = ((r_4811_B)entitylivingbaseIn).A_2714_y();
        matrixStackIn.n_1700_B();
        if (itemstack.J_1907_R() == Items.j_2461_G) {
            ((WitchModel)this.getEntityModel()).R_4764_Y().n_1700_B(matrixStackIn);
            ((WitchModel)this.getEntityModel()).J_1907_R().n_1700_B(matrixStackIn);
            matrixStackIn.n_1700_B(0.0625, 0.25, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(140.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(10.0f));
            matrixStackIn.n_1700_B(0.0, (double)-0.4f, (double)0.4f);
        }
        super.n_1700_B(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        matrixStackIn.J_1907_R();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


