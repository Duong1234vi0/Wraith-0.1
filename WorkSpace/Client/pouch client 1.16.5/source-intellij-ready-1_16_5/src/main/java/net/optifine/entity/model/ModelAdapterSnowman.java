/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SnowGolemRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.SnowGolemModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSnowman
extends ModelAdapter {
    public ModelAdapterSnowman() {
        super(t_5_h.q_1982_R, "snow_golem", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SnowGolemModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SnowGolemModel)) {
            return null;
        }
        SnowGolemModel snowmanmodel = (SnowGolemModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelSnowman_ModelRenderers.getValue(snowmanmodel, 0);
        }
        if (modelPart.equals("body_bottom")) {
            return (e_4189_z)Reflector.ModelSnowman_ModelRenderers.getValue(snowmanmodel, 1);
        }
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelSnowman_ModelRenderers.getValue(snowmanmodel, 2);
        }
        if (modelPart.equals("right_hand")) {
            return (e_4189_z)Reflector.ModelSnowman_ModelRenderers.getValue(snowmanmodel, 3);
        }
        return modelPart.equals("left_hand") ? (e_4189_z)Reflector.ModelSnowman_ModelRenderers.getValue(snowmanmodel, 4) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SnowGolemRenderer snowmanrenderer = new SnowGolemRenderer(entityrenderermanager);
        snowmanrenderer.v_4262_N = (SnowGolemModel)modelBase;
        snowmanrenderer.R_4764_Y = shadowSize;
        return snowmanrenderer;
    }
}



