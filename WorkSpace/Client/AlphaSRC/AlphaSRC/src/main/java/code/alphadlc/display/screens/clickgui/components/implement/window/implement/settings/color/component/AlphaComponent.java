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

public class AlphaComponent
extends AbstractComponent {
    private final ColorSetting setting;
    private boolean alphaDragging;
    private float X;
    private float Y;
    private float W;
    private float H;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.X = this.x + 6.0f;
        this.Y = this.y + 83.5f;
        this.W = 88.0f;
        this.H = 4.0f;
        float clampedX = class_3532.method_15363((float)(this.X + this.W * this.setting.getAlpha()), (float)this.X, (float)(this.X + this.W - 4.0f));
        float min = class_3532.method_15363((float)(((float)mouseX - this.X) / this.W), (float)0.0f, (float)1.0f);
        image.setTexture("textures/alpha.png").render(ShapeProperties.create(matrix, this.X, this.Y, this.W, this.H).build());
        rectangle.render(ShapeProperties.create(matrix, this.X, (double)this.Y - 0.2, (double)this.W + 0.5, this.H).round(1.5f).color(Integer.MIN_VALUE, 0x8000000, this.setting.getColorWithAlpha(), this.setting.getColorWithAlpha()).build());
        rectangle.render(ShapeProperties.create(matrix, clampedX, this.Y, this.H, this.H).round(this.H / 2.0f).thickness(3.0f).color(0xFFFFFF).outlineColor(-1).build());
        if (this.alphaDragging) {
            this.setting.setAlpha(min);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean bl = this.alphaDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, this.X, this.Y, this.W, this.H);
        if (this.alphaDragging) {
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.alphaDragging = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public AlphaComponent(ColorSetting setting) {
        this.setting = setting;
    }
}
