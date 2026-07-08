/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1733
 *  net.minecraft.class_1735
 *  net.minecraft.class_437
 *  net.minecraft.class_465
 *  net.minecraft.class_495
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1733;
import net.minecraft.class_1735;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_495;

public class ShulkerBypass
extends Module {
    public ShulkerBypass() {
        super("ShulkerBypass", ModuleCategory.MISC);
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        class_437 class_4372 = ShulkerBypass.mc.field_1755;
        if (!(class_4372 instanceof class_495)) {
            return;
        }
        class_495 screen = (class_495)class_4372;
        if (!(ShulkerBypass.mc.field_1724.field_7512 instanceof class_1733)) {
            return;
        }
        class_1735 hoveredSlot = this.getSlotUnderMouse((class_465<?>)screen);
        if (hoveredSlot == null || !hoveredSlot.method_7681()) {
            return;
        }
        int emptySlot = this.findEmptyHotbarOrInventorySlot();
        if (emptySlot == -1) {
            return;
        }
        ShulkerBypass.mc.field_1761.method_2906(ShulkerBypass.mc.field_1724.field_7512.field_7763, hoveredSlot.field_7874, emptySlot, class_1713.field_7791, (class_1657)ShulkerBypass.mc.field_1724);
    }

    private class_1735 getSlotUnderMouse(class_465<?> screen) {
        double mouseX = ShulkerBypass.mc.field_1729.method_1603() * (double)mc.method_22683().method_4486() / (double)mc.method_22683().method_4480();
        double mouseY = ShulkerBypass.mc.field_1729.method_1604() * (double)mc.method_22683().method_4502() / (double)mc.method_22683().method_4507();
        for (class_1735 slot : screen.method_17577().field_7761) {
            if (!this.isPointOverSlot(slot, mouseX, mouseY, screen)) continue;
            return slot;
        }
        return null;
    }

    private boolean isPointOverSlot(class_1735 slot, double pointX, double pointY, class_465<?> screen) {
        int x = screen.field_2776 + slot.field_7873;
        int y = screen.field_2800 + slot.field_7872;
        return pointX >= (double)(x - 1) && pointX < (double)(x + 16 + 1) && pointY >= (double)(y - 1) && pointY < (double)(y + 16 + 1);
    }

    private int findEmptyHotbarOrInventorySlot() {
        for (class_1735 slot : ShulkerBypass.mc.field_1724.field_7512.field_7761) {
            if (slot.method_7681() || slot.field_7871 != ShulkerBypass.mc.field_1724.method_31548()) continue;
            return slot.method_34266();
        }
        return -1;
    }
}
