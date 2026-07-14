/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_3224_L;
import lightning.product.Emotions;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;

public class j_240_A<T extends r_4811_B>
extends RenderLayer<T, PlayerModel<T>> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/trident_riptide.png");
    private final e_4189_z J_1907_R = new e_4189_z(64, 64, 0, 0);

    public j_240_A(j_4203_m<T, PlayerModel<T>> p_i50920_1_) {
        super(p_i50920_1_);
        this.J_1907_R.n_1700_B(-8.0f, -16.0f, -8.0f, 16.0f, 32.0f, 16.0f);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Emotions emotionsPreview = Emotions.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            return;
        }
        if (((r_4811_B)entitylivingbaseIn).B_3040_x()) {
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.G_564_y(n_1700_B));
            for (int i = 0; i < 3; ++i) {
                matrixStackIn.n_1700_B();
                float f = ageInTicks * (float)(-(45 + i * 5));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f));
                float f1 = 0.75f * (float)i;
                matrixStackIn.n_1700_B(f1, f1, f1);
                matrixStackIn.n_1700_B(0.0, (double)(-0.2f + 0.6f * (float)i), 0.0);
                this.J_1907_R.n_1700_B(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B);
                matrixStackIn.J_1907_R();
            }
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



