package ua.babka.ui.component;

import java.awt.Color;
import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.setting.ColorSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.Scissor;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.Component;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class ColorPickerComponent
extends Component {
    private final ColorSetting setting;
    private boolean opened;
    private final Animation openAnim = new Animation(Easing.QUINTIC_OUT, 300L);
    private boolean draggingSV;
    private boolean draggingHue;
    private float hue;
    private float saturation;
    private float value;

    public ColorPickerComponent(ColorSetting setting) {
        this.setting = setting;
        this.updateHSV();
    }

    private void updateHSV() {
        Color c2 = new Color(this.setting.getValue(), true);
        float[] hsv = Color.RGBtoHSB(c2.getRed(), c2.getGreen(), c2.getBlue(), null);
        this.hue = hsv[0];
        this.saturation = hsv[1];
        this.value = hsv[2];
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        float alpha = Math.min(this.getAlphaAnimSetting().getValue(), 1.0f);
        int alphaInt = (int)(255.0f * alpha);
        this.openAnim.run(this.opened);
        float expandedHeight = 55.0f;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), this.setting.getName(), this.x + 4.5f, this.y + 3.0f, ColorProvider.rgba(255, 255, 255, alphaInt), 6.5f);
        float previewSize = 8.0f;
        float previewX = this.x + this.width - previewSize - 5.0f;
        float previewY = this.y + 2.5f;
        if (HoverUtil.isHovered(mouseX, mouseY, previewX, previewY, previewSize, previewSize)) {
            CursorManager.requestHand();
        }
        DrawUtil.drawRound(previewX - 0.5f, previewY - 0.5f, previewSize + 1.0f, previewSize + 1.0f, 1.5f, ColorProvider.rgba(60, 60, 65, (int)(150.0f * alpha)));
        DrawUtil.drawRound(previewX, previewY, previewSize, previewSize, 1.5f, (int)this.setting.getValue());
        if (this.openAnim.getValue() > 0.01f) {
            float pickerY = this.y + 13.0f;
            float animH = this.openAnim.getValue() * expandedHeight;
            float animAlpha = alpha * this.openAnim.getValue();
            int animAlphaInt = (int)(255.0f * animAlpha);
            Scissor.push();
            Scissor.setFromComponentCoordinates(this.x, pickerY, this.width, animH);
            DrawUtil.drawRound(this.x + 4.5f, pickerY - 0.5f, this.width - 9.0f, expandedHeight + 1.0f, 2.5f, ColorProvider.rgba(60, 60, 65, (int)(150.0f * animAlpha)));
            DrawUtil.drawRound(this.x + 5.0f, pickerY, this.width - 10.0f, expandedHeight, 2.5f, ColorProvider.rgba(20, 20, 25, animAlphaInt));
            float svX = this.x + 9.0f;
            float svY = pickerY + 4.0f;
            float svSize = 40.0f;
            int cHue = ColorProvider.setAlpha(Color.HSBtoRGB(this.hue, 1.0f, 1.0f), animAlphaInt);
            int cWhite = ColorProvider.rgba(255, 255, 255, animAlphaInt);
            int cClearWhite = ColorProvider.rgba(255, 255, 255, 0.0f);
            int cBlack = ColorProvider.rgba(0, 0, 0, animAlphaInt);
            int cClearBlack = ColorProvider.rgba(0, 0, 0, 0.0f);
            DrawUtil.drawRound(svX, svY, svSize, svSize, 2.0f, cHue);
            DrawUtil.drawRound(svX, svY, svSize, svSize, 2.0f, cWhite, cWhite, cClearWhite, cClearWhite);
            DrawUtil.drawRound(svX, svY, svSize, svSize, 2.0f, cClearBlack, cBlack, cBlack, cClearBlack);
            float svCursorX = svX + this.saturation * svSize;
            float svCursorY = svY + (1.0f - this.value) * svSize;
            DrawUtil.drawRound(svCursorX - 2.5f, svCursorY - 2.5f, 5.0f, 5.0f, 2.5f, ColorProvider.rgba(0, 0, 0, (int)(180.0f * animAlpha)));
            DrawUtil.drawRound(svCursorX - 1.5f, svCursorY - 1.5f, 3.0f, 3.0f, 1.5f, ColorProvider.rgba(255, 255, 255, animAlphaInt));
            float hueX = svX + svSize + 6.0f;
            float hueY = svY;
            float hueW = 6.0f;
            for (float i2 = 0.0f; i2 <= svSize; i2 += 0.5f) {
                int color = ColorProvider.setAlpha(Color.HSBtoRGB(i2 / svSize, 1.0f, 1.0f), animAlphaInt);
                DrawUtil.drawRound(hueX, hueY + i2, hueW, 1.0f, 0.0f, color);
            }
            float hueCursorY = hueY + this.hue * svSize;
            DrawUtil.drawRound(hueX - 1.5f, hueCursorY - 2.5f, hueW + 3.0f, 5.0f, 2.0f, ColorProvider.rgba(0, 0, 0, (int)(180.0f * animAlpha)));
            DrawUtil.drawRound(hueX - 0.5f, hueCursorY - 1.5f, hueW + 1.0f, 3.0f, 1.0f, ColorProvider.rgba(255, 255, 255, animAlphaInt));
            if (this.draggingSV) {
                this.saturation = Math.max(0.0f, Math.min(1.0f, ((float)mouseX - svX) / svSize));
                this.value = 1.0f - Math.max(0.0f, Math.min(1.0f, ((float)mouseY - svY) / svSize));
                this.syncColor();
            } else if (this.draggingHue) {
                this.hue = Math.max(0.0f, Math.min(1.0f, ((float)mouseY - hueY) / svSize));
                this.syncColor();
            }
            Scissor.unset();
            Scissor.pop();
        }
        this.setHeight(13.0f + this.openAnim.getValue() * expandedHeight);
    }

    private void syncColor() {
        int rgb = Color.HSBtoRGB(this.hue, this.saturation, this.value);
        this.setting.setValue(rgb | 0xFF000000);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        float previewSize = 8.0f;
        float previewX = this.x + this.width - previewSize - 5.0f;
        float previewY = this.y + 2.5f;
        if (HoverUtil.isHovered(mouseX, mouseY, previewX, previewY, previewSize, previewSize) && button == 1) {
            this.opened = !this.opened;
            return;
        }
        if (this.opened && button == 0) {
            float svX = this.x + 9.0f;
            float svY = this.y + 13.0f + 4.0f;
            float svSize = 40.0f;
            float hueX = svX + svSize + 6.0f;
            if (HoverUtil.isHovered(mouseX, mouseY, svX, svY, svSize, svSize)) {
                this.draggingSV = true;
            } else if (HoverUtil.isHovered(mouseX, mouseY, hueX - 2.0f, svY, 10.0, svSize)) {
                this.draggingHue = true;
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.draggingSV = false;
        this.draggingHue = false;
    }

    @Override
    public boolean isVisible() {
        return (Boolean)this.setting.visible.get();
    }
}

