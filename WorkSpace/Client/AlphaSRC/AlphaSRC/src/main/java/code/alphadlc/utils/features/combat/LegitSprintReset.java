/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package code.alphadlc.utils.features.combat;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.main.listener.impl.EventListener;
import code.alphadlc.mixins.ClientPlayerEntityAccessor;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2848;

public final class LegitSprintReset
implements QuickImports {
    private static boolean pendingInputReset;

    private LegitSprintReset() {
    }

    public static boolean isServerSprinting() {
        if (LegitSprintReset.mc.field_1724 == null) {
            return false;
        }
        return EventListener.serverSprint && !LegitSprintReset.mc.field_1724.method_6128() && !LegitSprintReset.mc.field_1724.method_5799();
    }

    public static boolean prepareForAttack() {
        if (LegitSprintReset.mc.field_1724 == null) {
            return true;
        }
        if (LegitSprintReset.mc.field_1724.method_5869() || LegitSprintReset.mc.field_1724.method_5799() || LegitSprintReset.mc.field_1724.method_5681() || LegitSprintReset.mc.field_1724.method_6128()) {
            return true;
        }
        ClientPlayerEntityAccessor accessor = (ClientPlayerEntityAccessor)LegitSprintReset.mc.field_1724;
        LegitSprintReset.mc.field_1690.field_1867.method_23481(false);
        if (LegitSprintReset.mc.field_1724.method_5624()) {
            LegitSprintReset.mc.field_1724.method_5728(false);
        }
        if (LegitSprintReset.mc.field_1724.method_24828()) {
            if (accessor.getLastSprinting()) {
                pendingInputReset = true;
                return false;
            }
            return true;
        }
        if (accessor.getLastSprinting() && LegitSprintReset.mc.field_1724.field_3944 != null) {
            accessor.setLastSprinting(false);
            LegitSprintReset.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)LegitSprintReset.mc.field_1724, class_2848.class_2849.field_12985));
        }
        return !LegitSprintReset.mc.field_1724.method_5624();
    }

    public static void applyInputReset(InputEvent event) {
        if (!pendingInputReset || LegitSprintReset.mc.field_1724 == null || !LegitSprintReset.mc.field_1724.method_24828()) {
            return;
        }
        event.setForward(0.0f);
        event.setStrafe(0.0f);
        pendingInputReset = false;
    }

    public static void reset() {
        pendingInputReset = false;
    }
}
