/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_1077_C;
import lightning.product.g_2336_b;
import lightning.product.SnowGolemModel;
import lightning.product.n_4684_O;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class SnowGolemRenderer
extends r_1334_c<N_1077_C, SnowGolemModel<N_1077_C>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/snow_golem.png");

    public SnowGolemRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SnowGolemModel(), 0.5f);
        this.n_1700_B(new n_4684_O(this));
    }

    @Override
    public g_2336_b n_1700_B(N_1077_C entity) {
        return n_1700_B;
    }
}


