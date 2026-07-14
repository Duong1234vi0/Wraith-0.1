/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.k_4690_i;
import lightning.product.AirBlock;
import lightning.product.n_1494_c;
import lightning.product.v_1669_V;
import lightning.product.FallingBlock;
import lightning.product.z_3539_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalRunAway;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalTwoBlocks;
import mods.baritone.api.api.java.baritone.api.process.IMineProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.api.api.java.baritone.api.utils.BlockUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.cache.CachedChunk;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.utils.BaritoneProcessHelper;
import mods.baritone.utils.BlockStateInterface;

public final class MineProcess
extends BaritoneProcessHelper
implements IMineProcess {
    private static final int ORE_LOCATIONS_COUNT = 64;
    private BlockOptionalMetaLookup filter;
    private List<c_1514_x> knownOreLocations;
    private List<c_1514_x> blacklist;
    private Map<c_1514_x, Long> anticipatedDrops;
    private c_1514_x branchPoint;
    private GoalRunAway branchPointRunaway;
    private int desiredQuantity;
    private int tickCount;

    public MineProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        return this.filter != null;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        PathingCommand command;
        if (this.desiredQuantity > 0) {
            int curr = this.ctx.player().l_1268_F.n_1700_B.stream().filter(stack -> this.filter.has((Z_1993_T)stack)).mapToInt(Z_1993_T::t_4043_B).sum();
            System.out.println("Currently have " + curr + " valid items");
            if (curr >= this.desiredQuantity) {
                this.logDirect("Have " + curr + " valid items");
                this.cancel();
                return null;
            }
        }
        if (calcFailed) {
            if (!this.knownOreLocations.isEmpty() && ((Boolean)Baritone.settings().blacklistClosestOnFailure.value).booleanValue()) {
                this.logDirect("Unable to find any path to " + String.valueOf(this.filter) + ", blacklisting presumably unreachable closest instance...");
                if (((Boolean)Baritone.settings().notificationOnMineFail.value).booleanValue()) {
                    this.logNotification("Unable to find any path to " + String.valueOf(this.filter) + ", blacklisting presumably unreachable closest instance...", true);
                }
                this.knownOreLocations.stream().min(Comparator.comparingDouble(this.ctx.playerFeet()::distanceSq)).ifPresent(this.blacklist::add);
                this.knownOreLocations.removeIf(this.blacklist::contains);
            } else {
                this.logDirect("Unable to find any path to " + String.valueOf(this.filter) + ", canceling mine");
                if (((Boolean)Baritone.settings().notificationOnMineFail.value).booleanValue()) {
                    this.logNotification("Unable to find any path to " + String.valueOf(this.filter) + ", canceling mine", true);
                }
                this.cancel();
                return null;
            }
        }
        this.updateLoucaSystem();
        int mineGoalUpdateInterval = (Integer)Baritone.settings().mineGoalUpdateInterval.value;
        ArrayList<c_1514_x> curr = new ArrayList<c_1514_x>(this.knownOreLocations);
        if (mineGoalUpdateInterval != 0 && this.tickCount++ % mineGoalUpdateInterval == 0) {
            CalculationContext context = new CalculationContext(this.baritone, true);
            Baritone.getExecutor().execute(() -> this.rescan(curr, context));
        }
        if (((Boolean)Baritone.settings().legitMine.value).booleanValue() && !this.addNearby()) {
            this.cancel();
            return null;
        }
        Optional<c_1514_x> shaft = curr.stream().filter(pos -> pos.getX() == this.ctx.playerFeet().getX() && pos.getZ() == this.ctx.playerFeet().getZ()).filter(pos -> pos.getY() >= this.ctx.playerFeet().getY()).filter(pos -> !(BlockStateInterface.get(this.ctx, pos).J_1907_R() instanceof AirBlock)).min(Comparator.comparingDouble(this.ctx.playerFeet()::distanceSq));
        this.baritone.getInputOverrideHandler().clearAllKeys();
        if (shaft.isPresent() && this.ctx.player().M_1641_O()) {
            Optional<Rotation> rot;
            c_1514_x pos2 = shaft.get();
            K_4074_S state = this.baritone.bsi.get0(pos2);
            if (!MovementHelper.avoidBreaking(this.baritone.bsi, pos2.getX(), pos2.getY(), pos2.getZ(), state) && (rot = RotationUtils.reachable(this.ctx, pos2)).isPresent() && isSafeToCancel) {
                this.baritone.getLookBehavior().updateTarget(rot.get(), true);
                MovementHelper.switchToBestToolFor(this.ctx, this.ctx.world().getBlockState(pos2));
                if (this.ctx.isLookingAt(pos2) || this.ctx.playerRotations().isReallyCloseTo(rot.get())) {
                    this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_LEFT, true);
                }
                return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            }
        }
        if ((command = this.updateGoal()) == null) {
            this.cancel();
            return null;
        }
        return command;
    }

    private void updateLoucaSystem() {
        HashMap<c_1514_x, Long> copy = new HashMap<c_1514_x, Long>(this.anticipatedDrops);
        this.ctx.getSelectedBlock().ifPresent(pos -> {
            if (this.knownOreLocations.contains(pos)) {
                copy.put((c_1514_x)pos, System.currentTimeMillis() + (Long)Baritone.settings().mineDropLoiterDurationMSThanksLouca.value);
            }
        });
        for (c_1514_x pos2 : this.anticipatedDrops.keySet()) {
            if ((Long)copy.get(pos2) >= System.currentTimeMillis()) continue;
            copy.remove(pos2);
        }
        this.anticipatedDrops = copy;
    }

    @Override
    public void onLostControl() {
        this.mine(0, (BlockOptionalMetaLookup)null);
    }

    @Override
    public String displayName0() {
        return "Mine " + String.valueOf(this.filter);
    }

    private PathingCommand updateGoal() {
        BlockOptionalMetaLookup filter = this.filterFilter();
        if (filter == null) {
            return null;
        }
        boolean legit = (Boolean)Baritone.settings().legitMine.value;
        List<c_1514_x> locs = this.knownOreLocations;
        if (!locs.isEmpty()) {
            CalculationContext context = new CalculationContext(this.baritone);
            List<c_1514_x> locs2 = MineProcess.prune(context, new ArrayList<c_1514_x>(locs), filter, 64, this.blacklist, this.droppedItemsScan());
            GoalComposite goal = new GoalComposite((Goal[])locs2.stream().map(loc -> this.coalesce((c_1514_x)loc, locs2, context)).toArray(Goal[]::new));
            this.knownOreLocations = locs2;
            return new PathingCommand(goal, legit ? PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH : PathingCommandType.REVALIDATE_GOAL_AND_PATH);
        }
        if (!legit && !((Boolean)Baritone.settings().exploreForBlocks.value).booleanValue()) {
            return null;
        }
        int y = (Integer)Baritone.settings().legitMineYLevel.value;
        if (this.branchPoint == null) {
            this.branchPoint = this.ctx.playerFeet();
        }
        if (this.branchPointRunaway == null) {
            this.branchPointRunaway = new GoalRunAway(this, 1.0, y, new c_1514_x[]{this.branchPoint}){

                @Override
                public boolean isInGoal(int x, int y, int z) {
                    return false;
                }

                @Override
                public double heuristic() {
                    return Double.NEGATIVE_INFINITY;
                }
            };
        }
        return new PathingCommand(this.branchPointRunaway, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
    }

    private void rescan(List<c_1514_x> already, CalculationContext context) {
        BlockOptionalMetaLookup filter = this.filterFilter();
        if (filter == null) {
            return;
        }
        if (((Boolean)Baritone.settings().legitMine.value).booleanValue()) {
            return;
        }
        List<c_1514_x> dropped = this.droppedItemsScan();
        List<c_1514_x> locs = MineProcess.searchWorld(context, filter, 64, already, this.blacklist, dropped);
        locs.addAll(dropped);
        if (locs.isEmpty() && !((Boolean)Baritone.settings().exploreForBlocks.value).booleanValue()) {
            this.logDirect("No locations for " + String.valueOf(filter) + " known, cancelling");
            if (((Boolean)Baritone.settings().notificationOnMineFail.value).booleanValue()) {
                this.logNotification("No locations for " + String.valueOf(filter) + " known, cancelling", true);
            }
            this.cancel();
            return;
        }
        this.knownOreLocations = locs;
    }

    private boolean internalMiningGoal(c_1514_x pos, CalculationContext context, List<c_1514_x> locs) {
        if (locs.contains(pos)) {
            return true;
        }
        K_4074_S state = context.bsi.get0(pos);
        if (((Boolean)Baritone.settings().internalMiningAirException.value).booleanValue() && state.J_1907_R() instanceof AirBlock) {
            return true;
        }
        return this.filter.has(state) && MineProcess.plausibleToBreak(context, pos);
    }

    private Goal coalesce(c_1514_x loc, List<c_1514_x> locs, CalculationContext context) {
        boolean assumeVerticalShaftMine;
        boolean bl = assumeVerticalShaftMine = !(this.baritone.bsi.get0(loc.up()).J_1907_R() instanceof FallingBlock);
        if (!((Boolean)Baritone.settings().forceInternalMining.value).booleanValue()) {
            if (assumeVerticalShaftMine) {
                return new GoalThreeBlocks(loc);
            }
            return new GoalTwoBlocks(loc);
        }
        boolean upwardGoal = this.internalMiningGoal(loc.up(), context, locs);
        boolean downwardGoal = this.internalMiningGoal(loc.down(), context, locs);
        boolean doubleDownwardGoal = this.internalMiningGoal(loc.down(2), context, locs);
        if (upwardGoal == downwardGoal) {
            if (doubleDownwardGoal && assumeVerticalShaftMine) {
                return new GoalThreeBlocks(loc);
            }
            return new GoalTwoBlocks(loc);
        }
        if (upwardGoal) {
            return new GoalBlock(loc);
        }
        if (doubleDownwardGoal && assumeVerticalShaftMine) {
            return new GoalTwoBlocks(loc.down());
        }
        return new GoalBlock(loc.down());
    }

    public List<c_1514_x> droppedItemsScan() {
        if (!((Boolean)Baritone.settings().mineScanDroppedItems.value).booleanValue()) {
            return Collections.emptyList();
        }
        ArrayList<c_1514_x> ret = new ArrayList<c_1514_x>();
        for (N_4263_v entity : ((k_4690_i)this.ctx.world()).J_1907_R()) {
            T_2915_h block;
            n_1494_c ei;
            Z_1993_T stack;
            if (!(entity instanceof n_1494_c) || !((stack = (ei = (n_1494_c)entity).P_1922_E()).J_1907_R() instanceof v_1669_V) || !this.filter.has(block = ((v_1669_V)stack.J_1907_R()).v_4262_N())) continue;
            ret.add(entity.b_2312_j());
        }
        ret.addAll(this.anticipatedDrops.keySet());
        return ret;
    }

    public static List<c_1514_x> searchWorld(CalculationContext ctx, BlockOptionalMetaLookup filter, int max, List<c_1514_x> alreadyKnown, List<c_1514_x> blacklist, List<c_1514_x> dropped) {
        List<c_1514_x> locs = new ArrayList<c_1514_x>();
        ArrayList<T_2915_h> untracked = new ArrayList<T_2915_h>();
        for (BlockOptionalMeta bom : filter.blocks()) {
            T_2915_h block = bom.getBlock();
            if (CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains((Object)block)) {
                BetterBlockPos pf = ctx.baritone.getPlayerContext().playerFeet();
                locs.addAll(ctx.worldData.getCachedWorld().getLocationsOf(BlockUtils.blockToString(block), (Integer)Baritone.settings().maxCachedWorldScanCount.value, pf.x, pf.z, 2));
                continue;
            }
            untracked.add(block);
        }
        locs = MineProcess.prune(ctx, locs, filter, max, blacklist, dropped);
        if (!untracked.isEmpty() || ((Boolean)Baritone.settings().extendCacheOnThreshold.value).booleanValue() && locs.size() < max) {
            locs.addAll(BaritoneAPI.getProvider().getWorldScanner().scanChunkRadius(ctx.getBaritone().getPlayerContext(), filter, max, 10, 32));
        }
        locs.addAll(alreadyKnown);
        return MineProcess.prune(ctx, locs, filter, max, blacklist, dropped);
    }

    private boolean addNearby() {
        List<c_1514_x> dropped = this.droppedItemsScan();
        this.knownOreLocations.addAll(dropped);
        BetterBlockPos playerFeet = this.ctx.playerFeet();
        BlockStateInterface bsi = new BlockStateInterface(this.ctx);
        BlockOptionalMetaLookup filter = this.filterFilter();
        if (filter == null) {
            return false;
        }
        int searchDist = 10;
        double fakedBlockReachDistance = 20.0;
        for (int x = playerFeet.getX() - searchDist; x <= playerFeet.getX() + searchDist; ++x) {
            for (int y = playerFeet.getY() - searchDist; y <= playerFeet.getY() + searchDist; ++y) {
                for (int z = playerFeet.getZ() - searchDist; z <= playerFeet.getZ() + searchDist; ++z) {
                    if (!filter.has(bsi.get0(x, y, z))) continue;
                    c_1514_x pos = new c_1514_x(x, y, z);
                    if ((!((Boolean)Baritone.settings().legitMineIncludeDiagonals.value).booleanValue() || !this.knownOreLocations.stream().anyMatch(ore -> ore.distanceSq(pos) <= 2.0)) && !RotationUtils.reachable(this.ctx, pos, fakedBlockReachDistance).isPresent()) continue;
                    this.knownOreLocations.add(pos);
                }
            }
        }
        this.knownOreLocations = MineProcess.prune(new CalculationContext(this.baritone), this.knownOreLocations, filter, 64, this.blacklist, dropped);
        return true;
    }

    private static List<c_1514_x> prune(CalculationContext ctx, List<c_1514_x> locs2, BlockOptionalMetaLookup filter, int max, List<c_1514_x> blacklist, List<c_1514_x> dropped) {
        dropped.removeIf(drop -> {
            for (c_1514_x pos : locs2) {
                if (!(pos.distanceSq((z_3539_x)drop) <= 9.0) || !filter.has(ctx.get(pos.getX(), pos.getY(), pos.getZ())) || !MineProcess.plausibleToBreak(ctx, pos)) continue;
                return true;
            }
            return false;
        });
        List<c_1514_x> locs = locs2.stream().distinct().filter(pos -> !ctx.bsi.worldContainsLoadedChunk(pos.getX(), pos.getZ()) || filter.has(ctx.get(pos.getX(), pos.getY(), pos.getZ())) || dropped.contains(pos)).filter(pos -> MineProcess.plausibleToBreak(ctx, pos)).filter(pos -> {
            if (((Boolean)Baritone.settings().allowOnlyExposedOres.value).booleanValue()) {
                return MineProcess.isNextToAir(ctx, pos);
            }
            return true;
        }).filter(pos -> pos.getY() >= (Integer)Baritone.settings().minYLevelWhileMining.value).filter(pos -> pos.getY() <= (Integer)Baritone.settings().maxYLevelWhileMining.value).filter(pos -> !blacklist.contains(pos)).sorted(Comparator.comparingDouble(ctx.getBaritone().getPlayerContext().player().b_2312_j()::distanceSq)).collect(Collectors.toList());
        if (locs.size() > max) {
            return locs.subList(0, max);
        }
        return locs;
    }

    public static boolean isNextToAir(CalculationContext ctx, c_1514_x pos) {
        int radius = (Integer)Baritone.settings().allowOnlyExposedOresDistance.value;
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) > radius || !MovementHelper.isTransparent(ctx.getBlock(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean plausibleToBreak(CalculationContext ctx, c_1514_x pos) {
        if (MovementHelper.getMiningDurationTicks(ctx, pos.getX(), pos.getY(), pos.getZ(), ctx.bsi.get0(pos), true) >= 1000000.0) {
            return false;
        }
        return ctx.bsi.get0(pos.up()).J_1907_R() != a_3742_W.Z_875_P || ctx.bsi.get0(pos.down()).J_1907_R() != a_3742_W.Z_875_P;
    }

    @Override
    public void mineByName(int quantity, String ... blocks) {
        this.mine(quantity, new BlockOptionalMetaLookup(blocks));
    }

    @Override
    public void mine(int quantity, BlockOptionalMetaLookup filter) {
        this.filter = filter;
        if (this.filterFilter() == null) {
            this.filter = null;
        }
        this.desiredQuantity = quantity;
        this.knownOreLocations = new ArrayList<c_1514_x>();
        this.blacklist = new ArrayList<c_1514_x>();
        this.branchPoint = null;
        this.branchPointRunaway = null;
        this.anticipatedDrops = new HashMap<c_1514_x, Long>();
        if (filter != null) {
            this.rescan(new ArrayList<c_1514_x>(), new CalculationContext(this.baritone));
        }
    }

    private BlockOptionalMetaLookup filterFilter() {
        if (this.filter == null) {
            return null;
        }
        if (!((Boolean)Baritone.settings().allowBreak.value).booleanValue()) {
            BlockOptionalMetaLookup f = new BlockOptionalMetaLookup((BlockOptionalMeta[])this.filter.blocks().stream().filter(e -> ((List)Baritone.settings().allowBreakAnyway.value).contains(e.getBlock())).toArray(BlockOptionalMeta[]::new));
            if (f.blocks().isEmpty()) {
                this.logDirect("Unable to mine when allowBreak is false and target block is not in allowBreakAnyway!");
                return null;
            }
            return f;
        }
        return this.filter;
    }

    private static class GoalThreeBlocks
    extends GoalTwoBlocks {
        public GoalThreeBlocks(c_1514_x pos) {
            super(pos);
        }

        @Override
        public boolean isInGoal(int x, int y, int z) {
            return x == this.x && (y == this.y || y == this.y - 1 || y == this.y - 2) && z == this.z;
        }

        @Override
        public double heuristic(int x, int y, int z) {
            int xDiff = x - this.x;
            int yDiff = y - this.y;
            int zDiff = z - this.z;
            return GoalBlock.calculate(xDiff, yDiff < -1 ? yDiff + 2 : (yDiff == -1 ? 0 : yDiff), zDiff);
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 393857768;
        }

        @Override
        public String toString() {
            return String.format("GoalThreeBlocks{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z));
        }
    }
}


