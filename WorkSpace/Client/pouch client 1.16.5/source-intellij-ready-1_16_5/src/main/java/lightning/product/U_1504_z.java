/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_4536_S;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.e_933_M;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_3275_w;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.v_1296_A;
import net.optifine.Config;
import net.optifine.CustomColors;

public class U_1504_z
extends RenderLayer<G_4536_S, v_1296_A<G_4536_S>> {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/entity/sheep/sheep_fur.png");
    public g_3275_w<G_4536_S> n_1700_B = new g_3275_w();

    public U_1504_z(j_4203_m<G_4536_S, v_1296_A<G_4536_S>> rendererIn) {
        super(rendererIn);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, G_4536_S entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.V_1176_p() && !entitylivingbaseIn.F_3572_x()) {
            float f2;
            float f1;
            float f;
            if (entitylivingbaseIn.t_3452_g() && "jeb_".equals(entitylivingbaseIn.O_1309_Q().J_1907_R())) {
                int i1 = 25;
                int i = entitylivingbaseIn.RealmsWorldResetDto / 25 + entitylivingbaseIn.j_276_v();
                int j = e_933_M.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float)(entitylivingbaseIn.RealmsWorldResetDto % 25) + partialTicks) / 25.0f;
                float[] afloat1 = G_4536_S.n_1700_B(e_933_M.n_1700_B(k));
                float[] afloat2 = G_4536_S.n_1700_B(e_933_M.n_1700_B(l));
                if (Config.isCustomColors()) {
                    afloat1 = CustomColors.getSheepColors(e_933_M.n_1700_B(k), afloat1);
                    afloat2 = CustomColors.getSheepColors(e_933_M.n_1700_B(l), afloat2);
                }
                f = afloat1[0] * (1.0f - f3) + afloat2[0] * f3;
                f1 = afloat1[1] * (1.0f - f3) + afloat2[1] * f3;
                f2 = afloat1[2] * (1.0f - f3) + afloat2[2] * f3;
            } else {
                float[] afloat = G_4536_S.n_1700_B(entitylivingbaseIn.h_1640_b());
                if (Config.isCustomColors()) {
                    afloat = CustomColors.getSheepColors(entitylivingbaseIn.h_1640_b(), afloat);
                }
                f = afloat[0];
                f1 = afloat[1];
                f2 = afloat[2];
            }
            U_1504_z.renderCopyCutoutModel(this.getEntityModel(), this.n_1700_B, J_1907_R, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (G_4536_S)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


