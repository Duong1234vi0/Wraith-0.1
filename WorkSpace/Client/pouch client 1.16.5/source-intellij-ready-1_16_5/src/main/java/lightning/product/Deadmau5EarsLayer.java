/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.X_4340_E;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.u_530_F;

public class Deadmau5EarsLayer
extends RenderLayer<X_4340_E, PlayerModel<X_4340_E>> {
    public Deadmau5EarsLayer(j_4203_m<X_4340_E, PlayerModel<X_4340_E>> p_i50945_1_) {
        super(p_i50945_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, X_4340_E entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if ("deadmau5".equals(entitylivingbaseIn.O_1309_Q().getString()) && entitylivingbaseIn.z_4693_k() && !entitylivingbaseIn.F_3572_x()) {
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(entitylivingbaseIn.g_221_o()));
            int i = o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f);
            for (int j = 0; j < 2; ++j) {
                float f = u_530_F.v_4262_N(partialTicks, entitylivingbaseIn.j_276_v, entitylivingbaseIn.p_178_J) - u_530_F.v_4262_N(partialTicks, entitylivingbaseIn.D_4361_a, entitylivingbaseIn.C_1162_e);
                float f1 = u_530_F.v_4262_N(partialTicks, entitylivingbaseIn.UploadStatus, entitylivingbaseIn.f_4016_n);
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f));
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f1));
                matrixStackIn.n_1700_B((double)(0.375f * (float)(j * 2 - 1)), 0.0, 0.0);
                matrixStackIn.n_1700_B(0.0, -0.375, 0.0);
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-f1));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-f));
                float f2 = 1.3333334f;
                matrixStackIn.n_1700_B(1.3333334f, 1.3333334f, 1.3333334f);
                ((PlayerModel)this.getEntityModel()).n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, i);
                matrixStackIn.J_1907_R();
            }
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (X_4340_E)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


