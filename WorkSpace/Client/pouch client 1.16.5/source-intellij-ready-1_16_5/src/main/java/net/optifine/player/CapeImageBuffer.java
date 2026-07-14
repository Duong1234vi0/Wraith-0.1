/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.player;

import lightning.product.X_4340_E;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import net.optifine.player.CapeUtils;

public class CapeImageBuffer
implements Runnable {
    private X_4340_E player;
    private g_2336_b resourceLocation;
    private boolean elytraOfCape;

    public CapeImageBuffer(X_4340_E player, g_2336_b resourceLocation) {
        this.player = player;
        this.resourceLocation = resourceLocation;
    }

    @Override
    public void run() {
    }

    public i_2518_W parseUserSkin(i_2518_W imageRaw) {
        i_2518_W nativeimage = CapeUtils.parseCape(imageRaw);
        this.elytraOfCape = CapeUtils.isElytraCape(imageRaw, nativeimage);
        return nativeimage;
    }

    public void skinAvailable() {
        if (this.player != null) {
            this.player.n_1700_B(this.resourceLocation);
            this.player.G_564_y(this.elytraOfCape);
        }
        this.cleanup();
    }

    public void cleanup() {
        this.player = null;
    }

    public boolean isElytraOfCape() {
        return this.elytraOfCape;
    }
}

