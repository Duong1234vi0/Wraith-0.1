/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import lightning.product.BannerRenderer;
import net.optifine.Config;
import net.optifine.entity.model.BannerModel;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterBanner
extends ModelAdapter {
    public ModelAdapterBanner() {
        super(BlockEntityType.w_1457_N, "banner", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new BannerModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BannerModel)) {
            return null;
        }
        BannerModel bannermodel = (BannerModel)model;
        if (modelPart.equals("slate")) {
            return bannermodel.bannerSlate;
        }
        if (modelPart.equals("stand")) {
            return bannermodel.bannerStand;
        }
        return modelPart.equals("top") ? bannermodel.bannerTop : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"slate", "stand", "top"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v model, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.w_1457_N);
        if (!(tileentityrenderer instanceof BannerRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new BannerRenderer(tileentityrendererdispatcher);
        }
        if (!(model instanceof BannerModel)) {
            Config.warn("Not a banner model: " + String.valueOf(model));
            return null;
        }
        BannerModel bannermodel = (BannerModel)model;
        return bannermodel.updateRenderer(tileentityrenderer);
    }
}


