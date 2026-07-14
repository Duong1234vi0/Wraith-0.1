/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.goals;

import lightning.product.c_1514_x;

public interface Goal {
    public boolean isInGoal(int var1, int var2, int var3);

    public double heuristic(int var1, int var2, int var3);

    default public boolean isInGoal(c_1514_x pos) {
        return this.isInGoal(pos.getX(), pos.getY(), pos.getZ());
    }

    default public double heuristic(c_1514_x pos) {
        return this.heuristic(pos.getX(), pos.getY(), pos.getZ());
    }

    default public double heuristic() {
        return 0.0;
    }
}

