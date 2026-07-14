/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.RavagerModel;
import lightning.product.RavagerRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterRavager
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterRavager() {
        super(t_5_h.e_1992_r, "ravager", 1.1f);
    }

    @Override
    public v_3569_v makeModel() {
        return new RavagerModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof RavagerModel)) {
            return null;
        }
        RavagerModel ravagermodel = (RavagerModel)model;
        Map<String, Integer> map = ModelAdapterRavager.getMapPartFields();
        if (map.containsKey(modelPart)) {
            int i = map.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(ravagermodel, Reflector.ModelRavager_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return ModelAdapterRavager.getMapPartFields().keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("head", 0);
        mapPartFields.put("jaw", 1);
        mapPartFields.put("body", 2);
        mapPartFields.put("leg1", 3);
        mapPartFields.put("leg2", 4);
        mapPartFields.put("leg3", 5);
        mapPartFields.put("leg4", 6);
        mapPartFields.put("neck", 7);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        RavagerRenderer ravagerrenderer = new RavagerRenderer(entityrenderermanager);
        ravagerrenderer.v_4262_N = (RavagerModel)modelBase;
        ravagerrenderer.R_4764_Y = shadowSize;
        return ravagerrenderer;
    }
}



