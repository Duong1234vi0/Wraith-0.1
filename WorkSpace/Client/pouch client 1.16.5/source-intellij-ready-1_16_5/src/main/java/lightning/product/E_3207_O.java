/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.q_2335_j;
import lightning.product.WolfModel;
import net.optifine.Config;
import net.optifine.CustomColors;

public class E_3207_O
extends RenderLayer<q_2335_j, WolfModel<q_2335_j>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wolf/wolf_collar.png");

    public E_3207_O(j_4203_m<q_2335_j, WolfModel<q_2335_j>> rendererIn) {
        super(rendererIn);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, q_2335_j entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.U_3758_B() && !entitylivingbaseIn.F_3572_x()) {
            float[] afloat = entitylivingbaseIn.y_2447_C().G_564_y();
            if (Config.isCustomColors()) {
                afloat = CustomColors.getWolfCollarColors(entitylivingbaseIn.y_2447_C(), afloat);
            }
            E_3207_O.renderCutoutModel(this.getEntityModel(), n_1700_B, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, afloat[0], afloat[1], afloat[2]);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (q_2335_j)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


