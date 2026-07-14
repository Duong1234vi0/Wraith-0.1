/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.PigRenderer;
import lightning.product.MinecraftClient;
import lightning.product.o_667_y;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterPig
extends ModelAdapterQuadruped {
    public ModelAdapterPig() {
        super(t_5_h.A_1038_p, "pig", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new o_667_y();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        PigRenderer pigrenderer = new PigRenderer(entityrenderermanager);
        pigrenderer.v_4262_N = (o_667_y)modelBase;
        pigrenderer.R_4764_Y = shadowSize;
        return pigrenderer;
    }
}



