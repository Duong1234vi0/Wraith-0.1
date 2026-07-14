package ua.babka.ui.component;

import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class BooleanComponent
extends Component {
    private final BooleanSetting setting;
    private boolean binding;
    private final Animation hoverAnim = new Animation(Easing.QUINTIC_OUT, 180L);
    private final Animation glowAnim = new Animation(Easing.SINE_IN_OUT, 1500L);
    private boolean glowDirection = true;
    private static final float TW = 24.0f;
    private static final float TH = 12.0f;
    private static final float HEIGHT = 18.0f;

    public BooleanComponent(BooleanSetting setting) {
        this.setting = setting;
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        float alpha = Math.max(Math.min(this.getAlphaAnimSetting().getValue(), 1.0f), 0.0f);
        int ai2 = (int)(255.0f * alpha);
        float tx = this.x + this.width - 24.0f - 4.0f;
        float ty = this.y + 3.0f;
        boolean hovered = HoverUtil.isHovered(mouseX, mouseY, tx, ty, 24.0, 12.0);
        this.hoverAnim.run(hovered);
        if (hovered) {
            CursorManager.requestHand();
        }
        if (this.setting.getValue()) {
            this.glowAnim.run(this.glowDirection);
            if ((double)this.glowAnim.getValue() >= 0.99) {
                this.glowDirection = false;
            } else if ((double)this.glowAnim.getValue() <= 0.01) {
                this.glowDirection = true;
            }
        }
        String displayName = this.binding ? "⌨ Binding..." : this.setting.getName();
        int nameColor = this.binding ? ColorProvider.setAlpha(ColorProvider.getThemeColor(), ai2) : ColorProvider.rgba(200, 200, 205, ai2);
        DrawUtil.drawScrollingText(Fonts.SFREGULAR.get(), displayName, this.x + 5.0f, this.y + 5.5f, nameColor, 6.5f, this.width - 24.0f - 14.0f, "bool_" + this.setting.getName());
        float anim = this.setting.getAnimation().getValue();
        int themeC = ColorProvider.getThemeColor();
        if (anim > 0.1f) {
            float glowIntensity = (float)((double)anim * 0.3 * (0.7 + 0.3 * (double)this.glowAnim.getValue()));
            DrawUtil.drawRound(tx - 1.5f, ty - 1.5f, 27.0f, 15.0f, 7.5f, ColorProvider.setAlpha(themeC, (int)(glowIntensity * 80.0f * alpha)));
        }
        int trackOff = ColorProvider.rgba(48, 48, 53, ai2);
        int trackOn = ColorProvider.setAlpha(themeC, ai2);
        int trackColor = ColorProvider.interpolateColor(trackOff, trackOn, anim);
        DrawUtil.drawRound(tx, ty, 24.0f, 12.0f, 6.0f, trackColor);
        float ksz = 9.0f;
        float kMinX = tx + 1.5f;
        float kMaxX = tx + 24.0f - ksz - 1.5f;
        float kx = kMinX + (kMaxX - kMinX) * anim;
        DrawUtil.drawRound(kx, ty + 1.8f, ksz, ksz, ksz / 2.0f, ColorProvider.rgba(0, 0, 0, (int)(60.0f * alpha)));
        float knobScale = 1.0f - (float)((double)this.hoverAnim.getValue() * 0.08);
        float scaledSize = ksz * knobScale;
        float offset = (ksz - scaledSize) / 2.0f;
        DrawUtil.drawRound(kx + offset, ty + 1.5f + offset, scaledSize, scaledSize, scaledSize / 2.0f, ColorProvider.rgba(255, 255, 255, ai2));
        if (this.setting.getKey() != -1 && !this.binding) {
            String keyText = "⌨";
            float keyW = Fonts.SFREGULAR.get().getWidth(keyText, 5.0f);
            DrawUtil.drawText(Fonts.SFREGULAR.get(), keyText, tx - keyW - 4.0f, this.y + 6.5f, ColorProvider.rgba(120, 120, 125, ai2), 5.0f);
        }
        this.setHeight(18.0f);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        float tx = this.x + this.width - 24.0f - 4.0f;
        float ty = this.y + 3.0f;
        if (!HoverUtil.isHovered(mouseX, mouseY, tx, ty, 24.0, 12.0)) {
            return;
        }
        if (button == 0) {
            this.setting.setValue(!this.setting.getValue());
        } else if (button == 2) {
            this.binding = !this.binding;
        } else if (this.binding) {
            this.setting.setKey(button);
            this.binding = false;
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.binding) {
            return;
        }
        if (keyCode == 256) {
            this.binding = false;
        } else if (keyCode == 261) {
            this.setting.setKey(-1);
            this.binding = false;
        } else {
            this.setting.setKey(keyCode);
            this.binding = false;
        }
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

