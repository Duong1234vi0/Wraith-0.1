/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.BellRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.BellModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterBell
extends ModelAdapter {
    public ModelAdapterBell() {
        super(BlockEntityType.Y_1740_V, "bell", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BellModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BellModel)) {
            return null;
        }
        BellModel bellmodel = (BellModel)model;
        return modelPart.equals("body") ? bellmodel.bellBody : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v model, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.Y_1740_V);
        if (!(tileentityrenderer instanceof BellRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new BellRenderer(tileentityrendererdispatcher);
        }
        if (!(model instanceof BellModel)) {
            Config.warn("Not a bell model: " + String.valueOf(model));
            return null;
        }
        BellModel bellmodel = (BellModel)model;
        return bellmodel.updateRenderer(tileentityrenderer);
    }
}


