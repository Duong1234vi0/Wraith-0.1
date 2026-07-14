/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ShulkerBulletRenderer;
import lightning.product.X_2599_Q;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterShulkerBullet
extends ModelAdapter {
    public ModelAdapterShulkerBullet() {
        super(t_5_h.h_4320_q, "shulker_bullet", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new X_2599_Q();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof X_2599_Q)) {
            return null;
        }
        X_2599_Q shulkerbulletmodel = (X_2599_Q)model;
        return modelPart.equals("bullet") ? (e_4189_z)Reflector.ModelShulkerBullet_renderer.getValue(shulkerbulletmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bullet"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ShulkerBulletRenderer shulkerbulletrenderer = new ShulkerBulletRenderer(entityrenderermanager);
        if (!Reflector.RenderShulkerBullet_model.exists()) {
            Config.warn("Field not found: RenderShulkerBullet.model");
            return null;
        }
        Reflector.setFieldValue(shulkerbulletrenderer, Reflector.RenderShulkerBullet_model, modelBase);
        shulkerbulletrenderer.R_4764_Y = shadowSize;
        return shulkerbulletrenderer;
    }
}



