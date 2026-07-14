/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import lightning.product.D_4792_h;
import lightning.product.X_4340_E;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_3091_w;

public interface CapeRenderer {
    public void render(X_4340_E var1, int var2, e_4189_z var3, g_221_o var4, o_3091_w var5, int var6, int var7);

    default public D_4792_h getVertexConsumer(o_3091_w multiBufferSource, X_4340_E player) {
        return null;
    }

    public boolean vanillaUvValues();
}

