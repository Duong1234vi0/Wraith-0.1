/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 */
package code.alphadlc.utils.display.render;

import code.alphadlc.utils.display.render.BackgroundIcons;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_332;

public final class StartupBackgroundRenderer {
    public static final class_2960 BACKGROUND = BackgroundIcons.BACKGROUND;
    private static final int BG_TEX_WIDTH = 1672;
    private static final int BG_TEX_HEIGHT = 941;

    private StartupBackgroundRenderer() {
    }

    public static void drawStartupBackdrop(class_332 context, int width, int height) {
        StartupBackgroundRenderer.drawBackground(context, width, height);
    }

    public static void drawBackground(class_332 context, int width, int height) {
        StartupBackgroundRenderer.drawTexture(context, BACKGROUND, 0, 0, width, height, 1672, 941);
    }

    public static void drawBackground(class_332 context, float x, float y, float renderWidth, float renderHeight, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)Math.max(0.0f, Math.min(1.0f, alpha)));
        context.method_25290(class_1921::method_62277, BACKGROUND, (int)x, (int)y, 0.0f, 0.0f, (int)renderWidth, (int)renderHeight, 1672, 941);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
    }

    private static void drawTexture(class_332 context, class_2960 texture, int x, int y, int width, int height, int textureWidth, int textureHeight) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        context.method_25290(class_1921::method_62277, texture, x, y, 0.0f, 0.0f, width, height, textureWidth, textureHeight);
        RenderSystem.disableBlend();
    }
}
