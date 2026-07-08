/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 */
package code.alphadlc.features.impl.misc.newAutoMine;

import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_2338;
import net.minecraft.class_238;

public class RegionState {
    final class_2338 min;
    final class_2338 max;

    public RegionState(class_2338 min, class_2338 max) {
        this.min = min;
        this.max = max;
    }

    public class_238 getBox() {
        return new class_238((double)this.min.method_10263(), (double)this.min.method_10264(), (double)this.min.method_10260(), (double)this.max.method_10263(), (double)this.max.method_10264(), (double)this.max.method_10260());
    }

    public boolean inRegion(class_2338 blockPos) {
        float minX = this.min.method_10263();
        float minY = this.min.method_10264();
        float minZ = this.min.method_10260();
        float maxX = this.max.method_10263();
        float maxY = this.max.method_10264();
        float maxZ = this.max.method_10260();
        return (float)blockPos.method_10263() >= minX && (float)blockPos.method_10264() >= minY && (float)blockPos.method_10260() >= minZ && (float)blockPos.method_10263() <= maxX && (float)blockPos.method_10264() <= maxY && (float)blockPos.method_10260() <= maxZ;
    }

    public List<class_2338> eachBlocks(Predicate<class_2338> predicate) {
        ArrayList<class_2338> list = new ArrayList<class_2338>();
        class_2338.method_10097((class_2338)this.min, (class_2338)this.max).forEach(blockPos -> {
            if (predicate.test((class_2338)blockPos)) {
                list.add((class_2338)blockPos);
            }
        });
        return list;
    }

    public double distanceTo(class_2338 blockPos) {
        return Math.min(WorldUtility.horizontalDistance(blockPos, this.min), WorldUtility.horizontalDistance(blockPos, this.max));
    }
}
