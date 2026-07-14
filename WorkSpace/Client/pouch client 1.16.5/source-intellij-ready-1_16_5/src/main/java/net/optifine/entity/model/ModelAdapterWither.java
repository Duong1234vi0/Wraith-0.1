/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.WitherBossRenderer;
import lightning.product.WitherBossModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWither
extends ModelAdapter {
    public ModelAdapterWither() {
        super(t_5_h.r_3651_U, "wither", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new WitherBossModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof WitherBossModel)) {
            return null;
        }
        WitherBossModel withermodel = (WitherBossModel)model;
        String s = "body";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer1 = (e_4189_z[])Reflector.getFieldValue(withermodel, Reflector.ModelWither_bodyParts);
            if (amodelrenderer1 == null) {
                return null;
            }
            String s3 = modelPart.substring(s.length());
            int j = Config.parseInt(s3, -1);
            return --j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
        }
        String s1 = "head";
        if (modelPart.startsWith(s1)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(withermodel, Reflector.ModelWither_heads);
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
        return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WitherBossRenderer witherrenderer = new WitherBossRenderer(entityrenderermanager);
        witherrenderer.v_4262_N = (WitherBossModel)modelBase;
        witherrenderer.R_4764_Y = shadowSize;
        return witherrenderer;
    }
}



