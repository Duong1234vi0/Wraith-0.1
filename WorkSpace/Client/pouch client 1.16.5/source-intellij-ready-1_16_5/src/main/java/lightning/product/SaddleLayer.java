/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.Saddleable;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.v_3569_v;

public class SaddleLayer<T extends N_4263_v, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private final g_2336_b n_1700_B;
    private final M J_1907_R;

    public SaddleLayer(j_4203_m<T, M> p_i232478_1_, M p_i232478_2_, g_2336_b p_i232478_3_) {
        super(p_i232478_1_);
        this.J_1907_R = p_i232478_2_;
        this.n_1700_B = p_i232478_3_;
    }

    @Override
    public void render(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (((Saddleable)entitylivingbaseIn).G_564_y()) {
            ((EntityModel)this.getEntityModel()).n_1700_B(this.J_1907_R);
            ((EntityModel)this.J_1907_R).n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            ((EntityModel)this.J_1907_R).n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.G_564_y(this.n_1700_B));
            ((v_3569_v)this.J_1907_R).render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}


