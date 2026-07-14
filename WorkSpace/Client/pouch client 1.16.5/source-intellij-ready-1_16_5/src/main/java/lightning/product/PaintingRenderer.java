/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3871_I;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Painting;
import lightning.product.PaintingTextureManager;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.Motive;
import lightning.product.w_2040_b;
import lightning.product.z_883_p;

public class PaintingRenderer
extends Z_2049_e<Painting> {
    public PaintingRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(Painting entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - entityYaw));
        Motive paintingtype = entityIn.G_564_y;
        float f = 0.0625f;
        matrixStackIn.n_1700_B(0.0625f, 0.0625f, 0.0625f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.J_1907_R(this.n_1700_B(entityIn)));
        PaintingTextureManager paintingspriteuploader = MinecraftClient.A_4115_X().t_4219_U();
        this.n_1700_B(matrixStackIn, ivertexbuilder, entityIn, paintingtype.n_1700_B(), paintingtype.J_1907_R(), paintingspriteuploader.n_1700_B(paintingtype), paintingspriteuploader.R_4764_Y());
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(Painting entity) {
        return MinecraftClient.A_4115_X().t_4219_U().R_4764_Y().u_2550_I().R_4764_Y();
    }

    private void n_1700_B(g_221_o p_229122_1_, D_4792_h p_229122_2_, Painting p_229122_3_, int p_229122_4_, int p_229122_5_, B_3871_I p_229122_6_, B_3871_I p_229122_7_) {
        g_221_o.n_1700_B matrixstack$entry = p_229122_1_.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        float f = (float)(-p_229122_4_) / 2.0f;
        float f1 = (float)(-p_229122_5_) / 2.0f;
        float f2 = 0.5f;
        float f3 = p_229122_7_.u_1723_Y();
        float f4 = p_229122_7_.v_4262_N();
        float f5 = p_229122_7_.w_1484_f();
        float f6 = p_229122_7_.t_148_a();
        float f7 = p_229122_7_.u_1723_Y();
        float f8 = p_229122_7_.v_4262_N();
        float f9 = p_229122_7_.w_1484_f();
        float f10 = p_229122_7_.J_1907_R(1.0);
        float f11 = p_229122_7_.u_1723_Y();
        float f12 = p_229122_7_.n_1700_B(1.0);
        float f13 = p_229122_7_.w_1484_f();
        float f14 = p_229122_7_.t_148_a();
        int i = p_229122_4_ / 16;
        int j = p_229122_5_ / 16;
        double d0 = 16.0 / (double)i;
        double d1 = 16.0 / (double)j;
        for (int k = 0; k < i; ++k) {
            for (int l = 0; l < j; ++l) {
                float f15 = f + (float)((k + 1) * 16);
                float f16 = f + (float)(k * 16);
                float f17 = f1 + (float)((l + 1) * 16);
                float f18 = f1 + (float)(l * 16);
                int i1 = u_530_F.R_4764_Y(p_229122_3_.O_3598_v());
                int j1 = u_530_F.R_4764_Y(p_229122_3_.X_2960_b() + (double)((f17 + f18) / 2.0f / 16.0f));
                int k1 = u_530_F.R_4764_Y(p_229122_3_.l_2647_k());
                b_257_Y direction = p_229122_3_.o_2767_H();
                if (direction == b_257_Y.R_4764_Y) {
                    i1 = u_530_F.R_4764_Y(p_229122_3_.O_3598_v() + (double)((f15 + f16) / 2.0f / 16.0f));
                }
                if (direction == b_257_Y.P_1922_E) {
                    k1 = u_530_F.R_4764_Y(p_229122_3_.l_2647_k() - (double)((f15 + f16) / 2.0f / 16.0f));
                }
                if (direction == b_257_Y.G_564_y) {
                    i1 = u_530_F.R_4764_Y(p_229122_3_.O_3598_v() - (double)((f15 + f16) / 2.0f / 16.0f));
                }
                if (direction == b_257_Y.u_1723_Y) {
                    k1 = u_530_F.R_4764_Y(p_229122_3_.l_2647_k() + (double)((f15 + f16) / 2.0f / 16.0f));
                }
                int l1 = z_883_p.n_1700_B(p_229122_3_.O_508_d, new c_1514_x(i1, j1, k1));
                float f19 = p_229122_6_.n_1700_B(d0 * (double)(i - k));
                float f20 = p_229122_6_.n_1700_B(d0 * (double)(i - (k + 1)));
                float f21 = p_229122_6_.J_1907_R(d1 * (double)(j - l));
                float f22 = p_229122_6_.J_1907_R(d1 * (double)(j - (l + 1)));
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f20, f21, -0.5f, 0, 0, -1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f19, f21, -0.5f, 0, 0, -1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f19, f22, -0.5f, 0, 0, -1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f20, f22, -0.5f, 0, 0, -1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f3, f5, 0.5f, 0, 0, 1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f4, f5, 0.5f, 0, 0, 1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f4, f6, 0.5f, 0, 0, 1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f3, f6, 0.5f, 0, 0, 1, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f7, f9, -0.5f, 0, 1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f8, f9, -0.5f, 0, 1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f8, f10, 0.5f, 0, 1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f7, f10, 0.5f, 0, 1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f7, f9, 0.5f, 0, -1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f8, f9, 0.5f, 0, -1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f8, f10, -0.5f, 0, -1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f7, f10, -0.5f, 0, -1, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f12, f13, 0.5f, -1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f12, f14, 0.5f, -1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f18, f11, f14, -0.5f, -1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f15, f17, f11, f13, -0.5f, -1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f12, f13, -0.5f, 1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f12, f14, -0.5f, 1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f18, f11, f14, 0.5f, 1, 0, 0, l1);
                this.n_1700_B(matrix4f, matrix3f, p_229122_2_, f16, f17, f11, f13, 0.5f, 1, 0, 0, l1);
            }
        }
    }

    private void n_1700_B(D_1098_v p_229121_1_, o_1290_k p_229121_2_, D_4792_h p_229121_3_, float p_229121_4_, float p_229121_5_, float p_229121_6_, float p_229121_7_, float p_229121_8_, int p_229121_9_, int p_229121_10_, int p_229121_11_, int p_229121_12_) {
        p_229121_3_.n_1700_B(p_229121_1_, p_229121_4_, p_229121_5_, p_229121_8_).color(255, 255, 255, 255).tex(p_229121_6_, p_229121_7_).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229121_12_).n_1700_B(p_229121_2_, (float)p_229121_9_, (float)p_229121_10_, (float)p_229121_11_).endVertex();
    }
}



