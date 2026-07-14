/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.LavaSlimeModel;
import lightning.product.MagmaCubeRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterMagmaCube
extends ModelAdapter {
    public ModelAdapterMagmaCube() {
        super(t_5_h.B_1668_F, "magma_cube", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new LavaSlimeModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof LavaSlimeModel)) {
            return null;
        }
        LavaSlimeModel magmacubemodel = (LavaSlimeModel)model;
        if (modelPart.equals("core")) {
            return (e_4189_z)Reflector.getFieldValue(magmacubemodel, Reflector.ModelMagmaCube_core);
        }
        String s = "segment";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(magmacubemodel, Reflector.ModelMagmaCube_segments);
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
        return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        MagmaCubeRenderer magmacuberenderer = new MagmaCubeRenderer(entityrenderermanager);
        magmacuberenderer.v_4262_N = (LavaSlimeModel)modelBase;
        magmacuberenderer.R_4764_Y = shadowSize;
        return magmacuberenderer;
    }
}



