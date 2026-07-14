/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.Iterator;
import java.util.List;
import lightning.product.RenderLayer;
import lightning.product.U_1504_z;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.g_3275_w;
import lightning.product.t_5_h;
import lightning.product.v_1296_A;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.SheepRenderer;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterSheepWool
extends ModelAdapterQuadruped {
    public ModelAdapterSheepWool() {
        super(t_5_h.k_3961_g, "sheep_wool", 0.7f);
    }

    @Override
    public v_3569_v makeModel() {
        return new g_3275_w();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Z_2049_e entityrenderer = entityrenderermanager.P_1922_E().get(t_5_h.k_3961_g);
        if (!(entityrenderer instanceof SheepRenderer)) {
            Config.warn("Not a RenderSheep: " + String.valueOf(entityrenderer));
            return null;
        }
        if (entityrenderer.getType() == null) {
            SheepRenderer sheeprenderer = new SheepRenderer(entityrenderermanager);
            sheeprenderer.v_4262_N = new v_1296_A();
            sheeprenderer.R_4764_Y = 0.7f;
            entityrenderer = sheeprenderer;
        }
        SheepRenderer sheeprenderer1 = (SheepRenderer)entityrenderer;
        List list = sheeprenderer1.P_1922_E();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            RenderLayer layerrenderer = iterator.next();
            if (!(layerrenderer instanceof U_1504_z)) continue;
            iterator.remove();
        }
        U_1504_z sheepwoollayer = new U_1504_z(sheeprenderer1);
        sheepwoollayer.n_1700_B = (g_3275_w)modelBase;
        sheeprenderer1.n_1700_B(sheepwoollayer);
        return sheeprenderer1;
    }
}



