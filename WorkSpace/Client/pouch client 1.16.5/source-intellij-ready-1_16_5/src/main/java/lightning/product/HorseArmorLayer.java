/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.HorseModel;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.Horse;
import lightning.product.v_21_F;
import lightning.product.w_540_o;

public class HorseArmorLayer
extends RenderLayer<Horse, HorseModel<Horse>> {
    private final HorseModel<Horse> n_1700_B = new HorseModel(0.1f);

    public HorseArmorLayer(j_4203_m<Horse, HorseModel<Horse>> p_i50937_1_) {
        super(p_i50937_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, Horse entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack = entitylivingbaseIn.h_1640_b();
        if (itemstack.J_1907_R() instanceof v_21_F) {
            float f2;
            float f1;
            float f;
            v_21_F horsearmoritem = (v_21_F)itemstack.J_1907_R();
            ((HorseModel)this.getEntityModel()).n_1700_B(this.n_1700_B);
            this.n_1700_B.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.n_1700_B.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            if (horsearmoritem instanceof w_540_o) {
                int i = ((w_540_o)horsearmoritem).d_(itemstack);
                f = (float)(i >> 16 & 0xFF) / 255.0f;
                f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                f2 = (float)(i & 0xFF) / 255.0f;
            } else {
                f = 1.0f;
                f1 = 1.0f;
                f2 = 1.0f;
            }
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.G_564_y(horsearmoritem.v_4262_N()));
            this.n_1700_B.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, f, f1, f2, 1.0f);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (Horse)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


