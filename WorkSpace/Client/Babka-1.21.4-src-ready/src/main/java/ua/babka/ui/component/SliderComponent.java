package ua.babka.ui.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class SliderComponent
extends Component {
    private final SliderSetting setting;
    private boolean drag;
    private final Animation sliderAnimation = new Animation(Easing.QUINTIC_OUT, 100L);
    private static final float PAD = 4.5f;
    private static final float LINE_H = 1.5f;
    private static final float KNOB_SZ = 7.0f;

    public SliderComponent(SliderSetting setting) {
        this.setting = setting;
    }

    private double round(double v2, double step) {
        return new BigDecimal((double)Math.round(v2 / step) * step).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String fmt(double v2) {
        int scale = (int)Math.ceil(-Math.log10(this.setting.getStep()));
        if (scale < 0) {
            scale = 0;
        }
        return BigDecimal.valueOf(v2).setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        float alpha = Math.min(this.getAlphaAnimSetting().getValue(), 1.0f);
        int ai2 = (int)(255.0f * alpha);
        float trackW = this.width - 9.0f;
        float pct = (float)((this.setting.getValue() - this.setting.getMin()) / (this.setting.getMax() - this.setting.getMin()));
        this.sliderAnimation.run(pct * trackW);
        String val = this.fmt(this.setting.getValue());
        float valW = Fonts.SFREGULAR.get().getWidth(val, 6.5f);
        DrawUtil.drawScrollingText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 2.0f, ColorProvider.rgba(210, 210, 215, ai2), 6.5f, trackW - valW - 6.0f, "slider_" + this.setting.getName());
        DrawUtil.drawText(Fonts.SFREGULAR.get(), val, this.x + this.width - 4.5f - valW, this.y + 2.0f, ColorProvider.rgba(140, 140, 145, ai2), 6.5f);
        float tx = this.x + 4.5f;
        float ty = this.y + 13.0f;
        float fill = MathHelper.clamp((float)this.sliderAnimation.getValue(), (float)0.0f, (float)trackW);
        DrawUtil.drawRound(tx, ty - 0.75f, trackW, 1.5f, 0.75f, ColorProvider.rgba(60, 60, 65, (int)(200.0f * alpha)));
        if (fill > 0.5f) {
            DrawUtil.drawRound(tx, ty - 0.75f, fill, 1.5f, 0.75f, ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2));
        }
        float kx = tx + fill - 3.5f;
        float ky = ty - 3.5f;
        DrawUtil.drawRound(kx, ky, 7.0f, 7.0f, 3.5f, ColorProvider.rgba(245, 245, 248, ai2));
        if (this.drag) {
            double v2 = (double)(((float)mouseX - tx) / trackW) * (this.setting.getMax() - this.setting.getMin()) + this.setting.getMin();
            this.setting.setValue(MathHelper.clamp((double)this.round(v2, this.setting.getStep()), (double)this.setting.getMin(), (double)this.setting.getMax()));
        }
        this.setHeight(19.0f);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        float tx = this.x + 4.5f;
        float ty = this.y + 13.0f;
        if (HoverUtil.isHovered(mouseX, mouseY, tx - 4.0f, ty - 6.0f, this.width - 9.0f + 8.0f, 12.0) && button == 0) {
            this.drag = true;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.drag = false;
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.drag = false;
        }
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

