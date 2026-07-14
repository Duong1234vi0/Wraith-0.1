/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.PandaRenderer;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.PandaModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterPanda
extends ModelAdapterQuadruped {
    public ModelAdapterPanda() {
        super(t_5_h.z_1333_t, "panda", 0.9f);
    }

    @Override
    public v_3569_v makeModel() {
        return new PandaModel(9, 0.0f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        PandaRenderer pandarenderer = new PandaRenderer(entityrenderermanager);
        pandarenderer.v_4262_N = (PandaModel)modelBase;
        pandarenderer.R_4764_Y = shadowSize;
        return pandarenderer;
    }
}



