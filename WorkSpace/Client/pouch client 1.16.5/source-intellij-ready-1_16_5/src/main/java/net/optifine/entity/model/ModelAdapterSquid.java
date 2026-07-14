/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SquidModel;
import lightning.product.SquidRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSquid
extends ModelAdapter {
    public ModelAdapterSquid() {
        super(t_5_h.j_1564_a, "squid", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SquidModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SquidModel)) {
            return null;
        }
        SquidModel squidmodel = (SquidModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(squidmodel, Reflector.ModelSquid_body);
        }
        String s = "tentacle";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(squidmodel, Reflector.ModelSquid_tentacles);
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
        return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SquidRenderer squidrenderer = new SquidRenderer(entityrenderermanager);
        squidrenderer.v_4262_N = (SquidModel)modelBase;
        squidrenderer.R_4764_Y = shadowSize;
        return squidrenderer;
    }
}



