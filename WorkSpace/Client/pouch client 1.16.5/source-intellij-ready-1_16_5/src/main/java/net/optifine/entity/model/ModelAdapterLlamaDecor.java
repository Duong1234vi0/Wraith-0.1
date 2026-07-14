/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.Iterator;
import java.util.List;
import lightning.product.RenderLayer;
import lightning.product.LlamaRenderer;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.LlamaDecorLayer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.LlamaModel;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterLlama;
import net.optifine.reflect.Reflector;

public class ModelAdapterLlamaDecor
extends ModelAdapterLlama {
    public ModelAdapterLlamaDecor() {
        super(t_5_h.g_221_o, "llama_decor", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new LlamaModel(0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Z_2049_e entityrenderer = entityrenderermanager.P_1922_E().get(t_5_h.g_221_o);
        if (!(entityrenderer instanceof LlamaRenderer)) {
            Config.warn("Not a RenderLlama: " + String.valueOf(entityrenderer));
            return null;
        }
        if (entityrenderer.getType() == null) {
            LlamaRenderer llamarenderer = new LlamaRenderer(entityrenderermanager);
            llamarenderer.v_4262_N = new LlamaModel(0.0f);
            llamarenderer.R_4764_Y = 0.7f;
            entityrenderer = llamarenderer;
        }
        LlamaRenderer llamarenderer1 = (LlamaRenderer)entityrenderer;
        List list = llamarenderer1.P_1922_E();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            RenderLayer layerrenderer = iterator.next();
            if (!(layerrenderer instanceof LlamaDecorLayer)) continue;
            iterator.remove();
        }
        LlamaDecorLayer llamadecorlayer = new LlamaDecorLayer(llamarenderer1);
        if (!Reflector.LayerLlamaDecor_model.exists()) {
            Config.warn("Field not found: LayerLlamaDecor.model");
            return null;
        }
        Reflector.LayerLlamaDecor_model.setValue(llamadecorlayer, modelBase);
        llamarenderer1.n_1700_B(llamadecorlayer);
        return llamarenderer1;
    }
}



