/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1531
 */
package code.alphadlc.features.impl.player.autoMine.mine.state;

import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1531;

public class MineState
implements QuickImports {
    private long duration;

    public void onTick() {
        this.duration = MineState.getMineDuration();
    }

    public static long getMineDuration() {
        for (class_1297 entity : MineState.mc.field_1687.method_18112()) {
            int id;
            class_1297 nextArmorEntity;
            class_1531 armorStandEntity;
            String string;
            if (!(entity instanceof class_1531) || !(string = (armorStandEntity = (class_1531)entity).method_5477().getString()).contains("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0435\u0440\u0435\u0437") || (nextArmorEntity = MineState.mc.field_1687.method_8469((id = armorStandEntity.method_5628()) + 1)) == null) continue;
            String duration = nextArmorEntity.method_5477().getString().replaceAll("[^0-9:]", "");
            String[] mmss = duration.split(":");
            if (mmss.length > 1) {
                return (long)Integer.parseInt(mmss[0]) * 60L + (long)Integer.parseInt(mmss[1]);
            }
            return Integer.parseInt(mmss[0]);
        }
        return 0L;
    }

    public long getDuration() {
        return this.duration;
    }
}
