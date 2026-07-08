/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.other.ToggleSwitchRenderer;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.bind.BindCaptureHelper;
import code.alphadlc.utils.client.bind.BindCodes;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class BindComponent
extends AbstractSettingComponent {
    private final BindSetting setting;

    public BindComponent(BindSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 21.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        ToggleSwitchRenderer.drawLabel(labelFont, matrices, this.setting.getName(), this.x + 6.0f, this.y, this.height, ThemeManager.textColor.getColor());
        this.renderBindPill(context, mouseX, mouseY, labelFont);
    }

    private void renderBindPill(class_332 context, int mouseX, int mouseY, FontRenderer labelFont) {
        int outlineColor;
        int pulseAlpha;
        class_4587 matrices = context.method_51448();
        boolean listening = BindCaptureHelper.isCapturing(this.setting);
        String keyText = listening ? "..." : BindCodes.getDisplayName(this.setting.getKey());
        FontRenderer pillFont = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD);
        float pillW = ToggleSwitchRenderer.bindPillWidth(keyText);
        float pillH = 14.0f;
        float pillX = this.x + this.width - 6.0f - pillW;
        float pillY = ToggleSwitchRenderer.bindPillTrackY(labelFont, this.setting.getName(), this.y, this.height);
        boolean hovered = Calculate.isHovered(mouseX, mouseY, pillX, pillY, pillW, pillH);
        long pulse = System.currentTimeMillis();
        int n = pulseAlpha = listening ? (int)(120.0 + 80.0 * Math.abs(Math.sin((double)pulse / 220.0))) : 0;
        int n2 = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha + 80) : (outlineColor = ColorAssist.applyOpacity(hovered ? ColorAssist.getClientColor() : ColorAssist.getOutline(), hovered ? 200 : 130));
        int fillColor = listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha) : ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), hovered ? 165 : 120);
        rectangle.render(ShapeProperties.create(matrices, pillX, pillY, pillW, pillH).round(5.0f).thickness(listening ? 1.2f : (hovered ? 1.0f : 0.7f)).outlineColor(outlineColor).color(fillColor).build());
        ToggleSwitchRenderer.drawPillTextCentered(pillFont, matrices, keyText, pillX, pillY, pillW, pillH, listening ? ColorAssist.getClientColor() : ThemeManager.textColor.getColor());
    }

    private float[] getPillBounds() {
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        boolean listening = BindCaptureHelper.isCapturing(this.setting);
        String keyText = listening ? "..." : BindCodes.getDisplayName(this.setting.getKey());
        float pillW = ToggleSwitchRenderer.bindPillWidth(keyText);
        float pillH = 14.0f;
        float pillX = this.x + this.width - 6.0f - pillW;
        float pillY = ToggleSwitchRenderer.bindPillTrackY(labelFont, this.setting.getName(), this.y, this.height);
        return new float[]{pillX, pillY, pillW, pillH};
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        float[] pill = this.getPillBounds();
        float pillX = pill[0];
        float pillY = pill[1];
        float pillW = pill[2];
        float pillH = pill[3];
        if (BindCaptureHelper.isCapturing(this.setting)) {
            if (button == 0 && Calculate.isHovered(mouseX, mouseY, pillX, pillY, pillW, pillH)) {
                BindCaptureHelper.cancel();
                return true;
            }
            return BindCaptureHelper.handleMouse(button);
        }
        if (button != 0) {
            return false;
        }
        if (Calculate.isHovered(mouseX, mouseY, pillX, pillY, pillW, pillH) || Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            BindCaptureHelper.toggle(this.setting);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (BindCaptureHelper.isCapturing(this.setting)) {
            return BindCaptureHelper.handleKey(keyCode, scanCode);
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (BindCaptureHelper.isCapturing(this.setting)) {
            return BindCaptureHelper.handleScroll(amount);
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
