/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1661
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 */
package code.alphadlc.features.impl.combat.autoswapUtil;

import code.alphadlc.features.impl.combat.AutoSwap;
import code.alphadlc.features.impl.combat.autoswapUtil.AutoSwapWheelScreen;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import java.awt.Color;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class AutoSwapItemSelectScreen
extends class_437
implements QuickImports {
    private final AutoSwap autoSwap;
    private final int wheelSlotIndex;
    private final int rows = 4;
    private final int cols = 9;
    private final int slotSize = 18;
    private final int slotPadding = 4;

    public AutoSwapItemSelectScreen(AutoSwap autoSwap, int wheelSlotIndex) {
        super((class_2561)class_2561.method_43470((String)"\u0412\u044b\u0431\u043e\u0440 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430"));
        this.autoSwap = autoSwap;
        this.wheelSlotIndex = wheelSlotIndex;
    }

    public boolean method_25421() {
        return false;
    }

    public boolean method_25422() {
        return true;
    }

    public void method_25419() {
        if (this.field_22787 != null) {
            this.field_22787.method_1507((class_437)new AutoSwapWheelScreen(this.autoSwap));
        }
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
        if (this.field_22787 == null || this.field_22787.field_1724 == null) {
            super.method_25394(ctx, mouseX, mouseY, delta);
            return;
        }
        int panelW = 214;
        int panelH = 128;
        int x = (this.field_22789 - panelW) / 2;
        int y = (this.field_22790 - panelH) / 2;
        rectangle.render(ShapeProperties.create(ctx.method_51448(), x, y, panelW, panelH).color(new Color(20, 20, 25, 230).getRGB()).round(8.0f).build());
        ctx.method_27535(this.field_22793, (class_2561)class_2561.method_43470((String)("\u041b\u041a\u041c \u2013 \u0432\u044b\u0431\u0440\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0434\u043b\u044f \u0441\u043b\u043e\u0442\u0430 " + this.wheelSlotIndex)), x + 10, y + 8, -1);
        int invX = x + 8;
        int invYStart = y + 24;
        class_1661 inv = this.field_22787.field_1724.method_31548();
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 9; ++col) {
                int index = row * 9 + col;
                int sx = invX + col * 22;
                int sy = invYStart + row * 22;
                rectangle.render(ShapeProperties.create(ctx.method_51448(), sx, sy, 18.0, 18.0).color(new Color(35, 35, 45, 200).getRGB()).round(4.0f).build());
                class_1799 stack = inv.method_5438(index);
                if (stack.method_7960()) continue;
                ctx.method_51427(stack, sx + 1, sy + 1);
            }
        }
        super.method_25394(ctx, mouseX, mouseY, delta);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (this.field_22787 == null || this.field_22787.field_1724 == null) {
            return super.method_25402(mouseX, mouseY, button);
        }
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        int panelW = 214;
        int panelH = 128;
        int x = (this.field_22789 - panelW) / 2;
        int y = (this.field_22790 - panelH) / 2;
        int invX = x + 8;
        int invYStart = y + 24;
        class_1661 inv = this.field_22787.field_1724.method_31548();
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 9; ++col) {
                int index = row * 9 + col;
                int sx = invX + col * 22;
                int sy = invYStart + row * 22;
                if (!(mouseX >= (double)sx) || !(mouseX <= (double)(sx + 18)) || !(mouseY >= (double)sy) || !(mouseY <= (double)(sy + 18))) continue;
                class_1799 stack = inv.method_5438(index);
                if (!stack.method_7960()) {
                    this.applyItem(stack);
                }
                return true;
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    private void applyItem(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return;
        }
        String itemName = stack.method_7964().getString();
        this.autoSwap.setWheelSlotItem(this.wheelSlotIndex, stack.method_7909(), itemName);
        this.method_25419();
    }

    private String mapItemName(class_1792 item) {
        if (item == class_1802.field_8288) {
            return "Totem of Undying";
        }
        if (item == class_1802.field_8575) {
            return "Player Head";
        }
        if (item == class_1802.field_8463) {
            return "Golden Apple";
        }
        if (item == class_1802.field_8255) {
            return "Shield";
        }
        return null;
    }
}
