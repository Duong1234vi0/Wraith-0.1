package ua.babka.ui.component;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.ModeSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class ModeComponent
extends Component {
    private final ModeSetting setting;
    private static final float NAME_H = 9.0f;
    private static final float OPT_H = 11.0f;
    private static final float GAP = 2.0f;
    private static final float PAD = 4.5f;
    private static final float R = 3.0f;
    private final List<Animation> anims = new ArrayList<Animation>();

    public ModeComponent(ModeSetting setting) {
        this.setting = setting;
    }

    private float optW(String m2) {
        return Fonts.SFREGULAR.get().getWidth(m2, 6.0f) + 8.0f;
    }

    private float calcH() {
        float maxW = this.width - 9.0f;
        float rowW = 0.0f;
        float total = 11.0f;
        boolean first = true;
        for (String m2 : this.setting.getModes()) {
            float ow = this.optW(m2);
            if (!first && rowW + ow > maxW) {
                total += 13.0f;
                rowW = ow + 2.0f;
                continue;
            }
            rowW += ow + 2.0f;
            first = false;
        }
        return total + 11.0f;
    }

    private void ensureAnimsSize() {
        int size = this.setting.getModes().size();
        while (this.anims.size() < size) {
            this.anims.add(new Animation(Easing.QUINTIC_OUT, 200L));
        }
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        this.ensureAnimsSize();
        float av2 = this.getAlphaAnimSetting().getValue();
        float a2 = Math.max(Math.min(av2 * this.getAlphaAnim().getValue(), 1.0f), 0.0f);
        int ai2 = (int)(255.0f * a2);
        if (a2 < 0.02f) {
            return;
        }
        this.setHeight((this.calcH() + 2.0f) * av2);
        DrawUtil.drawScrollingText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 1.0f, ColorProvider.rgba(160, 160, 165, ai2), 6.0f, this.width - 9.0f, "mode_" + this.setting.getName());
        float curX = this.x + 4.5f;
        float curY = this.y + 9.0f + 2.0f;
        boolean first = true;
        int i2 = 0;
        int themeC = ColorProvider.getThemeColor();
        for (String mode : this.setting.getModes()) {
            float ow = this.optW(mode);
            if (!first && curX + ow > this.x + this.width - 4.5f) {
                curX = this.x + 4.5f;
                curY += 13.0f;
            }
            first = false;
            boolean sel = this.setting.is(mode);
            this.anims.get(i2).run(sel);
            float av22 = this.anims.get(i2).getValue();
            if (HoverUtil.isHovered(mouseX, mouseY, curX, curY, ow, 11.0)) {
                CursorManager.requestHand();
            }
            int bgOff = ColorProvider.rgba(45, 45, 50, ai2);
            int bgOn = ColorProvider.setAlpha(themeC, ai2);
            DrawUtil.drawRound(curX, curY, ow, 11.0f, 3.0f, ColorProvider.interpolateColor(bgOff, bgOn, av22));
            int txtOff = ColorProvider.rgba(130, 130, 135, ai2);
            int txtOn = ColorProvider.rgba(255, 255, 255, ai2);
            float tw = Fonts.SFREGULAR.get().getWidth(mode, 6.0f);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), mode, curX + (ow - tw) / 2.0f, curY + 2.0f, ColorProvider.interpolateColor(txtOff, txtOn, av22), 6.0f);
            curX += ow + 2.0f;
            ++i2;
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        this.ensureAnimsSize();
        if (button != 0) {
            return;
        }
        float curX = this.x + 4.5f;
        float curY = this.y + 9.0f + 2.0f;
        boolean first = true;
        for (String mode : this.setting.getModes()) {
            float ow = this.optW(mode);
            if (!first && curX + ow > this.x + this.width - 4.5f) {
                curX = this.x + 4.5f;
                curY += 13.0f;
            }
            first = false;
            if (HoverUtil.isHovered(mouseX, mouseY, curX, curY, ow, 11.0)) {
                this.setting.setValue(mode);
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

