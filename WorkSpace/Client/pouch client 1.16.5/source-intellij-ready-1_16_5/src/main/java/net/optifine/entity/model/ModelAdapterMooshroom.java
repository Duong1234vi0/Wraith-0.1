/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MushroomCowRenderer;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.w_3245_r;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterMooshroom
extends ModelAdapterQuadruped {
    public ModelAdapterMooshroom() {
        super(t_5_h.D_4792_h, "mooshroom", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new w_3245_r();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        MushroomCowRenderer mooshroomrenderer = new MushroomCowRenderer(entityrenderermanager);
        mooshroomrenderer.v_4262_N = (w_3245_r)modelBase;
        mooshroomrenderer.R_4764_Y = shadowSize;
        return mooshroomrenderer;
    }
}



