/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.VindicatorRenderer;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterIllager;

public class ModelAdapterVindicator
extends ModelAdapterIllager {
    public ModelAdapterVindicator() {
        super(t_5_h.y_1700_S, "vindicator", 0.5f, new String[]{"vindication_illager"});
    }

    @Override
    public v_3569_v makeModel() {
        return new IllagerModel(0.0f, 0.0f, 64, 64);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        VindicatorRenderer vindicatorrenderer = new VindicatorRenderer(entityrenderermanager);
        vindicatorrenderer.v_4262_N = (IllagerModel)modelBase;
        vindicatorrenderer.R_4764_Y = shadowSize;
        return vindicatorrenderer;
    }
}



