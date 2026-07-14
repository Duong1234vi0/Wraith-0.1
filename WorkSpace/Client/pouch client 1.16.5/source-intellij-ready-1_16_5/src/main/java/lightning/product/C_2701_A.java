/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiConsumer;
import lightning.product.B_3871_I;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.X_933_l;
import lightning.product.Y_4083_F;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.x_282_a;

public abstract class C_2701_A {
    public static final g_2336_b BACKGROUND_LOCATION = new g_2336_b("textures/gui/options_background.png");
    public static final g_2336_b STATS_ICON_LOCATION = new g_2336_b("textures/gui/container/stats_icons.png");
    public static final g_2336_b GUI_ICONS_LOCATION = new g_2336_b("textures/gui/icons.png");
    private static int blitOffset;

    protected void hLine(g_221_o matrixStack, int minX, int maxX, int y, int color) {
        if (maxX < minX) {
            int i = minX;
            minX = maxX;
            maxX = i;
        }
        C_2701_A.fill(matrixStack, minX, y, maxX + 1, y + 1, color);
    }

    protected void vLine(g_221_o matrixStack, int x, int minY, int maxY, int color) {
        if (maxY < minY) {
            int i = minY;
            minY = maxY;
            maxY = i;
        }
        C_2701_A.fill(matrixStack, x, minY + 1, x + 1, maxY, color);
    }

    public static void fill(g_221_o matrixStack, int minX, int minY, int maxX, int maxY, int color) {
        C_2701_A.fill(matrixStack.R_4764_Y().n_1700_B(), minX, minY, maxX, maxY, color);
    }

    private static void fill(D_1098_v matrix, int minX, int minY, int maxX, int maxY, int color) {
        if (minX < maxX) {
            int i = minX;
            minX = maxX;
            maxX = i;
        }
        if (minY < maxY) {
            int j = minY;
            minY = maxY;
            maxY = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        c_4037_x.Y_601_j();
        c_4037_x.e_4240_b();
        c_4037_x.s_2632_s();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        bufferbuilder.n_1700_B(matrix, (float)minX, (float)maxY, 0.0f).n_1700_B(f, f1, f2, f3).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)maxX, (float)maxY, 0.0f).n_1700_B(f, f1, f2, f3).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)maxX, (float)minY, 0.0f).n_1700_B(f, f1, f2, f3).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)minX, (float)minY, 0.0f).n_1700_B(f, f1, f2, f3).endVertex();
        bufferbuilder.u_1723_Y();
        o_2840_r.n_1700_B(bufferbuilder);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }

    public static void fillGradient(g_221_o matrixStack, int x1, int y1, int x2, int y2, int colorFrom, int colorTo) {
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.u_2550_I();
        c_4037_x.s_2632_s();
        c_4037_x.w_1484_f(7425);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        C_2701_A.fillGradient(matrixStack.R_4764_Y().n_1700_B(), bufferbuilder, x1, y1, x2, y2, blitOffset, colorFrom, colorTo);
        tessellator.J_1907_R();
        c_4037_x.w_1484_f(7424);
        c_4037_x.Y_259_p();
        c_4037_x.M_588_G();
        c_4037_x.x_607_J();
    }

    protected static void fillGradient(D_1098_v matrix, D_3318_r builder, int x1, int y1, int x2, int y2, int z, int colorA, int colorB) {
        float f = (float)(colorA >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(colorA >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(colorA >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(colorA & 0xFF) / 255.0f;
        float f4 = (float)(colorB >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(colorB >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(colorB >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(colorB & 0xFF) / 255.0f;
        builder.n_1700_B(matrix, (float)x2, (float)y1, (float)z).n_1700_B(f1, f2, f3, f).endVertex();
        builder.n_1700_B(matrix, (float)x1, (float)y1, (float)z).n_1700_B(f1, f2, f3, f).endVertex();
        builder.n_1700_B(matrix, (float)x1, (float)y2, (float)z).n_1700_B(f5, f6, f7, f4).endVertex();
        builder.n_1700_B(matrix, (float)x2, (float)y2, (float)z).n_1700_B(f5, f6, f7, f4).endVertex();
    }

    public static void drawCenteredString(g_221_o matrixStack, Y_4083_F fontRenderer, String font, int text, int x, int y) {
        fontRenderer.n_1700_B(matrixStack, font, (float)(text - fontRenderer.J_1907_R(font) / 2), (float)x, y);
    }

    public static void drawCenteredString(g_221_o matrixStack, Y_4083_F fontRenderer, x_282_a font, int text, int x, int y) {
        FormattedCharSequence ireorderingprocessor = font.u_1723_Y();
        fontRenderer.n_1700_B(matrixStack, ireorderingprocessor, (float)(text - fontRenderer.n_1700_B(ireorderingprocessor) / 2), (float)x, y);
    }

    public static void drawString(g_221_o matrixStack, Y_4083_F fontRenderer, String font, int text, int x, int y) {
        fontRenderer.n_1700_B(matrixStack, font, (float)text, (float)x, y);
    }

    public static void drawString(g_221_o matrixStack, Y_4083_F fontRenderer, x_282_a font, int text, int x, int y) {
        fontRenderer.n_1700_B(matrixStack, font, (float)text, (float)x, y);
    }

    public void blitBlackOutline(int width, int height, BiConsumer<Integer, Integer> boxXYConsumer) {
        c_4037_x.n_1700_B(X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        boxXYConsumer.accept(width + 1, height);
        boxXYConsumer.accept(width - 1, height);
        boxXYConsumer.accept(width, height + 1);
        boxXYConsumer.accept(width, height - 1);
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        boxXYConsumer.accept(width, height);
    }

    public static void blit(g_221_o matrixStack, int x, int y, int blitOffset, int width, int height, B_3871_I sprite) {
        C_2701_A.innerBlit(matrixStack.R_4764_Y().n_1700_B(), x, x + width, y, y + height, blitOffset, sprite.u_1723_Y(), sprite.v_4262_N(), sprite.w_1484_f(), sprite.t_148_a());
    }

    public void blit(g_221_o matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        C_2701_A.blit(matrixStack, x, y, blitOffset, uOffset, vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(g_221_o matrixStack, int x, int y, int blitOffset, int uOffset, int vOffset, int uWidth, int vHeight) {
        C_2701_A.blit(matrixStack, x, y, blitOffset, uOffset, vOffset, uWidth, vHeight, 256, 256);
    }

    public static void blit(g_221_o matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
        C_2701_A.innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(g_221_o matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        C_2701_A.innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(g_221_o matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        C_2701_A.blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(g_221_o matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
        C_2701_A.innerBlit(matrixStack.R_4764_Y().n_1700_B(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0f) / (float)textureWidth, (uOffset + (float)uWidth) / (float)textureWidth, (vOffset + 0.0f) / (float)textureHeight, (vOffset + (float)vHeight) / (float)textureHeight);
    }

    private static void innerBlit(D_1098_v matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
        bufferbuilder.n_1700_B(matrix, (float)x1, (float)y2, (float)blitOffset).tex(minU, maxV).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)x2, (float)y2, (float)blitOffset).tex(maxU, maxV).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)x2, (float)y1, (float)blitOffset).tex(maxU, minV).endVertex();
        bufferbuilder.n_1700_B(matrix, (float)x1, (float)y1, (float)blitOffset).tex(minU, minV).endVertex();
        bufferbuilder.u_1723_Y();
        c_4037_x.M_588_G();
        o_2840_r.n_1700_B(bufferbuilder);
    }

    public int getBlitOffset() {
        return blitOffset;
    }

    public void setBlitOffset(int value) {
        blitOffset = value;
    }
}


