/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.ArrayList;
import java.util.Arrays;
import lightning.product.ChestedHorseRenderer;
import lightning.product.ChestedHorseModel;
import lightning.product.EntityModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterHorse;
import net.optifine.reflect.Reflector;

public class ModelAdapterDonkey
extends ModelAdapterHorse {
    public ModelAdapterDonkey() {
        super(t_5_h.Q_4569_t, "donkey", 0.75f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ChestedHorseModel(0.87f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ChestedHorseModel)) {
            return null;
        }
        ChestedHorseModel horsearmorchestsmodel = (ChestedHorseModel)model;
        if (modelPart.equals("left_chest")) {
            return (e_4189_z)Reflector.ModelHorseChests_ModelRenderers.getValue(horsearmorchestsmodel, 0);
        }
        return modelPart.equals("right_chest") ? (e_4189_z)Reflector.ModelHorseChests_ModelRenderers.getValue(horsearmorchestsmodel, 1) : super.getModelRenderer(model, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(super.getModelRendererNames()));
        list.add("left_chest");
        list.add("right_chest");
        return list.toArray(new String[list.size()]);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ChestedHorseRenderer chestedhorserenderer = new ChestedHorseRenderer(entityrenderermanager, 0.87f);
        chestedhorserenderer.v_4262_N = (EntityModel)modelBase;
        chestedhorserenderer.R_4764_Y = shadowSize;
        return chestedhorserenderer;
    }
}



