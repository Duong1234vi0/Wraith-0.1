/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import lightning.product.e_2866_D;
import lightning.product.u_530_F;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;

public class GoalXZ
implements Goal {
    private static final double SQRT_2 = Math.sqrt(2.0);
    private final int x;
    private final int z;

    public GoalXZ(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public GoalXZ(BetterBlockPos pos) {
        this.x = pos.x;
        this.z = pos.z;
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return x == this.x && z == this.z;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        int xDiff = x - this.x;
        int zDiff = z - this.z;
        return GoalXZ.calculate(xDiff, zDiff);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GoalXZ goal = (GoalXZ)o;
        return this.x == goal.x && this.z == goal.z;
    }

    public int hashCode() {
        int hash = 1791873246;
        hash = hash * 222601791 + this.x;
        hash = hash * -1331679453 + this.z;
        return hash;
    }

    public String toString() {
        return String.format("GoalXZ{x=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.z));
    }

    public static double calculate(double xDiff, double zDiff) {
        double straight;
        double z;
        double x = Math.abs(xDiff);
        if (x < (z = Math.abs(zDiff))) {
            straight = z - x;
            diagonal = x;
        } else {
            straight = x - z;
            diagonal = z;
        }
        return ((diagonal *= SQRT_2) + straight) * (Double)BaritoneAPI.getSettings().costHeuristic.value;
    }

    public static GoalXZ fromDirection(e_2866_D origin, float yaw, double distance) {
        float theta = (float)Math.toRadians(yaw);
        double x = origin.J_1907_R - (double)u_530_F.n_1700_B(theta) * distance;
        double z = origin.G_564_y + (double)u_530_F.J_1907_R(theta) * distance;
        return new GoalXZ(u_530_F.R_4764_Y(x), u_530_F.R_4764_Y(z));
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }
}

