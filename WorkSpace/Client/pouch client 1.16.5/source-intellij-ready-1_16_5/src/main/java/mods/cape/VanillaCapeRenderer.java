/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import lightning.product.D_4792_h;
import lightning.product.X_4340_E;
import lightning.product.Z_3224_L;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import mods.cape.CapeRenderer;

public class VanillaCapeRenderer
implements CapeRenderer {
    public D_4792_h vertexConsumer = null;

    @Override
    public void render(X_4340_E player, int part, e_4189_z model, g_221_o poseStack, o_3091_w multiBufferSource, int light, int overlay) {
        model.n_1700_B(poseStack, this.vertexConsumer, light, Z_3224_L.n_1700_B);
    }

    @Override
    public D_4792_h getVertexConsumer(o_3091_w multiBufferSource, X_4340_E player) {
        return multiBufferSource.getBuffer(o_2576_A.R_4764_Y(player.e_2887_G()));
    }

    @Override
    public boolean vanillaUvValues() {
        return true;
    }
}

