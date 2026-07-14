/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.WitchModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.WitchRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterVillager;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitch
extends ModelAdapterVillager {
    public ModelAdapterWitch() {
        super(t_5_h.RetryCallException, "witch", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new WitchModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof WitchModel)) {
            return null;
        }
        WitchModel witchmodel = (WitchModel)model;
        return modelPart.equals("mole") ? (e_4189_z)Reflector.getFieldValue(witchmodel, Reflector.ModelWitch_mole) : super.getModelRenderer(witchmodel, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] astring = super.getModelRendererNames();
        return (String[])Config.addObjectToArray(astring, "mole");
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WitchRenderer witchrenderer = new WitchRenderer(entityrenderermanager);
        witchrenderer.v_4262_N = (WitchModel)modelBase;
        witchrenderer.R_4764_Y = shadowSize;
        return witchrenderer;
    }
}



