/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SkeletonModel;
import lightning.product.MinecraftClient;
import lightning.product.WitherSkeletonRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterWitherSkeleton
extends ModelAdapterBiped {
    public ModelAdapterWitherSkeleton() {
        super(t_5_h.RowButton, "wither_skeleton", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SkeletonModel();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WitherSkeletonRenderer witherskeletonrenderer = new WitherSkeletonRenderer(entityrenderermanager);
        witherskeletonrenderer.v_4262_N = (SkeletonModel)modelBase;
        witherskeletonrenderer.R_4764_Y = shadowSize;
        return witherskeletonrenderer;
    }
}



