/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.OcelotModel;
import lightning.product.g_2336_b;
import lightning.product.l_3090_i;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class OcelotRenderer
extends r_1334_c<l_3090_i, OcelotModel<l_3090_i>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/cat/ocelot.png");

    public OcelotRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new OcelotModel(0.0f), 0.4f);
    }

    @Override
    public g_2336_b n_1700_B(l_3090_i entity) {
        return n_1700_B;
    }
}


