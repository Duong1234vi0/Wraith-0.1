/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.L_3489_J;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.BeeModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBee
extends ModelAdapter {
    private static Map<String, Integer> mapParts = ModelAdapterBee.makeMapParts();

    public ModelAdapterBee() {
        super(t_5_h.P_1922_E, "bee", 0.4f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BeeModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BeeModel)) {
            return null;
        }
        BeeModel beemodel = (BeeModel)model;
        if (mapParts.containsKey(modelPart)) {
            int i = mapParts.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(beemodel, Reflector.ModelBee_ModelRenderers, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return mapParts.keySet().toArray(new String[0]);
    }

    private static Map<String, Integer> makeMapParts() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("body", 0);
        map.put("torso", 1);
        map.put("right_wing", 2);
        map.put("left_wing", 3);
        map.put("front_legs", 4);
        map.put("middle_legs", 5);
        map.put("back_legs", 6);
        map.put("stinger", 7);
        map.put("left_antenna", 8);
        map.put("right_antenna", 9);
        return map;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        L_3489_J beerenderer = new L_3489_J(entityrenderermanager);
        beerenderer.v_4262_N = (BeeModel)modelBase;
        beerenderer.R_4764_Y = shadowSize;
        return beerenderer;
    }
}



