/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;

public class GoalStrictDirection
implements Goal {
    public final int x;
    public final int y;
    public final int z;
    public final int dx;
    public final int dz;

    public GoalStrictDirection(c_1514_x origin, b_257_Y direction) {
        this.x = origin.getX();
        this.y = origin.getY();
        this.z = origin.getZ();
        this.dx = direction.t_148_a();
        this.dz = direction.u_2550_I();
        if (this.dx == 0 && this.dz == 0) {
            throw new IllegalArgumentException(String.valueOf(direction));
        }
    }

    @Override
    public boolean isInGoal(int x, int y, int z) {
        return false;
    }

    @Override
    public double heuristic(int x, int y, int z) {
        int distanceFromStartInDesiredDirection = (x - this.x) * this.dx + (z - this.z) * this.dz;
        int distanceFromStartInIncorrectDirection = Math.abs((x - this.x) * this.dz) + Math.abs((z - this.z) * this.dx);
        int verticalDistanceFromStart = Math.abs(y - this.y);
        double heuristic = -distanceFromStartInDesiredDirection * 100;
        heuristic += (double)(distanceFromStartInIncorrectDirection * 1000);
        return heuristic += (double)(verticalDistanceFromStart * 1000);
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
        GoalStrictDirection goal = (GoalStrictDirection)o;
        return this.x == goal.x && this.y == goal.y && this.z == goal.z && this.dx == goal.dx && this.dz == goal.dz;
    }

    public int hashCode() {
        int hash = (int)BetterBlockPos.longHash(this.x, this.y, this.z);
        hash = hash * 630627507 + this.dx;
        hash = hash * -283028380 + this.dz;
        return hash;
    }

    public String toString() {
        return String.format("GoalStrictDirection{x=%s, y=%s, z=%s, dx=%s, dz=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z), SettingsUtil.maybeCensor(this.dx), SettingsUtil.maybeCensor(this.dz));
    }
}

