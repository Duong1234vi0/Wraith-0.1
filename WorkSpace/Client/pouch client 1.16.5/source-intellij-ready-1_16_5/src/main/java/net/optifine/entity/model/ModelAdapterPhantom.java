/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.PhantomRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.PhantomModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterPhantom
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterPhantom() {
        super(t_5_h.r_715_M, "phantom", 0.75f);
    }

    @Override
    public v_3569_v makeModel() {
        return new PhantomModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        int i;
        e_4189_z modelrenderer;
        if (!(model instanceof PhantomModel)) {
            return null;
        }
        PhantomModel phantommodel = (PhantomModel)model;
        Map<String, Integer> map = ModelAdapterPhantom.getMapPartFields();
        if (map.containsKey(modelPart)) {
            int j = map.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(phantommodel, Reflector.ModelPhantom_ModelRenderers, j);
        }
        if (modelPart.equals("head") && (modelrenderer = (e_4189_z)Reflector.getFieldValue(phantommodel, Reflector.ModelPhantom_ModelRenderers, i = map.get("body").intValue())) != null) {
            return modelrenderer.n_1700_B(1);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return ModelAdapterPhantom.getMapPartFields().keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("body", 0);
        mapPartFields.put("left_wing", 1);
        mapPartFields.put("left_wing_tip", 2);
        mapPartFields.put("right_wing", 3);
        mapPartFields.put("right_wing_tip", 4);
        mapPartFields.put("tail", 5);
        mapPartFields.put("tail2", 6);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        PhantomRenderer phantomrenderer = new PhantomRenderer(entityrenderermanager);
        phantomrenderer.v_4262_N = (PhantomModel)modelBase;
        phantomrenderer.R_4764_Y = shadowSize;
        return phantomrenderer;
    }
}



