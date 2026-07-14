/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ConduitRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.ConduitModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterConduit
extends ModelAdapter {
    public ModelAdapterConduit() {
        super(BlockEntityType.q_2307_F, "conduit", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ConduitModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof ConduitModel)) {
            return null;
        }
        ConduitModel conduitmodel = (ConduitModel)model;
        if (modelPart.equals("eye")) {
            return conduitmodel.eye;
        }
        if (modelPart.equals("wind")) {
            return conduitmodel.wind;
        }
        if (modelPart.equals("base")) {
            return conduitmodel.base;
        }
        return modelPart.equals("cage") ? conduitmodel.cage : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"eye", "wind", "base", "cage"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.q_2307_F);
        if (!(tileentityrenderer instanceof ConduitRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new ConduitRenderer(tileentityrendererdispatcher);
        }
        if (!(modelBase instanceof ConduitModel)) {
            Config.warn("Not a conduit model: " + String.valueOf(modelBase));
            return null;
        }
        ConduitModel conduitmodel = (ConduitModel)modelBase;
        return conduitmodel.updateRenderer(tileentityrenderer);
    }
}


