/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.BatModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.BatRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBat
extends ModelAdapter {
    public ModelAdapterBat() {
        super(t_5_h.G_564_y, "bat", 0.25f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BatModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BatModel)) {
            return null;
        }
        BatModel batmodel = (BatModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 1);
        }
        if (modelPart.equals("right_wing")) {
            return (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 2);
        }
        if (modelPart.equals("left_wing")) {
            return (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 3);
        }
        if (modelPart.equals("outer_right_wing")) {
            return (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 4);
        }
        return modelPart.equals("outer_left_wing") ? (e_4189_z)Reflector.getFieldValue(batmodel, Reflector.ModelBat_ModelRenderers, 5) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        BatRenderer batrenderer = new BatRenderer(entityrenderermanager);
        batrenderer.v_4262_N = (BatModel)modelBase;
        batrenderer.R_4764_Y = shadowSize;
        return batrenderer;
    }
}



