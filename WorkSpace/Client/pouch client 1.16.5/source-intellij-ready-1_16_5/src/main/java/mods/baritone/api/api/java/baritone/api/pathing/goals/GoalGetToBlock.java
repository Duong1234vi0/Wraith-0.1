/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.interfaces.IGoalRenderPos;

public class GoalGetToBlock
implements Goal,
IGoalRenderPos {
    public final int x;
    public final int y;
    public final int z;

    public GoalGetToBlock(c_1514_x pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    @Override
    public c_1514_x getGoalPos() {
        return new c_1514_x(this.x, this.y, this.z);
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        int xDiff = x - this.x;
        int yDiff = y - this.y;
        int zDiff = z - this.z;
        return Math.abs(xDiff) + Math.abs(yDiff < 0 ? yDiff + 1 : yDiff) + Math.abs(zDiff) <= 1;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        int xDiff = x - this.x;
        int yDiff = y - this.y;
        int zDiff = z - this.z;
        return GoalBlock.calculate(xDiff, yDiff < 0 ? yDiff + 1 : yDiff, zDiff);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalGetToBlock goal = (GoalGetToBlock)o;
        return this.x == goal.x && this.y == goal.y && this.z == goal.z;
    }

    public int hashCode() {
        return (int)BetterBlockPos.longHash(this.x, this.y, this.z) * -49639096;
    }

    public String toString() {
        return String.format("GoalGetToBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
    }
}

