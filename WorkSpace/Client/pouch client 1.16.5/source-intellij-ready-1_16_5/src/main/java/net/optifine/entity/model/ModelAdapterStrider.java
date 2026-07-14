/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.StriderModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.StriderRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterStrider
extends ModelAdapter {
    private static Map<String, Integer> mapParts = ModelAdapterStrider.makeMapParts();

    public ModelAdapterStrider() {
        super(t_5_h.RealmsWorldOptions, "strider", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new StriderModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof StriderModel)) {
            return null;
        }
        StriderModel stridermodel = (StriderModel)model;
        if (mapParts.containsKey(modelPart)) {
            int i = mapParts.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(stridermodel, Reflector.ModelStrider_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return mapParts.keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> makeMapParts() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("right_leg", 0);
        map.put("left_leg", 1);
        map.put("body", 2);
        map.put("hair_right_bottom", 3);
        map.put("hair_right_middle", 4);
        map.put("hair_right_top", 5);
        map.put("hair_left_top", 6);
        map.put("hair_left_middle", 7);
        map.put("hair_left_bottom", 8);
        return map;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        StriderRenderer striderrenderer = new StriderRenderer(entityrenderermanager);
        striderrenderer.v_4262_N = (StriderModel)modelBase;
        striderrenderer.R_4764_Y = shadowSize;
        return striderrenderer;
    }
}



