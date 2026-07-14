package ua.babka.ui.component;

import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.ThemeSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.theme.Theme;
import ua.babka.ui.component.Component;

public class ThemeComponent
extends Component {
    private final ThemeSetting option;

    public ThemeComponent(ThemeSetting option) {
        this.option = option;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        float startX;
        float currentX = startX = this.x + 2.0f;
        float circleY = this.y + 8.0f;
        for (Theme theme : this.option.getThemes()) {
            theme.checkAnimation.run(this.option.getValue() == theme);
            if (theme.checkAnimation.getValue() > 0.01f) {
                float alphaAnim = theme.checkAnimation.getValue();
                DrawUtil.drawRound(currentX - 1.5f, circleY - 1.5f, 11.0f, 11.0f, 5.5f, ColorProvider.rgba(255, 255, 255, (int)(255.0f * alphaAnim)));
            }
            theme.x = currentX;
            theme.y = circleY;
            theme.drawTheme(this.getAlphaAnimSetting().getValue());
            currentX += 14.0f;
        }
        this.setHeight(20.0f);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        float currentX = this.x + 2.0f;
        float circleY = this.y + 8.0f;
        for (Theme theme : this.option.getThemes()) {
            if (HoverUtil.isHovered(mouseX, mouseY, currentX - 1.0f, circleY - 1.0f, 10.0, 10.0) && this.option.getValue() != theme && button == 0) {
                this.option.setValue(theme);
                theme.animation.setValue(0.0f);
            }
            currentX += 14.0f;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    @Override
    public void mouseScrolled(double mouseX, double mouseY, double delta) {
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}

