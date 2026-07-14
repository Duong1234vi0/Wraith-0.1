/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.G_2271_Y;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class HuskRenderer
extends G_2271_Y {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie/husk.png");

    public HuskRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected void n_1700_B(F_4355_q entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 1.0625f;
        matrixStackIn.n_1700_B(1.0625f, 1.0625f, 1.0625f);
        super.n_1700_B(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public g_2336_b n_1700_B(F_4355_q entity) {
        return n_1700_B;
    }
}


