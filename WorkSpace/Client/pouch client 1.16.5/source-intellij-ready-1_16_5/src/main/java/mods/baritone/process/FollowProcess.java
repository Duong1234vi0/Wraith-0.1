/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.N_4263_v;
import lightning.product.c_1514_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalNear;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.process.IFollowProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.utils.BaritoneProcessHelper;

public final class FollowProcess
extends BaritoneProcessHelper
implements IFollowProcess {
    private Predicate<N_4263_v> filter;
    private List<N_4263_v> cache;

    public FollowProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        this.scanWorld();
        GoalComposite goal = new GoalComposite((Goal[])this.cache.stream().map(this::towards).toArray(Goal[]::new));
        return new PathingCommand(goal, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
    }

    private Goal towards(N_4263_v following) {
        c_1514_x pos;
        if ((Double)Baritone.settings().followOffsetDistance.value == 0.0) {
            pos = following.b_2312_j();
        } else {
            GoalXZ g = GoalXZ.fromDirection(following.s_4990_V(), ((Float)Baritone.settings().followOffsetDirection.value).floatValue(), (Double)Baritone.settings().followOffsetDistance.value);
            pos = new c_1514_x((double)g.getX(), following.s_4990_V().R_4764_Y, (double)g.getZ());
        }
        return new GoalNear(pos, (Integer)Baritone.settings().followRadius.value);
    }

    private boolean followable(N_4263_v entity) {
        if (entity == null) {
            return false;
        }
        if (!entity.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (entity.equals(this.ctx.player())) {
            return false;
        }
        return this.ctx.entitiesStream().anyMatch(entity::equals);
    }

    private void scanWorld() {
        this.cache = this.ctx.entitiesStream().filter(this::followable).filter(this.filter).distinct().collect(Collectors.toList());
    }

    @Override
    public boolean isActive() {
        if (this.filter == null) {
            return false;
        }
        this.scanWorld();
        return !this.cache.isEmpty();
    }

    @Override
    public void onLostControl() {
        this.filter = null;
        this.cache = null;
    }

    @Override
    public String displayName0() {
        return "Following " + String.valueOf(this.cache);
    }

    @Override
    public void follow(Predicate<N_4263_v> filter) {
        this.filter = filter;
    }

    @Override
    public List<N_4263_v> following() {
        return this.cache;
    }

    @Override
    public Predicate<N_4263_v> currentFilter() {
        return this.filter;
    }
}


