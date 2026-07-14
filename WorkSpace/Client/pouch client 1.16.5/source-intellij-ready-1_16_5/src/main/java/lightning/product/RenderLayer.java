/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;

public abstract class RenderLayer<T extends N_4263_v, M extends EntityModel<T>> {
    private final j_4203_m<T, M> entityRenderer;

    public RenderLayer(j_4203_m<T, M> entityRendererIn) {
        this.entityRenderer = entityRendererIn;
    }

    protected static <T extends r_4811_B> void renderCopyCutoutModel(EntityModel<T> modelParentIn, EntityModel<T> modelIn, g_2336_b textureLocationIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, float red, float green, float blue) {
        if (!entityIn.F_3572_x()) {
            modelParentIn.n_1700_B(modelIn);
            modelIn.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTicks);
            modelIn.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            RenderLayer.renderCutoutModel(modelIn, textureLocationIn, matrixStackIn, bufferIn, packedLightIn, entityIn, red, green, blue);
        }
    }

    protected static <T extends r_4811_B> void renderCutoutModel(EntityModel<T> modelIn, g_2336_b textureLocationIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entityIn, float red, float green, float blue) {
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.G_564_y(textureLocationIn));
        modelIn.render(matrixStackIn, ivertexbuilder, packedLightIn, o_4479_Q.R_4764_Y(entityIn, 0.0f), red, green, blue, 1.0f);
    }

    public M getEntityModel() {
        return this.entityRenderer.n_1700_B();
    }

    protected g_2336_b getEntityTexture(T entityIn) {
        return this.entityRenderer.n_1700_B(entityIn);
    }

    public abstract void render(g_221_o var1, o_3091_w var2, int var3, T var4, float var5, float var6, float var7, float var8, float var9, float var10);
}


