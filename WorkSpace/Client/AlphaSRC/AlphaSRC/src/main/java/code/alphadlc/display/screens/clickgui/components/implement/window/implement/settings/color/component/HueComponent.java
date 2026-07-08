/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class HueComponent
extends AbstractComponent {
    private final ColorSetting setting;
    private boolean hueDragging;
    private float X;
    private float Y;
    private float W;
    private float H;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.X = this.x + 6.0f;
        this.Y = this.y + 18.5f;
        this.W = 88.0f;
        this.H = 50.0f;
        int[] color = new int[]{-16777216, -1, -16777216, Color.HSBtoRGB(this.setting.getHue(), 1.0f, 1.0f)};
        rectangle.render(ShapeProperties.create(matrix, this.X, this.Y, this.W, this.H).round(2.0f).color(color).build());
        float clampedX = class_3532.method_15363((float)(this.X + this.W * this.setting.getSaturation()), (float)this.X, (float)(this.X + this.W - 5.0f));
        float clampedY = class_3532.method_15363((float)(this.Y + this.H * (1.0f - this.setting.getBrightness())), (float)this.Y, (float)(this.Y + this.H - 5.0f));
        rectangle.render(ShapeProperties.create(matrix, clampedX, clampedY, 5.0, 5.0).round(2.5f).softness(1.0f).thickness(3.0f).color(0xFFFFFF).outlineColor(-1).build());
        float min = class_3532.method_15363((float)(((float)mouseX - this.X) / this.W), (float)0.0f, (float)1.0f);
        if (this.hueDragging) {
            this.setting.setBrightness(class_3532.method_15363((float)(1.0f - ((float)mouseY - this.Y) / this.H), (float)0.0f, (float)1.0f));
            this.setting.setSaturation(min);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean bl = this.hueDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, this.X, this.Y, this.W, this.H);
        if (this.hueDragging) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.hueDragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public HueComponent(ColorSetting setting) {
        this.setting = setting;
    }
}
