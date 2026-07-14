/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;

public class GoalAxis
implements Goal {
    private static final double SQRT_2_OVER_2 = Math.sqrt(2.0) / 2.0;

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return y == (Integer)BaritoneAPI.getSettings().axisHeight.value && (x == 0 || z == 0 || Math.abs(x) == Math.abs(z));
    }

    @Override
    public double heuristic(int x0, int y, int z0) {
        int x = Math.abs(x0);
        int z = Math.abs(z0);
        int shrt = Math.min(x, z);
        int lng = Math.max(x, z);
        int diff = lng - shrt;
        double flatAxisDistance = Math.min((double)x, Math.min((double)z, (double)diff * SQRT_2_OVER_2));
        return flatAxisDistance * (Double)BaritoneAPI.getSettings().costHeuristic.value + GoalYLevel.calculate((Integer)BaritoneAPI.getSettings().axisHeight.value, y);
    }

    public boolean equals(Object o) {
        return o.getClass() == GoalAxis.class;
    }

    public int hashCode() {
        return 201385781;
    }

    public String toString() {
        return "GoalAxis";
    }
}

