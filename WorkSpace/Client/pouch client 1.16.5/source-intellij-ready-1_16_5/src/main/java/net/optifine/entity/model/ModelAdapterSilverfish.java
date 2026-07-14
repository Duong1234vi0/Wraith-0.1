/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.SilverfishModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.SilverfishRenderer;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSilverfish
extends ModelAdapter {
    public ModelAdapterSilverfish() {
        super(t_5_h.t_4219_U, "silverfish", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SilverfishModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SilverfishModel)) {
            return null;
        }
        SilverfishModel silverfishmodel = (SilverfishModel)model;
        String s = "body";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer1 = (e_4189_z[])Reflector.getFieldValue(silverfishmodel, Reflector.ModelSilverfish_bodyParts);
            if (amodelrenderer1 == null) {
                return null;
            }
            String s3 = modelPart.substring(s.length());
            int j = Config.parseInt(s3, -1);
            return --j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
        }
        String s1 = "wing";
        if (modelPart.startsWith(s1)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(silverfishmodel, Reflector.ModelSilverfish_wingParts);
            if (amodelrenderer == null) {
                return null;
            }
            String s2 = modelPart.substring(s1.length());
            int i = Config.parseInt(s2, -1);
            return --i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SilverfishRenderer silverfishrenderer = new SilverfishRenderer(entityrenderermanager);
        silverfishrenderer.v_4262_N = (SilverfishModel)modelBase;
        silverfishrenderer.R_4764_Y = shadowSize;
        return silverfishrenderer;
    }
}



