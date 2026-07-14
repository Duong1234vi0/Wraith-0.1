/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import java.util.Objects;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;

public class GoalInverted
implements Goal {
    public final Goal origin;

    public GoalInverted(Goal origin) {
        this.origin = origin;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return false;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        return -this.origin.heuristic(x, y, z);
    }

    @Override
    public double heuristic() {
        return Double.NEGATIVE_INFINITY;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalInverted goal = (GoalInverted)o;
        return Objects.equals(this.origin, goal.origin);
    }

    public int hashCode() {
        return this.origin.hashCode() * 495796690;
    }

    public String toString() {
        return String.format("GoalInverted{%s}", this.origin.toString());
    }
}

