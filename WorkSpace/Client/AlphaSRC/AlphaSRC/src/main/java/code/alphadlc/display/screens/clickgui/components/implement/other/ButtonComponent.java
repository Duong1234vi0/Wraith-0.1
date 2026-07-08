/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class ButtonComponent
extends AbstractComponent {
    private String text;
    private Runnable runnable;
    private int color = -8288257;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.width = Fonts.getSize(12).getStringWidth(this.text) + 13.0f;
        this.height = 12.0f;
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, this.width, this.height).round(2.0f).color(this.color).build());
        Fonts.getSize(12, Fonts.Type.BOLD).drawCenteredString(matrix, this.text, (double)this.x + (double)this.width / 2.0, this.y + 5.0f, -1);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height) && button == 0) {
            this.runnable.run();
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public ButtonComponent setText(String text) {
        this.text = text;
        return this;
    }

    public ButtonComponent setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public ButtonComponent setColor(int color) {
        this.color = color;
        return this;
    }
}
