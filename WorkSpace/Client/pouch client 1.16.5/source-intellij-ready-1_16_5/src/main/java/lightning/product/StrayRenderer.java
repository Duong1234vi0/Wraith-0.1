/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.StrayClothingLayer;
import lightning.product.SkeletonModel;
import lightning.product.AbstractSkeleton;
import lightning.product.SkeletonRenderer;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class StrayRenderer
extends SkeletonRenderer {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/skeleton/stray.png");

    public StrayRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.n_1700_B(new StrayClothingLayer<AbstractSkeleton, SkeletonModel<AbstractSkeleton>>(this));
    }

    @Override
    public g_2336_b n_1700_B(AbstractSkeleton entity) {
        return n_1700_B;
    }
}


