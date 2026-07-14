/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.LlamaSpitRenderer;
import lightning.product.LlamaSpitModel;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterLlamaSpit
extends ModelAdapter {
    public ModelAdapterLlamaSpit() {
        super(t_5_h.e_2887_G, "llama_spit", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new LlamaSpitModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof LlamaSpitModel)) {
            return null;
        }
        LlamaSpitModel llamaspitmodel = (LlamaSpitModel)model;
        return modelPart.equals("body") ? (e_4189_z)Reflector.ModelLlamaSpit_renderer.getValue(llamaspitmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        LlamaSpitRenderer llamaspitrenderer = new LlamaSpitRenderer(entityrenderermanager);
        if (!Reflector.RenderLlamaSpit_model.exists()) {
            Config.warn("Field not found: RenderLlamaSpit.model");
            return null;
        }
        Reflector.setFieldValue(llamaspitrenderer, Reflector.RenderLlamaSpit_model, modelBase);
        llamaspitrenderer.R_4764_Y = shadowSize;
        return llamaspitrenderer;
    }
}



