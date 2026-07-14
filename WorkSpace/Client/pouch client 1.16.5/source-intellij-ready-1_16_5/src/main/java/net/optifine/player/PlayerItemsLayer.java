/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import java.util.Map;
import java.util.Set;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.X_4340_E;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.h_4311_S;
import lightning.product.n_1658_l;
import lightning.product.o_3091_w;
import net.optifine.Config;
import net.optifine.player.PlayerConfigurations;

public class PlayerItemsLayer
extends RenderLayer {
    private h_4311_S renderPlayer = null;

    public PlayerItemsLayer(h_4311_S renderPlayer) {
        super(renderPlayer);
        this.renderPlayer = renderPlayer;
    }

    public void render(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, N_4263_v entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderEquippedItems(entitylivingbaseIn, matrixStackIn, bufferIn, packedLightIn, Z_3224_L.n_1700_B);
    }

    protected void renderEquippedItems(N_4263_v entityLiving, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        if (!entityLiving.F_3572_x() && entityLiving instanceof X_4340_E) {
            X_4340_E abstractclientplayerentity = (X_4340_E)entityLiving;
            n_1658_l bipedmodel = (n_1658_l)this.renderPlayer.n_1700_B();
            if (Config.isShowCapes()) {
                PlayerConfigurations.renderPlayerItems(bipedmodel, abstractclientplayerentity, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            }
            PlayerConfigurations.renderHatItem(bipedmodel, abstractclientplayerentity, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    public static void register(Map renderPlayerMap) {
        Set set = renderPlayerMap.keySet();
        boolean flag = false;
        for (Object object : set) {
            Object object1 = renderPlayerMap.get(object);
            if (!(object1 instanceof h_4311_S)) continue;
            h_4311_S playerrenderer = (h_4311_S)object1;
            playerrenderer.n_1700_B(new PlayerItemsLayer(playerrenderer));
            flag = true;
        }
        if (!flag) {
            Config.warn("PlayerItemsLayer not registered");
        }
    }
}


