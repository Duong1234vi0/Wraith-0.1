/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.EvokerFangsRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.EvokerFangsModel;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEvokerFangs
extends ModelAdapter {
    public ModelAdapterEvokerFangs() {
        super(t_5_h.k_2293_S, "evoker_fangs", 0.0f, new String[]{"evocation_fangs"});
    }

    @Override
    public v_3569_v makeModel() {
        return new EvokerFangsModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof EvokerFangsModel)) {
            return null;
        }
        EvokerFangsModel evokerfangsmodel = (EvokerFangsModel)model;
        if (modelPart.equals("base")) {
            return (e_4189_z)Reflector.getFieldValue(evokerfangsmodel, Reflector.ModelEvokerFangs_ModelRenderers, 0);
        }
        if (modelPart.equals("upper_jaw")) {
            return (e_4189_z)Reflector.getFieldValue(evokerfangsmodel, Reflector.ModelEvokerFangs_ModelRenderers, 1);
        }
        return modelPart.equals("lower_jaw") ? (e_4189_z)Reflector.getFieldValue(evokerfangsmodel, Reflector.ModelEvokerFangs_ModelRenderers, 2) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "upper_jaw", "lower_jaw"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        EvokerFangsRenderer evokerfangsrenderer = new EvokerFangsRenderer(entityrenderermanager);
        if (!Reflector.RenderEvokerFangs_model.exists()) {
            Config.warn("Field not found: RenderEvokerFangs.model");
            return null;
        }
        Reflector.setFieldValue(evokerfangsrenderer, Reflector.RenderEvokerFangs_model, modelBase);
        evokerfangsrenderer.R_4764_Y = shadowSize;
        return evokerfangsrenderer;
    }
}



