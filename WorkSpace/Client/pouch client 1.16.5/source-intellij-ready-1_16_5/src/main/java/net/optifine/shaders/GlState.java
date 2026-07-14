/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import lightning.product.X_933_l;
import net.optifine.shaders.DrawBuffers;
import net.optifine.shaders.ShadersFramebuffer;

public class GlState {
    private static ShadersFramebuffer activeFramebuffer;

    public static void bindFramebuffer(ShadersFramebuffer framebufferIn) {
        activeFramebuffer = framebufferIn;
        X_933_l.w_1484_f(36160, activeFramebuffer.getGlFramebuffer());
    }

    public static ShadersFramebuffer getFramebuffer() {
        return activeFramebuffer;
    }

    public static void setFramebufferTexture2D(int target, int attachment, int texTarget, int texture, int level) {
        activeFramebuffer.setFramebufferTexture2D(target, attachment, texTarget, texture, level);
    }

    public static void setDrawBuffers(DrawBuffers drawBuffers) {
        activeFramebuffer.setDrawBuffers(drawBuffers);
    }

    public static DrawBuffers getDrawBuffers() {
        return activeFramebuffer.getDrawBuffers();
    }
}

