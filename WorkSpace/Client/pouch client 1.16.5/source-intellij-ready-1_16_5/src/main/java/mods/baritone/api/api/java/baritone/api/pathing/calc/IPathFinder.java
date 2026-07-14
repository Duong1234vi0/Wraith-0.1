/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.calc;

import java.util.Optional;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPath;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.utils.PathCalculationResult;

public interface IPathFinder {
    public Goal getGoal();

    public PathCalculationResult calculate(long var1, long var3);

    public boolean isFinished();

    public Optional<IPath> pathToMostRecentNodeConsidered();

    public Optional<IPath> bestPathSoFar();
}

