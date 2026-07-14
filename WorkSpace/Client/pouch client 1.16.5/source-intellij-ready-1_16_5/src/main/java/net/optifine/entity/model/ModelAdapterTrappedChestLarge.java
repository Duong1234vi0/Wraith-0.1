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
import net.optifine.entity.model.ChestLargeModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterTrappedChestLarge
extends ModelAdapter {
    public ModelAdapterTrappedChestLarge() {
        super(BlockEntityType.R_4764_Y, "trapped_chest_large", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ChestLargeModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ChestLargeModel)) {
            return null;
        }
        ChestLargeModel chestlargemodel = (ChestLargeModel)model;
        if (modelPart.equals("lid_left")) {
            return chestlargemodel.lid_left;
        }
        if (modelPart.equals("base_left")) {
            return chestlargemodel.base_left;
        }
        if (modelPart.equals("knob_left")) {
            return chestlargemodel.knob_left;
        }
        if (modelPart.equals("lid_right")) {
            return chestlargemodel.lid_right;
        }
        if (modelPart.equals("base_right")) {
            return chestlargemodel.base_right;
        }
        return modelPart.equals("knob_right") ? chestlargemodel.knob_right : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"lid_left", "base_left", "knob_left", "lid_right", "base_right", "knob_right"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        ChestRenderer tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.R_4764_Y);
        if (!(tileentityrenderer instanceof ChestRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new ChestRenderer(tileentityrendererdispatcher);
        }
        if (!(modelBase instanceof ChestLargeModel)) {
            Config.warn("Not a large chest model: " + String.valueOf(modelBase));
            return null;
        }
        ChestLargeModel chestlargemodel = (ChestLargeModel)modelBase;
        return chestlargemodel.updateRenderer(tileentityrenderer);
    }
}


