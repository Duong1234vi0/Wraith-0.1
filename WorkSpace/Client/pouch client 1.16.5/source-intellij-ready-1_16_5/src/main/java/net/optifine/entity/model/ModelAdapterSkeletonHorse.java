/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.HorseModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.UndeadHorseRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterHorse;

public class ModelAdapterSkeletonHorse
extends ModelAdapterHorse {
    public ModelAdapterSkeletonHorse() {
        super(t_5_h.PlayerInfo, "skeleton_horse", 0.75f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        UndeadHorseRenderer undeadhorserenderer = new UndeadHorseRenderer(entityrenderermanager);
        undeadhorserenderer.v_4262_N = (HorseModel)modelBase;
        undeadhorserenderer.R_4764_Y = shadowSize;
        return undeadhorserenderer;
    }
}



