/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import lightning.product.CropBlock;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BonemealableBlock;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.CactusBlock;
import lightning.product.AirBlock;
import lightning.product.n_1494_c;
import lightning.product.NetherWartBlock;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.CocoaBlock;
import lightning.product.SugarCaneBlock;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalGetToBlock;
import mods.baritone.api.api.java.baritone.api.process.IFarmProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.utils.RayTraceUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.process.BuilderProcess;
import mods.baritone.utils.BaritoneProcessHelper;

public final class FarmProcess
extends BaritoneProcessHelper
implements IFarmProcess {
    private boolean active;
    private List<c_1514_x> locations;
    private int tickCount;
    private int range;
    private c_1514_x center;
    private static final List<q_1613_l> FARMLAND_PLANTABLE = Arrays.asList(Items.MushroomBlock, Items.y_2836_h, Items.G_4691_Q, Items.WrappedMinMaxBounds, Items.l_683_e, Items.BaseCoralWallFanBlock);
    private static final List<q_1613_l> PICKUP_DROPPED = Arrays.asList(Items.MushroomBlock, Items.s_3401_U, Items.y_2836_h, Items.B_368_w, a_3742_W.E_3343_g.u_1723_Y(), Items.G_4691_Q, Items.V_3441_j, Items.WrappedMinMaxBounds, a_3742_W.A_3244_K.u_1723_Y(), Items.l_683_e, Items.BaseCoralWallFanBlock, Items.g_1096_r, Items.M_712_N, a_3742_W.l_3609_d.u_1723_Y(), a_3742_W.d_3244_b.u_1723_Y());

    public FarmProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void farm(int range, c_1514_x pos) {
        this.center = pos == null ? this.baritone.getPlayerContext().playerFeet() : pos;
        this.range = range;
        this.active = true;
        this.locations = null;
    }

    private boolean readyForHarvest(b_4507_u world, c_1514_x pos, K_4074_S state) {
        for (Harvest harvest : Harvest.values()) {
            if (harvest.block != state.J_1907_R()) continue;
            return harvest.readyToHarvest(world, pos, state);
        }
        return false;
    }

    private boolean isPlantable(Z_1993_T stack) {
        return FARMLAND_PLANTABLE.contains(stack.J_1907_R());
    }

    private boolean isBoneMeal(Z_1993_T stack) {
        return !stack.n_1700_B() && stack.J_1907_R().equals(Items.r_1970_q);
    }

    private boolean isNetherWart(Z_1993_T stack) {
        return !stack.n_1700_B() && stack.J_1907_R().equals(Items.g_1096_r);
    }

    private boolean isCocoa(Z_1993_T stack) {
        return !stack.n_1700_B() && stack.J_1907_R().equals(Items.M_712_N);
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        ArrayList<T_2915_h> scan = new ArrayList<T_2915_h>();
        for (Harvest harvest : Harvest.values()) {
            scan.add(harvest.block);
        }
        if (((Boolean)Baritone.settings().replantCrops.value).booleanValue()) {
            scan.add(a_3742_W.Z_735_d);
            scan.add(a_3742_W.G_624_v);
            if (((Boolean)Baritone.settings().replantNetherWart.value).booleanValue()) {
                scan.add(a_3742_W.C_415_h);
            }
        }
        if ((Integer)Baritone.settings().mineGoalUpdateInterval.value != 0 && this.tickCount++ % (Integer)Baritone.settings().mineGoalUpdateInterval.value == 0) {
            Baritone.getExecutor().execute(() -> {
                this.locations = BaritoneAPI.getProvider().getWorldScanner().scanChunkRadius(this.ctx, scan, 256, 10, 10);
            });
        }
        if (this.locations == null) {
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        ArrayList<c_1514_x> toBreak = new ArrayList<c_1514_x>();
        ArrayList<c_1514_x> openFarmland = new ArrayList<c_1514_x>();
        ArrayList<c_1514_x> bonemealable = new ArrayList<c_1514_x>();
        ArrayList<c_1514_x> openSoulsand = new ArrayList<c_1514_x>();
        ArrayList<c_1514_x> openLog = new ArrayList<c_1514_x>();
        block1: for (c_1514_x c_1514_x2 : this.locations) {
            BonemealableBlock ig;
            if (this.range != 0 && c_1514_x2.distanceSq(this.center) > (double)(this.range * this.range)) continue;
            K_4074_S k_4074_S = this.ctx.world().getBlockState(c_1514_x2);
            boolean bl = this.ctx.world().getBlockState(c_1514_x2.up()).J_1907_R() instanceof AirBlock;
            if (k_4074_S.J_1907_R() == a_3742_W.Z_735_d) {
                if (!bl) continue;
                openFarmland.add(c_1514_x2);
                continue;
            }
            if (k_4074_S.J_1907_R() == a_3742_W.C_415_h) {
                if (!bl) continue;
                openSoulsand.add(c_1514_x2);
                continue;
            }
            if (k_4074_S.J_1907_R() == a_3742_W.G_624_v) {
                for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                    if (!(this.ctx.world().getBlockState(c_1514_x2.offset(direction)).J_1907_R() instanceof AirBlock)) continue;
                    openLog.add(c_1514_x2);
                    continue block1;
                }
                continue;
            }
            if (this.readyForHarvest(this.ctx.world(), c_1514_x2, k_4074_S)) {
                toBreak.add(c_1514_x2);
                continue;
            }
            if (!(k_4074_S.J_1907_R() instanceof BonemealableBlock) || !(ig = (BonemealableBlock)((Object)k_4074_S.J_1907_R())).n_1700_B((BlockGetter)this.ctx.world(), c_1514_x2, k_4074_S, true) || !ig.n_1700_B(this.ctx.world(), this.ctx.world().w_1457_N, c_1514_x2, k_4074_S)) continue;
            bonemealable.add(c_1514_x2);
        }
        this.baritone.getInputOverrideHandler().clearAllKeys();
        for (c_1514_x c_1514_x3 : toBreak) {
            Optional<Rotation> optional = RotationUtils.reachable(this.ctx, c_1514_x3);
            if (!optional.isPresent() || !isSafeToCancel) continue;
            this.baritone.getLookBehavior().updateTarget(optional.get(), true);
            MovementHelper.switchToBestToolFor(this.ctx, this.ctx.world().getBlockState(c_1514_x3));
            if (this.ctx.isLookingAt(c_1514_x3)) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_LEFT, true);
            }
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        ArrayList<c_1514_x> both = new ArrayList<c_1514_x>(openFarmland);
        both.addAll(openSoulsand);
        for (c_1514_x c_1514_x4 : both) {
            HitResult result;
            boolean bl = openSoulsand.contains(c_1514_x4);
            Optional<Rotation> rot = RotationUtils.reachableOffset(this.ctx, c_1514_x4, new e_2866_D((double)c_1514_x4.getX() + 0.5, c_1514_x4.getY() + 1, (double)c_1514_x4.getZ() + 0.5), this.ctx.playerController().getBlockReachDistance(), false);
            if (!rot.isPresent() || !isSafeToCancel || !this.baritone.getInventoryBehavior().throwaway(true, bl ? this::isNetherWart : this::isPlantable) || !((result = RayTraceUtils.rayTraceTowards(this.ctx.player(), rot.get(), this.ctx.playerController().getBlockReachDistance())) instanceof BlockHitResult) || ((BlockHitResult)result).J_1907_R() != b_257_Y.J_1907_R) continue;
            this.baritone.getLookBehavior().updateTarget(rot.get(), true);
            if (this.ctx.isLookingAt(c_1514_x4)) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
            }
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        for (c_1514_x c_1514_x5 : openLog) {
            for (Object dir : b_257_Y.R_4764_Y.n_1700_B) {
                HitResult result;
                e_2866_D faceCenter;
                Optional<Rotation> rot;
                if (!(this.ctx.world().getBlockState(c_1514_x5.offset((b_257_Y)dir)).J_1907_R() instanceof AirBlock) || !(rot = RotationUtils.reachableOffset(this.ctx, c_1514_x5, faceCenter = e_2866_D.n_1700_B(c_1514_x5).P_1922_E(e_2866_D.J_1907_R(((b_257_Y)dir).M_182_A()).n_1700_B(0.5)), this.ctx.playerController().getBlockReachDistance(), false)).isPresent() || !isSafeToCancel || !this.baritone.getInventoryBehavior().throwaway(true, this::isCocoa) || !((result = RayTraceUtils.rayTraceTowards(this.ctx.player(), rot.get(), this.ctx.playerController().getBlockReachDistance())) instanceof BlockHitResult) || ((BlockHitResult)result).J_1907_R() != dir) continue;
                this.baritone.getLookBehavior().updateTarget(rot.get(), true);
                if (this.ctx.isLookingAt(c_1514_x5)) {
                    this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
                }
                return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            }
        }
        for (c_1514_x c_1514_x6 : bonemealable) {
            Optional<Rotation> optional = RotationUtils.reachable(this.ctx, c_1514_x6);
            if (!optional.isPresent() || !isSafeToCancel || !this.baritone.getInventoryBehavior().throwaway(true, this::isBoneMeal)) continue;
            this.baritone.getLookBehavior().updateTarget(optional.get(), true);
            if (this.ctx.isLookingAt(c_1514_x6)) {
                this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
            }
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        if (calcFailed) {
            this.logDirect("Farm failed");
            if (((Boolean)Baritone.settings().notificationOnFarmFail.value).booleanValue()) {
                this.logNotification("Farm failed", true);
            }
            this.onLostControl();
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        ArrayList<Goal> arrayList = new ArrayList<Goal>();
        for (c_1514_x c_1514_x7 : toBreak) {
            arrayList.add(new BuilderProcess.GoalBreak(c_1514_x7));
        }
        if (this.baritone.getInventoryBehavior().throwaway(false, this::isPlantable)) {
            for (c_1514_x c_1514_x8 : openFarmland) {
                arrayList.add(new GoalBlock(c_1514_x8.up()));
            }
        }
        if (this.baritone.getInventoryBehavior().throwaway(false, this::isNetherWart)) {
            for (c_1514_x c_1514_x9 : openSoulsand) {
                arrayList.add(new GoalBlock(c_1514_x9.up()));
            }
        }
        if (this.baritone.getInventoryBehavior().throwaway(false, this::isCocoa)) {
            for (c_1514_x c_1514_x10 : openLog) {
                for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                    if (!(this.ctx.world().getBlockState(c_1514_x10.offset(direction)).J_1907_R() instanceof AirBlock)) continue;
                    arrayList.add(new GoalGetToBlock(c_1514_x10.offset(direction)));
                }
            }
        }
        if (this.baritone.getInventoryBehavior().throwaway(false, this::isBoneMeal)) {
            for (c_1514_x c_1514_x11 : bonemealable) {
                arrayList.add(new GoalBlock(c_1514_x11));
            }
        }
        for (N_4263_v n_4263_v : this.ctx.entities()) {
            n_1494_c ei;
            if (!(n_4263_v instanceof n_1494_c) || !n_4263_v.M_1641_O() || !PICKUP_DROPPED.contains((ei = (n_1494_c)n_4263_v).P_1922_E().J_1907_R())) continue;
            arrayList.add(new GoalBlock(new c_1514_x(n_4263_v.s_4990_V().J_1907_R, n_4263_v.s_4990_V().R_4764_Y + 0.1, n_4263_v.s_4990_V().G_564_y)));
        }
        return new PathingCommand(new GoalComposite(arrayList.toArray(new Goal[0])), PathingCommandType.SET_GOAL_AND_PATH);
    }

    @Override
    public void onLostControl() {
        this.active = false;
    }

    @Override
    public String displayName0() {
        return "Farming";
    }

    private static enum Harvest {
        WHEAT((CropBlock)a_3742_W.l_4088_R),
        CARROTS((CropBlock)a_3742_W.P_2295_B),
        POTATOES((CropBlock)a_3742_W.U_1697_c),
        BEETROOT((CropBlock)a_3742_W.FreeCam),
        PUMPKIN(a_3742_W.A_3244_K, state -> true),
        MELON(a_3742_W.E_3343_g, state -> true),
        NETHERWART(a_3742_W.W_3729_Q, state -> state.R_4764_Y(NetherWartBlock.P_4830_p) >= 3),
        COCOA(a_3742_W.U_3823_u, state -> state.R_4764_Y(CocoaBlock.P_4830_p) >= 2),
        SUGARCANE(a_3742_W.l_3609_d, null){

            @Override
            public boolean readyToHarvest(b_4507_u world, c_1514_x pos, K_4074_S state) {
                if (((Boolean)Baritone.settings().replantCrops.value).booleanValue()) {
                    return world.getBlockState(pos.down()).J_1907_R() instanceof SugarCaneBlock;
                }
                return true;
            }
        }
        ,
        CACTUS(a_3742_W.d_3244_b, null){

            @Override
            public boolean readyToHarvest(b_4507_u world, c_1514_x pos, K_4074_S state) {
                if (((Boolean)Baritone.settings().replantCrops.value).booleanValue()) {
                    return world.getBlockState(pos.down()).J_1907_R() instanceof CactusBlock;
                }
                return true;
            }
        };

        public final T_2915_h block;
        public final Predicate<K_4074_S> readyToHarvest;

        private Harvest(CropBlock blockCrops) {
            this(blockCrops, blockCrops::t_148_a);
        }

        private Harvest(T_2915_h block, Predicate<K_4074_S> readyToHarvest) {
            this.block = block;
            this.readyToHarvest = readyToHarvest;
        }

        public boolean readyToHarvest(b_4507_u world, c_1514_x pos, K_4074_S state) {
            return this.readyToHarvest.test(state);
        }
    }
}



