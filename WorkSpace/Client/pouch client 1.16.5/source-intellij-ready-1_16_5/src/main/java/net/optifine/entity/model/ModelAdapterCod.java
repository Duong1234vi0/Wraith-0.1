/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.CodRenderer;
import lightning.product.MinecraftClient;
import lightning.product.CodModel;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterCod
extends ModelAdapter {
    public ModelAdapterCod() {
        super(t_5_h.u_2550_I, "cod", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new CodModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof CodModel)) {
            return null;
        }
        CodModel codmodel = (CodModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 0);
        }
        if (modelPart.equals("fin_back")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 1);
        }
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 2);
        }
        if (modelPart.equals("nose")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 3);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 4);
        }
        if (modelPart.equals("fin_left")) {
            return (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 5);
        }
        return modelPart.equals("tail") ? (e_4189_z)Reflector.ModelCod_ModelRenderers.getValue(codmodel, 6) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "fin_back", "head", "nose", "fin_right", "fin_left", "tail"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        CodRenderer codrenderer = new CodRenderer(entityrenderermanager);
        codrenderer.v_4262_N = (CodModel)modelBase;
        codrenderer.R_4764_Y = shadowSize;
        return codrenderer;
    }
}



