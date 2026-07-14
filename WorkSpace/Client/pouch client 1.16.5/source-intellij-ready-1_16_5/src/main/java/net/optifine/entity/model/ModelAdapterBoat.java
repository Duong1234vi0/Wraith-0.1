/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package net.optifine.entity.model;

import com.google.common.collect.ImmutableList;
import lightning.product.BoatModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.m_4813_h;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.ModelRendererUtils;
import net.optifine.reflect.Reflector;

public class ModelAdapterBoat
extends ModelAdapter {
    public ModelAdapterBoat() {
        super(t_5_h.v_4262_N, "boat", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BoatModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BoatModel)) {
            return null;
        }
        BoatModel boatmodel = (BoatModel)model;
        ImmutableList<e_4189_z> immutablelist = boatmodel.J_1907_R();
        if (immutablelist != null) {
            if (modelPart.equals("bottom")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 0);
            }
            if (modelPart.equals("back")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 1);
            }
            if (modelPart.equals("front")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 2);
            }
            if (modelPart.equals("right")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 3);
            }
            if (modelPart.equals("left")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 4);
            }
            if (modelPart.equals("paddle_left")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 5);
            }
            if (modelPart.equals("paddle_right")) {
                return ModelRendererUtils.getModelRenderer(immutablelist, 6);
            }
        }
        return modelPart.equals("bottom_no_water") ? boatmodel.R_4764_Y() : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bottom", "back", "front", "right", "left", "paddle_left", "paddle_right", "bottom_no_water"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        m_4813_h boatrenderer = new m_4813_h(entityrenderermanager);
        if (!Reflector.RenderBoat_modelBoat.exists()) {
            Config.warn("Field not found: RenderBoat.modelBoat");
            return null;
        }
        Reflector.setFieldValue(boatrenderer, Reflector.RenderBoat_modelBoat, modelBase);
        boatrenderer.R_4764_Y = shadowSize;
        return boatrenderer;
    }
}



