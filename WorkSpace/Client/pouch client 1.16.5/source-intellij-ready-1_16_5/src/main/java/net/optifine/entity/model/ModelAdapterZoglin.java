/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ZoglinRenderer;
import lightning.product.MinecraftClient;
import lightning.product.HoglinModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterHoglin;

public class ModelAdapterZoglin
extends ModelAdapterHoglin {
    public ModelAdapterZoglin() {
        super(t_5_h.S_980_j, "zoglin", 0.7f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ZoglinRenderer zoglinrenderer = new ZoglinRenderer(entityrenderermanager);
        zoglinrenderer.v_4262_N = (HoglinModel)modelBase;
        zoglinrenderer.R_4764_Y = shadowSize;
        return zoglinrenderer;
    }
}



