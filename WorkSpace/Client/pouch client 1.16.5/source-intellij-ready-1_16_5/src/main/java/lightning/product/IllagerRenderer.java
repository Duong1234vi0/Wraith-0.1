/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AbstractIllager;
import lightning.product.g_2016_P;
import lightning.product.g_221_o;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;

public abstract class IllagerRenderer<T extends AbstractIllager>
extends r_1334_c<T, IllagerModel<T>> {
    protected IllagerRenderer(w_2040_b p_i50966_1_, IllagerModel<T> p_i50966_2_, float p_i50966_3_) {
        super(p_i50966_1_, p_i50966_2_, p_i50966_3_);
        this.n_1700_B(new g_2016_P(this));
    }

    @Override
    protected void n_1700_B(T entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.9375f;
        matrixStackIn.n_1700_B(0.9375f, 0.9375f, 0.9375f);
    }
}


