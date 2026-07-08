/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 */
package code.alphadlc.display.screens.altscreen;

import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class Alt
extends class_437
implements QuickImports {
    public static Alt INSTANCE = new Alt();
    public int x;
    public int y;
    public int width;
    public int height;

    public void initialize() {
    }

    public Alt() {
        super(class_2561.method_30163((String)"Alt"));
        this.initialize();
    }

    public void method_25393() {
        super.method_25393();
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.x = window.getScaledWidth() / 2 - 200;
        this.y = window.getScaledHeight() / 2 - 125;
        this.width = window.getScaledWidth();
        this.height = window.getScaledHeight();
        super.method_25394(context, mouseX, mouseY, delta);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
