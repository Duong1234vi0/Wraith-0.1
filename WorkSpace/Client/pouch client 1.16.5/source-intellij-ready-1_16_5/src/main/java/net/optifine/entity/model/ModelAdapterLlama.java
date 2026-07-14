/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.LlamaRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.LlamaModel;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterLlama
extends ModelAdapter {
    public ModelAdapterLlama() {
        super(t_5_h.g_221_o, "llama", 0.7f);
    }

    public ModelAdapterLlama(t_5_h entityType, String name, float shadowSize) {
        super(entityType, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new LlamaModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof LlamaModel)) {
            return null;
        }
        LlamaModel llamamodel = (LlamaModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 1);
        }
        if (modelPart.equals("leg1")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 2);
        }
        if (modelPart.equals("leg2")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 3);
        }
        if (modelPart.equals("leg3")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 4);
        }
        if (modelPart.equals("leg4")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 5);
        }
        if (modelPart.equals("chest_right")) {
            return (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 6);
        }
        return modelPart.equals("chest_left") ? (e_4189_z)Reflector.ModelLlama_ModelRenderers.getValue(llamamodel, 7) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "chest_right", "chest_left"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        LlamaRenderer llamarenderer = new LlamaRenderer(entityrenderermanager);
        llamarenderer.v_4262_N = (LlamaModel)modelBase;
        llamarenderer.R_4764_Y = shadowSize;
        return llamarenderer;
    }
}



