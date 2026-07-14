/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.n_1658_l;
import net.optifine.player.PlayerItemModel;

public class PlayerItemRenderer {
    private int attachTo = 0;
    private e_4189_z modelRenderer = null;

    public PlayerItemRenderer(int attachTo, e_4189_z modelRenderer) {
        this.attachTo = attachTo;
        this.modelRenderer = modelRenderer;
    }

    public e_4189_z getModelRenderer() {
        return this.modelRenderer;
    }

    public void render(n_1658_l modelBiped, g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn) {
        e_4189_z modelrenderer = PlayerItemModel.getAttachModel(modelBiped, this.attachTo);
        if (modelrenderer != null) {
            modelrenderer.n_1700_B(matrixStackIn);
        }
        this.modelRenderer.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}

