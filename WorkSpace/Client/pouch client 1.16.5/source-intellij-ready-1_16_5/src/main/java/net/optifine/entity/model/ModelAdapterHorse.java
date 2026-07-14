/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import lightning.product.MinecraftClient;
import lightning.product.HorseModel;
import lightning.product.e_4189_z;
import lightning.product.HorseRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterHorse
extends ModelAdapter {
    private static Map<String, Integer> mapParts = ModelAdapterHorse.makeMapParts();
    private static Map<String, Integer> mapPartsNeck = ModelAdapterHorse.makeMapPartsNeck();
    private static Map<String, Integer> mapPartsBody = ModelAdapterHorse.makeMapPartsBody();

    public ModelAdapterHorse() {
        super(t_5_h.n_3318_d, "horse", 0.75f);
    }

    protected ModelAdapterHorse(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new HorseModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof HorseModel)) {
            return null;
        }
        HorseModel horsemodel = (HorseModel)model;
        if (mapParts.containsKey(modelPart)) {
            int j = mapParts.get(modelPart);
            return (e_4189_z)Reflector.getFieldValue(horsemodel, Reflector.ModelHorse_ModelRenderers, j);
        }
        if (mapPartsNeck.containsKey(modelPart)) {
            e_4189_z modelrenderer1 = this.getModelRenderer(horsemodel, "neck");
            int k = mapPartsNeck.get(modelPart);
            return modelrenderer1.n_1700_B(k);
        }
        if (mapPartsBody.containsKey(modelPart)) {
            e_4189_z modelrenderer = this.getModelRenderer(horsemodel, "body");
            int i = mapPartsBody.get(modelPart);
            return modelrenderer.n_1700_B(i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "neck", "back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "child_back_left_leg", "child_back_right_leg", "child_front_left_leg", "child_front_right_leg", "tail", "saddle", "head", "mane", "mouth", "left_ear", "right_ear", "left_bit", "right_bit", "left_rein", "right_rein", "headpiece", "noseband"};
    }

    private static Map<String, Integer> makeMapParts() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("body", 0);
        map.put("neck", 1);
        map.put("back_left_leg", 2);
        map.put("back_right_leg", 3);
        map.put("front_left_leg", 4);
        map.put("front_right_leg", 5);
        map.put("child_back_left_leg", 6);
        map.put("child_back_right_leg", 7);
        map.put("child_front_left_leg", 8);
        map.put("child_front_right_leg", 9);
        return map;
    }

    private static Map<String, Integer> makeMapPartsNeck() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("head", 0);
        map.put("mane", 1);
        map.put("mouth", 2);
        map.put("left_ear", 3);
        map.put("right_ear", 4);
        map.put("left_bit", 5);
        map.put("right_bit", 6);
        map.put("left_rein", 7);
        map.put("right_rein", 8);
        map.put("headpiece", 9);
        map.put("noseband", 10);
        return map;
    }

    private static Map<String, Integer> makeMapPartsBody() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("tail", 0);
        map.put("saddle", 1);
        return map;
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        HorseRenderer horserenderer = new HorseRenderer(entityrenderermanager);
        horserenderer.v_4262_N = (HorseModel)modelBase;
        horserenderer.R_4764_Y = shadowSize;
        return horserenderer;
    }
}



