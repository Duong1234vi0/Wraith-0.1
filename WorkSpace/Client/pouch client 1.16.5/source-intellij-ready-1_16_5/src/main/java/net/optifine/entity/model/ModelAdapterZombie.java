/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.G_2271_Y;
import lightning.product.MinecraftClient;
import lightning.product.o_4662_o;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterZombie
extends ModelAdapterBiped {
    public ModelAdapterZombie() {
        super(t_5_h.R_3077_Z, "zombie", 0.5f);
    }

    protected ModelAdapterZombie(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new o_4662_o(0.0f, false);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        G_2271_Y zombierenderer = new G_2271_Y(entityrenderermanager);
        zombierenderer.v_4262_N = (o_4662_o)modelBase;
        zombierenderer.R_4764_Y = shadowSize;
        return zombierenderer;
    }
}


