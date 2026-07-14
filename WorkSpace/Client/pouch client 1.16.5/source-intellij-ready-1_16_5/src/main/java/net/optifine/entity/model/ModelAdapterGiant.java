/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.GiantMobRenderer;
import lightning.product.S_4174_n;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterZombie;

public class ModelAdapterGiant
extends ModelAdapterZombie {
    public ModelAdapterGiant() {
        super(t_5_h.t_4043_B, "giant", 3.0f);
    }

    @Override
    public v_3569_v makeModel() {
        return new S_4174_n();
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        GiantMobRenderer giantzombierenderer = new GiantMobRenderer(entityrenderermanager, 6.0f);
        giantzombierenderer.v_4262_N = (S_4174_n)modelBase;
        giantzombierenderer.R_4764_Y = shadowSize;
        return giantzombierenderer;
    }
}



