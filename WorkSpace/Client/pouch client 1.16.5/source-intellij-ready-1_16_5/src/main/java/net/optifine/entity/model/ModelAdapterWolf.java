/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.WolfRenderer;
import lightning.product.e_4189_z;
import lightning.product.WolfModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWolf
extends ModelAdapter {
    public ModelAdapterWolf() {
        super(t_5_h.j_2266_I, "wolf", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new WolfModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof WolfModel)) {
            return null;
        }
        WolfModel wolfmodel = (WolfModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 2);
        }
        if (modelPart.equals("leg1")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 3);
        }
        if (modelPart.equals("leg2")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 4);
        }
        if (modelPart.equals("leg3")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 5);
        }
        if (modelPart.equals("leg4")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 6);
        }
        if (modelPart.equals("tail")) {
            return (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 7);
        }
        return modelPart.equals("mane") ? (e_4189_z)Reflector.ModelWolf_ModelRenderers.getValue(wolfmodel, 9) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WolfRenderer wolfrenderer = new WolfRenderer(entityrenderermanager);
        wolfrenderer.v_4262_N = (WolfModel)modelBase;
        wolfrenderer.R_4764_Y = shadowSize;
        return wolfrenderer;
    }
}



