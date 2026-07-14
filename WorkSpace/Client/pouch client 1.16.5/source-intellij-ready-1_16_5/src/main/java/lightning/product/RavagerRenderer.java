/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.X_1275_n;
import lightning.product.g_2336_b;
import lightning.product.RavagerModel;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class RavagerRenderer
extends r_1334_c<X_1275_n, RavagerModel> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/ravager.png");

    public RavagerRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new RavagerModel(), 1.1f);
    }

    @Override
    public g_2336_b n_1700_B(X_1275_n entity) {
        return n_1700_B;
    }
}


