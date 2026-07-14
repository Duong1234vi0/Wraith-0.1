/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.behavior;

import java.util.Optional;
import mods.baritone.api.api.java.baritone.api.behavior.IBehavior;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPath;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPathFinder;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.path.IPathExecutor;

public interface IPathingBehavior
extends IBehavior {
    default public Optional<Double> ticksRemainingInSegment() {
        return this.ticksRemainingInSegment(true);
    }

    default public Optional<Double> ticksRemainingInSegment(boolean includeCurrentMovement) {
        IPathExecutor current = this.getCurrent();
        if (current == null) {
            return Optional.empty();
        }
        int start = includeCurrentMovement ? current.getPosition() : current.getPosition() + 1;
        return Optional.of(current.getPath().ticksRemainingFrom(start));
    }

    public Optional<Double> estimatedTicksToGoal();

    public Goal getGoal();

    public boolean isPathing();

    default public boolean hasPath() {
        return this.getCurrent() != null;
    }

    public boolean cancelEverything();

    public void forceCancel();

    default public Optional<IPath> getPath() {
        return Optional.ofNullable(this.getCurrent()).map(IPathExecutor::getPath);
    }

    public Optional<? extends IPathFinder> getInProgress();

    public IPathExecutor getCurrent();

    public IPathExecutor getNext();
}

