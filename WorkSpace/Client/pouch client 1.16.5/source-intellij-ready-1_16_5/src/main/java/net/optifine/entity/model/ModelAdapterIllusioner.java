/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.IllusionerRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterIllager;

public class ModelAdapterIllusioner
extends ModelAdapterIllager {
    public ModelAdapterIllusioner() {
        super(t_5_h.z_1737_N, "illusioner", 0.5f, new String[]{"illusion_illager"});
    }

    @Override
    public v_3569_v makeModel() {
        IllagerModel illagermodel = new IllagerModel(0.0f, 0.0f, 64, 64);
        illagermodel.J_1907_R().s_956_w = true;
        return illagermodel;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        IllusionerRenderer illusionerrenderer = new IllusionerRenderer(entityrenderermanager);
        illusionerrenderer.v_4262_N = (IllagerModel)modelBase;
        illusionerrenderer.R_4764_Y = shadowSize;
        return illusionerrenderer;
    }
}



