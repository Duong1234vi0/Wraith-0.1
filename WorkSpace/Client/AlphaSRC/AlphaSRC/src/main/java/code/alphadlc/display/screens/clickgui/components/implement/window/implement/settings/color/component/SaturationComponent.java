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
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class SaturationComponent
extends AbstractComponent {
    private final ColorSetting setting;
    private boolean saturationDragging;
    private float X;
    private float Y;
    private float W;
    private float H;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.X = this.x + 6.0f;
        this.Y = this.y + 73.5f;
        this.W = 88.0f;
        this.H = 4.0f;
        float clampedX = class_3532.method_15363((float)(this.X + this.W * this.setting.getHue()), (float)this.X, (float)(this.X + this.W - 4.0f));
        float min = class_3532.method_15363((float)(((float)mouseX - this.X) / this.W), (float)0.0f, (float)1.0f);
        image.setTexture("textures/hue.png").render(ShapeProperties.create(matrix, this.X, (double)this.Y + 0.5, this.W, this.H - 1.0f).build());
        rectangle.render(ShapeProperties.create(matrix, clampedX, this.Y, this.H, this.H).round(this.H / 2.0f).thickness(3.0f).color(0xFFFFFF).outlineColor(-1).build());
        if (this.saturationDragging) {
            this.setting.setHue(min);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean bl = this.saturationDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, this.X, this.Y, this.W, this.H);
        if (this.saturationDragging) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.saturationDragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public SaturationComponent(ColorSetting setting) {
        this.setting = setting;
    }
}
