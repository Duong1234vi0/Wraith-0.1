/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.O_1806_w;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSign
extends ModelAdapter {
    public ModelAdapterSign() {
        super(BlockEntityType.w_1484_f, "sign", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new O_1806_w.n_1700_B();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof O_1806_w.n_1700_B)) {
            return null;
        }
        O_1806_w.n_1700_B signtileentityrenderer$signmodel = (O_1806_w.n_1700_B)model;
        if (modelPart.equals("board")) {
            return (e_4189_z)Reflector.ModelSign_ModelRenderers.getValue(signtileentityrenderer$signmodel, 0);
        }
        return modelPart.equals("stick") ? (e_4189_z)Reflector.ModelSign_ModelRenderers.getValue(signtileentityrenderer$signmodel, 1) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"board", "stick"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.w_1484_f);
        if (!(tileentityrenderer instanceof O_1806_w)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new O_1806_w(tileentityrendererdispatcher);
        }
        if (!Reflector.TileEntitySignRenderer_model.exists()) {
            Config.warn("Field not found: TileEntitySignRenderer.model");
            return null;
        }
        Reflector.setFieldValue(tileentityrenderer, Reflector.TileEntitySignRenderer_model, modelBase);
        return tileentityrenderer;
    }
}


