/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecartRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.MinecartModel;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecart
extends ModelAdapter {
    public ModelAdapterMinecart() {
        super(t_5_h.g_164_R, "minecart", 0.5f);
    }

    protected ModelAdapterMinecart(t_5_h type, String name, float shadow) {
        super(type, name, shadow);
    }

    @Override
    public v_3569_v makeModel() {
        return new MinecartModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof MinecartModel)) {
            return null;
        }
        MinecartModel minecartmodel = (MinecartModel)model;
        e_4189_z[] amodelrenderer = (e_4189_z[])Reflector.ModelMinecart_sideModels.getValue(minecartmodel);
        if (amodelrenderer != null) {
            if (modelPart.equals("bottom")) {
                return amodelrenderer[0];
            }
            if (modelPart.equals("back")) {
                return amodelrenderer[1];
            }
            if (modelPart.equals("front")) {
                return amodelrenderer[2];
            }
            if (modelPart.equals("right")) {
                return amodelrenderer[3];
            }
            if (modelPart.equals("left")) {
                return amodelrenderer[4];
            }
            if (modelPart.equals("dirt")) {
                return amodelrenderer[5];
            }
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        MinecartRenderer minecartrenderer = new MinecartRenderer(entityrenderermanager);
        if (!Reflector.RenderMinecart_modelMinecart.exists()) {
            Config.warn("Field not found: RenderMinecart.modelMinecart");
            return null;
        }
        Reflector.setFieldValue(minecartrenderer, Reflector.RenderMinecart_modelMinecart, modelBase);
        minecartrenderer.R_4764_Y = shadowSize;
        return minecartrenderer;
    }
}



