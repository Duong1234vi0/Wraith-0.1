/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.DrownedModel;
import lightning.product.S_3848_S;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;

public class DrownedOuterLayer<T extends S_3848_S>
extends RenderLayer<T, DrownedModel<T>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie/drowned_outer_layer.png");
    private final DrownedModel<T> J_1907_R = new DrownedModel(0.25f, 0.0f, 64, 64);

    public DrownedOuterLayer(j_4203_m<T, DrownedModel<T>> p_i50943_1_) {
        super(p_i50943_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        DrownedOuterLayer.renderCopyCutoutModel(this.getEntityModel(), this.J_1907_R, n_1700_B, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (S_3848_S)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


