/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_4587
 */
package code.alphadlc.utils.display.render.font;

import code.alphadlc.utils.display.atlasfont.msdf.MsdfFont;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.MsdfFontLibrary;
import code.alphadlc.utils.display.render.font.MsdfTextRenderer;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Font;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_4587;

public class FontRenderer
implements QuickImports {
    private final String fontTypeName;
    private final float displaySize;
    private final Font font;
    private MsdfFont msdfFont;

    public FontRenderer(String fontTypeName, float displaySize) {
        this.fontTypeName = fontTypeName;
        this.displaySize = displaySize;
        this.font = new Font("SansSerif", 0, Math.max(1, Math.round(displaySize)));
    }

    private MsdfFont resolvedFont() {
        if (this.msdfFont == null) {
            this.msdfFont = MsdfFontLibrary.resolveType(this.fontTypeName);
        }
        return this.msdfFont;
    }

    public void drawText(class_4587 matrix, class_2561 text, double x, double y) {
        StringBuilder sb = new StringBuilder();
        this.findStyle(sb, text);
        this.drawString(matrix, sb.toString(), x, y, ColorAssist.getText());
    }

    public void findStyle(StringBuilder sb, class_2561 component) {
        class_2583 style = component.method_10866();
        if (component.method_10855().isEmpty()) {
            if (style.method_10973() != null) {
                sb.append(ColorAssist.formatting(style.method_10973().method_27716()));
            }
            sb.append(component.getString()).append(class_124.field_1070);
        } else {
            component.method_36136(style).forEach(text -> this.findStyle(sb, (class_2561)text));
        }
    }

    public void drawStringWithScroll(class_4587 matrix, String text, double x, double y, float width, int color) {
        String separation = "  |  ";
        float textWidth = this.getStringWidth(text + separation);
        if (textWidth - width < 10.0f) {
            this.drawString(matrix, text, x, y, color);
            return;
        }
        this.drawString(matrix, text + separation + text, x - (double)Calculate.textScrolling(textWidth), y, color);
    }

    public void drawString(class_4587 matrix, String text, double x, double y, int color) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawString(matrix, this.resolvedFont(), this.displaySize, text, x, y, color);
    }

    public void drawFlatString(class_4587 matrix, String text, double x, double y, int color) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawFlatString(matrix, this.resolvedFont(), this.displaySize, text, x, y, color);
    }

    public void drawFlatVerticallyCentered(class_4587 stack, String s, double x, double y, float height, int color) {
        this.drawFlatString(stack, s, x, this.getCenteredY((float)y, height, s), color);
    }

    public void drawStringWithSpacing(class_4587 matrix, String text, double x, double y, int color, float spacing) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawStringWithSpacing(matrix, this.resolvedFont(), this.displaySize, text, x, y, color, spacing);
    }

    public void drawGradientString(class_4587 matrix, String text, double x, double y, int colorStart, int colorEnd) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawGradientString(matrix, this.resolvedFont(), this.displaySize, text, x, y, colorStart, colorEnd);
    }

    public void drawRainbowString(class_4587 matrix, String text, double x, double y, int color1, int color2) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawRainbowString(matrix, this.resolvedFont(), this.displaySize, text, x, y, color1, color2);
    }

    public void drawHalfSplitString(class_4587 matrix, String text, double x, double y, int color1, int color2) {
        Rectangle.flushPending();
        MsdfTextRenderer.drawHalfSplitString(matrix, this.resolvedFont(), this.displaySize, text, x, y, color1, color2);
    }

    public void drawCenteredString(class_4587 stack, String s, double x, double y, int color) {
        this.drawString(stack, s, x - (double)(this.getStringWidth(s) / 2.0f), y, color);
    }

    public void drawVerticallyCentered(class_4587 stack, String s, double x, double y, float height, int color) {
        this.drawString(stack, s, x, this.getCenteredY((float)y, height, s), color);
    }

    public void drawCenteredBoth(class_4587 stack, String s, double centerX, double y, float height, int color) {
        this.drawCenteredString(stack, s, centerX, this.getCenteredY((float)y, height, s), color);
    }

    public float getCenteredY(float boxY, float boxHeight) {
        return boxY + (boxHeight - this.getStringHeight("A")) / 2.0f;
    }

    public float getCenteredY(float boxY, float boxHeight, String sample) {
        String text = sample == null || sample.isEmpty() ? "A" : sample;
        return boxY + (boxHeight - this.getStringHeight(text)) / 2.0f;
    }

    public float getStringWidth(class_2561 text) {
        return text != null ? this.getStringWidth(text.getString()) : 0.0f;
    }

    public float getStringWidth(String text) {
        return MsdfTextRenderer.getStringWidth(this.resolvedFont(), this.displaySize, text);
    }

    public float getStringHeight(class_2561 text) {
        return this.getStringHeight(text.getString());
    }

    public float getStringHeight(String text) {
        return MsdfTextRenderer.getStringHeight(this.resolvedFont(), this.displaySize, text);
    }

    public String trimToWidth(String text, int maxWidth, boolean backwards) {
        return backwards ? this.trimToWidthBackwards(text, maxWidth) : this.trimToWidth(text, maxWidth);
    }

    public String trimToWidth(String text, int maxWidth) {
        return text.substring(0, this.getTrimmedLength(text, maxWidth));
    }

    private int getTrimmedLength(String text, int maxWidth) {
        int length = 0;
        int i = 1;
        while (i <= text.length()) {
            if (this.getStringWidth(text.substring(0, i)) > (float)maxWidth) {
                return Math.max(0, i - 1);
            }
            length = i++;
        }
        return length;
    }

    public String trimToWidthBackwards(String text, int maxWidth) {
        for (int i = text.length(); i >= 0; --i) {
            if (!(this.getStringWidth(text.substring(i)) <= (float)maxWidth)) continue;
            return text.substring(i);
        }
        return "";
    }

    public String getFontTypeName() {
        return this.fontTypeName;
    }

    public float getDisplaySize() {
        return this.displaySize;
    }

    public Font getFont() {
        return this.font;
    }

    public MsdfFont getMsdfFont() {
        return this.msdfFont;
    }
}
