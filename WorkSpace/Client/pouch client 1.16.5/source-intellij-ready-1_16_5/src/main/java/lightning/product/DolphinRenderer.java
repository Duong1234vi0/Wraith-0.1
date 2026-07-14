/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DolphinCarryingItemLayer;
import lightning.product.DolphinModel;
import lightning.product.Y_559_r;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class DolphinRenderer
extends r_1334_c<Y_559_r, DolphinModel<Y_559_r>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/dolphin.png");

    public DolphinRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new DolphinModel(), 0.7f);
        this.n_1700_B(new DolphinCarryingItemLayer(this));
    }

    @Override
    public g_2336_b n_1700_B(Y_559_r entity) {
        return n_1700_B;
    }
}


