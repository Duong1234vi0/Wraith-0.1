/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public record InventoryResult(int slot, boolean found, class_1799 stack) {
    private static final InventoryResult NOT_FOUND_RESULT = new InventoryResult(-1, false, null);

    public static InventoryResult notFound() {
        return NOT_FOUND_RESULT;
    }

    @NotNull
    public static InventoryResult inOffhand(class_1799 stack) {
        return new InventoryResult(999, true, stack);
    }

    public boolean isHolding() {
        if (QuickImports.mc.field_1724 == null) {
            return false;
        }
        return QuickImports.mc.field_1724.method_31548().field_7545 == this.slot;
    }

    public boolean isInHotBar() {
        return this.slot < 9;
    }

    public boolean isInLeftHand() {
        return (this.slot > 36 ? this.slot : this.slot - 36) == 40;
    }

    public void switchTo() {
        if (this.found && this.isInHotBar()) {
            InventoryToolkit.switchTo(this.slot);
        }
    }

    public void switchToSilent() {
        if (this.found && this.isInHotBar()) {
            InventoryToolkit.switchToSilent(this.slot);
        }
    }
}
