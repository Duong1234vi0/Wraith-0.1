/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.MinecraftClient;
import lightning.product.GuardianModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.ElderGuardianRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterGuardian;

public class ModelAdapterElderGuardian
extends ModelAdapterGuardian {
    public ModelAdapterElderGuardian() {
        super(t_5_h.multiplayerClientSuggestionProvider, "elder_guardian", 0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        ElderGuardianRenderer elderguardianrenderer = new ElderGuardianRenderer(entityrenderermanager);
        elderguardianrenderer.v_4262_N = (GuardianModel)modelBase;
        elderguardianrenderer.R_4764_Y = shadowSize;
        return elderguardianrenderer;
    }
}



