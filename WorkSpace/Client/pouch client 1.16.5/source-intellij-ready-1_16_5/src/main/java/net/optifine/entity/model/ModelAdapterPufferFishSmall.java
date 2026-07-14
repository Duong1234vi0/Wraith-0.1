/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.PufferfishSmallModel;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.PufferfishRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterPufferFishSmall
extends ModelAdapter {
    public ModelAdapterPufferFishSmall() {
        super(t_5_h.j_276_v, "puffer_fish_small", 0.2f);
    }

    @Override
    public v_3569_v makeModel() {
        return new PufferfishSmallModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof PufferfishSmallModel)) {
            return null;
        }
        PufferfishSmallModel pufferfishsmallmodel = (PufferfishSmallModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 0);
        }
        if (modelPart.equals("eye_right")) {
            return (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 1);
        }
        if (modelPart.equals("eye_left")) {
            return (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 2);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 3);
        }
        if (modelPart.equals("fin_left")) {
            return (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 4);
        }
        return modelPart.equals("tail") ? (e_4189_z)Reflector.ModelPufferFishSmall_ModelRenderers.getValue(pufferfishsmallmodel, 5) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "eye_right", "eye_left", "tail", "fin_right", "fin_left"};
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
        if (!Reflector.RenderPufferfish_modelSmall.exists()) {
            Config.warn("Model field not found: RenderPufferfish.modelSmall");
            return null;
        }
        Reflector.RenderPufferfish_modelSmall.setValue(pufferfishrenderer1, modelBase);
        return pufferfishrenderer1;
    }
}



