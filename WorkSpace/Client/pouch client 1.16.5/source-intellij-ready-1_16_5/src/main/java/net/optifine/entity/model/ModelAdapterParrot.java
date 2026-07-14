/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ParrotRenderer;
import lightning.product.P_2855_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterParrot
extends ModelAdapter {
    public ModelAdapterParrot() {
        super(t_5_h.O_508_d, "parrot", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new P_2855_e();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof P_2855_e)) {
            return null;
        }
        P_2855_e parrotmodel = (P_2855_e)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 0);
        }
        if (modelPart.equals("tail")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 1);
        }
        if (modelPart.equals("left_wing")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 2);
        }
        if (modelPart.equals("right_wing")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 3);
        }
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 4);
        }
        if (modelPart.equals("left_leg")) {
            return (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 9);
        }
        return modelPart.equals("right_leg") ? (e_4189_z)Reflector.getFieldValue(parrotmodel, Reflector.ModelParrot_ModelRenderers, 10) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tail", "left_wing", "right_wing", "head", "left_leg", "right_leg"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ParrotRenderer parrotrenderer = new ParrotRenderer(entityrenderermanager);
        parrotrenderer.v_4262_N = (P_2855_e)modelBase;
        parrotrenderer.R_4764_Y = shadowSize;
        return parrotrenderer;
    }
}



