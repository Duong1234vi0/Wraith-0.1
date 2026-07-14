/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.I_3755_Y;
import lightning.product.MinecraftClient;
import lightning.product.o_2315_w;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterPiglin;

public class ModelAdapterZombifiedPiglin
extends ModelAdapterPiglin {
    public ModelAdapterZombifiedPiglin() {
        super(t_5_h.c_132_F, "zombified_piglin", 0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        o_2315_w piglinrenderer = new o_2315_w(entityrenderermanager, true);
        piglinrenderer.v_4262_N = (I_3755_Y)modelBase;
        piglinrenderer.R_4764_Y = shadowSize;
        return piglinrenderer;
    }
}


