/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.W_1200_P;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;

public abstract class EnergySwirlLayer<T extends N_4263_v, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    public EnergySwirlLayer(j_4203_m<T, M> p_i226038_1_) {
        super(p_i226038_1_);
    }

    @Override
    public void render(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (((W_1200_P)entitylivingbaseIn).n_1700_B()) {
            float f = (float)((N_4263_v)entitylivingbaseIn).RealmsWorldResetDto + partialTicks;
            EntityModel<T> entitymodel = this.J_1907_R();
            entitymodel.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            ((EntityModel)this.getEntityModel()).n_1700_B(entitymodel);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.n_1700_B(this.n_1700_B(), this.n_1700_B(f), f * 0.01f));
            entitymodel.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            entitymodel.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 0.5f, 0.5f, 0.5f, 1.0f);
        }
    }

    protected abstract float n_1700_B(float var1);

    protected abstract g_2336_b n_1700_B();

    protected abstract EntityModel<T> J_1907_R();
}


