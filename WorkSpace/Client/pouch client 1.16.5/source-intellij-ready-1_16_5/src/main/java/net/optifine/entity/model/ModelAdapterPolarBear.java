/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.PolarBearRenderer;
import lightning.product.MinecraftClient;
import lightning.product.PolarBearModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterPolarBear
extends ModelAdapterQuadruped {
    public ModelAdapterPolarBear() {
        super(t_5_h.RealmsClientConfig, "polar_bear", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new PolarBearModel();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        PolarBearRenderer polarbearrenderer = new PolarBearRenderer(entityrenderermanager);
        polarbearrenderer.v_4262_N = (PolarBearModel)modelBase;
        polarbearrenderer.R_4764_Y = shadowSize;
        return polarbearrenderer;
    }
}



