/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Hoglin;
import lightning.product.HoglinModel;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;

public class HoglinRenderer
extends r_1334_c<Hoglin, HoglinModel<Hoglin>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/hoglin/hoglin.png");

    public HoglinRenderer(w_2040_b p_i232470_1_) {
        super(p_i232470_1_, new HoglinModel(), 0.7f);
    }

    @Override
    public g_2336_b n_1700_B(Hoglin entity) {
        return n_1700_B;
    }

    @Override
    protected boolean J_1907_R(Hoglin p_230495_1_) {
        return p_230495_1_.y_2447_C();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(r_4811_B r_4811_B2) {
        return this.J_1907_R((Hoglin)r_4811_B2);
    }
}


