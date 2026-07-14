/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.BetterMinecraft;
import lightning.product.MinecraftClient;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;

public class B_2580_P {
    private static final MinecraftClient n_1700_B = MinecraftClient.A_4115_X();
    private static final long J_1907_R = 180000L;
    private static final double R_4764_Y = 0.82;
    private long G_564_y = System.currentTimeMillis();

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (B_2580_P.n_1700_B.Y_601_j == null || B_2580_P.n_1700_B.Y_259_p == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.G_564_y < 180000L) {
            return;
        }
        this.G_564_y = now;
        this.n_1700_B();
    }

    private void n_1700_B() {
        try {
            Module mod;
            ClientBootstrap pouch = ClientBootstrap.Y_601_j();
            if (pouch != null && (mod = pouch.J_1907_R().n_1700_B(BetterMinecraft.class)) instanceof BetterMinecraft) {
                BetterMinecraft bm = (BetterMinecraft)mod;
                if (mod.w_1484_f()) {
                    bm.h_1847_R();
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        this.J_1907_R();
    }

    private void J_1907_R() {
        try {
            long free;
            Runtime rt = Runtime.getRuntime();
            long max = rt.maxMemory();
            if (max <= 0L) {
                return;
            }
            long total = rt.totalMemory();
            long used = total - (free = rt.freeMemory());
            double usage = (double)used / (double)max;
            if (usage >= 0.82) {
                System.runFinalization();
                System.gc();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}


