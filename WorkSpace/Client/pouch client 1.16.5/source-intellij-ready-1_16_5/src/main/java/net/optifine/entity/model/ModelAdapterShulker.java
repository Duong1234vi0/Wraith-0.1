/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_434_g;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.ShulkerModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterShulker
extends ModelAdapter {
    public ModelAdapterShulker() {
        super(t_5_h.Ops, "shulker", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ShulkerModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ShulkerModel)) {
            return null;
        }
        ShulkerModel shulkermodel = (ShulkerModel)model;
        if (modelPart.equals("base")) {
            return (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 0);
        }
        if (modelPart.equals("lid")) {
            return (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 1);
        }
        return modelPart.equals("head") ? (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 2) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "lid", "head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        D_434_g shulkerrenderer = new D_434_g(entityrenderermanager);
        shulkerrenderer.v_4262_N = (ShulkerModel)modelBase;
        shulkerrenderer.R_4764_Y = shadowSize;
        return shulkerrenderer;
    }
}



