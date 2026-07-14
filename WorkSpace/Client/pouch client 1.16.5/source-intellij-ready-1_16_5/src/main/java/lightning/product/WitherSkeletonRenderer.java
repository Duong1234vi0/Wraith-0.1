/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AbstractSkeleton;
import lightning.product.SkeletonRenderer;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class WitherSkeletonRenderer
extends SkeletonRenderer {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/skeleton/wither_skeleton.png");

    public WitherSkeletonRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public g_2336_b n_1700_B(AbstractSkeleton entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(AbstractSkeleton entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(1.2f, 1.2f, 1.2f);
    }
}


