/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.Iterator;
import lightning.product.DolphinModel;
import lightning.product.DolphinRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.ModelRendererUtils;

public class ModelAdapterDolphin
extends ModelAdapter {
    public ModelAdapterDolphin() {
        super(t_5_h.h_1847_R, "dolphin", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new DolphinModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof DolphinModel)) {
            return null;
        }
        DolphinModel dolphinmodel = (DolphinModel)model;
        Iterator<e_4189_z> iterator = dolphinmodel.n_1700_B().iterator();
        e_4189_z modelrenderer = ModelRendererUtils.getModelRenderer(iterator, 0);
        if (modelrenderer == null) {
            return null;
        }
        if (modelPart.equals("body")) {
            return modelrenderer;
        }
        if (modelPart.equals("back_fin")) {
            return modelrenderer.n_1700_B(0);
        }
        if (modelPart.equals("left_fin")) {
            return modelrenderer.n_1700_B(1);
        }
        if (modelPart.equals("right_fin")) {
            return modelrenderer.n_1700_B(2);
        }
        if (modelPart.equals("tail")) {
            return modelrenderer.n_1700_B(3);
        }
        if (modelPart.equals("tail_fin")) {
            return modelrenderer.n_1700_B(3).n_1700_B(0);
        }
        return modelPart.equals("head") ? modelrenderer.n_1700_B(4) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "back_fin", "left_fin", "right_fin", "tail", "tail_fin", "head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        DolphinRenderer dolphinrenderer = new DolphinRenderer(entityrenderermanager);
        dolphinrenderer.v_4262_N = (DolphinModel)modelBase;
        dolphinrenderer.R_4764_Y = shadowSize;
        return dolphinrenderer;
    }
}



