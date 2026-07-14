/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.RenderBuffers;
import lightning.product.MinecraftClient;

public class RenderUtils {
    private static boolean flushRenderBuffers = true;
    private static MinecraftClient mc = MinecraftClient.A_4115_X();

    public static boolean setFlushRenderBuffers(boolean flushRenderBuffers) {
        boolean flag = RenderUtils.flushRenderBuffers;
        RenderUtils.flushRenderBuffers = flushRenderBuffers;
        return flag;
    }

    public static boolean isFlushRenderBuffers() {
        return flushRenderBuffers;
    }

    public static void flushRenderBuffers() {
        if (flushRenderBuffers) {
            RenderBuffers rendertypebuffers = mc.j_1564_a();
            rendertypebuffers.J_1907_R().n_1700_B();
            rendertypebuffers.R_4764_Y().n_1700_B();
        }
    }
}



