/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.Iterator;
import lightning.product.GhastModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.u_588_T;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.ModelRendererUtils;

public class ModelAdapterGhast
extends ModelAdapter {
    public ModelAdapterGhast() {
        super(t_5_h.Y_1740_V, "ghast", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new GhastModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof GhastModel)) {
            return null;
        }
        GhastModel ghastmodel = (GhastModel)model;
        Iterator<e_4189_z> iterator = ghastmodel.n_1700_B().iterator();
        if (modelPart.equals("body")) {
            return ModelRendererUtils.getModelRenderer(iterator, 0);
        }
        String s = "tentacle";
        if (modelPart.startsWith(s)) {
            String s1 = modelPart.substring(s.length());
            int i = Config.parseInt(s1, -1);
            return ModelRendererUtils.getModelRenderer(iterator, i);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        u_588_T ghastrenderer = new u_588_T(entityrenderermanager);
        ghastrenderer.v_4262_N = (GhastModel)modelBase;
        ghastrenderer.R_4764_Y = shadowSize;
        return ghastrenderer;
    }
}



