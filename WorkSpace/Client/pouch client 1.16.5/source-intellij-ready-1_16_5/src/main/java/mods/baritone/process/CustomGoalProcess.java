/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.process.ICustomGoalProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.utils.BaritoneProcessHelper;

public final class CustomGoalProcess
extends BaritoneProcessHelper
implements ICustomGoalProcess {
    private Goal goal;
    private State state;

    public CustomGoalProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void setGoal(Goal goal) {
        this.goal = goal;
        if (this.state == State.NONE) {
            this.state = State.GOAL_SET;
        }
        if (this.state == State.EXECUTING) {
            this.state = State.PATH_REQUESTED;
        }
    }

    @Override
    public void path() {
        this.state = State.PATH_REQUESTED;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }

    @Override
    public boolean isActive() {
        return this.state != State.NONE;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        switch (this.state.ordinal()) {
            case 1: {
                return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
            }
            case 2: {
                PathingCommand ret = new PathingCommand(this.goal, PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
                this.state = State.EXECUTING;
                return ret;
            }
            case 3: {
                if (calcFailed) {
                    this.onLostControl();
                    return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
                }
                if (this.goal == null || this.goal.isInGoal(this.ctx.playerFeet()) && this.goal.isInGoal(this.baritone.getPathingBehavior().pathStart())) {
                    this.onLostControl();
                    if (((Boolean)Baritone.settings().disconnectOnArrival.value).booleanValue()) {
                        this.ctx.world().w_1484_f();
                    }
                    if (((Boolean)Baritone.settings().notificationOnPathComplete.value).booleanValue()) {
                        this.logNotification("Pathing complete", false);
                    }
                    return new PathingCommand(this.goal, PathingCommandType.CANCEL_AND_SET_GOAL);
                }
                return new PathingCommand(this.goal, PathingCommandType.SET_GOAL_AND_PATH);
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public void onLostControl() {
        this.state = State.NONE;
        this.goal = null;
    }

    @Override
    public String displayName0() {
        return "Custom Goal " + String.valueOf(this.goal);
    }

    protected static enum State {
        NONE,
        GOAL_SET,
        PATH_REQUESTED,
        EXECUTING;

    }
}

