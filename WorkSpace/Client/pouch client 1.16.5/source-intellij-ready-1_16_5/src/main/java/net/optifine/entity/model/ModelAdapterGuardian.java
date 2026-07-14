/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.GuardianRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.GuardianModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterGuardian
extends ModelAdapter {
    public ModelAdapterGuardian() {
        super(t_5_h.x_607_J, "guardian", 0.5f);
    }

    public ModelAdapterGuardian(t_5_h entityType, String name, float shadowSize) {
        super(entityType, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new GuardianModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof GuardianModel)) {
            return null;
        }
        GuardianModel guardianmodel = (GuardianModel)model;
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.getFieldValue(guardianmodel, Reflector.ModelGuardian_body);
        }
        if (modelPart.equals("eye")) {
            return (e_4189_z)Reflector.getFieldValue(guardianmodel, Reflector.ModelGuardian_eye);
        }
        String s = "spine";
        if (modelPart.startsWith(s)) {
            e_4189_z[] amodelrenderer1 = (e_4189_z[])Reflector.getFieldValue(guardianmodel, Reflector.ModelGuardian_spines);
            if (amodelrenderer1 == null) {
                return null;
            }
            String s3 = modelPart.substring(s.length());
            int j = Config.parseInt(s3, -1);
            return --j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
        }
        String s1 = "tail";
        if (modelPart.startsWith(s1)) {
            e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.getFieldValue(guardianmodel, Reflector.ModelGuardian_tail);
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
        return new String[]{"body", "eye", "spine1", "spine2", "spine3", "spine4", "spine5", "spine6", "spine7", "spine8", "spine9", "spine10", "spine11", "spine12", "tail1", "tail2", "tail3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        GuardianRenderer guardianrenderer = new GuardianRenderer(entityrenderermanager);
        guardianrenderer.v_4262_N = (GuardianModel)modelBase;
        guardianrenderer.R_4764_Y = shadowSize;
        return guardianrenderer;
    }
}



