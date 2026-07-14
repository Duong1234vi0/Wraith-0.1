/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SpiderModel;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.CaveSpiderRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterSpider;

public class ModelAdapterCaveSpider
extends ModelAdapterSpider {
    public ModelAdapterCaveSpider() {
        super(t_5_h.t_148_a, "cave_spider", 0.7f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        CaveSpiderRenderer cavespiderrenderer = new CaveSpiderRenderer(entityrenderermanager);
        cavespiderrenderer.v_4262_N = (SpiderModel)modelBase;
        cavespiderrenderer.R_4764_Y = shadowSize;
        return cavespiderrenderer;
    }
}



