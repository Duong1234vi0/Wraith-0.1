/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.player.autoMine.region.states;

import code.alphadlc.features.impl.player.autoMine.region.utils.RandStack;
import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class RegionState
implements QuickImports {
    private final class_2338 min;
    private final class_2338 max;
    private final RandStack<class_2338> randStack = new RandStack();

    public RegionState(class_2338 min, class_2338 max) {
        this.min = min;
        this.max = max;
    }

    public void eachBlocks(Predicate<class_2680> predicate, Consumer<class_2338> consumer) {
        for (class_2338 blockPos : class_2338.method_10097((class_2338)this.min, (class_2338)this.max)) {
            class_2680 blockState = WorldUtility.fromBlockPos(blockPos);
            if (!predicate.test(blockState)) continue;
            consumer.accept(blockPos.method_10062());
        }
    }

    public void eachBlocksFromPlayer(class_2338 playerPos, Predicate<class_2680> predicate, Consumer<class_2338> consumer) {
        int maxRadius = Math.max(Math.max(Math.abs(this.max.method_10263() - this.min.method_10263()), Math.abs(this.max.method_10264() - this.min.method_10264())), Math.abs(this.max.method_10260() - this.min.method_10260())) + 1;
        for (int radius = 0; radius <= maxRadius; ++radius) {
            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dy = -radius; dy <= radius; ++dy) {
                    for (int dz = -radius; dz <= radius; ++dz) {
                        class_2338 blockPos;
                        class_2680 blockState;
                        if (Math.abs(dx) != radius && Math.abs(dy) != radius && Math.abs(dz) != radius) continue;
                        int x = playerPos.method_10263() + dx;
                        int y = playerPos.method_10264() + dy;
                        int z = playerPos.method_10260() + dz;
                        if (x < this.min.method_10263() || x > this.max.method_10263() || y < this.min.method_10264() || y > this.max.method_10264() || z < this.min.method_10260() || z > this.max.method_10260() || !predicate.test(blockState = WorldUtility.fromBlockPos(blockPos = new class_2338(x, y, z)))) continue;
                        consumer.accept(blockPos.method_10062());
                    }
                }
            }
        }
    }

    public List<class_2338> findBlocksSortedByDistance(class_2338 playerPos, Predicate<class_2680> predicate) {
        ArrayList<class_2338> blocks = new ArrayList<class_2338>();
        for (class_2338 blockPos : class_2338.method_10097((class_2338)this.min, (class_2338)this.max)) {
            class_2680 blockState = WorldUtility.fromBlockPos(blockPos);
            if (!predicate.test(blockState)) continue;
            blocks.add(blockPos.method_10062());
        }
        blocks.sort(Comparator.comparingDouble(arg_0 -> ((class_2338)playerPos).method_10262(arg_0)));
        return blocks;
    }

    public boolean inState(class_2338 blockPos) {
        return blockPos.method_10263() >= this.min.method_10263() && blockPos.method_10263() <= this.max.method_10263() && blockPos.method_10264() >= this.min.method_10264() && blockPos.method_10264() <= this.max.method_10264() && blockPos.method_10260() >= this.min.method_10260() && blockPos.method_10260() <= this.max.method_10260();
    }

    public class_238 asBox() {
        return new class_238(new class_243((double)this.min.method_10263(), (double)this.min.method_10264(), (double)this.min.method_10260()), new class_243((double)this.max.method_10263(), (double)this.max.method_10264(), (double)this.max.method_10260()));
    }

    public class_2338 getCenter() {
        return new class_2338((this.min.method_10263() + this.max.method_10263()) / 2, (this.min.method_10264() + this.max.method_10264()) / 2, (this.min.method_10260() + this.max.method_10260()) / 2);
    }

    public class_243 getCenterVec() {
        return new class_243((double)(this.min.method_10263() + this.max.method_10263()) / 2.0, (double)(this.min.method_10264() + this.max.method_10264()) / 2.0, (double)(this.min.method_10260() + this.max.method_10260()) / 2.0);
    }

    public class_2338 getMin() {
        return this.min;
    }

    public class_2338 getMax() {
        return this.max;
    }

    public RandStack<class_2338> getRandStack() {
        return this.randStack;
    }
}
