/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.EndCrystalRenderer;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.EnderCrystalModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterEnderCrystal
extends ModelAdapter {
    public ModelAdapterEnderCrystal() {
        this("end_crystal");
    }

    protected ModelAdapterEnderCrystal(String name) {
        super(t_5_h.w_1457_N, name, 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new EnderCrystalModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof EnderCrystalModel)) {
            return null;
        }
        EnderCrystalModel endercrystalmodel = (EnderCrystalModel)model;
        if (modelPart.equals("cube")) {
            return endercrystalmodel.cube;
        }
        if (modelPart.equals("glass")) {
            return endercrystalmodel.glass;
        }
        return modelPart.equals("base") ? endercrystalmodel.base : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"cube", "glass", "base"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Z_2049_e entityrenderer = entityrenderermanager.P_1922_E().get(t_5_h.w_1457_N);
        if (!(entityrenderer instanceof EndCrystalRenderer)) {
            Config.warn("Not an instance of RenderEnderCrystal: " + String.valueOf(entityrenderer));
            return null;
        }
        EndCrystalRenderer endercrystalrenderer = (EndCrystalRenderer)entityrenderer;
        if (endercrystalrenderer.getType() == null) {
            endercrystalrenderer = new EndCrystalRenderer(entityrenderermanager);
        }
        if (!(modelBase instanceof EnderCrystalModel)) {
            Config.warn("Not a EnderCrystalModel model: " + String.valueOf(modelBase));
            return null;
        }
        EnderCrystalModel endercrystalmodel = (EnderCrystalModel)modelBase;
        endercrystalrenderer = endercrystalmodel.updateRenderer(endercrystalrenderer);
        endercrystalrenderer.R_4764_Y = shadowSize;
        return endercrystalrenderer;
    }
}



