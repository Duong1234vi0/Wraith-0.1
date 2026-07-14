/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4816_K;
import lightning.product.HoglinModel;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class ZoglinRenderer
extends r_1334_c<C_4816_K, HoglinModel<C_4816_K>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/hoglin/zoglin.png");

    public ZoglinRenderer(w_2040_b p_i232474_1_) {
        super(p_i232474_1_, new HoglinModel(), 0.7f);
    }

    @Override
    public g_2336_b n_1700_B(C_4816_K entity) {
        return n_1700_B;
    }
}


