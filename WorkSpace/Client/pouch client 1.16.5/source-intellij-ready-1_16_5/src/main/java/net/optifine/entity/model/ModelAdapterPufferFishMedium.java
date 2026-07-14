/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.PufferfishRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.PufferfishMidModel;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterPufferFishMedium
extends ModelAdapter {
    public ModelAdapterPufferFishMedium() {
        super(t_5_h.j_276_v, "puffer_fish_medium", 0.2f);
    }

    @Override
    public v_3569_v makeModel() {
        return new PufferfishMidModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof PufferfishMidModel)) {
            return null;
        }
        PufferfishMidModel pufferfishmediummodel = (PufferfishMidModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 0);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 1);
        }
        if (modelPart.equals("fin_left")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 2);
        }
        if (modelPart.equals("spikes_front_top")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 3);
        }
        if (modelPart.equals("spikes_back_top")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 4);
        }
        if (modelPart.equals("spikes_front_right")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 5);
        }
        if (modelPart.equals("spikes_back_right")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 6);
        }
        if (modelPart.equals("spikes_back_left")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 7);
        }
        if (modelPart.equals("spikes_front_left")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 8);
        }
        if (modelPart.equals("spikes_back_bottom")) {
            return (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 9);
        }
        return modelPart.equals("spikes_front_bottom") ? (e_4189_z)Reflector.ModelPufferFishMedium_ModelRenderers.getValue(pufferfishmediummodel, 10) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "fin_right", "fin_left", "spikes_front_top", "spikes_back_top", "spikes_front_right", "spikes_back_right", "spikes_back_left", "spikes_front_left", "spikes_back_bottom", "spikes_front_bottom"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Z_2049_e entityrenderer = entityrenderermanager.P_1922_E().get(t_5_h.j_276_v);
        if (!(entityrenderer instanceof PufferfishRenderer)) {
            Config.warn("Not a PufferfishRenderer: " + String.valueOf(entityrenderer));
            return null;
        }
        if (entityrenderer.getType() == null) {
            PufferfishRenderer pufferfishrenderer = new PufferfishRenderer(entityrenderermanager);
            pufferfishrenderer.R_4764_Y = shadowSize;
            entityrenderer = pufferfishrenderer;
        }
        PufferfishRenderer pufferfishrenderer1 = (PufferfishRenderer)entityrenderer;
        if (!Reflector.RenderPufferfish_modelMedium.exists()) {
            Config.warn("Model field not found: RenderPufferfish.modelMedium");
            return null;
        }
        Reflector.RenderPufferfish_modelMedium.setValue(pufferfishrenderer1, modelBase);
        return pufferfishrenderer1;
    }
}



