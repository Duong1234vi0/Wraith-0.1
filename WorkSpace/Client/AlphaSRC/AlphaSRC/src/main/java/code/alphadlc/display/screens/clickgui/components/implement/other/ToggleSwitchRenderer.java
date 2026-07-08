/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public final class ToggleSwitchRenderer {
    public static final float WIDTH = 22.0f;
    public static final float HEIGHT = 10.0f;
    public static final float PILL_WIDTH = 22.0f;
    public static final float PILL_HEIGHT = 10.0f;
    public static final float HEADER_WIDTH = 22.0f;
    public static final float HEADER_HEIGHT = 10.0f;
    public static final float HEADER_RIGHT_INSET = 8.0f;
    public static final float RIGHT_MARGIN = 6.0f;
    public static final float ROW_HEIGHT = 21.0f;
    public static final int BIND_PILL_FONT_SIZE = 12;
    public static final float BIND_PILL_HEIGHT = 14.0f;
    public static final float BIND_PILL_MIN_WIDTH = 48.0f;
    public static final float BIND_PILL_TEXT_PADDING = 14.0f;
    private static final float KNOB_PADDING = 1.25f;
    private static final float OUTLINE_THICKNESS = 1.0f;
    private static final int OUTLINE_COLOR = -16777216;
    private static final float KNOB_LERP_SPEED = 11.0f;
    private static final Map<Object, KnobState> knobStates = new HashMap<Object, KnobState>();

    private ToggleSwitchRenderer() {
    }

    public static float trackY(float rowY, float rowHeight) {
        return rowY + (rowHeight - 10.0f) / 2.0f;
    }

    public static float headerTrackY(float rowY, float rowHeight) {
        return ToggleSwitchRenderer.trackY(rowY, rowHeight);
    }

    public static float alignedControlY(FontRenderer labelFont, String labelText, float rowY, float rowHeight, float controlHeight) {
        return rowY + (rowHeight - controlHeight) / 2.0f;
    }

    public static float toggleAlignedY(FontRenderer labelFont, String labelText, float rowY, float rowHeight) {
        return ToggleSwitchRenderer.alignedControlY(labelFont, labelText, rowY, rowHeight, 10.0f);
    }

    public static float bindPillTrackY(FontRenderer labelFont, String labelText, float rowY, float rowHeight) {
        return ToggleSwitchRenderer.alignedControlY(labelFont, labelText, rowY, rowHeight, 14.0f);
    }

    public static float bindPillWidth(String keyText) {
        float textW = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringWidth(keyText);
        return Math.max(48.0f, textW + 14.0f);
    }

    public static void drawPillTextCentered(FontRenderer font, class_4587 matrices, String text, float pillX, float pillY, float pillW, float pillH, int color) {
        font.drawCenteredBoth(matrices, text, pillX + pillW / 2.0f, pillY, pillH, color);
    }

    public static float resolveHeaderX(float headerX, float headerWidth) {
        float x = headerX + headerWidth - 22.0f - 8.0f;
        float minX = headerX + 58.0f;
        float maxX = headerX + headerWidth - 22.0f - 4.0f;
        return Math.max(minX, Math.min(x, maxX));
    }

    public static float labelDrawY(FontRenderer font, String text, float rowY, float rowHeight) {
        return font.getCenteredY(rowY, rowHeight, text);
    }

    public static void drawLabel(FontRenderer font, class_4587 matrices, String text, float labelX, float rowY, float rowHeight, int color) {
        font.drawString(matrices, text, labelX, ToggleSwitchRenderer.labelDrawY(font, text, rowY, rowHeight), color);
    }

    public static void renderDefault(class_332 context, float x, float y, boolean enabled, Object animId) {
        ToggleSwitchRenderer.renderOnOffPill(context, x, y, enabled, animId);
    }

    public static void renderHeader(class_332 context, float x, float y, boolean enabled, Object animId) {
        ToggleSwitchRenderer.renderOnOffPill(context, x, y, enabled, animId);
    }

    public static void renderOnOffPill(class_332 context, float x, float y, boolean enabled, Object animId) {
        Rectangle.flushPending();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        class_4587 matrices = context.method_51448();
        float progress = ToggleSwitchRenderer.knobProgress(animId, enabled);
        float round = 5.0f;
        int offBg = ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 220);
        int onBg = ColorAssist.getClientColor() | 0xFF000000;
        int bg = ColorAssist.interpolateColor(offBg, onBg, progress);
        QuickImports.rectangle.render(ShapeProperties.create(matrices, x, y, 22.0, 10.0).round(round).thickness(1.0f).outlineColor(-16777216).color(bg).build());
        Rectangle.flushPending();
        float knobSize = 7.5f;
        float knobTravel = 19.5f - knobSize;
        float knobX = x + 1.25f + knobTravel * progress;
        float knobY = y + 1.25f;
        QuickImports.rectangle.render(ShapeProperties.create(matrices, knobX, knobY, knobSize, knobSize).round(knobSize / 2.0f).thickness(1.0f).outlineColor(-16777216).color(-1).build());
        Rectangle.flushPending();
    }

    private static float knobProgress(Object id, boolean enabled) {
        float target = enabled ? 1.0f : 0.0f;
        KnobState state = knobStates.computeIfAbsent(id, key -> {
            KnobState created = new KnobState();
            created.progress = target;
            created.lastNanos = System.nanoTime();
            return created;
        });
        long now = System.nanoTime();
        float dt = (float)(now - state.lastNanos) / 1.0E9f;
        state.lastNanos = now;
        dt = Math.min(dt, 0.05f);
        float blend = 1.0f - (float)Math.exp(-11.0f * dt);
        state.progress += (target - state.progress) * blend;
        if (Math.abs(state.progress - target) < 0.002f) {
            state.progress = target;
        }
        return state.progress;
    }

    public static boolean isHeaderHovered(double mouseX, double mouseY, float x, float y) {
        return ToggleSwitchRenderer.isPillHovered(mouseX, mouseY, x, y);
    }

    public static boolean isDefaultHovered(double mouseX, double mouseY, float x, float y) {
        return ToggleSwitchRenderer.isPillHovered(mouseX, mouseY, x, y);
    }

    public static boolean isPillHovered(double mouseX, double mouseY, float x, float y) {
        return ToggleSwitchRenderer.isHovered(mouseX, mouseY, x, y, 22.0f, 10.0f);
    }

    public static boolean isHovered(double mouseX, double mouseY, float x, float y, float width, float height) {
        return Calculate.isHovered(mouseX, mouseY, x, y, width, height);
    }

    private static final class KnobState {
        float progress;
        long lastNanos;

        private KnobState() {
        }
    }
}
