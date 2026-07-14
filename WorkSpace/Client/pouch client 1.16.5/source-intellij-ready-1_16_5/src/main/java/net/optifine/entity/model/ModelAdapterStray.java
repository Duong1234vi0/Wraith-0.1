/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.StrayRenderer;
import lightning.product.SkeletonModel;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterStray
extends ModelAdapterBiped {
    public ModelAdapterStray() {
        super(t_5_h.M_1641_O, "stray", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SkeletonModel();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        StrayRenderer strayrenderer = new StrayRenderer(entityrenderermanager);
        strayrenderer.v_4262_N = (SkeletonModel)modelBase;
        strayrenderer.R_4764_Y = shadowSize;
        return strayrenderer;
    }
}



