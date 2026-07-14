/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4390_i;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.TropicalFishModelB;
import lightning.product.TropicalFishModelA;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.r_2604_d;

public class TropicalFishPatternLayer
extends RenderLayer<A_4390_i, EntityModel<A_4390_i>> {
    private final TropicalFishModelA<A_4390_i> n_1700_B = new TropicalFishModelA(0.008f);
    private final TropicalFishModelB<A_4390_i> J_1907_R = new TropicalFishModelB(0.008f);

    public TropicalFishPatternLayer(j_4203_m<A_4390_i, EntityModel<A_4390_i>> p_i50918_1_) {
        super(p_i50918_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, A_4390_i entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        r_2604_d entitymodel = entitylivingbaseIn.U_3758_B() == 0 ? this.n_1700_B : this.J_1907_R;
        float[] afloat = entitylivingbaseIn.o_4117_e();
        TropicalFishPatternLayer.renderCopyCutoutModel(this.getEntityModel(), entitymodel, entitylivingbaseIn.y_3417_N(), matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, afloat[0], afloat[1], afloat[2]);
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (A_4390_i)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


