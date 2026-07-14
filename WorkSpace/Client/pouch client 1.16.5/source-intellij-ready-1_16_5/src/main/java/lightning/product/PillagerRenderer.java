/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Pillager;
import lightning.product.g_2336_b;
import lightning.product.IllagerRenderer;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import lightning.product.x_4904_Z;

public class PillagerRenderer
extends IllagerRenderer<Pillager> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/pillager.png");

    public PillagerRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new IllagerModel(0.0f, 0.0f, 64, 64), 0.5f);
        this.n_1700_B(new x_4904_Z<Pillager, IllagerModel<Pillager>>(this));
    }

    @Override
    public g_2336_b n_1700_B(Pillager entity) {
        return n_1700_B;
    }
}


