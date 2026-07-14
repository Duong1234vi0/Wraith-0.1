/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SalmonModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.SalmonRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSalmon
extends ModelAdapter {
    public ModelAdapterSalmon() {
        super(t_5_h.D_60_a, "salmon", 0.3f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SalmonModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        e_4189_z modelrenderer2;
        e_4189_z modelrenderer1;
        e_4189_z modelrenderer;
        if (!(model instanceof SalmonModel)) {
            return null;
        }
        SalmonModel salmonmodel = (SalmonModel)model;
        if (modelPart.equals("body_front")) {
            return (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 0);
        }
        if (modelPart.equals("body_back")) {
            return (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 1);
        }
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 2);
        }
        if (modelPart.equals("fin_back_1") && (modelrenderer = (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 0)) != null) {
            return modelrenderer.n_1700_B(0);
        }
        if (modelPart.equals("fin_back_2") && (modelrenderer1 = (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 1)) != null) {
            return modelrenderer1.n_1700_B(1);
        }
        if (modelPart.equals("tail") && (modelrenderer2 = (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 1)) != null) {
            return modelrenderer2.n_1700_B(0);
        }
        if (modelPart.equals("fin_right")) {
            return (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 3);
        }
        return modelPart.equals("fin_left") ? (e_4189_z)Reflector.ModelSalmon_ModelRenderers.getValue(salmonmodel, 4) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body_front", "body_back", "head", "fin_back_1", "fin_back_2", "tail", "fin_right", "fin_left"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SalmonRenderer salmonrenderer = new SalmonRenderer(entityrenderermanager);
        salmonrenderer.v_4262_N = (SalmonModel)modelBase;
        salmonrenderer.R_4764_Y = shadowSize;
        return salmonrenderer;
    }
}



