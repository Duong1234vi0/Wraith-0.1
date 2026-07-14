/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.LeashKnotRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.f_1643_e;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterLeadKnot
extends ModelAdapter {
    public ModelAdapterLeadKnot() {
        super(t_5_h.q_4610_l, "lead_knot", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new f_1643_e();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof f_1643_e)) {
            return null;
        }
        f_1643_e leashknotmodel = (f_1643_e)model;
        return modelPart.equals("knot") ? (e_4189_z)Reflector.ModelLeashKnot_knotRenderer.getValue(leashknotmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"knot"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        LeashKnotRenderer leashknotrenderer = new LeashKnotRenderer(entityrenderermanager);
        if (!Reflector.RenderLeashKnot_leashKnotModel.exists()) {
            Config.warn("Field not found: RenderLeashKnot.leashKnotModel");
            return null;
        }
        Reflector.setFieldValue(leashknotrenderer, Reflector.RenderLeashKnot_leashKnotModel, modelBase);
        leashknotrenderer.R_4764_Y = shadowSize;
        return leashknotrenderer;
    }
}



