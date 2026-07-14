/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.c_4467_L;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterMinecart;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecartTnt
extends ModelAdapterMinecart {
    public ModelAdapterMinecartTnt() {
        super(t_5_h.g_2268_R, "tnt_minecart", 0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        c_4467_L tntminecartrenderer = new c_4467_L(entityrenderermanager);
        if (!Reflector.RenderMinecart_modelMinecart.exists()) {
            Config.warn("Field not found: RenderMinecart.modelMinecart");
            return null;
        }
        Reflector.setFieldValue(tntminecartrenderer, Reflector.RenderMinecart_modelMinecart, modelBase);
        tntminecartrenderer.R_4764_Y = shadowSize;
        return tntminecartrenderer;
    }
}


