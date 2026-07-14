package ua.babka.ui.component;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class ModeListComponent
extends Component {
    private final ModeListSetting setting;
    private static final float NAME_HEIGHT = 10.0f;
    private static final float OPTION_H = 11.0f;
    private static final float GAP = 2.0f;
    private static final float PADDING = 4.5f;
    private static final float RADIUS = 1.0f;
    private final List<Animation> anims = new ArrayList<Animation>();

    public ModeListComponent(ModeListSetting setting) {
        this.setting = setting;
        for (int i2 = 0; i2 < setting.getSettings().size(); ++i2) {
            this.anims.add(new Animation(Easing.QUINTIC_OUT, 250L));
        }
    }

    private float optionWidth(String name) {
        return Fonts.SFREGULAR.get().getWidth(name, 6.5f) + 8.0f;
    }

    private float calcHeight() {
        float maxRowWidth = this.width - 9.0f;
        float rowWidth = 0.0f;
        float totalHeight = 12.0f;
        boolean firstInRow = true;
        for (BooleanSetting s2 : this.setting.getSettings()) {
            float ow = this.optionWidth(s2.getName());
            if (!firstInRow && rowWidth + ow > maxRowWidth) {
                totalHeight += 13.0f;
                rowWidth = ow + 2.0f;
                continue;
            }
            rowWidth += ow + 2.0f;
            firstInRow = false;
        }
        return totalHeight += 11.0f;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        float animValue = this.getAlphaAnimSetting().getValue();
        float alpha = Math.max(Math.min(animValue * this.getAlphaAnim().getValue(), 1.0f), 0.0f);
        int alphaInt = (int)(255.0f * alpha);
        if (alpha < 0.02f) {
            return;
        }
        float totalHeight = this.calcHeight();
        this.setHeight((totalHeight + 2.0f) * animValue);
        DrawUtil.drawScrollingText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 1.5f, ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt), 6.5f, this.width - 9.0f, "ml_" + this.setting.getName());
        float curX = this.x + 4.5f;
        float curY = this.y + 10.0f + 2.0f;
        boolean firstInRow = true;
        int i2 = 0;
        for (BooleanSetting s2 : this.setting.getSettings()) {
            float ow = this.optionWidth(s2.getName());
            if (!firstInRow && curX + ow > this.x + this.width - 4.5f) {
                curX = this.x + 4.5f;
                curY += 13.0f;
            }
            firstInRow = false;
            boolean selected = s2.getValue();
            Animation anim = this.anims.get(i2);
            anim.run(selected);
            float av2 = anim.getValue();
            if (HoverUtil.isHovered(mouseX, mouseY, curX, curY, ow, 11.0)) {
                CursorManager.requestHand();
            }
            int bgColor = ColorProvider.interpolateColor(ColorProvider.setAlpha(ColorProvider.getColorInactiveButton(), alphaInt), ColorProvider.setAlpha(ColorProvider.getColorButton(), alphaInt), av2);
            DrawUtil.drawRound(curX, curY, ow, 11.0f, 1.0f, bgColor);
            int textColor = ColorProvider.interpolateColor(ColorProvider.setAlpha(ColorProvider.getColorInactiveText(), alphaInt), ColorProvider.setAlpha(ColorProvider.getColorText(), alphaInt), av2);
            float tw = Fonts.SFREGULAR.get().getWidth(s2.getName(), 6.5f);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), s2.getName(), curX + (ow - tw) / 2.0f, curY + 1.75f, textColor, 6.5f);
            curX += ow + 2.0f;
            ++i2;
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return;
        }
        float curX = this.x + 4.5f;
        float curY = this.y + 10.0f + 2.0f;
        boolean firstInRow = true;
        for (BooleanSetting s2 : this.setting.getSettings()) {
            float ow = this.optionWidth(s2.getName());
            if (!firstInRow && curX + ow > this.x + this.width - 4.5f) {
                curX = this.x + 4.5f;
                curY += 13.0f;
            }
            firstInRow = false;
            if (HoverUtil.isHovered(mouseX, mouseY, curX, curY, ow, 11.0)) {
                s2.setValue(!s2.getValue());
                s2.setClicked(true);
                return;
            }
            curX += ow + 2.0f;
        }
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

