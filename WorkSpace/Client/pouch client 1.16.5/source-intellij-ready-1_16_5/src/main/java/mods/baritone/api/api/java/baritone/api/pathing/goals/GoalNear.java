/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleIterator
 *  it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.interfaces.IGoalRenderPos;

public class GoalNear
implements Goal,
IGoalRenderPos {
    private final int x;
    private final int y;
    private final int z;
    private final int rangeSq;

    public GoalNear(c_1514_x pos, int range) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.rangeSq = range * range;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        int xDiff = x - this.x;
        int yDiff = y - this.y;
        int zDiff = z - this.z;
        return xDiff * xDiff + yDiff * yDiff + zDiff * zDiff <= this.rangeSq;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        int xDiff = x - this.x;
        int yDiff = y - this.y;
        int zDiff = z - this.z;
        return GoalBlock.calculate(xDiff, yDiff, zDiff);
    }

    @Override
    public double heuristic() {
        int range = (int)Math.ceil(Math.sqrt(this.rangeSq));
        DoubleOpenHashSet maybeAlwaysInside = new DoubleOpenHashSet();
        double minOutside = Double.POSITIVE_INFINITY;
        for (int dx = -range; dx <= range; ++dx) {
            for (int dy = -range; dy <= range; ++dy) {
                for (int dz = -range; dz <= range; ++dz) {
                    double h = this.heuristic(this.x + dx, this.y + dy, this.z + dz);
                    if (h < minOutside && this.isInGoal(this.x + dx, this.y + dy, this.z + dz)) {
                        maybeAlwaysInside.add(h);
                        continue;
                    }
                    minOutside = Math.min(minOutside, h);
                }
            }
        }
        double maxInside = Double.NEGATIVE_INFINITY;
        DoubleIterator it = maybeAlwaysInside.iterator();
        while (it.hasNext()) {
            double inside = it.nextDouble();
            if (!(inside < minOutside)) continue;
            maxInside = Math.max(maxInside, inside);
        }
        return maxInside;
    }

    @Override
    public c_1514_x getGoalPos() {
        return new c_1514_x(this.x, this.y, this.z);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalNear goal = (GoalNear)o;
        return this.x == goal.x && this.y == goal.y && this.z == goal.z && this.rangeSq == goal.rangeSq;
    }

    public int hashCode() {
        return (int)BetterBlockPos.longHash(this.x, this.y, this.z) + this.rangeSq;
    }

    public String toString() {
        return String.format("GoalNear{x=%s, y=%s, z=%s, rangeSq=%d}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z), this.rangeSq);
    }
}

