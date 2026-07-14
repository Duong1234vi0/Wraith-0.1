/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4315_z;
import lightning.product.SkeletonModel;
import lightning.product.AbstractSkeleton;
import lightning.product.HumanoidMobRenderer;
import lightning.product.g_2336_b;
import lightning.product.w_2040_b;

public class SkeletonRenderer
extends HumanoidMobRenderer<AbstractSkeleton, SkeletonModel<AbstractSkeleton>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/skeleton/skeleton.png");

    public SkeletonRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SkeletonModel(), 0.5f);
        this.n_1700_B(new B_4315_z(this, new SkeletonModel(0.5f, true), new SkeletonModel(1.0f, true)));
    }

    @Override
    public g_2336_b n_1700_B(AbstractSkeleton entity) {
        return n_1700_B;
    }
}


