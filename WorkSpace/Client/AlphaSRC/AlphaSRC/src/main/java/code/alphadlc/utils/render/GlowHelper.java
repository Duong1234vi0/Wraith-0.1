/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.utils.render;

import code.alphadlc.utils.render.GlowFilter;
import com.mojang.blaze3d.platform.GlStateManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.lwjgl.opengl.GL11;

public class GlowHelper {
    public static final HashMap<Integer, Integer> glowCache = new HashMap();

    public static void drawGlow(double x, double y, int width, int height, int glowRadius, Color color) {
        GlStateManager._enableBlend();
        GlStateManager._blendFunc((int)770, (int)771);
        GL11.glEnable((int)3008);
        GL11.glAlphaFunc((int)516, (float)1.0E-4f);
        GlStateManager._bindTexture((int)GlowHelper.getGlowTexture(width, height, glowRadius));
        width += glowRadius * 2;
        GlowHelper.applyColor(color);
        GL11.glBegin((int)7);
        GL11.glTexCoord2d((double)0.0, (double)1.0);
        GL11.glVertex2d((double)(x -= (double)glowRadius), (double)(y -= (double)((height += glowRadius * 2) - glowRadius)));
        GL11.glTexCoord2d((double)0.0, (double)0.0);
        GL11.glVertex2d((double)x, (double)(y + (double)height));
        GL11.glTexCoord2d((double)1.0, (double)0.0);
        GL11.glVertex2d((double)(x + (double)width), (double)(y + (double)height));
        GL11.glTexCoord2d((double)1.0, (double)1.0);
        GL11.glVertex2d((double)(x + (double)width), (double)y);
        GL11.glEnd();
        GlStateManager._bindTexture((int)0);
        GL11.glDisable((int)3008);
        GlStateManager._disableBlend();
    }

    public static int getGlowTexture(int width, int height, int blurRadius) {
        int identifier = (width * 401 + height) * 407 + blurRadius;
        int texId = glowCache.getOrDefault(identifier, -1);
        if (texId == -1) {
            BufferedImage original = new BufferedImage(width + blurRadius * 2, height + blurRadius * 2, 3);
            Graphics g = original.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(blurRadius, blurRadius, width, height);
            g.dispose();
            GlowFilter glow = new GlowFilter(blurRadius);
            BufferedImage blurred = glow.filter(original, null);
            try {
                texId = GlowHelper.loadTexture(blurred);
                glowCache.put(identifier, texId);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return texId;
    }

    public static void applyColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    private static int loadTexture(BufferedImage image) {
        return (int)(System.currentTimeMillis() % 10000L);
    }
}
