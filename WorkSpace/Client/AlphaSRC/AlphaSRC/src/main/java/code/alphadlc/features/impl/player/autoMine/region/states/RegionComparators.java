/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package code.alphadlc.features.impl.player.autoMine.region.states;

import code.alphadlc.features.impl.player.autoMine.region.states.RegionBlockMaps;
import code.alphadlc.features.impl.player.autoMine.region.states.RegionBlockState;
import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Comparator;
import net.minecraft.class_2338;

public class RegionComparators
implements QuickImports {
    public static final Comparator<class_2338> BY_DISTANCE_COMPARATOR = (a, b) -> Double.compare(WorldUtility.horizontalDistance(RegionComparators.mc.field_1724.method_24515(), a), WorldUtility.horizontalDistance(RegionComparators.mc.field_1724.method_24515(), b));
    public static final Comparator<class_2338> BY_PRIORITY_COMPARATOR = (a, b) -> {
        RegionBlockState aState = RegionBlockMaps.IMMUTABLE_BLOCK_MAP.get(WorldUtility.fromBlockPos(a).method_26204());
        RegionBlockState bState = RegionBlockMaps.IMMUTABLE_BLOCK_MAP.get(WorldUtility.fromBlockPos(b).method_26204());
        if (aState == null && bState == null) {
            return 0;
        }
        if (aState == null) {
            return 1;
        }
        if (bState == null) {
            return -1;
        }
        return Integer.compare(aState.priority(), bState.priority());
    };

    public static double score(class_2338 blockPos) {
        class_2338 playerPos = RegionComparators.mc.field_1724.method_24515();
        double horizontal = WorldUtility.horizontalDistance(playerPos, blockPos);
        double vertical = Math.abs(WorldUtility.verticalDistance(playerPos, blockPos));
        RegionBlockState state = RegionBlockMaps.IMMUTABLE_BLOCK_MAP.get(WorldUtility.fromBlockPos(blockPos).method_26204());
        int priority = state != null ? state.priority() : 100;
        return horizontal + vertical * 0.5 + (double)priority * 2.0;
    }
}
