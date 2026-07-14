/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.PillagerRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterIllager;

public class ModelAdapterPillager
extends ModelAdapterIllager {
    public ModelAdapterPillager() {
        super(t_5_h.p_178_J, "pillager", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new IllagerModel(0.0f, 0.0f, 64, 64);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        PillagerRenderer pillagerrenderer = new PillagerRenderer(entityrenderermanager);
        pillagerrenderer.v_4262_N = (IllagerModel)modelBase;
        pillagerrenderer.R_4764_Y = shadowSize;
        return pillagerrenderer;
    }
}



