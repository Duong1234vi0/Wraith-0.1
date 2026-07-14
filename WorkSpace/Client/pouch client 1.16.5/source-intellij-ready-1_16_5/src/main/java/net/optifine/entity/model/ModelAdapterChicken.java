/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ChickenRenderer;
import lightning.product.MinecraftClient;
import lightning.product.ChickenModel;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterChicken
extends ModelAdapter {
    public ModelAdapterChicken() {
        super(t_5_h.s_956_w, "chicken", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ChickenModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ChickenModel)) {
            return null;
        }
        ChickenModel chickenmodel = (ChickenModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 1);
        }
        if (modelPart.equals("right_leg")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 2);
        }
        if (modelPart.equals("left_leg")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 3);
        }
        if (modelPart.equals("right_wing")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 4);
        }
        if (modelPart.equals("left_wing")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 5);
        }
        if (modelPart.equals("bill")) {
            return (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 6);
        }
        return modelPart.equals("chin") ? (e_4189_z)Reflector.ModelChicken_ModelRenderers.getValue(chickenmodel, 7) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ChickenRenderer chickenrenderer = new ChickenRenderer(entityrenderermanager);
        chickenrenderer.v_4262_N = (ChickenModel)modelBase;
        chickenrenderer.R_4764_Y = shadowSize;
        return chickenrenderer;
    }
}



