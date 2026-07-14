/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Cow;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.w_3245_r;

public class CowRenderer
extends r_1334_c<Cow, w_3245_r<Cow>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/cow/cow.png");

    public CowRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new w_3245_r(), 0.7f);
    }

    @Override
    public g_2336_b n_1700_B(Cow entity) {
        return n_1700_B;
    }
}


