/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.SkeletonModel;
import lightning.product.Z_530_i;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;

public class StrayClothingLayer<T extends Z_530_i, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/skeleton/stray_overlay.png");
    private final SkeletonModel<T> J_1907_R = new SkeletonModel(0.25f, true);

    public StrayClothingLayer(j_4203_m<T, M> p_i50919_1_) {
        super(p_i50919_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        StrayClothingLayer.renderCopyCutoutModel(this.getEntityModel(), this.J_1907_R, n_1700_B, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (Z_530_i)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


