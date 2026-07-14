/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_2149_k;
import lightning.product.BlazeModel;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class BlazeRenderer
extends r_1334_c<G_2149_k, BlazeModel<G_2149_k>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/blaze.png");

    public BlazeRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new BlazeModel(), 0.5f);
    }

    @Override
    protected int n_1700_B(G_2149_k entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public g_2336_b n_1700_B(G_2149_k entity) {
        return n_1700_B;
    }
}


