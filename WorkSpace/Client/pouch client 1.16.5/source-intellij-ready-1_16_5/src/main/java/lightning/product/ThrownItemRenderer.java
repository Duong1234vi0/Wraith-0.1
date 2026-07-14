/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_1066_I;
import lightning.product.ItemTransforms;
import lightning.product.H_3330_w;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;

public class ThrownItemRenderer<T extends N_4263_v>
extends Z_2049_e<T> {
    private final H_3330_w n_1700_B;
    private final float v_4262_N;
    private final boolean w_1484_f;

    public ThrownItemRenderer(w_2040_b p_i226035_1_, H_3330_w p_i226035_2_, float p_i226035_3_, boolean p_i226035_4_) {
        super(p_i226035_1_);
        this.n_1700_B = p_i226035_2_;
        this.v_4262_N = p_i226035_3_;
        this.w_1484_f = p_i226035_4_;
    }

    public ThrownItemRenderer(w_2040_b renderManagerIn, H_3330_w itemRendererIn) {
        this(renderManagerIn, itemRendererIn, 1.0f, false);
    }

    @Override
    protected int n_1700_B(T entityIn, c_1514_x partialTicks) {
        return this.w_1484_f ? 15 : super.n_1700_B(entityIn, partialTicks);
    }

    @Override
    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (((N_4263_v)entityIn).RealmsWorldResetDto >= 2 || !(this.J_1907_R.J_1907_R.v_4262_N().G_564_y((N_4263_v)entityIn) < 12.25)) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(this.v_4262_N, this.v_4262_N, this.v_4262_N);
            matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
            this.n_1700_B.n_1700_B(((G_1066_I)entityIn).n_1700_B(), ItemTransforms.J_1907_R.w_1484_f, packedLightIn, Z_3224_L.n_1700_B, matrixStackIn, bufferIn);
            matrixStackIn.J_1907_R();
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public g_2336_b n_1700_B(N_4263_v entity) {
        return L_3848_p.n_1700_B;
    }
}


