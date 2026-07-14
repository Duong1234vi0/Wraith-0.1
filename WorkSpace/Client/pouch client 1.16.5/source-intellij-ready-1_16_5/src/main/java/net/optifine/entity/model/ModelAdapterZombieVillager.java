/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ZombieVillagerModel;
import lightning.product.ReloadableResourceManager;
import lightning.product.ZombieVillagerRenderer;
import lightning.product.MinecraftClient;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterZombieVillager
extends ModelAdapterBiped {
    public ModelAdapterZombieVillager() {
        super(t_5_h.M_2677_i, "zombie_villager", 0.5f);
    }

    @Override
    public v_3569_v makeModel() {
        return new ZombieVillagerModel(0.0f, false);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        ReloadableResourceManager ireloadableresourcemanager = (ReloadableResourceManager)MinecraftClient.A_4115_X().T_2506_i();
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ZombieVillagerRenderer zombievillagerrenderer = new ZombieVillagerRenderer(entityrenderermanager, ireloadableresourcemanager);
        zombievillagerrenderer.v_4262_N = (ZombieVillagerModel)modelBase;
        zombievillagerrenderer.R_4764_Y = shadowSize;
        return zombievillagerrenderer;
    }
}



