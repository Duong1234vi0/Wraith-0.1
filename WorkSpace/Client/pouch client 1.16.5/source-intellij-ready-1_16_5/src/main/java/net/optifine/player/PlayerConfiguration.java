/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import lightning.product.X_4340_E;
import lightning.product.g_221_o;
import lightning.product.n_1658_l;
import lightning.product.o_3091_w;
import net.optifine.Config;
import net.optifine.player.PlayerItemModel;

public class PlayerConfiguration {
    private PlayerItemModel[] playerItemModels = new PlayerItemModel[0];
    private boolean initialized = false;

    public void renderPlayerItems(n_1658_l modelBiped, X_4340_E player, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        if (this.initialized) {
            for (int i = 0; i < this.playerItemModels.length; ++i) {
                PlayerItemModel playeritemmodel = this.playerItemModels[i];
                playeritemmodel.render(modelBiped, player, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            }
        }
    }

    public void renderPlayerItemsForced(n_1658_l modelBiped, X_4340_E player, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        for (int i = 0; i < this.playerItemModels.length; ++i) {
            PlayerItemModel playeritemmodel = this.playerItemModels[i];
            playeritemmodel.render(modelBiped, player, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public PlayerItemModel[] getPlayerItemModels() {
        return this.playerItemModels;
    }

    public void addPlayerItemModel(PlayerItemModel playerItemModel) {
        this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray(this.playerItemModels, playerItemModel);
    }
}

