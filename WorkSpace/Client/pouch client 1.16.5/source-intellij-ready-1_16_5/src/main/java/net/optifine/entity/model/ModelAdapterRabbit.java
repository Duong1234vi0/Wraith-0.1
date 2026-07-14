/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.RabbitModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.RabbitRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterRabbit
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterRabbit() {
        super(t_5_h.UploadStatus, "rabbit", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new RabbitModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof RabbitModel)) {
            return null;
        }
        RabbitModel rabbitmodel = (RabbitModel)model;
        Map<String, Integer> map = ModelAdapterRabbit.getMapPartFields();
        if (map.containsKey(modelPart)) {
            int i = map.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(rabbitmodel, Reflector.ModelRabbit_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"};
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("left_foot", 0);
        mapPartFields.put("right_foot", 1);
        mapPartFields.put("left_thigh", 2);
        mapPartFields.put("right_thigh", 3);
        mapPartFields.put("body", 4);
        mapPartFields.put("left_arm", 5);
        mapPartFields.put("right_arm", 6);
        mapPartFields.put("head", 7);
        mapPartFields.put("right_ear", 8);
        mapPartFields.put("left_ear", 9);
        mapPartFields.put("tail", 10);
        mapPartFields.put("nose", 11);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        RabbitRenderer rabbitrenderer = new RabbitRenderer(entityrenderermanager);
        rabbitrenderer.v_4262_N = (RabbitModel)modelBase;
        rabbitrenderer.R_4764_Y = shadowSize;
        return rabbitrenderer;
    }
}



