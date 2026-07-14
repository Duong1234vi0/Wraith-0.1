/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.y_6_Q;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalGetToBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalRunAway;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalTwoBlocks;
import mods.baritone.api.api.java.baritone.api.process.IGetToBlockProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.process.MineProcess;
import mods.baritone.utils.BaritoneProcessHelper;

public final class GetToBlockProcess
extends BaritoneProcessHelper
implements IGetToBlockProcess {
    private BlockOptionalMeta gettingTo;
    private List<c_1514_x> knownLocations;
    private List<c_1514_x> blacklist;
    private c_1514_x start;
    private int tickCount = 0;
    private int arrivalTickCount = 0;

    public GetToBlockProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void getToBlock(BlockOptionalMeta block) {
        this.onLostControl();
        this.gettingTo = block;
        this.start = this.ctx.playerFeet();
        this.blacklist = new ArrayList<c_1514_x>();
        this.arrivalTickCount = 0;
        this.rescan(new ArrayList<c_1514_x>(), new GetToBlockCalculationContext(this, false));
    }

    @Override
    public boolean isActive() {
        return this.gettingTo != null;
    }

    @Override
    public synchronized PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        if (this.knownLocations == null) {
            this.rescan(new ArrayList<c_1514_x>(), new GetToBlockCalculationContext(this, false));
        }
        if (this.knownLocations.isEmpty()) {
            if (((Boolean)Baritone.settings().exploreForBlocks.value).booleanValue() && !calcFailed) {
                return new PathingCommand(new GoalRunAway(this, 1.0, new c_1514_x[]{this.start}){

                    @Override
                    public boolean isInGoal(int x, int y, int z) {
                        return false;
                    }

                    @Override
                    public double heuristic() {
                        return Double.NEGATIVE_INFINITY;
                    }
                }, PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
            }
            this.logDirect("No known locations of " + String.valueOf(this.gettingTo) + ", canceling GetToBlock");
            if (isSafeToCancel) {
                this.onLostControl();
            }
            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
        }
        GoalComposite goal = new GoalComposite((Goal[])this.knownLocations.stream().map(this::createGoal).toArray(Goal[]::new));
        if (calcFailed) {
            if (((Boolean)Baritone.settings().blacklistClosestOnFailure.value).booleanValue()) {
                this.logDirect("Unable to find any path to " + String.valueOf(this.gettingTo) + ", blacklisting presumably unreachable closest instances...");
                this.blacklistClosest();
                return this.onTick(false, isSafeToCancel);
            }
            this.logDirect("Unable to find any path to " + String.valueOf(this.gettingTo) + ", canceling GetToBlock");
            if (isSafeToCancel) {
                this.onLostControl();
            }
            return new PathingCommand(goal, PathingCommandType.CANCEL_AND_SET_GOAL);
        }
        int mineGoalUpdateInterval = (Integer)Baritone.settings().mineGoalUpdateInterval.value;
        if (mineGoalUpdateInterval != 0 && this.tickCount++ % mineGoalUpdateInterval == 0) {
            ArrayList<c_1514_x> current = new ArrayList<c_1514_x>(this.knownLocations);
            GetToBlockCalculationContext context = new GetToBlockCalculationContext(this, true);
            Baritone.getExecutor().execute(() -> this.rescan(current, context));
        }
        if (goal.isInGoal(this.ctx.playerFeet()) && goal.isInGoal(this.baritone.getPathingBehavior().pathStart()) && isSafeToCancel) {
            if (this.rightClickOnArrival(this.gettingTo.getBlock())) {
                if (this.rightClick()) {
                    this.onLostControl();
                    return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
                }
            } else {
                this.onLostControl();
                return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
            }
        }
        return new PathingCommand(goal, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
    }

    @Override
    public synchronized boolean blacklistClosest() {
        ArrayList<c_1514_x> newBlacklist = new ArrayList<c_1514_x>();
        this.knownLocations.stream().min(Comparator.comparingDouble(this.ctx.playerFeet()::distanceSq)).ifPresent(newBlacklist::add);
        block2: while (true) {
            block3: for (c_1514_x known : this.knownLocations) {
                for (c_1514_x blacklist : newBlacklist) {
                    if (!this.areAdjacent(known, blacklist)) continue;
                    newBlacklist.add(known);
                    this.knownLocations.remove(known);
                    continue block2;
                    continue block3;
                }
            }
            break;
        }
        switch (newBlacklist.size()) {
            default: 
        }
        this.logDebug("Blacklisting unreachable locations " + String.valueOf(newBlacklist));
        this.blacklist.addAll(newBlacklist);
        return !newBlacklist.isEmpty();
    }

    private boolean areAdjacent(c_1514_x posA, c_1514_x posB) {
        int diffZ;
        int diffY;
        int diffX = Math.abs(posA.getX() - posB.getX());
        return diffX + (diffY = Math.abs(posA.getY() - posB.getY())) + (diffZ = Math.abs(posA.getZ() - posB.getZ())) == 1;
    }

    @Override
    public synchronized void onLostControl() {
        this.gettingTo = null;
        this.knownLocations = null;
        this.start = null;
        this.blacklist = null;
        this.baritone.getInputOverrideHandler().clearAllKeys();
    }

    @Override
    public String displayName0() {
        if (this.knownLocations.isEmpty()) {
            return "Exploring randomly to find " + String.valueOf(this.gettingTo) + ", no known locations";
        }
        return "Get To " + String.valueOf(this.gettingTo) + ", " + this.knownLocations.size() + " known locations";
    }

    private synchronized void rescan(List<c_1514_x> known, CalculationContext context) {
        List<c_1514_x> positions = MineProcess.searchWorld(context, new BlockOptionalMetaLookup(this.gettingTo), 64, known, this.blacklist, Collections.emptyList());
        positions.removeIf(this.blacklist::contains);
        this.knownLocations = positions;
    }

    private Goal createGoal(c_1514_x pos) {
        if (this.walkIntoInsteadOfAdjacent(this.gettingTo.getBlock())) {
            return new GoalTwoBlocks(pos);
        }
        if (this.blockOnTopMustBeRemoved(this.gettingTo.getBlock()) && MovementHelper.isBlockNormalCube(this.baritone.bsi.get0(pos.up()))) {
            return new GoalBlock(pos.up());
        }
        return new GoalGetToBlock(pos);
    }

    private boolean rightClick() {
        for (c_1514_x pos : this.knownLocations) {
            Optional<Rotation> reachable = RotationUtils.reachable(this.ctx, pos, this.ctx.playerController().getBlockReachDistance());
            if (!reachable.isPresent()) continue;
            this.baritone.getLookBehavior().updateTarget(reachable.get(), true);
            if (this.knownLocations.contains(this.ctx.getSelectedBlock().orElse(null))) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
                System.out.println(this.ctx.player().H_1873_g);
                if (!(this.ctx.player().H_1873_g instanceof y_6_Q)) {
                    return true;
                }
            }
            if (this.arrivalTickCount++ > 20) {
                this.logDirect("Right click timed out");
                return true;
            }
            return false;
        }
        this.logDirect("Arrived but failed to right click open");
        return true;
    }

    private boolean walkIntoInsteadOfAdjacent(T_2915_h block) {
        if (!((Boolean)Baritone.settings().enterPortal.value).booleanValue()) {
            return false;
        }
        return block == a_3742_W.M_766_z;
    }

    private boolean rightClickOnArrival(T_2915_h block) {
        if (!((Boolean)Baritone.settings().rightClickContainerOnArrival.value).booleanValue()) {
            return false;
        }
        return block == a_3742_W.O_2934_T || block == a_3742_W.P_925_e || block == a_3742_W.k_2348_i || block == a_3742_W.L_1362_X || block == a_3742_W.NumberSetting;
    }

    private boolean blockOnTopMustBeRemoved(T_2915_h block) {
        if (!this.rightClickOnArrival(block)) {
            return false;
        }
        return block == a_3742_W.k_2348_i || block == a_3742_W.L_1362_X || block == a_3742_W.NumberSetting;
    }

    public class GetToBlockCalculationContext
    extends CalculationContext {
        public GetToBlockCalculationContext(GetToBlockProcess this$0, boolean forUseOnAnotherThread) {
            super(this$0.baritone, forUseOnAnotherThread);
        }

        @Override
        public double breakCostMultiplierAt(int x, int y, int z, K_4074_S current) {
            return 1.0;
        }
    }
}

