/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.features.impl.render.ClickGui;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.bind.BindCodes;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class BindPickerWindow
extends AbstractWindow {
    private final Object target;
    private boolean listening = true;

    public BindPickerWindow(BindSetting setting) {
        this.target = setting;
    }

    public BindPickerWindow(Module module) {
        this.target = module;
    }

    public String getTitle() {
        Object object = this.target;
        if (object instanceof BindSetting) {
            BindSetting setting = (BindSetting)object;
            return setting.getName();
        }
        object = this.target;
        if (object instanceof Module) {
            Module module = (Module)object;
            return module.getVisibleName();
        }
        return "\u0411\u0438\u043d\u0434";
    }

    private int getBindKey() {
        Object object = this.target;
        if (object instanceof BindSetting) {
            BindSetting setting = (BindSetting)object;
            return setting.getKey();
        }
        object = this.target;
        if (object instanceof Module) {
            Module module = (Module)object;
            return module.getKey();
        }
        return -1;
    }

    private void applyBind(int key) {
        Object object = this.target;
        if (object instanceof BindSetting) {
            BindSetting setting = (BindSetting)object;
            setting.setKey(key);
            setting.clearSecondKey();
        } else {
            object = this.target;
            if (object instanceof Module) {
                Module module = (Module)object;
                module.setKey(key);
                module.setSecondKey(-1);
            }
        }
        this.listening = false;
    }

    private void clearBind() {
        this.applyBind(-1);
        this.startCloseAnimation();
    }

    @Override
    protected void drawWindow(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        ClickGui clickGui = ClickGui.getInstance();
        if (ThemeManager.isLiquidGlassTheme() || clickGui != null && clickGui.shouldRenderBackgroundBlur()) {
            Render2D.rectangleWithMask(matrices.method_23760().method_23761(), this.x, this.y, this.width, this.height, 8.0f, 0, KawaseBlur.INSTANCE.getBlurTexture());
        }
        rectangle.render(ShapeProperties.create(matrices, this.x, this.y, this.width, this.height).round(8.0f).thickness(1.5f).outlineColor(ColorAssist.applyOpacity(ColorAssist.getOutline(), 180)).color(ThemeManager.BackgroundGui.getColor()).build());
        rectangle.render(ShapeProperties.create(matrices, this.x, this.y, this.width, 2.0).round(2.0f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), 200)).build());
        Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, this.getTitle(), this.x + 10.0f, this.y + 11.0f, ThemeManager.textColor.getColor());
        float closeSize = 10.0f;
        float closeX = this.x + this.width - closeSize - 8.0f;
        float closeY = this.y + 8.0f;
        boolean closeHovered = Calculate.isHovered(mouseX, mouseY, closeX - 2.0f, closeY - 2.0f, closeSize + 4.0f, closeSize + 4.0f);
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawCenteredString(matrices, "\u00d7", closeX + closeSize / 2.0f, closeY + 1.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), closeHovered ? 255 : 150));
        String pillText = this.listening ? "\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u043a\u043b\u0430\u0432\u0438\u0448\u0443..." : BindCodes.getDisplayName(this.getBindKey());
        int pillFontSize = this.listening ? 11 : 12;
        float pillTextWidth = Fonts.getSize(pillFontSize, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringWidth(pillText);
        float pillW = Math.max(118.0f, pillTextWidth + 22.0f);
        float pillH = 22.0f;
        float pillX = this.x + (this.width - pillW) / 2.0f;
        float pillY = this.y + 34.0f;
        long pulse = System.currentTimeMillis();
        int pulseAlpha = this.listening ? (int)(120.0 + 80.0 * Math.abs(Math.sin((double)pulse / 220.0))) : 90;
        int pillOutline = this.listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha + 60) : ColorAssist.applyOpacity(ColorAssist.getOutline(), 160);
        int pillFill = this.listening ? ColorAssist.applyOpacity(ColorAssist.getClientColor(), pulseAlpha) : ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), 130);
        rectangle.render(ShapeProperties.create(matrices, pillX, pillY, pillW, pillH).round(6.0f).thickness(this.listening ? 1.2f : 0.8f).outlineColor(pillOutline).color(pillFill).build());
        Fonts.getSize(pillFontSize, Fonts.Type.ROCKSTAR_SEMIBOLD).drawCenteredString(matrices, pillText, pillX + pillW / 2.0f, pillY + 8.5f, this.listening ? ColorAssist.getClientColor() : ThemeManager.textColor.getColor());
        String hint = this.listening ? "\u041a\u043b\u0430\u0432\u0438\u0430\u0442\u0443\u0440\u0430, \u043c\u044b\u0448\u044c, \u043a\u043e\u043b\u0451\u0441\u0438\u043a\u043e \u2022 Esc \u2014 \u0441\u0431\u0440\u043e\u0441\u0438\u0442\u044c" : "Esc \u2014 \u0441\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u2022 \u00d7 \u2014 \u0437\u0430\u043a\u0440\u044b\u0442\u044c";
        float hintW = Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).getStringWidth(hint);
        Fonts.getSize(10, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, hint, this.x + (this.width - hintW) / 2.0f, this.y + this.height - 14.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 145));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        float closeSize = 10.0f;
        float closeX = this.x + this.width - closeSize - 8.0f;
        float closeY = this.y + 8.0f;
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, closeX - 2.0f, closeY - 2.0f, closeSize + 4.0f, closeSize + 4.0f)) {
            this.startCloseAnimation();
            return true;
        }
        float pillW = 118.0f;
        float pillH = 22.0f;
        float pillX = this.x + (this.width - pillW) / 2.0f;
        float pillY = this.y + 34.0f;
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, pillX, pillY, pillW, pillH)) {
            this.listening = true;
            return true;
        }
        if (this.listening && BindCodes.canBind(button)) {
            this.applyBind(button);
            return true;
        }
        this.draggable(Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, 18.0));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.listening && amount != 0.0) {
            this.applyBind(amount > 0.0 ? 998 : 999);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            if (this.listening) {
                this.clearBind();
            } else {
                this.startCloseAnimation();
            }
            return true;
        }
        if (this.listening && BindCodes.canBind(keyCode)) {
            this.applyBind(keyCode);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public Object getTarget() {
        return this.target;
    }
}
