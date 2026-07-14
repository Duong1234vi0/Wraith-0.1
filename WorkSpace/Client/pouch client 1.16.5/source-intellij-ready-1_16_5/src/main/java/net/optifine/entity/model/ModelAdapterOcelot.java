/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.OcelotModel;
import lightning.product.OcelotRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterOcelot
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterOcelot() {
        super(t_5_h.s_2632_s, "ocelot", 0.4f);
    }

    protected ModelAdapterOcelot(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new OcelotModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof OcelotModel)) {
            return null;
        }
        OcelotModel ocelotmodel = (OcelotModel)model;
        Map<String, Integer> map = ModelAdapterOcelot.getMapPartFields();
        if (map.containsKey(modelPart)) {
            int i = map.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(ocelotmodel, Reflector.ModelOcelot_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("back_left_leg", 0);
        mapPartFields.put("back_right_leg", 1);
        mapPartFields.put("front_left_leg", 2);
        mapPartFields.put("front_right_leg", 3);
        mapPartFields.put("tail", 4);
        mapPartFields.put("tail2", 5);
        mapPartFields.put("head", 6);
        mapPartFields.put("body", 7);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        OcelotRenderer ocelotrenderer = new OcelotRenderer(entityrenderermanager);
        ocelotrenderer.v_4262_N = (OcelotModel)modelBase;
        ocelotrenderer.R_4764_Y = shadowSize;
        return ocelotrenderer;
    }
}



