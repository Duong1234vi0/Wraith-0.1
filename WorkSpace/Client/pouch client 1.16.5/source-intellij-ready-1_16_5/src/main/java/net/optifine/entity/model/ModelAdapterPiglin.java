/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.ArrayList;
import java.util.Arrays;
import lightning.product.I_3755_Y;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.o_2315_w;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterPiglin
extends ModelAdapterBiped {
    public ModelAdapterPiglin() {
        super(t_5_h.i_1637_u, "piglin", 0.5f);
    }

    protected ModelAdapterPiglin(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new I_3755_Y(0.0f, 64, 64);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (model instanceof I_3755_Y) {
            I_3755_Y piglinmodel = (I_3755_Y)model;
            if (modelPart.equals("left_ear")) {
                return piglinmodel.M_588_G;
            }
            if (modelPart.equals("right_ear")) {
                return piglinmodel.P_4830_p;
            }
        }
        return super.getModelRenderer(model, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(super.getModelRendererNames()));
        list.add("left_ear");
        list.add("right_ear");
        return list.toArray(new String[list.size()]);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        o_2315_w piglinrenderer = new o_2315_w(entityrenderermanager, false);
        piglinrenderer.v_4262_N = (I_3755_Y)modelBase;
        piglinrenderer.R_4764_Y = shadowSize;
        return piglinrenderer;
    }
}


