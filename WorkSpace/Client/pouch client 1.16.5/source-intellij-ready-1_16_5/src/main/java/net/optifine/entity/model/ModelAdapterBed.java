/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.BedRenderer;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.BedModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterBed
extends ModelAdapter {
    public ModelAdapterBed() {
        super(BlockEntityType.k_2293_S, "bed", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BedModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BedModel)) {
            return null;
        }
        BedModel bedmodel = (BedModel)model;
        if (modelPart.equals("head")) {
            return bedmodel.headPiece;
        }
        if (modelPart.equals("foot")) {
            return bedmodel.footPiece;
        }
        e_4189_z[] amodelrenderer = bedmodel.legs;
        if (amodelrenderer != null) {
            if (modelPart.equals("leg1")) {
                return amodelrenderer[0];
            }
            if (modelPart.equals("leg2")) {
                return amodelrenderer[1];
            }
            if (modelPart.equals("leg3")) {
                return amodelrenderer[2];
            }
            if (modelPart.equals("leg4")) {
                return amodelrenderer[3];
            }
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "foot", "leg1", "leg2", "leg3", "leg4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.k_2293_S);
        if (!(tileentityrenderer instanceof BedRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new BedRenderer(tileentityrendererdispatcher);
        }
        if (!(modelBase instanceof BedModel)) {
            Config.warn("Not a BedModel: " + String.valueOf(modelBase));
            return null;
        }
        BedModel bedmodel = (BedModel)modelBase;
        return bedmodel.updateRenderer(tileentityrenderer);
    }
}


