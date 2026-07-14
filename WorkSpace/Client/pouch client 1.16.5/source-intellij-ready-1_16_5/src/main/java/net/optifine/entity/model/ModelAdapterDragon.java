/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.j_4563_n;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterDragon
extends ModelAdapter {
    public ModelAdapterDragon() {
        super(t_5_h.Y_601_j, "dragon", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new j_4563_n.n_1700_B();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof j_4563_n.n_1700_B)) {
            return null;
        }
        j_4563_n.n_1700_B enderdragonrenderer$enderdragonmodel = (j_4563_n.n_1700_B)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 0);
        }
        if (modelPart.equals("spine")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 1);
        }
        if (modelPart.equals("jaw")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 2);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 3);
        }
        if (modelPart.equals("left_wing")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 4);
        }
        if (modelPart.equals("left_wing_tip")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 5);
        }
        if (modelPart.equals("front_left_leg")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 6);
        }
        if (modelPart.equals("front_left_shin")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 7);
        }
        if (modelPart.equals("front_left_foot")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 8);
        }
        if (modelPart.equals("back_left_leg")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 9);
        }
        if (modelPart.equals("back_left_shin")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 10);
        }
        if (modelPart.equals("back_left_foot")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 11);
        }
        if (modelPart.equals("right_wing")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 12);
        }
        if (modelPart.equals("right_wing_tip")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 13);
        }
        if (modelPart.equals("front_right_leg")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 14);
        }
        if (modelPart.equals("front_right_shin")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 15);
        }
        if (modelPart.equals("front_right_foot")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 16);
        }
        if (modelPart.equals("back_right_leg")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 17);
        }
        if (modelPart.equals("back_right_shin")) {
            return (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 18);
        }
        return modelPart.equals("back_right_foot") ? (e_4189_z)Reflector.getFieldValue(enderdragonrenderer$enderdragonmodel, Reflector.ModelDragon_ModelRenderers, 19) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "spine", "jaw", "body", "left_wing", "left_wing_tip", "front_left_leg", "front_left_shin", "front_left_foot", "back_left_leg", "back_left_shin", "back_left_foot", "right_wing", "right_wing_tip", "front_right_leg", "front_right_shin", "front_right_foot", "back_right_leg", "back_right_shin", "back_right_foot"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        j_4563_n enderdragonrenderer = new j_4563_n(entityrenderermanager);
        if (!Reflector.EnderDragonRenderer_model.exists()) {
            Config.warn("Field not found: EnderDragonRenderer.model");
            return null;
        }
        Reflector.setFieldValue(enderdragonrenderer, Reflector.EnderDragonRenderer_model, modelBase);
        enderdragonrenderer.R_4764_Y = shadowSize;
        return enderdragonrenderer;
    }
}


