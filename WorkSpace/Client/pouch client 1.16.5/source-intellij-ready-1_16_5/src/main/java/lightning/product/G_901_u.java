/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RenderLayer;
import lightning.product.K_550_M;
import lightning.product.N_4263_v;
import lightning.product.CatModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import net.optifine.Config;
import net.optifine.CustomColors;

public class G_901_u
extends RenderLayer<K_550_M, CatModel<K_550_M>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/cat/cat_collar.png");
    private final CatModel<K_550_M> J_1907_R = new CatModel(0.01f);

    public G_901_u(j_4203_m<K_550_M, CatModel<K_550_M>> p_i50948_1_) {
        super(p_i50948_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, K_550_M entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.U_3758_B()) {
            float[] afloat = entitylivingbaseIn.J_3635_s().G_564_y();
            if (Config.isCustomColors()) {
                afloat = CustomColors.getWolfCollarColors(entitylivingbaseIn.J_3635_s(), afloat);
            }
            G_901_u.renderCopyCutoutModel(this.getEntityModel(), this.J_1907_R, n_1700_B, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, afloat[0], afloat[1], afloat[2]);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (K_550_M)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


