/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.SlimeModel;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;

public class y_663_y<T extends r_4811_B>
extends RenderLayer<T, SlimeModel<T>> {
    private final EntityModel<T> n_1700_B = new SlimeModel(0);

    public y_663_y(j_4203_m<T, SlimeModel<T>> p_i50923_1_) {
        super(p_i50923_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!((N_4263_v)entitylivingbaseIn).F_3572_x()) {
            ((SlimeModel)this.getEntityModel()).n_1700_B(this.n_1700_B);
            this.n_1700_B.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.n_1700_B.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.w_1484_f(this.getEntityTexture(entitylivingbaseIn)));
            this.n_1700_B.render(matrixStackIn, ivertexbuilder, packedLightIn, o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


