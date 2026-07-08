/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.player.autoMine.region.utils;

import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class WorldUtility
implements QuickImports {
    public static class_2680 fromBlockPos(class_2338 blockPos) {
        return WorldUtility.mc.field_1724.method_37908().method_8320(blockPos);
    }

    public static double horizontalDistance(class_243 from, class_243 to) {
        return to.method_1020(from).method_37267();
    }

    public static double horizontalDistance(class_2338 from, class_2338 to) {
        return WorldUtility.horizontalDistance(class_243.method_24953((class_2382)from), class_243.method_24953((class_2382)to));
    }

    public static double verticalDistance(class_2338 from, class_2338 to) {
        return class_243.method_24953((class_2382)to).method_1020(class_243.method_24953((class_2382)from)).method_10214();
    }
}
