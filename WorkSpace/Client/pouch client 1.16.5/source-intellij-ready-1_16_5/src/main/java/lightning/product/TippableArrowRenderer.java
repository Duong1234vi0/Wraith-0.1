/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Arrow;
import lightning.product.ArrowRenderer;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class TippableArrowRenderer
extends ArrowRenderer<Arrow> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/projectiles/arrow.png");
    public static final g_2336_b v_4262_N = new g_2336_b("textures/entity/projectiles/tipped_arrow.png");

    public TippableArrowRenderer(w_2040_b manager) {
        super(manager);
    }

    @Override
    public g_2336_b n_1700_B(Arrow entity) {
        return entity.w_1457_N() > 0 ? v_4262_N : n_1700_B;
    }
}


