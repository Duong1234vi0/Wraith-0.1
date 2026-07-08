/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2815;
import net.minecraft.class_2848;

public class ElytraJump
extends Module {
    private static final float PITCH_CLAMP_MIN = -90.0f;
    private static final float PITCH_CLAMP_MAX = 90.0f;
    private static final double VERTICAL_BOOST = 0.08;
    private static final double MIN_FALL = 0.1;
    private static final float MIN_FALL_DISTANCE = 0.5f;
    private static final double HORIZONTAL_EPSILON = 0.01;
    private final BooleanSetting autoSwap = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0441\u0432\u0430\u043f \u044d\u043b\u0438\u0442\u0440\u044b", "\u041d\u0430\u0434\u0435\u0432\u0430\u0435\u0442 \u044d\u043b\u0438\u0442\u0440\u0443 \u043f\u0440\u0438 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0438 \u043c\u043e\u0434\u0443\u043b\u044f").setValue(false);

    public ElytraJump() {
        super("ElytraJump", "Elytra Jump", ModuleCategory.MOVEMENT);
        this.setup(this.autoSwap);
    }

    private boolean wearingElytra() {
        return ElytraJump.mc.field_1724 != null && ElytraJump.mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.state || PlayerInteractionHelper.nullCheck()) {
            return;
        }
        if (ElytraJump.mc.field_1724.method_6128()) {
            float pitch = ElytraJump.mc.field_1724.method_36455();
            if (pitch < -90.0f || pitch > 90.0f) {
                ElytraJump.mc.field_1724.method_36457(0.0f);
            }
            ElytraJump.mc.field_1724.field_6241 = ElytraJump.mc.field_1724.method_36454();
            ElytraJump.mc.field_1724.field_6283 = ElytraJump.mc.field_1724.method_36454();
            ElytraJump.mc.field_1724.field_6259 = ElytraJump.mc.field_1724.method_36454();
            ElytraJump.mc.field_1724.field_6220 = ElytraJump.mc.field_1724.method_36454();
        }
        if (!ElytraJump.mc.field_1724.method_31549().field_7479 && ElytraJump.mc.field_1724.method_24828() && !ElytraJump.mc.field_1724.method_5869() && !ElytraJump.mc.field_1724.method_5771() && this.wearingElytra() && !ElytraJump.mc.field_1690.field_1903.method_1434()) {
            ElytraJump.mc.field_1724.method_6043();
        }
        if (!(ElytraJump.mc.field_1724.method_31549().field_7479 || ElytraJump.mc.field_1724.method_24828() || ElytraJump.mc.field_1724.method_5869() || ElytraJump.mc.field_1724.method_5771() || ElytraJump.mc.field_1724.method_6128() || !this.canStartGlide())) {
            ElytraJump.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)ElytraJump.mc.field_1724, class_2848.class_2849.field_12982));
            ElytraJump.mc.field_1724.method_23669();
        }
        if (ElytraJump.mc.field_1724.method_24828() || ElytraJump.mc.field_1724.method_5869() || ElytraJump.mc.field_1724.method_5771()) {
            ElytraJump.mc.field_1690.field_1903.method_23481(false);
        }
        if (ElytraJump.mc.field_1724.field_6235 > 0 && this.autoSwap.isValue() && this.wearingElytra()) {
            this.swapElytraFromInventory();
        } else if (this.wearingElytra()) {
            ElytraJump.mc.field_1690.field_1903.method_23481(true);
            if (ElytraJump.mc.field_1724.method_6128()) {
                this.applyVerticalBoost();
            }
        } else if (this.autoSwap.isValue()) {
            this.equipElytra();
        } else {
            ElytraJump.mc.field_1690.field_1903.method_23481(false);
        }
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (!this.state || ElytraJump.mc.field_1724 == null || !ElytraJump.mc.field_1724.method_6128()) {
            return;
        }
        event.setForward(0.0f);
        event.setStrafe(0.0f);
    }

    private void applyVerticalBoost() {
        boolean horizontalStill;
        class_243 velocity = ElytraJump.mc.field_1724.method_18798();
        boolean fallingFast = velocity.field_1351 > 0.1 || ElytraJump.mc.field_1724.field_6017 > 0.5f;
        boolean bl = horizontalStill = Math.abs(velocity.field_1352) <= 0.01 && Math.abs(velocity.field_1350) <= 0.01;
        if (fallingFast && horizontalStill) {
            ElytraJump.mc.field_1724.method_18800(0.0, velocity.field_1351 + 0.08, 0.0);
        }
    }

    private boolean canStartGlide() {
        if (!this.wearingElytra()) {
            return false;
        }
        class_1799 chest = ElytraJump.mc.field_1724.method_6118(class_1304.field_6174);
        return !ElytraJump.mc.field_1724.method_5869() && !ElytraJump.mc.field_1724.method_5771() && !ElytraJump.mc.field_1724.method_5765() && chest.method_31574(class_1802.field_8833) && chest.method_7919() < chest.method_7936() - 1;
    }

    private void equipElytra() {
        int slot = InventoryTask.findInventorySlot(class_1802.field_8833);
        if (slot != -1) {
            this.swapChest(slot);
        }
    }

    private void swapElytraFromInventory() {
        int slot = InventoryTask.findInventorySlot(class_1802.field_8833);
        if (slot != -1) {
            this.swapChest(slot);
        }
    }

    private void swapChest(int slot) {
        InventoryTask.moveItem(slot, 6, true, true);
        if (ElytraJump.mc.field_1724.field_3944 != null) {
            ElytraJump.mc.field_1724.field_3944.method_52787((class_2596)new class_2815(ElytraJump.mc.field_1724.field_7512.field_7763));
        }
    }

    @Override
    public void deactivate() {
        if (ElytraJump.mc.field_1724 != null) {
            ElytraJump.mc.field_1690.field_1903.method_23481(false);
            if (this.autoSwap.isValue() && this.wearingElytra()) {
                this.swapElytraFromInventory();
            }
        }
        super.deactivate();
    }
}
