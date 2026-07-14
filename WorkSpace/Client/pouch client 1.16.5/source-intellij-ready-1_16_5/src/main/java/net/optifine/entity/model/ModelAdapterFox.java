/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.FoxRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.FoxModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterFox
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterFox() {
        super(t_5_h.A_4115_X, "fox", 0.4f);
    }

    @Override
    public v_3569_v makeModel() {
        return new FoxModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof FoxModel)) {
            return null;
        }
        FoxModel foxmodel = (FoxModel)model;
        Map<String, Integer> map = ModelAdapterFox.getMapPartFields();
        if (map.containsKey(modelPart)) {
            int i = map.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(foxmodel, Reflector.ModelFox_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return ModelAdapterFox.getMapPartFields().keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("head", 0);
        mapPartFields.put("body", 4);
        mapPartFields.put("leg1", 5);
        mapPartFields.put("leg2", 6);
        mapPartFields.put("leg3", 7);
        mapPartFields.put("leg4", 8);
        mapPartFields.put("tail", 9);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        FoxRenderer foxrenderer = new FoxRenderer(entityrenderermanager);
        foxrenderer.v_4262_N = (FoxModel)modelBase;
        foxrenderer.R_4764_Y = shadowSize;
        return foxrenderer;
    }
}



