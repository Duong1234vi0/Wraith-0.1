/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.interfaces.IGoalRenderPos;

public class GoalBlock
implements Goal,
IGoalRenderPos {
    public final int x;
    public final int y;
    public final int z;

    public GoalBlock(c_1514_x pos) {
        this(pos.getX(), pos.getY(), pos.getZ());
    }

    public GoalBlock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return x == this.x && y == this.y && z == this.z;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        int xDiff = x - this.x;
        int yDiff = y - this.y;
        int zDiff = z - this.z;
        return GoalBlock.calculate(xDiff, yDiff, zDiff);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalBlock goal = (GoalBlock)o;
        return this.x == goal.x && this.y == goal.y && this.z == goal.z;
    }

    public int hashCode() {
        return (int)BetterBlockPos.longHash(this.x, this.y, this.z) * 905165533;
    }

    public String toString() {
        return String.format("GoalBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
    }

    @Override
    public c_1514_x getGoalPos() {
        return new c_1514_x(this.x, this.y, this.z);
    }

    public static double calculate(double xDiff, int yDiff, double zDiff) {
        double heuristic = 0.0;
        heuristic += GoalYLevel.calculate(0, yDiff);
        return heuristic += GoalXZ.calculate(xDiff, zDiff);
    }
}

