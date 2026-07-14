/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import java.util.Arrays;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;

public class GoalComposite
implements Goal {
    private final Goal[] goals;

    public GoalComposite(Goal ... goals) {
        this.goals = goals;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        for (Goal goal : this.goals) {
            if (!goal.isInGoal(x, y, z)) continue;
            return true;
        }
        return false;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        double min = Double.MAX_VALUE;
        for (Goal g : this.goals) {
            min = Math.min(min, g.heuristic(x, y, z));
        }
        return min;
    }

    @Override
    public double heuristic() {
        double min = Double.MAX_VALUE;
        for (Goal g : this.goals) {
            min = Math.min(min, g.heuristic());
        }
        return min;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalComposite goal = (GoalComposite)o;
        return Arrays.equals(this.goals, goal.goals);
    }

    public int hashCode() {
        return Arrays.hashCode(this.goals);
    }

    public String toString() {
        return "GoalComposite" + Arrays.toString(this.goals);
    }

    public Goal[] goals() {
        return this.goals;
    }
}

