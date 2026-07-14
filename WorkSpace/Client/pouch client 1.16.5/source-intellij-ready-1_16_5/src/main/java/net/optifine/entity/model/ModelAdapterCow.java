/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.CowRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.w_3245_r;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterCow
extends ModelAdapterQuadruped {
    public ModelAdapterCow() {
        super(t_5_h.M_588_G, "cow", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new w_3245_r();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        CowRenderer cowrenderer = new CowRenderer(entityrenderermanager);
        cowrenderer.v_4262_N = (w_3245_r)modelBase;
        cowrenderer.R_4764_Y = shadowSize;
        return cowrenderer;
    }
}



