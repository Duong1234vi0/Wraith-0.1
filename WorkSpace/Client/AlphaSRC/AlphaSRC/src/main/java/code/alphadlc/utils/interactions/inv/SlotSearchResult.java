/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.HotbarUtil;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public record SlotSearchResult(int slot, boolean found, class_1799 stack) implements QuickImports
{
    private static final SlotSearchResult NOT_FOUND_RESULT = new SlotSearchResult(-1, false, class_1799.field_8037);

    public static SlotSearchResult notFound() {
        return NOT_FOUND_RESULT;
    }

    @NotNull
    public static SlotSearchResult inOffhand(class_1799 stack) {
        return new SlotSearchResult(999, true, stack);
    }

    public boolean isHolding() {
        if (SlotSearchResult.mc.field_1724 == null) {
            return false;
        }
        return this.isOffhand() || SlotSearchResult.mc.field_1724.method_31548().field_7545 == this.slot;
    }

    public boolean isOffhand() {
        return this.slot == 999;
    }

    public boolean isInHotBar() {
        return this.slot >= 0 && this.slot < 9;
    }

    public void switchTo() {
        if (this.found && this.isInHotBar()) {
            HotbarUtil.switchTo(this.slot);
        }
    }

    public void switchToSilent() {
        if (this.found && this.isInHotBar()) {
            HotbarUtil.switchToSilent(this.slot);
        }
    }
}
