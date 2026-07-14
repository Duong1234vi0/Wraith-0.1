/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.h_384_L;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public abstract class ArrowRenderer<T extends h_384_L>
extends Z_2049_e<T> {
    public ArrowRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(partialTicks, ((h_384_L)entityIn).j_276_v, ((h_384_L)entityIn).p_178_J) - 90.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.v_4262_N(partialTicks, ((h_384_L)entityIn).UploadStatus, ((h_384_L)entityIn).f_4016_n)));
        boolean i = false;
        float f = 0.0f;
        float f1 = 0.5f;
        float f2 = 0.0f;
        float f3 = 0.15625f;
        float f4 = 0.0f;
        float f5 = 0.15625f;
        float f6 = 0.15625f;
        float f7 = 0.3125f;
        float f8 = 0.05625f;
        float f9 = (float)((h_384_L)entityIn).G_564_y - partialTicks;
        if (f9 > 0.0f) {
            float f10 = -u_530_F.n_1700_B(f9 * 3.0f) * f9;
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f10));
        }
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(45.0f));
        matrixStackIn.n_1700_B(0.05625f, 0.05625f, 0.05625f);
        matrixStackIn.n_1700_B(-4.0, 0.0, 0.0);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.R_4764_Y(this.n_1700_B(entityIn)));
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0f, 0.15625f, -1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0f, 0.3125f, -1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0f, 0.15625f, 1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, packedLightIn);
        this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0f, 0.3125f, 1, 0, 0, packedLightIn);
        for (int j = 0; j < 4; ++j) {
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
            this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -8, -2, 0, 0.0f, 0.0f, 0, 1, 0, packedLightIn);
            this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, 8, -2, 0, 0.5f, 0.0f, 0, 1, 0, packedLightIn);
            this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, 8, 2, 0, 0.5f, 0.15625f, 0, 1, 0, packedLightIn);
            this.n_1700_B(matrix4f, matrix3f, ivertexbuilder, -8, 2, 0, 0.0f, 0.15625f, 0, 1, 0, packedLightIn);
        }
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public void n_1700_B(D_1098_v matrix, o_1290_k normals, D_4792_h vertexBuilder, int offsetX, int offsetY, int offsetZ, float textureX, float textureY, int p_229039_9_, int p_229039_10_, int p_229039_11_, int packedLightIn) {
        vertexBuilder.n_1700_B(matrix, (float)offsetX, (float)offsetY, (float)offsetZ).color(255, 255, 255, 255).tex(textureX, textureY).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(packedLightIn).n_1700_B(normals, (float)p_229039_9_, (float)p_229039_11_, (float)p_229039_10_).endVertex();
    }
}


