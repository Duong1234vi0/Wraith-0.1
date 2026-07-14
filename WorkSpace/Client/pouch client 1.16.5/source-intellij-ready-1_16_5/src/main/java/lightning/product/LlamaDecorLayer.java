/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.Z_3224_L;
import lightning.product.e_933_M;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_4407_j;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.LlamaModel;

public class LlamaDecorLayer
extends RenderLayer<g_4407_j, LlamaModel<g_4407_j>> {
    private static final g_2336_b[] n_1700_B = new g_2336_b[]{new g_2336_b("textures/entity/llama/decor/white.png"), new g_2336_b("textures/entity/llama/decor/orange.png"), new g_2336_b("textures/entity/llama/decor/magenta.png"), new g_2336_b("textures/entity/llama/decor/light_blue.png"), new g_2336_b("textures/entity/llama/decor/yellow.png"), new g_2336_b("textures/entity/llama/decor/lime.png"), new g_2336_b("textures/entity/llama/decor/pink.png"), new g_2336_b("textures/entity/llama/decor/gray.png"), new g_2336_b("textures/entity/llama/decor/light_gray.png"), new g_2336_b("textures/entity/llama/decor/cyan.png"), new g_2336_b("textures/entity/llama/decor/purple.png"), new g_2336_b("textures/entity/llama/decor/blue.png"), new g_2336_b("textures/entity/llama/decor/brown.png"), new g_2336_b("textures/entity/llama/decor/green.png"), new g_2336_b("textures/entity/llama/decor/red.png"), new g_2336_b("textures/entity/llama/decor/black.png")};
    private static final g_2336_b J_1907_R = new g_2336_b("textures/entity/llama/decor/trader_llama.png");
    private final LlamaModel<g_4407_j> R_4764_Y = new LlamaModel(0.5f);

    public LlamaDecorLayer(j_4203_m<g_4407_j, LlamaModel<g_4407_j>> p_i50933_1_) {
        super(p_i50933_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, g_4407_j entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        g_2336_b resourcelocation;
        e_933_M dyecolor = entitylivingbaseIn.Q_4222_k();
        if (dyecolor != null) {
            resourcelocation = n_1700_B[dyecolor.J_1907_R()];
        } else {
            if (!entitylivingbaseIn.s_3815_K()) {
                return;
            }
            resourcelocation = J_1907_R;
        }
        ((LlamaModel)this.getEntityModel()).n_1700_B(this.R_4764_Y);
        this.R_4764_Y.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.G_564_y(resourcelocation));
        this.R_4764_Y.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (g_4407_j)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


