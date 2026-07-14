/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.IronGolemRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.IronGolemModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterIronGolem
extends ModelAdapter {
    public ModelAdapterIronGolem() {
        super(t_5_h.v_4276_D, "iron_golem", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new IronGolemModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof IronGolemModel)) {
            return null;
        }
        IronGolemModel irongolemmodel = (IronGolemModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 1);
        }
        if (modelPart.equals("right_arm")) {
            return (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 2);
        }
        if (modelPart.equals("left_arm")) {
            return (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 3);
        }
        if (modelPart.equals("left_leg")) {
            return (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 4);
        }
        return modelPart.equals("right_leg") ? (e_4189_z)Reflector.ModelIronGolem_ModelRenderers.getValue(irongolemmodel, 5) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        IronGolemRenderer irongolemrenderer = new IronGolemRenderer(entityrenderermanager);
        irongolemrenderer.v_4262_N = (IronGolemModel)modelBase;
        irongolemrenderer.R_4764_Y = shadowSize;
        return irongolemrenderer;
    }
}



