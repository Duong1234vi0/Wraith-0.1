/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SkeletonModel;
import lightning.product.MinecraftClient;
import lightning.product.SkeletonRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterSkeleton
extends ModelAdapterBiped {
    public ModelAdapterSkeleton() {
        super(t_5_h.V_1446_Y, "skeleton", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SkeletonModel();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SkeletonRenderer skeletonrenderer = new SkeletonRenderer(entityrenderermanager);
        skeletonrenderer.v_4262_N = (SkeletonModel)modelBase;
        skeletonrenderer.R_4764_Y = shadowSize;
        return skeletonrenderer;
    }
}



