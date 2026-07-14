/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Locale;
import lightning.product.D_1098_v;
import lightning.product.H_2506_c;
import lightning.product.TextColor;
import lightning.product.K_4237_u;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.f_4705_f;
import lightning.product.g_221_o;
import lightning.product.i_4833_u;
import lightning.product.s_4405_m;
import lightning.product.x_282_a;
import org.lwjgl.opengl.GL11;

public final class Z_3822_q
extends f_4705_f
implements MinecraftAccess {
    private final float w_1484_f;
    private static final int t_148_a = 1024;
    private static final String[] M_588_G = new String[]{"Segoe UI", "Arial", "Tahoma", "Dialog", "SansSerif"};
    private static final int[] s_956_w;
    private static final char[] u_2550_I;

    public float n_1700_B(g_221_o matrixStack, String text, double x, double y, int color) {
        return Z_3822_q.n_1700_B(matrixStack, this, text, x, y, color);
    }

    public float n_1700_B(g_221_o matrixStack, x_282_a component, double x, double y, int color) {
        return this.J_1907_R(matrixStack, component, x, y, color);
    }

    public void n_1700_B(g_221_o stack, String text, double x, double y, int color, boolean left, boolean right, boolean up, boolean down, int outline_color) {
        if (left) {
            Z_3822_q.n_1700_B(stack, this, text, x - 0.5, y, outline_color);
        }
        if (right) {
            Z_3822_q.n_1700_B(stack, this, text, x + 0.5, y, outline_color);
        }
        if (up) {
            Z_3822_q.n_1700_B(stack, this, text, x, y - 0.5, outline_color);
        }
        if (down) {
            Z_3822_q.n_1700_B(stack, this, text, x, y + 0.5, outline_color);
        }
        this.n_1700_B(stack, text, x, y, color);
    }

    public void n_1700_B(g_221_o matrixStack, String text, float x, float y, int color, float maxWidth) {
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 771);
        s_4405_m.M_588_G.J_1907_R();
        s_4405_m.M_588_G.J_1907_R("inColor", (float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f, (float)(color >> 24 & 0xFF) / 255.0f);
        s_4405_m.M_588_G.n_1700_B("width", maxWidth);
        s_4405_m.M_588_G.n_1700_B("maxWidth", (x + maxWidth) * 2.0f);
        this.n_1700_B(matrixStack, text, (double)x, (double)y, color);
        s_4405_m.M_588_G.R_4764_Y();
        c_4037_x.Y_259_p();
    }

    public void n_1700_B(g_221_o matrixStack, String text, float x, float y, float maxWidth, int color, boolean isHovered, n_1700_B scrollState) {
        boolean shouldScroll;
        float textWidth = this.n_1700_B(text);
        float fontHeight = this.h_1847_R();
        i_4833_u.n_1700_B(x, y - 1.0f, maxWidth, fontHeight + 3.0f);
        long currentTime = System.currentTimeMillis();
        float speed = 40.0f;
        float loopWidth = textWidth + 10.0f;
        float scrollDuration = loopWidth / speed * 1000.0f;
        float pauseDuration = 600.0f;
        float totalCycle = scrollDuration + pauseDuration;
        float scrollOffset = 0.0f;
        boolean bl = shouldScroll = textWidth > maxWidth;
        if (shouldScroll) {
            if (isHovered && !scrollState.n_1700_B) {
                scrollState.J_1907_R = currentTime;
                scrollState.n_1700_B = true;
            }
            if (scrollState.n_1700_B) {
                long elapsed = currentTime - scrollState.J_1907_R;
                if ((float)elapsed < totalCycle) {
                    scrollOffset = (float)elapsed < scrollDuration ? (float)elapsed / 1000.0f * speed : 0.0f;
                } else {
                    scrollState.n_1700_B = false;
                    scrollOffset = 0.0f;
                }
            }
        }
        this.n_1700_B(matrixStack, text, (double)(x - scrollOffset), (double)y, color);
        if (shouldScroll && scrollOffset > 0.0f) {
            this.n_1700_B(matrixStack, text, (double)(x - scrollOffset + textWidth + 10.0f), (double)y, color);
        }
        i_4833_u.n_1700_B();
    }

    private static float n_1700_B(g_221_o matrices, Z_3822_q font, String text, double x, double y, int color) {
        float startPos;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float posX = startPos = (float)x * 2.0f;
        float posY = (float)(y -= 3.0) * 2.0f;
        float[] rgb = H_2506_c.P_1922_E(color);
        float red = rgb[0];
        float green = rgb[1];
        float blue = rgb[2];
        float alpha = rgb[3];
        matrices.n_1700_B();
        matrices.n_1700_B(0.5f, 0.5f, 1.0f);
        D_1098_v matrix = matrices.R_4764_Y().n_1700_B();
        font.n_1700_B(matrix);
        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char c0 = text.charAt(i);
            posX += font.n_1700_B(matrix, c0, posX, posY, red, green, blue, alpha);
        }
        matrices.J_1907_R();
        font.G_564_y();
        return (posX - startPos) / 2.0f;
    }

    public float J_1907_R(g_221_o matrixStack, x_282_a component, double x, double y, int defaultColor) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float startPos = (float)x * 2.0f;
        float[] posX = new float[]{startPos};
        float posY = (float)(y -= 3.0) * 2.0f;
        matrixStack.n_1700_B();
        matrixStack.n_1700_B(0.5f, 0.5f, 1.0f);
        D_1098_v matrix = matrixStack.R_4764_Y().n_1700_B();
        this.n_1700_B(matrix);
        FormattedCharSequence processor = component.u_1723_Y();
        processor.accept((index, style, codePoint) -> {
            char[] chars;
            TextColor styleColor = style.n_1700_B();
            int color = defaultColor;
            if (styleColor != null) {
                int rgb = styleColor.n_1700_B();
                int alpha = defaultColor >> 24 & 0xFF;
                color = alpha << 24 | rgb & 0xFFFFFF;
            }
            float[] rgba = H_2506_c.P_1922_E(color);
            String replacement = K_4237_u.n_1700_B(codePoint);
            if (replacement != null) {
                if (!replacement.isEmpty()) {
                    for (int j = 0; j < replacement.length(); ++j) {
                        char ch = replacement.charAt(j);
                        int replacementColor = K_4237_u.n_1700_B(codePoint, j, replacement.length(), rgba[3], color);
                        float[] replacementRgba = H_2506_c.P_1922_E(replacementColor);
                        posX[0] = posX[0] + this.n_1700_B(matrix, ch, posX[0], posY, replacementRgba[0], replacementRgba[1], replacementRgba[2], replacementRgba[3]);
                    }
                }
                return true;
            }
            for (char ch : chars = Character.toChars(codePoint)) {
                posX[0] = posX[0] + this.n_1700_B(matrix, ch, posX[0], posY, rgba[0], rgba[1], rgba[2], rgba[3]);
            }
            return true;
        });
        matrixStack.J_1907_R();
        this.G_564_y();
        return (posX[0] - startPos) / 2.0f;
    }

    public Z_3822_q(String fileName, int size, float spacing, boolean antialiasing) {
        Font font = f_4705_f.n_1700_B(fileName, 0, size);
        if (font == null) {
            font = new Font("SansSerif", 0, size);
            System.err.println("Warning: Failed to load font " + fileName + ", using fallback font");
        }
        this.u_1723_Y = font.getFontName(Locale.ENGLISH);
        this.w_1484_f = spacing;
        this.v_4262_N = antialiasing;
        int textureSize = size >= 48 ? 4096 : (size >= 24 ? 2048 : 1024);
        this.R_4764_Y = textureSize;
        this.G_564_y = textureSize;
        BufferedImage image = new BufferedImage(textureSize, textureSize, 2);
        Graphics2D graphics = this.n_1700_B(image, font);
        this.P_1922_E = 0.0f;
        int x = 0;
        int y = 0;
        int padding = 2;
        boolean textFont = fileName != null && fileName.toLowerCase(Locale.ROOT).startsWith("sf_");
        for (char character : u_2550_I) {
            Font glyphFont = textFont ? Z_3822_q.n_1700_B(font, character, size) : font;
            graphics.setFont(glyphFont);
            FontMetrics fontMetrics = graphics.getFontMetrics(glyphFont);
            Rectangle2D sizeRect = fontMetrics.getStringBounds(String.valueOf(character), graphics);
            int width = sizeRect.getBounds().width;
            int height = sizeRect.getBounds().height;
            int cellW = width + 4;
            int cellH = height + 4;
            if (x + cellW >= textureSize) {
                x = 0;
                y += cellH;
            }
            if (y + cellH >= textureSize) {
                System.err.println("Warning: Font atlas is full for " + fileName + " size " + size + "; missing glyph " + (int)character);
                continue;
            }
            f_4705_f.n_1700_B glyph = new f_4705_f.n_1700_B();
            glyph.R_4764_Y = width;
            glyph.G_564_y = height;
            glyph.n_1700_B = x + 2;
            glyph.J_1907_R = y + 2;
            graphics.drawString(String.valueOf(character), x + 2, y + 2 + fontMetrics.getAscent());
            this.n_1700_B.put(Character.valueOf(character), glyph);
            this.P_1922_E = Math.max(this.P_1922_E, (float)fontMetrics.getHeight() / 2.0f);
            x += cellW;
        }
        BufferedImage finalImage = image;
        c_4037_x.n_1700_B((Runnable)() -> this.n_1700_B(finalImage));
        graphics.dispose();
    }

    private static Font n_1700_B(Font primary, char character, int size) {
        if (primary.canDisplay(character)) {
            return primary;
        }
        for (String family : M_588_G) {
            Font fallback = new Font(family, primary.getStyle(), size);
            if (fallback.canDisplay(character)) {
                return fallback;
            }
        }
        return primary;
    }

    @Override
    public float n_1700_B() {
        return this.w_1484_f;
    }

    public float n_1700_B(String text) {
        if (text == null || text.isEmpty()) {
            return 0.0f;
        }
        float width = 0.0f;
        int i = 0;
        while (i < text.length()) {
            char[] chars;
            int codePoint = text.codePointAt(i);
            i += Character.charCount(codePoint);
            String replacement = K_4237_u.n_1700_B(codePoint);
            if (replacement != null) {
                if (replacement.isEmpty()) continue;
                for (int j = 0; j < replacement.length(); ++j) {
                    char ch = replacement.charAt(j);
                    width += this.n_1700_B(ch) + this.n_1700_B();
                }
                continue;
            }
            for (char ch : chars = Character.toChars(codePoint)) {
                width += this.n_1700_B(ch) + this.n_1700_B();
            }
        }
        return (float)Math.round(width - this.n_1700_B()) / 2.0f;
    }

    public float h_1847_R() {
        return (float)Math.round(this.t_148_a()) / 2.0f;
    }

    static {
        int i;
        s_956_w = new int[]{31, 256, 1024, 1106, 9728, 9984, 9632, 9728};
        int len = s_956_w[1] - s_956_w[0] + (s_956_w[3] - s_956_w[2]) + (s_956_w[5] - s_956_w[4]) + (s_956_w[7] - s_956_w[6]);
        u_2550_I = new char[len];
        int idx = 0;
        for (i = s_956_w[0]; i < s_956_w[1]; ++i) {
            Z_3822_q.u_2550_I[idx++] = (char)i;
        }
        for (i = s_956_w[2]; i < s_956_w[3]; ++i) {
            Z_3822_q.u_2550_I[idx++] = (char)i;
        }
        for (i = s_956_w[4]; i < s_956_w[5]; ++i) {
            Z_3822_q.u_2550_I[idx++] = (char)i;
        }
        for (i = s_956_w[6]; i < s_956_w[7]; ++i) {
            Z_3822_q.u_2550_I[idx++] = (char)i;
        }
    }

    public static class n_1700_B {
        public boolean n_1700_B = false;
        public long J_1907_R = 0L;
    }
}



