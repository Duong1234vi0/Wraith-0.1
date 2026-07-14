package ua.babka.ui.component;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.StringSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;

public class StringComponent
extends Component {
    private final StringSetting setting;
    private boolean focused = false;

    public StringComponent(StringSetting setting) {
        this.setting = setting;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        float alpha = Math.max(Math.min(this.getAlphaAnimSetting().getValue(), 1.0f), 0.0f);
        int alphaInt = (int)(255.0f * alpha);
        float fieldX = this.x + 4.5f;
        float fieldY = this.y + 13.0f;
        float fieldW = this.width - 9.0f;
        float fieldH = 11.0f;
        if (HoverUtil.isHovered(mouseX, mouseY, fieldX, fieldY, fieldW, fieldH)) {
            CursorManager.requestIBeam();
        }
        DrawUtil.drawText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 2.0f, ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt), 6.5f);
        int bgColor = this.focused ? ColorProvider.setAlpha(ColorProvider.getColorButton(), alphaInt) : ColorProvider.setAlpha(ColorProvider.getColorInactiveButton(), alphaInt);
        DrawUtil.drawRound(fieldX, fieldY, fieldW, fieldH, 2.0f, bgColor);
        String display = this.setting.getValue().isEmpty() && !this.focused ? "Введите текст..." : this.setting.getValue();
        int textColor = this.setting.getValue().isEmpty() && !this.focused ? ColorProvider.setAlpha(ColorProvider.getColorInactiveText(), alphaInt) : ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt);
        DrawUtil.drawScrollingText(Fonts.SFREGULAR.get(), display, fieldX + 3.0f, fieldY + 2.0f, textColor, 6.5f, fieldW - 6.0f, "string_" + this.setting.getName());
        this.setHeight(26.0f);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        float fieldX = this.x + 4.5f;
        float fieldY = this.y + 13.0f;
        float fieldW = this.width - 9.0f;
        float fieldH = 11.0f;
        this.focused = HoverUtil.isHovered(mouseX, mouseY, fieldX, fieldY, fieldW, fieldH);
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.focused) {
            return;
        }
        if (keyCode == 256 || keyCode == 257) {
            this.focused = false;
            return;
        }
        if (keyCode == 259) {
            String val = this.setting.getValue();
            if (!val.isEmpty()) {
                this.setting.setValue(val.substring(0, val.length() - 1));
            }
            return;
        }
        if (keyCode == 86 && (modifiers & 2) != 0) {
            String clipboard = MinecraftClient.getInstance().keyboard.getClipboard();
            if (clipboard != null && !clipboard.isEmpty()) {
                this.setting.setValue(this.setting.getValue() + clipboard);
            }
            return;
        }
        if (keyCode == 65 && (modifiers & 2) != 0) {
            this.setting.setValue("");
        }
    }

    public void charTyped(char chr) {
        if (!this.focused) {
            return;
        }
        this.setting.setValue(this.setting.getValue() + chr);
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

