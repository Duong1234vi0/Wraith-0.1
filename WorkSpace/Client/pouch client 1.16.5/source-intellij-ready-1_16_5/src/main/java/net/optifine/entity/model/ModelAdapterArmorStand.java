/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ArmorStandArmorModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.ArmorStandModel;
import lightning.product.w_2040_b;
import lightning.product.ArmorStandRenderer;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;
import net.optifine.reflect.Reflector;

public class ModelAdapterArmorStand
extends ModelAdapterBiped {
    public ModelAdapterArmorStand() {
        super(t_5_h.J_1907_R, "armor_stand", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ArmorStandModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ArmorStandModel)) {
            return null;
        }
        ArmorStandModel armorstandmodel = (ArmorStandModel)model;
        if (modelPart.equals("right")) {
            return (e_4189_z)Reflector.getFieldValue(armorstandmodel, Reflector.ModelArmorStand_ModelRenderers, 0);
        }
        if (modelPart.equals("left")) {
            return (e_4189_z)Reflector.getFieldValue(armorstandmodel, Reflector.ModelArmorStand_ModelRenderers, 1);
        }
        if (modelPart.equals("waist")) {
            return (e_4189_z)Reflector.getFieldValue(armorstandmodel, Reflector.ModelArmorStand_ModelRenderers, 2);
        }
        return modelPart.equals("base") ? (e_4189_z)Reflector.getFieldValue(armorstandmodel, Reflector.ModelArmorStand_ModelRenderers, 3) : super.getModelRenderer(armorstandmodel, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] astring = super.getModelRendererNames();
        return (String[])Config.addObjectsToArray(astring, new String[]{"right", "left", "waist", "base"});
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ArmorStandRenderer armorstandrenderer = new ArmorStandRenderer(entityrenderermanager);
        armorstandrenderer.v_4262_N = (ArmorStandArmorModel)modelBase;
        armorstandrenderer.R_4764_Y = shadowSize;
        return armorstandrenderer;
    }
}



