/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.TurtleModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.t_4149_i;
import lightning.product.w_2040_b;

public class TurtleRenderer
extends r_1334_c<t_4149_i, TurtleModel<t_4149_i>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/turtle/big_sea_turtle.png");

    public TurtleRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new TurtleModel(0.0f), 0.7f);
    }

    @Override
    public void n_1700_B(t_4149_i entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (entityIn.d_()) {
            this.R_4764_Y *= 0.5f;
        }
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(t_4149_i entity) {
        return n_1700_B;
    }
}


