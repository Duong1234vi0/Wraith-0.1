/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_4536_S;
import lightning.product.U_1504_z;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.v_1296_A;
import lightning.product.w_2040_b;

public class SheepRenderer
extends r_1334_c<G_4536_S, v_1296_A<G_4536_S>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/sheep/sheep.png");

    public SheepRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new v_1296_A(), 0.7f);
        this.n_1700_B(new U_1504_z(this));
    }

    @Override
    public g_2336_b n_1700_B(G_4536_S entity) {
        return n_1700_B;
    }
}


