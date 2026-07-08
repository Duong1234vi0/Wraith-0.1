/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.interpolation;

import code.alphadlc.features.impl.combat.components.interpolation.BestPoint;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class AimPoint
implements QuickImports {
    private static double lockedYOffset = 0.55;
    private static class_243 smoothed = null;
    private static int lastId = -1;

    public static class_243 get(class_1309 target) {
        if (target == null) {
            return null;
        }
        class_238 box = target.method_5829();
        class_243 center = box.method_1005();
        double distance = AimPoint.mc.field_1724.method_33571().method_1022(center) + 2.0;
        class_243 chosen = BestPoint.getMultipoint((class_1297)target, distance);
        if (chosen == null) {
            chosen = center;
        }
        class_243 visible = new class_243(class_3532.method_15350((double)chosen.field_1352, (double)box.field_1323, (double)box.field_1320), class_3532.method_15350((double)chosen.field_1351, (double)box.field_1322, (double)box.field_1325), class_3532.method_15350((double)chosen.field_1350, (double)box.field_1321, (double)box.field_1324));
        int id = target.method_5628();
        smoothed = smoothed == null || id != lastId || smoothed.method_1022(visible) > 4.0 ? visible : smoothed.method_1019(visible.method_1020(smoothed).method_1021(0.2));
        lastId = id;
        return smoothed;
    }

    public static void reset() {
        lockedYOffset = 0.55;
        smoothed = null;
        lastId = -1;
    }
}
