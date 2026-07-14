/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.HuskRenderer;
import lightning.product.o_4662_o;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterHusk
extends ModelAdapterBiped {
    public ModelAdapterHusk() {
        super(t_5_h.d_2427_y, "husk", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new o_4662_o(0.0f, false);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        HuskRenderer huskrenderer = new HuskRenderer(entityrenderermanager);
        huskrenderer.v_4262_N = (o_4662_o)modelBase;
        huskrenderer.R_4764_Y = shadowSize;
        return huskrenderer;
    }
}



