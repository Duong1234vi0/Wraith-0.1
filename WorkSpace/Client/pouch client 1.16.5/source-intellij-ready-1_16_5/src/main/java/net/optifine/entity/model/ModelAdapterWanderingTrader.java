/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.VillagerModel;
import lightning.product.WanderingTraderRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterVillager;

public class ModelAdapterWanderingTrader
extends ModelAdapterVillager {
    public ModelAdapterWanderingTrader() {
        super(t_5_h.u_744_e, "wandering_trader", 0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WanderingTraderRenderer wanderingtraderrenderer = new WanderingTraderRenderer(entityrenderermanager);
        wanderingtraderrenderer.v_4262_N = (VillagerModel)modelBase;
        wanderingtraderrenderer.R_4764_Y = shadowSize;
        return wanderingtraderrenderer;
    }
}



