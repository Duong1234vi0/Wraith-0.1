/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SkullModel;
import lightning.product.WitherSkullRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitherSkull
extends ModelAdapter {
    public ModelAdapterWitherSkull() {
        super(t_5_h.LongRunningTask, "wither_skull", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SkullModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SkullModel)) {
            return null;
        }
        SkullModel genericheadmodel = (SkullModel)model;
        return modelPart.equals("head") ? (e_4189_z)Reflector.ModelGenericHead_skeletonHead.getValue(genericheadmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        WitherSkullRenderer witherskullrenderer = new WitherSkullRenderer(entityrenderermanager);
        if (!Reflector.RenderWitherSkull_model.exists()) {
            Config.warn("Field not found: RenderWitherSkull_model");
            return null;
        }
        Reflector.setFieldValue(witherskullrenderer, Reflector.RenderWitherSkull_model, modelBase);
        witherskullrenderer.R_4764_Y = shadowSize;
        return witherskullrenderer;
    }
}



