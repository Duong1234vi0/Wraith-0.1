/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.TropicalFishModelB;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.TropicalFishRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterTropicalFishB
extends ModelAdapter {
    public ModelAdapterTropicalFishB() {
        super(t_5_h.S_4022_R, "tropical_fish_b", 0.2f);
    }

    @Override
    public v_3569_v makeModel() {
        return new TropicalFishModelB(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof TropicalFishModelB)) {
            return null;
        }
        TropicalFishModelB tropicalfishbmodel = (TropicalFishModelB)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 0);
        }
        if (modelPart.equals("tail")) {
            return (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 1);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 2);
        }
        if (modelPart.equals("fin_left")) {
            return (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 3);
        }
        if (modelPart.equals("fin_top")) {
            return (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 4);
        }
        return modelPart.equals("fin_bottom") ? (e_4189_z)Reflector.ModelTropicalFishB_ModelRenderers.getValue(tropicalfishbmodel, 5) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tail", "fin_right", "fin_left", "fin_top", "fin_bottom"};
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
        if (!Reflector.RenderTropicalFish_modelB.exists()) {
            Config.warn("Model field not found: RenderTropicalFish.modelB");
            return null;
        }
        Reflector.RenderTropicalFish_modelB.setValue(tropicalfishrenderer1, modelBase);
        return tropicalfishrenderer1;
    }
}



