/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.O_3276_Y;
import lightning.product.WitchModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.w_3611_Y;

public class WitchRenderer
extends r_1334_c<w_3611_Y, WitchModel<w_3611_Y>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/witch.png");

    public WitchRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new WitchModel(0.0f), 0.5f);
        this.n_1700_B(new O_3276_Y<w_3611_Y>(this));
    }

    @Override
    public void n_1700_B(w_3611_Y entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        ((WitchModel)this.v_4262_N).J_1907_R(!entityIn.A_2714_y().n_1700_B());
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(w_3611_Y entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(w_3611_Y entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.9375f;
        matrixStackIn.n_1700_B(0.9375f, 0.9375f, 0.9375f);
    }
}


