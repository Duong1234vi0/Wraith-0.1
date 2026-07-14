/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.TropicalFishModelA;
import lightning.product.TropicalFishRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterTropicalFishA
extends ModelAdapter {
    public ModelAdapterTropicalFishA() {
        super(t_5_h.S_4022_R, "tropical_fish_a", 0.2f);
    }

    @Override
    public v_3569_v makeModel() {
        return new TropicalFishModelA(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof TropicalFishModelA)) {
            return null;
        }
        TropicalFishModelA tropicalfishamodel = (TropicalFishModelA)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelTropicalFishA_ModelRenderers.getValue(tropicalfishamodel, 0);
        }
        if (modelPart.equals("tail")) {
            return (e_4189_z)Reflector.ModelTropicalFishA_ModelRenderers.getValue(tropicalfishamodel, 1);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelTropicalFishA_ModelRenderers.getValue(tropicalfishamodel, 2);
        }
        if (modelPart.equals("fin_left")) {
            return (e_4189_z)Reflector.ModelTropicalFishA_ModelRenderers.getValue(tropicalfishamodel, 3);
        }
        return modelPart.equals("fin_top") ? (e_4189_z)Reflector.ModelTropicalFishA_ModelRenderers.getValue(tropicalfishamodel, 4) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tail", "fin_right", "fin_left", "fin_top"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Z_2049_e entityrenderer = entityrenderermanager.P_1922_E().get(t_5_h.S_4022_R);
        if (!(entityrenderer instanceof TropicalFishRenderer)) {
            Config.warn("Not a TropicalFishRenderer: " + String.valueOf(entityrenderer));
            return null;
        }
        if (entityrenderer.getType() == null) {
            TropicalFishRenderer tropicalfishrenderer = new TropicalFishRenderer(entityrenderermanager);
            tropicalfishrenderer.R_4764_Y = shadowSize;
            entityrenderer = tropicalfishrenderer;
        }
        TropicalFishRenderer tropicalfishrenderer1 = (TropicalFishRenderer)entityrenderer;
        if (!Reflector.RenderTropicalFish_modelA.exists()) {
            Config.warn("Model field not found: RenderTropicalFish.modelA");
            return null;
        }
        Reflector.RenderTropicalFish_modelA.setValue(tropicalfishrenderer1, modelBase);
        return tropicalfishrenderer1;
    }
}



