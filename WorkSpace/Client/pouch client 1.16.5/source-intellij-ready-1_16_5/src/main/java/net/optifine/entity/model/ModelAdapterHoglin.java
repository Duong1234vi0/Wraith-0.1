/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.HoglinRenderer;
import lightning.product.MinecraftClient;
import lightning.product.HoglinModel;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterHoglin
extends ModelAdapter {
    private static Map<String, Integer> mapParts = ModelAdapterHoglin.makeMapParts();

    public ModelAdapterHoglin() {
        super(t_5_h.e_4240_b, "hoglin", 0.7f);
    }

    public ModelAdapterHoglin(t_5_h entityType, String name, float shadowSize) {
        super(entityType, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new HoglinModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof HoglinModel)) {
            return null;
        }
        HoglinModel boarmodel = (HoglinModel)model;
        if (mapParts.containsKey(modelPart)) {
            int i = mapParts.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(boarmodel, Reflector.ModelBoar_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return mapParts.keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> makeMapParts() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("head", 0);
        map.put("right_ear", 1);
        map.put("left_ear", 2);
        map.put("body", 3);
        map.put("front_right_leg", 4);
        map.put("front_left_leg", 5);
        map.put("back_right_leg", 6);
        map.put("back_left_leg", 7);
        map.put("mane", 8);
        return map;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        HoglinRenderer hoglinrenderer = new HoglinRenderer(entityrenderermanager);
        hoglinrenderer.v_4262_N = (HoglinModel)modelBase;
        hoglinrenderer.R_4764_Y = shadowSize;
        return hoglinrenderer;
    }
}



