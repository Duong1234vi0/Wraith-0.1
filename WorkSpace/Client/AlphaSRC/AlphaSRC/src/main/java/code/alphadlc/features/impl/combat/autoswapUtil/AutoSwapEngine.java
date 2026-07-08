/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 */
package code.alphadlc.features.impl.combat.autoswapUtil;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2596;
import net.minecraft.class_2815;
import net.minecraft.class_437;
import net.minecraft.class_490;

public final class AutoSwapEngine
implements QuickImports {
    private static final AutoSwapEngine INSTANCE = new AutoSwapEngine();
    private final StopWatch funtimeTimer = new StopWatch();
    private class_1735 pendingSlot;
    private boolean swapQueued;
    private int delayTicks;
    private int stage;
    private boolean funtimeQueued;
    private String bypassMode = "Vanilla";

    private AutoSwapEngine() {
    }

    public static AutoSwapEngine get() {
        return INSTANCE;
    }

    public void requestSwap(class_1735 slot, String bypassMode) {
        if (AutoSwapEngine.mc.field_1724 == null || slot == null || slot.field_7874 == -1) {
            this.reset();
            return;
        }
        this.bypassMode = bypassMode != null ? bypassMode : "Vanilla";
        this.pendingSlot = slot;
        this.swapQueued = true;
    }

    public void onTick(TickEvent event) {
        if (AutoSwapEngine.mc.field_1724 == null || AutoSwapEngine.mc.field_1761 == null) {
            return;
        }
        if (this.delayTicks > 0) {
            --this.delayTicks;
        }
        if (this.funtimeQueued && this.funtimeTimer.finished(100.0) && this.pendingSlot != null) {
            this.executeFuntimeSwap(this.pendingSlot);
            this.reset();
            return;
        }
        if (this.stage == 1 && this.pendingSlot != null && this.delayTicks <= 0) {
            this.executeDelayedSwap(this.pendingSlot);
            this.reset();
            return;
        }
        if (!this.swapQueued || this.pendingSlot == null) {
            return;
        }
        this.swapQueued = false;
        class_1735 slot = this.pendingSlot;
        if (this.isFuntimeBypass()) {
            this.funtimeQueued = true;
            this.funtimeTimer.reset();
            return;
        }
        if (this.shouldDelaySwap()) {
            this.delayTicks = 2;
            this.stage = 1;
            return;
        }
        this.executeInstantSwap(slot);
        this.pendingSlot = null;
    }

    public void onInput(InputEvent event) {
        if (!this.isBusy()) {
            return;
        }
        if (this.delayTicks > 0 || this.funtimeQueued) {
            event.inputNone();
        }
        if (this.funtimeQueued && !this.funtimeTimer.finished(350.0)) {
            event.setSneak(false);
        }
    }

    public boolean isBusy() {
        return this.swapQueued || this.funtimeQueued || this.stage > 0 || this.delayTicks > 0 || this.pendingSlot != null;
    }

    public void reset() {
        this.pendingSlot = null;
        this.swapQueued = false;
        this.delayTicks = 0;
        this.stage = 0;
        this.funtimeQueued = false;
        this.bypassMode = "Vanilla";
    }

    private boolean isFuntimeBypass() {
        return "Funtime".equals(this.bypassMode);
    }

    private boolean shouldDelaySwap() {
        return "SpookyTime".equals(this.bypassMode) || "Reallyworld".equals(this.bypassMode) || "Holyworld".equals(this.bypassMode) || "LonyGrief".equals(this.bypassMode);
    }

    private boolean usesInventoryTask() {
        return !"Vanilla".equals(this.bypassMode) && !"Reallyworld".equals(this.bypassMode);
    }

    private void executeInstantSwap(class_1735 slot) {
        this.withGuiMoveBypass(false, () -> {
            if (this.usesInventoryTask()) {
                InventoryTask.swapHand(slot, class_1268.field_5810, true, true);
            } else {
                this.clickOffhand(slot);
            }
        });
    }

    private void executeDelayedSwap(class_1735 slot) {
        this.withGuiMoveBypass(true, () -> this.clickOffhand(slot));
    }

    private void executeFuntimeSwap(class_1735 slot) {
        if (AutoSwapEngine.mc.field_1724 == null) {
            return;
        }
        mc.method_1507((class_437)new class_490((class_1657)AutoSwapEngine.mc.field_1724));
        this.withGuiMoveBypass(false, () -> this.clickOffhand(slot));
        mc.method_1507(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void withGuiMoveBypass(boolean delayedSwap, Runnable action) {
        boolean previousBypass;
        GuiMove guiMove = GuiMove.getInstance();
        boolean bl = previousBypass = guiMove != null && guiMove.isBypassActive();
        if (guiMove != null && guiMove.isState()) {
            if (delayedSwap && "SpookyTime".equals(this.bypassMode)) {
                guiMove.setBypassActive(true);
            } else {
                guiMove.beginBypassForSwap(this.bypassMode);
            }
            if ("Funtime".equals(this.bypassMode)) {
                guiMove.notifySpookyClick();
            }
        }
        try {
            action.run();
        }
        finally {
            if (guiMove != null && delayedSwap && "SpookyTime".equals(this.bypassMode)) {
                guiMove.setBypassActive(previousBypass);
            }
        }
    }

    private void clickOffhand(class_1735 slot) {
        if (AutoSwapEngine.mc.field_1724 == null || AutoSwapEngine.mc.field_1761 == null || slot == null) {
            return;
        }
        InventoryTask.clickSlot(slot, 40, class_1713.field_7791, false);
        if (AutoSwapEngine.mc.field_1724.field_3944 != null) {
            AutoSwapEngine.mc.field_1724.field_3944.method_52787((class_2596)new class_2815(AutoSwapEngine.mc.field_1724.field_7512.field_7763));
        }
        InventoryFlowManager.enableMoveKeys();
        AutoSwapEngine.mc.field_1761.method_2911();
    }
}
