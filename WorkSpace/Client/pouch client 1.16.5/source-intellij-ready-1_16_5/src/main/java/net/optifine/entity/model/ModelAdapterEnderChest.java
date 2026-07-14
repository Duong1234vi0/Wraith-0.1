/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ChestRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.ChestModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterEnderChest
extends ModelAdapter {
    public ModelAdapterEnderChest() {
        super(BlockEntityType.G_564_y, "ender_chest", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ChestModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ChestModel)) {
            return null;
        }
        ChestModel chestmodel = (ChestModel)model;
        if (modelPart.equals("lid")) {
            return chestmodel.lid;
        }
        if (modelPart.equals("base")) {
            return chestmodel.base;
        }
        return modelPart.equals("knob") ? chestmodel.knob : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"lid", "base", "knob"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        ChestRenderer tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.G_564_y);
        if (!(tileentityrenderer instanceof ChestRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new ChestRenderer(tileentityrendererdispatcher);
        }
        if (!(modelBase instanceof ChestModel)) {
            Config.warn("Not a chest model: " + String.valueOf(modelBase));
            return null;
        }
        ChestModel chestmodel = (ChestModel)modelBase;
        return chestmodel.updateRenderer(tileentityrenderer);
    }
}


