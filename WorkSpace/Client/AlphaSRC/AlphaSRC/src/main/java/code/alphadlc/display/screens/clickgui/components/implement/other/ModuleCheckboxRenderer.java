/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public final class ModuleCheckboxRenderer {
    public static final float SIZE = 12.0f;
    public static final float LABEL_GAP = 5.0f;

    private ModuleCheckboxRenderer() {
    }

    public static float centerY(float rowY, float rowHeight) {
        return rowY + (rowHeight - 12.0f) / 2.0f;
    }

    public static void render(class_332 context, float x, float y, boolean checked) {
        int left = Math.round(x);
        int top = Math.round(y);
        int right = Math.round(x + 12.0f);
        int bottom = Math.round(y + 12.0f);
        int fill = checked ? ColorAssist.getClientColor() | 0xFF000000 : ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), 200);
        int border = checked ? -1512204 : -7629400;
        context.method_25294(left, top, right, bottom, fill);
        context.method_25294(left, top, right, top + 1, border);
        context.method_25294(left, bottom - 1, right, bottom, border);
        context.method_25294(left, top, left + 1, bottom, border);
        context.method_25294(right - 1, top, right, bottom, border);
        if (checked) {
            class_4587 matrices = context.method_51448();
            Fonts.getSize(10, Fonts.Type.ALPHADLC_ICONS).drawString(matrices, "k", x + 1.8f, y + 2.2f, -1);
        }
    }

    public static boolean isHovered(double mouseX, double mouseY, float x, float y) {
        return Calculate.isHovered(mouseX, mouseY, x, y, 12.0, 12.0);
    }
}
