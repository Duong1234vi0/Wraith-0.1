/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SlimeModel;
import lightning.product.SlimeRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSlime
extends ModelAdapter {
    public ModelAdapterSlime() {
        super(t_5_h.V_1225_t, "slime", 0.25f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SlimeModel(16);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SlimeModel)) {
            return null;
        }
        SlimeModel slimemodel = (SlimeModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(slimemodel, Reflector.ModelSlime_ModelRenderers, 0);
        }
        if (modelPart.equals("left_eye")) {
            return (e_4189_z)Reflector.getFieldValue(slimemodel, Reflector.ModelSlime_ModelRenderers, 1);
        }
        if (modelPart.equals("right_eye")) {
            return (e_4189_z)Reflector.getFieldValue(slimemodel, Reflector.ModelSlime_ModelRenderers, 2);
        }
        return modelPart.equals("mouth") ? (e_4189_z)Reflector.getFieldValue(slimemodel, Reflector.ModelSlime_ModelRenderers, 3) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "left_eye", "right_eye", "mouth"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SlimeRenderer slimerenderer = new SlimeRenderer(entityrenderermanager);
        slimerenderer.v_4262_N = (SlimeModel)modelBase;
        slimerenderer.R_4764_Y = shadowSize;
        return slimerenderer;
    }
}



