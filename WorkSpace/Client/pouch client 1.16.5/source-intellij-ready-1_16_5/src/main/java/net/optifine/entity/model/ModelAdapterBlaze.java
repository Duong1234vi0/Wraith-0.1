/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.BlazeModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.BlazeRenderer;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBlaze
extends ModelAdapter {
    public ModelAdapterBlaze() {
        super(t_5_h.u_1723_Y, "blaze", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BlazeModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BlazeModel)) {
            return null;
        }
        BlazeModel blazemodel = (BlazeModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.getFieldValue(blazemodel, Reflector.ModelBlaze_blazeHead);
        }
        String s = "stick";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(blazemodel, Reflector.ModelBlaze_blazeSticks);
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
        return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        BlazeRenderer blazerenderer = new BlazeRenderer(entityrenderermanager);
        blazerenderer.v_4262_N = (BlazeModel)modelBase;
        blazerenderer.R_4764_Y = shadowSize;
        return blazerenderer;
    }
}



