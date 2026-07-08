/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_310;

public class ShiftTap
extends Module {
    private long shiftTapEndTime = 0L;
    private boolean isModuleControllingSneak = false;
    private int shiftTapDuration = 100;
    private final class_310 mc = class_310.method_1551();

    public ShiftTap() {
        super("ShiftTap", "ShiftTap", ModuleCategory.COMBAT);
    }

    private void startShiftTap() {
        this.shiftTapEndTime = System.currentTimeMillis() + (long)this.shiftTapDuration;
        if (!this.isModuleControllingSneak) {
            this.mc.field_1690.field_1832.method_23481(true);
            this.isModuleControllingSneak = true;
        }
    }

    private void stopShiftTap() {
        if (this.isModuleControllingSneak) {
            this.mc.field_1690.field_1832.method_23481(false);
            this.isModuleControllingSneak = false;
        }
    }

    @EventHandler
    public void onAttack(AttackEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        this.startShiftTap();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (this.mc.field_1724 == null || this.mc.field_1724.method_7325()) {
            this.stopShiftTap();
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (this.isModuleControllingSneak && currentTime > this.shiftTapEndTime) {
            this.stopShiftTap();
        }
    }
}
