/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.ShulkerBoxRenderer;
import lightning.product.ShulkerModel;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterShulkerBox
extends ModelAdapter {
    public ModelAdapterShulkerBox() {
        super(BlockEntityType.C_2741_M, "shulker_box", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ShulkerModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ShulkerModel)) {
            return null;
        }
        ShulkerModel shulkermodel = (ShulkerModel)model;
        if (modelPart.equals("base")) {
            return (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 0);
        }
        if (modelPart.equals("lid")) {
            return (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 1);
        }
        return modelPart.equals("head") ? (e_4189_z)Reflector.ModelShulker_ModelRenderers.getValue(shulkermodel, 2) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "lid", "head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.C_2741_M);
        if (!(tileentityrenderer instanceof ShulkerBoxRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new ShulkerBoxRenderer((ShulkerModel)modelBase, tileentityrendererdispatcher);
        }
        if (!Reflector.TileEntityShulkerBoxRenderer_model.exists()) {
            Config.warn("Field not found: TileEntityShulkerBoxRenderer.model");
            return null;
        }
        Reflector.setFieldValue(tileentityrenderer, Reflector.TileEntityShulkerBoxRenderer_model, modelBase);
        return tileentityrenderer;
    }
}


