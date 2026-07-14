/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.movement.ActionCosts;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;

public class GoalYLevel
implements Goal,
ActionCosts {
    public final int level;

    public GoalYLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return y == this.level;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        return GoalYLevel.calculate(this.level, y);
    }

    public static double calculate(int goalY, int currentY) {
        if (currentY > goalY) {
            return FALL_N_BLOCKS_COST[2] / 2.0 * (double)(currentY - goalY);
        }
        if (currentY < goalY) {
            return (double)(goalY - currentY) * JUMP_ONE_BLOCK_COST;
        }
        return 0.0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalYLevel goal = (GoalYLevel)o;
        return this.level == goal.level;
    }

    public int hashCode() {
        return this.level * 1271009915;
    }

    public String toString() {
        return String.format("GoalYLevel{y=%s}", SettingsUtil.maybeCensor(this.level));
    }
}

