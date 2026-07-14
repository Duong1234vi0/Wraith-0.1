/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;

public interface ICustomGoalProcess
extends IBaritoneProcess {
    public void setGoal(Goal var1);

    public void path();

    public Goal getGoal();

    default public void setGoalAndPath(Goal goal) {
        this.setGoal(goal);
        this.path();
    }
}

