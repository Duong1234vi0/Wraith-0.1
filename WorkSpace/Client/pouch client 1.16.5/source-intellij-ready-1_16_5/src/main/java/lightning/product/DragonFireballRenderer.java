/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;
import lightning.product.DragonFireball;

public class DragonFireballRenderer
extends Z_2049_e<DragonFireball> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/enderdragon/dragon_fireball.png");
    private static final o_2576_A v_4262_N = o_2576_A.G_564_y(n_1700_B);

    public DragonFireballRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected int n_1700_B(DragonFireball entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public void n_1700_B(DragonFireball entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(2.0f, 2.0f, 2.0f);
        matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        D_4792_h ivertexbuilder = bufferIn.getBuffer(v_4262_N);
        DragonFireballRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0f, 0, 0, 1);
        DragonFireballRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0f, 0, 1, 1);
        DragonFireballRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0f, 1, 1, 0);
        DragonFireballRenderer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0f, 1, 0, 0);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void n_1700_B(D_4792_h p_229045_0_, D_1098_v p_229045_1_, o_1290_k p_229045_2_, int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
        p_229045_0_.n_1700_B(p_229045_1_, p_229045_4_ - 0.5f, (float)p_229045_5_ - 0.25f, 0.0f).color(255, 255, 255, 255).tex(p_229045_6_, p_229045_7_).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229045_3_).n_1700_B(p_229045_2_, 0.0f, 1.0f, 0.0f).endVertex();
    }

    @Override
    public g_2336_b n_1700_B(DragonFireball entity) {
        return n_1700_B;
    }
}


