/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.M_2044_c;
import lightning.product.VexModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;
import net.optifine.reflect.Reflector;

public class ModelAdapterVex
extends ModelAdapterBiped {
    public ModelAdapterVex() {
        super(t_5_h.F_2624_D, "vex", 0.3f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof VexModel)) {
            return null;
        }
        e_4189_z modelrenderer = super.getModelRenderer(model, modelPart);
        if (modelrenderer != null) {
            return modelrenderer;
        }
        VexModel vexmodel = (VexModel)model;
        if (modelPart.equals("left_wing")) {
            return (e_4189_z)Reflector.getFieldValue(vexmodel, Reflector.ModelVex_leftWing);
        }
        return modelPart.equals("right_wing") ? (e_4189_z)Reflector.getFieldValue(vexmodel, Reflector.ModelVex_rightWing) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] astring = super.getModelRendererNames();
        return (String[])Config.addObjectsToArray(astring, new String[]{"left_wing", "right_wing"});
    }

    @Override
    public v_3569_v makeModel() {
        return new VexModel();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        M_2044_c vexrenderer = new M_2044_c(entityrenderermanager);
        vexrenderer.v_4262_N = (VexModel)modelBase;
        vexrenderer.R_4764_Y = shadowSize;
        return vexrenderer;
    }
}



