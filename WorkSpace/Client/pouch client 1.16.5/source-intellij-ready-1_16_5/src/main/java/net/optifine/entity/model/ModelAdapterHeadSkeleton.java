/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.Map;
import lightning.product.SkullModel;
import lightning.product.W_2396_q;
import lightning.product.SkullBlock;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterHeadSkeleton
extends ModelAdapter {
    public ModelAdapterHeadSkeleton() {
        super(BlockEntityType.Q_4569_t, "head_skeleton", 0.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new SkullModel(0, 0, 64, 32);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof SkullModel)) {
            return null;
        }
        SkullModel genericheadmodel = (SkullModel)model;
        return modelPart.equals("head") ? (e_4189_z)Reflector.ModelGenericHead_skeletonHead.getValue(genericheadmodel) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        Map map;
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.Q_4569_t);
        if (!(tileentityrenderer instanceof W_2396_q)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new W_2396_q(tileentityrendererdispatcher);
        }
        if ((map = (Map)Reflector.TileEntitySkullRenderer_MODELS.getValue()) == null) {
            Config.warn("Field not found: TileEntitySkullRenderer.MODELS");
            return null;
        }
        map.put(SkullBlock.J_1907_R.n_1700_B, modelBase);
        return tileentityrenderer;
    }
}


