/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.SpiderModel;
import lightning.product.EntityModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.SpiderRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSpider
extends ModelAdapter {
    public ModelAdapterSpider() {
        super(t_5_h.RealmsServerPing, "spider", 1.0f);
    }

    protected ModelAdapterSpider(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new SpiderModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SpiderModel)) {
            return null;
        }
        SpiderModel spidermodel = (SpiderModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 0);
        }
        if (modelPart.equals("neck")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 1);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 2);
        }
        if (modelPart.equals("leg1")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 3);
        }
        if (modelPart.equals("leg2")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 4);
        }
        if (modelPart.equals("leg3")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 5);
        }
        if (modelPart.equals("leg4")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 6);
        }
        if (modelPart.equals("leg5")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 7);
        }
        if (modelPart.equals("leg6")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 8);
        }
        if (modelPart.equals("leg7")) {
            return (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 9);
        }
        return modelPart.equals("leg8") ? (e_4189_z)Reflector.ModelSpider_ModelRenderers.getValue(spidermodel, 10) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        SpiderRenderer spiderrenderer = new SpiderRenderer(entityrenderermanager);
        spiderrenderer.v_4262_N = (EntityModel)modelBase;
        spiderrenderer.R_4764_Y = shadowSize;
        return spiderrenderer;
    }
}



