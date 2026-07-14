/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.TurtleModel;
import lightning.product.QuadrupedModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.TurtleRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;
import net.optifine.reflect.Reflector;

public class ModelAdapterTurtle
extends ModelAdapterQuadruped {
    public ModelAdapterTurtle() {
        super(t_5_h.l_4537_E, "turtle", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new TurtleModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof QuadrupedModel)) {
            return null;
        }
        TurtleModel turtlemodel = (TurtleModel)model;
        return modelPart.equals("body2") ? (e_4189_z)Reflector.ModelTurtle_body2.getValue(turtlemodel) : super.getModelRenderer(model, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] astring = super.getModelRendererNames();
        return (String[])Config.addObjectToArray(astring, "body2");
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        TurtleRenderer turtlerenderer = new TurtleRenderer(entityrenderermanager);
        turtlerenderer.v_4262_N = (TurtleModel)modelBase;
        turtlerenderer.R_4764_Y = shadowSize;
        return turtlerenderer;
    }
}



