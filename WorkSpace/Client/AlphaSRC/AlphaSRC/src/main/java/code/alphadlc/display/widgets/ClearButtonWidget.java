/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_4068
 *  net.minecraft.class_6379
 *  net.minecraft.class_6379$class_6380
 *  net.minecraft.class_6382
 */
package code.alphadlc.display.widgets;

import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_6379;
import net.minecraft.class_6382;

public class ClearButtonWidget
implements class_4068,
class_364,
class_6379 {
    private static final class_2960 CLEAR_BUTTON_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/clear_button.png");
    private static final class_2960 HOVERED_CLEAR_BUTTON_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/hovered_clear_button.png");
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final Runnable onPress;
    private boolean hovered = false;

    public ClearButtonWidget(int x, int y, int width, int height, Runnable onPress) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onPress = onPress;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        class_2960 texture = this.hovered ? HOVERED_CLEAR_BUTTON_TEXTURE : CLEAR_BUTTON_TEXTURE;
        context.method_25290(class_1921::method_62277, texture, this.x, this.y, 0.0f, 0.0f, this.width, this.height, this.width, this.height);
        if (this.hovered && QuickImports.mc.field_1755 != null) {
            context.method_51438(QuickImports.mc.field_1772, (class_2561)class_2561.method_43470((String)"\u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c"), mouseX, mouseY);
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button == 0 && mouseX >= (double)this.x && mouseY >= (double)this.y && mouseX < (double)(this.x + this.width) && mouseY < (double)(this.y + this.height)) {
            this.onPress.run();
            return true;
        }
        return false;
    }

    public void method_25365(boolean focused) {
    }

    public boolean method_25370() {
        return false;
    }

    public class_6379.class_6380 method_37018() {
        return class_6379.class_6380.field_33784;
    }

    public void method_37020(class_6382 builder) {
    }
}
