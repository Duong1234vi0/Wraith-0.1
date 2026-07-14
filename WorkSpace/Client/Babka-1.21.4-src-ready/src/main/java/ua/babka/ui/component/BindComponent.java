package ua.babka.ui.component;

import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.BindSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;
import ua.babka.util.KeyStorage;

public class BindComponent
extends Component {
    private final BindSetting setting;
    private boolean binding;
    private static final float SETTING_HEIGHT = 14.0f;
    private static final float BIND_H = 10.0f;
    private static final float PADDING = 4.5f;
    private static final float RADIUS = 1.0f;

    public BindComponent(BindSetting setting) {
        this.setting = setting;
    }

    private String getKeyText() {
        if (this.binding) {
            return "...";
        }
        return this.setting.getValue() == -1 ? "-" : KeyStorage.getKey(this.setting.getValue());
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        boolean hasBind;
        float bindY;
        float alpha = Math.max(Math.min(this.getAlphaAnimSetting().getValue(), 1.0f), 0.0f);
        int alphaInt = (int)(255.0f * alpha);
        if (alpha < 0.02f) {
            return;
        }
        String keyText = this.getKeyText();
        float keyTextW = Fonts.SFREGULAR.get().getWidth(keyText, 6.5f);
        float bindBoxW = Math.max(16.0f, keyTextW + 8.0f);
        float bindX = this.x + this.width - bindBoxW - 4.5f;
        if (HoverUtil.isHovered(mouseX, mouseY, bindX, bindY = this.y + 2.0f, bindBoxW, 10.0)) {
            CursorManager.requestHand();
        }
        DrawUtil.drawText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 3.5f, ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt), 6.5f);
        boolean bl = hasBind = this.setting.getValue() != -1;
        int bgColor = this.binding ? ColorProvider.setAlpha(ColorProvider.getColorButton(), (int)(35.0f * alpha)) : (hasBind ? ColorProvider.setAlpha(ColorProvider.getColorButton(), (int)(80.0f * alpha)) : ColorProvider.setAlpha(ColorProvider.getColorInactiveButton(), (int)(80.0f * alpha)));
        DrawUtil.drawRound(bindX, bindY, bindBoxW, 10.0f, 1.0f, bgColor);
        int textColor = this.binding || hasBind ? ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt) : ColorProvider.setAlpha(ColorProvider.getColorInactiveText(), alphaInt);
        float keyX = bindX + (bindBoxW - keyTextW) / 2.0f;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), keyText, keyX, bindY + 1.25f, textColor, 6.5f);
        this.setHeight(14.0f);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        String keyText = this.getKeyText();
        float keyTextW = Fonts.SFREGULAR.get().getWidth(keyText, 6.5f);
        float bindBoxW = Math.max(16.0f, keyTextW + 8.0f);
        float bindX = this.x + this.width - bindBoxW - 4.5f;
        float bindY = this.y + 2.0f;
        if (this.binding) {
            if (button != 0) {
                this.setting.setValue(button);
            }
            this.binding = false;
        } else if (HoverUtil.isHovered(mouseX, mouseY, bindX, bindY, bindBoxW, 10.0) && button == 0) {
            this.binding = true;
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.binding) {
            if (keyCode == 256 || keyCode == 261) {
                this.setting.setValue(-1);
            } else {
                this.setting.setValue(keyCode);
            }
            this.binding = false;
        }
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

