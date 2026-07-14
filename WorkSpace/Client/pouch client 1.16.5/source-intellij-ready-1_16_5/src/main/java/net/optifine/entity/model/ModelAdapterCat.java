/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.CatModel;
import lightning.product.MinecraftClient;
import lightning.product.h_1935_L;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterOcelot;

public class ModelAdapterCat
extends ModelAdapterOcelot {
    public ModelAdapterCat() {
        super(t_5_h.w_1484_f, "cat", 0.4f);
    }

    @Override
    public v_3569_v makeModel() {
        return new CatModel(0.0f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        h_1935_L catrenderer = new h_1935_L(entityrenderermanager);
        catrenderer.v_4262_N = (CatModel)modelBase;
        catrenderer.R_4764_Y = shadowSize;
        return catrenderer;
    }
}



