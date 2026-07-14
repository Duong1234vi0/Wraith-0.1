/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.CreeperRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.CreeperModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterCreeper
extends ModelAdapter {
    public ModelAdapterCreeper() {
        super(t_5_h.P_4830_p, "creeper", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new CreeperModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof CreeperModel)) {
            return null;
        }
        CreeperModel creepermodel = (CreeperModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 0);
        }
        if (modelPart.equals("armor")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 1);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 2);
        }
        if (modelPart.equals("leg1")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 3);
        }
        if (modelPart.equals("leg2")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 4);
        }
        if (modelPart.equals("leg3")) {
            return (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 5);
        }
        return modelPart.equals("leg4") ? (e_4189_z)Reflector.ModelCreeper_ModelRenderers.getValue(creepermodel, 6) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        CreeperRenderer creeperrenderer = new CreeperRenderer(entityrenderermanager);
        creeperrenderer.v_4262_N = (CreeperModel)modelBase;
        creeperrenderer.R_4764_Y = shadowSize;
        return creeperrenderer;
    }
}



