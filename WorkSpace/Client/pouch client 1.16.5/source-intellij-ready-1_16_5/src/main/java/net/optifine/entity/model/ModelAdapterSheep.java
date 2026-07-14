/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_1296_A;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.SheepRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterSheep
extends ModelAdapterQuadruped {
    public ModelAdapterSheep() {
        super(t_5_h.k_3961_g, "sheep", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new v_1296_A();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SheepRenderer sheeprenderer = new SheepRenderer(entityrenderermanager);
        sheeprenderer.v_4262_N = (v_1296_A)modelBase;
        sheeprenderer.R_4764_Y = shadowSize;
        return sheeprenderer;
    }
}



