/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.DrownedModel;
import lightning.product.MinecraftClient;
import lightning.product.DrownedRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterZombie;

public class ModelAdapterDrowned
extends ModelAdapterZombie {
    public ModelAdapterDrowned() {
        super(t_5_h.t_1786_h, "drowned", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new DrownedModel(0.0f, 0.0f, 64, 64);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        DrownedRenderer drownedrenderer = new DrownedRenderer(entityrenderermanager);
        drownedrenderer.v_4262_N = (DrownedModel)modelBase;
        drownedrenderer.R_4764_Y = shadowSize;
        return drownedrenderer;
    }
}



