/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.EndermiteRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.EndermiteModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEndermite
extends ModelAdapter {
    public ModelAdapterEndermite() {
        super(t_5_h.Q_2552_b, "endermite", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new EndermiteModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof EndermiteModel)) {
            return null;
        }
        EndermiteModel endermitemodel = (EndermiteModel)model;
        String s = "body";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(endermitemodel, Reflector.ModelEnderMite_bodyParts);
            if (amodelrenderer == null) {
                return null;
            }
            String s1 = modelPart.substring(s.length());
            int i = Config.parseInt(s1, -1);
            return --i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body1", "body2", "body3", "body4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        EndermiteRenderer endermiterenderer = new EndermiteRenderer(entityrenderermanager);
        endermiterenderer.v_4262_N = (EndermiteModel)modelBase;
        endermiterenderer.R_4764_Y = shadowSize;
        return endermiterenderer;
    }
}



