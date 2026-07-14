/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ThrownTridentRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.TridentModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterTrident
extends ModelAdapter {
    public ModelAdapterTrident() {
        super(t_5_h.ValueObject, "trident", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new TridentModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof TridentModel)) {
            return null;
        }
        TridentModel tridentmodel = (TridentModel)model;
        return modelPart.equals("body") ? (e_4189_z)Reflector.ModelTrident_tridentRenderer.getValue(tridentmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ThrownTridentRenderer tridentrenderer = new ThrownTridentRenderer(entityrenderermanager);
        if (!Reflector.RenderTrident_modelTrident.exists()) {
            Config.warn("Field not found: RenderTrident.modelTrident");
            return null;
        }
        Reflector.setFieldValue(tridentrenderer, Reflector.RenderTrident_modelTrident, modelBase);
        tridentrenderer.R_4764_Y = shadowSize;
        return tridentrenderer;
    }
}



