/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4271_P;
import lightning.product.SaddleLayer;
import lightning.product.g_2336_b;
import lightning.product.o_667_y;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class PigRenderer
extends r_1334_c<B_4271_P, o_667_y<B_4271_P>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/pig/pig.png");

    public PigRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new o_667_y(), 0.7f);
        this.n_1700_B(new SaddleLayer(this, new o_667_y(0.5f), new g_2336_b("textures/entity/pig/pig_saddle.png")));
    }

    @Override
    public g_2336_b n_1700_B(B_4271_P entity) {
        return n_1700_B;
    }
}


