/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.EndermanRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.w_2498_n;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterEnderman
extends ModelAdapterBiped {
    public ModelAdapterEnderman() {
        super(t_5_h.Y_259_p, "enderman", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new w_2498_n(0.0f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        EndermanRenderer endermanrenderer = new EndermanRenderer(entityrenderermanager);
        endermanrenderer.v_4262_N = (w_2498_n)modelBase;
        endermanrenderer.R_4764_Y = shadowSize;
        return endermanrenderer;
    }
}



